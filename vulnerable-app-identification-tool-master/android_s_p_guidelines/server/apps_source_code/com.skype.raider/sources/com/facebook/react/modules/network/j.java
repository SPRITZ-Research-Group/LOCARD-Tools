package com.facebook.react.modules.network;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public final class j implements a {
    @Nullable
    private CookieJar a = null;

    public final void a(CookieJar cookieJar) {
        this.a = cookieJar;
    }

    public final void a() {
        this.a = null;
    }

    public final void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (this.a != null) {
            this.a.saveFromResponse(url, cookies);
        }
    }

    public final List<Cookie> loadForRequest(HttpUrl url) {
        if (this.a != null) {
            return this.a.loadForRequest(url);
        }
        return Collections.emptyList();
    }
}
