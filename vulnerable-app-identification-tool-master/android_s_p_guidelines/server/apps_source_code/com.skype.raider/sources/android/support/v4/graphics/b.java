package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class b {

    private static class a {
        int a;
        boolean b;

        a() {
        }
    }

    public static class b {
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public char a;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public float[] b;

        b(char type, float[] params) {
            this.a = type;
            this.b = params;
        }

        b(b n) {
            this.a = n.a;
            this.b = b.a(n.b, n.b.length);
        }

        public static void a(b[] node, Path path) {
            float[] current = new float[6];
            char previousCommand = 'm';
            for (int i = 0; i < node.length; i++) {
                int i2;
                char c = node[i].a;
                float[] fArr = node[i].b;
                float f = current[0];
                float f2 = current[1];
                float f3 = current[2];
                float f4 = current[3];
                float f5 = current[4];
                float f6 = current[5];
                switch (c) {
                    case 'A':
                    case 'a':
                        i2 = 7;
                        break;
                    case 'C':
                    case 'c':
                        i2 = 6;
                        break;
                    case 'H':
                    case 'V':
                    case 'h':
                    case 'v':
                        i2 = 1;
                        break;
                    case 'L':
                    case 'M':
                    case 'T':
                    case 'l':
                    case 'm':
                    case 't':
                        i2 = 2;
                        break;
                    case 'Q':
                    case 'S':
                    case 'q':
                    case 's':
                        i2 = 4;
                        break;
                    case 'Z':
                    case 'z':
                        path.close();
                        path.moveTo(f5, f6);
                        f4 = f6;
                        f3 = f5;
                        f2 = f6;
                        f = f5;
                        i2 = 2;
                        break;
                    default:
                        i2 = 2;
                        break;
                }
                int i3 = 0;
                float f7 = f6;
                float f8 = f5;
                float f9 = f2;
                float f10 = f;
                while (i3 < fArr.length) {
                    float f11;
                    float f12;
                    boolean z;
                    boolean z2;
                    switch (c) {
                        case 'A':
                            f5 = fArr[i3 + 5];
                            f2 = fArr[i3 + 6];
                            f = fArr[i3 + 0];
                            f11 = fArr[i3 + 1];
                            f12 = fArr[i3 + 2];
                            z = fArr[i3 + 3] != 0.0f;
                            if (fArr[i3 + 4] != 0.0f) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            a(path, f10, f9, f5, f2, f, f11, f12, z, z2);
                            f3 = fArr[i3 + 5];
                            f4 = fArr[i3 + 6];
                            f6 = f7;
                            f5 = f8;
                            f2 = f3;
                            f = f4;
                            f11 = f3;
                            f3 = f4;
                            break;
                        case 'C':
                            path.cubicTo(fArr[i3 + 0], fArr[i3 + 1], fArr[i3 + 2], fArr[i3 + 3], fArr[i3 + 4], fArr[i3 + 5]);
                            f5 = fArr[i3 + 4];
                            f6 = fArr[i3 + 5];
                            f2 = fArr[i3 + 2];
                            f = f6;
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            f3 = fArr[i3 + 3];
                            break;
                        case 'H':
                            path.lineTo(fArr[i3 + 0], f9);
                            f6 = f7;
                            f2 = f3;
                            f = f9;
                            f11 = fArr[i3 + 0];
                            f3 = f4;
                            f5 = f8;
                            break;
                        case 'L':
                            path.lineTo(fArr[i3 + 0], fArr[i3 + 1]);
                            f5 = fArr[i3 + 0];
                            f2 = f3;
                            f = fArr[i3 + 1];
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            f3 = f4;
                            break;
                        case 'M':
                            f5 = fArr[i3 + 0];
                            f6 = fArr[i3 + 1];
                            if (i3 <= 0) {
                                path.moveTo(fArr[i3 + 0], fArr[i3 + 1]);
                                f2 = f3;
                                f = f6;
                                f11 = f5;
                                f3 = f4;
                                break;
                            }
                            path.lineTo(fArr[i3 + 0], fArr[i3 + 1]);
                            f2 = f3;
                            f = f6;
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            f3 = f4;
                            break;
                        case 'Q':
                            path.quadTo(fArr[i3 + 0], fArr[i3 + 1], fArr[i3 + 2], fArr[i3 + 3]);
                            f3 = fArr[i3 + 0];
                            f4 = fArr[i3 + 1];
                            f5 = fArr[i3 + 2];
                            f2 = f3;
                            f = fArr[i3 + 3];
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            f3 = f4;
                            break;
                        case 'S':
                            if (previousCommand == 'c' || previousCommand == 's' || previousCommand == 'C' || previousCommand == 'S') {
                                f6 = (2.0f * f10) - f3;
                                f3 = (2.0f * f9) - f4;
                            } else {
                                f3 = f9;
                                f6 = f10;
                            }
                            path.cubicTo(f6, f3, fArr[i3 + 0], fArr[i3 + 1], fArr[i3 + 2], fArr[i3 + 3]);
                            f3 = fArr[i3 + 0];
                            f4 = fArr[i3 + 1];
                            f5 = fArr[i3 + 2];
                            f2 = f3;
                            f = fArr[i3 + 3];
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            f3 = f4;
                            break;
                        case 'T':
                            if (previousCommand == 'q' || previousCommand == 't' || previousCommand == 'Q' || previousCommand == 'T') {
                                f10 = (2.0f * f10) - f3;
                                f9 = (2.0f * f9) - f4;
                            }
                            path.quadTo(f10, f9, fArr[i3 + 0], fArr[i3 + 1]);
                            f5 = fArr[i3 + 0];
                            f3 = f9;
                            f2 = f10;
                            f = fArr[i3 + 1];
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            break;
                        case 'V':
                            path.lineTo(f10, fArr[i3 + 0]);
                            f5 = f8;
                            f2 = f3;
                            f = fArr[i3 + 0];
                            f11 = f10;
                            f3 = f4;
                            f6 = f7;
                            break;
                        case 'a':
                            f5 = fArr[i3 + 5] + f10;
                            f2 = fArr[i3 + 6] + f9;
                            f = fArr[i3 + 0];
                            f11 = fArr[i3 + 1];
                            f12 = fArr[i3 + 2];
                            z = fArr[i3 + 3] != 0.0f;
                            if (fArr[i3 + 4] != 0.0f) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            a(path, f10, f9, f5, f2, f, f11, f12, z, z2);
                            f3 = f10 + fArr[i3 + 5];
                            f4 = fArr[i3 + 6] + f9;
                            f6 = f7;
                            f5 = f8;
                            f2 = f3;
                            f = f4;
                            f11 = f3;
                            f3 = f4;
                            break;
                        case 'c':
                            path.rCubicTo(fArr[i3 + 0], fArr[i3 + 1], fArr[i3 + 2], fArr[i3 + 3], fArr[i3 + 4], fArr[i3 + 5]);
                            f2 = f10 + fArr[i3 + 2];
                            f = fArr[i3 + 5] + f9;
                            f11 = f10 + fArr[i3 + 4];
                            f6 = f7;
                            f5 = f8;
                            f3 = fArr[i3 + 3] + f9;
                            break;
                        case 'h':
                            path.rLineTo(fArr[i3 + 0], 0.0f);
                            f6 = f7;
                            f2 = f3;
                            f = f9;
                            f11 = f10 + fArr[i3 + 0];
                            f3 = f4;
                            f5 = f8;
                            break;
                        case 'l':
                            path.rLineTo(fArr[i3 + 0], fArr[i3 + 1]);
                            f2 = f3;
                            f = fArr[i3 + 1] + f9;
                            f11 = f10 + fArr[i3 + 0];
                            f6 = f7;
                            f5 = f8;
                            f3 = f4;
                            break;
                        case 'm':
                            f5 = f10 + fArr[i3 + 0];
                            f6 = fArr[i3 + 1] + f9;
                            if (i3 <= 0) {
                                path.rMoveTo(fArr[i3 + 0], fArr[i3 + 1]);
                                f2 = f3;
                                f = f6;
                                f11 = f5;
                                f3 = f4;
                                break;
                            }
                            path.rLineTo(fArr[i3 + 0], fArr[i3 + 1]);
                            f2 = f3;
                            f = f6;
                            f11 = f5;
                            f6 = f7;
                            f5 = f8;
                            f3 = f4;
                            break;
                        case 'q':
                            path.rQuadTo(fArr[i3 + 0], fArr[i3 + 1], fArr[i3 + 2], fArr[i3 + 3]);
                            f2 = f10 + fArr[i3 + 0];
                            f = fArr[i3 + 3] + f9;
                            f11 = f10 + fArr[i3 + 2];
                            f6 = f7;
                            f5 = f8;
                            f3 = fArr[i3 + 1] + f9;
                            break;
                        case 's':
                            if (previousCommand == 'c' || previousCommand == 's' || previousCommand == 'C' || previousCommand == 'S') {
                                f6 = f10 - f3;
                                f3 = f9 - f4;
                            } else {
                                f3 = 0.0f;
                                f6 = 0.0f;
                            }
                            path.rCubicTo(f6, f3, fArr[i3 + 0], fArr[i3 + 1], fArr[i3 + 2], fArr[i3 + 3]);
                            f2 = f10 + fArr[i3 + 0];
                            f = fArr[i3 + 3] + f9;
                            f11 = f10 + fArr[i3 + 2];
                            f6 = f7;
                            f5 = f8;
                            f3 = fArr[i3 + 1] + f9;
                            break;
                        case 't':
                            if (previousCommand == 'q' || previousCommand == 't' || previousCommand == 'Q' || previousCommand == 'T') {
                                f6 = f10 - f3;
                                f4 = f9 - f4;
                            } else {
                                f4 = 0.0f;
                                f6 = 0.0f;
                            }
                            path.rQuadTo(f6, f4, fArr[i3 + 0], fArr[i3 + 1]);
                            f2 = f10 + f6;
                            f = fArr[i3 + 1] + f9;
                            f11 = f10 + fArr[i3 + 0];
                            f6 = f7;
                            f5 = f8;
                            f3 = f4 + f9;
                            break;
                        case 'v':
                            path.rLineTo(0.0f, fArr[i3 + 0]);
                            f5 = f8;
                            f2 = f3;
                            f = fArr[i3 + 0] + f9;
                            f11 = f10;
                            f3 = f4;
                            f6 = f7;
                            break;
                        default:
                            f6 = f7;
                            f5 = f8;
                            f2 = f3;
                            f = f9;
                            f11 = f10;
                            f3 = f4;
                            break;
                    }
                    i3 += i2;
                    f7 = f6;
                    f8 = f5;
                    f9 = f;
                    f10 = f11;
                    previousCommand = c;
                    f4 = f3;
                    f3 = f2;
                }
                current[0] = f10;
                current[1] = f9;
                current[2] = f3;
                current[3] = f4;
                current[4] = f8;
                current[5] = f7;
                previousCommand = node[i].a;
            }
        }

        public final void a(b nodeFrom, b nodeTo, float fraction) {
            for (int i = 0; i < nodeFrom.b.length; i++) {
                this.b[i] = (nodeFrom.b[i] * (1.0f - fraction)) + (nodeTo.b[i] * fraction);
            }
        }

        private static void a(Path p, float x0, float y0, float x1, float y1, float a, float b, float theta, boolean isMoreThanHalf, boolean isPositiveArc) {
            while (true) {
                double thetaD = Math.toRadians((double) theta);
                double cosTheta = Math.cos(thetaD);
                double sinTheta = Math.sin(thetaD);
                double x0p = ((((double) x0) * cosTheta) + (((double) y0) * sinTheta)) / ((double) a);
                double y0p = ((((double) (-x0)) * sinTheta) + (((double) y0) * cosTheta)) / ((double) b);
                double x1p = ((((double) x1) * cosTheta) + (((double) y1) * sinTheta)) / ((double) a);
                double y1p = ((((double) (-x1)) * sinTheta) + (((double) y1) * cosTheta)) / ((double) b);
                double dx = x0p - x1p;
                double dy = y0p - y1p;
                double xm = (x0p + x1p) / 2.0d;
                double ym = (y0p + y1p) / 2.0d;
                double dsq = (dx * dx) + (dy * dy);
                if (dsq != 0.0d) {
                    double disc = (1.0d / dsq) - 0.25d;
                    if (disc < 0.0d) {
                        float adjust = (float) (Math.sqrt(dsq) / 1.99999d);
                        a *= adjust;
                        b *= adjust;
                    } else {
                        double cx;
                        double cy;
                        double s = Math.sqrt(disc);
                        double sdx = s * dx;
                        double sdy = s * dy;
                        if (isMoreThanHalf == isPositiveArc) {
                            cx = xm - sdy;
                            cy = ym + sdx;
                        } else {
                            cx = xm + sdy;
                            cy = ym - sdx;
                        }
                        double eta0 = Math.atan2(y0p - cy, x0p - cx);
                        double sweep = Math.atan2(y1p - cy, x1p - cx) - eta0;
                        if (isPositiveArc != (sweep >= 0.0d)) {
                            if (sweep > 0.0d) {
                                sweep -= 6.283185307179586d;
                            } else {
                                sweep += 6.283185307179586d;
                            }
                        }
                        cx *= (double) a;
                        cy *= (double) b;
                        double tcx = cx;
                        cx = (cx * cosTheta) - (cy * sinTheta);
                        cy = (tcx * sinTheta) + (cy * cosTheta);
                        double d = (double) a;
                        double d2 = (double) b;
                        double d3 = (double) x0;
                        double d4 = (double) y0;
                        int ceil = (int) Math.ceil(Math.abs((4.0d * sweep) / 3.141592653589793d));
                        double cos = Math.cos(thetaD);
                        double sin = Math.sin(thetaD);
                        double cos2 = Math.cos(eta0);
                        double sin2 = Math.sin(eta0);
                        double d5 = sweep / ((double) ceil);
                        double d6 = d3;
                        double d7 = (sin2 * ((-d) * sin)) + (cos2 * (d2 * cos));
                        sin2 = (((-d) * cos) * sin2) - ((d2 * sin) * cos2);
                        double d8 = d4;
                        int i = 0;
                        cos2 = d7;
                        while (i < ceil) {
                            double d9 = eta0 + d5;
                            d3 = Math.sin(d9);
                            double cos3 = Math.cos(d9);
                            double d10 = (((d * cos) * cos3) + cx) - ((d2 * sin) * d3);
                            double d11 = ((d2 * cos) * d3) + (((d * sin) * cos3) + cy);
                            double d12 = (((-d) * cos) * d3) - ((d2 * sin) * cos3);
                            d3 = (d3 * ((-d) * sin)) + (cos3 * (d2 * cos));
                            cos3 = Math.tan((d9 - eta0) / 2.0d);
                            cos3 = ((Math.sqrt((cos3 * (3.0d * cos3)) + 4.0d) - 1.0d) * Math.sin(d9 - eta0)) / 3.0d;
                            sin2 = (sin2 * cos3) + d6;
                            d8 += cos2 * cos3;
                            d6 = d10 - (cos3 * d12);
                            cos3 = d11 - (cos3 * d3);
                            p.rLineTo(0.0f, 0.0f);
                            p.cubicTo((float) sin2, (float) d8, (float) d6, (float) cos3, (float) d10, (float) d11);
                            i++;
                            sin2 = d12;
                            eta0 = d9;
                            d8 = d11;
                            d6 = d10;
                            cos2 = d3;
                        }
                        return;
                    }
                }
                return;
            }
        }
    }

    static float[] a(float[] original, int end) {
        if (end < 0) {
            throw new IllegalArgumentException();
        }
        int originalLength = original.length;
        if (originalLength < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int resultLength = end + 0;
        float[] result = new float[resultLength];
        System.arraycopy(original, 0, result, 0, Math.min(resultLength, originalLength + 0));
        return result;
    }

    public static Path a(String pathData) {
        Path path = new Path();
        b[] nodes = b(pathData);
        if (nodes == null) {
            return null;
        }
        try {
            b.a(nodes, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + pathData, e);
        }
    }

    public static b[] b(String pathData) {
        if (pathData == null) {
            return null;
        }
        int start = 0;
        int end = 1;
        ArrayList<b> list = new ArrayList();
        while (end < pathData.length()) {
            end = a(pathData, end);
            String s = pathData.substring(start, end).trim();
            if (s.length() > 0) {
                a(list, s.charAt(0), c(s));
            }
            start = end;
            end++;
        }
        if (end - start == 1 && start < pathData.length()) {
            a(list, pathData.charAt(start), new float[0]);
        }
        return (b[]) list.toArray(new b[list.size()]);
    }

    public static b[] a(b[] source) {
        if (source == null) {
            return null;
        }
        b[] copy = new b[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = new b(source[i]);
        }
        return copy;
    }

    public static boolean a(b[] nodesFrom, b[] nodesTo) {
        if (nodesFrom == null || nodesTo == null || nodesFrom.length != nodesTo.length) {
            return false;
        }
        int i = 0;
        while (i < nodesFrom.length) {
            if (nodesFrom[i].a != nodesTo[i].a || nodesFrom[i].b.length != nodesTo[i].b.length) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int a(String s, int end) {
        while (end < s.length()) {
            char c = s.charAt(end);
            if (((c - 65) * (c - 90) <= 0 || (c - 97) * (c - 122) <= 0) && c != 'e' && c != 'E') {
                break;
            }
            end++;
        }
        return end;
    }

    private static void a(ArrayList<b> list, char cmd, float[] val) {
        list.add(new b(cmd, val));
    }

    private static float[] c(String s) {
        if (s.charAt(0) == 'z' || s.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] results = new float[s.length()];
            int startPosition = 1;
            a result = new a();
            int totalLength = s.length();
            int count = 0;
            while (startPosition < totalLength) {
                int endPosition;
                int count2;
                Object obj = null;
                result.b = false;
                Object obj2 = null;
                Object obj3 = null;
                int i = startPosition;
                while (i < s.length()) {
                    Object obj4 = null;
                    switch (s.charAt(i)) {
                        case ' ':
                        case ',':
                            obj = 1;
                            break;
                        case '-':
                            if (i != startPosition && obj3 == null) {
                                obj = 1;
                                result.b = true;
                                break;
                            }
                        case '.':
                            if (obj2 != null) {
                                obj = 1;
                                result.b = true;
                                break;
                            }
                            obj2 = 1;
                            break;
                        case 'E':
                        case 'e':
                            obj4 = 1;
                            break;
                    }
                    if (obj == null) {
                        i++;
                        obj3 = obj4;
                    } else {
                        result.a = i;
                        endPosition = result.a;
                        if (startPosition >= endPosition) {
                            count2 = count + 1;
                            results[count] = Float.parseFloat(s.substring(startPosition, endPosition));
                        } else {
                            count2 = count;
                        }
                        if (result.b) {
                            startPosition = endPosition + 1;
                            count = count2;
                        } else {
                            startPosition = endPosition;
                            count = count2;
                        }
                    }
                }
                result.a = i;
                endPosition = result.a;
                if (startPosition >= endPosition) {
                    count2 = count;
                } else {
                    count2 = count + 1;
                    results[count] = Float.parseFloat(s.substring(startPosition, endPosition));
                }
                if (result.b) {
                    startPosition = endPosition + 1;
                    count = count2;
                } else {
                    startPosition = endPosition;
                    count = count2;
                }
            }
            return a(results, count);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + s + "\"", e);
        }
    }
}
