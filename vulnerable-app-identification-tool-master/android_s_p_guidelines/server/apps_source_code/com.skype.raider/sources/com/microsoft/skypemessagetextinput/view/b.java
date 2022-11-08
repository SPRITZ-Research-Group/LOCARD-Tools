package com.microsoft.skypemessagetextinput.view;

import android.text.Editable;
import android.text.TextWatcher;

public final class b implements TextWatcher {
    private int a = 0;

    public final int a() {
        return this.a;
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        this.a++;
    }
}
