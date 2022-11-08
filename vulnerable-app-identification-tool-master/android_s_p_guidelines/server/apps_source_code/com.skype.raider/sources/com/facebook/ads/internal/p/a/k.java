package com.facebook.ads.internal.p.a;

public final class k extends l {
    public k(String str, p pVar) {
        super(str);
        this.b = j.POST;
        this.a = str;
        this.c = "application/x-www-form-urlencoded;charset=UTF-8";
        if (pVar != null) {
            this.d = pVar.a();
        }
    }
}
