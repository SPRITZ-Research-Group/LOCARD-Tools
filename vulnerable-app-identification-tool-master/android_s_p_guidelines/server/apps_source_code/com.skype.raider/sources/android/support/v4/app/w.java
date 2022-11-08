package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Action;
import android.app.Notification.Builder;
import android.app.Notification.MessagingStyle;
import android.app.Notification.MessagingStyle.Message;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(24)
final class w {

    public static class a implements s, t {
        private Builder a;
        private int b;

        public a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, String category, ArrayList<String> people, Bundle extras, int color, int visibility, Notification publicVersion, String groupKey, boolean groupSummary, String sortKey, CharSequence[] remoteInputHistory, RemoteViews contentView, RemoteViews bigContentView, RemoteViews headsUpContentView, int groupAlertBehavior) {
            this.a = new Builder(context).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setExtras(extras).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey).setCategory(category).setColor(color).setVisibility(visibility).setPublicVersion(publicVersion).setRemoteInputHistory(remoteInputHistory);
            if (contentView != null) {
                this.a.setCustomContentView(contentView);
            }
            if (bigContentView != null) {
                this.a.setCustomBigContentView(bigContentView);
            }
            if (headsUpContentView != null) {
                this.a.setCustomHeadsUpContentView(headsUpContentView);
            }
            Iterator it = people.iterator();
            while (it.hasNext()) {
                this.a.addPerson((String) it.next());
            }
            this.b = groupAlertBehavior;
        }

        public final void a(android.support.v4.app.y.a action) {
            w.a(this.a, action);
        }

        public final Builder a() {
            return this.a;
        }

        public final Notification b() {
            Notification notification = this.a.build();
            if (this.b != 0) {
                if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || this.b != 2)) {
                    a(notification);
                }
                if (notification.getGroup() != null && (notification.flags & 512) == 0 && this.b == 1) {
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

    public static void a(t b, CharSequence userDisplayName, CharSequence conversationTitle, List<CharSequence> texts, List<Long> timestamps, List<CharSequence> senders, List<String> dataMimeTypes, List<Uri> dataUris) {
        MessagingStyle style = new MessagingStyle(userDisplayName).setConversationTitle(conversationTitle);
        for (int i = 0; i < texts.size(); i++) {
            Message message = new Message((CharSequence) texts.get(i), ((Long) timestamps.get(i)).longValue(), (CharSequence) senders.get(i));
            if (dataMimeTypes.get(i) != null) {
                message.setData((String) dataMimeTypes.get(i), (Uri) dataUris.get(i));
            }
            style.addMessage(message);
        }
        style.setBuilder(b.a());
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
        actionBuilder.setAllowGeneratedReplies(action.e());
        actionBuilder.addExtras(actionExtras);
        b.addAction(actionBuilder.build());
    }
}
