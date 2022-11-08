package com.airbnb.lottie.c;

import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class e {
    private final List<String> a;
    @Nullable
    private f b;

    public e(String... keys) {
        this.a = Arrays.asList(keys);
    }

    private e(e keyPath) {
        this.a = new ArrayList(keyPath.a);
        this.b = keyPath.b;
    }

    @CheckResult
    public final e a(String key) {
        e newKeyPath = new e(this);
        newKeyPath.a.add(key);
        return newKeyPath;
    }

    public final e a(f element) {
        e keyPath = new e(this);
        keyPath.b = element;
        return keyPath;
    }

    @Nullable
    public final f a() {
        return this.b;
    }

    public final boolean a(String key, int depth) {
        if (key.equals("__container")) {
            return true;
        }
        if (depth >= this.a.size()) {
            return false;
        }
        if (((String) this.a.get(depth)).equals(key) || ((String) this.a.get(depth)).equals("**") || ((String) this.a.get(depth)).equals("*")) {
            return true;
        }
        return false;
    }

    public final int b(String key, int depth) {
        if (key.equals("__container")) {
            return 0;
        }
        if (!((String) this.a.get(depth)).equals("**")) {
            return 1;
        }
        if (depth == this.a.size() - 1) {
            return 0;
        }
        return ((String) this.a.get(depth + 1)).equals(key) ? 2 : 0;
    }

    public final boolean c(String key, int depth) {
        if (depth >= this.a.size()) {
            return false;
        }
        boolean isLastDepth;
        if (depth == this.a.size() - 1) {
            isLastDepth = true;
        } else {
            isLastDepth = false;
        }
        String keyAtDepth = (String) this.a.get(depth);
        if (keyAtDepth.equals("**")) {
            boolean z;
            if (isLastDepth || !((String) this.a.get(depth + 1)).equals(key)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                if (depth == this.a.size() - 2 || (depth == this.a.size() - 3 && b())) {
                    return true;
                }
                return false;
            } else if (isLastDepth) {
                return true;
            } else {
                if (depth + 1 >= this.a.size() - 1) {
                    return ((String) this.a.get(depth + 1)).equals(key);
                }
                return false;
            }
        }
        boolean matches;
        if (keyAtDepth.equals(key) || keyAtDepth.equals("*")) {
            matches = true;
        } else {
            matches = false;
        }
        if ((isLastDepth || (depth == this.a.size() - 2 && b())) && matches) {
            return true;
        }
        return false;
    }

    public final boolean d(String key, int depth) {
        if (key.equals("__container")) {
            return true;
        }
        return depth < this.a.size() + -1 || ((String) this.a.get(depth)).equals("**");
    }

    private boolean b() {
        return ((String) this.a.get(this.a.size() - 1)).equals("**");
    }

    public final String toString() {
        return "KeyPath{keys=" + this.a + ",resolved=" + (this.b != null) + '}';
    }
}
