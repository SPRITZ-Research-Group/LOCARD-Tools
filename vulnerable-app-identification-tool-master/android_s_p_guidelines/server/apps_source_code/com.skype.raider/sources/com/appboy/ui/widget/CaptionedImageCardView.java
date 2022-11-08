package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.e.a.b;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.facebook.drawee.view.SimpleDraweeView;

public class CaptionedImageCardView extends BaseCardView<b> {
    private static final String TAG = c.a(CaptionedImageCardView.class);
    private float mAspectRatio;
    private IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final TextView mTitle;

    public CaptionedImageCardView(Context context) {
        this(context, null);
    }

    public CaptionedImageCardView(Context context, b card) {
        super(context);
        this.mAspectRatio = 1.3333334f;
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_captioned_image_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_captioned_image_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        this.mTitle = (TextView) findViewById(R.id.com_appboy_captioned_image_title);
        this.mDescription = (TextView) findViewById(R.id.com_appboy_captioned_image_description);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_captioned_image_card_domain);
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return R.layout.com_appboy_captioned_image_card;
    }

    public void onSetCard(final b card) {
        this.mTitle.setText(card.c());
        this.mDescription.setText(card.d());
        setOptionalTextView(this.mDomain, card.e());
        this.mCardAction = BaseCardView.getUriActionForCard(card);
        boolean respectAspectRatio = false;
        if (card.f() != 0.0f) {
            this.mAspectRatio = card.f();
            respectAspectRatio = true;
        }
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(CaptionedImageCardView.this.mContext, card, CaptionedImageCardView.this.mCardAction, CaptionedImageCardView.TAG);
            }
        });
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.a(), this.mAspectRatio, respectAspectRatio);
        } else {
            setImageViewToUrl(this.mImage, card.a(), this.mAspectRatio, respectAspectRatio);
        }
    }
}
