package com.adjust.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class Util {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z";
    public static final DecimalFormat SecondsDisplayFormat = newLocalDecimalFormat();
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    private static final String fieldReadErrorMessage = "Unable to read '%s' field in migration device with message (%s)";

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    protected static String createUuid() {
        return UUID.randomUUID().toString();
    }

    private static DecimalFormat newLocalDecimalFormat() {
        return new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
    }

    public static String quote(String string) {
        if (string == null) {
            return null;
        }
        if (!Pattern.compile("\\s").matcher(string).find()) {
            return string;
        }
        return formatString("'%s'", string);
    }

    public static String getPlayAdId(Context context) {
        return Reflection.getPlayAdId(context);
    }

    public static void runInBackground(Runnable command) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            command.run();
            return;
        }
        new AsyncTask<Object, Void, Void>() {
            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                ((Runnable) objArr[0]).run();
                return null;
            }
        }.execute(new Object[]{command});
    }

    public static void getGoogleAdId(Context context, final OnDeviceIdsRead onDeviceIdRead) {
        ILogger logger = AdjustFactory.getLogger();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            logger.debug("GoogleAdId being read in the background", new Object[0]);
            String GoogleAdId = getPlayAdId(context);
            logger.debug("GoogleAdId read " + GoogleAdId, new Object[0]);
            onDeviceIdRead.onGoogleAdIdRead(GoogleAdId);
            return;
        }
        logger.debug("GoogleAdId being read in the foreground", new Object[0]);
        new AsyncTask<Context, Void, String>() {
            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                Context[] contextArr = (Context[]) objArr;
                ILogger logger = AdjustFactory.getLogger();
                String playAdId = Util.getPlayAdId(contextArr[0]);
                logger.debug("GoogleAdId read " + playAdId, new Object[0]);
                return playAdId;
            }

            protected final /* synthetic */ void onPostExecute(Object obj) {
                String str = (String) obj;
                AdjustFactory.getLogger();
                onDeviceIdRead.onGoogleAdIdRead(str);
            }
        }.execute(new Context[]{context});
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        return Reflection.isPlayTrackingEnabled(context);
    }

    public static String getMacAddress(Context context) {
        return Reflection.getMacAddress(context);
    }

    public static Map<String, String> getPluginKeys(Context context) {
        return Reflection.getPluginKeys(context);
    }

    public static String getAndroidId(Context context) {
        return Reflection.getAndroidId(context);
    }

    public static String getTelephonyId(TelephonyManager telephonyManager) {
        return Reflection.getTelephonyId(telephonyManager);
    }

    public static String getIMEI(TelephonyManager telephonyManager) {
        return Reflection.getImei(telephonyManager);
    }

    public static String getMEID(TelephonyManager telephonyManager) {
        return Reflection.getMeid(telephonyManager);
    }

    public static String getIMEI(TelephonyManager telephonyManager, int index) {
        return Reflection.getImei(telephonyManager, index);
    }

    public static String getMEID(TelephonyManager telephonyManager, int index) {
        return Reflection.getMeid(telephonyManager, index);
    }

    public static String getTelephonyId(TelephonyManager telephonyManager, int index) {
        return Reflection.getTelephonyId(telephonyManager, index);
    }

    public static boolean tryAddToStringList(List<String> list, String value) {
        if (value == null || list.contains(value)) {
            return false;
        }
        return list.add(value);
    }

    public static String getTelephonyIds(TelephonyManager telephonyManager) {
        List<String> telephonyIdList = new ArrayList();
        tryAddToStringList(telephonyIdList, getTelephonyId(telephonyManager, 0));
        int i = 1;
        while (i < 10 && tryAddToStringList(telephonyIdList, getTelephonyId(telephonyManager, i))) {
            i++;
        }
        tryAddToStringList(telephonyIdList, getTelephonyId(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join(",", telephonyIdList);
    }

    public static String getIMEIs(TelephonyManager telephonyManager) {
        List<String> imeiList = new ArrayList();
        tryAddToStringList(imeiList, getIMEI(telephonyManager, 0));
        int i = 1;
        while (i < 10 && tryAddToStringList(imeiList, getIMEI(telephonyManager, i))) {
            i++;
        }
        tryAddToStringList(imeiList, getIMEI(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join(",", imeiList);
    }

    public static String getMEIDs(TelephonyManager telephonyManager) {
        List<String> meidList = new ArrayList();
        tryAddToStringList(meidList, getMEID(telephonyManager, 0));
        int i = 1;
        while (i < 10 && tryAddToStringList(meidList, getMEID(telephonyManager, i))) {
            i++;
        }
        tryAddToStringList(meidList, getMEID(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join(",", meidList);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T readObject(Context context, String filename, String objectName, Class<T> type) {
        Closeable closable = null;
        T t = null;
        try {
            FileInputStream inputStream = context.openFileInput(filename);
            FileInputStream closable2 = inputStream;
            BufferedInputStream bufferedStream = new BufferedInputStream(inputStream);
            closable = bufferedStream;
            ObjectInputStream objectStream = new ObjectInputStream(bufferedStream);
            closable = objectStream;
            t = type.cast(objectStream.readObject());
            getLogger().debug("Read %s: %s", objectName, t);
        } catch (ClassNotFoundException e) {
            getLogger().error("Failed to find %s class (%s)", objectName, e.getMessage());
        } catch (ClassCastException e2) {
            getLogger().error("Failed to cast %s object (%s)", objectName, e2.getMessage());
        } catch (Exception e3) {
            getLogger().error("Failed to read %s object (%s)", objectName, e3.getMessage());
        } catch (FileNotFoundException e4) {
            getLogger().debug("%s file not found", objectName);
        }
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e32) {
                getLogger().error("Failed to close %s file for reading (%s)", objectName, e32);
            }
        }
        return t;
    }

    public static <T> void writeObject(T object, Context context, String filename, String objectName) {
        Closeable closable = null;
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, 0);
            FileOutputStream closable2 = outputStream;
            BufferedOutputStream bufferedStream = new BufferedOutputStream(outputStream);
            closable = bufferedStream;
            ObjectOutputStream objectStream = new ObjectOutputStream(bufferedStream);
            closable = objectStream;
            try {
                objectStream.writeObject(object);
                getLogger().debug("Wrote %s: %s", objectName, object);
            } catch (NotSerializableException e) {
                getLogger().error("Failed to serialize %s", objectName);
            }
        } catch (Exception e2) {
            getLogger().error("Failed to open %s for writing (%s)", objectName, e2);
        }
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e22) {
                getLogger().error("Failed to close %s file for writing (%s)", objectName, e22);
            }
        }
    }

    public static boolean checkPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    public static String readStringField(GetField fields, String name, String defaultValue) {
        return (String) readObjectField(fields, name, defaultValue);
    }

    public static <T> T readObjectField(GetField fields, String name, T defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static boolean readBooleanField(GetField fields, String name, boolean defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static int readIntField(GetField fields, String name, int defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static long readLongField(GetField fields, String name, long defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static boolean equalObject(Object first, Object second) {
        if (first == null || second == null) {
            return first == null && second == null;
        } else {
            return first.equals(second);
        }
    }

    public static boolean equalsDouble(Double first, Double second) {
        if (first == null || second == null) {
            if (first == null && second == null) {
                return true;
            }
            return false;
        } else if (Double.doubleToLongBits(first.doubleValue()) != Double.doubleToLongBits(second.doubleValue())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean equalString(String first, String second) {
        return equalObject(first, second);
    }

    public static boolean equalEnum(Enum first, Enum second) {
        return equalObject(first, second);
    }

    public static boolean equalLong(Long first, Long second) {
        return equalObject(first, second);
    }

    public static boolean equalInt(Integer first, Integer second) {
        return equalObject(first, second);
    }

    public static boolean equalBoolean(Boolean first, Boolean second) {
        return equalObject(first, second);
    }

    public static int hashBoolean(Boolean value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashLong(Long value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashString(String value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashEnum(Enum value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashObject(Object value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static String sha1(String text) {
        return hash(text, Constants.SHA1);
    }

    public static String sha256(String text) {
        return hash(text, Constants.SHA256);
    }

    public static String md5(String text) {
        return hash(text, Constants.MD5);
    }

    public static String hash(String text, String method) {
        String hashString = null;
        try {
            byte[] bytes = text.getBytes(Constants.ENCODING);
            MessageDigest mesd = MessageDigest.getInstance(method);
            mesd.update(bytes, 0, bytes.length);
            return convertToHex(mesd.digest());
        } catch (Exception e) {
            return hashString;
        }
    }

    public static String convertToHex(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);
        return formatString("%0" + (bytes.length << 1) + "x", bigInt);
    }

    public static String[] getSupportedAbis() {
        return Reflection.getSupportedAbis();
    }

    public static String getCpuAbi() {
        return Reflection.getCpuAbi();
    }

    public static String getReasonString(String message, Throwable throwable) {
        if (throwable != null) {
            return formatString("%s: %s", message, throwable);
        }
        return formatString("%s", message);
    }

    public static long getWaitingTime(int retries, BackoffStrategy backoffStrategy) {
        if (retries < backoffStrategy.minRetries) {
            return 0;
        }
        long ceilingTime = Math.min(((long) Math.pow(2.0d, (double) (retries - backoffStrategy.minRetries))) * backoffStrategy.milliSecondMultiplier, backoffStrategy.maxWait);
        return (long) (((double) ceilingTime) * randomInRange(backoffStrategy.minRange, backoffStrategy.maxRange));
    }

    private static double randomInRange(double minRange, double maxRange) {
        return (new Random().nextDouble() * (maxRange - minRange)) + minRange;
    }

    public static boolean isValidParameter(String attribute, String attributeType, String parameterName) {
        if (attribute == null) {
            getLogger().error("%s parameter %s is missing", parameterName, attributeType);
            return false;
        } else if (!attribute.equals("")) {
            return true;
        } else {
            getLogger().error("%s parameter %s is empty", parameterName, attributeType);
            return false;
        }
    }

    public static Map<String, String> mergeParameters(Map<String, String> target, Map<String, String> source, String parameterName) {
        if (target == null) {
            return source;
        }
        if (source == null) {
            return target;
        }
        Map<String, String> mergedParameters = new HashMap(target);
        ILogger logger = getLogger();
        for (Entry<String, String> parameterSourceEntry : source.entrySet()) {
            if (((String) mergedParameters.put(parameterSourceEntry.getKey(), parameterSourceEntry.getValue())) != null) {
                logger.warn("Key %s with value %s from %s parameter was replaced by value %s", parameterSourceEntry.getKey(), (String) mergedParameters.put(parameterSourceEntry.getKey(), parameterSourceEntry.getValue()), parameterName, parameterSourceEntry.getValue());
            }
        }
        return mergedParameters;
    }

    public static String getVmInstructionSet() {
        return Reflection.getVmInstructionSet();
    }

    public static Locale getLocale(Configuration configuration) {
        Locale locale = Reflection.getLocaleFromLocaleList(configuration);
        return locale != null ? locale : Reflection.getLocaleFromField(configuration);
    }

    public static String getFireAdvertisingId(ContentResolver contentResolver) {
        String str = null;
        if (contentResolver == null) {
            return str;
        }
        try {
            return Secure.getString(contentResolver, "advertising_id");
        } catch (Exception e) {
            return str;
        }
    }

    public static Boolean getFireTrackingEnabled(ContentResolver contentResolver) {
        try {
            return Boolean.valueOf(Secure.getInt(contentResolver, "limit_ad_tracking") == 0);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getConnectivityType(Context context) {
        int connectivityType = -1;
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getType();
        } catch (Exception e) {
            getLogger().warn("Couldn't read connectivity type (%s)", e.getMessage());
            return connectivityType;
        }
    }

    public static int getNetworkType(Context context) {
        int networkType = -1;
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
            getLogger().warn("Couldn't read network type (%s)", e.getMessage());
            return networkType;
        }
    }

    public static String getMcc(Context context) {
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                return networkOperator.substring(0, 3);
            }
            AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MCC", new Object[0]);
            return null;
        } catch (Exception e) {
            AdjustFactory.getLogger().warn("Couldn't return mcc", new Object[0]);
            return null;
        }
    }

    public static String getMnc(Context context) {
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                return networkOperator.substring(3);
            }
            AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MNC", new Object[0]);
            return null;
        } catch (Exception e) {
            AdjustFactory.getLogger().warn("Couldn't return mnc", new Object[0]);
            return null;
        }
    }

    public static String formatString(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static boolean hasRootCause(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString().contains("Caused by:");
    }

    public static String getRootCause(Exception ex) {
        if (!hasRootCause(ex)) {
            return null;
        }
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String sStackTrace = sw.toString();
        int startOccuranceOfRootCause = sStackTrace.indexOf("Caused by:");
        return sStackTrace.substring(startOccuranceOfRootCause, sStackTrace.indexOf("\n", startOccuranceOfRootCause));
    }
}
