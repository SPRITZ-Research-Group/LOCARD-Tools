package com.skype.react.activationExperiment;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.am;
import com.google.gson.e;
import com.google.gson.j;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    private static e a = new e();

    private Utils() {
    }

    static String a(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a", Locale.US).format(calendar.getTime());
    }

    static String a() {
        return new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a", Locale.US).format(Calendar.getInstance().getTime());
    }

    public static Double a(am data, String key, Double defaultValue) {
        double d;
        if (data != null) {
            try {
                if (data.hasKey(key)) {
                    d = data.getDouble(key);
                    return Double.valueOf(d);
                }
            } catch (Exception e) {
                return defaultValue;
            }
        }
        d = defaultValue.doubleValue();
        return Double.valueOf(d);
    }

    public static String a(am data, String key, String defaultValue) {
        if (data != null) {
            try {
                if (data.hasKey(key)) {
                    return data.getString(key);
                }
            } catch (Exception e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static boolean a(am data, String key) {
        if (data != null) {
            try {
                if (data.hasKey(key)) {
                    return data.getBoolean(key);
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public static <T> T a(String msgJson, Class<T> classOfT) {
        T responseObject = null;
        try {
            return a.a(msgJson, (Class) classOfT);
        } catch (Throwable exception) {
            FLog.w("activationExperiment.Utils", "fromJson: ", exception);
            return null;
        } catch (Throwable throwable) {
            FLog.e("activationExperiment.Utils", "fromJson: ", throwable);
            return responseObject;
        }
    }

    public static String a(Object object, Type typeOfSrc) {
        String jsonStr = null;
        try {
            return new e().a(object, typeOfSrc);
        } catch (j e) {
            return null;
        } catch (Throwable throwable) {
            FLog.e("activationExperiment.Utils", "toJson: ", throwable);
            return jsonStr;
        }
    }

    public static int a(Context context, String drawableName) {
        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }

    public static int b(Context context, String stringName) {
        return context.getResources().getIdentifier(stringName, "string", context.getPackageName());
    }

    public static int c(Context context, String colorName) {
        return context.getResources().getIdentifier(colorName, "color", context.getPackageName());
    }

    public static Date a(String date, String dateFormat) {
        Date date2 = null;
        if (date == null) {
            return date2;
        }
        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            return date2;
        }
    }
}
