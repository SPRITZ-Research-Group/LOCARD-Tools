package com.microsoft.skypemessagetextinput.b;

import android.text.Editable;
import android.text.TextWatcher;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.view.a;

public final class d extends i implements TextWatcher {
    private boolean a = true;

    public d(a view) {
        super(view);
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        boolean isEmpty = f().d();
        if (isEmpty != this.a) {
            this.a = isEmpty;
            ar event = new WritableNativeMap();
            event.putBoolean("isEmpty", isEmpty);
            f().a(a.a.onEmptyIndicationChanged, event);
        }
    }
}
