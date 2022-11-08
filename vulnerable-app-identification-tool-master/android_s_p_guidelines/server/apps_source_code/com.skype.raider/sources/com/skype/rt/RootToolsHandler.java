package com.skype.rt;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RootToolsHandler extends RtContext {
    private static final boolean DBG = false;
    private static final String TAG = "rt::";
    private static Pattern m_p0 = Pattern.compile(".*?JNI local reference table has no entries.*");
    private static Pattern m_p1 = Pattern.compile(".*?JNI local reference table.*");
    private static Pattern m_p1m = Pattern.compile(".*?local reference table dump:.*");
    private static Pattern m_p1n = Pattern.compile(".*?JNI local reference table \\(0x(\\d|[a-zA-Z])+\\) dump:.*");
    private static Pattern m_p1old = Pattern.compile(".*?Last (\\d+) entries in JNI local reference table.*");
    private static Pattern m_p1summary = Pattern.compile(".*?JNI local reference table summary.*");
    private static Pattern m_p2 = Pattern.compile(".*?Last \\d+ entries \\(of (\\d+)\\):.*");
    private static Pattern m_p2n = Pattern.compile(".*?Last \\d+ entries \\(of \\d\\):.*");
    private static Pattern m_p2nempty = Pattern.compile(".*?\\(empty\\).*");
    private static Pattern m_p3 = Pattern.compile(".*?(\\d+:)\\s+0x[\\dabcdef]+\\s+(.*)");
    private static RootToolsHandler m_this = null;
    private Stack<ArrayDeque<String>> logStack = new Stack();
    private NetIntf m_netIntf;
    private HashSet m_paths;

    public native void callbackListInterfaces(int i, int i2, JniNetworkParams[] jniNetworkParamsArr);

    public native void notifyPowerEvent(boolean z);

    public static Object createHandler() {
        m_this = new RootToolsHandler();
        RtContext.getContext();
        return m_this;
    }

    public static void dropHandler() {
        if (m_this != null) {
            RootToolsHandler ri = m_this;
            if (ri.m_netIntf != null) {
                ri.m_netIntf.finish();
                ri.m_netIntf = null;
            }
        }
        m_this = null;
    }

    private RootToolsHandler() {
        new StringBuilder("SDK_INT: ").append(String.valueOf(VERSION.SDK_INT));
        this.m_netIntf = VERSION.SDK_INT >= 21 ? new NetInterface() : new NetInterfacePreLollipop();
        this.m_paths = new HashSet();
    }

    public JniNetworkParams activeConnectionType() {
        return this.m_netIntf.getActiveConnectionInfo();
    }

    public int uiModeType() {
        return RtContext.getContext().getResources().getConfiguration().uiMode & 15;
    }

    public int screenLayoutSize() {
        return RtContext.getContext().getResources().getConfiguration().screenLayout & 15;
    }

    public Object registerPowerReceiver() {
        PowerReceiver pr = new PowerReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        if (VERSION.SDK_INT >= 21) {
            filter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        }
        if (VERSION.SDK_INT >= 23) {
            filter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
        }
        RtContext.getContext().registerReceiver(pr, filter);
        return pr;
    }

    public void unregisterPowerReceiver(Object obj) {
        RtContext.getContext().unregisterReceiver((PowerReceiver) obj);
    }

    public int getPowerStatus() {
        if (VERSION.SDK_INT >= 23) {
            return ((PowerManager) RtContext.getContext().getSystemService("power")).isDeviceIdleMode() ? 1 : 0;
        } else {
            return -1;
        }
    }

    public int[] getPowerSourceInfo() {
        int i;
        int i2;
        int[] ret;
        int[] iArr = new int[]{-1, -1, -1};
        PowerManager pm = (PowerManager) RtContext.getContext().getSystemService("power");
        BatteryManager bm = (BatteryManager) RtContext.getContext().getSystemService("batterymanager");
        Intent intent = RtContext.getContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (VERSION.SDK_INT < 21 || !pm.isPowerSaveMode()) {
            int plugged = intent.getIntExtra("plugged", -1);
            boolean isPlugged = ((plugged == 1 ? 1 : 0) | 0) | (plugged == 2 ? 1 : 0);
            if (VERSION.SDK_INT >= 17) {
                isPlugged |= plugged == 4 ? 1 : 0;
            }
            i = 0;
            if (isPlugged) {
                i2 = 0;
                ret = iArr;
            } else {
                i2 = 1;
                ret = iArr;
            }
        } else {
            i = 0;
            i2 = 2;
            ret = iArr;
        }
        ret[i] = i2;
        int lvl = intent.getIntExtra("level", 0);
        int scl = intent.getIntExtra("scale", -1);
        if (scl <= 0) {
            iArr[1] = 0;
        } else {
            iArr[1] = (lvl * 100) / scl;
        }
        if (VERSION.SDK_INT >= 21) {
            int charge = bm.getIntProperty(1);
            int capacity = bm.getIntProperty(4);
            long energy = bm.getLongProperty(5);
            if (charge > 0 && capacity > 0 && energy > 0) {
                iArr[2] = (int) ((((energy / 1000000) * ((long) charge)) * 100) / ((long) capacity));
            }
        }
        return iArr;
    }

    public Object registerConnectivityChangeReceiver(int writeFd) {
        ConnectivityChangeReceiver ccr = new ConnectivityChangeReceiver(writeFd, this);
        RtContext.getContext().registerReceiver(ccr, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        return ccr;
    }

    public void unregisterConnectivityChangeReceiver(Object obj) {
        RtContext.getContext().unregisterReceiver((ConnectivityChangeReceiver) obj);
    }

    public void listInterfaces(int writeFd) {
        JniNetworkParams[] intfs = this.m_netIntf.getConnectionInfos();
        callbackListInterfaces(writeFd, intfs.length, intfs);
    }

    public WiFiNetworkStatus getWiFiNetworkStatus() {
        WifiManager wm = RtContext.getWifiManager();
        if (!wm.isWifiEnabled()) {
            return new WiFiNetworkStatus(false, "", "", "", 0, 0);
        }
        WifiInfo inf = wm.getConnectionInfo();
        return new WiFiNetworkStatus(true, inf.getMacAddress(), inf.getSSID(), inf.getBSSID(), inf.getLinkSpeed(), VERSION.SDK_INT >= 21 ? inf.getFrequency() : 0);
    }

    public WiFiNetworkState getWiFiNetworkState() {
        int freq = 0;
        WifiManager wm = RtContext.getWifiManager();
        if (!wm.isWifiEnabled()) {
            return new WiFiNetworkState(0, 0);
        }
        WifiInfo inf = wm.getConnectionInfo();
        if (VERSION.SDK_INT >= 21) {
            freq = inf.getFrequency();
        }
        return new WiFiNetworkState(freq, inf.getRssi());
    }

    private boolean dumpReferenceTables() {
        try {
            Method m = Debug.class.getDeclaredMethod("dumpReferenceTables", new Class[0]);
            m.setAccessible(true);
            m.invoke(null, new Object[0]);
            m.setAccessible(false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void extractLocalReferenceTableFromLog(boolean secondPass) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
            ArrayDeque<String> dump = new ArrayDeque();
            dump.offer("-1");
            boolean inspectLine = false;
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    this.logStack.push(dump);
                    return;
                } else if (m_p0.matcher(line).matches()) {
                    dump = new ArrayDeque();
                    dump.offer("Empty local refs table");
                } else if (m_p1summary.matcher(line).matches()) {
                    inspectLine = false;
                } else if (m_p1old.matcher(line).matches()) {
                    dump = new ArrayDeque();
                    inspectLine = true;
                } else if (m_p1n.matcher(line).matches() || m_p1m.matcher(line).matches()) {
                    dump = new ArrayDeque();
                    line = bufferedReader.readLine();
                    if (m_p2nempty.matcher(line).matches()) {
                        dump.offer("Empty local refs table");
                    } else {
                        inspectLine = true;
                        if (m_p2n.matcher(line).matches()) {
                        }
                    }
                } else if (m_p1.matcher(line).matches()) {
                    dump = new ArrayDeque();
                    inspectLine = true;
                    if (m_p2.matcher(bufferedReader.readLine()).matches()) {
                    }
                } else if (inspectLine) {
                    Matcher m3 = m_p3.matcher(line);
                    if (m3.matches()) {
                        dump.offer(m3.group(1) + " " + m3.group(2));
                    } else {
                        inspectLine = false;
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    private String popAndCompareLatestDumps() {
        if (this.logStack.size() < 2) {
            return "BAD: logStack.size() < 2\n";
        }
        ArrayDeque<String> d1 = (ArrayDeque) this.logStack.pop();
        ArrayDeque<String> d0 = (ArrayDeque) this.logStack.pop();
        String s0;
        if (d1.size() != d0.size()) {
            StringBuilder sb = new StringBuilder();
            sb.append("BAD: dump sizes mismatch\n-- Dump 1:\n");
            while (true) {
                s0 = (String) d0.poll();
                if (s0 == null) {
                    break;
                }
                sb.append(s0);
            }
            sb.append("\n-- Dump 2:\n");
            while (true) {
                s0 = (String) d1.poll();
                if (s0 != null) {
                    sb.append(s0);
                } else {
                    sb.append("\n-- End dumps\n");
                    return sb.toString();
                }
            }
        }
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        boolean failed = false;
        while (true) {
            s0 = (String) d0.poll();
            if (s0 == null) {
                break;
            }
            String s1 = (String) d1.poll();
            sb0.append(s0);
            sb1.append(s1);
            if (!s0.equals(s1)) {
                failed = true;
            }
        }
        if (failed) {
            return "BAD: dump lines mismatch\n-- Dump 1:\n" + sb0.toString() + "\n-- Dump 2:\n" + sb1.toString() + "\n-- End dumps\n";
        }
        return "GOOD: No local reference leaks";
    }

    public void dumpLocalReferenceTables() {
        dumpReferenceTables();
        extractLocalReferenceTableFromLog(false);
    }

    public String dumpAndCompareLocalReferenceTables() {
        dumpReferenceTables();
        extractLocalReferenceTableFromLog(true);
        return popAndCompareLatestDumps();
    }

    public String[] pickInterface(int type, String remoteAddr) {
        return this.m_netIntf.pickInterface(type, remoteAddr);
    }

    public void dropInterface(int type) {
        this.m_netIntf.dropInterface(type);
    }

    public boolean assetManagerPathExists(String path) {
        if (path.length() == 0 || this.m_paths.contains(path)) {
            return true;
        }
        String dir = "";
        int v = path.lastIndexOf(File.separator);
        if (v > 1) {
            dir = path.substring(0, v);
            if (v + 1 < path.length()) {
                path.substring(v + 1);
            }
        }
        try {
            String[] l = RtContext.getContext().getAssets().list(dir);
            for (int i = 0; i < l.length; i++) {
                if (dir.length() > 0) {
                    this.m_paths.add(dir + File.separator + l[i]);
                } else {
                    this.m_paths.add(l[i]);
                }
            }
            return this.m_paths.contains(path);
        } catch (IOException e) {
            return false;
        }
    }

    public FileStat assetManagerPathStat(String path) {
        AssetManager assetMgr = RtContext.getContext().getAssets();
        boolean isDirectory = false;
        long size = 0;
        if (assetManagerPathExists(path)) {
            try {
                InputStream is = assetMgr.open(path);
                size = (long) is.available();
                is.close();
            } catch (IOException e) {
                isDirectory = true;
            }
        }
        return new FileStat(isDirectory, size);
    }

    public String[] assetManagerReadDirectory(String path) {
        String[] l = new String[0];
        try {
            return RtContext.getContext().getAssets().list(path);
        } catch (IOException e) {
            return l;
        }
    }

    public boolean assetManagerCacheFile(String assetPath, String cachePath) {
        try {
            InputStream is = RtContext.getContext().getAssets().open(assetPath, 3);
            File cacheFile = new File(cachePath);
            File cacheDir = cacheFile.getParentFile();
            if (cacheDir.exists() || cacheDir.mkdirs()) {
                if (cacheFile.exists() && !cacheFile.delete()) {
                    new StringBuilder("assetManagerCacheFile: error deleting ").append(cacheFile.toString());
                }
                if (!cacheFile.exists()) {
                    try {
                        cacheFile.createNewFile();
                    } catch (IOException ex) {
                        new StringBuilder("assetManagerCacheFile: exception creating ").append(cacheFile.toString());
                        if (ex.getCause() != null) {
                            new StringBuilder("Exception msg: ").append(ex.getCause().toString());
                        }
                        try {
                            is.close();
                            return false;
                        } catch (Exception e) {
                            return false;
                        }
                    }
                }
                try {
                    FileOutputStream os = new FileOutputStream(cacheFile);
                    byte[] buffer = new byte[1024];
                    while (true) {
                        try {
                            int bytesRead = is.read(buffer);
                            if (bytesRead > 0) {
                                os.write(buffer, 0, bytesRead);
                            } else {
                                try {
                                    is.close();
                                    os.close();
                                } catch (Exception e2) {
                                }
                                cacheFile.setReadOnly();
                                return true;
                            }
                        } catch (IOException ex2) {
                            new StringBuilder("assetManagerCacheFile: exception reading/writing ").append(assetPath).append(" to ").append(cachePath);
                            if (ex2.getCause() != null) {
                                new StringBuilder("Exception msg: ").append(ex2.getCause().toString());
                            }
                            try {
                                is.close();
                                os.close();
                                return false;
                            } catch (Exception e3) {
                                return false;
                            }
                        } catch (Throwable th) {
                            try {
                                is.close();
                                os.close();
                            } catch (Exception e4) {
                            }
                            throw th;
                        }
                    }
                } catch (IOException ex22) {
                    if (ex22.getCause() != null) {
                        new StringBuilder("Exception msg: ").append(ex22.getCause().toString());
                    }
                    try {
                        is.close();
                        return false;
                    } catch (Exception e5) {
                        return false;
                    }
                }
            }
            new StringBuilder("assetManagerCacheFile: error creating ").append(cacheDir.toString());
            try {
                is.close();
                return false;
            } catch (Exception e6) {
                return false;
            }
        } catch (IOException ex222) {
            if (ex222.getCause() == null) {
                return false;
            }
            new StringBuilder("Exception msg: ").append(ex222.getCause().toString());
            return false;
        }
    }

    public String getDataDirectoryPath() {
        try {
            return RtContext.getContext().getApplicationInfo().dataDir;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTempDirectoryPath() {
        try {
            return RtContext.getContext().getCacheDir().getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    public String getSecureDirectoryPath() {
        try {
            return RtContext.getContext().getFilesDir().getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    public String[] fingerprintInfo() {
        String[] fpFields = new String[6];
        fpFields[0] = Secure.getString(RtContext.getContext().getContentResolver(), "android_id");
        fpFields[1] = Build.SERIAL;
        fpFields[2] = Build.getRadioVersion();
        TelephonyManager mgr = (TelephonyManager) RtContext.getContext().getSystemService("phone");
        if (mgr != null) {
            fpFields[3] = mgr.getDeviceId();
            fpFields[4] = mgr.getSimSerialNumber();
            fpFields[5] = mgr.getSubscriberId();
        }
        return fpFields;
    }

    public long[] getNetworkStats() {
        return new long[]{TrafficStats.getTotalTxBytes(), TrafficStats.getTotalTxPackets(), TrafficStats.getTotalRxBytes(), TrafficStats.getTotalRxPackets()};
    }
}
