package com.skype.rt;

import android.content.Context;

public final class Spl {

    public static abstract class Pii<T> {
        static boolean anonymize = Spl.getNativeAnonymizeFlag();
        final T value;

        abstract T getValue();

        public static Pii<Byte> omit(byte value) {
            return new BytePii(value);
        }

        public static Pii<Short> omit(short value) {
            return new ShortPii(value);
        }

        public static Pii<Integer> omit(int value) {
            return new IntegerPii(value);
        }

        public static Pii<Long> omit(long value) {
            return new LongPii(value);
        }

        public static Pii<Float> omit(float value) {
            return new FloatPii(value);
        }

        public static Pii<Double> omit(double value) {
            return new DoublePii(value);
        }

        public static Pii<Character> omit(char value) {
            return new CharPii(value);
        }

        public static Pii<String> omit(String value) {
            return new StringPii(value);
        }

        Pii(T value) {
            this.value = value;
        }

        static void updateAnonymizeStatus() {
            anonymize = Spl.getNativeAnonymizeFlag();
        }
    }

    static final class BytePii extends Pii<Byte> {
        BytePii(byte value) {
            super(Byte.valueOf(value));
        }

        final Byte getValue() {
            return Byte.valueOf(anonymize ? (byte) 0 : ((Byte) this.value).byteValue());
        }
    }

    static final class CharPii extends Pii<Character> {
        CharPii(char value) {
            super(Character.valueOf(value));
        }

        final Character getValue() {
            return Character.valueOf(anonymize ? ' ' : ((Character) this.value).charValue());
        }
    }

    static final class DoublePii extends Pii<Double> {
        DoublePii(double value) {
            super(Double.valueOf(value));
        }

        final Double getValue() {
            return Double.valueOf(anonymize ? 0.0d : ((Double) this.value).doubleValue());
        }
    }

    static final class FloatPii extends Pii<Float> {
        FloatPii(float value) {
            super(Float.valueOf(value));
        }

        final Float getValue() {
            return Float.valueOf(anonymize ? 0.0f : ((Float) this.value).floatValue());
        }
    }

    static final class IntegerPii extends Pii<Integer> {
        IntegerPii(int value) {
            super(Integer.valueOf(value));
        }

        final Integer getValue() {
            return Integer.valueOf(anonymize ? 0 : ((Integer) this.value).intValue());
        }
    }

    static final class LongPii extends Pii<Long> {
        LongPii(long value) {
            super(Long.valueOf(value));
        }

        final Long getValue() {
            return Long.valueOf(anonymize ? 0 : ((Long) this.value).longValue());
        }
    }

    static final class ShortPii extends Pii<Short> {
        ShortPii(short value) {
            super(Short.valueOf(value));
        }

        final Short getValue() {
            return Short.valueOf(anonymize ? (short) 0 : ((Short) this.value).shortValue());
        }
    }

    static final class StringPii extends Pii<String> {
        StringPii(String value) {
            super(value);
        }

        final String getValue() {
            return anonymize ? "<censored>" : (String) this.value;
        }
    }

    private static native boolean getNativeAnonymizeFlag();

    public static native long getSysInfoNodeID();

    public static void setContext(Context ctx) {
        RtContext.setContext(ctx);
    }
}
