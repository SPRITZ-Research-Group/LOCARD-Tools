package com.google.a.d;

public final class r extends p {
    static final int[][] a = new int[][]{new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};
    private static final int[] g = new int[]{1, 1, 1, 1, 1, 1};

    public static String a(String upce) {
        char[] upceChars = new char[6];
        upce.getChars(1, 7, upceChars, 0);
        StringBuilder result = new StringBuilder(12);
        result.append(upce.charAt(0));
        char lastChar = upceChars[5];
        switch (lastChar) {
            case '0':
            case '1':
            case '2':
                result.append(upceChars, 0, 2);
                result.append(lastChar);
                result.append("0000");
                result.append(upceChars, 2, 3);
                break;
            case '3':
                result.append(upceChars, 0, 3);
                result.append("00000");
                result.append(upceChars, 3, 2);
                break;
            case '4':
                result.append(upceChars, 0, 4);
                result.append("00000");
                result.append(upceChars[4]);
                break;
            default:
                result.append(upceChars, 0, 5);
                result.append("0000");
                result.append(lastChar);
                break;
        }
        if (upce.length() >= 8) {
            result.append(upce.charAt(7));
        }
        return result.toString();
    }
}
