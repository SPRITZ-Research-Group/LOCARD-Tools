package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.e;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.k.d;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class ai implements aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> {
    private final aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> a;
    private final f b;
    private final Executor c;

    private class a extends m<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>, com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> {
        final /* synthetic */ ai a;
        private final am b;
        private final String c;
        private final d d;
        @GuardedBy("PostprocessorConsumer.this")
        private boolean e;
        @GuardedBy("PostprocessorConsumer.this")
        @Nullable
        private com.facebook.common.f.a<com.facebook.imagepipeline.image.c> f = null;
        @GuardedBy("PostprocessorConsumer.this")
        private int g = 0;
        @GuardedBy("PostprocessorConsumer.this")
        private boolean h = false;
        @GuardedBy("PostprocessorConsumer.this")
        private boolean i = false;

        static /* synthetic */ void a(a x0, com.facebook.common.f.a x1, int x2) {
            com.facebook.common.f.a aVar = null;
            h.a(com.facebook.common.f.a.a(x1));
            if (((com.facebook.imagepipeline.image.c) x1.a()) instanceof com.facebook.imagepipeline.image.d) {
                x0.b.a(x0.c, "PostprocessorProducer");
                try {
                    aVar = x0.a((com.facebook.imagepipeline.image.c) x1.a());
                    x0.b.a(x0.c, "PostprocessorProducer", a(x0.b, x0.c, x0.d));
                    x0.a(aVar, x2);
                } catch (Throwable e) {
                    x0.b.a(x0.c, "PostprocessorProducer", e, a(x0.b, x0.c, x0.d));
                    x0.c(e);
                } finally {
                    com.facebook.common.f.a.c(aVar);
                }
            } else {
                x0.a(x1, x2);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected final /* synthetic */ void a(Object obj, int i) {
            com.facebook.common.f.a aVar = (com.facebook.common.f.a) obj;
            if (com.facebook.common.f.a.a(aVar)) {
                synchronized (this) {
                    if (this.e) {
                        return;
                    }
                    com.facebook.common.f.a aVar2 = this.f;
                    this.f = com.facebook.common.f.a.b(aVar);
                    this.g = i;
                    this.h = true;
                    boolean e = e();
                }
            } else if (b.a(i)) {
                a(null, i);
            }
        }

        public a(final ai aiVar, Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> consumer, am listener, String requestId, d postprocessor, ak producerContext) {
            this.a = aiVar;
            super(consumer);
            this.b = listener;
            this.c = requestId;
            this.d = postprocessor;
            producerContext.a(new e(this) {
                final /* synthetic */ a b;

                public final void a() {
                    this.b.f();
                }
            });
        }

        protected final void a(Throwable t) {
            c(t);
        }

        protected final void a() {
            f();
        }

        private void c() {
            this.a.c.execute(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    com.facebook.common.f.a closeableImageRef;
                    int status;
                    synchronized (this.a) {
                        closeableImageRef = this.a.f;
                        status = this.a.g;
                        this.a.f = null;
                        this.a.h = false;
                    }
                    if (com.facebook.common.f.a.a(closeableImageRef)) {
                        try {
                            a.a(this.a, closeableImageRef, status);
                        } finally {
                            com.facebook.common.f.a.c(closeableImageRef);
                        }
                    }
                    a.f(this.a);
                }
            });
        }

        private synchronized boolean e() {
            boolean z = true;
            synchronized (this) {
                if (this.e || !this.h || this.i || !com.facebook.common.f.a.a(this.f)) {
                    z = false;
                } else {
                    this.i = true;
                }
            }
            return z;
        }

        private static Map<String, String> a(am listener, String requestId, d postprocessor) {
            if (listener.b(requestId)) {
                return e.a("Postprocessor", postprocessor.b());
            }
            return null;
        }

        private com.facebook.common.f.a<com.facebook.imagepipeline.image.c> a(com.facebook.imagepipeline.image.c sourceImage) {
            com.facebook.imagepipeline.image.d staticBitmap = (com.facebook.imagepipeline.image.d) sourceImage;
            com.facebook.common.f.a bitmapRef = this.d.a(staticBitmap.f(), this.a.b);
            try {
                com.facebook.common.f.a<com.facebook.imagepipeline.image.c> a = com.facebook.common.f.a.a(new com.facebook.imagepipeline.image.d(bitmapRef, sourceImage.g(), staticBitmap.i(), staticBitmap.j()));
                return a;
            } finally {
                com.facebook.common.f.a.c(bitmapRef);
            }
        }

        private void a(com.facebook.common.f.a<com.facebook.imagepipeline.image.c> newRef, int status) {
            boolean isLast = b.a(status);
            if ((!isLast && !g()) || (isLast && h())) {
                d().b(newRef, status);
            }
        }

        private void c(Throwable throwable) {
            if (h()) {
                d().b(throwable);
            }
        }

        private void f() {
            if (h()) {
                d().b();
            }
        }

        private synchronized boolean g() {
            return this.e;
        }

        private boolean h() {
            boolean z = true;
            synchronized (this) {
                if (this.e) {
                    z = false;
                } else {
                    com.facebook.common.f.a<com.facebook.imagepipeline.image.c> oldSourceImageRef = this.f;
                    this.f = null;
                    this.e = true;
                    com.facebook.common.f.a.c(oldSourceImageRef);
                }
            }
            return z;
        }

        static /* synthetic */ void f(a x0) {
            boolean e;
            synchronized (x0) {
                x0.i = false;
                e = x0.e();
            }
            if (e) {
                x0.c();
            }
        }
    }

    class b extends m<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>, com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> {
        final /* synthetic */ ai a;
        @GuardedBy("RepeatedPostprocessorConsumer.this")
        private boolean b;
        @GuardedBy("RepeatedPostprocessorConsumer.this")
        @Nullable
        private com.facebook.common.f.a<com.facebook.imagepipeline.image.c> c;

        /* synthetic */ b(ai x0, a x1, ak x3, byte b) {
            this(x0, x1, x3);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected final /* synthetic */ void a(Object obj, int i) {
            com.facebook.common.f.a aVar = (com.facebook.common.f.a) obj;
            if (!b.b(i)) {
                synchronized (this) {
                    if (this.b) {
                    } else {
                        com.facebook.common.f.a aVar2 = this.c;
                        this.c = com.facebook.common.f.a.b(aVar);
                        com.facebook.common.f.a.c(aVar2);
                    }
                }
                synchronized (this) {
                    if (this.b) {
                        return;
                    }
                    com.facebook.common.f.a b = com.facebook.common.f.a.b(this.c);
                }
            }
        }

        private b(final ai this$0, a postprocessorConsumer, ak context) {
            this.a = this$0;
            super(postprocessorConsumer);
            this.b = false;
            this.c = null;
            context.a(new e(this) {
                final /* synthetic */ b b;

                public final void a() {
                    if (this.b.c()) {
                        this.b.d().b();
                    }
                }
            });
        }

        protected final void a(Throwable throwable) {
            if (c()) {
                d().b(throwable);
            }
        }

        protected final void a() {
            if (c()) {
                d().b();
            }
        }

        private boolean c() {
            boolean z = true;
            synchronized (this) {
                if (this.b) {
                    z = false;
                } else {
                    com.facebook.common.f.a<com.facebook.imagepipeline.image.c> oldSourceImageRef = this.c;
                    this.c = null;
                    this.b = true;
                    com.facebook.common.f.a.c(oldSourceImageRef);
                }
            }
            return z;
        }
    }

    class c extends m<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>, com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> {
        final /* synthetic */ ai a;

        /* synthetic */ c(ai x0, a x1, byte b) {
            this(x0, x1);
        }

        protected final /* synthetic */ void a(Object obj, int i) {
            com.facebook.common.f.a aVar = (com.facebook.common.f.a) obj;
            if (!b.b(i)) {
                d().b(aVar, i);
            }
        }

        private c(ai this$0, a postprocessorConsumer) {
            this.a = this$0;
            super(postprocessorConsumer);
        }
    }

    public ai(aj<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> inputProducer, f platformBitmapFactory, Executor executor) {
        this.a = (aj) h.a((Object) inputProducer);
        this.b = platformBitmapFactory;
        this.c = (Executor) h.a((Object) executor);
    }

    public final void a(Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> consumer, ak context) {
        Consumer<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> postprocessorConsumer;
        am listener = context.c();
        d postprocessor = context.a().q();
        a basePostprocessorConsumer = new a(this, consumer, listener, context.b(), postprocessor, context);
        if (postprocessor instanceof com.facebook.imagepipeline.k.e) {
            postprocessorConsumer = new b(this, basePostprocessorConsumer, context, (byte) 0);
        } else {
            postprocessorConsumer = new c(this, basePostprocessorConsumer, (byte) 0);
        }
        this.a.a(postprocessorConsumer, context);
    }
}
