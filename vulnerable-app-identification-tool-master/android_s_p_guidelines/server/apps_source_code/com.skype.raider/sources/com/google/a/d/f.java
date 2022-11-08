package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.h;
import java.util.Map;

public final class f extends n {
    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        if (format == a.CODE_39) {
            return super.a(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got " + format);
    }

    public final boolean[] a(String contents) {
        int length = contents.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int i;
        int[] widths;
        int codeWidth;
        int i2;
        boolean[] result;
        int pos;
        int[] narrowWhite;
        for (i = 0; i < length; i++) {
            if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i)) < 0) {
                contents = b(contents);
                length = contents.length();
                if (length > 80) {
                    throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length + " (extended full ASCII mode)");
                }
                widths = new int[9];
                codeWidth = length + 25;
                for (i = 0; i < length; i++) {
                    a(e.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i))], widths);
                    for (i2 = 0; i2 < 9; i2++) {
                        codeWidth += widths[i2];
                    }
                }
                result = new boolean[codeWidth];
                a(148, widths);
                pos = n.a(result, 0, widths, true);
                narrowWhite = new int[]{1};
                pos += n.a(result, pos, narrowWhite, false);
                for (i = 0; i < length; i++) {
                    a(e.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i))], widths);
                    pos += n.a(result, pos, widths, true);
                    pos += n.a(result, pos, narrowWhite, false);
                }
                a(148, widths);
                n.a(result, pos, widths, true);
                return result;
            }
        }
        widths = new int[9];
        codeWidth = length + 25;
        for (i = 0; i < length; i++) {
            a(e.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i))], widths);
            for (i2 = 0; i2 < 9; i2++) {
                codeWidth += widths[i2];
            }
        }
        result = new boolean[codeWidth];
        a(148, widths);
        pos = n.a(result, 0, widths, true);
        narrowWhite = new int[]{1};
        pos += n.a(result, pos, narrowWhite, false);
        for (i = 0; i < length; i++) {
            a(e.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i))], widths);
            pos += n.a(result, pos, widths, true);
            pos += n.a(result, pos, narrowWhite, false);
        }
        a(148, widths);
        n.a(result, pos, widths, true);
        return result;
    }

    private static void a(int a, int[] toReturn) {
        for (int i = 0; i < 9; i++) {
            toReturn[i] = (a & (1 << (8 - i))) == 0 ? 1 : 2;
        }
    }

    private static String b(String contents) {
        int length = contents.length();
        StringBuilder extendedContent = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char character = contents.charAt(i);
            switch (character) {
                case 0:
                    extendedContent.append("%U");
                    break;
                case ' ':
                case '-':
                case '.':
                    extendedContent.append(character);
                    break;
                case '@':
                    extendedContent.append("%V");
                    break;
                case '`':
                    extendedContent.append("%W");
                    break;
                default:
                    if (character <= 0 || character >= 27) {
                        if (character <= 26 || character >= ' ') {
                            if ((character <= ' ' || character >= '-') && character != '/' && character != ':') {
                                if (character <= '/' || character >= ':') {
                                    if (character <= ':' || character >= '@') {
                                        if (character <= '@' || character >= '[') {
                                            if (character <= 'Z' || character >= '`') {
                                                if (character <= '`' || character >= '{') {
                                                    if (character > 'z' && character < 128) {
                                                        extendedContent.append('%');
                                                        extendedContent.append((char) ((character - 123) + 80));
                                                        break;
                                                    }
                                                    throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + contents.charAt(i) + "'");
                                                }
                                                extendedContent.append('+');
                                                extendedContent.append((char) ((character - 97) + 65));
                                                break;
                                            }
                                            extendedContent.append('%');
                                            extendedContent.append((char) ((character - 91) + 75));
                                            break;
                                        }
                                        extendedContent.append((char) ((character - 65) + 65));
                                        break;
                                    }
                                    extendedContent.append('%');
                                    extendedContent.append((char) ((character - 59) + 70));
                                    break;
                                }
                                extendedContent.append((char) ((character - 48) + 48));
                                break;
                            }
                            extendedContent.append('/');
                            extendedContent.append((char) ((character - 33) + 65));
                            break;
                        }
                        extendedContent.append('%');
                        extendedContent.append((char) ((character - 27) + 65));
                        break;
                    }
                    extendedContent.append('$');
                    extendedContent.append((char) ((character - 1) + 65));
                    break;
                    break;
            }
        }
        return extendedContent.toString();
    }
}
