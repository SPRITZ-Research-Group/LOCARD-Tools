package com.google.android.exoplayer2.metadata.id3;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.metadata.Metadata.Entry;

public abstract class Id3Frame implements Entry {
    public final String f;

    public Id3Frame(String id) {
        this.f = (String) a.a((Object) id);
    }

    public int describeContents() {
        return 0;
    }
}
