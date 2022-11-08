package com.facebook.react.c;

import android.preference.PreferenceManager;
import com.facebook.imagepipeline.core.h;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.y;
import com.facebook.react.e;
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
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.location.LocationModule;
import com.facebook.react.modules.netinfo.NetInfoModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.storage.AsyncStorageModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.art.ARTRenderableViewManager;
import com.facebook.react.views.art.ARTSurfaceViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.picker.ReactDialogPickerManager;
import com.facebook.react.views.picker.ReactDropdownPickerManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.slider.ReactSliderManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.toolbar.ReactToolbarManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import com.facebook.react.views.webview.ReactWebViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.a;

public class b extends e {
    private a a;

    public b(a config) {
        this.a = config;
    }

    public final List<y> c(final ag context) {
        return Arrays.asList(new y[]{new y(AccessibilityInfoModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new AccessibilityInfoModule(context);
            }
        }), new y(AppStateModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new AppStateModule(context);
            }
        }), new y(AsyncStorageModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new AsyncStorageModule(context);
            }
        }), new y(CameraRollManager.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new CameraRollManager(context);
            }
        }), new y(ClipboardModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new ClipboardModule(context);
            }
        }), new y(DatePickerDialogModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new DatePickerDialogModule(context);
            }
        }), new y(DialogModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new DialogModule(context);
            }
        }), new y(FrescoModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                h a;
                ag agVar = context;
                if (this.b.a != null) {
                    a = this.b.a.a();
                } else {
                    a = null;
                }
                return new FrescoModule(agVar, true, a);
            }
        }), new y(I18nManagerModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new I18nManagerModule(context);
            }
        }), new y(ImageEditingManager.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new ImageEditingManager(context);
            }
        }), new y(ImageLoaderModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new ImageLoaderModule(context);
            }
        }), new y(ImageStoreManager.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new ImageStoreManager(context);
            }
        }), new y(IntentModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new IntentModule(context);
            }
        }), new y(LocationModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new LocationModule(context);
            }
        }), new y(NativeAnimatedModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new NativeAnimatedModule(context);
            }
        }), new y(NetworkingModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new NetworkingModule(context);
            }
        }), new y(NetInfoModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new NetInfoModule(context);
            }
        }), new y(PermissionsModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new PermissionsModule(context);
            }
        }), new y(ShareModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new ShareModule(context);
            }
        }), new y(StatusBarModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new StatusBarModule(context);
            }
        }), new y(TimePickerDialogModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new TimePickerDialogModule(context);
            }
        }), new y(ToastModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new ToastModule(context);
            }
        }), new y(VibrationModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new VibrationModule(context);
            }
        }), new y(WebSocketModule.class, new a<NativeModule>(this) {
            final /* synthetic */ b b;

            public final /* synthetic */ Object a() {
                return new WebSocketModule(context);
            }
        })});
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public List<ViewManager> b(ag reactContext) {
        List<ViewManager> viewManagers = new ArrayList();
        viewManagers.add(ARTRenderableViewManager.createARTGroupViewManager());
        viewManagers.add(ARTRenderableViewManager.createARTShapeViewManager());
        viewManagers.add(ARTRenderableViewManager.createARTTextViewManager());
        viewManagers.add(new ReactDialogPickerManager());
        viewManagers.add(new ReactDrawerLayoutManager());
        viewManagers.add(new ReactDropdownPickerManager());
        viewManagers.add(new ReactHorizontalScrollViewManager());
        viewManagers.add(new ReactProgressBarViewManager());
        viewManagers.add(new ReactScrollViewManager());
        viewManagers.add(new ReactSliderManager());
        viewManagers.add(new ReactSwitchManager());
        viewManagers.add(new ReactToolbarManager());
        viewManagers.add(new ReactWebViewManager());
        viewManagers.add(new SwipeRefreshLayoutManager());
        if (PreferenceManager.getDefaultSharedPreferences(reactContext).getBoolean("flat_uiimplementation", false)) {
            viewManagers.add(new FlatARTSurfaceViewManager());
            viewManagers.add(new RCTTextInlineImageManager());
            viewManagers.add(new RCTImageViewManager());
            viewManagers.add(new RCTModalHostManager());
            viewManagers.add(new RCTRawTextManager());
            viewManagers.add(new RCTTextInputManager());
            viewManagers.add(new RCTTextManager());
            viewManagers.add(new RCTViewManager());
            viewManagers.add(new RCTViewPagerManager());
            viewManagers.add(new RCTVirtualTextManager());
        } else {
            viewManagers.add(new ARTSurfaceViewManager());
            viewManagers.add(new FrescoBasedReactTextInlineImageViewManager());
            viewManagers.add(new ReactImageManager());
            viewManagers.add(new ReactModalHostManager());
            viewManagers.add(new ReactRawTextManager());
            viewManagers.add(new ReactTextInputManager());
            viewManagers.add(new ReactTextViewManager());
            viewManagers.add(new ReactViewManager());
            viewManagers.add(new ReactViewPagerManager());
            viewManagers.add(new ReactVirtualTextViewManager());
        }
        return viewManagers;
    }

    public final com.facebook.react.module.a.b b() {
        return e.a((e) this);
    }
}
