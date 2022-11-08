package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.obf.ly;

@KeepForSdk
@Deprecated
public final class GoogleServices {
    private static final Object sLock = new Object();
    private static GoogleServices zzay;
    private final String zzaz;
    private final Status zzba;
    private final boolean zzbb;
    private final boolean zzbc;

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        boolean z = true;
        if (identifier != 0) {
            if (resources.getInteger(identifier) == 0) {
                z = false;
            }
            this.zzbc = z ^ 1;
        } else {
            this.zzbc = false;
        }
        this.zzbb = z;
        Object zzc = zzp.zzc(context);
        if (zzc == null) {
            zzc = new StringResourceValueReader(context).getString("google_app_id");
        }
        if (TextUtils.isEmpty(zzc)) {
            this.zzba = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzaz = null;
            return;
        }
        this.zzaz = zzc;
        this.zzba = Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(String str, boolean z) {
        this.zzaz = str;
        this.zzba = Status.RESULT_SUCCESS;
        this.zzbb = z;
        this.zzbc = z ^ 1;
    }

    @KeepForSdk
    public static Status initialize(Context context, String str, boolean z) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (sLock) {
            Status checkGoogleAppId;
            if (zzay != null) {
                checkGoogleAppId = zzay.checkGoogleAppId(str);
                return checkGoogleAppId;
            }
            GoogleServices googleServices = new GoogleServices(str, z);
            zzay = googleServices;
            checkGoogleAppId = googleServices.zzba;
            return checkGoogleAppId;
        }
    }

    @KeepForSdk
    @VisibleForTesting
    final Status checkGoogleAppId(String str) {
        if (this.zzaz == null || this.zzaz.equals(str)) {
            return Status.RESULT_SUCCESS;
        }
        String str2 = this.zzaz;
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 97);
        stringBuilder.append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '");
        stringBuilder.append(str2);
        stringBuilder.append("'.");
        return new Status(10, stringBuilder.toString());
    }

    @KeepForSdk
    public static Status initialize(Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (sLock) {
            if (zzay == null) {
                zzay = new GoogleServices(context);
            }
            status = zzay.zzba;
        }
        return status;
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzaz;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices checkInitialized = checkInitialized("isMeasurementEnabled");
        return checkInitialized.zzba.isSuccess() && checkInitialized.zzbb;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbc;
    }

    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        synchronized (sLock) {
            zzay = null;
        }
    }

    @KeepForSdk
    private static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (sLock) {
            if (zzay != null) {
                googleServices = zzay;
            } else {
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 34);
                stringBuilder.append("Initialize must be called before ");
                stringBuilder.append(str);
                stringBuilder.append(ly.a);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        return googleServices;
    }
}
