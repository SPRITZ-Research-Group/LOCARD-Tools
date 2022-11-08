package com.microsoft.skypemessagetextinput.b;

import android.text.Editable;
import android.text.TextWatcher;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.view.a;

public final class h extends g implements TextWatcher {
    private boolean a = false;

    public h(a view) {
        super(view);
    }

    public final void a() {
        if (this.a) {
            this.a = false;
            ar event = new WritableNativeMap();
            event.putBoolean("hasUncommittedChanges", false);
            f().a(a.a.onUncommittedChangesIndicationChanged, event);
        }
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        if (!e() && !this.a) {
            this.a = true;
            ar event = new WritableNativeMap();
            event.putBoolean("hasUncommittedChanges", true);
            f().a(a.a.onUncommittedChangesIndicationChanged, event);
        }
    }
}
