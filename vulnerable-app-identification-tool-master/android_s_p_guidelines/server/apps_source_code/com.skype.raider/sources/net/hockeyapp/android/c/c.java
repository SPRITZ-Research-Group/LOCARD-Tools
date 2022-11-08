package net.hockeyapp.android.c;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import net.hockeyapp.android.c.a.g;

public final class c {
    private static final String[] a = new String[128];

    static {
        for (int i = 0; i <= 31; i++) {
            a[i] = String.format("\\u%04X", new Object[]{Integer.valueOf(i)});
        }
        a[34] = "\\\"";
        a[92] = "\\\\";
        a[8] = "\\b";
        a[12] = "\\f";
        a[10] = "\\n";
        a[13] = "\\r";
        a[9] = "\\t";
    }

    public static String a(Integer value) {
        return Integer.toString(value.intValue());
    }

    public static String a(Long value) {
        return Long.toString(value.longValue());
    }

    public static String a(String value) {
        if (value == null) {
            return "null";
        }
        if (value.length() == 0) {
            return "\"\"";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        for (int i = 0; i < value.length(); i++) {
            char charAt = value.charAt(i);
            if (charAt < 128) {
                String str = a[charAt];
                if (str == null) {
                    stringBuilder.append(charAt);
                } else {
                    stringBuilder.append(str);
                }
            } else if (charAt == 8232) {
                stringBuilder.append("\\u2028");
            } else if (charAt == 8233) {
                stringBuilder.append("\\u2029");
            } else {
                stringBuilder.append(charAt);
            }
        }
        stringBuilder.append("\"");
        return stringBuilder.toString();
    }

    public static void a(Writer writer, g value) throws IOException {
        if (value != null) {
            value.a(writer);
        }
    }

    public static <T> void a(Writer writer, Map<String, T> map) throws IOException {
        if (map == null || map.isEmpty()) {
            writer.write("null");
            return;
        }
        Iterator<String> iterator = map.keySet().iterator();
        if (iterator.hasNext()) {
            writer.write("{");
            String key = (String) iterator.next();
            Object item = map.get(key);
            writer.write("\"" + key + "\"");
            writer.write(":");
            a(writer, item);
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                writer.write(",");
                writer.write("\"" + key + "\"");
                writer.write(":");
                a(writer, map.get(key));
            }
            writer.write("}");
        }
    }

    private static <T> void a(Writer writer, T item) throws IOException {
        if (item == null) {
            writer.write("null");
        } else if (item instanceof String) {
            writer.write(a((String) item));
        } else if (item instanceof Double) {
            writer.write(Double.toString(((Double) item).doubleValue()));
        } else if (item instanceof Integer) {
            writer.write(Integer.toString(((Integer) item).intValue()));
        } else if (item instanceof Long) {
            writer.write(Long.toString(((Long) item).longValue()));
        } else if (item instanceof g) {
            ((g) item).a(writer);
        } else {
            throw new IOException("Cannot serialize: " + item.toString());
        }
    }
}
