package jp.naver.android.npush.common;

import java.util.concurrent.atomic.AtomicInteger;
import jp.naver.android.npush.network.NPushNetworkController;

public class NPushConstant {
    protected static ClientType CLIENT_TYPE = null;
    public static final String NNI = "nni";
    public static final String PACKAGE_TYPE = "jp.naver.android.npush";
    public static final String SERVICE_CLASS = "jp.naver.android.npush.service.NPushMessageService";
    public static final String TAG = "NPush";
    public static final boolean USE_ENCRYPTION = true;
    public static final int VERSION_CODE = 7;
    public static final String VERSION_NAME = "7.2.5";
    private static String servicePackageName = "";
    public static AtomicInteger wakeLockRefCount = new AtomicInteger(0);

    public enum ClientType {
        REAL("real"),
        REAL_DEBUG("real_debug"),
        BETA("beta");
        
        private String mName;

        private ClientType(String str) {
            this.mName = str;
        }

        public final String toString() {
            return this.mName;
        }

        public static ClientType fromString(String str) {
            if (str != null) {
                for (ClientType clientType : values()) {
                    if (str.equalsIgnoreCase(clientType.mName)) {
                        return clientType;
                    }
                }
            }
            return null;
        }
    }

    public static jp.naver.android.npush.common.NPushConstant.ClientType getClientType(android.content.Context r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.android.npush.common.NPushConstant.getClientType(android.content.Context):jp.naver.android.npush.common.NPushConstant$ClientType. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = CLIENT_TYPE;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r3 = CLIENT_TYPE;
        return r3;
    L_0x0007:
        r0 = jp.naver.android.npush.common.NPushContentProvider.getDefaultSharedPreferences(r3);	 Catch:{ Exception -> 0x0022 }
        r1 = "nni";	 Catch:{ Exception -> 0x0022 }
        r2 = 0;	 Catch:{ Exception -> 0x0022 }
        r0 = r0.getString(r1, r2);	 Catch:{ Exception -> 0x0022 }
        if (r0 != 0) goto L_0x001b;	 Catch:{ Exception -> 0x0022 }
    L_0x0014:
        r3 = jp.naver.android.npush.register.NPushPreferences.loadLocalClientType(r3);	 Catch:{ Exception -> 0x0022 }
        CLIENT_TYPE = r3;	 Catch:{ Exception -> 0x0022 }
        goto L_0x0026;	 Catch:{ Exception -> 0x0022 }
    L_0x001b:
        r3 = jp.naver.android.npush.common.NPushConstant.ClientType.fromString(r0);	 Catch:{ Exception -> 0x0022 }
        CLIENT_TYPE = r3;	 Catch:{ Exception -> 0x0022 }
        goto L_0x0026;
    L_0x0022:
        r3 = jp.naver.android.npush.common.NPushConstant.ClientType.REAL;
        CLIENT_TYPE = r3;
    L_0x0026:
        r3 = CLIENT_TYPE;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.android.npush.common.NPushConstant.getClientType(android.content.Context):jp.naver.android.npush.common.NPushConstant$ClientType");
    }

    public static ClientType setClientType(ClientType clientType) {
        ClientType clientType2 = CLIENT_TYPE;
        if (clientType != null) {
            CLIENT_TYPE = clientType;
        }
        return clientType2;
    }

    public static void initClientType() {
        CLIENT_TYPE = null;
    }

    public static String getServicePackageName() {
        if (servicePackageName == null || servicePackageName.length() == 0) {
            servicePackageName = NPushNetworkController.getInstance().getServiceContext().getPackageName();
        }
        return servicePackageName;
    }
}
