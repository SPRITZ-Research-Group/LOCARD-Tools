package com.microsoft.skypemessagetextinput.b;

import android.text.Editable;
import android.text.TextWatcher;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.view.a;

public final class e extends i implements TextWatcher {
    private boolean a = true;

    public e(a view) {
        super(view);
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        boolean isEqual = f().a(editable);
        if (isEqual != this.a) {
            this.a = isEqual;
            ar event = new WritableNativeMap();
            event.putBoolean("isEqual", isEqual);
            f().a(a.a.onEqualsInitialContentChanged, event);
        }
    }
}
