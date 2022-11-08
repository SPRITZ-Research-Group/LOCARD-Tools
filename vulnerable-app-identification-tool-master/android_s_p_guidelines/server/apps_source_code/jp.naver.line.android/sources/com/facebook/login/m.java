package com.facebook.login;

import com.facebook.AccessToken;
import java.util.Set;

public final class m {
    private final AccessToken a;
    private final Set<String> b;
    private final Set<String> c;

    public m(AccessToken accessToken, Set<String> set, Set<String> set2) {
        this.a = accessToken;
        this.b = set;
        this.c = set2;
    }

    public final AccessToken a() {
        return this.a;
    }

    public final Set<String> b() {
        return this.b;
    }
}
