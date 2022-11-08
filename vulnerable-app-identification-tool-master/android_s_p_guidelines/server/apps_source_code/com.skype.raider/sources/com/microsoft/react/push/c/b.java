package com.microsoft.react.push.c;

import android.content.Context;
import javax.annotation.Nullable;

public interface b {
    String getToken(Context context);

    void setToken(Context context, @Nullable String str);
}
