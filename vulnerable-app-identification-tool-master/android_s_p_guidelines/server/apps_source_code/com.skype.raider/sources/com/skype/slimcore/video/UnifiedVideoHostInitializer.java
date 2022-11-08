package com.skype.slimcore.video;

import android.content.Context;
import com.skype.android.video.Platform;
import com.skype.slimcore.logging.MethodTrace;
import com.skype.slimcore.skylib.VideoHostInitializer;

public class UnifiedVideoHostInitializer implements VideoHostInitializer {
    public final void a(Context context) {
        MethodTrace trace = new MethodTrace("UnifiedVideoHostInitializer", "postInit");
        Platform.initialize(context);
        trace.a();
    }
}
