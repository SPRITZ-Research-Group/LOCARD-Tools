package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.ParserException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class HlsParserUtil {
    private static final String BOOLEAN_NO = "NO";
    private static final String BOOLEAN_YES = "YES";

    private HlsParserUtil() {
    }

    public static String parseStringAttr(String str, Pattern pattern, String str2) throws ParserException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        StringBuilder stringBuilder = new StringBuilder("Couldn't match ");
        stringBuilder.append(str2);
        stringBuilder.append(" tag in ");
        stringBuilder.append(str);
        throw new ParserException(stringBuilder.toString());
    }

    public static int parseIntAttr(String str, Pattern pattern, String str2) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, str2));
    }

    public static double parseDoubleAttr(String str, Pattern pattern, String str2) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, str2));
    }

    public static String parseOptionalStringAttr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1) : null;
    }

    public static boolean parseOptionalBooleanAttr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? BOOLEAN_YES.equals(matcher.group(1)) : false;
    }

    public static Pattern compileBooleanAttrPattern(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("=(YES|NO)");
        return Pattern.compile(stringBuilder.toString());
    }
}
