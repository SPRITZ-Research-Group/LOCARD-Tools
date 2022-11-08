package jp.naver.android.npush.network;

import android.content.Context;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import jp.naver.android.npush.common.Logger;
import jp.naver.android.npush.common.NPushConstant;

public class NPushNetworkConfig {
    static final int CHINA_MCC = 460;
    public static final int INT_CODE_ERRORLOGIN = -1;
    public static final int INT_CODE_SOCKETCLOSED = -2;
    static final int KOREA_MCC = 450;
    public static final int NETWORK_STATUS_CONNECTED_LOOKUP = 8;
    public static final int NETWORK_STATUS_CONNECTED_NPUSH = 2;
    public static final int NETWORK_STATUS_CONNECTED_SUBSCRIBE = 7;
    public static final int NETWORK_STATUS_CONNECTIONPENDING = 5;
    public static final int NETWORK_STATUS_DISCONNECTED = 4;
    public static final int NETWORK_STATUS_GRACEFULLY_CLOSED = 3;
    public static final int NETWORK_STATUS_READY = 0;
    public static final int NETWORK_STATUS_REFUSE = 6;
    public static final int NETWORK_STATUS_SESSION_EXCHANGE = 9;
    public static final int NETWORK_STATUS_SOCKET_EXCEPTION = -1;
    public static final int REQ_API_CALL = 3;
    public static final int REQ_LOOKUP_CONNECT = 0;
    public static final int REQ_NPUSH_CONNECT = 1;
    public static final int RES_CODE_SUCCESS = 0;
    static InetSocketAddress lookupServerAddress;
    static InetSocketAddress nPushServerAddress;

    public static void initServerAddress() {
        lookupServerAddress = null;
        nPushServerAddress = null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static InetSocketAddress getLookupServerHost() {
        Context serviceContext = NPushNetworkController.getInstance().getServiceContext();
        if (lookupServerAddress == null) {
            String targetId;
            InetSocketAddress inetSocketAddress;
            switch (NPushConstant.getClientType(serviceContext)) {
                case BETA:
                    try {
                        targetId = NPushConnectedData.getInstance().getTargetId();
                        if (targetId != null && targetId.length() > 37 && Integer.parseInt(targetId.substring(0, 3)) == CHINA_MCC) {
                            lookupServerAddress = new InetSocketAddress("beta.lookup460.nniline.naver.com", 10103);
                        }
                        if (lookupServerAddress == null) {
                            inetSocketAddress = new InetSocketAddress("beta.lookup.nniline.naver.com", 10103);
                            lookupServerAddress = inetSocketAddress;
                            break;
                        }
                        break;
                    } catch (Throwable th) {
                        if (lookupServerAddress == null) {
                            lookupServerAddress = new InetSocketAddress("beta.lookup.nniline.naver.com", 10103);
                        }
                    }
                    break;
                case REAL:
                case REAL_DEBUG:
                    try {
                        targetId = NPushConnectedData.getInstance().getTargetId();
                        if (targetId != null && targetId.length() > 37 && Integer.parseInt(targetId.substring(0, 3)) == CHINA_MCC) {
                            lookupServerAddress = new InetSocketAddress("lookup460.nniline.naver.com", 10301);
                        }
                        if (lookupServerAddress == null) {
                            inetSocketAddress = new InetSocketAddress("lookup.nniline.naver.com", 10301);
                            lookupServerAddress = inetSocketAddress;
                            break;
                        }
                        break;
                    } catch (Throwable th2) {
                        if (lookupServerAddress == null) {
                            lookupServerAddress = new InetSocketAddress("lookup.nniline.naver.com", 10301);
                        }
                    }
                    break;
            }
        }
        return lookupServerAddress;
    }

    public static InetSocketAddress getServerHost() {
        if (nPushServerAddress == null) {
            byte[] connectIpAddress = NPushConnectedData.getInstance().getConnectIpAddress();
            int connectPort = NPushConnectedData.getInstance().getConnectPort();
            if (NPushNetworkController.getInstance().mRetryCount.get() == NPushNetworkController.RECONNECT_COUNT) {
                connectIpAddress = NPushConnectedData.getInstance().getAlternativeConnectIpAddress();
                connectPort = NPushConnectedData.getInstance().getAlternativeConnectPort();
                NPushConnectedData.getInstance().setAlternativeConnect(true);
                Logger.d("Retry Count over, Therefore Alternative IP/Port Setting");
                if (connectPort == 0) {
                    return null;
                }
            }
            try {
                nPushServerAddress = new InetSocketAddress(InetAddress.getByAddress(connectIpAddress), connectPort);
            } catch (Throwable e) {
                Logger.e(e);
            }
        }
        return nPushServerAddress;
    }
}
