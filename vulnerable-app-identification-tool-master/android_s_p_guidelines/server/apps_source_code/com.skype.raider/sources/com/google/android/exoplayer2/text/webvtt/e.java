package com.google.android.exoplayer2.text.webvtt;

import android.support.annotation.NonNull;
import android.text.Layout.Alignment;
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
import com.google.android.exoplayer2.d.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class e {
    public static final Pattern a = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern b = Pattern.compile("(\\S+?):(\\S+)");
    private final StringBuilder c = new StringBuilder();

    private static final class a {
        private static final String[] e = new String[0];
        public final String a;
        public final int b;
        public final String c;
        public final String[] d;

        private a(String name, int position, String voice, String[] classes) {
            this.b = position;
            this.a = name;
            this.c = voice;
            this.d = classes;
        }

        public static a a(String fullTagExpression, int position) {
            fullTagExpression = fullTagExpression.trim();
            if (fullTagExpression.isEmpty()) {
                return null;
            }
            String voice;
            String[] classes;
            int voiceStartIndex = fullTagExpression.indexOf(" ");
            if (voiceStartIndex == -1) {
                voice = "";
            } else {
                voice = fullTagExpression.substring(voiceStartIndex).trim();
                fullTagExpression = fullTagExpression.substring(0, voiceStartIndex);
            }
            String[] nameAndClasses = fullTagExpression.split("\\.");
            String name = nameAndClasses[0];
            if (nameAndClasses.length > 1) {
                classes = (String[]) Arrays.copyOfRange(nameAndClasses, 1, nameAndClasses.length);
            } else {
                classes = e;
            }
            return new a(name, position, voice, classes);
        }

        public static a a() {
            return new a("", 0, "", new String[0]);
        }
    }

    private static final class b implements Comparable<b> {
        public final int a;
        public final WebvttCssStyle b;

        public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
            return this.a - ((b) obj).a;
        }

        public b(int score, WebvttCssStyle style) {
            this.a = score;
            this.b = style;
        }
    }

    final boolean a(k webvttData, com.google.android.exoplayer2.text.webvtt.d.a builder, List<WebvttCssStyle> styles) {
        String firstLine = webvttData.x();
        Matcher cueHeaderMatcher = a.matcher(firstLine);
        if (cueHeaderMatcher.matches()) {
            return a(null, cueHeaderMatcher, webvttData, builder, this.c, styles);
        }
        cueHeaderMatcher = a.matcher(webvttData.x());
        if (!cueHeaderMatcher.matches()) {
            return false;
        }
        return a(firstLine.trim(), cueHeaderMatcher, webvttData, builder, this.c, styles);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(String cueSettingsList, com.google.android.exoplayer2.text.webvtt.d.a builder) {
        Matcher cueSettingMatcher = b.matcher(cueSettingsList);
        while (cueSettingMatcher.find()) {
            String name = cueSettingMatcher.group(1);
            String value = cueSettingMatcher.group(2);
            try {
                int indexOf;
                if ("line".equals(name)) {
                    indexOf = value.indexOf(44);
                    if (indexOf != -1) {
                        builder.b(a(value.substring(indexOf + 1)));
                        value = value.substring(0, indexOf);
                    } else {
                        builder.b(Integer.MIN_VALUE);
                    }
                    if (value.endsWith("%")) {
                        builder.a(g.b(value)).a(0);
                    } else {
                        indexOf = Integer.parseInt(value);
                        if (indexOf < 0) {
                            indexOf--;
                        }
                        builder.a((float) indexOf).a(1);
                    }
                } else if ("align".equals(name)) {
                    Alignment alignment;
                    switch (value.hashCode()) {
                        case -1364013995:
                            if (value.equals("center")) {
                                indexOf = 2;
                                break;
                            }
                        case -1074341483:
                            if (value.equals("middle")) {
                                indexOf = 3;
                                break;
                            }
                        case 100571:
                            if (value.equals("end")) {
                                indexOf = 4;
                                break;
                            }
                        case 3317767:
                            if (value.equals("left")) {
                                indexOf = 1;
                                break;
                            }
                        case 108511772:
                            if (value.equals("right")) {
                                indexOf = 5;
                                break;
                            }
                        case 109757538:
                            if (value.equals("start")) {
                                indexOf = 0;
                                break;
                            }
                        default:
                            indexOf = -1;
                            break;
                    }
                    switch (indexOf) {
                        case 0:
                        case 1:
                            alignment = Alignment.ALIGN_NORMAL;
                            break;
                        case 2:
                        case 3:
                            alignment = Alignment.ALIGN_CENTER;
                            break;
                        case 4:
                        case 5:
                            alignment = Alignment.ALIGN_OPPOSITE;
                            break;
                        default:
                            alignment = null;
                            break;
                    }
                    builder.a(alignment);
                } else if ("position".equals(name)) {
                    indexOf = value.indexOf(44);
                    if (indexOf != -1) {
                        builder.c(a(value.substring(indexOf + 1)));
                        value = value.substring(0, indexOf);
                    } else {
                        builder.c(Integer.MIN_VALUE);
                    }
                    builder.b(g.b(value));
                } else if ("size".equals(name)) {
                    builder.c(g.b(value));
                } else {
                    new StringBuilder("Unknown cue setting ").append(name).append(":").append(value);
                }
            } catch (NumberFormatException e) {
                new StringBuilder("Skipping bad cue setting: ").append(cueSettingMatcher.group());
            }
        }
    }

    static void a(String id, String markup, com.google.android.exoplayer2.text.webvtt.d.a builder, List<WebvttCssStyle> styles) {
        SpannableStringBuilder spannedText = new SpannableStringBuilder();
        Stack<a> startTagStack = new Stack();
        List<b> scratchStyleMatches = new ArrayList();
        int pos = 0;
        while (pos < markup.length()) {
            char curr = markup.charAt(pos);
            Object obj;
            switch (curr) {
                case '&':
                    int semiColonEndIndex = markup.indexOf(59, pos + 1);
                    int spaceEndIndex = markup.indexOf(32, pos + 1);
                    int entityEndIndex = semiColonEndIndex == -1 ? spaceEndIndex : spaceEndIndex == -1 ? semiColonEndIndex : Math.min(semiColonEndIndex, spaceEndIndex);
                    if (entityEndIndex == -1) {
                        spannedText.append(curr);
                        pos++;
                        break;
                    }
                    String substring = markup.substring(pos + 1, entityEndIndex);
                    obj = -1;
                    switch (substring.hashCode()) {
                        case 3309:
                            if (substring.equals("gt")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 3464:
                            if (substring.equals("lt")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 96708:
                            if (substring.equals("amp")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3374865:
                            if (substring.equals("nbsp")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            spannedText.append('<');
                            break;
                        case 1:
                            spannedText.append('>');
                            break;
                        case 2:
                            spannedText.append(' ');
                            break;
                        case 3:
                            spannedText.append('&');
                            break;
                        default:
                            new StringBuilder("ignoring unsupported entity: '&").append(substring).append(";'");
                            break;
                    }
                    if (entityEndIndex == spaceEndIndex) {
                        spannedText.append(" ");
                    }
                    pos = entityEndIndex + 1;
                    break;
                case '<':
                    if (pos + 1 < markup.length()) {
                        String tagName;
                        int ltPos = pos;
                        boolean isClosingTag = markup.charAt(ltPos + 1) == '/';
                        int indexOf = markup.indexOf(62, ltPos + 1);
                        if (indexOf == -1) {
                            pos = markup.length();
                        } else {
                            pos = indexOf + 1;
                        }
                        boolean isVoidTag = markup.charAt(pos + -2) == '/';
                        if (isClosingTag) {
                            indexOf = 2;
                        } else {
                            indexOf = 1;
                        }
                        int i = ltPos + indexOf;
                        if (isVoidTag) {
                            indexOf = pos - 2;
                        } else {
                            indexOf = pos - 1;
                        }
                        String fullTagExpression = markup.substring(i, indexOf);
                        String trim = fullTagExpression.trim();
                        if (trim.isEmpty()) {
                            tagName = null;
                        } else {
                            tagName = trim.split("[ \\.]")[0];
                        }
                        if (tagName == null) {
                            break;
                        }
                        obj = -1;
                        switch (tagName.hashCode()) {
                            case 98:
                                if (tagName.equals("b")) {
                                    obj = null;
                                    break;
                                }
                                break;
                            case 99:
                                if (tagName.equals("c")) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 105:
                                if (tagName.equals("i")) {
                                    obj = 2;
                                    break;
                                }
                                break;
                            case 117:
                                if (tagName.equals("u")) {
                                    obj = 4;
                                    break;
                                }
                                break;
                            case 118:
                                if (tagName.equals("v")) {
                                    obj = 5;
                                    break;
                                }
                                break;
                            case 3314158:
                                if (tagName.equals("lang")) {
                                    obj = 3;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                obj = 1;
                                break;
                            default:
                                obj = null;
                                break;
                        }
                        if (obj != null) {
                            if (!isClosingTag) {
                                if (!isVoidTag) {
                                    startTagStack.push(a.a(fullTagExpression, spannedText.length()));
                                    break;
                                }
                                break;
                            }
                            while (!startTagStack.isEmpty()) {
                                a startTag = (a) startTagStack.pop();
                                a(id, startTag, spannedText, styles, scratchStyleMatches);
                                if (startTag.a.equals(tagName)) {
                                    break;
                                }
                            }
                            break;
                        }
                        break;
                    }
                    pos++;
                    break;
                default:
                    spannedText.append(curr);
                    pos++;
                    break;
            }
        }
        while (!startTagStack.isEmpty()) {
            a(id, (a) startTagStack.pop(), spannedText, styles, scratchStyleMatches);
        }
        a(id, a.a(), spannedText, styles, scratchStyleMatches);
        builder.a(spannedText);
    }

    private static boolean a(String id, Matcher cueHeaderMatcher, k webvttData, com.google.android.exoplayer2.text.webvtt.d.a builder, StringBuilder textBuilder, List<WebvttCssStyle> styles) {
        try {
            builder.a(g.a(cueHeaderMatcher.group(1))).b(g.a(cueHeaderMatcher.group(2)));
            a(cueHeaderMatcher.group(3), builder);
            textBuilder.setLength(0);
            while (true) {
                String line = webvttData.x();
                if (line == null || line.isEmpty()) {
                    a(id, textBuilder.toString(), builder, (List) styles);
                } else {
                    if (textBuilder.length() > 0) {
                        textBuilder.append("\n");
                    }
                    textBuilder.append(line.trim());
                }
            }
            a(id, textBuilder.toString(), builder, (List) styles);
            return true;
        } catch (NumberFormatException e) {
            new StringBuilder("Skipping cue with bad header: ").append(cueHeaderMatcher.group());
            return false;
        }
    }

    private static int a(String s) {
        int i = -1;
        switch (s.hashCode()) {
            case -1364013995:
                if (s.equals("center")) {
                    i = 1;
                    break;
                }
                break;
            case -1074341483:
                if (s.equals("middle")) {
                    i = 2;
                    break;
                }
                break;
            case 100571:
                if (s.equals("end")) {
                    i = 3;
                    break;
                }
                break;
            case 109757538:
                if (s.equals("start")) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 1;
            case 3:
                return 2;
            default:
                return Integer.MIN_VALUE;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(String cueId, a startTag, SpannableStringBuilder text, List<WebvttCssStyle> styles, List<b> scratchStyleMatches) {
        boolean z;
        int start = startTag.b;
        int end = text.length();
        String str = startTag.a;
        switch (str.hashCode()) {
            case 0:
                if (str.equals("")) {
                    z = true;
                    break;
                }
            case 98:
                if (str.equals("b")) {
                    z = false;
                    break;
                }
            case 99:
                if (str.equals("c")) {
                    z = true;
                    break;
                }
            case 105:
                if (str.equals("i")) {
                    z = true;
                    break;
                }
            case 117:
                if (str.equals("u")) {
                    z = true;
                    break;
                }
            case 118:
                if (str.equals("v")) {
                    z = true;
                    break;
                }
            case 3314158:
                if (str.equals("lang")) {
                    z = true;
                    break;
                }
            default:
                z = true;
                break;
        }
        switch (z) {
            case false:
                text.setSpan(new StyleSpan(1), start, end, 33);
                break;
            case true:
                text.setSpan(new StyleSpan(2), start, end, 33);
                break;
            case true:
                text.setSpan(new UnderlineSpan(), start, end, 33);
                break;
            case true:
            case true:
            case true:
            case true:
                break;
            default:
                return;
        }
        scratchStyleMatches.clear();
        a((List) styles, cueId, startTag, (List) scratchStyleMatches);
        int styleMatchesCount = scratchStyleMatches.size();
        for (int i = 0; i < styleMatchesCount; i++) {
            WebvttCssStyle webvttCssStyle = ((b) scratchStyleMatches.get(i)).b;
            if (webvttCssStyle != null) {
                if (webvttCssStyle.a() != -1) {
                    text.setSpan(new StyleSpan(webvttCssStyle.a()), start, end, 33);
                }
                if (webvttCssStyle.b()) {
                    text.setSpan(new StrikethroughSpan(), start, end, 33);
                }
                if (webvttCssStyle.c()) {
                    text.setSpan(new UnderlineSpan(), start, end, 33);
                }
                if (webvttCssStyle.i()) {
                    text.setSpan(new ForegroundColorSpan(webvttCssStyle.h()), start, end, 33);
                }
                if (webvttCssStyle.k()) {
                    text.setSpan(new BackgroundColorSpan(webvttCssStyle.j()), start, end, 33);
                }
                if (webvttCssStyle.g() != null) {
                    text.setSpan(new TypefaceSpan(webvttCssStyle.g()), start, end, 33);
                }
                if (webvttCssStyle.l() != null) {
                    text.setSpan(new Standard(webvttCssStyle.l()), start, end, 33);
                }
                switch (webvttCssStyle.m()) {
                    case 1:
                        text.setSpan(new AbsoluteSizeSpan((int) webvttCssStyle.n(), true), start, end, 33);
                        break;
                    case 2:
                        text.setSpan(new RelativeSizeSpan(webvttCssStyle.n()), start, end, 33);
                        break;
                    case 3:
                        text.setSpan(new RelativeSizeSpan(webvttCssStyle.n() / 100.0f), start, end, 33);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void a(List<WebvttCssStyle> declaredStyles, String id, a tag, List<b> output) {
        int styleCount = declaredStyles.size();
        for (int i = 0; i < styleCount; i++) {
            WebvttCssStyle style = (WebvttCssStyle) declaredStyles.get(i);
            int score = style.a(id, tag.a, tag.d, tag.c);
            if (score > 0) {
                output.add(new b(score, style));
            }
        }
        Collections.sort(output);
    }
}
