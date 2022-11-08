package com.skype4life.modules;

import android.preference.PreferenceManager;
import com.facebook.react.bridge.ag;
import com.facebook.react.c.a;
import com.facebook.react.c.b;
import com.facebook.react.flat.FlatARTSurfaceViewManager;
import com.facebook.react.flat.RCTImageViewManager;
import com.facebook.react.flat.RCTModalHostManager;
import com.facebook.react.flat.RCTRawTextManager;
import com.facebook.react.flat.RCTTextInlineImageManager;
import com.facebook.react.flat.RCTTextInputManager;
import com.facebook.react.flat.RCTTextManager;
import com.facebook.react.flat.RCTViewManager;
import com.facebook.react.flat.RCTViewPagerManager;
import com.facebook.react.flat.RCTVirtualTextManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.art.ARTRenderableViewManager;
import com.facebook.react.views.art.ARTSurfaceViewManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import com.facebook.react.views.webview.ReactWebViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class c extends b {
    public c(a config) {
        super(config);
    }

    public final List<ViewManager> b(ag reactContext) {
        List<ViewManager> viewManagers = new ArrayList();
        viewManagers.add(new ReactHorizontalScrollViewManager());
        viewManagers.add(new ReactImageManager());
        viewManagers.add(new ReactRawTextManager());
        viewManagers.add(new ReactScrollViewManager());
        viewManagers.add(new ReactTextInputManager());
        viewManagers.add(new ReactTextViewManager());
        viewManagers.add(new ReactViewManager());
        viewManagers.add(new ReactViewPagerManager());
        viewManagers.add(new ReactVirtualTextViewManager());
        viewManagers.add(new ReactWebViewManager());
        viewManagers.add(new ARTSurfaceViewManager());
        viewManagers.add(ARTRenderableViewManager.createARTShapeViewManager());
        viewManagers.add(ARTRenderableViewManager.createARTGroupViewManager());
        viewManagers.add(new ReactProgressBarViewManager());
        viewManagers.add(new FrescoBasedReactTextInlineImageViewManager());
        if (PreferenceManager.getDefaultSharedPreferences(reactContext).getBoolean("flat_uiimplementation", false)) {
            viewManagers.addAll(Arrays.asList(new ViewManager[]{new RCTViewManager(), new RCTTextManager(), new RCTRawTextManager(), new RCTVirtualTextManager(), new RCTTextInlineImageManager(), new RCTImageViewManager(), new RCTTextInputManager(), new RCTViewPagerManager(), new FlatARTSurfaceViewManager(), new RCTModalHostManager()}));
        }
        return viewManagers;
    }
}
