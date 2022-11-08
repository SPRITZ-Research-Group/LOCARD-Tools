package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Set;

public final class eg {
    private static SharedPreferences b;
    private final AppLovinSdkImpl a;
    private final SharedPreferences c;

    eg(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        StringBuilder stringBuilder = new StringBuilder("com.applovin.sdk.preferences.");
        stringBuilder.append(appLovinSdkImpl.getSdkKey());
        this.c = appLovinSdkImpl.getApplicationContext().getSharedPreferences(stringBuilder.toString(), 0);
    }

    private static SharedPreferences a(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("com.applovin.sdk.shared", 0);
        }
        return b;
    }

    private static <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences, AppLovinSdkImpl appLovinSdkImpl) {
        try {
            if (sharedPreferences.contains(str)) {
                Object valueOf;
                if (Boolean.class.equals(cls)) {
                    valueOf = Boolean.valueOf(t != null ? sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue()) : sharedPreferences.getBoolean(str, false));
                } else if (Float.class.equals(cls)) {
                    valueOf = Float.valueOf(t != null ? sharedPreferences.getFloat(str, ((Float) t).floatValue()) : sharedPreferences.getFloat(str, BitmapDescriptorFactory.HUE_RED));
                } else if (Integer.class.equals(cls)) {
                    valueOf = Integer.valueOf(t != null ? sharedPreferences.getInt(str, ((Integer) t).intValue()) : sharedPreferences.getInt(str, 0));
                } else if (Long.class.equals(cls)) {
                    valueOf = Long.valueOf(t != null ? sharedPreferences.getLong(str, ((Long) t).longValue()) : sharedPreferences.getLong(str, 0));
                } else if (String.class.equals(cls)) {
                    valueOf = sharedPreferences.getString(str, (String) t);
                } else {
                    if (Set.class.isAssignableFrom(cls)) {
                        if (ab.b()) {
                            valueOf = sharedPreferences.getStringSet(str, (Set) t);
                        } else if (appLovinSdkImpl != null) {
                            appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Attempting to get string set on older Android version for key: ".concat(String.valueOf(str)));
                        }
                    }
                    valueOf = t;
                }
                if (valueOf != null) {
                    return cls.cast(valueOf);
                }
            }
            return t;
        } catch (Throwable th) {
            if (appLovinSdkImpl != null) {
                appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Error getting value for key: ".concat(String.valueOf(str)), th);
            }
            return t;
        }
    }

    private static void a(Editor editor, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null || !((Boolean) appLovinSdkImpl.get(ea.dg)).booleanValue()) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    static <T> void a(ef<T> efVar, T t, Context context) {
        a(efVar.a(), (Object) t, a(context), null);
    }

    private static <T> void a(String str, T t, SharedPreferences sharedPreferences, AppLovinSdkImpl appLovinSdkImpl) {
        Editor edit = sharedPreferences.edit();
        Object obj = null;
        if (t instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            edit.putFloat(str, ((Float) t).floatValue());
        } else if (t instanceof Integer) {
            edit.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof Long) {
            edit.putLong(str, ((Long) t).longValue());
        } else if (t instanceof String) {
            edit.putString(str, (String) t);
        } else {
            if (t instanceof Set) {
                if (ab.b()) {
                    edit.putStringSet(str, (Set) t);
                } else if (appLovinSdkImpl != null) {
                    StringBuilder stringBuilder = new StringBuilder("Attempting to put string set on older Android version for key: ");
                    stringBuilder.append(str);
                    stringBuilder.append(" and value: ");
                    stringBuilder.append(t);
                    appLovinSdkImpl.getLogger().e("SharedPreferencesManager", stringBuilder.toString());
                }
            } else if (appLovinSdkImpl != null) {
                appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Unable to put default value of invalid type: ".concat(String.valueOf(t)));
            }
            if (obj != null) {
                a(edit, appLovinSdkImpl);
            }
        }
        obj = 1;
        if (obj != null) {
            a(edit, appLovinSdkImpl);
        }
    }

    static <T> T b(ef<T> efVar, T t, Context context) {
        return a(efVar.a(), t, efVar.b(), a(context), null);
    }

    final <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences) {
        return a(str, t, cls, sharedPreferences, this.a);
    }

    final void a(SharedPreferences sharedPreferences) {
        a(sharedPreferences.edit().clear(), this.a);
    }

    final <T> void a(ef<T> efVar) {
        a(this.c.edit().remove(efVar.a()), this.a);
    }

    final <T> void a(ef<T> efVar, T t) {
        a((ef) efVar, (Object) t, this.c);
    }

    final <T> void a(ef<T> efVar, T t, SharedPreferences sharedPreferences) {
        a(efVar.a(), (Object) t, sharedPreferences);
    }

    final <T> void a(String str, T t, SharedPreferences sharedPreferences) {
        a(str, (Object) t, sharedPreferences, this.a);
    }

    final <T> T b(ef<T> efVar, T t) {
        return b((ef) efVar, (Object) t, this.c);
    }

    final <T> T b(ef<T> efVar, T t, SharedPreferences sharedPreferences) {
        return a(efVar.a(), (Object) t, efVar.b(), sharedPreferences);
    }
}
