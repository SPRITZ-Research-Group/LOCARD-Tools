package com.microsoft.media;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import junit.framework.Assert;

public class NetworkPal {
    private static final String TAG = "NetworkPal";
    private static ConnectivityManager m_conMan = null;

    public static boolean initialize(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService("connectivity");
        m_conMan = connectivityManager;
        return connectivityManager != null;
    }

    private static int getNetworkType() {
        Assert.assertNotNull(m_conMan);
        NetworkInfo netInfo = m_conMan.getActiveNetworkInfo();
        if (netInfo != null) {
            return netInfo.getType();
        }
        return -1;
    }

    public static ifaddrs[] getNetworkInterfaceAddresses() {
        ifaddrs[] result = null;
        int index = -1;
        ArrayList<ifaddrs> resultArrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> deviceIntfs = NetworkInterface.getNetworkInterfaces();
            while (deviceIntfs != null && deviceIntfs.hasMoreElements()) {
                NetworkInterface nwIntf = (NetworkInterface) deviceIntfs.nextElement();
                index++;
                try {
                    if (!nwIntf.isLoopback() && nwIntf.isUp()) {
                        for (InterfaceAddress intfAddr : nwIntf.getInterfaceAddresses()) {
                            InetAddress inetAddress = intfAddr.getAddress();
                            if (!(inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress())) {
                                if (!(inetAddress instanceof Inet6Address) || !((Inet6Address) inetAddress).isIPv4CompatibleAddress()) {
                                    resultArrayList.add(new ifaddrs(nwIntf.getName(), 0, inetAddress, intfAddr.getNetworkPrefixLength(), index, getNetworkType()));
                                    if (RtcPalConfig.isLogcatEnabled()) {
                                        String.format("addr=%s, siteLocal:%b, anyLocal:%b", new Object[]{addr.toString(), Boolean.valueOf(inetAddress.isSiteLocalAddress()), Boolean.valueOf(inetAddress.isLoopbackAddress())});
                                    }
                                }
                            }
                        }
                    }
                } catch (SocketException e) {
                }
            }
            return (ifaddrs[]) resultArrayList.toArray(new ifaddrs[resultArrayList.size()]);
        } catch (SocketException e2) {
            RtcPalConfig.isLogcatEnabled();
            return result;
        }
    }
}
