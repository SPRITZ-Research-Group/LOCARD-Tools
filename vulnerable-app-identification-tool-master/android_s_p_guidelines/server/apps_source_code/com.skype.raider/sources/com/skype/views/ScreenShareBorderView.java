package com.skype.views;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.skype.slimcore.R;

public class ScreenShareBorderView extends View {
    private Context a;
    private FrameLayout b;
    private WindowManager c;

    public ScreenShareBorderView(@NonNull Context context) {
        super(context);
        this.a = context;
        this.b = new FrameLayout(context);
        FLog.d("ScreenShareBorderView", "attachView");
        LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, VERSION.SDK_INT >= 26 ? 2038 : 2002, 1048, -3);
        layoutParams.gravity = 17;
        layoutParams.systemUiVisibility = 1024;
        this.c = (WindowManager) this.a.getSystemService("window");
        this.c.addView(this.b, layoutParams);
        FLog.d("ScreenShareBorderView", "inflating screen_share_notification_layout");
        ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.screen_share_notification_layout, this.b);
    }

    public final void a() {
        FLog.d("ScreenShareBorderView", "removing view");
        this.c.removeView(this.b);
        this.a = null;
    }
}
