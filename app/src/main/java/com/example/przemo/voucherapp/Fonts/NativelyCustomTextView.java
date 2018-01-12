package com.example.przemo.voucherapp.Fonts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by PrzemO on 13.01.2018.
 */

public class NativelyCustomTextView extends android.support.v7.widget.AppCompatTextView {

    public NativelyCustomTextView(Context context) {
        super(context);
        setTypeface(CRAZY_RAS.getInstance(context).getTypeFace());
    }

    public NativelyCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(CRAZY_RAS.getInstance(context).getTypeFace());
    }

    public NativelyCustomTextView(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(CRAZY_RAS.getInstance(context).getTypeFace());
    }
}