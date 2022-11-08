package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Action;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;

@RequiresApi(20)
final class u {

    public static class a implements s, t {
        private Builder a;
        private Bundle b;
        private RemoteViews c;
        private RemoteViews d;
        private int e;

        public a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, ArrayList<String> people, Bundle extras, String groupKey, boolean groupSummary, String sortKey, RemoteViews contentView, RemoteViews bigContentView, int groupAlertBehavior) {
            this.a = new Builder(context).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey);
            this.b = new Bundle();
            if (extras != null) {
                this.b.putAll(extras);
            }
            if (!(people == null || people.isEmpty())) {
                this.b.putStringArray("android.people", (String[]) people.toArray(new String[people.size()]));
            }
            this.c = contentView;
            this.d = bigContentView;
            this.e = groupAlertBehavior;
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
            if (this.e != 0) {
                if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || this.e != 2)) {
                    a(notification);
                }
                if (notification.getGroup() != null && (notification.flags & 512) == 0 && this.e == 1) {
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

    public static void a(Builder b, android.support.v4.app.y.a action) {
        Bundle actionExtras;
        Action.Builder actionBuilder = new Action.Builder(action.a(), action.b(), action.c());
        if (action.g() != null) {
            for (RemoteInput remoteInput : ad.a(action.g())) {
                actionBuilder.addRemoteInput(remoteInput);
            }
        }
        if (action.d() != null) {
            actionExtras = new Bundle(action.d());
        } else {
            actionExtras = new Bundle();
        }
        actionExtras.putBoolean("android.support.allowGeneratedReplies", action.e());
        actionBuilder.addExtras(actionExtras);
        b.addAction(actionBuilder.build());
    }
}
