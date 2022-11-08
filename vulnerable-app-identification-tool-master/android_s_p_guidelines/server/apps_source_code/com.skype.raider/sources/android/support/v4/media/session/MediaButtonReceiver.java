package android.support.v4.media.session;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserCompat.b;
import android.view.KeyEvent;
import java.util.List;

public class MediaButtonReceiver extends BroadcastReceiver {

    private static class a extends b {
        private final Context c;
        private final Intent d;
        private final PendingResult e;
        private MediaBrowserCompat f;

        a(Context context, Intent intent, PendingResult pendingResult) {
            this.c = context;
            this.d = intent;
            this.e = pendingResult;
        }

        final void a(MediaBrowserCompat mediaBrowser) {
            this.f = mediaBrowser;
        }

        public final void a() {
            try {
                new MediaControllerCompat(this.c, this.f.c()).a((KeyEvent) this.d.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            } catch (RemoteException e) {
            }
            d();
        }

        public final void b() {
            d();
        }

        public final void c() {
            d();
        }

        private void d() {
            this.f.b();
            this.e.finish();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) && intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            ComponentName mediaButtonServiceComponentName = a(context, "android.intent.action.MEDIA_BUTTON");
            if (mediaButtonServiceComponentName != null) {
                intent.setComponent(mediaButtonServiceComponentName);
                if (VERSION.SDK_INT >= 26) {
                    context.startForegroundService(intent);
                    return;
                } else {
                    context.startService(intent);
                    return;
                }
            }
            ComponentName mediaBrowserServiceComponentName = a(context, "android.media.browse.MediaBrowserService");
            if (mediaBrowserServiceComponentName != null) {
                PendingResult pendingResult = goAsync();
                Context applicationContext = context.getApplicationContext();
                a connectionCallback = new a(applicationContext, intent, pendingResult);
                MediaBrowserCompat mediaBrowser = new MediaBrowserCompat(applicationContext, mediaBrowserServiceComponentName, connectionCallback);
                connectionCallback.a(mediaBrowser);
                mediaBrowser.a();
                return;
            }
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
        new StringBuilder("Ignore unsupported intent: ").append(intent);
    }

    private static ComponentName a(Context context, String action) {
        PackageManager pm = context.getPackageManager();
        Intent queryIntent = new Intent(action);
        queryIntent.setPackage(context.getPackageName());
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(queryIntent, 0);
        if (resolveInfos.size() == 1) {
            ResolveInfo resolveInfo = (ResolveInfo) resolveInfos.get(0);
            return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        } else if (resolveInfos.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Expected 1 service that handles " + action + ", found " + resolveInfos.size());
        }
    }
}
