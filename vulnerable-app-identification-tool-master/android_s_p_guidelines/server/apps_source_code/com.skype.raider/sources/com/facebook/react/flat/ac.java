package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.k.c;
import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.a.a;
import javax.annotation.Nullable;

class ac extends s {
    private v d = new v();

    ac() {
    }

    public final void a(float width) {
        super.a(width);
        if (this.d.e() != width) {
            n().a(width);
            a(true);
        }
    }

    public final void b(float height) {
        super.b(height);
        if (this.d.f() != height) {
            n().b(height);
            a(true);
        }
    }

    protected final void b(SpannableStringBuilder builder) {
        builder.append("I");
    }

    protected final void a(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        this.d.g();
        builder.setSpan(this.d, begin, end, 17);
    }

    @ReactProp(name = "src")
    public void setSource(@Nullable al sources) {
        a imageSource;
        b bVar = null;
        String source = (sources == null || sources.size() == 0) ? null : sources.getMap(0).getString(ReactVideoViewManager.PROP_SRC_URI);
        if (source == null) {
            imageSource = null;
        } else {
            imageSource = new a(E(), source);
        }
        v n = n();
        if (imageSource != null) {
            bVar = c.a(imageSource.b()).q();
        }
        n.a(bVar);
    }

    private v n() {
        if (this.d.h()) {
            this.d = this.d.d();
        }
        return this.d;
    }
}
