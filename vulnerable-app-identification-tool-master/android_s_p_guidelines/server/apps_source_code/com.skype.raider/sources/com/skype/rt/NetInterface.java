package com.skype.rt;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest.Builder;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.skype.rt.JniNetworkParams.NetworkConnectionCost;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;

@TargetApi(21)
public class NetInterface extends RtContext implements NetIntf {
    private static final String TAG = "rt.ni::";
    private HashMap<Integer, NetworkCallback> callbackMap = new HashMap();
    private HashMap<Integer, Integer> typeToTransportMap = new HashMap();

    class NetworkCallback extends android.net.ConnectivityManager.NetworkCallback {
        boolean initialConnectionComplete = false;
        Network network = null;
        int usageCounter = 0;

        NetworkCallback() {
        }

        public void onLost(Network lostnetwork) {
            super.onLost(lostnetwork);
            synchronized (this) {
                this.network = null;
                notifyAll();
            }
        }

        public void onAvailable(Network newnetwork) {
            super.onAvailable(newnetwork);
            synchronized (this) {
                this.network = newnetwork;
                this.initialConnectionComplete = true;
                notifyAll();
            }
        }

        public void onLosing(Network network, int maxMsToLive) {
            super.onLosing(network, maxMsToLive);
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties);
        }
    }

    public NetInterface() {
        this.typeToTransportMap.put(Integer.valueOf(3), Integer.valueOf(0));
        this.typeToTransportMap.put(Integer.valueOf(2), Integer.valueOf(1));
        this.typeToTransportMap.put(Integer.valueOf(1), Integer.valueOf(3));
    }

    private JniNetworkParams convertToJniNetworkParams(NetworkInfo info, NetworkCapabilities caps, boolean isActive) {
        NetworkConnectionCost cost;
        TelephonyManager mgr = (TelephonyManager) RtContext.getContext().getSystemService("phone");
        if (info.isRoaming()) {
            cost = NetworkConnectionCost.OverCap;
        } else if (caps == null || caps.hasCapability(11)) {
            cost = NetworkConnectionCost.Free;
        } else {
            cost = NetworkConnectionCost.Metered;
        }
        return JniNetworkParams.createFromNetworkInfo(info, isActive, cost, mgr);
    }

    private boolean equals(NetworkInfo i1, NetworkInfo i2) {
        return i1 != null && i2 != null && i1.getType() == i2.getType() && i1.getSubtype() == i2.getSubtype() && i1.isConnected() == i2.isConnected();
    }

    public JniNetworkParams getActiveConnectionInfo() {
        ConnectivityManager cm = RtContext.getConnectivityManager();
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return JniNetworkParams.createErroneous();
        }
        for (Network nw : cm.getAllNetworks()) {
            if (equals(cm.getNetworkInfo(nw), ni)) {
                return convertToJniNetworkParams(cm.getNetworkInfo(nw), cm.getNetworkCapabilities(nw), true);
            }
        }
        return JniNetworkParams.createErroneous();
    }

    public JniNetworkParams[] getConnectionInfos() {
        ConnectivityManager cm = RtContext.getConnectivityManager();
        NetworkInfo activeNwInfo = cm.getActiveNetworkInfo();
        NetworkInfo[] allNwInfo = cm.getAllNetworkInfo();
        Network[] allNw = cm.getAllNetworks();
        ArrayList<JniNetworkParams> validNetworks = new ArrayList();
        int length = allNwInfo.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                break;
            }
            NetworkInfo nwi = allNwInfo[i2];
            NetworkCapabilities nwc = null;
            for (Network nw : allNw) {
                if (equals(cm.getNetworkInfo(nw), nwi)) {
                    nwc = cm.getNetworkCapabilities(nw);
                }
            }
            JniNetworkParams net_params = convertToJniNetworkParams(nwi, nwc, equals(nwi, activeNwInfo));
            if (!net_params.error) {
                validNetworks.add(net_params);
            }
            i = i2 + 1;
        }
        JniNetworkParams[] result = new JniNetworkParams[validNetworks.size()];
        int i3 = 0;
        Iterator it = validNetworks.iterator();
        while (it.hasNext()) {
            int i4 = i3 + 1;
            result[i3] = (JniNetworkParams) it.next();
            i3 = i4;
        }
        return result;
    }

    public String[] pickInterface(int type, String remoteAddr) {
        NetworkCallback callback;
        new StringBuilder("pickInterface() start type ").append(String.valueOf(type));
        Assert.assertTrue("Unknown interface type", this.typeToTransportMap.containsKey(Integer.valueOf(type)));
        ConnectivityManager connmanager = RtContext.getConnectivityManager();
        List<LinkAddress> addrlist = null;
        synchronized (this) {
            callback = (NetworkCallback) this.callbackMap.get(Integer.valueOf(type));
            if (callback == null) {
                callback = new NetworkCallback();
                this.callbackMap.put(Integer.valueOf(type), callback);
                Builder builder = new Builder();
                builder.addCapability(12);
                builder.addCapability(13);
                builder.addTransportType(((Integer) this.typeToTransportMap.get(Integer.valueOf(type))).intValue());
                connmanager.requestNetwork(builder.build(), callback);
            }
            callback.usageCounter++;
        }
        synchronized (callback) {
            if (callback.network == null && !callback.initialConnectionComplete) {
                try {
                    callback.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (callback.network != null) {
                if (VERSION.SDK_INT >= 23 && type == 3) {
                    connmanager.bindProcessToNetwork(callback.network);
                }
                LinkProperties lprop = connmanager.getLinkProperties(callback.network);
                if (lprop != null) {
                    addrlist = lprop.getLinkAddresses();
                }
            }
        }
        new StringBuilder("pickInterface() end type ").append(String.valueOf(type));
        if (addrlist == null || addrlist.size() == 0) {
            dropInterface(type);
            return null;
        }
        ArrayList<String> addrStrings = new ArrayList();
        for (LinkAddress addr : addrlist) {
            addrStrings.add(addr.getAddress().getHostAddress());
        }
        String[] resp = new String[addrStrings.size()];
        addrStrings.toArray(resp);
        return resp;
    }

    public synchronized void dropInterface(int type) {
        new StringBuilder("dropInterface() type ").append(String.valueOf(type));
        Assert.assertTrue("Unknown interface type", this.typeToTransportMap.containsKey(Integer.valueOf(type)));
        if (this.callbackMap.containsKey(Integer.valueOf(type))) {
            NetworkCallback networkCallback = (NetworkCallback) this.callbackMap.get(Integer.valueOf(type));
            int i = networkCallback.usageCounter - 1;
            networkCallback.usageCounter = i;
            if (i == 0) {
                ConnectivityManager connmanager = RtContext.getConnectivityManager();
                android.net.ConnectivityManager.NetworkCallback callback = (android.net.ConnectivityManager.NetworkCallback) this.callbackMap.get(Integer.valueOf(type));
                connmanager.unregisterNetworkCallback(callback);
                this.callbackMap.remove(Integer.valueOf(type));
                if (VERSION.SDK_INT >= 23 && type == 3) {
                    connmanager.bindProcessToNetwork(null);
                }
                synchronized (callback) {
                    callback.notifyAll();
                }
            }
        }
    }

    public synchronized void finish() {
        ConnectivityManager connmanager = RtContext.getConnectivityManager();
        for (NetworkCallback callback : this.callbackMap.values()) {
            connmanager.unregisterNetworkCallback(callback);
        }
        this.callbackMap.clear();
    }
}
