package com.skype.rt;

import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.skype.rt.JniNetworkParams.NetworkConnectionCost;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetInterfacePreLollipop extends RtContext implements NetIntf {
    private static final String TAG = "rt.netIntfPre21::";
    private static Pattern m_ipAddrPat = Pattern.compile("/([a-fA-F0-9\\.:]+)(%.*)?");
    private static Method requestRouteToHostMethod = null;
    private static Method startUsingNetworkFeatureMethod = null;
    private static Method stopUsingNetworkFeatureMethod = null;
    private int m_cntMobile = 0;
    private boolean m_mobileInitiallyEnabled;
    private Timer m_timer = null;

    private static boolean InitStaticProcs() {
        if ((startUsingNetworkFeatureMethod == null || stopUsingNetworkFeatureMethod == null || requestRouteToHostMethod == null) && RtContext.getContext() != null) {
            try {
                startUsingNetworkFeatureMethod = RtContext.getConnectivityManager().getClass().getMethod("startUsingNetworkFeature", new Class[]{Integer.TYPE, String.class});
                stopUsingNetworkFeatureMethod = RtContext.getConnectivityManager().getClass().getMethod("stopUsingNetworkFeature", new Class[]{Integer.TYPE, String.class});
                requestRouteToHostMethod = RtContext.getConnectivityManager().getClass().getMethod("requestRouteToHost", new Class[]{Integer.TYPE, Integer.TYPE});
            } catch (NoSuchMethodException e) {
            }
        }
        if (startUsingNetworkFeatureMethod == null || stopUsingNetworkFeatureMethod == null || requestRouteToHostMethod == null) {
            return false;
        }
        return true;
    }

    public NetInterfacePreLollipop() {
        InitStaticProcs();
    }

    private JniNetworkParams convertToJniNetworkParams(NetworkInfo info, boolean isActive) {
        TelephonyManager mgr = (TelephonyManager) RtContext.getContext().getSystemService("phone");
        NetworkConnectionCost cost = NetworkConnectionCost.Unknown;
        if (info.isRoaming()) {
            cost = NetworkConnectionCost.OverCap;
        } else if (isActive && VERSION.SDK_INT >= 16) {
            cost = RtContext.getConnectivityManager().isActiveNetworkMetered() ? NetworkConnectionCost.Metered : NetworkConnectionCost.Free;
        }
        return JniNetworkParams.createFromNetworkInfo(info, isActive, cost, mgr);
    }

    private boolean equals(NetworkInfo i1, NetworkInfo i2) {
        return i1 != null && i2 != null && i1.getType() == i2.getType() && i1.getSubtype() == i2.getSubtype() && i1.isConnected() == i2.isConnected();
    }

    public JniNetworkParams getActiveConnectionInfo() {
        return convertToJniNetworkParams(RtContext.getConnectivityManager().getActiveNetworkInfo(), true);
    }

    public JniNetworkParams[] getConnectionInfos() {
        NetworkInfo activeNwInfo = RtContext.getConnectivityManager().getActiveNetworkInfo();
        NetworkInfo[] allNwInfo = RtContext.getConnectivityManager().getAllNetworkInfo();
        ArrayList<JniNetworkParams> validNetworks = new ArrayList();
        boolean activeAdded = false;
        for (NetworkInfo nwi : allNwInfo) {
            boolean active = equals(nwi, activeNwInfo);
            if (!activeAdded || !active) {
                JniNetworkParams net_params = convertToJniNetworkParams(nwi, active);
                if (!net_params.error) {
                    validNetworks.add(net_params);
                    if (active) {
                        activeAdded = true;
                    }
                }
            }
        }
        JniNetworkParams[] result = new JniNetworkParams[validNetworks.size()];
        int i = 0;
        Iterator it = validNetworks.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            result[i] = (JniNetworkParams) it.next();
            i = i2;
        }
        return result;
    }

    public String[] pickInterface(int type, String remoteAddr) {
        if (type == 3) {
            return pickMobileInterface(remoteAddr);
        }
        if (type == 2) {
            return pickWifiInterface();
        }
        new StringBuilder("pickInterface: unrecognized interface type: ").append(String.valueOf(type));
        return null;
    }

    public void dropInterface(int type) {
        if (type == 3) {
            dropMobileInterface(false);
        } else if (type == 2) {
            dropWifiInterface();
        } else {
            new StringBuilder("dropInterface: unrecognized interface type: ").append(String.valueOf(type));
        }
    }

    private synchronized String[] pickMobileInterface(String remoteAddr) {
        String[] strArr;
        String[] addrs = null;
        if (this.m_cntMobile == 0) {
            int rc = -1;
            try {
                if (InitStaticProcs()) {
                    rc = ((Integer) startUsingNetworkFeatureMethod.invoke(RtContext.getConnectivityManager(), new Object[]{Integer.valueOf(0), "enableHIPRI"})).intValue();
                }
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
            }
            if (rc == -1) {
                strArr = null;
            } else {
                boolean z;
                startMobileIntfTimer();
                if (rc == 0) {
                    z = true;
                } else {
                    z = false;
                }
                this.m_mobileInitiallyEnabled = z;
                if (!this.m_mobileInitiallyEnabled) {
                    for (int i = 0; i < 30 && addrs == null; i++) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e3) {
                        }
                        addrs = addrListForInterfaceType(3);
                    }
                }
            }
        }
        if (addrs == null) {
            addrs = addrListForInterfaceType(3);
        }
        if (addrs == null) {
            strArr = null;
        } else {
            remoteAddr = remoteAddr.replace("::ffff:", "");
            boolean request = false;
            try {
                if (InitStaticProcs()) {
                    request = ((Boolean) requestRouteToHostMethod.invoke(RtContext.getConnectivityManager(), new Object[]{Integer.valueOf(5), Integer.valueOf(ipv4AddrToInt(remoteAddr))})).booleanValue();
                }
            } catch (IllegalAccessException e4) {
            } catch (InvocationTargetException e5) {
            }
            if (request) {
                this.m_cntMobile++;
                strArr = addrs;
            } else {
                if (this.m_cntMobile == 0) {
                    if (!this.m_mobileInitiallyEnabled) {
                        try {
                            if (InitStaticProcs()) {
                                stopUsingNetworkFeatureMethod.invoke(RtContext.getConnectivityManager(), new Object[]{Integer.valueOf(0), "enableHIPRI"});
                            }
                        } catch (IllegalAccessException e6) {
                        } catch (InvocationTargetException e7) {
                        }
                    }
                    stopMobileIntfTimer();
                }
                strArr = null;
            }
        }
        return strArr;
    }

    private String[] pickWifiInterface() {
        return RtContext.getWifiManager().isWifiEnabled() ? addrListForInterfaceType(2) : null;
    }

    private synchronized void dropMobileInterface(boolean doStop) {
        int i = this.m_cntMobile - 1;
        this.m_cntMobile = i;
        if (i <= 0 || doStop) {
            this.m_cntMobile = 0;
            stopMobileIntfTimer();
            if (!this.m_mobileInitiallyEnabled) {
                try {
                    if (InitStaticProcs()) {
                        stopUsingNetworkFeatureMethod.invoke(RtContext.getConnectivityManager(), new Object[]{Integer.valueOf(0), "enableHIPRI"});
                    }
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e2) {
                }
            }
        }
    }

    private void dropWifiInterface() {
    }

    public void finish() {
        dropMobileInterface(true);
    }

    private void startMobileIntfTimer() {
        if (this.m_timer == null) {
            this.m_timer = new Timer("hipriKeep");
            this.m_timer.schedule(new TimerTask() {
                public void run() {
                    try {
                        if (NetInterfacePreLollipop.InitStaticProcs()) {
                            NetInterfacePreLollipop.startUsingNetworkFeatureMethod.invoke(RtContext.getConnectivityManager(), new Object[]{Integer.valueOf(0), "enableHIPRI"});
                        }
                    } catch (IllegalAccessException e) {
                    } catch (InvocationTargetException e2) {
                    }
                }
            }, 1000, 15000);
        }
    }

    private void stopMobileIntfTimer() {
        if (this.m_timer != null) {
            this.m_timer.cancel();
            this.m_timer = null;
        }
    }

    private int ipv4AddrToInt(String ipv4) {
        int addrInt = 0;
        for (String part : ipv4.split(Pattern.quote("."))) {
            addrInt = (addrInt << 8) | Integer.parseInt(part);
        }
        return Integer.reverseBytes(addrInt);
    }

    private String[] addrListForInterfaceType(int type) {
        if (type != 2 && type != 3) {
            return null;
        }
        String name = type == 2 ? "wlan" : "rmnet";
        List<String> addrs = new ArrayList();
        try {
            Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
            while (ni.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) ni.nextElement();
                if (intf.isUp() && intf.getName().startsWith(name)) {
                    new StringBuilder("addrListForInterfaceType IP: ").append(intf.toString());
                    Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                    while (enumIpAddr.hasMoreElements()) {
                        InetAddress addr = (InetAddress) enumIpAddr.nextElement();
                        if (!addr.isLoopbackAddress()) {
                            new StringBuilder("addrListForInterfacetype IP addr: ").append(addr.toString());
                            Matcher m = m_ipAddrPat.matcher(addr.toString());
                            if (m.matches()) {
                                new StringBuilder("addrListForInterfaceType IP addr stored: ").append(m.group(1));
                                addrs.add(m.group(1));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        if (addrs.size() > 0) {
            return (String[]) addrs.toArray(new String[addrs.size()]);
        }
        return null;
    }
}
