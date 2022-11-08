package com.facebook.react.bridge;

import android.content.Context;

public abstract class ContextBaseJavaModule extends BaseJavaModule {
    private final Context mContext;

    public ContextBaseJavaModule(Context context) {
        this.mContext = context;
    }

    protected final Context getContext() {
        return this.mContext;
    }
}
