package com.microsoft.skypemessagetextinput.e;

import android.text.Editable;
import android.text.style.URLSpan;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.e.f.a;

public final class e extends f {
    private URLSpan a;

    public e(am originalEditableEntity) {
        super(originalEditableEntity);
        this.a = new URLSpan(originalEditableEntity.getString("href"));
    }

    public final String a() {
        return d().getString("href");
    }

    public final a b() {
        return a.InPlaceEditable;
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
        entity.putString("href", fromSource.subSequence(fromSource.getSpanStart(this), fromSource.getSpanEnd(this)).toString());
        toEditableEntities.pushMap(entity);
    }
}
