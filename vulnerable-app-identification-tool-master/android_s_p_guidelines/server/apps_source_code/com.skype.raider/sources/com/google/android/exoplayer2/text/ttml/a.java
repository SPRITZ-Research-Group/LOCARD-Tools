package com.google.android.exoplayer2.text.ttml;

import android.text.Layout.Alignment;
import com.google.android.exoplayer2.d.c;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.d.t;
import com.google.android.exoplayer2.text.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class a extends com.google.android.exoplayer2.text.a {
    private static final Pattern a = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern b = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern c = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern d = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final a e = new a(30.0f, 1, 1);
    private final XmlPullParserFactory f;

    private static final class a {
        final float a;
        final int b;
        final int c;

        a(float effectiveFrameRate, int subFrameRate, int tickRate) {
            this.a = effectiveFrameRate;
            this.b = subFrameRate;
            this.c = tickRate;
        }
    }

    public a() {
        super("TtmlDecoder");
        try {
            this.f = XmlPullParserFactory.newInstance();
            this.f.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    private d a(byte[] bytes, int length) throws e {
        try {
            XmlPullParser xmlParser = this.f.newPullParser();
            Map globalStyles = new HashMap();
            Map regionMap = new HashMap();
            regionMap.put("", new c());
            xmlParser.setInput(new ByteArrayInputStream(bytes, 0, length), null);
            d ttmlSubtitle = null;
            LinkedList<b> nodeStack = new LinkedList();
            int unsupportedNodeDepth = 0;
            a frameAndTickRate = e;
            for (int eventType = xmlParser.getEventType(); eventType != 1; eventType = xmlParser.getEventType()) {
                b parent = (b) nodeStack.peekLast();
                if (unsupportedNodeDepth == 0) {
                    String name = xmlParser.getName();
                    if (eventType == 2) {
                        Object obj;
                        if ("tt".equals(name)) {
                            int parseInt;
                            float parseInt2;
                            int parseInt3;
                            String attributeValue = xmlParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
                            if (attributeValue != null) {
                                parseInt = Integer.parseInt(attributeValue);
                            } else {
                                parseInt = 30;
                            }
                            attributeValue = xmlParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
                            if (attributeValue != null) {
                                String[] split = attributeValue.split(" ");
                                if (split.length != 2) {
                                    throw new e("frameRateMultiplier doesn't have 2 parts");
                                }
                                parseInt2 = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
                            } else {
                                parseInt2 = 1.0f;
                            }
                            int i = e.b;
                            attributeValue = xmlParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
                            if (attributeValue != null) {
                                parseInt3 = Integer.parseInt(attributeValue);
                            } else {
                                parseInt3 = i;
                            }
                            i = e.c;
                            String attributeValue2 = xmlParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
                            if (attributeValue2 != null) {
                                i = Integer.parseInt(attributeValue2);
                            }
                            frameAndTickRate = new a(parseInt2 * ((float) parseInt), parseInt3, i);
                        }
                        if (name.equals("tt") || name.equals("head") || name.equals("body") || name.equals("div") || name.equals("p") || name.equals("span") || name.equals("br") || name.equals("style") || name.equals("styling") || name.equals("layout") || name.equals("region") || name.equals("metadata") || name.equals("smpte:image") || name.equals("smpte:data") || name.equals("smpte:information")) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj == null) {
                            new StringBuilder("Ignoring unsupported tag: ").append(xmlParser.getName());
                            unsupportedNodeDepth++;
                        } else if ("head".equals(name)) {
                            a(xmlParser, globalStyles, regionMap);
                        } else {
                            try {
                                b node = a(xmlParser, parent, regionMap, frameAndTickRate);
                                nodeStack.addLast(node);
                                if (parent != null) {
                                    parent.a(node);
                                }
                            } catch (e e) {
                                unsupportedNodeDepth++;
                            }
                        }
                    } else if (eventType == 4) {
                        parent.a(b.a(xmlParser.getText()));
                    } else if (eventType == 3) {
                        if (xmlParser.getName().equals("tt")) {
                            ttmlSubtitle = new d((b) nodeStack.getLast(), globalStyles, regionMap);
                        }
                        nodeStack.removeLast();
                    }
                } else if (eventType == 2) {
                    unsupportedNodeDepth++;
                } else if (eventType == 3) {
                    unsupportedNodeDepth--;
                }
                xmlParser.next();
            }
            return ttmlSubtitle;
        } catch (Throwable xppe) {
            throw new e("Unable to decode source", xppe);
        } catch (IOException e2) {
            throw new IllegalStateException("Unexpected error when reading input.", e2);
        }
    }

    private static Map<String, TtmlStyle> a(XmlPullParser xmlParser, Map<String, TtmlStyle> globalStyles, Map<String, c> globalRegions) throws IOException, XmlPullParserException {
        Object obj;
        do {
            xmlParser.next();
            if (t.a(xmlParser, "style")) {
                String parentStyleId = t.b(xmlParser, "style");
                TtmlStyle style = a(xmlParser, new TtmlStyle());
                if (parentStyleId != null) {
                    for (String id : parentStyleId.split("\\s+")) {
                        style.a((TtmlStyle) globalStyles.get(id));
                    }
                }
                if (style.i() != null) {
                    globalStyles.put(style.i(), style);
                }
            } else if (t.a(xmlParser, "region")) {
                c ttmlRegion = a(xmlParser);
                if (ttmlRegion != null) {
                    globalRegions.put(ttmlRegion.a, ttmlRegion);
                }
            }
            String str = "head";
            if (xmlParser.getEventType() == 3) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null || !xmlParser.getName().equals(str)) {
                obj = null;
                continue;
            } else {
                obj = 1;
                continue;
            }
        } while (obj == null);
        return globalStyles;
    }

    private static c a(XmlPullParser xmlParser) {
        String regionId = t.b(xmlParser, "id");
        if (regionId == null) {
            return null;
        }
        float position;
        float line;
        float width;
        float height;
        String regionOrigin = t.b(xmlParser, "origin");
        if (regionOrigin != null) {
            Matcher originMatcher = d.matcher(regionOrigin);
            if (!originMatcher.matches()) {
                return null;
            }
            try {
                position = Float.parseFloat(originMatcher.group(1)) / 100.0f;
                line = Float.parseFloat(originMatcher.group(2)) / 100.0f;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        position = 0.0f;
        line = 0.0f;
        String regionExtent = t.b(xmlParser, "extent");
        if (regionExtent != null) {
            Matcher extentMatcher = d.matcher(regionExtent);
            if (!extentMatcher.matches()) {
                return null;
            }
            try {
                width = Float.parseFloat(extentMatcher.group(1)) / 100.0f;
                height = Float.parseFloat(extentMatcher.group(2)) / 100.0f;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        width = 1.0f;
        height = 1.0f;
        int lineAnchor = 0;
        String displayAlign = t.b(xmlParser, "displayAlign");
        if (displayAlign != null) {
            String toLowerCase = displayAlign.toLowerCase();
            Object obj = -1;
            switch (toLowerCase.hashCode()) {
                case -1364013995:
                    if (toLowerCase.equals("center")) {
                        obj = null;
                        break;
                    }
                    break;
                case 92734940:
                    if (toLowerCase.equals("after")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    lineAnchor = 1;
                    line += height / 2.0f;
                    break;
                case 1:
                    lineAnchor = 2;
                    line += height;
                    break;
            }
        }
        return new c(regionId, position, line, 0, lineAnchor, width);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static TtmlStyle a(XmlPullParser parser, TtmlStyle style) {
        int attributeCount = parser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            boolean z;
            String attributeValue = parser.getAttributeValue(i);
            String attributeName = parser.getAttributeName(i);
            switch (attributeName.hashCode()) {
                case -1550943582:
                    if (attributeName.equals("fontStyle")) {
                        z = true;
                        break;
                    }
                case -1224696685:
                    if (attributeName.equals("fontFamily")) {
                        z = true;
                        break;
                    }
                case -1065511464:
                    if (attributeName.equals("textAlign")) {
                        z = true;
                        break;
                    }
                case -879295043:
                    if (attributeName.equals("textDecoration")) {
                        z = true;
                        break;
                    }
                case -734428249:
                    if (attributeName.equals("fontWeight")) {
                        z = true;
                        break;
                    }
                case 3355:
                    if (attributeName.equals("id")) {
                        z = false;
                        break;
                    }
                case 94842723:
                    if (attributeName.equals("color")) {
                        z = true;
                        break;
                    }
                case 365601008:
                    if (attributeName.equals("fontSize")) {
                        z = true;
                        break;
                    }
                case 1287124693:
                    if (attributeName.equals("backgroundColor")) {
                        z = true;
                        break;
                    }
                default:
                    z = true;
                    break;
            }
            switch (z) {
                case false:
                    if (!"style".equals(parser.getName())) {
                        break;
                    }
                    style = a(style).b(attributeValue);
                    break;
                case true:
                    style = a(style);
                    try {
                        style.b(c.a(attributeValue));
                        break;
                    } catch (IllegalArgumentException e) {
                        break;
                    }
                case true:
                    style = a(style);
                    try {
                        style.a(c.a(attributeValue));
                        break;
                    } catch (IllegalArgumentException e2) {
                        break;
                    }
                case true:
                    style = a(style).a(attributeValue);
                    break;
                case true:
                    try {
                        Matcher matcher;
                        style = a(style);
                        String[] split = attributeValue.split("\\s+");
                        if (split.length == 1) {
                            matcher = c.matcher(attributeValue);
                        } else if (split.length == 2) {
                            matcher = c.matcher(split[1]);
                        } else {
                            throw new e("Invalid number of entries for fontSize: " + split.length + ".");
                        }
                        if (matcher.matches()) {
                            String group = matcher.group(3);
                            switch (group.hashCode()) {
                                case 37:
                                    if (group.equals("%")) {
                                        z = true;
                                        break;
                                    }
                                case 3240:
                                    if (group.equals("em")) {
                                        z = true;
                                        break;
                                    }
                                case 3592:
                                    if (group.equals("px")) {
                                        z = false;
                                        break;
                                    }
                                default:
                                    z = true;
                                    break;
                            }
                            switch (z) {
                                case false:
                                    style.c(1);
                                    break;
                                case true:
                                    style.c(2);
                                    break;
                                case true:
                                    style.c(3);
                                    break;
                                default:
                                    throw new e("Invalid unit for fontSize: '" + group + "'.");
                            }
                            style.a(Float.valueOf(matcher.group(1)).floatValue());
                            break;
                        }
                        throw new e("Invalid expression for fontSize: '" + attributeValue + "'.");
                    } catch (e e3) {
                        break;
                    }
                case true:
                    style = a(style).c("bold".equalsIgnoreCase(attributeValue));
                    break;
                case true:
                    style = a(style).d("italic".equalsIgnoreCase(attributeValue));
                    break;
                case true:
                    attributeName = s.d(attributeValue);
                    switch (attributeName.hashCode()) {
                        case -1364013995:
                            if (attributeName.equals("center")) {
                                z = true;
                                break;
                            }
                        case 100571:
                            if (attributeName.equals("end")) {
                                z = true;
                                break;
                            }
                        case 3317767:
                            if (attributeName.equals("left")) {
                                z = false;
                                break;
                            }
                        case 108511772:
                            if (attributeName.equals("right")) {
                                z = true;
                                break;
                            }
                        case 109757538:
                            if (attributeName.equals("start")) {
                                z = true;
                                break;
                            }
                        default:
                            z = true;
                            break;
                    }
                    switch (z) {
                        case false:
                            style = a(style).a(Alignment.ALIGN_NORMAL);
                            break;
                        case true:
                            style = a(style).a(Alignment.ALIGN_NORMAL);
                            break;
                        case true:
                            style = a(style).a(Alignment.ALIGN_OPPOSITE);
                            break;
                        case true:
                            style = a(style).a(Alignment.ALIGN_OPPOSITE);
                            break;
                        case true:
                            style = a(style).a(Alignment.ALIGN_CENTER);
                            break;
                        default:
                            break;
                    }
                case true:
                    attributeName = s.d(attributeValue);
                    switch (attributeName.hashCode()) {
                        case -1461280213:
                            if (attributeName.equals("nounderline")) {
                                z = true;
                                break;
                            }
                        case -1026963764:
                            if (attributeName.equals("underline")) {
                                z = true;
                                break;
                            }
                        case 913457136:
                            if (attributeName.equals("nolinethrough")) {
                                z = true;
                                break;
                            }
                        case 1679736913:
                            if (attributeName.equals("linethrough")) {
                                z = false;
                                break;
                            }
                        default:
                            z = true;
                            break;
                    }
                    switch (z) {
                        case false:
                            style = a(style).a(true);
                            break;
                        case true:
                            style = a(style).a(false);
                            break;
                        case true:
                            style = a(style).b(true);
                            break;
                        case true:
                            style = a(style).b(false);
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
        return style;
    }

    private static TtmlStyle a(TtmlStyle style) {
        return style == null ? new TtmlStyle() : style;
    }

    private static b a(XmlPullParser parser, b parent, Map<String, c> regionMap, a frameAndTickRate) throws e {
        long duration = -9223372036854775807L;
        long startTime = -9223372036854775807L;
        long endTime = -9223372036854775807L;
        String regionId = "";
        String[] styleIds = null;
        int attributeCount = parser.getAttributeCount();
        TtmlStyle style = a(parser, null);
        for (int i = 0; i < attributeCount; i++) {
            String attr = parser.getAttributeName(i);
            String value = parser.getAttributeValue(i);
            Object obj = -1;
            switch (attr.hashCode()) {
                case -934795532:
                    if (attr.equals("region")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 99841:
                    if (attr.equals("dur")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 100571:
                    if (attr.equals("end")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 93616297:
                    if (attr.equals("begin")) {
                        obj = null;
                        break;
                    }
                    break;
                case 109780401:
                    if (attr.equals("style")) {
                        obj = 3;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    startTime = a(value, frameAndTickRate);
                    break;
                case 1:
                    endTime = a(value, frameAndTickRate);
                    break;
                case 2:
                    duration = a(value, frameAndTickRate);
                    break;
                case 3:
                    String[] ids = value.split("\\s+");
                    if (ids.length <= 0) {
                        break;
                    }
                    styleIds = ids;
                    break;
                case 4:
                    if (!regionMap.containsKey(value)) {
                        break;
                    }
                    regionId = value;
                    break;
                default:
                    break;
            }
        }
        if (!(parent == null || parent.d == -9223372036854775807L)) {
            if (startTime != -9223372036854775807L) {
                startTime += parent.d;
            }
            if (endTime != -9223372036854775807L) {
                endTime += parent.d;
            }
        }
        if (endTime == -9223372036854775807L) {
            if (duration != -9223372036854775807L) {
                endTime = startTime + duration;
            } else if (!(parent == null || parent.e == -9223372036854775807L)) {
                endTime = parent.e;
            }
        }
        return b.a(parser.getName(), startTime, endTime, style, styleIds, regionId);
    }

    private static long a(String time, a frameAndTickRate) throws e {
        Matcher matcher = a.matcher(time);
        if (matcher.matches()) {
            double durationSeconds = (((double) (Long.parseLong(matcher.group(1)) * 3600)) + ((double) (Long.parseLong(matcher.group(2)) * 60))) + ((double) Long.parseLong(matcher.group(3)));
            String fraction = matcher.group(4);
            durationSeconds += fraction != null ? Double.parseDouble(fraction) : 0.0d;
            String frames = matcher.group(5);
            durationSeconds += frames != null ? (double) (((float) Long.parseLong(frames)) / frameAndTickRate.a) : 0.0d;
            String subframes = matcher.group(6);
            return (long) (((subframes != null ? (((double) Long.parseLong(subframes)) / ((double) frameAndTickRate.b)) / ((double) frameAndTickRate.a) : 0.0d) + durationSeconds) * 1000000.0d);
        }
        matcher = b.matcher(time);
        if (matcher.matches()) {
            double offsetSeconds = Double.parseDouble(matcher.group(1));
            String group = matcher.group(2);
            Object obj = -1;
            switch (group.hashCode()) {
                case 102:
                    if (group.equals("f")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 104:
                    if (group.equals("h")) {
                        obj = null;
                        break;
                    }
                    break;
                case 109:
                    if (group.equals("m")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 115:
                    if (group.equals("s")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 116:
                    if (group.equals("t")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 3494:
                    if (group.equals("ms")) {
                        obj = 3;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    offsetSeconds *= 3600.0d;
                    break;
                case 1:
                    offsetSeconds *= 60.0d;
                    break;
                case 3:
                    offsetSeconds /= 1000.0d;
                    break;
                case 4:
                    offsetSeconds /= (double) frameAndTickRate.a;
                    break;
                case 5:
                    offsetSeconds /= (double) frameAndTickRate.c;
                    break;
            }
            return (long) (1000000.0d * offsetSeconds);
        }
        throw new e("Malformed time expression: " + time);
    }
}
