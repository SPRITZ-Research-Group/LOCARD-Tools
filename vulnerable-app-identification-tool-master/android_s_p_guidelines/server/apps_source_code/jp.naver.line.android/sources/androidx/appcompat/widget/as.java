package androidx.appcompat.widget;

final class as implements Runnable {
    final /* synthetic */ aq a;

    as(aq aqVar) {
        this.a = aqVar;
    }

    public final void run() {
        this.a.a = null;
        this.a.drawableStateChanged();
    }
}
