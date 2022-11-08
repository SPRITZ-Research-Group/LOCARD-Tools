package com.google.android.exoplayer.metadata;

public final class GeobMetadata {
    public static final String TYPE = "GEOB";
    public final byte[] data;
    public final String description;
    public final String filename;
    public final String mimeType;

    public GeobMetadata(String str, String str2, String str3, byte[] bArr) {
        this.mimeType = str;
        this.filename = str2;
        this.description = str3;
        this.data = bArr;
    }
}
