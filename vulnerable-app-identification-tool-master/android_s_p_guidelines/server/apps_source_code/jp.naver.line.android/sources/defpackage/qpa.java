package defpackage;

import android.text.TextWatcher;

/* renamed from: qpa */
public interface qpa {
    void a();

    void addTextChangedListener(TextWatcher textWatcher);

    CharSequence getText();

    void removeTextChangedListener(TextWatcher textWatcher);

    void setImeEventListener(qpd qpd);
}
