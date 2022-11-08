package com.facebook.react;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import com.facebook.react.views.view.b;
import java.util.ArrayList;
import java.util.Iterator;

public final class o {
    private final k a = new k();
    private final ArrayList<a> b = new ArrayList();
    private int c = -1;

    class a {
        final /* synthetic */ o a;
        private int b;
        private int c;
        private int d;

        /* synthetic */ a(o x0, int x1, int x2, int x3, byte b) {
            this(x0, x1, x2, x3);
        }

        private a(o this$0, int _keyCode, int _modifier, int _focusAction) {
            this.a = this$0;
            this.b = _keyCode;
            this.c = _modifier;
            this.d = _focusAction;
        }
    }

    public o() {
        this.b.add(new a(this, 61, 1, 1, (byte) 0));
        this.b.add(new a(this, 61, 0, 2, (byte) 0));
        this.b.add(new a(this, 22, 0, 66, (byte) 0));
        this.b.add(new a(this, 21, 0, 17, (byte) 0));
        this.b.add(new a(this, 19, 0, 33, (byte) 0));
        this.b.add(new a(this, 20, 0, 130, (byte) 0));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final boolean a(Activity activity, KeyEvent event) {
        boolean z = false;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a action = (a) it.next();
            if (event.getKeyCode() == action.b) {
                boolean z2;
                if (action.c != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2 || event.hasModifiers(action.c)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    boolean z3 = false;
                    switch (event.getAction()) {
                        case 0:
                            if (event.getRepeatCount() != 0) {
                                return false;
                            }
                            int c = action.d;
                            View currentFocus = activity.getCurrentFocus();
                            if (currentFocus instanceof c) {
                                b a = ((c) currentFocus).a();
                                if (a != null) {
                                    break;
                                }
                            }
                            z = true;
                            if (!z) {
                                return false;
                            }
                            this.c = event.getKeyCode();
                            return true;
                        case 1:
                            if ((event.getFlags() & 32) == 0 && this.c == event.getKeyCode()) {
                                int c2 = action.d;
                                b.a(true);
                                z3 = this.a.a(activity, c2);
                                b.a(false);
                            }
                            this.c = -1;
                            return z3;
                        default:
                            return false;
                    }
                }
            }
        }
        return false;
    }
}
