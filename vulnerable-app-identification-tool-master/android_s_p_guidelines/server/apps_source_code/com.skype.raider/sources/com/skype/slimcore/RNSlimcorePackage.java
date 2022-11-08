package com.skype.slimcore;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.skype.slimcore.logging.LogsProvider;
import com.skype.slimcore.skylib.SkyLibManager;
import com.skype.slimcore.skylib.SkyLibProvider;
import com.skype.slimcore.video.RNCallingVideoViewLocalManager;
import com.skype.slimcore.video.RNCallingVideoViewManager;
import com.skype.slimcore.video.RNCallingVideoViewPreviewManager;
import com.skype.slimcore.video.VideoViewManagerProvider;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RNSlimcorePackage implements q, SkyLibProvider, VideoViewManagerProvider {
    private RNCallingVideoViewManager a;
    private RNCallingVideoViewLocalManager b;
    private RNCallingVideoViewPreviewManager c;
    private RNSlimcoreModule d;
    private final LogsProvider e;
    private final LogsProvider f;

    public RNSlimcorePackage(LogsProvider skyLibLogsProvider, LogsProvider mediaLogsProvider) {
        this.e = skyLibLogsProvider;
        this.f = mediaLogsProvider;
    }

    public final List<NativeModule> a(ag reactContext) {
        this.d = new RNSlimcoreModule(reactContext, c(), this.e, this.f);
        return Collections.singletonList(this.d);
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        this.a = new RNCallingVideoViewManager(reactContext, b());
        this.b = new RNCallingVideoViewLocalManager(reactContext, b());
        this.c = new RNCallingVideoViewPreviewManager(reactContext, b());
        return Arrays.asList(new ViewManager[]{this.a, this.b, this.c});
    }

    public final WeakReference<SkyLibProvider> b() {
        return new WeakReference(this);
    }

    public final WeakReference<VideoViewManagerProvider> c() {
        return new WeakReference(this);
    }

    public final SkyLibManager d() {
        return this.d.skyLibManager();
    }

    public final RNCallingVideoViewManager e() {
        return this.a;
    }

    public final RNCallingVideoViewLocalManager f() {
        return this.b;
    }

    public final RNCallingVideoViewPreviewManager g() {
        return this.c;
    }
}
