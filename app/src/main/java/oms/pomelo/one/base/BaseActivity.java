package oms.pomelo.one.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import oms.pomelo.one.permission.PermissionsUtils;
import oms.pomelo.one.utils.ToastUtil;

/**
 * Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    private boolean mPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHandler.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        mPaused = false;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPaused = true;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityHandler.getInstance().finishActivity(this);
        PermissionsUtils.onDestroy();
    }

    /**
     * 判断activity是否pause
     */
    public boolean isPaused() {
        return mPaused;
    }

    public void toast(String text) {
        ToastUtil.showTextToast(this, text);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PermissionsUtils.onActivityResult(requestCode, resultCode, data);
    }
}
