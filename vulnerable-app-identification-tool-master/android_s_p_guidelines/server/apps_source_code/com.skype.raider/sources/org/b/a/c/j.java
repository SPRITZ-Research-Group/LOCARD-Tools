package org.b.a.c;

import java.io.File;

public final class j {
    public static final String a = System.getProperty("line.separator");

    public static boolean a(Object x, Object y) {
        return x == y;
    }

    public static String a(String s) {
        return s.substring(1, s.length() - 1);
    }

    public static String b(String fullFileName) {
        if (fullFileName == null) {
            return null;
        }
        return new File(fullFileName).getName();
    }

    public static String c(String name) {
        if (name == null) {
            return "/";
        }
        String parent;
        if (name == null) {
            parent = null;
        } else {
            int lastIndexOf = name.lastIndexOf(47);
            if (lastIndexOf > 0) {
                parent = name.substring(0, lastIndexOf);
            } else if (lastIndexOf == 0) {
                parent = "/";
            } else {
                parent = "";
            }
        }
        String prefix = parent;
        if (parent.endsWith("/")) {
            return prefix;
        }
        return prefix + '/';
    }

    public static String d(String s) {
        return s.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\t", "\\\\t");
    }

    public static e a(String s, int index) {
        int line = 1;
        int charPos = 0;
        for (int p = 0; p < index; p++) {
            if (s.charAt(p) == 10) {
                line++;
                charPos = 0;
            } else {
                charPos++;
            }
        }
        return new e(line, charPos);
    }
}
