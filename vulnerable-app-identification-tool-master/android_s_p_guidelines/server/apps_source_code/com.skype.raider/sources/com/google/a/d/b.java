package com.google.a.d;

public final class b extends n {
    private static final char[] a = new char[]{'A', 'B', 'C', 'D'};
    private static final char[] b = new char[]{'T', 'N', '*', 'E'};
    private static final char[] c = new char[]{'/', ':', '+', '.'};
    private static final char d = a[0];

    public final boolean[] a(String contents) {
        if (contents.length() < 2) {
            contents = d + contents + d;
        } else {
            char firstChar = Character.toUpperCase(contents.charAt(0));
            char lastChar = Character.toUpperCase(contents.charAt(contents.length() - 1));
            boolean startsNormal = a.a(a, firstChar);
            boolean endsNormal = a.a(a, lastChar);
            boolean startsAlt = a.a(b, firstChar);
            boolean endsAlt = a.a(b, lastChar);
            if (startsNormal) {
                if (!endsNormal) {
                    throw new IllegalArgumentException("Invalid start/end guards: " + contents);
                }
            } else if (startsAlt) {
                if (!endsAlt) {
                    throw new IllegalArgumentException("Invalid start/end guards: " + contents);
                }
            } else if (endsNormal || endsAlt) {
                throw new IllegalArgumentException("Invalid start/end guards: " + contents);
            } else {
                contents = d + contents + d;
            }
        }
        int resultLength = 20;
        int i = 1;
        while (i < contents.length() - 1) {
            if (Character.isDigit(contents.charAt(i)) || contents.charAt(i) == '-' || contents.charAt(i) == '$') {
                resultLength += 9;
            } else if (a.a(c, contents.charAt(i))) {
                resultLength += 10;
            } else {
                throw new IllegalArgumentException("Cannot encode : '" + contents.charAt(i) + '\'');
            }
            i++;
        }
        boolean[] result = new boolean[((contents.length() - 1) + resultLength)];
        int position = 0;
        int index = 0;
        while (index < contents.length()) {
            boolean color;
            int counter;
            int bit;
            char c = Character.toUpperCase(contents.charAt(index));
            if (index == 0 || index == contents.length() - 1) {
                switch (c) {
                    case '*':
                        c = 'C';
                        break;
                    case 'E':
                        c = 'D';
                        break;
                    case 'N':
                        c = 'B';
                        break;
                    case 'T':
                        c = 'A';
                        break;
                }
            }
            int code = 0;
            i = 0;
            while (i < a.a.length) {
                if (c == a.a[i]) {
                    code = a.b[i];
                    color = true;
                    counter = 0;
                    bit = 0;
                    while (bit < 7) {
                        result[position] = color;
                        position++;
                        if (((code >> (6 - bit)) & 1) != 0 || counter == 1) {
                            color = color;
                            bit++;
                            counter = 0;
                        } else {
                            counter++;
                        }
                    }
                    if (index < contents.length() - 1) {
                        result[position] = false;
                        position++;
                    }
                    index++;
                } else {
                    i++;
                }
            }
            color = true;
            counter = 0;
            bit = 0;
            while (bit < 7) {
                result[position] = color;
                position++;
                if (((code >> (6 - bit)) & 1) != 0) {
                }
                if (color) {
                }
                bit++;
                counter = 0;
            }
            if (index < contents.length() - 1) {
                result[position] = false;
                position++;
            }
            index++;
        }
        return result;
    }
}
