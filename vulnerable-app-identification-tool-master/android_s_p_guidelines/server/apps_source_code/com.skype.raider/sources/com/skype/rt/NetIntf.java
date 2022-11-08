package com.skype.rt;

public interface NetIntf {
    public static final int ETHERNET = 1;
    public static final int MOBILE = 3;
    public static final int WIFI = 2;

    void dropInterface(int i);

    void finish();

    JniNetworkParams getActiveConnectionInfo();

    JniNetworkParams[] getConnectionInfos();

    String[] pickInterface(int i, String str);
}
