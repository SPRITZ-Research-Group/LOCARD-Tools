package com.facebook.common.e;

public enum b {
    OnCloseToDalvikHeapLimit(0.5d),
    OnSystemLowMemoryWhileAppInForeground(0.5d),
    OnSystemLowMemoryWhileAppInBackground(1.0d),
    OnAppBackgrounded(1.0d);
    
    private double e;

    private b(double suggestedTrimRatio) {
        this.e = suggestedTrimRatio;
    }

    public final double a() {
        return this.e;
    }
}
