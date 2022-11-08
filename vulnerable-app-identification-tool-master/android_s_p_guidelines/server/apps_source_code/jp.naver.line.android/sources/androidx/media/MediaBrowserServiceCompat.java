package androidx.media;

import android.app.Service;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import defpackage.bu;
import defpackage.gm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MediaBrowserServiceCompat extends Service {
    static final boolean a = Log.isLoggable("MBServiceCompat", 3);
    final bu<IBinder, f> b = new bu();
    f c;
    final k d = new k(this);
    Token e;

    /* renamed from: androidx.media.MediaBrowserServiceCompat$2 */
    final class AnonymousClass2 extends g<MediaItem> {
        final /* synthetic */ ResultReceiver a;
        final /* synthetic */ MediaBrowserServiceCompat b;

        AnonymousClass2(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            this.b = mediaBrowserServiceCompat;
            this.a = resultReceiver;
            super(obj);
        }

        final /* synthetic */ void a() {
            if ((f() & 2) != 0) {
                this.a.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", null);
            this.a.send(0, bundle);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$3 */
    final class AnonymousClass3 extends g<List<MediaItem>> {
        final /* synthetic */ ResultReceiver a;
        final /* synthetic */ MediaBrowserServiceCompat b;

        AnonymousClass3(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            this.b = mediaBrowserServiceCompat;
            this.a = resultReceiver;
            super(obj);
        }

        final /* synthetic */ void a() {
            this.a.send(-1, null);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$4 */
    final class AnonymousClass4 extends g<Bundle> {
        final /* synthetic */ ResultReceiver a;
        final /* synthetic */ MediaBrowserServiceCompat b;

        AnonymousClass4(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            this.b = mediaBrowserServiceCompat;
            this.a = resultReceiver;
            super(obj);
        }

        final void b() {
            this.a.send(-1, null);
        }

        final /* synthetic */ void a() {
            this.a.send(0, null);
        }
    }

    static List<MediaItem> b() {
        return null;
    }

    public abstract e a();

    final void a(String str, f fVar, IBinder iBinder, Bundle bundle) {
        List list = (List) fVar.g.get(str);
        if (list == null) {
            list = new ArrayList();
        }
        for (gm gmVar : list) {
            if (iBinder == gmVar.a && d.a(bundle, (Bundle) gmVar.b)) {
                return;
            }
        }
        list.add(new gm(iBinder, bundle));
        fVar.g.put(str, list);
        final f fVar2 = fVar;
        final String str2 = str;
        final Bundle bundle2 = bundle;
        g anonymousClass1 = new g<List<MediaItem>>(this, str) {
            final /* synthetic */ Bundle d = null;
            final /* synthetic */ MediaBrowserServiceCompat e;

            final /* synthetic */ void a() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.media.MediaBrowserServiceCompat.1.a():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                /*
                r5 = this;
                r0 = r5.e;
                r0 = r0.b;
                r1 = r4;
                r1 = r1.f;
                r1 = r1.a();
                r0 = r0.get(r1);
                r1 = r4;
                if (r0 == r1) goto L_0x0031;
            L_0x0014:
                r0 = androidx.media.MediaBrowserServiceCompat.a;
                if (r0 == 0) goto L_0x0030;
            L_0x0018:
                r0 = new java.lang.StringBuilder;
                r1 = "Not sending onLoadChildren result for connection that has been disconnected. pkg=";
                r0.<init>(r1);
                r1 = r4;
                r1 = r1.a;
                r0.append(r1);
                r1 = " id=";
                r0.append(r1);
                r1 = r5;
                r0.append(r1);
            L_0x0030:
                return;
            L_0x0031:
                r0 = r5.f();
                r0 = r0 & 1;
                if (r0 == 0) goto L_0x003c;
            L_0x0039:
                androidx.media.MediaBrowserServiceCompat.b();
            L_0x003c:
                r0 = r4;	 Catch:{ RemoteException -> 0x004b }
                r0 = r0.f;	 Catch:{ RemoteException -> 0x004b }
                r1 = r5;	 Catch:{ RemoteException -> 0x004b }
                r2 = 0;	 Catch:{ RemoteException -> 0x004b }
                r3 = r6;	 Catch:{ RemoteException -> 0x004b }
                r4 = r5.d;	 Catch:{ RemoteException -> 0x004b }
                r0.a(r1, r2, r3, r4);	 Catch:{ RemoteException -> 0x004b }
                return;
            L_0x004b:
                r0 = "MBServiceCompat";
                r1 = new java.lang.StringBuilder;
                r2 = "Calling onLoadChildren() failed for id=";
                r1.<init>(r2);
                r2 = r5;
                r1.append(r2);
                r2 = " package=";
                r1.append(r2);
                r2 = r4;
                r2 = r2.a;
                r1.append(r2);
                r1 = r1.toString();
                android.util.Log.w(r0, r1);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media.MediaBrowserServiceCompat.1.a():void");
            }
        };
        this.c = fVar;
        if (bundle != null) {
            anonymousClass1.a(1);
        }
        this.c = null;
        if (anonymousClass1.e()) {
            this.c = fVar;
            this.c = null;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("onLoadChildren must call detach() or sendResult() before returning for package=");
        stringBuilder.append(fVar.a);
        stringBuilder.append(" id=");
        stringBuilder.append(str);
        throw new IllegalStateException(stringBuilder.toString());
    }

    final boolean a(String str, f fVar, IBinder iBinder) {
        boolean z = true;
        boolean z2 = false;
        if (iBinder == null) {
            try {
                if (fVar.g.remove(str) == null) {
                    z = false;
                }
                this.c = fVar;
                this.c = null;
                return z;
            } catch (Throwable th) {
                this.c = fVar;
                this.c = null;
            }
        } else {
            List list = (List) fVar.g.get(str);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == ((gm) it.next()).a) {
                        it.remove();
                        z2 = true;
                    }
                }
                if (list.size() == 0) {
                    fVar.g.remove(str);
                }
            }
            this.c = fVar;
            this.c = null;
            return z2;
        }
    }
}
