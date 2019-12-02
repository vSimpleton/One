package oms.pomelo.one.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import oms.pomelo.one.R;

/**
 * Created by Sherry on 2019/12/2
 */

public class ToastUtil {

    public static void showToast(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_img, null);
        ImageView image = view.findViewById(R.id.iv_toast);
        image.setImageResource(R.drawable.ic_toast_doraemon);
        TextView text = view.findViewById(R.id.tv_toast);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public static void showTextToast(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_text, null);
        TextView text = view.findViewById(R.id.tv_toast);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

}
