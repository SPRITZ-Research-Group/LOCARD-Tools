package com.appboy.c;

import com.appboy.f.c;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public final class a {
    private static final String a = c.a(a.class);
    private final List<com.appboy.e.a.c> b;
    private final String c;
    private final boolean d;
    private final long e;

    public a(List<com.appboy.e.a.c> feedCards, String userId, boolean isFromOfflineStorage, long timestamp) {
        this.c = userId;
        this.d = isFromOfflineStorage;
        if (feedCards == null) {
            throw new NullPointerException();
        }
        this.b = feedCards;
        this.e = timestamp;
    }

    public final boolean a() {
        return this.d;
    }

    public final List<com.appboy.e.a.c> a(EnumSet<com.appboy.b.c> categories) {
        EnumSet categories2;
        if (categories2 == null) {
            try {
                c.d(a, "The categories passed to getFeedCards are null, FeedUpdatedEvent is going to return all the cards in cache.");
                categories2 = com.appboy.b.c.a();
            } catch (Throwable e) {
                c.c(a, "Unable to get cards with categories[" + categories2 + "]. Ignoring.", e);
                return null;
            }
        }
        if (categories2.isEmpty()) {
            c.f(a, "The parameter passed into categories is not valid, Braze is returning an empty card list.Please pass in a non-empty EnumSet of CardCategory for getFeedCards().");
            return new ArrayList();
        }
        List<com.appboy.e.a.c> arrayList = new ArrayList();
        for (com.appboy.e.a.c cVar : this.b) {
            if (cVar.a(categories2) && !cVar.q()) {
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    public final int b(EnumSet<com.appboy.b.c> categories) {
        if (categories == null) {
            c.d(a, "The categories passed into getCardCount are null, FeedUpdatedEvent is going to return the count of all the cards in cache.");
            return this.b.size();
        } else if (!categories.isEmpty()) {
            return a(categories).size();
        } else {
            c.f(a, "The parameters passed into categories are not valid, Braze is returning 0 in getCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
    }

    public final int c(EnumSet<com.appboy.b.c> categories) {
        EnumSet categories2;
        int i = 0;
        while (categories2 == null) {
            c.f(a, "The categories passed to getUnreadCardCount are null, FeedUpdatedEvent is going to return the count of all the unread cards in cache.");
            categories2 = com.appboy.b.c.a();
        }
        if (categories2.isEmpty()) {
            c.f(a, "The parameters passed into categories are Empty, Braze is returning 0 in getUnreadCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
        Iterator it = this.b.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            com.appboy.e.a.c cVar = (com.appboy.e.a.c) it.next();
            if (!(!cVar.a(categories2) || cVar.l() || cVar.q())) {
                i2++;
            }
            i = i2;
        }
    }

    public final long b() {
        return this.e;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("FeedUpdatedEvent{");
        stringBuilder.append("mFeedCards=").append(this.b);
        stringBuilder.append(", mUserId='").append(this.c).append('\'');
        stringBuilder.append(", mFromOfflineStorage=").append(this.d);
        stringBuilder.append(", mTimestamp=").append(this.e);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
