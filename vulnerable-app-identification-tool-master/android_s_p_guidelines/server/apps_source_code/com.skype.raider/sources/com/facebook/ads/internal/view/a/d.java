package com.facebook.ads.internal.view.a;

import android.text.TextUtils;

public final class d {
    private final f a;
    private boolean b = true;

    public d(f fVar) {
        this.a = fVar;
    }

    private static long a(String str, String str2) {
        Object substring = str.substring(str2.length());
        if (TextUtils.isEmpty(substring)) {
            return -1;
        }
        try {
            Long valueOf = Long.valueOf(Long.parseLong(substring));
            return valueOf.longValue() >= 0 ? valueOf.longValue() : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public final void a() {
        this.b = false;
    }

    public final void a(String str) {
        if (!this.b) {
            return;
        }
        if (str.startsWith("ANNavResponseEnd:")) {
            this.a.a(a(str, "ANNavResponseEnd:"));
        } else if (str.startsWith("ANNavDomContentLoaded:")) {
            this.a.b(a(str, "ANNavDomContentLoaded:"));
        } else if (str.startsWith("ANNavLoadEventEnd:")) {
            this.a.c(a(str, "ANNavLoadEventEnd:"));
        }
    }

    public final void b() {
        if (!this.b) {
            return;
        }
        if (this.a.canGoBack() || this.a.canGoForward()) {
            this.b = false;
            return;
        }
        f fVar = this.a;
        String str = "void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());";
        try {
            fVar.evaluateJavascript(str, null);
        } catch (IllegalStateException e) {
            fVar.loadUrl("javascript:" + str);
        }
    }
}
