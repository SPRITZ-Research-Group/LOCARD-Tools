package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

public class ClickTrackingOverlayView extends RelativeLayout {
    public ClickTrackingOverlayView(Context context, AppLovinSdk appLovinSdk) {
        super(context, null, new ee(appLovinSdk).C());
        a(context, appLovinSdk);
    }

    private void a(Context context, AppLovinSdk appLovinSdk) {
        LayoutParams layoutParams;
        ee eeVar = new ee(appLovinSdk);
        View progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        int B = eeVar.B();
        if (B == -2 || B == -1) {
            layoutParams = new RelativeLayout.LayoutParams(B, B);
        } else {
            int dpToPx = AppLovinSdkUtils.dpToPx(context, B);
            layoutParams = new RelativeLayout.LayoutParams(dpToPx, dpToPx);
        }
        layoutParams.addRule(13);
        progressBar.setLayoutParams(layoutParams);
        setBackgroundColor(Color.parseColor(eeVar.A()));
        addView(progressBar);
    }
}
