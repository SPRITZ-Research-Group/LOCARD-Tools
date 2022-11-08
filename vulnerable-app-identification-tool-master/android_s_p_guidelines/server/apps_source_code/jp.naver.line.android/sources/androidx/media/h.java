package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

final class h {
    final /* synthetic */ MediaBrowserServiceCompat a;

    /* renamed from: androidx.media.h$1 */
    final class AnonymousClass1 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ Bundle e;
        final /* synthetic */ h f;

        AnonymousClass1(h hVar, i iVar, String str, int i, int i2, Bundle bundle) {
            this.f = hVar;
            this.a = iVar;
            this.b = str;
            this.c = i;
            this.d = i2;
            this.e = bundle;
        }

        public final void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.media.h.1.run():void. bs: []
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
            r9 = this;
            r0 = r9.a;
            r0 = r0.a();
            r1 = r9.f;
            r1 = r1.a;
            r1 = r1.b;
            r1.remove(r0);
            r1 = new androidx.media.f;
            r2 = r9.f;
            r3 = r2.a;
            r4 = r9.b;
            r5 = r9.c;
            r6 = r9.d;
            r7 = r9.e;
            r8 = r9.a;
            r2 = r1;
            r2.<init>(r3, r4, r5, r6, r7, r8);
            r2 = r9.f;
            r2 = r2.a;
            r2.c = r1;
            r2 = r9.f;
            r2 = r2.a;
            r2 = r2.a();
            r1.h = r2;
            r2 = r9.f;
            r2 = r2.a;
            r3 = 0;
            r2.c = r3;
            r2 = r1.h;
            if (r2 != 0) goto L_0x007f;
        L_0x003e:
            r0 = "MBServiceCompat";
            r1 = new java.lang.StringBuilder;
            r2 = "No root for client ";
            r1.<init>(r2);
            r2 = r9.b;
            r1.append(r2);
            r2 = " from service ";
            r1.append(r2);
            r2 = r9.getClass();
            r2 = r2.getName();
            r1.append(r2);
            r1 = r1.toString();
            android.util.Log.i(r0, r1);
            r0 = r9.a;	 Catch:{ RemoteException -> 0x0069 }
            r0.b();	 Catch:{ RemoteException -> 0x0069 }
            return;
        L_0x0069:
            r0 = "MBServiceCompat";
            r1 = new java.lang.StringBuilder;
            r2 = "Calling onConnectFailed() failed. Ignoring. pkg=";
            r1.<init>(r2);
            r2 = r9.b;
            r1.append(r2);
            r1 = r1.toString();
            android.util.Log.w(r0, r1);
            return;
        L_0x007f:
            r2 = r9.f;	 Catch:{ RemoteException -> 0x00ac }
            r2 = r2.a;	 Catch:{ RemoteException -> 0x00ac }
            r2 = r2.b;	 Catch:{ RemoteException -> 0x00ac }
            r2.put(r0, r1);	 Catch:{ RemoteException -> 0x00ac }
            r2 = 0;	 Catch:{ RemoteException -> 0x00ac }
            r0.linkToDeath(r1, r2);	 Catch:{ RemoteException -> 0x00ac }
            r2 = r9.f;	 Catch:{ RemoteException -> 0x00ac }
            r2 = r2.a;	 Catch:{ RemoteException -> 0x00ac }
            r2 = r2.e;	 Catch:{ RemoteException -> 0x00ac }
            if (r2 == 0) goto L_0x00ab;	 Catch:{ RemoteException -> 0x00ac }
        L_0x0094:
            r2 = r9.a;	 Catch:{ RemoteException -> 0x00ac }
            r3 = r1.h;	 Catch:{ RemoteException -> 0x00ac }
            r3 = r3.a();	 Catch:{ RemoteException -> 0x00ac }
            r4 = r9.f;	 Catch:{ RemoteException -> 0x00ac }
            r4 = r4.a;	 Catch:{ RemoteException -> 0x00ac }
            r4 = r4.e;	 Catch:{ RemoteException -> 0x00ac }
            r1 = r1.h;	 Catch:{ RemoteException -> 0x00ac }
            r1 = r1.b();	 Catch:{ RemoteException -> 0x00ac }
            r2.a(r3, r4, r1);	 Catch:{ RemoteException -> 0x00ac }
        L_0x00ab:
            return;
        L_0x00ac:
            r1 = "MBServiceCompat";
            r2 = new java.lang.StringBuilder;
            r3 = "Calling onConnect() failed. Dropping client. pkg=";
            r2.<init>(r3);
            r3 = r9.b;
            r2.append(r3);
            r2 = r2.toString();
            android.util.Log.w(r1, r2);
            r1 = r9.f;
            r1 = r1.a;
            r1 = r1.b;
            r1.remove(r0);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media.h.1.run():void");
        }
    }

    /* renamed from: androidx.media.h$2 */
    final class AnonymousClass2 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ h b;

        AnonymousClass2(h hVar, i iVar) {
            this.b = hVar;
            this.a = iVar;
        }

        public final void run() {
            f fVar = (f) this.b.a.b.remove(this.a.a());
            if (fVar != null) {
                fVar.f.a().unlinkToDeath(fVar, 0);
            }
        }
    }

    /* renamed from: androidx.media.h$3 */
    final class AnonymousClass3 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ IBinder c;
        final /* synthetic */ Bundle d;
        final /* synthetic */ h e;

        AnonymousClass3(h hVar, i iVar, String str, IBinder iBinder, Bundle bundle) {
            this.e = hVar;
            this.a = iVar;
            this.b = str;
            this.c = iBinder;
            this.d = bundle;
        }

        public final void run() {
            f fVar = (f) this.e.a.b.get(this.a.a());
            if (fVar == null) {
                StringBuilder stringBuilder = new StringBuilder("addSubscription for callback that isn't registered id=");
                stringBuilder.append(this.b);
                Log.w("MBServiceCompat", stringBuilder.toString());
                return;
            }
            this.e.a.a(this.b, fVar, this.c, this.d);
        }
    }

    /* renamed from: androidx.media.h$4 */
    final class AnonymousClass4 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ IBinder c;
        final /* synthetic */ h d;

        AnonymousClass4(h hVar, i iVar, String str, IBinder iBinder) {
            this.d = hVar;
            this.a = iVar;
            this.b = str;
            this.c = iBinder;
        }

        public final void run() {
            f fVar = (f) this.d.a.b.get(this.a.a());
            StringBuilder stringBuilder;
            if (fVar == null) {
                stringBuilder = new StringBuilder("removeSubscription for callback that isn't registered id=");
                stringBuilder.append(this.b);
                Log.w("MBServiceCompat", stringBuilder.toString());
                return;
            }
            if (!this.d.a.a(this.b, fVar, this.c)) {
                stringBuilder = new StringBuilder("removeSubscription called for ");
                stringBuilder.append(this.b);
                stringBuilder.append(" which is not subscribed");
                Log.w("MBServiceCompat", stringBuilder.toString());
            }
        }
    }

    /* renamed from: androidx.media.h$5 */
    final class AnonymousClass5 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ ResultReceiver c;
        final /* synthetic */ h d;

        AnonymousClass5(h hVar, i iVar, String str, ResultReceiver resultReceiver) {
            this.d = hVar;
            this.a = iVar;
            this.b = str;
            this.c = resultReceiver;
        }

        public final void run() {
            f fVar = (f) this.d.a.b.get(this.a.a());
            if (fVar == null) {
                StringBuilder stringBuilder = new StringBuilder("getMediaItem for callback that isn't registered id=");
                stringBuilder.append(this.b);
                Log.w("MBServiceCompat", stringBuilder.toString());
                return;
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = this.d.a;
            String str = this.b;
            g anonymousClass2 = new androidx.media.MediaBrowserServiceCompat.AnonymousClass2(mediaBrowserServiceCompat, str, this.c);
            mediaBrowserServiceCompat.c = fVar;
            anonymousClass2.a(2);
            anonymousClass2.c();
            mediaBrowserServiceCompat.c = null;
            if (!anonymousClass2.e()) {
                throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=".concat(String.valueOf(str)));
            }
        }
    }

    /* renamed from: androidx.media.h$6 */
    final class AnonymousClass6 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ Bundle e;
        final /* synthetic */ h f;

        AnonymousClass6(h hVar, i iVar, String str, int i, int i2, Bundle bundle) {
            this.f = hVar;
            this.a = iVar;
            this.b = str;
            this.c = i;
            this.d = i2;
            this.e = bundle;
        }

        public final void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.media.h.6.run():void. bs: []
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
            r9 = this;
            r0 = r9.a;
            r0 = r0.a();
            r1 = r9.f;
            r1 = r1.a;
            r1 = r1.b;
            r1.remove(r0);
            r1 = new androidx.media.f;
            r2 = r9.f;
            r3 = r2.a;
            r4 = r9.b;
            r5 = r9.c;
            r6 = r9.d;
            r7 = r9.e;
            r8 = r9.a;
            r2 = r1;
            r2.<init>(r3, r4, r5, r6, r7, r8);
            r2 = r9.f;
            r2 = r2.a;
            r2 = r2.b;
            r2.put(r0, r1);
            r2 = 0;
            r0.linkToDeath(r1, r2);	 Catch:{ RemoteException -> 0x0031 }
            return;
        L_0x0031:
            r0 = "MBServiceCompat";
            r1 = "IBinder is already dead.";
            android.util.Log.w(r0, r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media.h.6.run():void");
        }
    }

    /* renamed from: androidx.media.h$7 */
    final class AnonymousClass7 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ h b;

        AnonymousClass7(h hVar, i iVar) {
            this.b = hVar;
            this.a = iVar;
        }

        public final void run() {
            IBinder a = this.a.a();
            f fVar = (f) this.b.a.b.remove(a);
            if (fVar != null) {
                a.unlinkToDeath(fVar, 0);
            }
        }
    }

    /* renamed from: androidx.media.h$8 */
    final class AnonymousClass8 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ Bundle c;
        final /* synthetic */ ResultReceiver d;
        final /* synthetic */ h e;

        AnonymousClass8(h hVar, i iVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.e = hVar;
            this.a = iVar;
            this.b = str;
            this.c = bundle;
            this.d = resultReceiver;
        }

        public final void run() {
            f fVar = (f) this.e.a.b.get(this.a.a());
            if (fVar == null) {
                StringBuilder stringBuilder = new StringBuilder("search for callback that isn't registered query=");
                stringBuilder.append(this.b);
                Log.w("MBServiceCompat", stringBuilder.toString());
                return;
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = this.e.a;
            String str = this.b;
            g anonymousClass3 = new androidx.media.MediaBrowserServiceCompat.AnonymousClass3(mediaBrowserServiceCompat, str, this.d);
            mediaBrowserServiceCompat.c = fVar;
            anonymousClass3.a(4);
            anonymousClass3.c();
            mediaBrowserServiceCompat.c = null;
            if (!anonymousClass3.e()) {
                throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=".concat(String.valueOf(str)));
            }
        }
    }

    /* renamed from: androidx.media.h$9 */
    final class AnonymousClass9 implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ String b;
        final /* synthetic */ Bundle c;
        final /* synthetic */ ResultReceiver d;
        final /* synthetic */ h e;

        AnonymousClass9(h hVar, i iVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.e = hVar;
            this.a = iVar;
            this.b = str;
            this.c = bundle;
            this.d = resultReceiver;
        }

        public final void run() {
            f fVar = (f) this.e.a.b.get(this.a.a());
            StringBuilder stringBuilder;
            if (fVar == null) {
                stringBuilder = new StringBuilder("sendCustomAction for callback that isn't registered action=");
                stringBuilder.append(this.b);
                stringBuilder.append(", extras=");
                stringBuilder.append(this.c);
                Log.w("MBServiceCompat", stringBuilder.toString());
                return;
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = this.e.a;
            String str = this.b;
            Bundle bundle = this.c;
            g anonymousClass4 = new androidx.media.MediaBrowserServiceCompat.AnonymousClass4(mediaBrowserServiceCompat, str, this.d);
            mediaBrowserServiceCompat.c = fVar;
            anonymousClass4.d();
            mediaBrowserServiceCompat.c = null;
            if (!anonymousClass4.e()) {
                stringBuilder = new StringBuilder("onCustomAction must call detach() or sendResult() or sendError() before returning for action=");
                stringBuilder.append(str);
                stringBuilder.append(" extras=");
                stringBuilder.append(bundle);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }

    h(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.a = mediaBrowserServiceCompat;
    }
}
