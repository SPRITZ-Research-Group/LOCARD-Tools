package com.skype.rt;

public class WiFiNetworkStatus {
    public String bssid;
    public boolean connected;
    public int freq;
    public String hwAddress;
    public int linkSpeed;
    public String ssid;

    WiFiNetworkStatus(boolean connected, String hwAddress, String ssid, String bssid, int linkSpeed, int freq) {
        this.connected = connected;
        if (hwAddress == null) {
            hwAddress = "";
        }
        this.hwAddress = hwAddress;
        if (ssid == null) {
            ssid = "";
        }
        this.ssid = ssid;
        if (bssid == null) {
            bssid = "";
        }
        this.bssid = bssid;
        this.linkSpeed = linkSpeed;
        this.freq = freq;
    }
}
