package androidx.core.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;

final class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.e) {
            if (this.a.c) {
                this.a.c = false;
                this.a.a.c();
            }
            b bVar = this.a.a;
            if (bVar.e() || !this.a.a()) {
                this.a.e = false;
                return;
            }
            if (this.a.d) {
                this.a.d = false;
                a aVar = this.a;
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                aVar.b.onTouchEvent(obtain);
                obtain.recycle();
            }
            bVar.f();
            this.a.a(bVar.i());
            hs.a(this.a.b, (Runnable) this);
        }
    }
}
