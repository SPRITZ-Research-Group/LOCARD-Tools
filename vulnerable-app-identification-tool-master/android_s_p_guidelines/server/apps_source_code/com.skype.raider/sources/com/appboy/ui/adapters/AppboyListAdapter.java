package com.appboy.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.appboy.e.a.a;
import com.appboy.e.a.b;
import com.appboy.e.a.c;
import com.appboy.e.a.d;
import com.appboy.e.a.e;
import com.appboy.e.a.f;
import com.appboy.ui.widget.BannerImageCardView;
import com.appboy.ui.widget.BaseCardView;
import com.appboy.ui.widget.CaptionedImageCardView;
import com.appboy.ui.widget.CrossPromotionSmallCardView;
import com.appboy.ui.widget.DefaultCardView;
import com.appboy.ui.widget.ShortNewsCardView;
import com.appboy.ui.widget.TextAnnouncementCardView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppboyListAdapter extends ArrayAdapter<c> {
    private static final String TAG = com.appboy.f.c.a(AppboyListAdapter.class);
    private final Set<String> mCardIdImpressions = new HashSet();
    private final Context mContext;

    public AppboyListAdapter(Context context, int layoutResourceId, List<c> cards) {
        super(context, layoutResourceId, cards);
        this.mContext = context;
    }

    public int getViewTypeCount() {
        return 8;
    }

    public int getItemViewType(int position) {
        c card = (c) getItem(position);
        if (card instanceof a) {
            return 1;
        }
        if (card instanceof b) {
            return 2;
        }
        if (card instanceof d) {
            return 3;
        }
        if (card instanceof e) {
            return 4;
        }
        if (card instanceof f) {
            return 5;
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BaseCardView view;
        c card = (c) getItem(position);
        if (convertView != null) {
            com.appboy.f.c.a(TAG, "Reusing convertView for rendering of item " + position);
            view = (BaseCardView) convertView;
        } else if (card instanceof a) {
            view = new BannerImageCardView(this.mContext);
        } else if (card instanceof b) {
            view = new CaptionedImageCardView(this.mContext);
        } else if (card instanceof d) {
            view = new CrossPromotionSmallCardView(this.mContext);
        } else if (card instanceof e) {
            view = new ShortNewsCardView(this.mContext);
        } else if (card instanceof f) {
            view = new TextAnnouncementCardView(this.mContext);
        } else {
            view = new DefaultCardView(this.mContext);
        }
        com.appboy.f.c.a(TAG, "Using view of type: " + view.getClass().getName() + " for card at position " + position + ": " + card.toString());
        view.setCard(card);
        logCardImpression(card);
        return view;
    }

    public synchronized void replaceFeed(List<c> cards) {
        setNotifyOnChange(false);
        if (cards == null) {
            clear();
            notifyDataSetChanged();
        } else {
            com.appboy.f.c.b(TAG, "Replacing existing feed of " + getCount() + " cards with new feed containing " + cards.size() + " cards.");
            int i = 0;
            int j = 0;
            int newFeedSize = cards.size();
            while (i < getCount()) {
                c existingCard = (c) getItem(i);
                c newCard = null;
                if (j < newFeedSize) {
                    newCard = (c) cards.get(j);
                }
                if (newCard == null || !newCard.a(existingCard)) {
                    remove(existingCard);
                } else {
                    i++;
                    j++;
                }
            }
            super.addAll(cards.subList(j, newFeedSize));
            notifyDataSetChanged();
        }
    }

    public synchronized void add(c card) {
        super.add(card);
    }

    public void resetCardImpressionTracker() {
        this.mCardIdImpressions.clear();
    }

    private void logCardImpression(c card) {
        String cardId = card.j();
        if (this.mCardIdImpressions.contains(cardId)) {
            com.appboy.f.c.a(TAG, "Already counted impression for card " + cardId);
        } else {
            this.mCardIdImpressions.add(cardId);
            card.g();
            com.appboy.f.c.a(TAG, "Logged impression for card " + cardId);
        }
        if (!card.l()) {
            card.n();
        }
    }

    public void batchSetCardsToRead(int startIndex, int endIndex) {
        if (getCount() == 0) {
            com.appboy.f.c.b(TAG, "mAdapter is empty in setting some cards to viewed.");
            return;
        }
        startIndex = Math.max(0, startIndex);
        endIndex = Math.min(getCount(), endIndex);
        for (int traversalIndex = startIndex; traversalIndex < endIndex; traversalIndex++) {
            c card = (c) getItem(traversalIndex);
            if (card == null) {
                com.appboy.f.c.b(TAG, "Card was null in setting some cards to viewed.");
                return;
            }
            if (!card.o()) {
                card.p();
            }
        }
    }
}
