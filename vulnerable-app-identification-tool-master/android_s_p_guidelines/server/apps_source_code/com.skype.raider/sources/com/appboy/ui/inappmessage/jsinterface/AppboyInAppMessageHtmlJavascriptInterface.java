package com.appboy.ui.inappmessage.jsinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appboy.a;
import com.appboy.f.c;
import com.facebook.common.internal.VisibleForTesting;
import java.math.BigDecimal;
import org.json.JSONObject;

public class AppboyInAppMessageHtmlJavascriptInterface {
    private static final String TAG = c.a(AppboyInAppMessageHtmlJavascriptInterface.class);
    private Context mContext;
    private AppboyInAppMessageHtmlUserJavascriptInterface mUserInterface;

    public AppboyInAppMessageHtmlJavascriptInterface(Context context) {
        this.mContext = context;
        this.mUserInterface = new AppboyInAppMessageHtmlUserJavascriptInterface(context);
    }

    @JavascriptInterface
    public void requestImmediateDataFlush() {
        a.a(this.mContext).e();
    }

    @JavascriptInterface
    public void logCustomEventWithJSON(String eventName, String propertiesJSON) {
        a.a(this.mContext).a(eventName, parseProperties(propertiesJSON));
    }

    @JavascriptInterface
    public void logPurchaseWithJSON(String productId, double price, String currencyCode, int quantity, String propertiesJSON) {
        com.appboy.e.b.a appboyProperties = parseProperties(propertiesJSON);
        a.a(this.mContext).a(productId, currencyCode, new BigDecimal(Double.toString(price)), quantity, appboyProperties);
    }

    @JavascriptInterface
    public AppboyInAppMessageHtmlUserJavascriptInterface getUser() {
        return this.mUserInterface;
    }

    @VisibleForTesting
    com.appboy.e.b.a parseProperties(String propertiesJSON) {
        if (propertiesJSON != null) {
            try {
                if (!(propertiesJSON.equals("undefined") || propertiesJSON.equals("null"))) {
                    return new com.appboy.e.b.a(new JSONObject(propertiesJSON));
                }
            } catch (Exception e) {
                c.d(TAG, "Failed to parse properties JSON String: " + propertiesJSON, e);
            }
        }
        return null;
    }
}
