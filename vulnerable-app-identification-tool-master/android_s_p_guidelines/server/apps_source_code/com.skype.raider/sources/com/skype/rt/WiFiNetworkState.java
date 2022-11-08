package com.skype.rt;

public class WiFiNetworkState {
    public int freq;
    public int rssi;

    WiFiNetworkState(int freq, int rssi) {
        this.freq = freq;
        this.rssi = rssi;
    }
}
