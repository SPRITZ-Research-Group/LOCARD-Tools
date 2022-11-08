package com.facebook.react.views.textinput;

import android.widget.EditText;
import com.facebook.react.b;

public final class c implements b {
    private final EditText a;

    public c(EditText editText) {
        this.a = editText;
    }

    public final boolean a(int focusAction) {
        boolean arrowLeftOrUp;
        boolean arrowRightOrDown;
        if (focusAction == 17 || focusAction == 33) {
            arrowLeftOrUp = true;
        } else {
            arrowLeftOrUp = false;
        }
        if (focusAction == 66 || focusAction == 130) {
            arrowRightOrDown = true;
        } else {
            arrowRightOrDown = false;
        }
        if (!arrowLeftOrUp && !arrowRightOrDown) {
            return true;
        }
        boolean z;
        int currentPosition = this.a.getSelectionStart();
        if (currentPosition != this.a.getSelectionEnd()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        if (arrowLeftOrUp && currentPosition == 0) {
            return true;
        }
        if (arrowRightOrDown && currentPosition == this.a.getText().length()) {
            return true;
        }
        return false;
    }
}
