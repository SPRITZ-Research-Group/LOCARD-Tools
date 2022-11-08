package com.microsoft.skypemessagetextinput.e;

import android.text.Editable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.e.f.a;

public class b extends f {
    private StyleSpan a = new StyleSpan(1);
    private BackgroundColorSpan b;

    public b(am originalEditableEntity, int backgroundColor) {
        super(originalEditableEntity);
        this.b = new BackgroundColorSpan(backgroundColor);
    }

    public final String a() {
        return "@" + d().getString("displayText");
    }

    public final a b() {
        return a.WordWiseConvertionToPlainText;
    }

    public final void a(Editable toSource, int startPos, int endPos) {
        super.a(toSource, startPos, endPos);
        toSource.setSpan(this.a, startPos, endPos, 33);
        toSource.setSpan(this.b, startPos, endPos, 33);
    }

    public final void a(Editable fromSource) {
        super.a(fromSource);
        fromSource.removeSpan(this.a);
        fromSource.removeSpan(this.b);
    }

    public final void a(Editable fromSource, aq toEditableEntities) {
        int displayTextStart;
        ar entity = new WritableNativeMap();
        entity.merge(d());
        int spanStart = fromSource.getSpanStart(this);
        if (fromSource.length() <= 0 || fromSource.charAt(spanStart) != '@') {
            displayTextStart = spanStart;
        } else {
            displayTextStart = spanStart + 1;
        }
        entity.putString("displayText", fromSource.subSequence(displayTextStart, fromSource.getSpanEnd(this)).toString());
        toEditableEntities.pushMap(entity);
    }
}
