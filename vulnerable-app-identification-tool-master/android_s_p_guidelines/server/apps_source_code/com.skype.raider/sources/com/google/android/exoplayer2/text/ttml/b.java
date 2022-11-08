package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.text.Cue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

final class b {
    public final String a;
    public final String b;
    public final boolean c;
    public final long d;
    public final long e;
    public final TtmlStyle f;
    public final String g;
    private final String[] h;
    private final HashMap<String, Integer> i;
    private final HashMap<String, Integer> j;
    private List<b> k;

    public static b a(String text) {
        return new b(null, text.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " "), -9223372036854775807L, -9223372036854775807L, null, null, "");
    }

    public static b a(String tag, long startTimeUs, long endTimeUs, TtmlStyle style, String[] styleIds, String regionId) {
        return new b(tag, null, startTimeUs, endTimeUs, style, styleIds, regionId);
    }

    private b(String tag, String text, long startTimeUs, long endTimeUs, TtmlStyle style, String[] styleIds, String regionId) {
        this.a = tag;
        this.b = text;
        this.f = style;
        this.h = styleIds;
        this.c = text != null;
        this.d = startTimeUs;
        this.e = endTimeUs;
        this.g = (String) a.a((Object) regionId);
        this.i = new HashMap();
        this.j = new HashMap();
    }

    public final void a(b child) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(child);
    }

    private b a(int index) {
        if (this.k != null) {
            return (b) this.k.get(index);
        }
        throw new IndexOutOfBoundsException();
    }

    private int b() {
        return this.k == null ? 0 : this.k.size();
    }

    public final long[] a() {
        TreeSet eventTimeSet = new TreeSet();
        a(eventTimeSet, false);
        long[] eventTimes = new long[eventTimeSet.size()];
        int i = 0;
        Iterator it = eventTimeSet.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            eventTimes[i] = ((Long) it.next()).longValue();
            i = i2;
        }
        return eventTimes;
    }

    private void a(TreeSet<Long> out, boolean descendsPNode) {
        boolean isPNode = "p".equals(this.a);
        if (descendsPNode || isPNode) {
            if (this.d != -9223372036854775807L) {
                out.add(Long.valueOf(this.d));
            }
            if (this.e != -9223372036854775807L) {
                out.add(Long.valueOf(this.e));
            }
        }
        if (this.k != null) {
            for (int i = 0; i < this.k.size(); i++) {
                b bVar = (b) this.k.get(i);
                boolean z = descendsPNode || isPNode;
                bVar.a((TreeSet) out, z);
            }
        }
    }

    public final List<Cue> a(long timeUs, Map<String, TtmlStyle> globalStyles, Map<String, c> regionMap) {
        Map regionOutputs = new TreeMap();
        a(timeUs, false, this.g, regionOutputs);
        a((Map) globalStyles, regionOutputs);
        List<Cue> cues = new ArrayList();
        for (Entry<String, SpannableStringBuilder> entry : regionOutputs.entrySet()) {
            c region = (c) regionMap.get(entry.getKey());
            cues.add(new Cue(a((SpannableStringBuilder) entry.getValue()), null, region.c, region.d, region.e, region.b, Integer.MIN_VALUE, region.f));
        }
        return cues;
    }

    private void a(long timeUs, boolean descendsPNode, String inheritedRegion, Map<String, SpannableStringBuilder> regionOutputs) {
        this.i.clear();
        this.j.clear();
        String resolvedRegionId = this.g;
        if ("".equals(resolvedRegionId)) {
            resolvedRegionId = inheritedRegion;
        }
        if (this.c && descendsPNode) {
            a(resolvedRegionId, (Map) regionOutputs).append(this.b);
        } else if ("br".equals(this.a) && descendsPNode) {
            a(resolvedRegionId, (Map) regionOutputs).append(10);
        } else if (!"metadata".equals(this.a)) {
            Object obj;
            if (!(this.d == -9223372036854775807L && this.e == -9223372036854775807L) && ((this.d > timeUs || this.e != -9223372036854775807L) && ((this.d != -9223372036854775807L || timeUs >= this.e) && (this.d > timeUs || timeUs >= this.e)))) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                boolean isPNode = "p".equals(this.a);
                for (Entry<String, SpannableStringBuilder> entry : regionOutputs.entrySet()) {
                    this.i.put(entry.getKey(), Integer.valueOf(((SpannableStringBuilder) entry.getValue()).length()));
                }
                for (int i = 0; i < b(); i++) {
                    b a = a(i);
                    boolean z = descendsPNode || isPNode;
                    a.a(timeUs, z, resolvedRegionId, regionOutputs);
                }
                if (isPNode) {
                    SpannableStringBuilder a2 = a(resolvedRegionId, (Map) regionOutputs);
                    int length = a2.length() - 1;
                    while (length >= 0 && a2.charAt(length) == ' ') {
                        length--;
                    }
                    if (length >= 0 && a2.charAt(length) != 10) {
                        a2.append(10);
                    }
                }
                for (Entry<String, SpannableStringBuilder> entry2 : regionOutputs.entrySet()) {
                    this.j.put(entry2.getKey(), Integer.valueOf(((SpannableStringBuilder) entry2.getValue()).length()));
                }
            }
        }
    }

    private static SpannableStringBuilder a(String resolvedRegionId, Map<String, SpannableStringBuilder> regionOutputs) {
        if (!regionOutputs.containsKey(resolvedRegionId)) {
            regionOutputs.put(resolvedRegionId, new SpannableStringBuilder());
        }
        return (SpannableStringBuilder) regionOutputs.get(resolvedRegionId);
    }

    private void a(Map<String, TtmlStyle> globalStyles, Map<String, SpannableStringBuilder> regionOutputs) {
        for (Entry<String, Integer> entry : this.j.entrySet()) {
            String regionId = (String) entry.getKey();
            int start = this.i.containsKey(regionId) ? ((Integer) this.i.get(regionId)).intValue() : 0;
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) regionOutputs.get(regionId);
            int intValue = ((Integer) entry.getValue()).intValue();
            if (start != intValue) {
                TtmlStyle ttmlStyle;
                TtmlStyle ttmlStyle2 = this.f;
                String[] strArr = this.h;
                if (ttmlStyle2 == null && strArr == null) {
                    ttmlStyle = null;
                } else if (ttmlStyle2 == null && strArr.length == 1) {
                    ttmlStyle = (TtmlStyle) globalStyles.get(strArr[0]);
                } else if (ttmlStyle2 == null && strArr.length > 1) {
                    ttmlStyle2 = new TtmlStyle();
                    for (Object obj : strArr) {
                        ttmlStyle2.a((TtmlStyle) globalStyles.get(obj));
                    }
                    ttmlStyle = ttmlStyle2;
                } else if (ttmlStyle2 != null && strArr != null && strArr.length == 1) {
                    ttmlStyle = ttmlStyle2.a((TtmlStyle) globalStyles.get(strArr[0]));
                } else if (ttmlStyle2 == null || strArr == null || strArr.length <= 1) {
                    ttmlStyle = ttmlStyle2;
                } else {
                    for (Object obj2 : strArr) {
                        ttmlStyle2.a((TtmlStyle) globalStyles.get(obj2));
                    }
                    ttmlStyle = ttmlStyle2;
                }
                if (ttmlStyle != null) {
                    if (ttmlStyle.a() != -1) {
                        spannableStringBuilder.setSpan(new StyleSpan(ttmlStyle.a()), start, intValue, 33);
                    }
                    if (ttmlStyle.b()) {
                        spannableStringBuilder.setSpan(new StrikethroughSpan(), start, intValue, 33);
                    }
                    if (ttmlStyle.c()) {
                        spannableStringBuilder.setSpan(new UnderlineSpan(), start, intValue, 33);
                    }
                    if (ttmlStyle.f()) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(ttmlStyle.e()), start, intValue, 33);
                    }
                    if (ttmlStyle.h()) {
                        spannableStringBuilder.setSpan(new BackgroundColorSpan(ttmlStyle.g()), start, intValue, 33);
                    }
                    if (ttmlStyle.d() != null) {
                        spannableStringBuilder.setSpan(new TypefaceSpan(ttmlStyle.d()), start, intValue, 33);
                    }
                    if (ttmlStyle.j() != null) {
                        spannableStringBuilder.setSpan(new Standard(ttmlStyle.j()), start, intValue, 33);
                    }
                    switch (ttmlStyle.k()) {
                        case 1:
                            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) ttmlStyle.l(), true), start, intValue, 33);
                            break;
                        case 2:
                            spannableStringBuilder.setSpan(new RelativeSizeSpan(ttmlStyle.l()), start, intValue, 33);
                            break;
                        case 3:
                            spannableStringBuilder.setSpan(new RelativeSizeSpan(ttmlStyle.l() / 100.0f), start, intValue, 33);
                            break;
                    }
                }
            }
            for (int i = 0; i < b(); i++) {
                a(i).a((Map) globalStyles, (Map) regionOutputs);
            }
        }
    }

    private static SpannableStringBuilder a(SpannableStringBuilder builder) {
        int i;
        int builderLength = builder.length();
        for (i = 0; i < builderLength; i++) {
            if (builder.charAt(i) == ' ') {
                int j = i + 1;
                while (j < builder.length() && builder.charAt(j) == ' ') {
                    j++;
                }
                int spacesToDelete = j - (i + 1);
                if (spacesToDelete > 0) {
                    builder.delete(i, i + spacesToDelete);
                    builderLength -= spacesToDelete;
                }
            }
        }
        if (builderLength > 0 && builder.charAt(0) == ' ') {
            builder.delete(0, 1);
            builderLength--;
        }
        i = 0;
        while (i < builderLength - 1) {
            if (builder.charAt(i) == 10 && builder.charAt(i + 1) == ' ') {
                builder.delete(i + 1, i + 2);
                builderLength--;
            }
            i++;
        }
        if (builderLength > 0 && builder.charAt(builderLength - 1) == ' ') {
            builder.delete(builderLength - 1, builderLength);
            builderLength--;
        }
        i = 0;
        while (i < builderLength - 1) {
            if (builder.charAt(i) == ' ' && builder.charAt(i + 1) == 10) {
                builder.delete(i, i + 1);
                builderLength--;
            }
            i++;
        }
        if (builderLength > 0 && builder.charAt(builderLength - 1) == 10) {
            builder.delete(builderLength - 1, builderLength);
        }
        return builder;
    }
}
