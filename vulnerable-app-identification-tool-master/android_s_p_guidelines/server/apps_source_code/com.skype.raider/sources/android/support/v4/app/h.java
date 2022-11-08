package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.l;
import android.view.LayoutInflater;
import android.view.View;
import java.io.PrintWriter;

public abstract class h<E> extends f {
    private final Activity a;
    final Context b;
    final int c;
    final j d;
    private final Handler e;
    private l<String, p> f;
    private boolean g;
    private q h;
    private boolean i;
    private boolean j;

    h(FragmentActivity activity) {
        this(activity, activity, activity.mHandler);
    }

    private h(Activity activity, Context context, Handler handler) {
        this.d = new j();
        this.a = activity;
        this.b = context;
        this.e = handler;
        this.c = 0;
    }

    public void a(String prefix, PrintWriter writer, String[] args) {
    }

    public boolean b() {
        return true;
    }

    public LayoutInflater c() {
        return (LayoutInflater) this.b.getSystemService("layout_inflater");
    }

    public void d() {
    }

    public boolean e() {
        return true;
    }

    public int f() {
        return this.c;
    }

    @Nullable
    public View a(int id) {
        return null;
    }

    public boolean a() {
        return true;
    }

    final Activity g() {
        return this.a;
    }

    final Handler h() {
        return this.e;
    }

    final void a(String who) {
        if (this.f != null) {
            q lm = (q) this.f.get(who);
            if (lm != null && !lm.f) {
                lm.g();
                this.f.remove(who);
            }
        }
    }

    void a(Fragment fragment) {
    }

    final boolean i() {
        return this.g;
    }

    final void j() {
        if (!this.j) {
            this.j = true;
            if (this.h != null) {
                this.h.b();
            } else if (!this.i) {
                this.h = a("(root)", this.j);
                if (!(this.h == null || this.h.e)) {
                    this.h.b();
                }
            }
            this.i = true;
        }
    }

    final void a(boolean retain) {
        this.g = retain;
        if (this.h != null && this.j) {
            this.j = false;
            if (retain) {
                this.h.d();
            } else {
                this.h.c();
            }
        }
    }

    final void k() {
        if (this.h != null) {
            this.h.g();
        }
    }

    final void l() {
        if (this.f != null) {
            int i;
            int N = this.f.size();
            q[] loaders = new q[N];
            for (i = N - 1; i >= 0; i--) {
                loaders[i] = (q) this.f.c(i);
            }
            for (i = 0; i < N; i++) {
                q lm = loaders[i];
                if (lm.f) {
                    if (q.a) {
                        new StringBuilder("Finished Retaining in ").append(lm);
                    }
                    lm.f = false;
                    for (int a = lm.b.a() - 1; a >= 0; a--) {
                        a aVar = (a) lm.b.d(a);
                        if (aVar.i) {
                            if (q.a) {
                                new StringBuilder("  Finished Retaining: ").append(aVar);
                            }
                            aVar.i = false;
                            if (!(aVar.h == aVar.j || aVar.h)) {
                                aVar.a();
                            }
                        }
                        if (aVar.h && aVar.e && !aVar.k) {
                            aVar.a(aVar.d, aVar.g);
                        }
                    }
                }
                lm.f();
            }
        }
    }

    final q a(String who, boolean started) {
        if (this.f == null) {
            this.f = new l();
        }
        q lm = (q) this.f.get(who);
        if (!(!started || lm == null || lm.e)) {
            lm.b();
        }
        return lm;
    }

    final l<String, p> m() {
        boolean retainLoaders = false;
        if (this.f != null) {
            int i;
            int N = this.f.size();
            q[] loaders = new q[N];
            for (i = N - 1; i >= 0; i--) {
                loaders[i] = (q) this.f.c(i);
            }
            boolean doRetainLoaders = this.g;
            for (i = 0; i < N; i++) {
                q lm = loaders[i];
                if (!lm.f && doRetainLoaders) {
                    if (!lm.e) {
                        lm.b();
                    }
                    lm.d();
                }
                if (lm.f) {
                    retainLoaders = true;
                } else {
                    lm.g();
                    this.f.remove(lm.d);
                }
            }
        }
        if (retainLoaders) {
            return this.f;
        }
        return null;
    }

    final void a(l<String, p> loaderManagers) {
        if (loaderManagers != null) {
            int N = loaderManagers.size();
            for (int i = 0; i < N; i++) {
                ((q) loaderManagers.c(i)).g = this;
            }
        }
        this.f = loaderManagers;
    }

    final void a(String prefix, PrintWriter writer) {
        writer.print(prefix);
        writer.print("mLoadersStarted=");
        writer.println(this.j);
        if (this.h != null) {
            writer.print(prefix);
            writer.print("Loader Manager ");
            writer.print(Integer.toHexString(System.identityHashCode(this.h)));
            writer.println(":");
            this.h.a(prefix + "  ", writer);
        }
    }
}
