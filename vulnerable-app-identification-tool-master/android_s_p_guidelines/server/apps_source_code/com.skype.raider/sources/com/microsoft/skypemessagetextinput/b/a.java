package com.microsoft.skypemessagetextinput.b;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.microsoft.skypemessagetextinput.e.f;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class a extends i implements TextWatcher, OnKeyListener {
    private long a = 500;
    private com.microsoft.skypemessagetextinput.c.a b;
    private Integer c = Integer.valueOf(0);
    private Integer d = null;
    private Integer e = null;
    private Integer f = null;
    private com.microsoft.skypemessagetextinput.d.a g = new com.microsoft.skypemessagetextinput.d.a();
    private ScheduledFuture<?> h;

    private enum a {
        Instant,
        Delayed
    }

    public a(com.microsoft.skypemessagetextinput.view.a view, com.microsoft.skypemessagetextinput.c.a viewSpecificAutoCompletionTriggersStore) {
        super(view);
        this.b = viewSpecificAutoCompletionTriggersStore;
    }

    public final void a() {
        b();
        this.g.a();
    }

    public final void b() {
        c();
        d();
    }

    public final void a(@Nullable Integer waitTime) {
        long j;
        if (waitTime == null) {
            j = 500;
        } else {
            j = (long) waitTime.intValue();
        }
        this.a = j;
    }

    public final void a(am response) {
        int requestId = response.getInt("requestId");
        int dataVersion = response.getInt("dataVersion");
        if (this.d != null && requestId == this.d.intValue()) {
            int startPos = this.e.intValue();
            int endPos = this.f.intValue();
            this.d = null;
            this.e = null;
            this.f = null;
            if (dataVersion == f().c()) {
                f().setCaretPosition(startPos + f().a(startPos, endPos, response.getMap("editableEntity")));
            }
        }
    }

    public final void b(am response) {
        int requestId = response.getInt("requestId");
        if (this.d != null && requestId == this.d.intValue()) {
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        d();
        boolean autoCompletionWasActive = this.d != null;
        if (a(editable, a(a.Instant)) == null) {
            if (!autoCompletionWasActive) {
                final a _this = this;
                this.h = this.g.a(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        if (_this.d == null && _this.a(_this.f().getEditableText(), _this.a(a.Delayed)) == null) {
                            _this.c();
                        }
                    }
                }, this.a);
            } else if (a(editable, a(a.Delayed)) == null) {
                c();
            }
        }
    }

    public final boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
        if (this.d != null) {
            switch (keyCode) {
                case 19:
                case 20:
                case 61:
                case 66:
                case 92:
                case 93:
                case 111:
                case 160:
                    ar event = new WritableNativeMap();
                    event.putBoolean("altKey", keyEvent.isAltPressed());
                    event.putBoolean("ctrlKey", keyEvent.isCtrlPressed());
                    String str = "keyCode";
                    switch (keyCode) {
                        case 19:
                            keyCode = 19;
                            break;
                        case 20:
                            keyCode = 20;
                            break;
                        case 61:
                            keyCode = 9;
                            break;
                        case 66:
                        case 160:
                            keyCode = 13;
                            break;
                        case 92:
                            keyCode = 92;
                            break;
                        case 93:
                            keyCode = 93;
                            break;
                        case 111:
                            keyCode = 27;
                            break;
                    }
                    event.putInt(str, keyCode);
                    event.putBoolean("metaKey", keyEvent.isMetaPressed());
                    event.putBoolean("shiftKey", keyEvent.isShiftPressed());
                    f().a(com.microsoft.skypemessagetextinput.view.a.a.onAutoCompletionNavigationKey, event);
                    return true;
            }
        }
        return false;
    }

    private ar a(Editable inEditable, List<String> regexes) {
        Integer caretPos = f().e();
        if (caretPos == null || caretPos.intValue() == 0 || caretPos.intValue() > inEditable.length()) {
            return null;
        }
        String textInFrontOfCaret = inEditable.subSequence(0, caretPos.intValue()).toString();
        for (String triggerRegex : regexes) {
            Matcher result = Pattern.compile(triggerRegex, 40).matcher(textInFrontOfCaret);
            if (result.matches() && result.groupCount() == 1) {
                String matchingText = result.group(1);
                int matchStartPos = caretPos.intValue() - matchingText.length();
                int matchEndPos = caretPos.intValue();
                if (((f[]) inEditable.getSpans(matchStartPos, matchEndPos, f.class)).length <= 0) {
                    Integer num = this.c;
                    this.c = Integer.valueOf(this.c.intValue() + 1);
                    this.d = num;
                    this.e = Integer.valueOf(matchStartPos);
                    this.f = Integer.valueOf(matchEndPos);
                    ar event = new WritableNativeMap();
                    event.putInt("requestId", this.d.intValue());
                    event.putInt("dataVersion", f().c());
                    event.putString("searchText", matchingText);
                    event.putString("triggeredByRegex", triggerRegex);
                    f().a(com.microsoft.skypemessagetextinput.view.a.a.onAutoCompletionRequested, event);
                    return event;
                }
            }
        }
        return null;
    }

    private List<String> a(a triggerType) {
        switch (triggerType) {
            case Instant:
                return this.b.a();
            case Delayed:
                return this.b.b();
            default:
                return null;
        }
    }

    private void c() {
        if (this.d != null) {
            f().a(com.microsoft.skypemessagetextinput.view.a.a.onAutoCompletionRequestAborted, new WritableNativeMap());
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    private void d() {
        if (this.h != null) {
            this.h.cancel(false);
            this.h = null;
        }
    }
}
