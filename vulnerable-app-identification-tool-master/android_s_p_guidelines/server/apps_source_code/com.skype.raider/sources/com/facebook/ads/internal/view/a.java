package com.facebook.ads.internal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.j.d;

public interface a {

    public interface a {
        void a(View view);

        void a(String str);

        void a(String str, d dVar);
    }

    void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity);

    void a(Bundle bundle);

    void e();

    void h();

    void i();

    void setListener(a aVar);
}
