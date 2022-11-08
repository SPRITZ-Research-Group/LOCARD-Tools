package com.google.android.gms.maps.model;

public final class CustomCap extends Cap {
    public final a a;
    public final float b;

    public final String toString() {
        String valueOf = String.valueOf(this.a);
        return new StringBuilder(String.valueOf(valueOf).length() + 55).append("[CustomCap: bitmapDescriptor=").append(valueOf).append(" refWidth=").append(this.b).append("]").toString();
    }
}
