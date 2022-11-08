package com.microsoft.react.push.gcm;

import com.facebook.common.logging.FLog;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIDService extends FirebaseInstanceIdService {
    private static final String b = InstanceIDService.class.getSimpleName();

    public final void a() {
        super.a();
        FLog.i(b, "onTokenRefresh");
        RegistrationService.a(getApplicationContext());
    }
}
