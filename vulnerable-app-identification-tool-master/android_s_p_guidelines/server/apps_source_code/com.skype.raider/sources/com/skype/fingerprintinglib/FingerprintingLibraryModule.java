package com.skype.fingerprintinglib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.support.v4.content.a;
import android.telephony.CellLocation;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.h;
import com.google.android.gms.common.i;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class FingerprintingLibraryModule extends ReactContextBaseJavaModule {
    private static final String APPLICATION_GUID_KEY = "FingerprintingLibAppGUID";
    private static final String LIBRARY_SETTINGS_KEY = "FingerprintingLibSettings";
    private ArrayList<String> androidAppsToCheck = new ArrayList(Arrays.asList(new String[]{"com.ebay.classifieds/com.ebay.app.MainTabActivity", "com.ebay.mobile/com.ebay.mobile.activities.eBay", "com.ebay.redlaser/com.ebay.redlaser.ScannedItemsActivity", "com.milo.android/com.milo.android.activity.HomeActivity", "com.paypal.android.p2pmobile/com.paypal.android.p2pmobile.activity.SendMoneyActivity", "com.rent/com.rent.activities.session.ActivityHome", "com.stubhub/com.stubhub.About", "com.ulocate/com.ulocate.activities.Settings", "com.noshufou.android.su/com.noshufou.android.su.Su", "stericson.busybox/stericson.busybox.Activity.MainActivity", "org.proxydroid/org.proxydroid.ProxyDroid", "com.aed.droidvpn/com.aed.droidvpn.MainGUI", "net.openvpn.openvpn/net.openvpn.openvpn.OpenVPNClient", "com.phoneapps99.aabiproxy/com.phoneapps99.aabiproxy.Orbot", "com.evanhe.proxymanager.pro/com.evanhe.proxymanager.MainActivity", "com.evanhe.proxymanager/com.evanhe.proxymanager.MainActivity", "com.andromo.dev30936.app76198/com.andromo.dev30936.app76198.AndromoDashboardActivity", "com.mgranja.autoproxy_lite/com.mgranja.autoproxy_lite.ProxyListActivity", "com.vpnoneclick.android/com.vpnoneclick.android.MainActivity", "net.hideman/net.hideman.StarterActivity", "com.doenter.android.vpn.fivevpn/com.doenter.android.vpn.fivevpn.FiveVpn", "com.tigervpns.android/com.tigervpns.android.MainActivity", "com.pandapow.vpn/com.pandapow.vpn.PandaPow", "com.expressvpn.vpn/com.expressvpn.vpn.MainActivity", "com.londontrustmedia.vpn/com.londontrustmedia.vpn.VpnServiceActivity", "fr.melecom.VPNPPTP.v101/fr.melecom.VPNPPTP.v101.SplashScreen", "com.checkpoint.VPN/com.checkpoint.VPN.MainHandler", "com.tunnelbear.android/com.tunnelbear.android.TbearMainActivity", "de.blinkt.openvpn/de.blinkt.openvpn.MainActivity", "org.ajeje.fakelocation/org.ajeje.fakelocation.Fake", "com.lexa.fakegps/com.lexa.fakegps.PickPoint", "com.forgottenprojects.mocklocations/com.forgottenprojects.mocklocations.Main", "kr.woot0pia.gps/kr.woot0pia.gps.CatchMeIfUCan", "com.my.fake.location/com.my.fake.location.com.my.fake.location", "jp.netart.arstalking/jp.netart.arstalking.MyPreferenceActivity", "locationPlay.GPSCheatFree/locationPlay.GPSCheatFree.ActivitySmartLocation", "org.goodev.latitude/org.goodev.latitude.LatitudeActivity", "com.scheffsblend.devicespoof/com.scheffsblend.devicespoof.DeviceSpoofActivity", "com.proxyBrowser/com.proxyBrowser.NewProxyBrowserActivity", "com.icecoldapps.proxyserverpro/com.icecoldapps.proxyserverpro.viewStart", "hotspotshield.android.vpn/com.anchorfree.ui.HotSpotShield", "com.doenter.onevpn/com.doenter.onevpn.VpnSettings", "com.yesvpn.en/com.yesvpn.MainTab", "com.officewyze.plutovpn/com.officewyze.plutovpn.HomeActivity", "org.ajeje.locationspooferpro/org.ajeje.locationspooferpro.Fake", "locationPlay.GPSCheat/locationPlay.GPSCheat.ActivitySmartLocation"}));
    private Context context;
    private TelephonyManager telephonyManager;

    public FingerprintingLibraryModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    public String getName() {
        return "FingerprintingLib";
    }

    protected String getApplicationGUID() {
        SharedPreferences preferences = this.context.getSharedPreferences(LIBRARY_SETTINGS_KEY, 0);
        String applicationGuid = preferences.getString(APPLICATION_GUID_KEY, null);
        if (applicationGuid != null) {
            return applicationGuid;
        }
        applicationGuid = UUID.randomUUID().toString();
        Editor editor = preferences.edit();
        editor.putString(APPLICATION_GUID_KEY, applicationGuid);
        editor.apply();
        return applicationGuid;
    }

    @ReactMethod
    public void getUserData(ae promise) {
        try {
            FingerprintReport fingerprintReport = new FingerprintReport();
            fillApplicationDetails(fingerprintReport);
            fillConnectionInfo(fingerprintReport);
            fillDeviceInfo(fingerprintReport);
            fillGsfId(fingerprintReport);
            fillLocationDetails(fingerprintReport);
            fillDatetimeInfo(fingerprintReport);
            fillKnownApps(fingerprintReport);
            promise.a(fingerprintReport.a());
        } catch (Throwable e) {
            promise.a(e.getMessage(), e);
        }
    }

    private void fillKnownApps(FingerprintReport fingerprintReport) {
        List<ApplicationInfo> packages = this.context.getPackageManager().getInstalledApplications(128);
        fingerprintReport.T = new ArrayList();
        for (ApplicationInfo packageInfo : packages) {
            fingerprintReport.T.add(packageInfo.packageName);
        }
        fingerprintReport.T.retainAll(this.androidAppsToCheck);
    }

    private void fillApplicationDetails(FingerprintReport fingerprintReport) {
        try {
            fingerprintReport.a = getApplicationGUID();
            fingerprintReport.b = this.context.getPackageName();
            PackageInfo appPackage = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            fingerprintReport.e = appPackage.versionName;
            fingerprintReport.c = appPackage.firstInstallTime;
            fingerprintReport.d = appPackage.lastUpdateTime;
        } catch (NameNotFoundException e) {
        }
    }

    private void fillDatetimeInfo(FingerprintReport fingerprintReport) {
        Date now = new Date();
        TimeZone tz = TimeZone.getDefault();
        fingerprintReport.L = tz.inDaylightTime(now);
        fingerprintReport.P = (long) tz.getOffset(now.getTime());
        fingerprintReport.Q = String.valueOf(tz.getDisplayName(fingerprintReport.L, 1, Locale.ENGLISH));
        fingerprintReport.O = System.currentTimeMillis();
    }

    public void fillDeviceInfo(final FingerprintReport fingerprintReport) {
        fingerprintReport.q = VERSION.RELEASE;
        fingerprintReport.i = Build.MANUFACTURER + ' ' + Build.MODEL;
        fingerprintReport.j = Build.DEVICE;
        fingerprintReport.s = Build.SERIAL;
        fingerprintReport.g = Secure.getString(this.context.getContentResolver(), "android_id");
        fingerprintReport.N = Locale.getDefault().getLanguage();
        fingerprintReport.M = Locale.getDefault().getCountry();
        fingerprintReport.m = this.context.getPackageManager().hasSystemFeature("android.hardware.telephony");
        if (isPermissionGranted("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = getTelephonyManager();
            fingerprintReport.h = telephonyManager.getDeviceId();
            switch (telephonyManager.getPhoneType()) {
                case 0:
                    fingerprintReport.r = "none";
                    break;
                case 1:
                    fingerprintReport.r = "gsm";
                    break;
                case 2:
                    fingerprintReport.r = "cdma";
                    break;
                default:
                    fingerprintReport.r = "unknown (" + telephonyManager.getPhoneType() + ")";
                    break;
            }
            fingerprintReport.C = telephonyManager.getSimSerialNumber();
            fingerprintReport.D = telephonyManager.getSubscriberId();
            fingerprintReport.B = telephonyManager.getNetworkOperatorName();
            fingerprintReport.A = telephonyManager.getNetworkOperator();
        }
        fingerprintReport.k = SystemClock.uptimeMillis();
        fingerprintReport.n = isEmulator();
        fingerprintReport.o = RootChecker.a();
        new AsyncTask<Void, Void, String>(this) {
            final /* synthetic */ FingerprintingLibraryModule b;

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return a();
            }

            protected final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
                fingerprintReport.f = (String) obj;
            }

            private String a() {
                try {
                    Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.b.context);
                    if (adInfo != null) {
                        return adInfo.getId();
                    }
                    return null;
                } catch (h e) {
                    return null;
                } catch (i e2) {
                    return null;
                } catch (IOException e3) {
                    return null;
                }
            }
        }.execute(new Void[0]);
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        fingerprintReport.t = ((long) stat.getBlockSize()) * ((long) stat.getBlockCount());
    }

    private TelephonyManager getTelephonyManager() {
        if (this.telephonyManager == null) {
            this.telephonyManager = (TelephonyManager) this.context.getSystemService("phone");
        }
        return this.telephonyManager;
    }

    private void fillConnectionInfo(FingerprintReport fingerprintReport) {
        if (isPermissionGranted("android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) this.context.getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            fingerprintReport.K = connectionInfo.getSSID();
            fingerprintReport.I = connectionInfo.getBSSID();
            if (isPermissionGranted("android.permission.CHANGE_WIFI_STATE") && (isPermissionGranted("android.permission.ACCESS_COARSE_LOCATION") || isPermissionGranted("android.permission.ACCESS_FINE_LOCATION"))) {
                Comparator<ScanResult> comparator = new Comparator<ScanResult>(this) {
                    final /* synthetic */ FingerprintingLibraryModule a;

                    {
                        this.a = this$0;
                    }

                    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                        ScanResult scanResult = (ScanResult) obj;
                        ScanResult scanResult2 = (ScanResult) obj2;
                        if (scanResult.level < scanResult2.level) {
                            return -1;
                        }
                        return scanResult.level == scanResult2.level ? 0 : 1;
                    }
                };
                if (wifiManager.startScan()) {
                    List<ScanResult> results = wifiManager.getScanResults();
                    Collections.sort(results, comparator);
                    fingerprintReport.J = new ArrayList();
                    if (results.size() > 0) {
                        fingerprintReport.J.add(((ScanResult) results.get(0)).BSSID.toString());
                        if (((ScanResult) results.get(0)).BSSID.toString() != fingerprintReport.I) {
                            fingerprintReport.J.add(fingerprintReport.I);
                        } else if (results.size() > 1) {
                            fingerprintReport.J.add(((ScanResult) results.get(1)).BSSID.toString());
                        }
                    }
                }
            }
        }
        String proxyAddress = System.getProperty("http.proxyHost");
        if (!(proxyAddress == null || proxyAddress.isEmpty())) {
            fingerprintReport.F = proxyAddress;
            fingerprintReport.G = (long) Integer.parseInt(System.getProperty("http.proxyPort"));
        }
        fingerprintReport.E = new ArrayList();
        if (isPermissionGranted("android.permission.ACCESS_NETWORK_STATE")) {
            try {
                Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                while (en.hasMoreElements()) {
                    NetworkInterface intf = (NetworkInterface) en.nextElement();
                    if (intf.isUp() && (intf.getName().startsWith("tun") || intf.getName().startsWith("tap") || intf.getName().startsWith("pop"))) {
                        fingerprintReport.H = intf.getName();
                    }
                    Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                    while (enumIpAddr.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            int positionOfPercentSeparator = hostAddress.indexOf("%");
                            if (positionOfPercentSeparator != -1) {
                                hostAddress = hostAddress.substring(0, positionOfPercentSeparator);
                            }
                            fingerprintReport.E.add(hostAddress);
                        }
                    }
                }
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    fingerprintReport.y = activeNetworkInfo.getTypeName();
                }
                fingerprintReport.z = new ServiceState().getRoaming();
            } catch (SocketException e) {
            }
        }
    }

    private void fillLocationDetails(FingerprintReport fingerprintReport) {
        if (isPermissionGranted("android.permission.ACCESS_COARSE_LOCATION") || isPermissionGranted("android.permission.ACCESS_FINE_LOCATION")) {
            Location location = ((LocationManager) this.context.getSystemService("location")).getLastKnownLocation("gps");
            if (location != null) {
                fingerprintReport.R = new GeoLocation(location.getLatitude(), location.getLongitude(), location.getAccuracy(), location.getTime());
            }
            CellLocation cellLocation = getTelephonyManager().getCellLocation();
            if (cellLocation != null) {
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    fingerprintReport.S = String.valueOf(gsmCellLocation.getLac());
                    fingerprintReport.x = String.valueOf(gsmCellLocation.getCid() & 65535);
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    fingerprintReport.u = String.valueOf(cdmaCellLocation.getBaseStationId());
                    fingerprintReport.v = (long) cdmaCellLocation.getNetworkId();
                    fingerprintReport.w = (long) cdmaCellLocation.getSystemId();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fillGsfId(FingerprintReport fingerprintReport) {
        if (isPermissionGranted("com.google.android.providers.gsf.permission.READ_GSERVICES")) {
            Cursor cursor = null;
            try {
                cursor = this.context.getContentResolver().query(Uri.parse("content://com.google.android.gsf.gservices"), null, null, new String[]{"android_id"}, null);
                if (cursor.moveToFirst() && cursor.getColumnCount() > 2) {
                    fingerprintReport.l = Long.toHexString(Long.parseLong(cursor.getString(1)));
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (NumberFormatException e) {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    private static boolean isEmulator() {
        return Build.BRAND.equalsIgnoreCase("generic");
    }

    private boolean isPermissionGranted(String permission) {
        return a.a(this.context, permission) == 0;
    }
}
