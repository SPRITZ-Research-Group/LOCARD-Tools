package com.appboy.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.e.a.d;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.ui.R;
import com.appboy.ui.actions.GooglePlayAppDetailsAction;
import com.appboy.ui.actions.IAction;
import com.facebook.drawee.view.SimpleDraweeView;
import java.text.NumberFormat;
import java.util.Locale;

public class CrossPromotionSmallCardView extends BaseCardView<d> {
    private static final String TAG = c.a(CrossPromotionSmallCardView.class);
    private final float mAspectRatio;
    private final TextView mCaption;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final Button mPrice;
    private IAction mPriceAction;
    private final TextView mReviewCount;
    private final StarRatingView mStarRating;
    private final TextView mSubtitle;
    private final TextView mTitle;

    public CrossPromotionSmallCardView(Context context) {
        this(context, null);
    }

    public CrossPromotionSmallCardView(Context context, d card) {
        super(context);
        this.mAspectRatio = 1.0f;
        this.mTitle = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_title);
        this.mSubtitle = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_subtitle);
        this.mReviewCount = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_review_count);
        this.mCaption = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_recommendation_tab);
        this.mStarRating = (StarRatingView) findViewById(R.id.com_appboy_cross_promotion_small_card_star_rating);
        this.mPrice = (Button) findViewById(R.id.com_appboy_cross_promotion_small_card_price);
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_cross_promotion_small_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_cross_promotion_small_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        if (card != null) {
            setCard(card);
        }
    }

    protected int getLayoutResource() {
        return R.layout.com_appboy_cross_promotion_small_card;
    }

    @SuppressLint({"SetTextI18n"})
    public void onSetCard(final d card) {
        this.mTitle.setText(card.a());
        if (card.c() == null || card.c().toUpperCase(Locale.getDefault()).equals("NULL")) {
            this.mSubtitle.setVisibility(8);
        } else {
            this.mSubtitle.setText(card.c().toUpperCase(Locale.getDefault()));
        }
        this.mCaption.setText(card.d().toUpperCase(Locale.getDefault()));
        if (card.f() <= 0.0d) {
            this.mReviewCount.setVisibility(8);
            this.mStarRating.setVisibility(8);
        } else {
            this.mReviewCount.setText("(" + NumberFormat.getInstance().format((long) card.r()) + ")");
            this.mStarRating.setRating((float) card.f());
        }
        if (i.c(card.w())) {
            this.mPrice.setText(getPriceString(card.s()));
        } else {
            this.mPrice.setText(card.w());
        }
        this.mPriceAction = new GooglePlayAppDetailsAction(card.t(), card.m(), card.v(), card.u(), com.appboy.b.d.NEWS_FEED);
        this.mPrice.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(CrossPromotionSmallCardView.this.mContext, card, CrossPromotionSmallCardView.this.mPriceAction, CrossPromotionSmallCardView.TAG);
            }
        });
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.e(), 1.0f, true);
        } else {
            setImageViewToUrl(this.mImage, card.e(), 1.0f);
        }
    }

    private String getPriceString(double price) {
        if (price == 0.0d) {
            return this.mContext.getString(R.string.com_appboy_recommendation_free);
        }
        return NumberFormat.getCurrencyInstance(Locale.US).format(price);
    }
}
