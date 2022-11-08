package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.ao;
import defpackage.ap;
import defpackage.v;

public abstract class ActionBar {

    public class LayoutParams extends MarginLayoutParams {
        public int a;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, v.ActionBarLayout);
            this.a = obtainStyledAttributes.getInt(v.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i) {
            super(i, -2);
            this.a = 0;
            this.a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
            this.a = layoutParams.a;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
        }
    }

    public ao a(ap apVar) {
        return null;
    }

    public abstract void a();

    public void a(int i) {
    }

    public void a(Configuration configuration) {
    }

    public abstract void a(View view, LayoutParams layoutParams);

    public abstract void a(CharSequence charSequence);

    public abstract void a(boolean z);

    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean a(KeyEvent keyEvent) {
        return false;
    }

    public abstract void b();

    public void b(CharSequence charSequence) {
    }

    public void b(boolean z) {
    }

    public abstract void c();

    public void c(boolean z) {
    }

    public abstract int d();

    public void d(boolean z) {
    }

    public abstract void e();

    public abstract void f();

    public Context g() {
        return null;
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    void m() {
    }

    public void h() {
        throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
    }

    public void a(float f) {
        if (f != BitmapDescriptorFactory.HUE_RED) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }
}
