package com.google.android.exoplayer.dash.mpd;

import android.net.Uri;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.UriUtil;

public final class RangedUri {
    private final String baseUri;
    private int hashCode;
    public final long length;
    private final String referenceUri;
    public final long start;

    public RangedUri(String str, String str2, long j, long j2) {
        boolean z = (str == null && str2 == null) ? false : true;
        Assertions.checkArgument(z);
        this.baseUri = str;
        this.referenceUri = str2;
        this.start = j;
        this.length = j2;
    }

    public final Uri getUri() {
        return UriUtil.resolveToUri(this.baseUri, this.referenceUri);
    }

    public final String getUriString() {
        return UriUtil.resolve(this.baseUri, this.referenceUri);
    }

    public final RangedUri attemptMerge(RangedUri rangedUri) {
        RangedUri rangedUri2 = this;
        RangedUri rangedUri3 = rangedUri;
        if (rangedUri3 == null || !getUriString().equals(rangedUri.getUriString())) {
            return null;
        }
        long j = -1;
        String str;
        String str2;
        long j2;
        if (rangedUri2.length != -1 && rangedUri2.start + rangedUri2.length == rangedUri3.start) {
            str = rangedUri2.baseUri;
            str2 = rangedUri2.referenceUri;
            j2 = rangedUri2.start;
            if (rangedUri3.length != -1) {
                j = rangedUri3.length + rangedUri2.length;
            }
            return new RangedUri(str, str2, j2, j);
        } else if (rangedUri3.length == -1 || rangedUri3.start + rangedUri3.length != rangedUri2.start) {
            return null;
        } else {
            str = rangedUri2.baseUri;
            str2 = rangedUri2.referenceUri;
            j2 = rangedUri3.start;
            if (rangedUri2.length != -1) {
                j = rangedUri2.length + rangedUri3.length;
            }
            return new RangedUri(str, str2, j2, j);
        }
    }

    public final int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((((((int) this.start) + 527) * 31) + ((int) this.length)) * 31) + getUriString().hashCode();
        }
        return this.hashCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RangedUri rangedUri = (RangedUri) obj;
        return this.start == rangedUri.start && this.length == rangedUri.length && getUriString().equals(rangedUri.getUriString());
    }
}
