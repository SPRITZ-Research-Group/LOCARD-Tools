package com.facebook.react.e;

import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class a {
    private static final Pattern a = Pattern.compile("(?:^|[/\\\\])(\\d+\\.js)$");

    public static String a(String message, al stack) {
        StringBuilder stringBuilder = new StringBuilder(message).append(", stack:\n");
        for (int i = 0; i < stack.size(); i++) {
            String str;
            am frame = stack.getMap(i);
            StringBuilder append = stringBuilder.append(frame.getString("methodName")).append("@");
            if (frame.hasKey("file") && !frame.isNull("file") && frame.getType("file") == ReadableType.String) {
                Matcher matcher = a.matcher(frame.getString("file"));
                if (matcher.find()) {
                    str = matcher.group(1) + ":";
                    append.append(str).append(frame.getInt("lineNumber"));
                    if (frame.hasKey("column") && !frame.isNull("column") && frame.getType("column") == ReadableType.Number) {
                        stringBuilder.append(":").append(frame.getInt("column"));
                    }
                    stringBuilder.append("\n");
                }
            }
            str = "";
            append.append(str).append(frame.getInt("lineNumber"));
            stringBuilder.append(":").append(frame.getInt("column"));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
