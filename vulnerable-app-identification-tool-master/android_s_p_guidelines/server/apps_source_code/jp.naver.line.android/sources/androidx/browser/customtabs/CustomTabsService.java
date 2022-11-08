package androidx.browser.customtabs;

import android.app.Service;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import defpackage.bu;
import defpackage.d;
import defpackage.h;
import java.util.List;
import java.util.Map;

public abstract class CustomTabsService extends Service {
    final Map<IBinder, DeathRecipient> a = new bu();
    private h b = new h(this) {
        final /* synthetic */ CustomTabsService a;

        {
            this.a = r1;
        }

        public final boolean a(long j) {
            return this.a.a();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(d dVar) {
            final g gVar = new g(dVar);
            DeathRecipient anonymousClass1 = new DeathRecipient(this) {
                final /* synthetic */ AnonymousClass1 b;

                public final void binderDied() {
                    this.b.a.a(gVar);
                }
            };
            synchronized (this.a.a) {
                try {
                    dVar.asBinder().linkToDeath(anonymousClass1, 0);
                    this.a.a.put(dVar.asBinder(), anonymousClass1);
                } catch (Throwable th) {
                    while (true) {
                    }
                }
            }
            return this.a.b();
        }

        public final boolean a(d dVar, Uri uri, Bundle bundle, List<Bundle> list) {
            CustomTabsService customTabsService = this.a;
            g gVar = new g(dVar);
            return customTabsService.c();
        }

        public final Bundle a(String str, Bundle bundle) {
            return this.a.d();
        }

        public final boolean a(d dVar, Bundle bundle) {
            CustomTabsService customTabsService = this.a;
            g gVar = new g(dVar);
            return customTabsService.e();
        }

        public final boolean a(d dVar, Uri uri) {
            CustomTabsService customTabsService = this.a;
            g gVar = new g(dVar);
            return customTabsService.f();
        }

        public final int a(d dVar, String str, Bundle bundle) {
            CustomTabsService customTabsService = this.a;
            g gVar = new g(dVar);
            return customTabsService.g();
        }

        public final boolean a(d dVar, int i, Uri uri, Bundle bundle) {
            CustomTabsService customTabsService = this.a;
            g gVar = new g(dVar);
            return customTabsService.h();
        }
    };

    protected abstract boolean a();

    protected abstract boolean b();

    protected abstract boolean c();

    protected abstract Bundle d();

    protected abstract boolean e();

    protected abstract boolean f();

    protected abstract int g();

    protected abstract boolean h();

    protected final boolean a(g gVar) {
        synchronized (this.a) {
            try {
                IBinder a = gVar.a();
                a.unlinkToDeath((DeathRecipient) this.a.get(a), 0);
                this.a.remove(a);
            } catch (Throwable th) {
                return false;
            }
        }
        return true;
    }
}
