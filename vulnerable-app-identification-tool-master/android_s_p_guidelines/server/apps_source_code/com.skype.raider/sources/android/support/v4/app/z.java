package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(16)
final class z {
    private static final Object a = new Object();
    private static Field b;
    private static boolean c;
    private static final Object d = new Object();

    public static class a implements s, t {
        private Builder a;
        private final Bundle b;
        private List<Bundle> c = new ArrayList();
        private RemoteViews d;
        private RemoteViews e;

        public a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, Bundle extras, String groupKey, boolean groupSummary, String sortKey, RemoteViews contentView, RemoteViews bigContentView) {
            this.a = new Builder(context).setWhen(n.when).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate);
            this.b = new Bundle();
            if (extras != null) {
                this.b.putAll(extras);
            }
            if (localOnly) {
                this.b.putBoolean("android.support.localOnly", true);
            }
            if (groupKey != null) {
                this.b.putString("android.support.groupKey", groupKey);
                if (groupSummary) {
                    this.b.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.b.putBoolean(NotificationManagerCompat.EXTRA_USE_SIDE_CHANNEL, true);
                }
            }
            if (sortKey != null) {
                this.b.putString("android.support.sortKey", sortKey);
            }
            this.d = contentView;
            this.e = bigContentView;
        }

        public final void a(android.support.v4.app.y.a action) {
            this.c.add(z.a(this.a, action));
        }

        public final Builder a() {
            return this.a;
        }

        public final Notification b() {
            Notification notif = this.a.build();
            Bundle extras = z.a(notif);
            Bundle mergeBundle = new Bundle(this.b);
            for (String key : this.b.keySet()) {
                if (extras.containsKey(key)) {
                    mergeBundle.remove(key);
                }
            }
            extras.putAll(mergeBundle);
            SparseArray<Bundle> actionExtrasMap = z.a(this.c);
            if (actionExtrasMap != null) {
                z.a(notif).putSparseParcelableArray("android.support.actionExtras", actionExtrasMap);
            }
            if (this.d != null) {
                notif.contentView = this.d;
            }
            if (this.e != null) {
                notif.bigContentView = this.e;
            }
            return notif;
        }
    }

    public static void a(t b, CharSequence bigContentTitle, boolean useSummary, CharSequence summaryText, CharSequence bigText) {
        BigTextStyle style = new BigTextStyle(b.a()).setBigContentTitle(bigContentTitle).bigText(bigText);
        if (useSummary) {
            style.setSummaryText(summaryText);
        }
    }

    public static void a(t b, CharSequence bigContentTitle, boolean useSummary, CharSequence summaryText, Bitmap bigPicture, Bitmap bigLargeIcon, boolean bigLargeIconSet) {
        BigPictureStyle style = new BigPictureStyle(b.a()).setBigContentTitle(bigContentTitle).bigPicture(bigPicture);
        if (bigLargeIconSet) {
            style.bigLargeIcon(bigLargeIcon);
        }
        if (useSummary) {
            style.setSummaryText(summaryText);
        }
    }

    public static void a(t b, CharSequence bigContentTitle, boolean useSummary, CharSequence summaryText, ArrayList<CharSequence> texts) {
        InboxStyle style = new InboxStyle(b.a()).setBigContentTitle(bigContentTitle);
        if (useSummary) {
            style.setSummaryText(summaryText);
        }
        Iterator it = texts.iterator();
        while (it.hasNext()) {
            style.addLine((CharSequence) it.next());
        }
    }

    public static SparseArray<Bundle> a(List<Bundle> actionExtrasList) {
        SparseArray<Bundle> actionExtrasMap = null;
        int count = actionExtrasList.size();
        for (int i = 0; i < count; i++) {
            Bundle actionExtras = (Bundle) actionExtrasList.get(i);
            if (actionExtras != null) {
                if (actionExtrasMap == null) {
                    actionExtrasMap = new SparseArray();
                }
                actionExtrasMap.put(i, actionExtras);
            }
        }
        return actionExtrasMap;
    }

    public static Bundle a(Notification notif) {
        synchronized (a) {
            if (c) {
                return null;
            }
            try {
                if (b == null) {
                    Field extrasField = Notification.class.getDeclaredField("extras");
                    if (Bundle.class.isAssignableFrom(extrasField.getType())) {
                        extrasField.setAccessible(true);
                        b = extrasField;
                    } else {
                        c = true;
                        return null;
                    }
                }
                Bundle extras = (Bundle) b.get(notif);
                if (extras == null) {
                    extras = new Bundle();
                    b.set(notif, extras);
                }
                return extras;
            } catch (IllegalAccessException e) {
                c = true;
                return null;
            } catch (NoSuchFieldException e2) {
                c = true;
                return null;
            }
        }
    }

    public static Bundle a(Builder builder, android.support.v4.app.y.a action) {
        builder.addAction(action.a(), action.b(), action.c());
        Bundle actionExtras = new Bundle(action.d());
        if (action.g() != null) {
            actionExtras.putParcelableArray("android.support.remoteInputs", af.a(action.g()));
        }
        if (action.f() != null) {
            actionExtras.putParcelableArray("android.support.dataRemoteInputs", af.a(action.f()));
        }
        actionExtras.putBoolean("android.support.allowGeneratedReplies", action.e());
        return actionExtras;
    }
}
