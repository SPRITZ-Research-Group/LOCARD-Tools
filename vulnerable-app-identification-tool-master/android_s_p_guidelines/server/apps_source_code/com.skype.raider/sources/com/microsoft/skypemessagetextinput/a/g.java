package com.microsoft.skypemessagetextinput.a;

import com.microsoft.skypemessagetextinput.e.f;

public final class g {
    private f a;
    private a b;
    private Integer c;

    public enum a {
        Delete,
        RestoreContent,
        ConvertToPlainText,
        WordWiseConvertionToPlainText
    }

    public g(f span, a action, Integer caretPosition) {
        this.a = span;
        this.b = action;
        this.c = caretPosition;
    }

    public final f a() {
        return this.a;
    }

    public final a b() {
        return this.b;
    }

    public final Integer c() {
        return this.c;
    }
}
