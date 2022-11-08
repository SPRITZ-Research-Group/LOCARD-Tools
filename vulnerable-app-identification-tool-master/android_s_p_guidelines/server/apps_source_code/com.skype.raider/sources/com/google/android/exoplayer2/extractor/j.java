package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.a.a;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class j {
    public static final a a = new a() {
        public final boolean a(int majorVersion, int id0, int id1, int id2, int id3) {
            return id0 == 67 && id1 == 79 && id2 == 77 && (id3 == 77 || majorVersion == 2);
        }
    };
    private static final Pattern d = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int b = -1;
    public int c = -1;

    public final boolean a(Metadata metadata) {
        for (int i = 0; i < metadata.a(); i++) {
            Entry entry = metadata.a(i);
            if (entry instanceof CommentFrame) {
                CommentFrame commentFrame = (CommentFrame) entry;
                if (a(commentFrame.b, commentFrame.c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean a(String name, String data) {
        if (!"iTunSMPB".equals(name)) {
            return false;
        }
        Matcher matcher = d.matcher(data);
        if (!matcher.find()) {
            return false;
        }
        try {
            int encoderDelay = Integer.parseInt(matcher.group(1), 16);
            int encoderPadding = Integer.parseInt(matcher.group(2), 16);
            if (encoderDelay <= 0 && encoderPadding <= 0) {
                return false;
            }
            this.b = encoderDelay;
            this.c = encoderPadding;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public final boolean a() {
        return (this.b == -1 || this.c == -1) ? false : true;
    }
}
