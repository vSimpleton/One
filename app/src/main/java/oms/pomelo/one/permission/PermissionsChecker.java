package oms.pomelo.one.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionsChecker {

    private Activity mActivity;

    public PermissionsChecker(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 是否需要检测权限
     *
     * @return
     */
    public boolean shouldCheckPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否用于某项权限
     *
     * @param permission
     * @return true
     * false
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkSelfPermission(String permission) {
        return ContextCompat.checkSelfPermission(mActivity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void onDestroy() {
        mActivity = null;
    }

    /**
     * @param permissions
     * @return true 拥有所有权限,否则返回 false
     */
    public boolean checkSelfPermissions(String... permissions) {
        if (permissions.length == 0) {
            return true;
        }
        for (String permission : permissions) {
            if (!checkSelfPermission(permission)) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldShowRequestPermissions(String... permissions) {
        if (permissions.length == 0) {
            return false;
        }
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                return true;
            }
        }
        return false;

    }

    public String[] filterPermission(String[] permissions, Context context) {
        /**
         * 此处将授权过的权限筛选掉，不然在同时请求多个权限的时候会有bug
         */
        ArrayList<String> permissionsList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            }
        }
        String[] unGrandPermissionArr = permissionsList.toArray(new String[permissionsList.size()]);
        return unGrandPermissionArr;
    }
}
