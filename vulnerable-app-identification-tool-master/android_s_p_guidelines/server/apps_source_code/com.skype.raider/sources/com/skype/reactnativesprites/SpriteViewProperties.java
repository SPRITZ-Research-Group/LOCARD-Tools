package com.skype.reactnativesprites;

import com.facebook.react.bridge.al;

public class SpriteViewProperties implements Cloneable {
    private String a;
    private String b;
    private String c;
    private al d;
    private Integer e;
    private Integer f;
    private Float g;
    private Boolean h;

    public static class Builder {
        private SpriteViewProperties a;
        private boolean b;
        private final SameThreadAssert c;

        public Builder(SpriteViewProperties viewProperties, SameThreadAssert sameThreadAssert) {
            this.c = sameThreadAssert;
            sameThreadAssert.a();
            if (viewProperties != null) {
                try {
                    this.a = (SpriteViewProperties) viewProperties.clone();
                    return;
                } catch (CloneNotSupportedException e) {
                    this.a = null;
                    throw new IllegalStateException("clone failed", e);
                }
            }
            this.a = new SpriteViewProperties();
        }

        public final SpriteViewProperties a() {
            this.c.a();
            if (!this.b) {
                return this.a;
            }
            throw new IllegalStateException("Builder can't be reused");
        }

        public final void a(String url) {
            this.c.a();
            this.a.a = url;
        }

        public final void b(String staticUrl) {
            this.c.a();
            this.a.b = staticUrl;
        }

        public final void c(String name) {
            this.c.a();
            this.a.c = name;
        }

        public final void a(int framesCount) {
            this.c.a();
            this.a.e = Integer.valueOf(framesCount);
        }

        public final void a(al framesSequence) {
            this.c.a();
            this.a.d = framesSequence;
        }

        public final void b(int firstFrame) {
            this.c.a();
            this.a.f = Integer.valueOf(firstFrame);
        }

        public final void a(float fps) {
            this.c.a();
            this.a.g = Float.valueOf(fps);
        }

        public final void a(boolean synchronizeAnimation) {
            this.c.a();
            this.a.h = Boolean.valueOf(synchronizeAnimation);
        }
    }

    /* synthetic */ SpriteViewProperties(byte b) {
        this();
    }

    private SpriteViewProperties() {
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final al c() {
        return this.d;
    }

    public final int d() {
        if (this.e != null) {
            return this.e.intValue();
        }
        return 0;
    }

    public final int e() {
        if (this.e != null) {
            return this.f.intValue();
        }
        return 0;
    }

    public final float f() {
        if (this.g != null) {
            return this.g.floatValue();
        }
        return 24.0f;
    }

    public final boolean g() {
        if (this.h != null) {
            return this.h.booleanValue();
        }
        return false;
    }

    public String toString() {
        return String.format("name: %s, url: %s, staticUrl: %s, framesCount: %s, firstFrame: %s, fps: %s", new Object[]{this.c, this.a, this.b, this.e, this.f, this.g});
    }
}
