package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appboy.b.a.i;
import com.appboy.e.b;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageSimpleDraweeView;
import com.appboy.ui.inappmessage.IInAppMessageView;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.appboy.ui.support.ViewUtils;

public abstract class AppboyInAppMessageBaseView extends RelativeLayout implements IInAppMessageView {
    final boolean mCanUseFresco;

    public abstract Object getMessageBackgroundObject();

    public abstract TextView getMessageIconView();

    public abstract ImageView getMessageImageView();

    public abstract View getMessageSimpleDraweeView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(context);
        setLayerType(1, null);
    }

    public void setMessageBackgroundColor(int color) {
        InAppMessageViewUtils.setViewBackgroundColor((View) getMessageBackgroundObject(), color);
    }

    public void setMessageTextColor(int color) {
        InAppMessageViewUtils.setTextViewColor(getMessageTextView(), color);
    }

    public void setMessageTextAlign(i textAlign) {
        InAppMessageViewUtils.setTextAlignment(getMessageTextView(), textAlign);
    }

    public void setMessage(String text) {
        getMessageTextView().setText(text);
    }

    public void setMessageImageView(Bitmap bitmap) {
        InAppMessageViewUtils.setImage(bitmap, getMessageImageView());
    }

    public void setMessageSimpleDrawee(b inAppMessage) {
        FrescoLibraryUtils.setDraweeControllerHelper((AppboyInAppMessageSimpleDraweeView) getMessageSimpleDraweeView(), getAppropriateImageUrl(inAppMessage), 0.0f, false);
    }

    public String getAppropriateImageUrl(b inAppMessage) {
        if (com.appboy.f.i.c(inAppMessage.k())) {
            return inAppMessage.j();
        }
        return inAppMessage.k();
    }

    public void setMessageIcon(String icon, int iconColor, int iconBackgroundColor) {
        if (getMessageIconView() != null) {
            InAppMessageViewUtils.setIcon(getContext(), icon, iconColor, iconBackgroundColor, getMessageIconView());
        }
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        View viewContainingImage;
        RelativeLayout layoutContainingImage;
        if (this.mCanUseFresco) {
            viewContainingImage = getMessageSimpleDraweeView();
            layoutContainingImage = (RelativeLayout) findViewById(R.id.com_appboy_stubbed_inappmessage_drawee_view_parent);
        } else {
            viewContainingImage = getMessageImageView();
            layoutContainingImage = (RelativeLayout) findViewById(R.id.com_appboy_stubbed_inappmessage_image_view_parent);
        }
        if (viewContainingImage != null) {
            if (imageRetrievalSuccessful) {
                ViewUtils.removeViewFromParent(getMessageIconView());
            } else {
                ViewUtils.removeViewFromParent(viewContainingImage);
                if (layoutContainingImage != null) {
                    ViewUtils.removeViewFromParent(layoutContainingImage);
                }
            }
        }
        if (getMessageIconView() != null && com.appboy.f.i.c((String) getMessageIconView().getText())) {
            ViewUtils.removeViewFromParent(getMessageIconView());
        }
    }

    public View getMessageClickableView() {
        return this;
    }

    View getProperViewFromInflatedStub(int stubLayoutId) {
        ((ViewStub) findViewById(stubLayoutId)).inflate();
        if (this.mCanUseFresco) {
            return findViewById(R.id.com_appboy_stubbed_inappmessage_drawee_view);
        }
        return findViewById(R.id.com_appboy_stubbed_inappmessage_image_view);
    }
}
