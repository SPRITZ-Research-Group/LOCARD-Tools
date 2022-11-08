package com.google.a.b.a;

final class b {
    private final a a;
    private final int[] b;

    b(a field, int[] coefficients) {
        if (coefficients.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = field;
        int coefficientsLength = coefficients.length;
        if (coefficientsLength <= 1 || coefficients[0] != 0) {
            this.b = coefficients;
            return;
        }
        int firstNonZero = 1;
        while (firstNonZero < coefficientsLength && coefficients[firstNonZero] == 0) {
            firstNonZero++;
        }
        if (firstNonZero == coefficientsLength) {
            this.b = new int[]{0};
            return;
        }
        this.b = new int[(coefficientsLength - firstNonZero)];
        System.arraycopy(coefficients, firstNonZero, this.b, 0, this.b.length);
    }

    final int[] a() {
        return this.b;
    }

    private boolean b() {
        return this.b[0] == 0;
    }

    private int a(int degree) {
        return this.b[(this.b.length - 1) - degree];
    }

    private b c(b other) {
        if (!this.a.equals(other.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (b()) {
            return other;
        } else {
            if (other.b()) {
                return this;
            }
            int[] smallerCoefficients = this.b;
            int[] largerCoefficients = other.b;
            if (smallerCoefficients.length > largerCoefficients.length) {
                int[] temp = smallerCoefficients;
                smallerCoefficients = largerCoefficients;
                largerCoefficients = temp;
            }
            int[] sumDiff = new int[largerCoefficients.length];
            int lengthDiff = largerCoefficients.length - smallerCoefficients.length;
            System.arraycopy(largerCoefficients, 0, sumDiff, 0, lengthDiff);
            for (int i = lengthDiff; i < largerCoefficients.length; i++) {
                sumDiff[i] = a.b(smallerCoefficients[i - lengthDiff], largerCoefficients[i]);
            }
            return new b(this.a, sumDiff);
        }
    }

    final b a(b other) {
        if (!this.a.equals(other.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (b() || other.b()) {
            return this.a.a();
        } else {
            int[] aCoefficients = this.b;
            int aLength = aCoefficients.length;
            int[] bCoefficients = other.b;
            int bLength = bCoefficients.length;
            int[] product = new int[((aLength + bLength) - 1)];
            for (int i = 0; i < aLength; i++) {
                int aCoeff = aCoefficients[i];
                for (int j = 0; j < bLength; j++) {
                    product[i + j] = a.b(product[i + j], this.a.c(aCoeff, bCoefficients[j]));
                }
            }
            return new b(this.a, product);
        }
    }

    final b a(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        } else if (coefficient == 0) {
            return this.a.a();
        } else {
            int size = this.b.length;
            int[] product = new int[(size + degree)];
            for (int i = 0; i < size; i++) {
                product[i] = this.a.c(this.b[i], coefficient);
            }
            return new b(this.a, product);
        }
    }

    final b[] b(b other) {
        if (!this.a.equals(other.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (other.b()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            b quotient = this.a.a();
            b remainder = this;
            int inverseDenominatorLeadingTerm = this.a.c(other.a(other.b.length - 1));
            while (remainder.b.length - 1 >= other.b.length - 1 && !remainder.b()) {
                int degreeDifference = (remainder.b.length - 1) - (other.b.length - 1);
                int scale = this.a.c(remainder.a(remainder.b.length - 1), inverseDenominatorLeadingTerm);
                b term = other.a(degreeDifference, scale);
                quotient = quotient.c(this.a.a(degreeDifference, scale));
                remainder = remainder.c(term);
            }
            return new b[]{quotient, remainder};
        }
    }

    public final String toString() {
        StringBuilder result = new StringBuilder((this.b.length - 1) * 8);
        for (int degree = this.b.length - 1; degree >= 0; degree--) {
            int coefficient = a(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    result.append(" - ");
                    coefficient = -coefficient;
                } else if (result.length() > 0) {
                    result.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int alphaPower = this.a.b(coefficient);
                    if (alphaPower == 0) {
                        result.append('1');
                    } else if (alphaPower == 1) {
                        result.append('a');
                    } else {
                        result.append("a^");
                        result.append(alphaPower);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        result.append('x');
                    } else {
                        result.append("x^");
                        result.append(degree);
                    }
                }
            }
        }
        return result.toString();
    }
}
