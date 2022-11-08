package android.support.design.widget;

import android.graphics.Outline;

final class c extends b {
    c() {
    }

    public final void getOutline(Outline outline) {
        copyBounds(this.b);
        outline.setOval(this.b);
    }
}
