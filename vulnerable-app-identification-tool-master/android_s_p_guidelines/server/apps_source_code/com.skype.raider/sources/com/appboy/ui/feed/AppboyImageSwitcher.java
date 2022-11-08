package com.appboy.ui.feed;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.widget.ImageSwitcher;
import com.appboy.f.c;
import com.appboy.ui.R;

public class AppboyImageSwitcher extends ImageSwitcher {
    private static final String TAG = c.a(AppboyImageSwitcher.class);
    private Drawable mReadIcon;
    private Drawable mUnReadIcon;

    public AppboyImageSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.com_appboy_ui_feed_AppboyImageSwitcher);
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int offset = typedArray.getIndex(i);
                Drawable drawable;
                if (offset == R.styleable.com_appboy_ui_feed_AppboyImageSwitcher_appboyFeedCustomReadIcon) {
                    drawable = typedArray.getDrawable(offset);
                    if (drawable != null) {
                        this.mReadIcon = drawable;
                    }
                } else if (typedArray.getIndex(i) == R.styleable.com_appboy_ui_feed_AppboyImageSwitcher_appboyFeedCustomUnReadIcon) {
                    drawable = typedArray.getDrawable(offset);
                    if (drawable != null) {
                        this.mUnReadIcon = drawable;
                    }
                }
            }
            typedArray.recycle();
        } catch (Exception e) {
            c.c(TAG, "Error while checking for custom drawable.", e);
        }
    }

    public Drawable getUnReadIcon() {
        return this.mUnReadIcon;
    }

    public Drawable getReadIcon() {
        return this.mReadIcon;
    }

    @VisibleForTesting
    public void setUnReadIcon(Drawable unReadIcon) {
        this.mUnReadIcon = unReadIcon;
    }

    @VisibleForTesting
    public void setReadIcon(Drawable readIcon) {
        this.mReadIcon = readIcon;
    }
}
