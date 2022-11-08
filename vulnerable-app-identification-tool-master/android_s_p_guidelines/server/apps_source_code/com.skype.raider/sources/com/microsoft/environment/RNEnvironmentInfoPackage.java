package com.microsoft.environment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.google.firebase.iid.FirebaseInstanceId;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class RNEnvironmentInfoPackage implements q {
    private long a;

    public class RNEnvironmentInfoModule extends ReactContextBaseJavaModule {
        private String externalCacheDir;
        private String instanceId;
        private String packageName;
        private String preInstallPartnerId;
        private TelephonyManager telephonyManager;
        private String versionName;

        public RNEnvironmentInfoModule(ag reactContext) {
            File cacheDirPath;
            super(reactContext);
            try {
                PackageInfo info = reactContext.getPackageManager().getPackageInfo(reactContext.getPackageName(), 0);
                this.versionName = info.versionName;
                this.packageName = info.packageName;
            } catch (NameNotFoundException e) {
                this.versionName = "0.0.84.1";
            }
            if (Environment.getExternalStorageState() == "mounted") {
                cacheDirPath = reactContext.getExternalCacheDir();
            } else {
                cacheDirPath = reactContext.getCacheDir();
            }
            if (cacheDirPath != null) {
                this.externalCacheDir = cacheDirPath.getAbsolutePath();
            }
            this.instanceId = FirebaseInstanceId.getInstance().getId();
            a.a();
            this.preInstallPartnerId = a.a(reactContext.getApplicationContext());
            if (a.a(this.preInstallPartnerId)) {
                this.preInstallPartnerId = null;
            }
            this.telephonyManager = (TelephonyManager) reactContext.getApplicationContext().getSystemService("phone");
        }

        public String getName() {
            return "EnvironmentInfo";
        }

        public Map<String, Object> getConstants() {
            Map<String, Object> environmentVariables = new HashMap();
            Map<String, Object> constants = new HashMap();
            String osName = getOsName();
            Locale locale = Locale.getDefault();
            environmentVariables.put("AppVersion", this.versionName);
            environmentVariables.put("AppIdentifier", this.packageName);
            environmentVariables.put("ExternalCacheDir", this.externalCacheDir);
            environmentVariables.put("OsVersion", VERSION.RELEASE);
            environmentVariables.put("AndroidOsVersionCode", Integer.valueOf(VERSION.SDK_INT));
            environmentVariables.put("OsName", osName);
            environmentVariables.put("CPUCount", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
            environmentVariables.put("CPUSpeed", Integer.valueOf(RNEnvironmentInfoPackage.c()));
            environmentVariables.put("Chipset", Build.HARDWARE);
            environmentVariables.put("DeviceId", this.instanceId);
            environmentVariables.put("SystemModel", Build.MODEL);
            environmentVariables.put("SystemManufacturer", Build.MANUFACTURER);
            environmentVariables.put("SystemLocale", locale.toString());
            environmentVariables.put("SystemCountry", locale.getCountry());
            environmentVariables.put("SystemLanguage", locale.getLanguage());
            environmentVariables.put("InitializationTimestamp", Long.valueOf(RNEnvironmentInfoPackage.this.a));
            environmentVariables.put("PreInstallPartnerId", this.preInstallPartnerId);
            environmentVariables.put("NetworkMCCCode", getMccCode());
            environmentVariables.put("NetworkMNCCode", getMncCode());
            environmentVariables.put("CpuAbi", Build.CPU_ABI);
            environmentVariables.put("AndroidPrivateCpuAbi", getSystemProperty("ro.product.cpu.abi"));
            constants.put("NativeEnvironmentInfo", environmentVariables);
            return constants;
        }

        public String getSystemProperty(String key) {
            String value = "";
            try {
                return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{key});
            } catch (Exception e) {
                return value;
            }
        }

        private String getOsName() {
            Field[] fields = VERSION_CODES.class.getFields();
            Map<Integer, String> m = new HashMap();
            for (Field field : fields) {
                try {
                    m.put(Integer.valueOf(field.getInt(new Object())), field.getName());
                } catch (Throwable e) {
                    FLog.e("RNEnvironmentInfoModule", "getOsName()", e);
                } catch (Throwable e2) {
                    FLog.e("RNEnvironmentInfoModule", "getOsName()", e2);
                } catch (Throwable e22) {
                    FLog.e("RNEnvironmentInfoModule", "getOsName()", e22);
                }
            }
            int sdk = VERSION.SDK_INT;
            if (m.containsKey(Integer.valueOf(sdk))) {
                return (String) m.get(Integer.valueOf(sdk));
            }
            return null;
        }

        private String getMncMccCode() {
            if (this.telephonyManager == null) {
                return null;
            }
            String mCCMncCode = this.telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(mCCMncCode)) {
                return null;
            }
            return mCCMncCode;
        }

        private String getMccCode() {
            String mCCMncCode = getMncMccCode();
            if (mCCMncCode != null && mCCMncCode.length() >= 3) {
                return mCCMncCode.substring(0, 3);
            }
            return null;
        }

        private String getMncCode() {
            String mCCMncCode = getMncMccCode();
            if (mCCMncCode != null && mCCMncCode.length() > 3) {
                return mCCMncCode.substring(3);
            }
            return null;
        }
    }

    public RNEnvironmentInfoPackage(long startTime) {
        this.a = startTime;
    }

    private static int c() {
        RandomAccessFile reader;
        Throwable e;
        Throwable th;
        int cpuCount = Runtime.getRuntime().availableProcessors();
        int cpuMaxFreq = 0;
        for (int cpu = 0; cpu < cpuCount; cpu++) {
            reader = null;
            try {
                RandomAccessFile reader2 = new RandomAccessFile("/sys/devices/system/cpu/cpu" + cpu + "/cpufreq/cpuinfo_max_freq", "r");
                try {
                    Integer freq = Integer.valueOf(reader2.readLine());
                    if (freq.intValue() > cpuMaxFreq) {
                        cpuMaxFreq = freq.intValue();
                    }
                    try {
                        reader2.close();
                        reader = reader2;
                    } catch (IOException e2) {
                        reader = reader2;
                    }
                } catch (IOException e3) {
                    e = e3;
                    reader = reader2;
                    try {
                        FLog.e("RNEnvironmentInfoModule", "getCpuMaxFrequency()", e);
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    reader = reader2;
                }
            } catch (IOException e5) {
                e = e5;
                FLog.e("RNEnvironmentInfoModule", "getCpuMaxFrequency()", e);
                if (reader != null) {
                    reader.close();
                }
            }
        }
        return cpuMaxFreq;
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e6) {
            }
        }
        throw th;
        throw th;
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new RNEnvironmentInfoModule(reactContext));
        return modules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
