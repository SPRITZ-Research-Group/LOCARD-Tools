package com.google.android.exoplayer2.text.c;

import android.text.Html;
import android.text.TextUtils;
import com.google.android.exoplayer2.d.f;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class a extends com.google.android.exoplayer2.text.a {
    private static final Pattern a = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    private final StringBuilder b = new StringBuilder();

    protected final /* bridge */ /* synthetic */ c a(byte[] bArr, int i, boolean z) throws e {
        return a(bArr, i);
    }

    public a() {
        super("SubripDecoder");
    }

    private b a(byte[] bytes, int length) {
        ArrayList<Cue> cues = new ArrayList();
        f cueTimesUs = new f();
        k subripData = new k(bytes, length);
        while (true) {
            String currentLine = subripData.x();
            if (currentLine == null) {
                Cue[] cuesArray = new Cue[cues.size()];
                cues.toArray(cuesArray);
                return new b(cuesArray, cueTimesUs.b());
            } else if (currentLine.length() != 0) {
                try {
                    Integer.parseInt(currentLine);
                    boolean haveEndTimecode = false;
                    Matcher matcher = a.matcher(subripData.x());
                    if (matcher.matches()) {
                        cueTimesUs.a(a(matcher, 1));
                        if (!TextUtils.isEmpty(matcher.group(6))) {
                            haveEndTimecode = true;
                            cueTimesUs.a(a(matcher, 6));
                        }
                        this.b.setLength(0);
                        while (true) {
                            currentLine = subripData.x();
                            if (TextUtils.isEmpty(currentLine)) {
                                break;
                            }
                            if (this.b.length() > 0) {
                                this.b.append("<br>");
                            }
                            this.b.append(currentLine.trim());
                        }
                        cues.add(new Cue(Html.fromHtml(this.b.toString())));
                        if (haveEndTimecode) {
                            cues.add(null);
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private static long a(Matcher matcher, int groupOffset) {
        return ((((((Long.parseLong(matcher.group(groupOffset + 1)) * 60) * 60) * 1000) + ((Long.parseLong(matcher.group(groupOffset + 2)) * 60) * 1000)) + (Long.parseLong(matcher.group(groupOffset + 3)) * 1000)) + Long.parseLong(matcher.group(groupOffset + 4))) * 1000;
    }
}
