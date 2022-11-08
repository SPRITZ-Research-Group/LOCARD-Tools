package com.microsoft.skypemessagetextinput.e;

import android.text.Editable;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.ae;
import com.microsoft.skypemessagetextinput.view.a;
import net.hockeyapp.android.j;

public class d extends f {
    private c a;

    public d(am originalEditableEntity, a view, ae context) {
        super(originalEditableEntity);
        this.a = new c(this, context, view, originalEditableEntity.hasKey(j.FRAGMENT_URL) ? originalEditableEntity.getString(j.FRAGMENT_URL) : null, d().getString("displayText"));
    }

    public final void c() {
        super.c();
        this.a.a();
    }

    public final String a() {
        return "(" + d().getString("emoticonName") + ")";
    }

    public final f.a b() {
        return f.a.NotEditable;
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
