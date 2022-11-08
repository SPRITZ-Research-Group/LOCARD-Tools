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

@RequiresApi(26)
final class x {

    public static class a implements s, t {
        private Builder a;

        a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, String category, ArrayList<String> people, Bundle extras, int color, int visibility, Notification publicVersion, String groupKey, boolean groupSummary, String sortKey, CharSequence[] remoteInputHistory, RemoteViews contentView, RemoteViews bigContentView, RemoteViews headsUpContentView, String channelId, int badgeIcon, String shortcutId, long timeoutMs, boolean colorized, boolean colorizedSet, int groupAlertBehavior) {
            this.a = new Builder(context, channelId).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setExtras(extras).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey).setCategory(category).setColor(color).setVisibility(visibility).setPublicVersion(publicVersion).setRemoteInputHistory(remoteInputHistory).setChannelId(channelId).setBadgeIconType(badgeIcon).setShortcutId(shortcutId).setTimeoutAfter(timeoutMs).setGroupAlertBehavior(groupAlertBehavior);
            if (colorizedSet) {
                this.a.setColorized(colorized);
            }
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
        }

        public final void a(android.support.v4.app.y.a action) {
            w.a(this.a, action);
        }

        public final Builder a() {
            return this.a;
        }

        public final Notification b() {
            return this.a.build();
        }
    }
}
