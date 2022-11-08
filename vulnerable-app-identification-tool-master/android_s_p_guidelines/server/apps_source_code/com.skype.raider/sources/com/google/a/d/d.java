package com.google.a.d;

import com.google.a.b.b;
import com.google.a.c;
import com.google.a.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class d extends n {

    private enum a {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    public final b a(String contents, com.google.a.a format, int width, int height, Map<c, ?> hints) throws h {
        if (format == com.google.a.a.CODE_128) {
            return super.a(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + format);
    }

    public final boolean[] a(String contents) {
        int length = contents.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        for (int i = 0; i < length; i++) {
            char c = contents.charAt(i);
            switch (c) {
                case 241:
                case 242:
                case 243:
                case 244:
                    break;
                default:
                    if (c <= 127) {
                        break;
                    }
                    throw new IllegalArgumentException("Bad character in input: " + c);
            }
        }
        Collection<int[]> patterns = new ArrayList();
        int checkSum = 0;
        int checkWeight = 1;
        int codeSet = 0;
        int position = 0;
        while (position < length) {
            int patternIndex;
            int newCodeSet = a(contents, position, codeSet);
            if (newCodeSet == codeSet) {
                switch (contents.charAt(position)) {
                    case 241:
                        patternIndex = 102;
                        break;
                    case 242:
                        patternIndex = 97;
                        break;
                    case 243:
                        patternIndex = 96;
                        break;
                    case 244:
                        if (codeSet != 101) {
                            patternIndex = 100;
                            break;
                        }
                        patternIndex = 101;
                        break;
                    default:
                        switch (codeSet) {
                            case 100:
                                patternIndex = contents.charAt(position) - 32;
                                break;
                            case 101:
                                patternIndex = contents.charAt(position) - 32;
                                if (patternIndex < 0) {
                                    patternIndex += 96;
                                    break;
                                }
                                break;
                            default:
                                patternIndex = Integer.parseInt(contents.substring(position, position + 2));
                                position++;
                                break;
                        }
                }
                position++;
            } else {
                if (codeSet == 0) {
                    switch (newCodeSet) {
                        case 100:
                            patternIndex = 104;
                            break;
                        case 101:
                            patternIndex = 103;
                            break;
                        default:
                            patternIndex = 105;
                            break;
                    }
                }
                patternIndex = newCodeSet;
                codeSet = newCodeSet;
            }
            patterns.add(c.a[patternIndex]);
            checkSum += patternIndex * checkWeight;
            if (position != 0) {
                checkWeight++;
            }
        }
        patterns.add(c.a[checkSum % 103]);
        patterns.add(c.a[106]);
        int codeWidth = 0;
        for (int[] iArr : patterns) {
            for (int width : (int[]) r19.next()) {
                codeWidth += width;
            }
        }
        boolean[] result = new boolean[codeWidth];
        int pos = 0;
        for (int[] pattern : patterns) {
            pos += n.a(result, pos, pattern, true);
        }
        return result;
    }

    private static a a(CharSequence value, int start) {
        int last = value.length();
        if (start >= last) {
            return a.UNCODABLE;
        }
        char c = value.charAt(start);
        if (c == 241) {
            return a.FNC_1;
        }
        if (c < '0' || c > '9') {
            return a.UNCODABLE;
        }
        if (start + 1 >= last) {
            return a.ONE_DIGIT;
        }
        c = value.charAt(start + 1);
        if (c < '0' || c > '9') {
            return a.ONE_DIGIT;
        }
        return a.TWO_DIGITS;
    }

    private static int a(CharSequence value, int start, int oldCode) {
        a lookahead = a(value, start);
        if (lookahead == a.ONE_DIGIT) {
            return 100;
        }
        if (lookahead == a.UNCODABLE) {
            if (start >= value.length()) {
                return 100;
            }
            char c = value.charAt(start);
            if (c < ' ' || (oldCode == 101 && c < '`')) {
                return 101;
            }
            return 100;
        } else if (oldCode == 99) {
            return 99;
        } else {
            if (oldCode != 100) {
                if (lookahead == a.FNC_1) {
                    lookahead = a(value, start + 1);
                }
                if (lookahead == a.TWO_DIGITS) {
                    return 99;
                }
                return 100;
            } else if (lookahead == a.FNC_1) {
                return 100;
            } else {
                lookahead = a(value, start + 2);
                if (lookahead == a.UNCODABLE || lookahead == a.ONE_DIGIT) {
                    return 100;
                }
                if (lookahead != a.FNC_1) {
                    int index = start + 4;
                    while (true) {
                        lookahead = a(value, index);
                        if (lookahead != a.TWO_DIGITS) {
                            break;
                        }
                        index += 2;
                    }
                    if (lookahead != a.ONE_DIGIT) {
                        return 99;
                    }
                    return 100;
                } else if (a(value, start + 3) == a.TWO_DIGITS) {
                    return 99;
                } else {
                    return 100;
                }
            }
        }
    }
}
