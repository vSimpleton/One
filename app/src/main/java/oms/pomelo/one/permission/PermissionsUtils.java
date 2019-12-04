package oms.pomelo.one.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public class PermissionsUtils {

    private static PermissionsHandler permissionsHandler;

    public interface OnGrantListener {
        void onGrantedSuccess();

        void onGrantedFailed();
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionsHandler != null) {
            permissionsHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (permissionsHandler != null) {
            permissionsHandler.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static void requestPermission(String[] permissions, Context context, final OnGrantListener listener) {
        requestPermission(permissions, context, listener, false);
    }

    public static void requestPermission(String[] permissions, Context context, final OnGrantListener listener, boolean isOnlyCheckPermission) {
        if (permissionsHandler == null || permissionsHandler.hasDestroy()) {
            permissionsHandler = new PermissionsHandler((Activity) context, permissions);
        }

        if (permissionsHandler.checkAllPermissions()) {
            permissionsHandler.onDestroy();
            if (listener != null) {
                listener.onGrantedSuccess();
            }
        } else {
            if (isOnlyCheckPermission) {
                permissionsHandler.onDestroy();
                if (listener != null) {
                    listener.onGrantedFailed();
                }
            } else {
                //申请权限
                permissionsHandler.startRequestNeedPermissions();
            }
        }
        permissionsHandler.setonAllNeedPermissionsGrantedListener(new PermissionsHandler.onAllNeedPermissionsGrantedListener() {
            @Override
            public void onAllNeedPermissionsGranted() {
                if (listener != null) {
                    listener.onGrantedSuccess();
                }
            }

            @Override
            public void onPermissionsDenied() {
                if (listener != null) {
                    listener.onGrantedFailed();
                }
            }

            @Override
            public void hasLockForever() {

            }

            @Override
            public void onBeforeRequestFinalPermissions(PermissionsHandler helper) {
            }
        });
    }

    public static void onDestroy() {
        if (permissionsHandler != null) {
            permissionsHandler.onDestroy();
            permissionsHandler = null;
        }
    }

    public static boolean checkPermission(String[] permissions, Context context, OnGrantListener onGrantListener) {
        boolean hasPermission = false;
        if (permissionsHandler == null || permissionsHandler.hasDestroy()) {
            permissionsHandler = new PermissionsHandler((Activity) context, permissions);
        }
        if (permissionsHandler.checkAllPermissions()) {
            hasPermission = true;
        }
        permissionsHandler.onDestroy();
        return hasPermission;
    }
}
