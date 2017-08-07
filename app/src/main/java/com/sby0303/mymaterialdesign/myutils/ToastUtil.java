package com.sby0303.mymaterialdesign.myutils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context, String toastInfo) {
        Toast.makeText(context, toastInfo, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String toastInfo) {
        Toast.makeText(context, toastInfo, Toast.LENGTH_LONG).show();
    }
}
