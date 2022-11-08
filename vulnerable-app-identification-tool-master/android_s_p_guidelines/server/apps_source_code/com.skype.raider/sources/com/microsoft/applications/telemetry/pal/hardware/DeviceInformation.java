package com.microsoft.applications.telemetry.pal.hardware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.microsoft.applications.telemetry.core.TraceHelper;
import com.microsoft.applications.telemetry.datamodels.PowerSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"NewApi"})
public class DeviceInformation {
    private static final int BYTE_MULTIPLIER = 1024;
    private static final String LOG_TAG = ("[ACT]:" + DeviceInformation.class.getSimpleName().toUpperCase());
    private static final String MEMORY_SIZE = "MemTotal";
    private static String m_cpu_manufacturer;
    private static String m_cpu_model;
    private static String m_device_id = "";
    private static boolean m_has_front_camera = false;
    private static boolean m_has_rear_camera = false;
    private static double m_height_dpi = 0.0d;
    private static int m_height_inches = 0;
    private static int m_height_pixels = 0;
    private static long m_internal_storage = 0;
    private static String m_manufacturer;
    private static long m_memory_size_mb = 0;
    private static String m_model;
    private static int m_os_architecture_type = 0;
    private static PowerSource m_power_src = PowerSource.UNKNOWN;
    private static boolean m_support_digitizer = false;
    private static boolean m_support_touch = false;
    private static double m_width_dpi = 0.0d;
    private static int m_width_inches = 0;
    private static int m_width_pixels = 0;

    private enum OsArchitectureType {
        ARCH_UNKNOWN(0),
        ARCH_X86(1),
        ARCH_X64(2),
        ARCH_ARM(3);
        
        private final int osArchType;

        private OsArchitectureType(int archType) {
            this.osArchType = archType;
        }

        public final int getValue() {
            return this.osArchType;
        }
    }

    static {
        m_manufacturer = "";
        m_model = "";
        try {
            m_manufacturer = Build.MANUFACTURER;
            m_model = Build.MODEL;
        } catch (Exception e) {
            TraceHelper.TraceError(LOG_TAG, "Exception when trying to init DeviceInformation", e);
        }
    }

    public static String getDeviceId() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getDeviceId|value: %s", new Object[]{m_device_id}));
        return m_device_id;
    }

    public static String getManufacturer() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getManufacturer|value: %s", new Object[]{m_manufacturer}));
        return m_manufacturer;
    }

    public static String getModel() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getModel|value: %s", new Object[]{m_model}));
        return m_model;
    }

    public static String getCpuMake() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getCpuMake|value: %s", new Object[]{m_cpu_manufacturer}));
        return m_cpu_manufacturer;
    }

    public static String getCpuModel() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getCpuMake|value: %s", new Object[]{m_cpu_model}));
        return m_cpu_model;
    }

    public static long getMemorySize() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getMemorySize|value: %s", new Object[]{Long.valueOf(m_memory_size_mb)}));
        return m_memory_size_mb;
    }

    public static boolean isDigitizerAvailable() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("isDigitizerAvailable|value:%s", new Object[]{Boolean.valueOf(m_support_digitizer)}));
        return m_support_digitizer;
    }

    public static boolean isTouchAvailable() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("isTouchAvailable|value:%s", new Object[]{Boolean.valueOf(m_support_touch)}));
        return m_support_touch;
    }

    public static boolean isFrontCameraAvailable() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("isFrontCameraAvailable|value:%s", new Object[]{Boolean.valueOf(m_has_front_camera)}));
        return m_has_front_camera;
    }

    public static boolean isRearCameraAvailable() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("isRearCameraAvailable|value:%s", new Object[]{Boolean.valueOf(m_has_rear_camera)}));
        return m_has_rear_camera;
    }

    public static int getScreenHeightInInches() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getScreenHeightInInches|value:%s", new Object[]{Integer.valueOf(m_height_inches)}));
        return m_height_inches;
    }

    public static int getScreenWidthInInches() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getScreenWidthInInches|value:%s", new Object[]{Integer.valueOf(m_width_inches)}));
        return m_width_inches;
    }

    public static int getScreenHeightInPixels() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getScreenHeightInPixels|value:%s", new Object[]{Integer.valueOf(m_height_pixels)}));
        return m_height_pixels;
    }

    public static int getScreenWidthInPixels() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getScreenWidthInPixels|value:%s", new Object[]{Integer.valueOf(m_width_pixels)}));
        return m_width_pixels;
    }

    public static double getScreenHeightDPI() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getScreenHeightDPI|value:%s", new Object[]{Double.valueOf(m_height_dpi)}));
        return m_height_dpi;
    }

    public static double getScreenWidthDPI() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getMemorySize|value %s", new Object[]{Long.valueOf(m_memory_size_mb)}));
        return m_width_dpi;
    }

    public static synchronized void update(Context context) {
        synchronized (DeviceInformation.class) {
            try {
                String string = Secure.getString(context.getContentResolver(), "android_id");
                m_device_id = string;
                m_device_id = string == null ? "" : m_device_id;
                updatePowerInfo(context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
            } catch (Exception e) {
                TraceHelper.TraceError(LOG_TAG, "Exception when trying to update DeviceInformation", e);
            }
        }
        return;
    }

    private static void determineDisplayInfo(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        display.getSize(new Point());
        DisplayMetrics display_metrics = new DisplayMetrics();
        display.getMetrics(display_metrics);
        m_height_dpi = (double) display_metrics.ydpi;
        m_width_dpi = (double) display_metrics.xdpi;
        m_height_inches = (int) (((float) display_metrics.heightPixels) / display_metrics.ydpi);
        m_width_inches = (int) (((float) display_metrics.widthPixels) / display_metrics.xdpi);
        m_height_pixels = display_metrics.heightPixels;
        m_width_pixels = display_metrics.widthPixels;
    }

    private static void determineHardwareSupport(Context context) {
        PackageManager pkg_mgr = context.getPackageManager();
        m_has_rear_camera = pkg_mgr.hasSystemFeature("android.hardware.camera");
        m_has_front_camera = pkg_mgr.hasSystemFeature("android.hardware.camera.front");
        m_support_touch = pkg_mgr.hasSystemFeature("android.hardware.touchscreen");
        m_support_digitizer = false;
    }

    private static Map<String, String> getProcInfo(String procPath) {
        IOException e;
        Throwable th;
        Map<String, String> data = new HashMap();
        if (new File(procPath).exists()) {
            BufferedReader br = null;
            try {
                BufferedReader br2 = new BufferedReader(new FileReader(new File(procPath)));
                while (true) {
                    try {
                        String aLine = br2.readLine();
                        if (aLine != null) {
                            String[] tokens = aLine.split(":");
                            String key = tokens[0].replaceAll("\t", "").trim();
                            String value = "";
                            if (tokens.length > 1) {
                                value = tokens[1].trim();
                            }
                            data.put(key, value);
                        } else {
                            try {
                                br2.close();
                                break;
                            } catch (IOException e2) {
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        br = br2;
                    } catch (Throwable th2) {
                        th = th2;
                        br = br2;
                    }
                }
            } catch (IOException e4) {
                e = e4;
                try {
                    TraceHelper.TraceError(LOG_TAG, "Error reading device process information: " + e.getMessage());
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e5) {
                        }
                    }
                    return data;
                } catch (Throwable th3) {
                    th = th3;
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            }
        }
        return data;
    }

    private static Map<String, String> getCpuInfo() {
        return getProcInfo("/proc/cpuinfo");
    }

    private static Map<String, String> getMemoryInfo() {
        return getProcInfo("/proc/meminfo");
    }

    private static long convertMemoryStringToMb(String memstr) {
        long mb = 0;
        try {
            return ((long) Integer.parseInt(memstr.split(" ")[0])) / 1024;
        } catch (NumberFormatException e) {
            TraceHelper.TraceError(LOG_TAG, "Error reading memory information: " + e.getMessage());
            return mb;
        }
    }

    private static long getInternalStorageSize() {
        return calculateStorageSize(Environment.getRootDirectory());
    }

    private static long getSdCardStorageSize() {
        try {
            String state = Environment.getExternalStorageState();
            if (!"mounted".equals(state) && !"mounted_ro".equals(state)) {
                return 0;
            }
            File path = Environment.getExternalStorageDirectory();
            if (path.exists()) {
                return calculateStorageSize(path);
            }
            return 0;
        } catch (Exception e) {
            TraceHelper.TraceError(LOG_TAG, "Exception when trying to get Sd Card Storage information.", e);
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    private static long calculateStorageSize(File path) {
        StatFs statFs = new StatFs(path.getAbsolutePath());
        long block_size = VERSION.SDK_INT < 18 ? (long) statFs.getBlockSize() : statFs.getBlockSizeLong();
        return VERSION.SDK_INT < 18 ? ((long) statFs.getBlockCount()) * block_size : statFs.getBlockCountLong() * block_size;
    }

    private static long convertBytesToMb(long bytes) {
        return bytes / 1048576;
    }

    protected static synchronized void updatePowerInfo(Intent powerIntent) {
        Object obj = null;
        synchronized (DeviceInformation.class) {
            if (powerIntent != null) {
                Object obj2;
                int chargePlug = powerIntent.getIntExtra("plugged", -1);
                boolean usbCharge;
                if (chargePlug == 2) {
                    usbCharge = true;
                } else {
                    usbCharge = false;
                }
                if (chargePlug == 1) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (obj2 != null || usbCharge) {
                    obj = 1;
                }
                m_power_src = obj != null ? PowerSource.AC : PowerSource.BATTERY;
            }
        }
    }

    protected static synchronized void updateStorageInfo() {
        synchronized (DeviceInformation.class) {
            m_internal_storage = convertBytesToMb(getSdCardStorageSize() + getInternalStorageSize());
        }
    }

    public static synchronized long getStorageSize() {
        long j;
        synchronized (DeviceInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getStorageSize|value:%s", new Object[]{Long.valueOf(m_internal_storage)}));
            j = m_internal_storage;
        }
        return j;
    }

    public static synchronized PowerSource getPowerSource() {
        PowerSource powerSource;
        synchronized (DeviceInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getPowerSource|value:%s", new Object[]{m_power_src}));
            powerSource = m_power_src;
        }
        return powerSource;
    }
}
