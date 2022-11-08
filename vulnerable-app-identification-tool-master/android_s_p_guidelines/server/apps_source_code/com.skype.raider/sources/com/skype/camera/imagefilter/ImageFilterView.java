package com.skype.camera.imagefilter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class ImageFilterView extends AppCompatImageView {
    private Uri a;
    private String b;
    private EventReporter c;

    public ImageFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageFilterView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ImageFilterView(Context context) {
        this(context, null);
    }

    public ImageFilterView(Context context, EventReporter eventReporter) {
        this(context);
        this.c = eventReporter;
    }

    public void setSrc(Uri src) {
        this.a = src;
        e();
    }

    public void setFilter(String filter) {
        this.b = filter;
        e();
    }

    private void e() {
        if (this.a != null && this.b != null) {
            LUTProcessor.a(getContext()).a(this.a, this.b, this, this.c);
        }
    }
}
