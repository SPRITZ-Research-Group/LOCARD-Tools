package com.microsoft.advertisement;

import android.content.Context;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RNAdvertisementUtilitiesPackage implements q {

    public class RNAdvertisementUtilitiesModule extends ReactContextBaseJavaModule {
        private static final String RN_CLASS = "RNAdvertisementUtilitiesModule";
        private Context appContext;

        public RNAdvertisementUtilitiesModule(ag reactContext) {
            super(reactContext);
            this.appContext = reactContext.getApplicationContext();
        }

        public String getName() {
            return "AdvertisementUtilities";
        }

        @ReactMethod
        public void getAdId(final ae promise) {
            new AsyncTask<Void, Void, String>(this) {
                final /* synthetic */ RNAdvertisementUtilitiesModule b;

                protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a();
                }

                protected final /* synthetic */ void onPostExecute(Object obj) {
                    obj = (String) obj;
                    if (obj != null) {
                        promise.a(obj);
                    } else {
                        promise.a("no_adid", "AdID not available");
                    }
                }

                private String a() {
                    try {
                        Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.b.appContext);
                        if (adInfo.isLimitAdTrackingEnabled()) {
                            return null;
                        }
                        return adInfo.getId();
                    } catch (Throwable e) {
                        FLog.e(RNAdvertisementUtilitiesModule.RN_CLASS, "getAdId()", e);
                        return null;
                    }
                }
            }.execute(new Void[0]);
        }
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new RNAdvertisementUtilitiesModule(reactContext));
        return modules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
