package com.microsoft.environment;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class a {
    private static final String a = a.class.getSimpleName();
    private static String b = null;
    private static a c = null;
    private static final Map<String, String> d;

    private static class a {
        public String a;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    static {
        Map<String, String> aMap = new HashMap();
        aMap.put("xiaomi", "619");
        d = Collections.unmodifiableMap(aMap);
    }

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public static String a(Context context) {
        if (b != null) {
            return b;
        }
        CharSequence string = PreferenceManager.getDefaultSharedPreferences(context).getString("1.0_preinstall_tracking_id", null);
        b = string;
        if (!TextUtils.isEmpty(string)) {
            return b;
        }
        string = b(context);
        b = string;
        if (TextUtils.isEmpty(string)) {
            b = "0";
        }
        String str = b;
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString("1.0_preinstall_tracking_id", str);
        edit.apply();
        return b;
    }

    private static String b(Context context) {
        long startTS = System.currentTimeMillis();
        String trackingId;
        try {
            String string = context.getString(com.microsoft.environment.b.a.preinstall_stub_path1);
            String string2 = context.getString(com.microsoft.environment.b.a.preinstall_stub_path2);
            String[] strArr = new String[]{string, string2};
            for (int i = 0; i < 2; i++) {
                trackingId = b(context, strArr[i]);
                if (!TextUtils.isEmpty(trackingId)) {
                    break;
                }
            }
            trackingId = a(context, "skype_lite_stub");
            if (trackingId != null) {
                return trackingId;
            }
            a aVar;
            Object a = a(context.getString(com.microsoft.environment.b.a.preinstall_properties_file_path), context.getPackageName());
            if (TextUtils.isEmpty(a)) {
                aVar = null;
            } else {
                String[] split = a.split(",");
                if (split.length < 2) {
                    aVar = null;
                } else {
                    aVar = new a();
                    aVar.a = split[1].trim();
                }
            }
            if (aVar == null) {
                trackingId = null;
            } else {
                trackingId = aVar.a;
            }
            if (TextUtils.isEmpty(trackingId)) {
                if (!Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
                    trackingId = null;
                } else if (c(context)) {
                    trackingId = (String) d.get("xiaomi");
                } else {
                    trackingId = null;
                }
                if (TextUtils.isEmpty(trackingId)) {
                    trackingId = context.getSharedPreferences("preload_shared_pref", 0).getString("PartnerId", null);
                }
            }
            FLog.i(a, "getTrackingIdImp, time taken : " + String.valueOf(System.currentTimeMillis() - startTS) + " ms");
            return trackingId;
        } catch (Exception e) {
            trackingId = null;
        }
    }

    public static boolean a(String trackingId) {
        return trackingId != null && trackingId.equals("0");
    }

    private static String a(String filePath, String packageName) {
        Throwable th;
        String packageRow = null;
        File propertyFile = new File(filePath);
        if (!propertyFile.exists()) {
            return null;
        }
        FileInputStream is = null;
        InputStreamReader isR = null;
        BufferedReader reader = null;
        try {
            InputStreamReader isR2;
            BufferedReader reader2;
            FileInputStream is2 = new FileInputStream(propertyFile);
            try {
                isR2 = new InputStreamReader(is2, Constants.ENCODING);
                try {
                    reader2 = new BufferedReader(isR2);
                } catch (RuntimeException e) {
                    isR = isR2;
                    is = is2;
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (isR != null) {
                        try {
                            isR.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e4) {
                        }
                    }
                    return packageRow;
                } catch (Exception e5) {
                    isR = isR2;
                    is = is2;
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e6) {
                        }
                    }
                    if (isR != null) {
                        try {
                            isR.close();
                        } catch (IOException e7) {
                        }
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e8) {
                        }
                    }
                    return packageRow;
                } catch (Throwable th2) {
                    th = th2;
                    isR = isR2;
                    is = is2;
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e9) {
                        }
                    }
                    if (isR != null) {
                        try {
                            isR.close();
                        } catch (IOException e10) {
                        }
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e11) {
                        }
                    }
                    throw th;
                }
            } catch (RuntimeException e12) {
                is = is2;
                if (is != null) {
                    is.close();
                }
                if (isR != null) {
                    isR.close();
                }
                if (reader != null) {
                    reader.close();
                }
                return packageRow;
            } catch (Exception e13) {
                is = is2;
                if (is != null) {
                    is.close();
                }
                if (isR != null) {
                    isR.close();
                }
                if (reader != null) {
                    reader.close();
                }
                return packageRow;
            } catch (Throwable th3) {
                th = th3;
                is = is2;
                if (is != null) {
                    is.close();
                }
                if (isR != null) {
                    isR.close();
                }
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
            try {
                String line = reader2.readLine();
                while (line != null) {
                    line = reader2.readLine();
                    if (line != null && line.contains(packageName)) {
                        packageRow = line;
                        break;
                    }
                }
                try {
                    is2.close();
                } catch (IOException e14) {
                }
                try {
                    isR2.close();
                } catch (IOException e15) {
                }
                try {
                    reader2.close();
                    reader = reader2;
                    isR = isR2;
                    is = is2;
                } catch (IOException e16) {
                    reader = reader2;
                    isR = isR2;
                    is = is2;
                }
            } catch (RuntimeException e17) {
                reader = reader2;
                isR = isR2;
                is = is2;
                if (is != null) {
                    is.close();
                }
                if (isR != null) {
                    isR.close();
                }
                if (reader != null) {
                    reader.close();
                }
                return packageRow;
            } catch (Exception e18) {
                reader = reader2;
                isR = isR2;
                is = is2;
                if (is != null) {
                    is.close();
                }
                if (isR != null) {
                    isR.close();
                }
                if (reader != null) {
                    reader.close();
                }
                return packageRow;
            } catch (Throwable th4) {
                th = th4;
                reader = reader2;
                isR = isR2;
                is = is2;
                if (is != null) {
                    is.close();
                }
                if (isR != null) {
                    isR.close();
                }
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (RuntimeException e19) {
            if (is != null) {
                is.close();
            }
            if (isR != null) {
                isR.close();
            }
            if (reader != null) {
                reader.close();
            }
            return packageRow;
        } catch (Exception e20) {
            if (is != null) {
                is.close();
            }
            if (isR != null) {
                isR.close();
            }
            if (reader != null) {
                reader.close();
            }
            return packageRow;
        } catch (Throwable th5) {
            th = th5;
            if (is != null) {
                is.close();
            }
            if (isR != null) {
                isR.close();
            }
            if (reader != null) {
                reader.close();
            }
            throw th;
        }
        return packageRow;
    }

    private static String a(Context context, final String prefix) {
        String str = null;
        try {
            String[] fileNames = new File(context.getString(com.microsoft.environment.b.a.preinstall_sys_app_dir_path)).list(new FilenameFilter() {
                public final boolean accept(File dir, String filename) {
                    return filename.endsWith(".apk") && filename.startsWith(prefix);
                }
            });
            if (fileNames == null || fileNames.length <= 0) {
                return null;
            }
            String str2 = fileNames[0];
            if (str2 == null) {
                return null;
            }
            Object obj;
            if (str2 == null) {
                obj = null;
            } else {
                obj = str2.replace(prefix, "").replace("-", "").replace(".apk", "");
            }
            if (TextUtils.isEmpty(obj)) {
                return null;
            }
            String[] split = obj.split("\\.");
            if (split.length == 4 && Integer.parseInt(split[3]) != 0) {
                str = split[3];
            }
            return str;
        } catch (RuntimeException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String b(Context context, String apkFilePath) {
        try {
            if (!new File(apkFilePath).exists()) {
                return null;
            }
            PackageInfo pi = context.getPackageManager().getPackageArchiveInfo(apkFilePath, 0);
            if (pi == null || pi.packageName == null || !pi.packageName.equals(context.getPackageName())) {
                return null;
            }
            Object obj = pi.versionName;
            if (TextUtils.isEmpty(obj)) {
                return null;
            }
            String[] split = obj.split("[\\.\\-]");
            if (split.length > 2) {
                return split[2];
            }
            return null;
        } catch (RuntimeException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private static boolean c(Context context) {
        try {
            return ((Boolean) Class.forName("miui.os.MiuiInit").getMethod("isPreinstalledPackage", new Class[]{String.class}).invoke(null, new Object[]{context.getPackageName()})).booleanValue();
        } catch (RuntimeException e) {
        } catch (Exception e2) {
        }
        return false;
    }
}
