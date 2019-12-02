package oms.pomelo.one.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
}
