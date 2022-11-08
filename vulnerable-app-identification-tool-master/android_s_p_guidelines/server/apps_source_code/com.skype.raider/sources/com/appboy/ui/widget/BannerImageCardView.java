package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.appboy.e.a.a;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.facebook.drawee.view.SimpleDraweeView;

public class BannerImageCardView extends BaseCardView<a> {
    private static final String TAG = c.a(BannerImageCardView.class);
    private float mAspectRatio;
    private IAction mCardAction;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;

    public BannerImageCardView(Context context) {
        this(context, null);
    }

    public BannerImageCardView(Context context, a card) {
        super(context);
        this.mAspectRatio = 6.0f;
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_banner_image_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_banner_image_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return R.layout.com_appboy_banner_image_card;
    }

    public void onSetCard(final a card) {
        boolean respectAspectRatio = false;
        if (card.c() != 0.0f) {
            this.mAspectRatio = card.c();
            respectAspectRatio = true;
        }
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.a(), this.mAspectRatio, respectAspectRatio);
        } else {
            setImageViewToUrl(this.mImage, card.a(), this.mAspectRatio, respectAspectRatio);
        }
        this.mCardAction = BaseCardView.getUriActionForCard(card);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(BannerImageCardView.this.mContext, card, BannerImageCardView.this.mCardAction, BannerImageCardView.TAG, false);
            }
        });
    }
}
