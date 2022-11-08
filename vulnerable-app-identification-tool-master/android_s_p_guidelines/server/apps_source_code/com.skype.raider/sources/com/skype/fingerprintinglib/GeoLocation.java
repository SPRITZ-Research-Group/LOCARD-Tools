package com.skype.fingerprintinglib;

public class GeoLocation {
    public double a = Double.NaN;
    public double b = Double.NaN;
    public float c = Float.NaN;
    public long d = 0;

    public GeoLocation(double latitude, double longitude, float accuracy, long timestamp) {
        this.a = latitude;
        this.b = longitude;
        this.c = accuracy;
        this.d = timestamp;
    }
}
