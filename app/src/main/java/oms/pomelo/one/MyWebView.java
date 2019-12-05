package oms.pomelo.one;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Sherry on 2019/12/5
 * 解决WebView中的onPageFinish方法的调用实际不准确（不同系统版本跟浏览器内核会有影响）
 * 通过监听onDraw方法，WebView显示完成后必调的方法
*/

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mOnDrawListener != null) {
            mOnDrawListener.onDrawCallBack();
        }
    }

    private OnDrawListener mOnDrawListener;
    public void setOnDrawListener(OnDrawListener onDrawListener) {
        mOnDrawListener = onDrawListener;
    }

    public interface OnDrawListener {
        public void onDrawCallBack();
    }

}
