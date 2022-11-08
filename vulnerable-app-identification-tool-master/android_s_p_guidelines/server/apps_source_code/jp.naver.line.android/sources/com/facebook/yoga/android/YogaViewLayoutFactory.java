package com.facebook.yoga.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory;
import android.view.View;

public class YogaViewLayoutFactory implements Factory {
    private static YogaViewLayoutFactory sYogaViewLayoutFactory;

    public static YogaViewLayoutFactory getInstance() {
        if (sYogaViewLayoutFactory == null) {
            sYogaViewLayoutFactory = new YogaViewLayoutFactory();
        }
        return sYogaViewLayoutFactory;
    }

    YogaViewLayoutFactory() {
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (YogaLayout.class.getSimpleName().equals(str)) {
            return new YogaLayout(context, attributeSet);
        }
        return VirtualYogaLayout.class.getSimpleName().equals(str) ? new VirtualYogaLayout(context, attributeSet) : null;
    }
}
