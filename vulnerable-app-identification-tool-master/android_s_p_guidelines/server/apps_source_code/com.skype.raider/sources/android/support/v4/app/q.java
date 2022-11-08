package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.b.b;
import android.support.v4.util.d;
import android.support.v4.util.m;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

final class q extends p {
    static boolean a = false;
    final m<a> b;
    final m<a> c;
    final String d;
    boolean e;
    boolean f;
    h g;

    final class a implements android.support.v4.content.b.a<Object>, b<Object> {
        final int a;
        final Bundle b;
        android.support.v4.app.p.a<Object> c;
        android.support.v4.content.b<Object> d;
        boolean e;
        boolean f;
        Object g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        a n;
        final /* synthetic */ q o;

        final void a() {
            if (q.a) {
                new StringBuilder("  Stopping: ").append(this);
            }
            this.h = false;
            if (!this.i && this.d != null && this.m) {
                this.m = false;
                this.d.a((b) this);
                this.d.b(this);
                this.d.b();
            }
        }

        final void b() {
            while (true) {
                b this;
                if (q.a) {
                    new StringBuilder("  Destroying: ").append(this);
                }
                this.l = true;
                boolean needReset = this.f;
                this.f = false;
                if (this.c != null && this.d != null && this.e && needReset) {
                    if (q.a) {
                        new StringBuilder("  Resetting: ").append(this);
                    }
                    String lastBecause = null;
                    if (this.o.g != null) {
                        lastBecause = this.o.g.d.u;
                        this.o.g.d.u = "onLoaderReset";
                    }
                    if (this.o.g != null) {
                        this.o.g.d.u = lastBecause;
                    }
                }
                this.c = null;
                this.g = null;
                this.e = false;
                if (this.d != null) {
                    if (this.m) {
                        this.m = false;
                        this.d.a(this);
                        this.d.b(this);
                    }
                    this.d.c();
                }
                if (this.n != null) {
                    this = this.n;
                } else {
                    return;
                }
            }
        }

        final void a(android.support.v4.content.b<Object> loader, Object data) {
            if (this.c != null) {
                String lastBecause = null;
                if (this.o.g != null) {
                    lastBecause = this.o.g.d.u;
                    this.o.g.d.u = "onLoadFinished";
                }
                try {
                    if (q.a) {
                        StringBuilder append = new StringBuilder("  onLoadFinished in ").append(loader).append(": ");
                        StringBuilder stringBuilder = new StringBuilder(64);
                        d.a(data, stringBuilder);
                        stringBuilder.append("}");
                        append.append(stringBuilder.toString());
                    }
                    if (this.o.g != null) {
                        this.o.g.d.u = lastBecause;
                    }
                    this.f = true;
                } catch (Throwable th) {
                    if (this.o.g != null) {
                        this.o.g.d.u = lastBecause;
                    }
                }
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.a);
            sb.append(" : ");
            d.a(this.d, sb);
            sb.append("}}");
            return sb.toString();
        }

        public final void a(String prefix, PrintWriter writer) {
            while (true) {
                writer.print(prefix);
                writer.print("mId=");
                writer.print(this.a);
                writer.print(" mArgs=");
                writer.println(this.b);
                writer.print(prefix);
                writer.print("mCallbacks=");
                writer.println(this.c);
                writer.print(prefix);
                writer.print("mLoader=");
                writer.println(this.d);
                if (this.d != null) {
                    this.d.a(prefix + "  ", writer);
                }
                if (this.e || this.f) {
                    writer.print(prefix);
                    writer.print("mHaveData=");
                    writer.print(this.e);
                    writer.print("  mDeliveredData=");
                    writer.println(this.f);
                    writer.print(prefix);
                    writer.print("mData=");
                    writer.println(this.g);
                }
                writer.print(prefix);
                writer.print("mStarted=");
                writer.print(this.h);
                writer.print(" mReportNextStart=");
                writer.print(this.k);
                writer.print(" mDestroyed=");
                writer.println(this.l);
                writer.print(prefix);
                writer.print("mRetaining=");
                writer.print(this.i);
                writer.print(" mRetainingStarted=");
                writer.print(this.j);
                writer.print(" mListenerRegistered=");
                writer.println(this.m);
                if (this.n != null) {
                    writer.print(prefix);
                    writer.println("Pending Loader ");
                    writer.print(this.n);
                    writer.println(":");
                    this = this.n;
                    prefix = prefix + "  ";
                } else {
                    return;
                }
            }
        }
    }

    final void b() {
        if (a) {
            new StringBuilder("Starting in ").append(this);
        }
        if (this.e) {
            new RuntimeException("here").fillInStackTrace();
            new StringBuilder("Called doStart when already started: ").append(this);
            return;
        }
        this.e = true;
        for (int i = this.b.a() - 1; i >= 0; i--) {
            android.support.v4.content.b.a aVar = (a) this.b.d(i);
            if (aVar.i && aVar.j) {
                aVar.h = true;
            } else if (aVar.h) {
                continue;
            } else {
                aVar.h = true;
                if (a) {
                    new StringBuilder("  Starting: ").append(aVar);
                }
                if (aVar.d == null && aVar.c != null) {
                    aVar.d = aVar.c.a();
                }
                if (aVar.d == null) {
                    continue;
                } else if (!aVar.d.getClass().isMemberClass() || Modifier.isStatic(aVar.d.getClass().getModifiers())) {
                    if (!aVar.m) {
                        aVar.d.a(aVar.a, (b) aVar);
                        aVar.d.a(aVar);
                        aVar.m = true;
                    }
                    aVar.d.a();
                } else {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + aVar.d);
                }
            }
        }
    }

    final void c() {
        if (a) {
            new StringBuilder("Stopping in ").append(this);
        }
        if (this.e) {
            for (int i = this.b.a() - 1; i >= 0; i--) {
                ((a) this.b.d(i)).a();
            }
            this.e = false;
            return;
        }
        new RuntimeException("here").fillInStackTrace();
        new StringBuilder("Called doStop when not started: ").append(this);
    }

    final void d() {
        if (a) {
            new StringBuilder("Retaining in ").append(this);
        }
        if (this.e) {
            this.f = true;
            this.e = false;
            for (int i = this.b.a() - 1; i >= 0; i--) {
                a aVar = (a) this.b.d(i);
                if (a) {
                    new StringBuilder("  Retaining: ").append(aVar);
                }
                aVar.i = true;
                aVar.j = aVar.h;
                aVar.h = false;
                aVar.c = null;
            }
            return;
        }
        new RuntimeException("here").fillInStackTrace();
        new StringBuilder("Called doRetain when not started: ").append(this);
    }

    final void e() {
        for (int i = this.b.a() - 1; i >= 0; i--) {
            ((a) this.b.d(i)).k = true;
        }
    }

    final void f() {
        for (int i = this.b.a() - 1; i >= 0; i--) {
            a aVar = (a) this.b.d(i);
            if (aVar.h && aVar.k) {
                aVar.k = false;
                if (aVar.e && !aVar.i) {
                    aVar.a(aVar.d, aVar.g);
                }
            }
        }
    }

    final void g() {
        int i;
        if (!this.f) {
            if (a) {
                new StringBuilder("Destroying Active in ").append(this);
            }
            for (i = this.b.a() - 1; i >= 0; i--) {
                ((a) this.b.d(i)).b();
            }
            this.b.b();
        }
        if (a) {
            new StringBuilder("Destroying Inactive in ").append(this);
        }
        for (i = this.c.a() - 1; i >= 0; i--) {
            ((a) this.c.d(i)).b();
        }
        this.c.b();
        this.g = null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        d.a(this.g, sb);
        sb.append("}}");
        return sb.toString();
    }

    public final void a(String prefix, PrintWriter writer) {
        String innerPrefix;
        int i;
        a li;
        if (this.b.a() > 0) {
            writer.print(prefix);
            writer.println("Active Loaders:");
            innerPrefix = prefix + "    ";
            for (i = 0; i < this.b.a(); i++) {
                li = (a) this.b.d(i);
                writer.print(prefix);
                writer.print("  #");
                writer.print(this.b.c(i));
                writer.print(": ");
                writer.println(li.toString());
                li.a(innerPrefix, writer);
            }
        }
        if (this.c.a() > 0) {
            writer.print(prefix);
            writer.println("Inactive Loaders:");
            innerPrefix = prefix + "    ";
            for (i = 0; i < this.c.a(); i++) {
                li = (a) this.c.d(i);
                writer.print(prefix);
                writer.print("  #");
                writer.print(this.c.c(i));
                writer.print(": ");
                writer.println(li.toString());
                li.a(innerPrefix, writer);
            }
        }
    }

    public final boolean a() {
        boolean loadersRunning = false;
        int count = this.b.a();
        for (int i = 0; i < count; i++) {
            a li = (a) this.b.d(i);
            int i2 = (!li.h || li.f) ? 0 : 1;
            loadersRunning |= i2;
        }
        return loadersRunning;
    }
}
