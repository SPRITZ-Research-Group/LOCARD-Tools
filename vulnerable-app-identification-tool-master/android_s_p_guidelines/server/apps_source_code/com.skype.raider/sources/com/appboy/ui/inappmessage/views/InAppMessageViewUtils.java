package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.appboy.b.a.i;
import com.appboy.e.n;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.support.ViewUtils;
import java.util.List;

public class InAppMessageViewUtils {
    private static final String TAG = c.a(InAppMessageViewUtils.class);

    public static void setImage(Bitmap bitmap, ImageView imageView) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void setIcon(Context context, String icon, int iconColor, int iconBackgroundColor, TextView textView) {
        if (isValidIcon(icon)) {
            try {
                textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf"));
                textView.setText(icon);
                setTextViewColor(textView, iconColor);
                if (textView.getBackground() != null) {
                    setDrawableColor(textView.getBackground(), iconBackgroundColor, context.getResources().getColor(R.color.com_appboy_inappmessage_icon_background));
                } else {
                    setViewBackgroundColor(textView, iconBackgroundColor);
                }
            } catch (Exception e) {
                c.d(TAG, "Caught exception setting icon typeface. Not rendering icon.", e);
            }
        }
    }

    public static void setButtons(List<View> buttonViews, View buttonLayoutView, int defaultColor, List<n> messageButtons) {
        if (messageButtons == null || messageButtons.size() == 0) {
            ViewUtils.removeViewFromParent(buttonLayoutView);
            return;
        }
        for (int i = 0; i < buttonViews.size(); i++) {
            if (messageButtons.size() <= i) {
                ((View) buttonViews.get(i)).setVisibility(8);
            } else if (buttonViews.get(i) instanceof Button) {
                Button button = (Button) buttonViews.get(i);
                n messageButton = (n) messageButtons.get(i);
                button.setText(messageButton.d());
                setTextViewColor(button, messageButton.f());
                setDrawableColor(button.getBackground(), messageButton.e(), defaultColor);
            }
        }
    }

    public static void setFrameColor(View view, Integer color) {
        if (color != null) {
            view.setBackgroundColor(color.intValue());
        }
    }

    public static void setTextViewColor(TextView textView, int color) {
        if (isValidInAppMessageColor(color)) {
            textView.setTextColor(color);
        }
    }

    public static void setViewBackgroundColor(View view, int color) {
        if (isValidInAppMessageColor(color)) {
            view.setBackgroundColor(color);
        }
    }

    public static void setViewBackgroundColorFilter(View view, int color, int defaultColor) {
        if (isValidInAppMessageColor(color)) {
            view.getBackground().setColorFilter(color, Mode.MULTIPLY);
        } else {
            view.getBackground().setColorFilter(defaultColor, Mode.MULTIPLY);
        }
    }

    public static void setDrawableColor(Drawable drawable, int color, int defaultColor) {
        if (drawable instanceof GradientDrawable) {
            setDrawableColor((GradientDrawable) drawable, color, defaultColor);
        } else if (isValidInAppMessageColor(color)) {
            drawable.setColorFilter(color, Mode.MULTIPLY);
        } else {
            drawable.setColorFilter(defaultColor, Mode.MULTIPLY);
        }
    }

    public static void setDrawableColor(GradientDrawable gradientDrawable, int color, int defaultColor) {
        if (isValidInAppMessageColor(color)) {
            gradientDrawable.setColor(color);
        } else {
            gradientDrawable.setColor(defaultColor);
        }
    }

    public static boolean isValidInAppMessageColor(int color) {
        return color != 0;
    }

    public static boolean isValidIcon(String icon) {
        return icon != null;
    }

    protected static void resetMessageMarginsIfNecessary(TextView messageView, TextView headerView) {
        if (headerView == null && messageView != null) {
            LayoutParams layoutParams = new LayoutParams(messageView.getLayoutParams().width, messageView.getLayoutParams().height);
            layoutParams.setMargins(0, 0, 0, 0);
            messageView.setLayoutParams(layoutParams);
        }
    }

    protected static void resetButtonSizesIfNecessary(List<View> buttonViews, List<n> messageButtons) {
        if (messageButtons != null && messageButtons.size() == 1) {
            ((View) buttonViews.get(0)).setLayoutParams(new LayoutParams(0, -2, 1.0f));
        }
    }

    public static void closeInAppMessageOnKeycodeBack() {
        c.b(TAG, "Back button intercepted by in-app message view, closing in-app message.");
        AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
    }

    public static void setTextAlignment(TextView textView, i textAlign) {
        if (textAlign.equals(i.START)) {
            textView.setGravity(8388611);
        } else if (textAlign.equals(i.END)) {
            textView.setGravity(8388613);
        } else if (textAlign.equals(i.CENTER)) {
            textView.setGravity(17);
        }
    }
}
