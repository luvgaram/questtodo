package com.questtodo.questtodo.utils;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


/**
 * Created by eunjooim on 15. 9. 1..
 */
public class ScreenMesure {

    private Context context;

    public ScreenMesure(Context context) {
        this.context = context;
    }

    public Point getRealSize(Display display) {
        Point result = new Point();
        Method rawH;
        try {
            rawH = Display.class.getMethod("getRawHeight");
            Method rawW = Display.class.getMethod("getRawWidth");
            result.x = (Integer) rawW.invoke(display);
            result.y = (Integer) rawH.invoke(display);
            return result;
        } catch (Throwable e) {
            return null;
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public Point getSize(Display display) {
        Point result = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics metrics = new DisplayMetrics();
            display.getRealMetrics(metrics);
            result.x = metrics.widthPixels;
            result.y = metrics.heightPixels;
        } else if (Build.VERSION.SDK_INT >= 14) {
            result = getRealSize(display);
        } else if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(result);
        } else {
            result.x = display.getWidth();
            result.y = display.getHeight();
        }
        return result;
    }

    public int getScreenStats() {

        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        Point pxSize = getSize(display);

        Point dpSize = new Point();
        dpSize.x = (int) (pxSize.x / metrics.density);
        dpSize.y = (int) (pxSize.y / metrics.density);

        return Math.min(dpSize.x, dpSize.y);
    }
}
