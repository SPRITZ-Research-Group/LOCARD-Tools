package com.appboy.ui.inappmessage.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appboy.e.d;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageImageView;
import com.appboy.ui.inappmessage.AppboyInAppMessageSimpleDraweeView;
import com.appboy.ui.inappmessage.IInAppMessageImageView;
import com.appboy.ui.inappmessage.config.AppboyInAppMessageParams;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.appboy.ui.support.ViewUtils;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.ArrayList;
import java.util.List;

public class AppboyInAppMessageModalView extends AppboyInAppMessageImmersiveBaseView {
    private static final String TAG = c.a(AppboyInAppMessageModalView.class);
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;
    private View mSimpleDraweeView;

    public AppboyInAppMessageModalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void inflateStubViews(Activity activity, d inAppMessage) {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_modal_drawee_stub);
            setInAppMessageImageViewAttributes(activity, inAppMessage, this.mSimpleDraweeView);
            return;
        }
        this.mAppboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_modal_imageview_stub);
        setInAppMessageImageViewAttributes(activity, inAppMessage, this.mAppboyInAppMessageImageView);
        if (inAppMessage.F().equals(com.appboy.b.a.d.GRAPHIC) && inAppMessage.p() != null) {
            resizeGraphicFrameIfAppropriate(activity, inAppMessage, ((double) inAppMessage.p().getWidth()) / ((double) inAppMessage.p().getHeight()));
        }
    }

    public View getFrameView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_frame);
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        super.resetMessageMargins(imageRetrievalSuccessful);
        RelativeLayout imageLayout = (RelativeLayout) findViewById(R.id.com_appboy_inappmessage_modal_image_layout);
        if ((imageRetrievalSuccessful || getMessageIconView() != null) && imageLayout != null) {
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 0);
            imageLayout.setLayoutParams(layoutParams);
        }
        findViewById(R.id.com_appboy_inappmessage_modal_text_layout).setOnClickListener(new OnClickListener() {
            public void onClick(View scrollView) {
                c.b(AppboyInAppMessageModalView.TAG, "Passing scrollView click event to message clickable view.");
                AppboyInAppMessageModalView.this.getMessageClickableView().performClick();
            }
        });
    }

    public void setMessageBackgroundColor(int color) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(findViewById(R.id.com_appboy_inappmessage_modal), color, getContext().getResources().getColor(R.color.com_appboy_inappmessage_background_light));
    }

    public List<View> getMessageButtonViews() {
        List<View> buttonViews = new ArrayList();
        if (findViewById(R.id.com_appboy_inappmessage_modal_button_one) != null) {
            buttonViews.add(findViewById(R.id.com_appboy_inappmessage_modal_button_one));
        }
        if (findViewById(R.id.com_appboy_inappmessage_modal_button_two) != null) {
            buttonViews.add(findViewById(R.id.com_appboy_inappmessage_modal_button_two));
        }
        return buttonViews;
    }

    public View getMessageButtonsView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_header_text);
    }

    public View getMessageClickableView() {
        return findViewById(R.id.com_appboy_inappmessage_modal);
    }

    public View getMessageCloseButtonView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_close_button);
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_icon);
    }

    public Drawable getMessageBackgroundObject() {
        return getMessageClickableView().getBackground();
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }

    public void setMessageSimpleDrawee(final d inAppMessage, final Activity activity) {
        if (inAppMessage.F().equals(com.appboy.b.a.d.GRAPHIC)) {
            FrescoLibraryUtils.setDraweeControllerHelper((AppboyInAppMessageSimpleDraweeView) getMessageSimpleDraweeView(), getAppropriateImageUrl(inAppMessage), 0.0f, false, new BaseControllerListener<ImageInfo>() {
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    if (imageInfo != null) {
                        final double imageAspectRatio = ((double) imageInfo.a()) / ((double) imageInfo.b());
                        AppboyInAppMessageModalView.this.mSimpleDraweeView.post(new Runnable() {
                            public void run() {
                                AppboyInAppMessageModalView.this.resizeGraphicFrameIfAppropriate(activity, inAppMessage, imageAspectRatio);
                            }
                        });
                    }
                }
            });
            return;
        }
        setMessageSimpleDrawee(inAppMessage);
    }

    private void setInAppMessageImageViewAttributes(Activity activity, d inAppMessage, IInAppMessageImageView inAppMessageImageView) {
        float pixelRadius = (float) ViewUtils.convertDpToPixels(activity, AppboyInAppMessageParams.getModalizedImageRadiusDp());
        if (inAppMessage.F().equals(com.appboy.b.a.d.GRAPHIC)) {
            inAppMessageImageView.setCornersRadiusPx(pixelRadius);
        } else {
            inAppMessageImageView.setCornersRadiiPx(pixelRadius, pixelRadius, 0.0f, 0.0f);
        }
        inAppMessageImageView.setInAppMessageImageCropType(inAppMessage.u());
    }

    private void resizeGraphicFrameIfAppropriate(Activity activity, d inAppMessage, double imageAspectRatio) {
        if (inAppMessage.F().equals(com.appboy.b.a.d.GRAPHIC)) {
            double maxWidthDp = AppboyInAppMessageParams.getGraphicModalMaxWidthDp();
            double maxHeightDp = AppboyInAppMessageParams.getGraphicModalMaxHeightDp();
            LayoutParams params = (LayoutParams) findViewById(R.id.com_appboy_inappmessage_modal_graphic_bound).getLayoutParams();
            if (imageAspectRatio >= maxWidthDp / maxHeightDp) {
                params.width = (int) ViewUtils.convertDpToPixels(activity, maxWidthDp);
                params.height = (int) (ViewUtils.convertDpToPixels(activity, maxWidthDp) / imageAspectRatio);
            } else {
                params.width = (int) (ViewUtils.convertDpToPixels(activity, maxHeightDp) * imageAspectRatio);
                params.height = (int) ViewUtils.convertDpToPixels(activity, maxHeightDp);
            }
            findViewById(R.id.com_appboy_inappmessage_modal_graphic_bound).setLayoutParams(params);
        }
    }
}
