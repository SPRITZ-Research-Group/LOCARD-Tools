package com.microsoft.skypemessagetextinput.view;

import android.text.Editable;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.d.e;
import com.microsoft.skypemessagetextinput.e.f;

public interface a {

    public enum a {
        onNewContentCommitted,
        onNewContentAborted,
        onEmptyIndicationChanged,
        onUncommittedChangesIndicationChanged,
        onEqualsInitialContentChanged,
        onComposingActive,
        onComposingInactive,
        onAutoCompletionRequested,
        onAutoCompletionRequestAborted,
        onAutoCompletionNavigationKey,
        onFocus2,
        onBlur2,
        onEmoticonLoadCompleted,
        onNativeCallCompleted,
        onContentSizeChanged
    }

    public enum b {
        GetContent,
        SetContent,
        InsertContent,
        NotifyBackspacePressedExternally,
        Focus,
        Blur,
        IsFocused,
        DismissKeyboard,
        AutoComplete,
        CancelAutoComplete
    }

    int a(int i, int i2, am amVar);

    void a(f fVar);

    void a(a aVar, ar arVar);

    boolean a(Editable editable);

    e b();

    int c();

    boolean d();

    Integer e();

    void f();

    Editable getEditableText();

    int getId();

    void setCaretPosition(int i);
}
