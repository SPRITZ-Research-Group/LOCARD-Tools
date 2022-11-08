package com.appboy.ui.inappmessage.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.e.d;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageImageView;
import com.appboy.ui.inappmessage.IInAppMessageImageView;
import com.appboy.ui.inappmessage.config.AppboyInAppMessageParams;
import com.appboy.ui.support.ViewUtils;
import java.util.ArrayList;
import java.util.List;

public class AppboyInAppMessageFullView extends AppboyInAppMessageImmersiveBaseView {
    private static final String TAG = c.a(AppboyInAppMessageFullView.class);
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;
    private View mSimpleDraweeView;

    public AppboyInAppMessageFullView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void inflateStubViews(Activity activity, d inAppMessage) {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_full_drawee_stub);
            setInAppMessageImageViewAttributes(activity, inAppMessage, this.mSimpleDraweeView);
            return;
        }
        this.mAppboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_full_imageview_stub);
        setInAppMessageImageViewAttributes(activity, inAppMessage, this.mAppboyInAppMessageImageView);
    }

    public void setMessageBackgroundColor(int color) {
        if (getMessageBackgroundObject().getBackground() instanceof GradientDrawable) {
            InAppMessageViewUtils.setViewBackgroundColorFilter(findViewById(R.id.com_appboy_inappmessage_full), color, getContext().getResources().getColor(R.color.com_appboy_inappmessage_background_light));
        } else {
            super.setMessageBackgroundColor(color);
        }
    }

    public List<View> getMessageButtonViews() {
        List<View> buttonViews = new ArrayList();
        if (findViewById(R.id.com_appboy_inappmessage_full_button_one) != null) {
            buttonViews.add(findViewById(R.id.com_appboy_inappmessage_full_button_one));
        }
        if (findViewById(R.id.com_appboy_inappmessage_full_button_two) != null) {
            buttonViews.add(findViewById(R.id.com_appboy_inappmessage_full_button_two));
        }
        return buttonViews;
    }

    public View getMessageButtonsView() {
        return findViewById(R.id.com_appboy_inappmessage_full_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_full_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_full_header_text);
    }

    public View getFrameView() {
        return findViewById(R.id.com_appboy_inappmessage_full_frame);
    }

    public View getMessageCloseButtonView() {
        return findViewById(R.id.com_appboy_inappmessage_full_close_button);
    }

    public View getMessageClickableView() {
        return findViewById(R.id.com_appboy_inappmessage_full);
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }

    public TextView getMessageIconView() {
        return null;
    }

    public View getMessageBackgroundObject() {
        return findViewById(R.id.com_appboy_inappmessage_full);
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        super.resetMessageMargins(imageRetrievalSuccessful);
        findViewById(R.id.com_appboy_inappmessage_full_text_layout).setOnClickListener(new OnClickListener() {
            public void onClick(View scrollView) {
                c.b(AppboyInAppMessageFullView.TAG, "Passing scrollView click event to message clickable view.");
                AppboyInAppMessageFullView.this.getMessageClickableView().performClick();
            }
        });
    }

    public int getLongEdge() {
        return findViewById(R.id.com_appboy_inappmessage_full).getLayoutParams().height;
    }

    public int getShortEdge() {
        return findViewById(R.id.com_appboy_inappmessage_full).getLayoutParams().width;
    }

    private void setInAppMessageImageViewAttributes(Activity activity, d inAppMessage, IInAppMessageImageView inAppMessageImageView) {
        inAppMessageImageView.setInAppMessageImageCropType(inAppMessage.u());
        if (ViewUtils.isRunningOnTablet(activity)) {
            float radiusInPx = (float) ViewUtils.convertDpToPixels(activity, AppboyInAppMessageParams.getModalizedImageRadiusDp());
            if (inAppMessage.F().equals(com.appboy.b.a.d.GRAPHIC)) {
                inAppMessageImageView.setCornersRadiusPx(radiusInPx);
                return;
            } else {
                inAppMessageImageView.setCornersRadiiPx(radiusInPx, radiusInPx, 0.0f, 0.0f);
                return;
            }
        }
        inAppMessageImageView.setCornersRadiusPx(0.0f);
    }
}
