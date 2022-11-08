package com.appboy.ui.inappmessage.jsinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appboy.a;
import com.appboy.b.e;
import com.appboy.b.f;
import com.appboy.b.g;
import com.appboy.d;
import com.appboy.f.c;
import com.facebook.common.internal.VisibleForTesting;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppboyInAppMessageHtmlUserJavascriptInterface {
    public static final String JS_BRIDGE_GENDER_FEMALE = e.FEMALE.a();
    public static final String JS_BRIDGE_GENDER_MALE = e.MALE.a();
    public static final String JS_BRIDGE_GENDER_NOT_APPLICABLE = e.NOT_APPLICABLE.a();
    public static final String JS_BRIDGE_GENDER_OTHER = e.OTHER.a();
    public static final String JS_BRIDGE_GENDER_PREFER_NOT_TO_SAY = e.PREFER_NOT_TO_SAY.a();
    public static final String JS_BRIDGE_GENDER_UNKNOWN = e.UNKNOWN.a();
    private static final String TAG = c.a(AppboyInAppMessageHtmlUserJavascriptInterface.class);
    private Context mContext;

    public AppboyInAppMessageHtmlUserJavascriptInterface(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void setFirstName(String firstName) {
        a.a(this.mContext).f().a(firstName);
    }

    @JavascriptInterface
    public void setLastName(String lastName) {
        a.a(this.mContext).f().b(lastName);
    }

    @JavascriptInterface
    public void setEmail(String email) {
        a.a(this.mContext).f().c(email);
    }

    @JavascriptInterface
    public void setGender(String genderString) {
        e gender = parseGender(genderString);
        if (gender == null) {
            c.g(TAG, "Failed to parse gender in Braze HTML in-app message javascript interface with gender: " + genderString);
        } else {
            a.a(this.mContext).f().a(gender);
        }
    }

    @VisibleForTesting
    e parseGender(String genderString) {
        if (genderString == null) {
            return null;
        }
        String genderStringLowerCase = genderString.toLowerCase(Locale.US);
        if (genderStringLowerCase.equals(JS_BRIDGE_GENDER_MALE)) {
            return e.MALE;
        }
        if (genderStringLowerCase.equals(JS_BRIDGE_GENDER_FEMALE)) {
            return e.FEMALE;
        }
        if (genderStringLowerCase.equals(JS_BRIDGE_GENDER_OTHER)) {
            return e.OTHER;
        }
        if (genderStringLowerCase.equals(JS_BRIDGE_GENDER_UNKNOWN)) {
            return e.UNKNOWN;
        }
        if (genderStringLowerCase.equals(JS_BRIDGE_GENDER_NOT_APPLICABLE)) {
            return e.NOT_APPLICABLE;
        }
        if (genderStringLowerCase.equals(JS_BRIDGE_GENDER_PREFER_NOT_TO_SAY)) {
            return e.PREFER_NOT_TO_SAY;
        }
        return null;
    }

    @JavascriptInterface
    public void setDateOfBirth(int year, int monthInt, int day) {
        f month = monthFromInt(monthInt);
        if (month == null) {
            c.g(TAG, "Failed to parse month for value " + monthInt);
        } else {
            a.a(this.mContext).f().a(year, month, day);
        }
    }

    @VisibleForTesting
    f monthFromInt(int monthInt) {
        if (monthInt <= 0 || monthInt > 12) {
            return null;
        }
        return f.a(monthInt - 1);
    }

    @JavascriptInterface
    public void setCountry(String country) {
        a.a(this.mContext).f().d(country);
    }

    @JavascriptInterface
    public void setLanguage(String language) {
        a.a(this.mContext).f().f(language);
    }

    @JavascriptInterface
    public void setHomeCity(String homeCity) {
        a.a(this.mContext).f().e(homeCity);
    }

    @JavascriptInterface
    public void setEmailNotificationSubscriptionType(String subscriptionType) {
        g subscriptionTypeEnum = subscriptionTypeFromJavascriptString(subscriptionType);
        if (subscriptionTypeEnum == null) {
            c.g(TAG, "Failed to parse email subscription type in Braze HTML in-app message javascript interface with subscription " + subscriptionType);
        } else {
            a.a(this.mContext).f().a(subscriptionTypeEnum);
        }
    }

    @JavascriptInterface
    public void setPushNotificationSubscriptionType(String subscriptionType) {
        g subscriptionTypeEnum = subscriptionTypeFromJavascriptString(subscriptionType);
        if (subscriptionTypeEnum == null) {
            c.g(TAG, "Failed to parse push subscription type in Braze HTML in-app message javascript interface with subscription: " + subscriptionType);
        } else {
            a.a(this.mContext).f().b(subscriptionTypeEnum);
        }
    }

    @VisibleForTesting
    g subscriptionTypeFromJavascriptString(String subscriptionType) {
        String subscriptionTypeLowerCase = subscriptionType.toLowerCase(Locale.US);
        if (subscriptionTypeLowerCase.equals("subscribed")) {
            return g.SUBSCRIBED;
        }
        if (subscriptionTypeLowerCase.equals("unsubscribed")) {
            return g.UNSUBSCRIBED;
        }
        if (subscriptionTypeLowerCase.equals("opted_in")) {
            return g.OPTED_IN;
        }
        return null;
    }

    @JavascriptInterface
    public void setPhoneNumber(String phoneNumber) {
        a.a(this.mContext).f().g(phoneNumber);
    }

    @JavascriptInterface
    public void setCustomUserAttributeJSON(String key, String jsonStringValue) {
        setCustomAttribute(a.a(this.mContext).f(), key, jsonStringValue);
    }

    @VisibleForTesting
    void setCustomAttribute(d user, String key, String jsonStringValue) {
        try {
            Object valueObject = new JSONObject(jsonStringValue).get(PropertiesEntry.COLUMN_NAME_VALUE);
            if (valueObject instanceof String) {
                user.a(key, (String) valueObject);
            } else if (valueObject instanceof Boolean) {
                user.a(key, ((Boolean) valueObject).booleanValue());
            } else if (valueObject instanceof Integer) {
                user.a(key, ((Integer) valueObject).intValue());
            } else if (valueObject instanceof Double) {
                user.a(key, ((Double) valueObject).floatValue());
            } else {
                c.g(TAG, "Failed to parse custom attribute type for key: " + key);
            }
        } catch (Exception e) {
            c.d(TAG, "Failed to parse custom attribute type for key: " + key, e);
        }
    }

    @JavascriptInterface
    public void setCustomUserAttributeArray(String key, String jsonArrayString) {
        String[] arrayValue = parseStringArrayFromJsonString(jsonArrayString);
        if (arrayValue == null) {
            c.g(TAG, "Failed to set custom attribute array for key " + key);
        } else {
            a.a(this.mContext).f().a(key, arrayValue);
        }
    }

    @VisibleForTesting
    String[] parseStringArrayFromJsonString(String jsonArrayString) {
        try {
            JSONArray parsedArray = new JSONArray(jsonArrayString);
            List<String> list = new ArrayList();
            for (int i = 0; i < parsedArray.length(); i++) {
                list.add(parsedArray.getString(i));
            }
            return (String[]) list.toArray(new String[0]);
        } catch (Exception e) {
            c.d(TAG, "Failed to parse custom attribute array", e);
            return null;
        }
    }

    @JavascriptInterface
    public void addToCustomAttributeArray(String key, String value) {
        a.a(this.mContext).f().b(key, value);
    }

    @JavascriptInterface
    public void removeFromCustomAttributeArray(String key, String value) {
        a.a(this.mContext).f().c(key, value);
    }

    @JavascriptInterface
    public void incrementCustomUserAttribute(String attribute) {
        a.a(this.mContext).f().h(attribute);
    }
}
