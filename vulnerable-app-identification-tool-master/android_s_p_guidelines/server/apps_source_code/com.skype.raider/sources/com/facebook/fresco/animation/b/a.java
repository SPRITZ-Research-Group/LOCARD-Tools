package com.facebook.fresco.animation.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.c.d;
import com.facebook.fresco.animation.c.b;
import javax.annotation.Nullable;

public class a extends Drawable implements Animatable, com.facebook.drawable.a.a {
    private static final Class<?> a = a.class;
    private static final b b = new c();
    @Nullable
    private com.facebook.fresco.animation.a.a c;
    @Nullable
    private b d;
    private volatile boolean e;
    private long f;
    private long g;
    private long h;
    private int i;
    private long j;
    private long k;
    private int l;
    private volatile b m;
    @Nullable
    private volatile a n;
    @Nullable
    private d o;
    private final Runnable p;

    public interface a {
    }

    public a() {
        this(null);
    }

    public a(@Nullable com.facebook.fresco.animation.a.a animationBackend) {
        b bVar = null;
        this.j = 8;
        this.k = 0;
        this.m = b;
        this.n = null;
        this.p = new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.unscheduleSelf(this.a.p);
                this.a.invalidateSelf();
            }
        };
        this.c = animationBackend;
        com.facebook.fresco.animation.a.d dVar = this.c;
        if (dVar != null) {
            bVar = new com.facebook.fresco.animation.c.a(dVar);
        }
        this.d = bVar;
    }

    public int getIntrinsicWidth() {
        if (this.c == null) {
            return super.getIntrinsicWidth();
        }
        return this.c.a();
    }

    public int getIntrinsicHeight() {
        if (this.c == null) {
            return super.getIntrinsicHeight();
        }
        return this.c.b();
    }

    public void start() {
        if (!this.e && this.c != null && this.c.d() > 1) {
            this.e = true;
            this.f = SystemClock.uptimeMillis();
            this.h = this.f;
            this.g = -1;
            this.i = -1;
            invalidateSelf();
            b bVar = this.m;
        }
    }

    public void stop() {
        if (this.e) {
            this.e = false;
            this.f = 0;
            this.h = this.f;
            this.g = -1;
            this.i = -1;
            unscheduleSelf(this.p);
            b bVar = this.m;
        }
    }

    public boolean isRunning() {
        return this.e;
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (this.c != null) {
            this.c.a(bounds);
        }
    }

    public void draw(Canvas canvas) {
        if (this.c != null && this.d != null) {
            long animationTimeMs;
            b bVar;
            long actualRenderTimeStartMs = SystemClock.uptimeMillis();
            if (this.e) {
                animationTimeMs = (actualRenderTimeStartMs - this.f) + this.k;
            } else {
                animationTimeMs = Math.max(this.g, 0);
            }
            int frameNumberToDraw = this.d.a(animationTimeMs);
            if (frameNumberToDraw == -1) {
                frameNumberToDraw = this.c.d() - 1;
                bVar = this.m;
                this.e = false;
            } else if (frameNumberToDraw == 0 && this.i != -1 && actualRenderTimeStartMs >= this.h) {
                bVar = this.m;
            }
            boolean frameDrawn = this.c.a(this, canvas, frameNumberToDraw);
            if (frameDrawn) {
                bVar = this.m;
                this.i = frameNumberToDraw;
            }
            if (!frameDrawn) {
                this.l++;
                if (FLog.isLoggable(2)) {
                    FLog.v(a, "Dropped a frame. Count: %s", Integer.valueOf(this.l));
                }
            }
            long actualRenderTimeEnd = SystemClock.uptimeMillis();
            if (this.e) {
                long targetRenderTimeForNextFrameMs = this.d.b(actualRenderTimeEnd - this.f);
                if (targetRenderTimeForNextFrameMs != -1) {
                    this.h = this.f + (targetRenderTimeForNextFrameMs + this.j);
                    scheduleSelf(this.p, this.h);
                }
            }
            if (this.n != null) {
                boolean z = this.e;
            }
            this.g = animationTimeMs;
        }
    }

    public void setAlpha(int alpha) {
        if (this.o == null) {
            this.o = new d();
        }
        this.o.a(alpha);
        if (this.c != null) {
            this.c.a(alpha);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.o == null) {
            this.o = new d();
        }
        this.o.a(colorFilter);
        if (this.c != null) {
            this.c.a(colorFilter);
        }
    }

    public int getOpacity() {
        return -3;
    }

    protected boolean onLevelChange(int level) {
        if (this.e || this.g == ((long) level)) {
            return false;
        }
        this.g = (long) level;
        invalidateSelf();
        return true;
    }

    public final void a() {
        if (this.c != null) {
            this.c.c();
        }
    }
}
