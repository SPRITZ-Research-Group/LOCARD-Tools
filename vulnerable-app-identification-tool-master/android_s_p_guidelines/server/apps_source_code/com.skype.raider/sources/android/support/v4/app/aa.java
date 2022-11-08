package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(19)
final class aa {

    public static class a implements s, t {
        private Builder a;
        private Bundle b;
        private List<Bundle> c = new ArrayList();
        private RemoteViews d;
        private RemoteViews e;

        public a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, ArrayList<String> people, Bundle extras, String groupKey, boolean groupSummary, String sortKey, RemoteViews contentView, RemoteViews bigContentView) {
            this.a = new Builder(context).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate);
            this.b = new Bundle();
            if (extras != null) {
                this.b.putAll(extras);
            }
            if (!(people == null || people.isEmpty())) {
                this.b.putStringArray("android.people", (String[]) people.toArray(new String[people.size()]));
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
            SparseArray<Bundle> actionExtrasMap = z.a(this.c);
            if (actionExtrasMap != null) {
                this.b.putSparseParcelableArray("android.support.actionExtras", actionExtrasMap);
            }
            this.a.setExtras(this.b);
            Notification notification = this.a.build();
            if (this.d != null) {
                notification.contentView = this.d;
            }
            if (this.e != null) {
                notification.bigContentView = this.e;
            }
            return notification;
        }
    }
}
