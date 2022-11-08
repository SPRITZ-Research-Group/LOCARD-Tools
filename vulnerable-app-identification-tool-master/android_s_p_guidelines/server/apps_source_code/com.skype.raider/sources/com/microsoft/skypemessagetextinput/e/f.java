package com.microsoft.skypemessagetextinput.e;

import android.text.Editable;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;

public abstract class f {
    private am a;

    public enum a {
        InPlaceEditable,
        NotEditable,
        ConvertToPlainText,
        WordWiseConvertionToPlainText
    }

    public abstract String a();

    public abstract void a(Editable editable, aq aqVar);

    public abstract a b();

    public f(am originalEditableEntity) {
        this.a = originalEditableEntity;
    }

    public void c() {
    }

    public final void a(Editable toSource, int startPos, String tailingString) {
        String displayText = a();
        toSource.insert(startPos, displayText);
        a(toSource, startPos, displayText.length() + startPos);
        if (tailingString.length() > 0) {
            toSource.insert(displayText.length() + startPos, tailingString);
        }
    }

    public void a(Editable toSource, int startPos, int endPos) {
        toSource.setSpan(this, startPos, endPos, 33);
    }

    public void a(Editable fromSource) {
        fromSource.removeSpan(this);
    }

    protected final am d() {
        return this.a;
    }
}
