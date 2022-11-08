package android.support.customtabs;

import android.app.Service;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.support.v4.util.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class CustomTabsService extends Service {
    private final Map<IBinder, DeathRecipient> a = new a();
    private e.a b = new e.a(this) {
        final /* synthetic */ CustomTabsService a;

        {
            this.a = this$0;
        }

        public final boolean a() {
            return this.a.a();
        }

        public final boolean a(d callback) {
            boolean z = false;
            final c sessionToken = new c(callback);
            try {
                DeathRecipient deathRecipient = new DeathRecipient(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public final void binderDied() {
                        this.b.a.a(sessionToken);
                    }
                };
                synchronized (this.a.a) {
                    callback.asBinder().linkToDeath(deathRecipient, 0);
                    this.a.a.put(callback.asBinder(), deathRecipient);
                }
                return this.a.b();
            } catch (RemoteException e) {
                return z;
            }
        }

        public final boolean b(d callback) {
            CustomTabsService customTabsService = this.a;
            c cVar = new c(callback);
            return customTabsService.c();
        }

        public final Bundle b() {
            return this.a.d();
        }

        public final boolean c(d callback) {
            CustomTabsService customTabsService = this.a;
            c cVar = new c(callback);
            return customTabsService.e();
        }

        public final boolean d(d callback) {
            CustomTabsService customTabsService = this.a;
            c cVar = new c(callback);
            return customTabsService.f();
        }

        public final int e(d callback) {
            CustomTabsService customTabsService = this.a;
            c cVar = new c(callback);
            return customTabsService.g();
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    protected abstract boolean a();

    protected abstract boolean b();

    protected abstract boolean c();

    protected abstract Bundle d();

    protected abstract boolean e();

    protected abstract boolean f();

    protected abstract int g();

    protected final boolean a(c sessionToken) {
        try {
            synchronized (this.a) {
                IBinder binder = sessionToken.a();
                binder.unlinkToDeath((DeathRecipient) this.a.get(binder), 0);
                this.a.remove(binder);
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
