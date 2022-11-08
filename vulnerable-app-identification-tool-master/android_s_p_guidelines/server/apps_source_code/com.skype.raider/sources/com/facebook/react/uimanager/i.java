package com.facebook.react.uimanager;

import java.lang.reflect.Array;

public final class i {

    public static class a {
        double[] a = new double[4];
        double[] b = new double[4];
        double[] c = new double[3];
        double[] d = new double[3];
        double[] e = new double[3];
        double[] f = new double[3];
    }

    private static boolean a(double d) {
        if (!Double.isNaN(d) && Math.abs(d) < 1.0E-5d) {
            return true;
        }
        return false;
    }

    public static void a(double[] transformMatrix, a ctx) {
        com.facebook.infer.annotation.a.a(transformMatrix.length == 16);
        double[] perspective = ctx.a;
        double[] quaternion = ctx.b;
        double[] scale = ctx.c;
        double[] skew = ctx.d;
        double[] translation = ctx.e;
        double[] rotationDegrees = ctx.f;
        if (!a(transformMatrix[15])) {
            int i;
            double[][] matrix = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
            double[] perspectiveMatrix = new double[16];
            for (i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    double value = transformMatrix[(i * 4) + j] / transformMatrix[15];
                    matrix[i][j] = value;
                    int i2 = (i * 4) + j;
                    if (j == 3) {
                        value = 0.0d;
                    }
                    perspectiveMatrix[i2] = value;
                }
            }
            perspectiveMatrix[15] = 1.0d;
            if (!a(b(perspectiveMatrix))) {
                double b;
                double d;
                double d2;
                double d3;
                double d4;
                double d5;
                double d6;
                double d7;
                double d8;
                if (a(matrix[0][3]) && a(matrix[1][3]) && a(matrix[2][3])) {
                    perspective[2] = 0.0d;
                    perspective[1] = 0.0d;
                    perspective[0] = 0.0d;
                    perspective[3] = 1.0d;
                } else {
                    double[] rightHandSide = new double[]{matrix[0][3], matrix[1][3], matrix[2][3], matrix[3][3]};
                    b = b(perspectiveMatrix);
                    if (!a(b)) {
                        d = perspectiveMatrix[0];
                        d2 = perspectiveMatrix[1];
                        d3 = perspectiveMatrix[2];
                        d4 = perspectiveMatrix[3];
                        d5 = perspectiveMatrix[4];
                        d6 = perspectiveMatrix[5];
                        d7 = perspectiveMatrix[6];
                        d8 = perspectiveMatrix[7];
                        double d9 = perspectiveMatrix[8];
                        double d10 = perspectiveMatrix[9];
                        double d11 = perspectiveMatrix[10];
                        double d12 = perspectiveMatrix[11];
                        double d13 = perspectiveMatrix[12];
                        double d14 = perspectiveMatrix[13];
                        double d15 = perspectiveMatrix[14];
                        double d16 = perspectiveMatrix[15];
                        perspectiveMatrix = new double[16];
                        perspectiveMatrix[10] = ((d16 * (d * d6)) + ((((((d2 * d8) * d13) - ((d4 * d6) * d13)) + ((d4 * d5) * d14)) - ((d * d8) * d14)) - ((d2 * d5) * d16))) / b;
                        perspectiveMatrix[11] = (((((((d4 * d6) * d9) - ((d2 * d8) * d9)) - ((d4 * d5) * d10)) + ((d8 * d) * d10)) + ((d2 * d5) * d12)) - ((d * d6) * d12)) / b;
                        perspectiveMatrix[12] = (((((((d7 * d10) * d13) - ((d6 * d11) * d13)) - ((d7 * d9) * d14)) + ((d5 * d11) * d14)) + ((d6 * d9) * d15)) - ((d5 * d10) * d15)) / b;
                        perspectiveMatrix[13] = (((((((d2 * d11) * d13) - ((d3 * d10) * d13)) + ((d3 * d9) * d14)) - ((d * d11) * d14)) - ((d2 * d9) * d15)) + ((d * d10) * d15)) / b;
                        perspectiveMatrix[14] = (((((((d3 * d6) * d13) - ((d2 * d7) * d13)) - ((d3 * d5) * d14)) + ((d * d7) * d14)) + ((d2 * d5) * d15)) - ((d * d6) * d15)) / b;
                        perspectiveMatrix[15] = (((d * d6) * d11) + (((((d3 * d5) * d10) + (((d2 * d7) * d9) - (d9 * (d3 * d6)))) - ((d7 * d) * d10)) - ((d2 * d5) * d11))) / b;
                    }
                    double[] transposedInversePerspectiveMatrix = new double[]{perspectiveMatrix[0], perspectiveMatrix[4], perspectiveMatrix[8], perspectiveMatrix[12], perspectiveMatrix[1], perspectiveMatrix[5], perspectiveMatrix[9], perspectiveMatrix[13], perspectiveMatrix[2], perspectiveMatrix[6], perspectiveMatrix[10], perspectiveMatrix[14], perspectiveMatrix[3], perspectiveMatrix[7], perspectiveMatrix[11], perspectiveMatrix[15]};
                    b = rightHandSide[0];
                    d = rightHandSide[1];
                    d2 = rightHandSide[2];
                    d3 = rightHandSide[3];
                    perspective[0] = (((transposedInversePerspectiveMatrix[0] * b) + (transposedInversePerspectiveMatrix[4] * d)) + (transposedInversePerspectiveMatrix[8] * d2)) + (transposedInversePerspectiveMatrix[12] * d3);
                    perspective[1] = (((transposedInversePerspectiveMatrix[1] * b) + (transposedInversePerspectiveMatrix[5] * d)) + (transposedInversePerspectiveMatrix[9] * d2)) + (transposedInversePerspectiveMatrix[13] * d3);
                    perspective[2] = (((transposedInversePerspectiveMatrix[2] * b) + (transposedInversePerspectiveMatrix[6] * d)) + (transposedInversePerspectiveMatrix[10] * d2)) + (transposedInversePerspectiveMatrix[14] * d3);
                    perspective[3] = (((b * transposedInversePerspectiveMatrix[3]) + (d * transposedInversePerspectiveMatrix[7])) + (transposedInversePerspectiveMatrix[11] * d2)) + (transposedInversePerspectiveMatrix[15] * d3);
                }
                for (i = 0; i < 3; i++) {
                    translation[i] = matrix[3][i];
                }
                double[][] row = (double[][]) Array.newInstance(Double.TYPE, new int[]{3, 3});
                for (i = 0; i < 3; i++) {
                    row[i][0] = matrix[i][0];
                    row[i][1] = matrix[i][1];
                    row[i][2] = matrix[i][2];
                }
                scale[0] = c(row[0]);
                row[0] = a(row[0], scale[0]);
                skew[0] = a(row[0], row[1]);
                row[1] = a(row[1], row[0], -skew[0]);
                skew[0] = a(row[0], row[1]);
                row[1] = a(row[1], row[0], -skew[0]);
                scale[1] = c(row[1]);
                row[1] = a(row[1], scale[1]);
                skew[0] = skew[0] / scale[1];
                skew[1] = a(row[0], row[2]);
                row[2] = a(row[2], row[0], -skew[1]);
                skew[2] = a(row[1], row[2]);
                row[2] = a(row[2], row[1], -skew[2]);
                scale[2] = c(row[2]);
                row[2] = a(row[2], scale[2]);
                skew[1] = skew[1] / scale[2];
                skew[2] = skew[2] / scale[2];
                double[] dArr = row[1];
                double[] dArr2 = row[2];
                double[] pdum3 = new double[3];
                pdum3[0] = (dArr[1] * dArr2[2]) - (dArr[2] * dArr2[1]);
                pdum3[1] = (dArr[2] * dArr2[0]) - (dArr[0] * dArr2[2]);
                pdum3[2] = (dArr[0] * dArr2[1]) - (dArr2[0] * dArr[1]);
                if (a(row[0], pdum3) < 0.0d) {
                    for (i = 0; i < 3; i++) {
                        scale[i] = scale[i] * -1.0d;
                        dArr = row[i];
                        dArr[0] = dArr[0] * -1.0d;
                        dArr = row[i];
                        dArr[1] = dArr[1] * -1.0d;
                        dArr = row[i];
                        dArr[2] = dArr[2] * -1.0d;
                    }
                }
                quaternion[0] = 0.5d * Math.sqrt(Math.max(((1.0d + row[0][0]) - row[1][1]) - row[2][2], 0.0d));
                quaternion[1] = 0.5d * Math.sqrt(Math.max(((1.0d - row[0][0]) + row[1][1]) - row[2][2], 0.0d));
                quaternion[2] = 0.5d * Math.sqrt(Math.max(((1.0d - row[0][0]) - row[1][1]) + row[2][2], 0.0d));
                quaternion[3] = 0.5d * Math.sqrt(Math.max(((1.0d + row[0][0]) + row[1][1]) + row[2][2], 0.0d));
                if (row[2][1] > row[1][2]) {
                    quaternion[0] = -quaternion[0];
                }
                if (row[0][2] > row[2][0]) {
                    quaternion[1] = -quaternion[1];
                }
                if (row[1][0] > row[0][1]) {
                    quaternion[2] = -quaternion[2];
                }
                if (quaternion[0] >= 0.001d || quaternion[0] < 0.0d || quaternion[1] >= 0.001d || quaternion[1] < 0.0d) {
                    b = quaternion[0];
                    d = quaternion[1];
                    d2 = quaternion[2];
                    d3 = quaternion[3];
                    d5 = b * b;
                    d6 = d * d;
                    d7 = d2 * d2;
                    d8 = (b * d) + (d2 * d3);
                    d4 = (((d3 * d3) + d5) + d6) + d7;
                    if (d8 > 0.49999d * d4) {
                        rotationDegrees[0] = 0.0d;
                        rotationDegrees[1] = (Math.atan2(b, d3) * 2.0d) * 57.29577951308232d;
                        rotationDegrees[2] = 90.0d;
                        return;
                    } else if (d8 < d4 * -0.49999d) {
                        rotationDegrees[0] = 0.0d;
                        rotationDegrees[1] = (Math.atan2(b, d3) * -2.0d) * 57.29577951308232d;
                        rotationDegrees[2] = -90.0d;
                        return;
                    } else {
                        rotationDegrees[0] = b(Math.atan2(((2.0d * b) * d3) - ((2.0d * d) * d2), (1.0d - (d5 * 2.0d)) - (2.0d * d7)) * 57.29577951308232d);
                        rotationDegrees[1] = b(Math.atan2(((2.0d * d) * d3) - ((2.0d * b) * d2), (1.0d - (d6 * 2.0d)) - (d7 * 2.0d)) * 57.29577951308232d);
                        rotationDegrees[2] = b(Math.asin(((b * 2.0d) * d) + ((2.0d * d2) * d3)) * 57.29577951308232d);
                        return;
                    }
                }
                rotationDegrees[1] = 0.0d;
                rotationDegrees[0] = 0.0d;
                rotationDegrees[2] = b((Math.atan2(row[0][1], row[0][0]) * 180.0d) / 3.141592653589793d);
            }
        }
    }

    private static double b(double[] matrix) {
        double m00 = matrix[0];
        double m01 = matrix[1];
        double m02 = matrix[2];
        double m03 = matrix[3];
        double m10 = matrix[4];
        double m11 = matrix[5];
        double m12 = matrix[6];
        double m13 = matrix[7];
        double m20 = matrix[8];
        double m21 = matrix[9];
        double m22 = matrix[10];
        double m23 = matrix[11];
        double m30 = matrix[12];
        double m31 = matrix[13];
        double m32 = matrix[14];
        double m33 = matrix[15];
        return (((((((((((((((((((((((((m03 * m12) * m21) * m30) - (((m02 * m13) * m21) * m30)) - (((m03 * m11) * m22) * m30)) + (((m01 * m13) * m22) * m30)) + (((m02 * m11) * m23) * m30)) - (((m01 * m12) * m23) * m30)) - (((m03 * m12) * m20) * m31)) + (((m02 * m13) * m20) * m31)) + (((m03 * m10) * m22) * m31)) - (((m00 * m13) * m22) * m31)) - (((m02 * m10) * m23) * m31)) + (((m00 * m12) * m23) * m31)) + (((m03 * m11) * m20) * m32)) - (((m01 * m13) * m20) * m32)) - (((m03 * m10) * m21) * m32)) + (((m00 * m13) * m21) * m32)) + (((m01 * m10) * m23) * m32)) - (((m00 * m11) * m23) * m32)) - (((m02 * m11) * m20) * m33)) + (((m01 * m12) * m20) * m33)) + (((m02 * m10) * m21) * m33)) - (((m00 * m12) * m21) * m33)) - (((m01 * m10) * m22) * m33)) + (((m00 * m11) * m22) * m33);
    }

    private static double c(double[] a) {
        return Math.sqrt(((a[0] * a[0]) + (a[1] * a[1])) + (a[2] * a[2]));
    }

    private static double[] a(double[] vector, double norm) {
        if (a(norm)) {
            norm = c(vector);
        }
        double im = 1.0d / norm;
        return new double[]{vector[0] * im, vector[1] * im, vector[2] * im};
    }

    private static double a(double[] a, double[] b) {
        return ((a[0] * b[0]) + (a[1] * b[1])) + (a[2] * b[2]);
    }

    private static double[] a(double[] a, double[] b, double bScale) {
        return new double[]{(a[0] * 1.0d) + (b[0] * bScale), (a[1] * 1.0d) + (b[1] * bScale), (a[2] * 1.0d) + (b[2] * bScale)};
    }

    private static double b(double n) {
        return ((double) Math.round(1000.0d * n)) * 0.001d;
    }

    public static void a(double[] matrix) {
        matrix[14] = 0.0d;
        matrix[13] = 0.0d;
        matrix[12] = 0.0d;
        matrix[11] = 0.0d;
        matrix[9] = 0.0d;
        matrix[8] = 0.0d;
        matrix[7] = 0.0d;
        matrix[6] = 0.0d;
        matrix[4] = 0.0d;
        matrix[3] = 0.0d;
        matrix[2] = 0.0d;
        matrix[1] = 0.0d;
        matrix[15] = 1.0d;
        matrix[10] = 1.0d;
        matrix[5] = 1.0d;
        matrix[0] = 1.0d;
    }

    public static void a(double[] m, double x, double y) {
        m[12] = x;
        m[13] = y;
    }
}
