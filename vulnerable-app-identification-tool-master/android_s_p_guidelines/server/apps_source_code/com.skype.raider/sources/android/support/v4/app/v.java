package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

@RequiresApi(21)
final class v {

    public static class a implements s, t {
        private Builder a;
        private Bundle b;
        private RemoteViews c;
        private RemoteViews d;
        private RemoteViews e;
        private int f;

        public a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, String category, ArrayList<String> people, Bundle extras, int color, int visibility, Notification publicVersion, String groupKey, boolean groupSummary, String sortKey, RemoteViews contentView, RemoteViews bigContentView, RemoteViews headsUpContentView, int groupAlertBehavior) {
            this.a = new Builder(context).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey).setCategory(category).setColor(color).setVisibility(visibility).setPublicVersion(publicVersion);
            this.b = new Bundle();
            if (extras != null) {
                this.b.putAll(extras);
            }
            Iterator it = people.iterator();
            while (it.hasNext()) {
                this.a.addPerson((String) it.next());
            }
            this.c = contentView;
            this.d = bigContentView;
            this.e = headsUpContentView;
            this.f = groupAlertBehavior;
        }

        public final void a(android.support.v4.app.y.a action) {
            u.a(this.a, action);
        }

        public final Builder a() {
            return this.a;
        }

        public final Notification b() {
            this.a.setExtras(this.b);
            Notification notification = this.a.build();
            if (this.c != null) {
                notification.contentView = this.c;
            }
            if (this.d != null) {
                notification.bigContentView = this.d;
            }
            if (this.e != null) {
                notification.headsUpContentView = this.e;
            }
            if (this.f != 0) {
                if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || this.f != 2)) {
                    a(notification);
                }
                if (notification.getGroup() != null && (notification.flags & 512) == 0 && this.f == 1) {
                    a(notification);
                }
            }
            return notification;
        }

        private static void a(Notification notification) {
            notification.sound = null;
            notification.vibrate = null;
            notification.defaults &= -2;
            notification.defaults &= -3;
        }
    }
}
