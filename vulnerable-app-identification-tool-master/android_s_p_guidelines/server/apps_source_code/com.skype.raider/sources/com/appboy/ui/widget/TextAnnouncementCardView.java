package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.appboy.e.a.f;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.actions.IAction;

public class TextAnnouncementCardView extends BaseCardView<f> {
    private static final String TAG = c.a(TextAnnouncementCardView.class);
    private IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private final TextView mTitle;

    public TextAnnouncementCardView(Context context) {
        this(context, null);
    }

    public TextAnnouncementCardView(Context context, f card) {
        super(context);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_text_announcement_card_title);
        this.mDescription = (TextView) findViewById(R.id.com_appboy_text_announcement_card_description);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_text_announcement_card_domain);
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return R.layout.com_appboy_text_announcement_card;
    }

    public void onSetCard(final f card) {
        this.mTitle.setText(card.c());
        this.mDescription.setText(card.a());
        setOptionalTextView(this.mDomain, card.d());
        this.mCardAction = BaseCardView.getUriActionForCard(card);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(TextAnnouncementCardView.this.mContext, card, TextAnnouncementCardView.this.mCardAction, TextAnnouncementCardView.TAG);
            }
        });
    }
}
