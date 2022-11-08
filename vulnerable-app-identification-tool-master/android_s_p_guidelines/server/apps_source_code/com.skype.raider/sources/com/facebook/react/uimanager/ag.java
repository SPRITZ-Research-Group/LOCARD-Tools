package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;

public final class ag {
    private static ThreadLocal<double[]> a = new ThreadLocal<double[]>() {
        protected final /* bridge */ /* synthetic */ Object initialValue() {
            return new double[16];
        }
    };

    private static double a(am transformMap, String key) {
        double value;
        boolean inRadians = true;
        if (transformMap.getType(key) == ReadableType.String) {
            String stringValue = transformMap.getString(key);
            if (stringValue.endsWith("rad")) {
                stringValue = stringValue.substring(0, stringValue.length() - 3);
            } else if (stringValue.endsWith("deg")) {
                inRadians = false;
                stringValue = stringValue.substring(0, stringValue.length() - 3);
            }
            value = (double) Float.parseFloat(stringValue);
        } else {
            value = transformMap.getDouble(key);
        }
        return inRadians ? value : (3.141592653589793d * value) / 180.0d;
    }

    public static void a(al transforms, double[] result) {
        double[] helperMatrix = (double[]) a.get();
        i.a(result);
        int size = transforms.size();
        for (int transformIdx = 0; transformIdx < size; transformIdx++) {
            double a;
            am transform = transforms.getMap(transformIdx);
            String transformType = transform.keySetIterator().nextKey();
            i.a(helperMatrix);
            if ("matrix".equals(transformType)) {
                al matrix = transform.getArray(transformType);
                for (int i = 0; i < 16; i++) {
                    helperMatrix[i] = matrix.getDouble(i);
                }
            } else if ("perspective".equals(transformType)) {
                helperMatrix[11] = -1.0d / transform.getDouble(transformType);
            } else if ("rotateX".equals(transformType)) {
                a = a(transform, transformType);
                helperMatrix[5] = Math.cos(a);
                helperMatrix[6] = Math.sin(a);
                helperMatrix[9] = -Math.sin(a);
                helperMatrix[10] = Math.cos(a);
            } else if ("rotateY".equals(transformType)) {
                a = a(transform, transformType);
                helperMatrix[0] = Math.cos(a);
                helperMatrix[2] = -Math.sin(a);
                helperMatrix[8] = Math.sin(a);
                helperMatrix[10] = Math.cos(a);
            } else if ("rotate".equals(transformType) || "rotateZ".equals(transformType)) {
                a = a(transform, transformType);
                helperMatrix[0] = Math.cos(a);
                helperMatrix[1] = Math.sin(a);
                helperMatrix[4] = -Math.sin(a);
                helperMatrix[5] = Math.cos(a);
            } else if ("scale".equals(transformType)) {
                double scale = transform.getDouble(transformType);
                helperMatrix[0] = scale;
                helperMatrix[5] = scale;
            } else if ("scaleX".equals(transformType)) {
                helperMatrix[0] = transform.getDouble(transformType);
            } else if ("scaleY".equals(transformType)) {
                helperMatrix[5] = transform.getDouble(transformType);
            } else if ("translate".equals(transformType)) {
                double z;
                al value = transform.getArray(transformType);
                double x = value.getDouble(0);
                double y = value.getDouble(1);
                if (value.size() > 2) {
                    z = value.getDouble(2);
                } else {
                    z = 0.0d;
                }
                helperMatrix[12] = x;
                helperMatrix[13] = y;
                helperMatrix[14] = z;
            } else if ("translateX".equals(transformType)) {
                i.a(helperMatrix, transform.getDouble(transformType), 0.0d);
            } else if ("translateY".equals(transformType)) {
                i.a(helperMatrix, 0.0d, transform.getDouble(transformType));
            } else if ("skewX".equals(transformType)) {
                helperMatrix[4] = Math.tan(a(transform, transformType));
            } else if ("skewY".equals(transformType)) {
                helperMatrix[1] = Math.tan(a(transform, transformType));
            } else {
                throw new n("Unsupported transform type: " + transformType);
            }
            a = result[0];
            double d = result[1];
            double d2 = result[2];
            double d3 = result[3];
            double d4 = result[4];
            double d5 = result[5];
            double d6 = result[6];
            double d7 = result[7];
            double d8 = result[8];
            double d9 = result[9];
            double d10 = result[10];
            double d11 = result[11];
            double d12 = result[12];
            double d13 = result[13];
            double d14 = result[14];
            double d15 = result[15];
            double d16 = helperMatrix[0];
            double d17 = helperMatrix[1];
            double d18 = helperMatrix[2];
            double d19 = helperMatrix[3];
            result[0] = (((d16 * a) + (d17 * d4)) + (d18 * d8)) + (d19 * d12);
            result[1] = (((d16 * d) + (d17 * d5)) + (d18 * d9)) + (d19 * d13);
            result[2] = (((d16 * d2) + (d17 * d6)) + (d18 * d10)) + (d19 * d14);
            result[3] = (((d16 * d3) + (d17 * d7)) + (d18 * d11)) + (d19 * d15);
            d16 = helperMatrix[4];
            d17 = helperMatrix[5];
            d18 = helperMatrix[6];
            d19 = helperMatrix[7];
            result[4] = (((d16 * a) + (d17 * d4)) + (d18 * d8)) + (d19 * d12);
            result[5] = (((d16 * d) + (d17 * d5)) + (d18 * d9)) + (d19 * d13);
            result[6] = (((d16 * d2) + (d17 * d6)) + (d18 * d10)) + (d19 * d14);
            result[7] = (((d16 * d3) + (d17 * d7)) + (d18 * d11)) + (d19 * d15);
            d16 = helperMatrix[8];
            d17 = helperMatrix[9];
            d18 = helperMatrix[10];
            d19 = helperMatrix[11];
            result[8] = (((d16 * a) + (d17 * d4)) + (d18 * d8)) + (d19 * d12);
            result[9] = (((d16 * d) + (d17 * d5)) + (d18 * d9)) + (d19 * d13);
            result[10] = (((d16 * d2) + (d17 * d6)) + (d18 * d10)) + (d19 * d14);
            result[11] = (((d16 * d3) + (d17 * d7)) + (d18 * d11)) + (d19 * d15);
            d16 = helperMatrix[12];
            d17 = helperMatrix[13];
            d18 = helperMatrix[14];
            d19 = helperMatrix[15];
            result[12] = (((a * d16) + (d4 * d17)) + (d18 * d8)) + (d19 * d12);
            result[13] = (((d * d16) + (d17 * d5)) + (d18 * d9)) + (d19 * d13);
            result[14] = (((d16 * d2) + (d17 * d6)) + (d18 * d10)) + (d19 * d14);
            result[15] = (((d16 * d3) + (d17 * d7)) + (d18 * d11)) + (d19 * d15);
        }
    }
}
