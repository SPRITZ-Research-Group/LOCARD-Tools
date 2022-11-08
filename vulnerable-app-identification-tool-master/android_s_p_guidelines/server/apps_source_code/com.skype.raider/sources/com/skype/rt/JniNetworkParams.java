package com.skype.rt;

import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class JniNetworkParams {
    public boolean active;
    public int category;
    public int cost;
    public boolean error;
    public String mcc;
    public String mnc;
    public int roaming;
    public int state;
    public int subType;
    public int type;

    public enum NetworkCategory {
        Unknown(0),
        Public(1),
        Private(2),
        DomainAuthenticated(3);
        
        public final int val;

        private NetworkCategory(int val) {
            this.val = val;
        }
    }

    public enum NetworkConnectionCost {
        Unknown(0),
        Free(1),
        Metered(2),
        OverCap(3);
        
        public final int val;

        private NetworkConnectionCost(int val) {
            this.val = val;
        }
    }

    public enum NetworkConnectionState {
        Unknown(0),
        Disconnected(1),
        NoTraffic(2),
        SubNet(3),
        Internet(4);
        
        public final int val;

        private NetworkConnectionState(int val) {
            this.val = val;
        }
    }

    public enum NetworkConnectionSubType {
        Unknown(0),
        Cellular_2G(1),
        Cellular_3G(2),
        Cellular_35G(3),
        Cellular_4G(4);
        
        public final int val;

        private NetworkConnectionSubType(int val) {
            this.val = val;
        }
    }

    public enum NetworkConnectionType {
        Unknown(0),
        Ethernet(1),
        WiFi(2),
        Mobile(3),
        Bluetooth(4),
        WiMax(5),
        PPP(6);
        
        public final int val;

        private NetworkConnectionType(int val) {
            this.val = val;
        }
    }

    public enum NetworkRoamingState {
        Unknown(0),
        Roaming(1),
        HomeNetwork(2);
        
        public final int val;

        private NetworkRoamingState(int val) {
            this.val = val;
        }
    }

    JniNetworkParams(boolean error, boolean active, NetworkConnectionState state, NetworkConnectionType type, NetworkConnectionSubType subType, NetworkConnectionCost cost, NetworkCategory category, NetworkRoamingState roaming, String mcc, String mnc) {
        this.error = error;
        this.active = active;
        this.state = state.val;
        this.type = type.val;
        this.subType = subType.val;
        this.cost = cost.val;
        this.category = category.val;
        this.roaming = roaming.val;
        this.mcc = mcc;
        this.mnc = mnc;
    }

    JniNetworkParams(boolean error) {
        this(error, false, NetworkConnectionState.Unknown, NetworkConnectionType.Unknown, NetworkConnectionSubType.Unknown, NetworkConnectionCost.Unknown, NetworkCategory.Unknown, NetworkRoamingState.Unknown, "", "");
    }

    public static JniNetworkParams createErroneous() {
        return new JniNetworkParams(true);
    }

    public static JniNetworkParams createDummy() {
        return new JniNetworkParams(false);
    }

    public static JniNetworkParams createFromNetworkInfo(NetworkInfo info, boolean active, NetworkConnectionCost cost, TelephonyManager mgr) {
        if (info == null) {
            return createErroneous();
        }
        NetworkConnectionType type;
        NetworkConnectionState state = NetworkConnectionState.Unknown;
        NetworkConnectionType networkConnectionType = NetworkConnectionType.Unknown;
        NetworkConnectionSubType subType = NetworkConnectionSubType.Unknown;
        NetworkCategory category = NetworkCategory.Unknown;
        NetworkRoamingState roaming = info.isRoaming() ? NetworkRoamingState.Roaming : NetworkRoamingState.HomeNetwork;
        String mcc = "";
        String mnc = "";
        if (info.isConnected()) {
            state = NetworkConnectionState.Internet;
        } else if (!info.isAvailable()) {
            state = NetworkConnectionState.Disconnected;
        }
        switch (info.getType()) {
            case 0:
                type = NetworkConnectionType.Mobile;
                break;
            case 1:
                type = NetworkConnectionType.WiFi;
                break;
            case 6:
                type = NetworkConnectionType.WiMax;
                break;
            case 7:
                type = NetworkConnectionType.Bluetooth;
                break;
            case 9:
                type = NetworkConnectionType.Ethernet;
                break;
            default:
                return createErroneous();
        }
        if (type == NetworkConnectionType.Mobile) {
            String mccMnc = mgr.getSimOperator();
            if (mccMnc != null && mccMnc.length() >= 5) {
                mcc = mccMnc.substring(0, 3);
                mnc = mccMnc.substring(3, mccMnc.length());
            }
            switch (info.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    subType = NetworkConnectionSubType.Cellular_2G;
                    break;
                case 3:
                case 5:
                case 6:
                case 12:
                case 14:
                    subType = NetworkConnectionSubType.Cellular_3G;
                    break;
                case 8:
                case 9:
                case 10:
                case 15:
                    subType = NetworkConnectionSubType.Cellular_35G;
                    break;
                case 13:
                    subType = NetworkConnectionSubType.Cellular_4G;
                    break;
            }
        }
        return new JniNetworkParams(false, active, state, type, subType, cost, category, roaming, mcc, mnc);
    }
}
