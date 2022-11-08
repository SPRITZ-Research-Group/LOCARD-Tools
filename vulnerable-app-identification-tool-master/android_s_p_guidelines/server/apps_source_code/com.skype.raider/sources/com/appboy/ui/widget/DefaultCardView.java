package com.appboy.ui.widget;

import android.content.Context;
import com.appboy.e.a.c;
import com.appboy.ui.R;

public class DefaultCardView extends BaseCardView<c> {
    private static final String TAG = com.appboy.f.c.a(DefaultCardView.class);

    public DefaultCardView(Context context) {
        this(context, null);
    }

    public DefaultCardView(Context context, c card) {
        super(context);
        if (card != null) {
            setCard(card);
        }
    }

    protected int getLayoutResource() {
        return R.layout.com_appboy_default_card;
    }

    public void onSetCard(c card) {
        com.appboy.f.c.f(TAG, "onSetCard called for blank view with: " + card.toString());
    }
}
