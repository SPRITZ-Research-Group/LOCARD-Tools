package com.appboy.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appboy.f.c;
import com.appboy.ui.R;
import java.util.ArrayList;
import java.util.List;

public class StarRatingView extends LinearLayout {
    private static final String TAG = c.a(StarRatingView.class);
    private float mRating = 0.0f;
    private List<ImageView> mStarRating;

    public StarRatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(0);
        this.mStarRating = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            ImageView star = new ImageView(context);
            star.setTag(Integer.valueOf(0));
            addView(star, new LayoutParams(-2, -2));
            this.mStarRating.add(star);
        }
        setRating(this.mRating);
    }

    public boolean setRating(float rating) {
        if (rating < 0.0f || rating > 5.0f) {
            c.g(TAG, "Unable to set rating to " + rating + ". Rating must be between 0 and 5");
            return false;
        }
        int starIndex;
        ImageView star;
        this.mRating = rating;
        int ratingRoundedDown = (int) Math.floor((double) this.mRating);
        int ratingRoundedUp = (int) Math.ceil((double) this.mRating);
        for (starIndex = 0; starIndex < ratingRoundedDown; starIndex++) {
            star = (ImageView) this.mStarRating.get(starIndex);
            star.setTag(Integer.valueOf(R.drawable.com_appboy_rating_full_star));
            star.setImageResource(R.drawable.com_appboy_rating_full_star);
        }
        for (starIndex = ratingRoundedUp; starIndex < this.mStarRating.size(); starIndex++) {
            star = (ImageView) this.mStarRating.get(starIndex);
            star.setTag(Integer.valueOf(R.drawable.com_appboy_rating_empty_star));
            star.setImageResource(R.drawable.com_appboy_rating_empty_star);
        }
        float remainder = rating - ((float) ratingRoundedDown);
        if (remainder > 0.0f) {
            star = (ImageView) this.mStarRating.get(ratingRoundedDown);
            if (remainder < 0.25f) {
                star.setTag(Integer.valueOf(R.drawable.com_appboy_rating_empty_star));
                star.setImageResource(R.drawable.com_appboy_rating_empty_star);
            } else if (remainder < 0.75f) {
                star.setTag(Integer.valueOf(R.drawable.com_appboy_rating_half_star));
                star.setImageResource(R.drawable.com_appboy_rating_half_star);
            } else {
                star.setTag(Integer.valueOf(R.drawable.com_appboy_rating_full_star));
                star.setImageResource(R.drawable.com_appboy_rating_full_star);
            }
        }
        return true;
    }
}
