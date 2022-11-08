package com.microsoft.skypemessagetextinput.e;

import android.text.Editable;
import android.text.style.BackgroundColorSpan;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;

public final class a extends f {
    private BackgroundColorSpan a;

    public a(am originalEditableEntity, int backgroundColor) {
        super(originalEditableEntity);
        this.a = new BackgroundColorSpan(backgroundColor);
    }

    public final String a() {
        return d().getString("displayText");
    }

    public final com.microsoft.skypemessagetextinput.e.f.a b() {
        return com.microsoft.skypemessagetextinput.e.f.a.NotEditable;
    }

    public final void a(Editable toSource, int startPos, int endPos) {
        super.a(toSource, startPos, endPos);
        toSource.setSpan(this.a, startPos, endPos, 33);
    }

    public final void a(Editable fromSource) {
        super.a(fromSource);
        fromSource.removeSpan(this.a);
    }

    public final void a(Editable fromSource, aq toEditableEntities) {
        ar entity = new WritableNativeMap();
        entity.merge(d());
        toEditableEntities.pushMap(entity);
    }
}
