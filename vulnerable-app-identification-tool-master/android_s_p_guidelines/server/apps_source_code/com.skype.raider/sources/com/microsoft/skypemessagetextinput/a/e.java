package com.microsoft.skypemessagetextinput.a;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import com.microsoft.skypemessagetextinput.a.d.a;
import com.microsoft.skypemessagetextinput.e.b;
import com.microsoft.skypemessagetextinput.e.f;
import java.util.Iterator;
import java.util.Vector;

public final class e implements TextWatcher {
    private b a;
    private boolean b = false;
    private boolean c = false;
    private c d;
    private d e;
    private f f;
    private Vector<g> g;

    public e(b editableTextProvider) {
        this.a = editableTextProvider;
        this.d = new c();
        this.f = new f();
    }

    public final void a(@Nullable String value) {
        this.d.a(value);
    }

    public final void a() {
        this.c = true;
    }

    public final void b() {
        this.c = false;
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.g = null;
        this.e = null;
        if (!this.b && !this.c) {
            d dVar;
            Editable editable = this.a.getEditableText();
            int selectionStart = this.a.getSelectionStart();
            int selectionEnd = this.a.getSelectionEnd();
            if (selectionStart >= 2 && selectionStart == selectionEnd && after == 1 && count == 0 && editable.charAt(selectionStart - 1) == ' ' && ((b[]) editable.getSpans(selectionStart - 2, selectionStart - 1, b.class)).length > 0) {
                dVar = new d(a.a, Integer.valueOf(selectionStart - 1), Integer.valueOf(selectionStart));
            } else {
                dVar = null;
            }
            this.e = dVar;
            this.g = f.a(editable, start, count, after);
        }
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void afterTextChanged(Editable editable) {
        this.b = true;
        if (!((this.e != null ? this.d.a(editable, this.e) : false) || this.g == null)) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                f a = gVar.a();
                int spanStart = editable.getSpanStart(a);
                int spanEnd = editable.getSpanEnd(a);
                if (!(spanStart == -1 || spanEnd == -1)) {
                    switch (gVar.b()) {
                        case Delete:
                            a.a(editable);
                            a.c();
                            editable.delete(spanStart, spanEnd);
                            continue;
                        case RestoreContent:
                            editable.replace(spanStart, spanEnd, a.a());
                            continue;
                        case ConvertToPlainText:
                            a.a(editable);
                        case WordWiseConvertionToPlainText:
                            int intValue;
                            String charSequence = editable.subSequence(spanStart, spanEnd).toString();
                            if (gVar.c() != null) {
                                intValue = gVar.c().intValue();
                            } else {
                                intValue = spanEnd;
                            }
                            intValue = charSequence.lastIndexOf(32, intValue);
                            a.a(editable);
                            if (intValue >= 0) {
                                a.a(editable, spanStart, intValue + spanStart);
                                continue;
                            }
                        default:
                            continue;
                    }
                    a.c();
                }
            }
        }
        this.g = null;
        this.e = null;
        this.b = false;
    }
}
