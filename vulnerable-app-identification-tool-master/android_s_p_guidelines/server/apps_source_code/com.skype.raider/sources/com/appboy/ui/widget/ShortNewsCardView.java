package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.e.a.e;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;
import com.facebook.drawee.view.SimpleDraweeView;

public class ShortNewsCardView extends BaseCardView<e> {
    private static final String TAG = c.a(ShortNewsCardView.class);
    private final float mAspectRatio;
    private IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final TextView mTitle;

    public ShortNewsCardView(Context context) {
        this(context, null);
    }

    public ShortNewsCardView(Context context, e card) {
        super(context);
        this.mAspectRatio = 1.0f;
        this.mDescription = (TextView) findViewById(R.id.com_appboy_short_news_card_description);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_short_news_card_title);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_short_news_card_domain);
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_short_news_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_short_news_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return R.layout.com_appboy_short_news_card;
    }

    public void onSetCard(final e card) {
        this.mDescription.setText(card.a());
        setOptionalTextView(this.mTitle, card.d());
        setOptionalTextView(this.mDomain, card.e());
        this.mCardAction = BaseCardView.getUriActionForCard(card);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(ShortNewsCardView.this.mContext, card, ShortNewsCardView.this.mCardAction, ShortNewsCardView.TAG);
            }
        });
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.c(), 1.0f, true);
        } else {
            setImageViewToUrl(this.mImage, card.c(), 1.0f);
        }
    }
}
