package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class al {
    private final int[] a = new int[4];
    private final l b;
    private final com.facebook.react.a.c c;
    private final Object d = new Object();
    private final Object e = new Object();
    private final g f;
    private final ag g;
    private ArrayList<s> h = new ArrayList();
    @GuardedBy("mDispatchRunnablesLock")
    private ArrayList<Runnable> i = new ArrayList();
    @GuardedBy("mNonBatchedOperationsLock")
    private ArrayDeque<s> j = new ArrayDeque();
    @Nullable
    private com.facebook.react.uimanager.debug.a k;
    private boolean l = false;
    private boolean m = false;

    public interface s {
        void a();
    }

    private static abstract class b implements s {
        protected final int b;

        public b(int animationID) {
            this.b = animationID;
        }
    }

    private class a extends b {
        final /* synthetic */ al a;
        private final int c;
        private final com.facebook.react.bridge.d d;

        /* synthetic */ a(al x0, int x1, int x2, com.facebook.react.bridge.d x3, byte b) {
            this(x0, x1, x2, x3);
        }

        private a(al alVar, int reactTag, int animationID, com.facebook.react.bridge.d successCallback) {
            this.a = alVar;
            super(animationID);
            this.c = reactTag;
            this.d = successCallback;
        }

        public final void a() {
            com.facebook.react.a.a animation = this.a.c.a(this.b);
            if (animation != null) {
                this.a.b.a(this.c, animation, this.d);
                return;
            }
            throw new f("Animation with id " + this.b + " was not found");
        }
    }

    private abstract class w implements s {
        public int b;
        final /* synthetic */ al c;

        public w(al alVar, int tag) {
            this.c = alVar;
            this.b = tag;
        }
    }

    private final class c extends w {
        final /* synthetic */ al a;
        private final int d;
        private final boolean e;
        private final boolean f;

        public c(al alVar, int tag, int initialTag, boolean clearResponder, boolean blockNativeResponder) {
            this.a = alVar;
            super(alVar, tag);
            this.d = initialTag;
            this.f = clearResponder;
            this.e = blockNativeResponder;
        }

        public final void a() {
            if (this.f) {
                this.a.b.b();
            } else {
                this.a.b.a(this.b, this.d, this.e);
            }
        }
    }

    private class d implements s {
        final /* synthetic */ al a;
        private final am b;

        /* synthetic */ d(al x0, am x1, byte b) {
            this(x0, x1);
        }

        private d(al alVar, am config) {
            this.a = alVar;
            this.b = config;
        }

        public final void a() {
            this.a.b.a(this.b);
        }
    }

    private final class e extends w {
        final /* synthetic */ al a;
        private final ae d;
        private final String e;
        @Nullable
        private final x f;

        public e(al alVar, ae themedContext, int tag, String className, @Nullable x initialProps) {
            this.a = alVar;
            super(alVar, tag);
            this.d = themedContext;
            this.e = className;
            this.f = initialProps;
        }

        public final void a() {
            this.a.b.a(this.d, this.b, this.e, this.f);
        }
    }

    private final class f extends w {
        final /* synthetic */ al a;
        private final int d;
        @Nullable
        private final com.facebook.react.bridge.al e;

        public f(al alVar, int tag, int command, @Nullable com.facebook.react.bridge.al args) {
            this.a = alVar;
            super(alVar, tag);
            this.d = command;
            this.e = args;
        }

        public final void a() {
            this.a.b.a(this.b, this.d, this.e);
        }
    }

    private class g extends d {
        final /* synthetic */ al a;

        /* synthetic */ g(al x0, ai x1, byte b) {
            this(x0, x1);
        }

        private g(al alVar, ai reactContext) {
            this.a = alVar;
            super(reactContext);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(long frameTimeNanos) {
            if (this.a.m) {
                FLog.w("React", "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            com.facebook.systrace.a.a("dispatchNonBatchedUIOperations");
            while (16 - ((System.nanoTime() - frameTimeNanos) / 1000000) >= 8) {
                try {
                    synchronized (this.a.e) {
                        if (!this.a.j.isEmpty()) {
                            s sVar = (s) this.a.j.pollFirst();
                        }
                    }
                } catch (Exception e) {
                    this.a.m = true;
                    throw e;
                } catch (Throwable th) {
                    com.facebook.systrace.a.a();
                }
            }
            com.facebook.systrace.a.a();
            this.a.f();
            com.facebook.react.modules.core.e.b().a(com.facebook.react.modules.core.e.a.DISPATCH_UI, this);
        }
    }

    private final class h implements s {
        final /* synthetic */ al a;
        private final int b;
        private final float c;
        private final float d;
        private final com.facebook.react.bridge.d e;

        /* synthetic */ h(al x0, int x1, float x2, float x3, com.facebook.react.bridge.d x4, byte b) {
            this(x0, x1, x2, x3, x4);
        }

        private h(al alVar, int reactTag, float targetX, float targetY, com.facebook.react.bridge.d callback) {
            this.a = alVar;
            this.b = reactTag;
            this.c = targetX;
            this.d = targetY;
            this.e = callback;
        }

        public final void a() {
            try {
                this.a.b.a(this.b, this.a.a);
                float containerX = (float) this.a.a[0];
                float containerY = (float) this.a.a[1];
                try {
                    this.a.b.a(this.a.b.a(this.b, this.c, this.d), this.a.a);
                    float x = p.b(((float) this.a.a[0]) - containerX);
                    float y = p.b(((float) this.a.a[1]) - containerY);
                    float width = p.b((float) this.a.a[2]);
                    float height = p.b((float) this.a.a[3]);
                    this.e.invoke(Integer.valueOf(touchTargetReactTag), Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
                } catch (f e) {
                    this.e.invoke(new Object[0]);
                }
            } catch (f e2) {
                this.e.invoke(new Object[0]);
            }
        }
    }

    private final class i extends w {
        final /* synthetic */ al a;
        @Nullable
        private final int[] d;
        @Nullable
        private final am[] e;
        @Nullable
        private final int[] f;

        public i(al alVar, int tag, @Nullable int[] indicesToRemove, @Nullable am[] viewsToAdd, @Nullable int[] tagsToDelete) {
            this.a = alVar;
            super(alVar, tag);
            this.d = indicesToRemove;
            this.e = viewsToAdd;
            this.f = tagsToDelete;
        }

        public final void a() {
            this.a.b.a(this.b, this.d, this.e, this.f);
        }
    }

    private final class j implements s {
        final /* synthetic */ al a;
        private final int b;
        private final com.facebook.react.bridge.d c;

        /* synthetic */ j(al x0, int x1, com.facebook.react.bridge.d x2, byte b) {
            this(x0, x1, x2);
        }

        private j(al alVar, int reactTag, com.facebook.react.bridge.d callback) {
            this.a = alVar;
            this.b = reactTag;
            this.c = callback;
        }

        public final void a() {
            try {
                this.a.b.b(this.b, this.a.a);
                float x = p.b((float) this.a.a[0]);
                float y = p.b((float) this.a.a[1]);
                float width = p.b((float) this.a.a[2]);
                float height = p.b((float) this.a.a[3]);
                this.c.invoke(Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
            } catch (n e) {
                this.c.invoke(new Object[0]);
            }
        }
    }

    private final class k implements s {
        final /* synthetic */ al a;
        private final int b;
        private final com.facebook.react.bridge.d c;

        /* synthetic */ k(al x0, int x1, com.facebook.react.bridge.d x2, byte b) {
            this(x0, x1, x2);
        }

        private k(al alVar, int reactTag, com.facebook.react.bridge.d callback) {
            this.a = alVar;
            this.b = reactTag;
            this.c = callback;
        }

        public final void a() {
            try {
                this.a.b.a(this.b, this.a.a);
                float x = p.b((float) this.a.a[0]);
                float y = p.b((float) this.a.a[1]);
                float width = p.b((float) this.a.a[2]);
                float height = p.b((float) this.a.a[3]);
                this.c.invoke(Integer.valueOf(0), Integer.valueOf(0), Float.valueOf(width), Float.valueOf(height), Float.valueOf(x), Float.valueOf(y));
            } catch (n e) {
                this.c.invoke(new Object[0]);
            }
        }
    }

    private class l extends b {
        final /* synthetic */ al a;
        private final com.facebook.react.a.a c;

        /* synthetic */ l(al x0, com.facebook.react.a.a x1, byte b) {
            this(x0, x1);
        }

        private l(al alVar, com.facebook.react.a.a animation) {
            this.a = alVar;
            super(animation.b());
            this.c = animation;
        }

        public final void a() {
            this.a.c.a(this.c);
        }
    }

    private final class m extends b {
        final /* synthetic */ al a;

        /* synthetic */ m(al x0, int x1, byte b) {
            this(x0, x1);
        }

        private m(al alVar, int animationID) {
            this.a = alVar;
            super(animationID);
        }

        public final void a() {
            com.facebook.react.a.a animation = this.a.c.a(this.b);
            if (animation != null) {
                animation.a();
            }
        }
    }

    private final class n extends w {
        final /* synthetic */ al a;

        public n(al alVar, int tag) {
            this.a = alVar;
            super(alVar, tag);
        }

        public final void a() {
            this.a.b.b(this.b);
        }
    }

    private final class o extends w {
        final /* synthetic */ al a;
        private final int d;

        /* synthetic */ o(al x0, int x1, int x2, byte b) {
            this(x0, x1, x2);
        }

        private o(al alVar, int tag, int eventType) {
            this.a = alVar;
            super(alVar, tag);
            this.d = eventType;
        }

        public final void a() {
            this.a.b.a(this.b, this.d);
        }
    }

    private class p implements s {
        final /* synthetic */ al a;
        private final boolean b;

        /* synthetic */ p(al x0, boolean x1, byte b) {
            this(x0, x1);
        }

        private p(al alVar, boolean enabled) {
            this.a = alVar;
            this.b = enabled;
        }

        public final void a() {
            this.a.b.a(this.b);
        }
    }

    private final class q extends w {
        final /* synthetic */ al a;
        private final com.facebook.react.bridge.al d;
        private final com.facebook.react.bridge.d e;
        private final com.facebook.react.bridge.d f;

        public q(al alVar, int tag, com.facebook.react.bridge.al items, com.facebook.react.bridge.d error, com.facebook.react.bridge.d success) {
            this.a = alVar;
            super(alVar, tag);
            this.d = items;
            this.e = error;
            this.f = success;
        }

        public final void a() {
            this.a.b.a(this.b, this.d, this.f, this.e);
        }
    }

    private class r implements s {
        final /* synthetic */ al a;
        private final ah b;

        public r(al alVar, ah block) {
            this.a = alVar;
            this.b = block;
        }

        public final void a() {
            this.b.a(this.a.b);
        }
    }

    private final class t extends w {
        final /* synthetic */ al a;
        private final int d;
        private final int e;
        private final int f;
        private final int g;
        private final int h;

        public t(al alVar, int parentTag, int tag, int x, int y, int width, int height) {
            this.a = alVar;
            super(alVar, tag);
            this.d = parentTag;
            this.e = x;
            this.f = y;
            this.g = width;
            this.h = height;
        }

        public final void a() {
            this.a.b.a(this.d, this.b, this.e, this.f, this.g, this.h);
        }
    }

    private final class u extends w {
        final /* synthetic */ al a;
        private final x d;

        /* synthetic */ u(al x0, int x1, x x2, byte b) {
            this(x0, x1, x2);
        }

        private u(al alVar, int tag, x props) {
            this.a = alVar;
            super(alVar, tag);
            this.d = props;
        }

        public final void a() {
            this.a.b.a(this.b, this.d);
        }
    }

    private final class v extends w {
        final /* synthetic */ al a;
        private final Object d;

        public v(al alVar, int tag, Object extraData) {
            this.a = alVar;
            super(alVar, tag);
            this.d = extraData;
        }

        public final void a() {
            this.a.b.a(this.b, this.d);
        }
    }

    public al(ag reactContext, l nativeViewHierarchyManager) {
        this.b = nativeViewHierarchyManager;
        this.c = nativeViewHierarchyManager.a();
        this.f = new g(this, reactContext, (byte) 0);
        this.g = reactContext;
    }

    final l a() {
        return this.b;
    }

    public final void a(@Nullable com.facebook.react.uimanager.debug.a listener) {
        this.k = listener;
    }

    public final boolean b() {
        return this.h.isEmpty();
    }

    public final void a(int tag, SizeMonitoringFrameLayout rootView) {
        this.b.a(tag, rootView);
    }

    public final void a(int rootViewTag) {
        this.h.add(new n(this, rootViewTag));
    }

    public final void a(int tag, int initialTag, boolean blockNativeResponder) {
        this.h.add(new c(this, tag, initialTag, false, blockNativeResponder));
    }

    public final void c() {
        this.h.add(new c(this, 0, 0, true, false));
    }

    public final void a(int reactTag, int commandId, com.facebook.react.bridge.al commandArgs) {
        this.h.add(new f(this, reactTag, commandId, commandArgs));
    }

    public final void a(int reactTag, Object extraData) {
        this.h.add(new v(this, reactTag, extraData));
    }

    public final void a(int reactTag, com.facebook.react.bridge.al items, com.facebook.react.bridge.d error, com.facebook.react.bridge.d success) {
        this.h.add(new q(this, reactTag, items, error, success));
    }

    public final void a(ae themedContext, int viewReactTag, String viewClassName, @Nullable x initialProps) {
        synchronized (this.e) {
            this.j.addLast(new e(this, themedContext, viewReactTag, viewClassName, initialProps));
        }
    }

    public final void a(int reactTag, x props) {
        this.h.add(new u(this, reactTag, props, (byte) 0));
    }

    public final void a(int parentTag, int reactTag, int x, int y, int width, int height) {
        this.h.add(new t(this, parentTag, reactTag, x, y, width, height));
    }

    public final void a(int reactTag, @Nullable int[] indicesToRemove, @Nullable am[] viewsToAdd, @Nullable int[] tagsToDelete) {
        this.h.add(new i(this, reactTag, indicesToRemove, viewsToAdd, tagsToDelete));
    }

    public final void a(com.facebook.react.a.a animation) {
        this.h.add(new l(this, animation, (byte) 0));
    }

    public final void a(int reactTag, int animationID, com.facebook.react.bridge.d onSuccess) {
        this.h.add(new a(this, reactTag, animationID, onSuccess, (byte) 0));
    }

    public final void b(int animationID) {
        this.h.add(new m(this, animationID, (byte) 0));
    }

    public final void a(boolean enabled) {
        this.h.add(new p(this, enabled, (byte) 0));
    }

    public final void a(am config) {
        this.h.add(new d(this, config, (byte) 0));
    }

    public final void a(int reactTag, com.facebook.react.bridge.d callback) {
        this.h.add(new k(this, reactTag, callback, (byte) 0));
    }

    public final void b(int reactTag, com.facebook.react.bridge.d callback) {
        this.h.add(new j(this, reactTag, callback, (byte) 0));
    }

    public final void a(int reactTag, float targetX, float targetY, com.facebook.react.bridge.d callback) {
        this.h.add(new h(this, reactTag, targetX, targetY, callback, (byte) 0));
    }

    public final void a(int tag, int eventType) {
        this.h.add(new o(this, tag, eventType, (byte) 0));
    }

    public final void a(ah block) {
        this.h.add(new r(this, block));
    }

    final void c(final int batchId) {
        com.facebook.systrace.b.a();
        try {
            ArrayList<s> batchedOperations;
            ArrayDeque<s> nonBatchedOperations;
            if (this.h.isEmpty()) {
                batchedOperations = null;
            } else {
                batchedOperations = this.h;
                this.h = new ArrayList();
            }
            synchronized (this.e) {
                if (this.j.isEmpty()) {
                    nonBatchedOperations = null;
                } else {
                    nonBatchedOperations = this.j;
                    this.j = new ArrayDeque();
                }
            }
            if (this.k != null) {
                this.k.c();
            }
            Runnable runOperations = new Runnable(this) {
                final /* synthetic */ al d;

                public final void run() {
                    com.facebook.systrace.b.a();
                    try {
                        Iterator it;
                        if (nonBatchedOperations != null) {
                            it = nonBatchedOperations.iterator();
                            while (it.hasNext()) {
                                ((s) it.next()).a();
                            }
                        }
                        if (batchedOperations != null) {
                            it = batchedOperations.iterator();
                            while (it.hasNext()) {
                                ((s) it.next()).a();
                            }
                        }
                        this.d.b.c();
                        if (this.d.k != null) {
                            this.d.k.d();
                        }
                        com.facebook.systrace.a.a();
                    } catch (Exception e) {
                        this.d.m = true;
                        throw e;
                    } catch (Throwable th) {
                        com.facebook.systrace.a.a();
                    }
                }
            };
            com.facebook.systrace.b.a();
            synchronized (this.d) {
                com.facebook.systrace.a.a();
                this.i.add(runOperations);
            }
            if (!this.l) {
                ap.a(new com.facebook.react.bridge.l(this, this.g) {
                    final /* synthetic */ al a;

                    public final void a() {
                        this.a.f();
                    }
                });
            }
            com.facebook.systrace.a.a();
        } catch (Throwable th) {
            com.facebook.systrace.a.a();
        }
    }

    final void d() {
        this.l = true;
        com.facebook.react.modules.core.e.b().a(com.facebook.react.modules.core.e.a.DISPATCH_UI, this.f);
    }

    final void e() {
        this.l = false;
        com.facebook.react.modules.core.e.b().b(com.facebook.react.modules.core.e.a.DISPATCH_UI, this.f);
        f();
    }

    private void f() {
        if (this.m) {
            FLog.w("React", "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        ArrayList<Runnable> runnables;
        synchronized (this.d) {
            if (this.i.isEmpty()) {
                runnables = null;
            } else {
                runnables = this.i;
                this.i = new ArrayList();
            }
        }
        if (runnables != null) {
            Iterator it = runnables.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }
}
