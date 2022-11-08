package com.projectseptember.RNGL;

import android.opengl.GLES20;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class f {
    public final List<l> a = new ArrayList();
    private int b;
    private int c = 0;
    private int d = 0;
    private Executor e;
    private int f;
    private int g;
    private boolean h = false;

    class a {
        final /* synthetic */ f a;
        private int b;
        private int c;
        private int d;

        public a(f this$0) {
            this.a = this$0;
            int[] fbo = new int[1];
            int[] rbo = new int[1];
            int[] tex = new int[1];
            GLES20.glGetIntegerv(36006, fbo, 0);
            GLES20.glGetIntegerv(36007, rbo, 0);
            GLES20.glGetIntegerv(32873, tex, 0);
            this.b = fbo[0];
            this.c = rbo[0];
            this.d = tex[0];
        }

        static /* synthetic */ void a(a x0) {
            FLog.i("RNGLFBO", "restore glBindFramebuffer %d Renderbuffer %d Texture %d", Integer.valueOf(x0.b), Integer.valueOf(x0.c), Integer.valueOf(x0.d));
            GLES20.glBindFramebuffer(36160, x0.b);
            GLES20.glBindRenderbuffer(36161, x0.c);
            GLES20.glBindTexture(3553, x0.d);
        }
    }

    public f(Executor glExecutor, int maxWidth, int maxHeight, int causeId) {
        this.e = glExecutor;
        a state = new a(this);
        int[] handleArr = new int[1];
        GLES20.glGenFramebuffers(1, handleArr, 0);
        this.b = handleArr[0];
        this.f = maxWidth;
        this.g = maxHeight;
        FLog.i("RNGLFBO", "init handle %d, glBindFrameBuffer (causeId %x)", Integer.valueOf(this.b), Integer.valueOf(causeId));
        GLES20.glBindFramebuffer(36160, this.b);
        for (int i = 0; i <= 0; i++) {
            List list = this.a;
            int i2 = this.c;
            int i3 = this.d;
            an.a(!this.h, "bind: Must not be invalidated (causeId " + causeId + ")");
            FLog.i("RNGLFBO", "initTexture %d x %d attachment %d (causeId %x)", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(36064), Integer.valueOf(causeId));
            l lVar = new l(this.e, this.f, this.g, causeId);
            lVar.b(causeId);
            lVar.a(i2, i3, causeId);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, lVar.a(), 0);
            list.add(lVar);
        }
        a.a(state);
    }

    protected final void finalize() throws Throwable {
        super.finalize();
        an.a(this.h, "finalize: Must be invalidated");
    }

    public final void a(final int causeId) {
        this.h = true;
        FLog.i("RNGLFBO", "invalidate %d (causeId %x)", Integer.valueOf(this.b), Integer.valueOf(causeId));
        this.e.execute(new Runnable(this) {
            final /* synthetic */ f b;

            public final void run() {
                FLog.i("RNGLFBO", "glDeleteFramebuffers %d (causeId %x)", Integer.valueOf(this.b.b), Integer.valueOf(causeId));
                GLES20.glDeleteFramebuffers(1, new int[]{this.b.b}, 0);
            }
        });
    }

    public final void b(int causeId) {
        boolean z;
        if (this.h) {
            z = false;
        } else {
            z = true;
        }
        an.a(z, "bind: Must not be invalidated (causeId " + causeId + ")");
        FLog.i("RNGLFBO", "bind glBindFramebuffer %d (causeId %x)", Integer.valueOf(this.b), Integer.valueOf(causeId));
        GLES20.glBindFramebuffer(36160, this.b);
        GLES20.glViewport(0, 0, this.c, this.d);
    }

    public final void a(int w, int h, int causeId) {
        an.a(!this.h, "setShape: Must not be invalidated (causeId " + causeId + ")");
        if (w != this.c || h != this.d) {
            int[] maxFBOSize = new int[1];
            GLES20.glGetIntegerv(34024, maxFBOSize, 0);
            if (w < 0 || w > maxFBOSize[0] || h < 0 || h > maxFBOSize[0]) {
                throw new IllegalArgumentException("Can't resize framebuffer. Invalid dimensions (causeId " + causeId + ")");
            }
            this.c = w;
            this.d = h;
            a state = new a(this);
            for (l a : this.a) {
                a.a(w, h, causeId);
            }
            FLog.i("RNGLFBO", "setShape glBindFramebuffer %d (causeId %x)", Integer.valueOf(this.b), Integer.valueOf(causeId));
            GLES20.glBindFramebuffer(36160, this.b);
            int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
            if (glCheckFramebufferStatus != 36053) {
                switch (glCheckFramebufferStatus) {
                    case 36054:
                        throw new RuntimeException("Framebuffer incomplete attachment");
                    case 36055:
                        throw new RuntimeException("Framebuffer incomplete missing attachment");
                    case 36057:
                        throw new RuntimeException("Framebuffer incomplete dimensions");
                    case 36061:
                        throw new RuntimeException("Framebuffer unsupported");
                    default:
                        throw new RuntimeException("Failed to create framebuffer: " + glCheckFramebufferStatus);
                }
            }
            a.a(state);
        }
    }
}
