package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.Arrays;
import java.util.UUID;

public class ContentProtection {
    public final byte[] data;
    public final String schemeUriId;
    public final UUID uuid;

    public ContentProtection(String str, UUID uuid, byte[] bArr) {
        this.schemeUriId = (String) Assertions.checkNotNull(str);
        this.uuid = uuid;
        this.data = bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ContentProtection)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ContentProtection contentProtection = (ContentProtection) obj;
        return this.schemeUriId.equals(contentProtection.schemeUriId) && Util.areEqual(this.uuid, contentProtection.uuid) && Arrays.equals(this.data, contentProtection.data);
    }

    public int hashCode() {
        int hashCode = this.schemeUriId.hashCode() + 37;
        if (this.uuid != null) {
            hashCode = (hashCode * 37) + this.uuid.hashCode();
        }
        return this.data != null ? (hashCode * 37) + Arrays.hashCode(this.data) : hashCode;
    }
}
