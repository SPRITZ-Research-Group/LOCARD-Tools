package com.facebook.react.bridge.queue;

import javax.annotation.Nullable;

public final class f {
    @Nullable
    private final b a;
    private final b b;
    private final b c;

    public static class a {
        @Nullable
        private b a;
        @Nullable
        private b b;
        @Nullable
        private b c;

        public final a a(b spec) {
            com.facebook.infer.annotation.a.a(this.a == null, "Setting UI background queue multiple times!");
            this.a = spec;
            return this;
        }

        public final a b(b spec) {
            com.facebook.infer.annotation.a.a(this.b == null, "Setting native modules queue spec multiple times!");
            this.b = spec;
            return this;
        }

        public final a c(b spec) {
            com.facebook.infer.annotation.a.a(this.c == null, "Setting JS queue multiple times!");
            this.c = spec;
            return this;
        }

        public final f a() {
            return new f(this.a, (b) com.facebook.infer.annotation.a.a(this.b), (b) com.facebook.infer.annotation.a.a(this.c), (byte) 0);
        }
    }

    /* synthetic */ f(b x0, b x1, b x2, byte b) {
        this(x0, x1, x2);
    }

    private f(@Nullable b uiBackgroundQueueThreadSpec, b nativeModulesQueueThreadSpec, b jsQueueThreadSpec) {
        this.a = uiBackgroundQueueThreadSpec;
        this.b = nativeModulesQueueThreadSpec;
        this.c = jsQueueThreadSpec;
    }

    @Nullable
    public final b a() {
        return this.a;
    }

    public final b b() {
        return this.b;
    }

    public final b c() {
        return this.c;
    }
}
