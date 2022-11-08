package com.google.android.exoplayer.text;

import android.text.Layout.Alignment;

public class Cue {
    public static final int UNSET_VALUE = -1;
    public final Alignment alignment;
    public final int line;
    public final int position;
    public final int size;
    public final CharSequence text;

    public Cue() {
        this(null);
    }

    public Cue(CharSequence charSequence) {
        this(charSequence, -1, -1, null, -1);
    }

    public Cue(CharSequence charSequence, int i, int i2, Alignment alignment, int i3) {
        this.text = charSequence;
        this.line = i;
        this.position = i2;
        this.alignment = alignment;
        this.size = i3;
    }
}
