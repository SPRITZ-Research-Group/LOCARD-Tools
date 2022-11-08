package androidx.recyclerview.widget;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class ah extends SimpleOnGestureListener {
    final /* synthetic */ af a;
    private boolean b = true;

    public final boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    ah(af afVar) {
        this.a = afVar;
    }

    final void a() {
        this.b = false;
    }

    public final void onLongPress(MotionEvent motionEvent) {
        if (this.b) {
            View a = this.a.a(motionEvent);
            if (a != null) {
                cb childViewHolder = this.a.k.getChildViewHolder(a);
                if (childViewHolder != null && this.a.h.c(this.a.k, childViewHolder) && motionEvent.getPointerId(0) == this.a.g) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.a.g);
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    this.a.c = x;
                    this.a.d = y;
                    af afVar = this.a;
                    this.a.f = BitmapDescriptorFactory.HUE_RED;
                    afVar.e = BitmapDescriptorFactory.HUE_RED;
                    if (this.a.h.a()) {
                        this.a.a(childViewHolder, 2);
                    }
                }
            }
        }
    }
}
