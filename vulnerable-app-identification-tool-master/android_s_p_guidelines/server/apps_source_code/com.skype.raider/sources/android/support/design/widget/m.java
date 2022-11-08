package android.support.design.widget;

final class m {
    static int a(int amount, int low, int high) {
        if (amount < low) {
            return low;
        }
        return amount > high ? high : amount;
    }
}
