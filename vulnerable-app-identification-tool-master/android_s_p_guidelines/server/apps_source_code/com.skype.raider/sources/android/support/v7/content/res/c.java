package android.support.v7.content.res;

import java.lang.reflect.Array;

final class c {
    static final /* synthetic */ boolean a = (!c.class.desiredAssertionStatus());

    public static <T> T[] a(T[] array, int currentSize, T element) {
        if (a || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                T[] newArray = (Object[]) Array.newInstance(array.getClass().getComponentType(), a(currentSize));
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        throw new AssertionError();
    }

    public static int[] a(int[] array, int currentSize, int element) {
        if (a || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                int[] newArray = new int[a(currentSize)];
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        throw new AssertionError();
    }

    private static int a(int currentSize) {
        return currentSize <= 4 ? 8 : currentSize * 2;
    }

    private c() {
    }
}
