package oms.pomelo.one.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;


public class PermissionsHandler {

    public static final int PERMISSION_REQUEST_CODE = 44;
    public static final int PERMISSION_REQUEST_SETTING = 45;
    private String[] mNeedPermissions;//需要的权限

    private Activity mActivity;
    private PermissionsChecker mChecker;
    private onAllNeedPermissionsGrantedListener mListener;
    private boolean isShowing = false;
    private AlertDialog mDialog;

    public PermissionsHandler(Activity activity, String[] mNeedPermissions) {
        mChecker = new PermissionsChecker(activity);
        this.mActivity = activity;
        this.mNeedPermissions = mChecker.filterPermission(mNeedPermissions, activity);
    }

    public void onDestroy() {
        if (mChecker != null) mChecker.onDestroy();
        mActivity = null;
        mDialog = null;
        mChecker = null;
        mNeedPermissions = null;
        mListener = null;
    }

    /**
     * 检测是不是所有的权限都有了
     *
     * @return true 所有的都有了,否则返回 false
     */
    public boolean checkAllPermissions() {
        if (mChecker != null && mChecker.shouldCheckPermission()) {
            return mChecker.checkSelfPermissions(mNeedPermissions);
        }
        return true;
    }

    /**
     * 接收来自Activity的权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (hasDestroy()) {
            return;
        }
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults != null && grantResults.length > 0 && hasAllPermissionsGranted(grantResults)) {
                allPermissionsGranted();
            } else {
                showSettingPermissionDialog(permissions);
            }

        }
    }

    /**
     * @param grantResults
     * @return true 有所有权限
     * false 还有未申请的权限
     */
    private boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    // 请求权限兼容低版本
    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(mActivity, permissions, PERMISSION_REQUEST_CODE);
    }

    // 全部权限均已获取
    private void allPermissionsGranted() {
        if (mListener != null) {
            mListener.onAllNeedPermissionsGranted();
        }
        if (mDialog != null) {
            mDialog.dismiss();
        }
        onDestroy();
    }

    public void startRequestNeedPermissions() {
        if (mChecker != null && mChecker.shouldCheckPermission()) {
            requestNeedPermissions();
        } else {
            allPermissionsGranted();
        }
    }

    private void requestNeedPermissions() {
        if (mChecker == null) return;
        mNeedPermissions = mChecker.filterPermission(mNeedPermissions, mActivity);
        if (mChecker.checkSelfPermissions(mNeedPermissions)) {
            allPermissionsGranted(); // 全部权限都已获取
        } else {
            requestPermissions(mNeedPermissions); // 请求权限
        }
    }


    // 显示缺失权限提示
    private void showSettingPermissionDialog(String[] permissions) {
        if (permissions != null && permissions.length > 0) {

            String title = "无法获取权限";
            String content = "为了App功能正常的使用\n你需要在手机的 系统设置>隐私>授权管理 中\n允许App使用该权限";
//            DialogUtils.showGrantTipsDialog(mActivity, mActivity.getWindow().getDecorView(), title, content, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!hasDestroy()) {
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
//                        mActivity.startActivityForResult(intent, PERMISSION_REQUEST_SETTING);
//                    }
//                }
//            }, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onDestroy();
//                    if (mListener != null) {
//                        mListener.onPermissionsDenied();
//                    }
//                }
//            });

        }
    }

    /**
     * 接收来自设置界面回来的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERMISSION_REQUEST_SETTING) {
            requestNeedPermissions();
        }
    }

    public interface onAllNeedPermissionsGrantedListener {
        void onAllNeedPermissionsGranted();//全部许可了,已经获得了所有权限

        void onPermissionsDenied();//被拒绝了,只要有一个权限被拒绝那么就会调用

        void hasLockForever();//用户已经永久的拒绝了

        void onBeforeRequestFinalPermissions(PermissionsHandler helper);//被拒绝后,在最后一次申请权限之前
    }


    public void setonAllNeedPermissionsGrantedListener(onAllNeedPermissionsGrantedListener listener) {
        this.mListener = listener;
    }

    /**
     * 是不是已经销毁了
     *
     * @return
     */
    public boolean hasDestroy() {
        if (mActivity == null || mChecker == null || mNeedPermissions == null) {
            return true;
        }
        return false;
    }
}
