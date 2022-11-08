package com.appboy.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.appboy.a.a;
import com.appboy.b.b;
import com.appboy.b.d;
import com.appboy.e.a.c;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.actions.UriAction;
import com.appboy.ui.feed.AppboyFeedManager;
import com.appboy.ui.feed.AppboyImageSwitcher;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.appboy.ui.support.ViewUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Observable;
import java.util.Observer;

public abstract class BaseCardView<T extends c> extends RelativeLayout implements Observer {
    private static final String TAG = com.appboy.f.c.a(BaseCardView.class);
    private static Boolean unreadCardVisualIndicatorOn;
    private final boolean mCanUseFresco;
    protected T mCard;
    protected final Context mContext;
    protected AppboyImageSwitcher mImageSwitcher = ((AppboyImageSwitcher) findViewById(R.id.com_appboy_newsfeed_item_read_indicator_image_switcher));

    protected abstract int getLayoutResource();

    protected abstract void onSetCard(T t);

    public BaseCardView(Context context) {
        super(context);
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(context);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getLayoutResource(), this);
        if (this.mImageSwitcher != null) {
            this.mImageSwitcher.setFactory(new ViewFactory() {
                public View makeView() {
                    return new ImageView(BaseCardView.this.mContext.getApplicationContext());
                }
            });
        }
        if (unreadCardVisualIndicatorOn == null) {
            unreadCardVisualIndicatorOn = Boolean.valueOf(new a(context).q());
        }
        if (!unreadCardVisualIndicatorOn.booleanValue() && this.mImageSwitcher != null) {
            this.mImageSwitcher.setVisibility(8);
        }
    }

    public void update(Observable observable, Object data) {
        setCardViewedIndicator();
    }

    private void setCardViewedIndicator() {
        if (getCard() == null) {
            com.appboy.f.c.b(TAG, "The card is null.");
        } else if (this.mImageSwitcher != null) {
            com.appboy.f.c.a(TAG, "Setting the read/unread indicator for the card.");
            if (getCard().o()) {
                if (this.mImageSwitcher.getReadIcon() != null) {
                    this.mImageSwitcher.setImageDrawable(this.mImageSwitcher.getReadIcon());
                } else {
                    this.mImageSwitcher.setImageResource(R.drawable.icon_read);
                }
                this.mImageSwitcher.setTag("icon_read");
            } else if (this.mImageSwitcher.getUnReadIcon() != null) {
                this.mImageSwitcher.setImageDrawable(this.mImageSwitcher.getUnReadIcon());
            } else {
                this.mImageSwitcher.setImageResource(R.drawable.icon_unread);
                this.mImageSwitcher.setTag("icon_unread");
            }
        }
    }

    public void setCard(T card) {
        this.mCard = card;
        onSetCard(card);
        card.addObserver(this);
        setCardViewedIndicator();
    }

    public c getCard() {
        return this.mCard;
    }

    void setOptionalTextView(TextView view, String value) {
        if (value == null || value.trim().equals("")) {
            view.setText("");
            view.setVisibility(8);
            return;
        }
        view.setText(value);
        view.setVisibility(0);
    }

    void safeSetBackground(Drawable background) {
        if (VERSION.SDK_INT < 16) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundNew(background);
        }
    }

    @TargetApi(16)
    private void setBackgroundNew(Drawable background) {
        setBackground(background);
    }

    void setImageViewToUrl(ImageView imageView, String imageUrl, float aspectRatio) {
        setImageViewToUrl(imageView, imageUrl, aspectRatio, true);
    }

    void setImageViewToUrl(final ImageView imageView, String imageUrl, final float aspectRatio, boolean respectAspectRatio) {
        if (imageUrl == null) {
            com.appboy.f.c.f(TAG, "The image url to render is null. Not setting the card image.");
        } else if (aspectRatio == 0.0f) {
            com.appboy.f.c.f(TAG, "The image aspect ratio is 0. Not setting the card image.");
        } else if (!imageUrl.equals(imageView.getTag(R.string.com_appboy_image_resize_tag_key))) {
            if (aspectRatio != 1.0f) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            int width = imageView.getWidth();
                            imageView.setLayoutParams(new LayoutParams(width, (int) (((float) width) / aspectRatio)));
                            ViewUtils.removeOnGlobalLayoutListenerSafe(imageView.getViewTreeObserver(), this);
                        }
                    });
                }
            }
            imageView.setImageResource(17170445);
            com.appboy.a.a(getContext()).g().a(getContext(), imageUrl, imageView, b.BASE_CARD_VIEW);
            imageView.setTag(R.string.com_appboy_image_resize_tag_key, imageUrl);
        }
    }

    void setSimpleDraweeToUrl(SimpleDraweeView simpleDraweeView, String imageUrl, float aspectRatio, boolean respectAspectRatio) {
        if (imageUrl == null) {
            com.appboy.f.c.f(TAG, "The image url to render is null. Not setting the card image.");
        } else {
            FrescoLibraryUtils.setDraweeControllerHelper(simpleDraweeView, imageUrl, aspectRatio, respectAspectRatio);
        }
    }

    boolean canUseFresco() {
        return this.mCanUseFresco;
    }

    protected static void handleCardClick(Context context, c card, IAction cardAction, String tag) {
        handleCardClick(context, card, cardAction, tag, true);
    }

    protected static void handleCardClick(Context context, c card, IAction cardAction, String tag, boolean markAsRead) {
        if (markAsRead) {
            card.p();
        }
        if (cardAction != null) {
            if (card.i()) {
                com.appboy.f.c.b(tag, "Logged click for card " + card.j());
            } else {
                com.appboy.f.c.b(tag, "Logging click failed for card " + card.j());
            }
            if (!AppboyFeedManager.getInstance().getFeedCardClickActionListener().onFeedCardClicked(context, card, cardAction)) {
                if (cardAction instanceof UriAction) {
                    AppboyNavigator.getAppboyNavigator().gotoUri(context, (UriAction) cardAction);
                } else {
                    cardAction.execute(context);
                }
            }
        }
    }

    protected static UriAction getUriActionForCard(c card) {
        Bundle extras = new Bundle();
        for (String key : card.k().keySet()) {
            extras.putString(key, (String) card.k().get(key));
        }
        return ActionFactory.createUriActionFromUrlString(card.b(), extras, card.m(), d.NEWS_FEED);
    }

    View getProperViewFromInflatedStub(int stubLayoutId) {
        ((ViewStub) findViewById(stubLayoutId)).inflate();
        if (this.mCanUseFresco) {
            return findViewById(R.id.com_appboy_stubbed_feed_drawee_view);
        }
        return findViewById(R.id.com_appboy_stubbed_feed_image_view);
    }
}
