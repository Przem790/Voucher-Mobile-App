package com.example.przemo.voucherapp.Fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by PrzemO on 13.01.2018.
 */

public class CRAZY_RAS{

    private static CRAZY_RAS instance;
    private static Typeface typeface;

    public static CRAZY_RAS getInstance(Context context) {
        synchronized (CRAZY_RAS.class) {
            if (instance == null) {
                instance = new CRAZY_RAS();
                typeface = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/crazy_ras.ttf");
            }
            return instance;
        }
    }

    public Typeface getTypeFace() {
        return typeface;
    }
}