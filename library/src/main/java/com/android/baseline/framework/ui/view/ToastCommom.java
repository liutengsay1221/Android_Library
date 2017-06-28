package com.android.baseline.framework.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.baseline.R;

/**
 * @author liuteng
 * @version [2017/2/23 17:14]
 */

public class ToastCommom {
    private static ToastCommom toastCommom;

    private Toast toast;

    public ToastCommom(){
    }

    public static ToastCommom createToastConfig(){
        if (toastCommom==null) {
            toastCommom = new ToastCommom();
        }
        return toastCommom;
    }

    /**
     * 显示Toast
     * @param context
     * @param tvString
     */

    public void ToastShow(Context context, String tvString){
        View layout = LayoutInflater.from(context).inflate(R.layout.toast_commom,null);
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(tvString);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
