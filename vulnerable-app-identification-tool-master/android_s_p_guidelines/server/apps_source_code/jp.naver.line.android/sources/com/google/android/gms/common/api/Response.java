package com.google.android.gms.common.api;

public class Response<T extends Result> {
    private T zzap;

    protected Response(T t) {
        this.zzap = t;
    }

    protected T getResult() {
        return this.zzap;
    }

    public void setResult(T t) {
        this.zzap = t;
    }
}
