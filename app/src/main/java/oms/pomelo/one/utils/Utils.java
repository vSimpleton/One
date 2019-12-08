package oms.pomelo.one.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.FloatRange;

/**
 * Created by Sherry on 2019/12/3
 */

public class Utils {

    public static float sDensity;
    public static float sDensityDpi;
    public static int sScreenW;
    public static int sScreenH;

    public static int sRelativeScreenW = 720;
    public static int sRelativeScreenH = 1280;
    private static Display mDis;

    public static void init(Activity activity) {
        mDis = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        mDis.getRealMetrics(dm);
        int h = dm.heightPixels;
        int w = dm.widthPixels;
        sScreenW = w < h ? w : h;
        sScreenH = w < h ? h : w;
        sDensity = dm.density;
        sDensityDpi = dm.densityDpi;
    }

    public static View.OnTouchListener getAlphaTouchListener() {
        View.OnTouchListener mTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setAlpha(0.5f);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_UP: {
                        v.setAlpha(1f);
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        };
        return mTouchListener;
    }

    public static View.OnTouchListener getTouchBackListener(@FloatRange(from = 0f, to = 1.0f) final float rate) {

        View.OnTouchListener mTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        AnimatorSet set = new AnimatorSet();
                        Animator animatorX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, rate);
                        Animator animatorY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, rate);
                        Animator animatorAlpha = ObjectAnimator.ofFloat(v, "alpha", 1.0f, rate * 2 / 3f);
                        set.play(animatorX).with(animatorY).with(animatorAlpha);
                        set.setDuration(100);
                        set.start();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_UP: {

                        AnimatorSet set = new AnimatorSet();
                        Animator animatorX = ObjectAnimator.ofFloat(v, "scaleX", v.getScaleX(), 1.0f);
                        Animator animatorY = ObjectAnimator.ofFloat(v, "scaleY", v.getScaleY(), 1.0f);
                        Animator animatorAlpha = ObjectAnimator.ofFloat(v, "alpha", v.getAlpha(), 1.0f);
                        set.play(animatorX).with(animatorY).with(animatorAlpha);
                        set.setDuration(200);
                        set.start();

                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        };
        return mTouchListener;
    }

    public static View.OnTouchListener getScaleTouchListener() {
        return getScaleTouchListener(0.9f);
    }


    public static View.OnTouchListener getScaleTouchListener(@FloatRange(from = 0f, to = 1.0f) final float rate) {

        View.OnTouchListener mTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        AnimatorSet set = new AnimatorSet();
                        Animator animatorX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, rate);
                        Animator animatorY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, rate);
                        set.play(animatorX).with(animatorY);
                        set.setDuration(100);
                        set.start();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_UP: {

                        AnimatorSet set = new AnimatorSet();
                        Animator animatorX = ObjectAnimator.ofFloat(v, "scaleX", v.getScaleX(), 1.0f);
                        Animator animatorY = ObjectAnimator.ofFloat(v, "scaleY", v.getScaleY(), 1.0f);
                        set.play(animatorX).with(animatorY);
                        set.setDuration(200);
                        set.start();

                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        };
        return mTouchListener;
    }

    public static String getDateFormat(String format) {
        String msg = null;
        String year = format.substring(0, 4);
        String month = format.substring(5, 7);
        if (month.equals("12")) {
            msg = "Dec." + year;
        } else if (month.equals("1")) {
            msg = "Jan." + year;
        } else if (month.equals("2")) {
            msg = "Feb." + year;
        } else if (month.equals("3")) {
            msg = "Mar." + year;
        } else if (month.equals("4")) {
            msg = "Apr." + year;
        } else if (month.equals("5")) {
            msg = "May." + year;
        } else if (month.equals("6")) {
            msg = "Jun." + year;
        } else if (month.equals("7")) {
            msg = "Jul." + year;
        } else if (month.equals("8")) {
            msg = "Aug." + year;
        } else if (month.equals("9")) {
            msg = "Sept." + year;
        } else if (month.equals("10")) {
            msg = "Oct." + year;
        } else if (month.equals("11")) {
            msg = "Nov." + year;
        }
        return msg;
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 动态时间流转换规则
     * @param time 动态发布的时间, seconds
     * @return 显示的时间
     */
    public static String getPublishTime(long time) {
        String msg;
        long currentTime = System.currentTimeMillis() / 1000; //获取当前的时间
        if (currentTime - time < 60) {
            msg = "刚刚发布";
        } else if (currentTime - time < 3600) {
            msg = String.format("%s分钟前发布", (currentTime - time) / 60);
        } else if (currentTime - time > 3600 * 24) {
            msg = "更早前发布";
        } else {
            msg = String.format("%s小时前发布", (currentTime - time) / 3600);
        }
        return msg;
    }

}
