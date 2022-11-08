package com.microsoft.skypemessagetextinput.b;

import android.text.Editable;
import android.text.TextWatcher;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.p;
import com.microsoft.skypemessagetextinput.d.b;
import com.microsoft.skypemessagetextinput.d.e;
import java.util.HashSet;
import java.util.Set;

public final class c extends i implements TextWatcher {
    private b a;
    private com.facebook.react.uimanager.events.c b;
    private e c = new e(0, 0);
    private int d = 0;
    private boolean e = false;
    private Set<a> f = new HashSet();

    public interface a {
        void a();
    }

    public c(com.microsoft.skypemessagetextinput.view.a view, b deferredExecutor, ai reactContext) {
        super(view);
        this.a = deferredExecutor;
        this.b = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
    }

    public final void a(a l) {
        this.f.add(l);
    }

    public final void b(a l) {
        this.f.remove(l);
    }

    public final void a(int maxHeight) {
        this.d = maxHeight;
        c();
    }

    public final boolean a() {
        return this.e;
    }

    public final void b() {
        c();
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        final c _this = this;
        this.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public final void run() {
                _this.c();
            }
        });
    }

    private void c() {
        int b;
        e b2 = f().b();
        int a = b2.a();
        if (this.d == 0 || b2.b() <= this.d) {
            b = b2.b();
        } else {
            b = this.d;
        }
        e newSize = new e(a, b);
        if (!newSize.a(this.c)) {
            this.c = newSize;
            float widthDip = p.b((float) newSize.a());
            float heightDip = p.b((float) newSize.b());
            this.b.a(new com.facebook.react.views.textinput.b(f().getId(), widthDip, heightDip));
            if (newSize.a() > 0 || newSize.b() > 0) {
                ar event = new WritableNativeMap();
                event.putDouble("width", (double) widthDip);
                event.putDouble("height", (double) heightDip);
                f().a(com.microsoft.skypemessagetextinput.view.a.a.onContentSizeChanged, event);
            }
        }
        boolean verticalScrollingEnabled = newSize.b() == this.d;
        if (verticalScrollingEnabled != this.e) {
            this.e = verticalScrollingEnabled;
            for (a a2 : this.f) {
                a2.a();
            }
        }
    }
}
