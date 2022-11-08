package com.google.firebase.analytics.connector;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;

public interface a {
    @WorkerThread
    @KeepForSdk
    void a(@NonNull String str, @NonNull String str2, Bundle bundle);

    @KeepForSdk
    void a(@NonNull String str, @NonNull String str2, Object obj);
}
