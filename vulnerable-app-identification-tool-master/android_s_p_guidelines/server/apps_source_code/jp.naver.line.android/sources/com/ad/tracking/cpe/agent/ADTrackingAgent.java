package com.ad.tracking.cpe.agent;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ad.tracking.cpe.agent.AdvertisingIdClient.AdInfo;

public class ADTrackingAgent {
    private static String adId = "";
    private static String appPackageName = "com.vitiglobal.cashtree";
    private static String imei = "";
    private static Context mContext = null;
    private static String merchantId = "";
    private static String simSerial = "";

    public static synchronized void completedWithMid(Context context, String str, Boolean bool) {
        synchronized (ADTrackingAgent.class) {
            completed(context, str, "", bool);
        }
    }

    public static synchronized void completed(final Context context, final String str, final String str2, final Boolean bool) {
        synchronized (ADTrackingAgent.class) {
            new Thread(new Runnable() {
                public final void run() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.ad.tracking.cpe.agent.ADTrackingAgent.1.run():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                    /*
                    r7 = this;
                    r0 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0239 }
                    r1 = "ADTrackingAgent SDK called : ";	 Catch:{ SQLException -> 0x0239 }
                    r0.<init>(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r4;	 Catch:{ SQLException -> 0x0239 }
                    r0.append(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = " / ";	 Catch:{ SQLException -> 0x0239 }
                    r0.append(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r5;	 Catch:{ SQLException -> 0x0239 }
                    r0.append(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = " / ";	 Catch:{ SQLException -> 0x0239 }
                    r0.append(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r6;	 Catch:{ SQLException -> 0x0239 }
                    r0.append(r1);	 Catch:{ SQLException -> 0x0239 }
                    r0 = r0.toString();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.v(r0);	 Catch:{ SQLException -> 0x0239 }
                    r0 = r6;	 Catch:{ SQLException -> 0x0239 }
                    r0 = r0.booleanValue();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.isDebug = r0;	 Catch:{ SQLException -> 0x0239 }
                    r0 = r3;	 Catch:{ SQLException -> 0x0239 }
                    if (r0 == 0) goto L_0x0038;	 Catch:{ SQLException -> 0x0239 }
                L_0x0033:
                    r0 = r3;	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTrackingAgent.mContext = r0;	 Catch:{ SQLException -> 0x0239 }
                L_0x0038:
                    r0 = com.ad.tracking.cpe.agent.ADTrackingAgent.mContext;	 Catch:{ SQLException -> 0x0239 }
                    r1 = "SharedPrefKey";	 Catch:{ SQLException -> 0x0239 }
                    r2 = 4;	 Catch:{ SQLException -> 0x0239 }
                    r0 = r0.getSharedPreferences(r1, r2);	 Catch:{ SQLException -> 0x0239 }
                    r1 = "APIRequestSuccess";	 Catch:{ SQLException -> 0x0239 }
                    r2 = 0;	 Catch:{ SQLException -> 0x0239 }
                    r1 = r0.getBoolean(r1, r2);	 Catch:{ SQLException -> 0x0239 }
                    r3 = "IntentRequestSuccess";	 Catch:{ SQLException -> 0x0239 }
                    r3 = r0.getBoolean(r3, r2);	 Catch:{ SQLException -> 0x0239 }
                    if (r1 == 0) goto L_0x005a;	 Catch:{ SQLException -> 0x0239 }
                L_0x0052:
                    if (r3 == 0) goto L_0x005a;	 Catch:{ SQLException -> 0x0239 }
                L_0x0054:
                    r0 = "You are already complete";	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.v(r0);	 Catch:{ SQLException -> 0x0239 }
                    return;	 Catch:{ SQLException -> 0x0239 }
                L_0x005a:
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.mContext;	 Catch:{ SQLException -> 0x0239 }
                    r4 = com.ad.tracking.cpe.agent.ADTrackingAgent.appPackageName;	 Catch:{ SQLException -> 0x0239 }
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.checkApkExist(r1, r4);	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x006e;	 Catch:{ SQLException -> 0x0239 }
                L_0x0068:
                    r0 = "[Error] ADTrackingAgent app not found";	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.e(r0);	 Catch:{ SQLException -> 0x0239 }
                    return;	 Catch:{ SQLException -> 0x0239 }
                L_0x006e:
                    r1 = r4;	 Catch:{ SQLException -> 0x0239 }
                    r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x008e;	 Catch:{ SQLException -> 0x0239 }
                L_0x0076:
                    r1 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0239 }
                    r4 = "Merchant ID : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r4);	 Catch:{ SQLException -> 0x0239 }
                    r4 = r4;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r4);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.toString();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.v(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r4;	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId = r1;	 Catch:{ SQLException -> 0x0239 }
                L_0x008e:
                    r1 = r5;	 Catch:{ SQLException -> 0x0239 }
                    r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x00b1;	 Catch:{ SQLException -> 0x0239 }
                L_0x0096:
                    r1 = r5;	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTrackingAgent.adId = r1;	 Catch:{ SQLException -> 0x0239 }
                    r1 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0239 }
                    r4 = "AD ID [Parameter] : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r4);	 Catch:{ SQLException -> 0x0239 }
                    r4 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r4);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.toString();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.v(r1);	 Catch:{ SQLException -> 0x0239 }
                    goto L_0x00d1;	 Catch:{ SQLException -> 0x0239 }
                L_0x00b1:
                    r1 = "ReferrerId";	 Catch:{ SQLException -> 0x0239 }
                    r4 = "";	 Catch:{ SQLException -> 0x0239 }
                    r1 = r0.getString(r1, r4);	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTrackingAgent.adId = r1;	 Catch:{ SQLException -> 0x0239 }
                    r1 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0239 }
                    r4 = "AD ID [Referrer] : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r4);	 Catch:{ SQLException -> 0x0239 }
                    r4 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r4);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.toString();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.v(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x00d1:
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId;	 Catch:{ SQLException -> 0x0239 }
                    r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ SQLException -> 0x0239 }
                    r4 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ SQLException -> 0x0239 }
                    r5 = 1;	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x00e6;	 Catch:{ SQLException -> 0x0239 }
                L_0x00e4:
                    if (r4 == 0) goto L_0x00fa;	 Catch:{ SQLException -> 0x0239 }
                L_0x00e6:
                    r4 = "[Error] Cannot find a request parameter : %s id";	 Catch:{ SQLException -> 0x0239 }
                    r6 = new java.lang.Object[r5];	 Catch:{ SQLException -> 0x0239 }
                    if (r1 == 0) goto L_0x00ef;	 Catch:{ SQLException -> 0x0239 }
                L_0x00ec:
                    r1 = "merchant";	 Catch:{ SQLException -> 0x0239 }
                    goto L_0x00f1;	 Catch:{ SQLException -> 0x0239 }
                L_0x00ef:
                    r1 = "ad[referrer]";	 Catch:{ SQLException -> 0x0239 }
                L_0x00f1:
                    r6[r2] = r1;	 Catch:{ SQLException -> 0x0239 }
                    r1 = java.lang.String.format(r4, r6);	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.e(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x00fa:
                    r1 = new com.ad.tracking.cpe.agent.ADTrackingRecordDB;	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.mContext;	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r2);	 Catch:{ SQLException -> 0x0239 }
                    r1.open();	 Catch:{ SQLException -> 0x0239 }
                    r2 = r1.isComplete();	 Catch:{ SQLException -> 0x0239 }
                    r1.showDB();	 Catch:{ SQLException -> 0x0239 }
                    r1.close();	 Catch:{ SQLException -> 0x0239 }
                    if (r2 == 0) goto L_0x0117;	 Catch:{ SQLException -> 0x0239 }
                L_0x0112:
                    r1 = "[Warning] You are already participated.";	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.e(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x0117:
                    r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ SQLException -> 0x0239 }
                    r2 = 23;	 Catch:{ SQLException -> 0x0239 }
                    if (r1 < r2) goto L_0x0128;	 Catch:{ SQLException -> 0x0239 }
                L_0x011d:
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.checkPermission_GRANTED();	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x0128;	 Catch:{ SQLException -> 0x0239 }
                L_0x0123:
                    r1 = "[Error] You did't register permission";	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.e(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x0128:
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.mContext;	 Catch:{ Exception -> 0x013f }
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.getSimId(r1);	 Catch:{ Exception -> 0x013f }
                    com.ad.tracking.cpe.agent.ADTrackingAgent.simSerial = r1;	 Catch:{ Exception -> 0x013f }
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.mContext;	 Catch:{ Exception -> 0x013f }
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.getIMEI(r1);	 Catch:{ Exception -> 0x013f }
                    com.ad.tracking.cpe.agent.ADTrackingAgent.imei = r1;	 Catch:{ Exception -> 0x013f }
                    goto L_0x0144;
                L_0x013f:
                    r1 = "[Error] You did't register permission";	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.e(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x0144:
                    r1 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0239 }
                    r2 = "simSerial : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.simSerial;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = "\nimei : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.imei;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = "\nadId : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = "\nmerchantId : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.toString();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.v(r1);	 Catch:{ SQLException -> 0x0239 }
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.simSerial;	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.isEmpty();	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x019b;	 Catch:{ SQLException -> 0x0239 }
                L_0x0187:
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.imei;	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.isEmpty();	 Catch:{ SQLException -> 0x0239 }
                    if (r1 != 0) goto L_0x019b;	 Catch:{ SQLException -> 0x0239 }
                L_0x0191:
                    r1 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.isEmpty();	 Catch:{ SQLException -> 0x0239 }
                    if (r1 == 0) goto L_0x01d4;	 Catch:{ SQLException -> 0x0239 }
                L_0x019b:
                    r1 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0239 }
                    r2 = "[Error] Abnormal request data : \nsimSerial : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.simSerial;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = "\nimei : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.imei;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = "\nreferrer aid : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = "\nmerchantId : ";	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId;	 Catch:{ SQLException -> 0x0239 }
                    r1.append(r2);	 Catch:{ SQLException -> 0x0239 }
                    r1 = r1.toString();	 Catch:{ SQLException -> 0x0239 }
                    com.ad.tracking.cpe.agent.ADTLogUtil.e(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x01d4:
                    if (r3 != 0) goto L_0x020f;	 Catch:{ SQLException -> 0x0239 }
                L_0x01d6:
                    r1 = new android.content.Intent;	 Catch:{ SQLException -> 0x0239 }
                    r2 = "com.vitiglobal.cashtree.CPE_SDK";	 Catch:{ SQLException -> 0x0239 }
                    r1.<init>(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ SQLException -> 0x0239 }
                    if (r2 != 0) goto L_0x01f0;	 Catch:{ SQLException -> 0x0239 }
                L_0x01e7:
                    r2 = "aid";	 Catch:{ SQLException -> 0x0239 }
                    r3 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r1.putExtra(r2, r3);	 Catch:{ SQLException -> 0x0239 }
                L_0x01f0:
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId;	 Catch:{ SQLException -> 0x0239 }
                    r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ SQLException -> 0x0239 }
                    if (r2 != 0) goto L_0x0203;	 Catch:{ SQLException -> 0x0239 }
                L_0x01fa:
                    r2 = "mid";	 Catch:{ SQLException -> 0x0239 }
                    r3 = com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId;	 Catch:{ SQLException -> 0x0239 }
                    r1.putExtra(r2, r3);	 Catch:{ SQLException -> 0x0239 }
                L_0x0203:
                    r2 = 32;	 Catch:{ SQLException -> 0x0239 }
                    r1.addFlags(r2);	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.mContext;	 Catch:{ SQLException -> 0x0239 }
                    r2.sendBroadcast(r1);	 Catch:{ SQLException -> 0x0239 }
                L_0x020f:
                    r0 = r0.edit();	 Catch:{ SQLException -> 0x0239 }
                    r1 = "LastReqIsDebug";	 Catch:{ SQLException -> 0x0239 }
                    r2 = r6;	 Catch:{ SQLException -> 0x0239 }
                    r2 = r2.booleanValue();	 Catch:{ SQLException -> 0x0239 }
                    r0.putBoolean(r1, r2);	 Catch:{ SQLException -> 0x0239 }
                    r1 = "LastReqMerchantId";	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.merchantId;	 Catch:{ SQLException -> 0x0239 }
                    r0.putString(r1, r2);	 Catch:{ SQLException -> 0x0239 }
                    r1 = "LastReqAdId";	 Catch:{ SQLException -> 0x0239 }
                    r2 = com.ad.tracking.cpe.agent.ADTrackingAgent.adId;	 Catch:{ SQLException -> 0x0239 }
                    r0.putString(r1, r2);	 Catch:{ SQLException -> 0x0239 }
                    r1 = "LastReqIsCalled";	 Catch:{ SQLException -> 0x0239 }
                    r0.putBoolean(r1, r5);	 Catch:{ SQLException -> 0x0239 }
                    r0.apply();	 Catch:{ SQLException -> 0x0239 }
                    return;
                L_0x0239:
                    r0 = move-exception;
                    r0.printStackTrace();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.ad.tracking.cpe.agent.ADTrackingAgent.1.run():void");
                }
            }).start();
        }
    }

    public static void resetData(Context context, boolean z) {
        Editor edit = context.getSharedPreferences(ADTConstants.SHARED_PREF_KEY, 4).edit();
        if (z) {
            edit.putBoolean(ADTConstants.API_REQUEST_SUCCESS, false);
            edit.putBoolean(ADTConstants.INTENT_REQUEST_SUCCESS, false);
        }
        edit.putBoolean(ADTConstants.LAST_REQ_SHOULD_CHECK, false);
        edit.putBoolean(ADTConstants.LAST_REQ_IS_DEBUG, false);
        edit.putString(ADTConstants.LAST_REQ_MERCHANT_ID, "");
        edit.putString(ADTConstants.LAST_REQ_AD_ID, "");
        edit.apply();
    }

    private static String getGoogleAdId(Context context) {
        AdInfo advertisingIdInfo;
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (Exception e) {
            e.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder("[Error] AdvertisingIdClient  Exception:");
            stringBuilder.append(e.getMessage());
            ADTLogUtil.e(stringBuilder.toString());
            advertisingIdInfo = null;
        }
        if (advertisingIdInfo == null) {
            return "";
        }
        ADTLogUtil.v("Success Google Ad Id  ");
        return advertisingIdInfo.getId();
    }

    private static TelephonyManager getTelephone(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static String getIMEI(Context context) {
        TelephonyManager telephone = getTelephone(context);
        return (telephone == null || TextUtils.isEmpty(telephone.getDeviceId())) ? "" : telephone.getDeviceId();
    }

    private static String getSimId(Context context) {
        TelephonyManager telephone = getTelephone(context);
        return (telephone == null || TextUtils.isEmpty(telephone.getSimSerialNumber())) ? "" : telephone.getSimSerialNumber();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean checkApkExist(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getApplicationInfo(str, 8192) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            StringBuilder stringBuilder = new StringBuilder("[Error] NameNotFoundException :");
            stringBuilder.append(e.getMessage());
            ADTLogUtil.e(stringBuilder.toString());
            return false;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isAvailable();
                }
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder("[Error] CheckNetWorkState Err :");
                stringBuilder.append(e.getMessage());
                ADTLogUtil.e(stringBuilder.toString());
            }
        }
        return false;
    }

    public static boolean checkPermission_GRANTED() {
        Exception e;
        StringBuilder stringBuilder;
        int i = 1;
        boolean z;
        try {
            z = mContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0;
            try {
                int i2 = z & (mContext.checkCallingOrSelfPermission("android.permission.INTERNET") == 0 ? 1 : 0);
                if (mContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
                    i = 0;
                }
                return i & i2;
            } catch (Exception e2) {
                e = e2;
                stringBuilder = new StringBuilder("[Error] checkPermission Err :");
                stringBuilder.append(e.getMessage());
                ADTLogUtil.e(stringBuilder.toString());
                return z;
            }
        } catch (Exception e3) {
            e = e3;
            z = true;
            stringBuilder = new StringBuilder("[Error] checkPermission Err :");
            stringBuilder.append(e.getMessage());
            ADTLogUtil.e(stringBuilder.toString());
            return z;
        }
    }
}
