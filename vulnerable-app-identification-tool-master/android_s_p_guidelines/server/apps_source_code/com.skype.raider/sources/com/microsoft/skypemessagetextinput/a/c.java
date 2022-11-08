package com.microsoft.skypemessagetextinput.a;

import android.support.annotation.Nullable;
import android.text.Editable;

public final class c {
    private String a = ".,:;?!";

    public final void a(@Nullable String value) {
        if (value == null) {
            value = ".,:;?!";
        }
        this.a = value;
    }

    public final boolean a(Editable toText, d operation) {
        switch (operation.a()) {
            case a:
                if (Integer.valueOf(this.a.indexOf(toText.charAt(operation.b().intValue() + 1))).intValue() >= 0) {
                    toText.delete(operation.b().intValue(), operation.c().intValue());
                    return true;
                }
                break;
        }
        return false;
    }
}
