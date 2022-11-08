package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.react.bridge.n;
import com.facebook.react.c;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.text.h;
import com.facebook.react.views.text.k;
import com.facebook.react.views.view.e;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

public class ReactEditText extends EditText implements c {
    private static final KeyListener y = QwertyKeyListener.getInstanceForFullKeyboard();
    private final InputMethodManager a;
    private boolean b;
    private boolean c;
    private int d;
    private int e;
    private int f;
    private int g;
    @Nullable
    private ArrayList<TextWatcher> h;
    @Nullable
    private b i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    @Nullable
    private String n;
    @Nullable
    private m o;
    @Nullable
    private a p;
    @Nullable
    private l q;
    private final a r;
    private boolean s = false;
    private float t;
    private float u = Float.NaN;
    private final com.facebook.react.b v;
    private e w;
    private com.facebook.react.views.view.b x = new com.facebook.react.views.view.b();

    private static class a implements KeyListener {
        private int a = 0;

        public final void a(int inputType) {
            this.a = inputType;
        }

        public final int getInputType() {
            return this.a;
        }

        public final boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
            return ReactEditText.y.onKeyDown(view, text, keyCode, event);
        }

        public final boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
            return ReactEditText.y.onKeyUp(view, text, keyCode, event);
        }

        public final boolean onKeyOther(View view, Editable text, KeyEvent event) {
            return ReactEditText.y.onKeyOther(view, text, event);
        }

        public final void clearMetaKeyState(View view, Editable content, int states) {
            ReactEditText.y.clearMetaKeyState(view, content, states);
        }
    }

    private class b implements TextWatcher {
        final /* synthetic */ ReactEditText a;

        private b(ReactEditText reactEditText) {
            this.a = reactEditText;
        }

        /* synthetic */ b(ReactEditText x0, byte b) {
            this(x0);
        }

        public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (!this.a.b && this.a.h != null) {
                Iterator it = this.a.h.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).beforeTextChanged(s, start, count, after);
                }
            }
        }

        public final void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!(this.a.b || this.a.h == null)) {
                Iterator it = this.a.h.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).onTextChanged(s, start, before, count);
                }
            }
            if (this.a.p != null) {
                this.a.p.a();
            }
        }

        public final void afterTextChanged(Editable s) {
            if (!this.a.b && this.a.h != null) {
                Iterator it = this.a.h.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).afterTextChanged(s);
                }
            }
        }
    }

    public ReactEditText(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        this.v = new c(this);
        this.a = (InputMethodManager) com.facebook.infer.annotation.a.a(getContext().getSystemService("input_method"));
        this.d = getGravity() & 8388615;
        this.e = getGravity() & 112;
        this.f = 0;
        this.g = 0;
        this.b = false;
        this.c = false;
        this.l = true;
        this.m = false;
        this.h = null;
        this.i = null;
        this.j = getInputType();
        this.r = new a();
        this.q = null;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.x.a((View) this);
    }

    public boolean isLayoutRequested() {
        if (this.p != null) {
            return k();
        }
        return false;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.p != null) {
            this.p.a();
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.s = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 2:
                if (this.s) {
                    if (!(canScrollVertically(-1) || canScrollVertically(1) || canScrollHorizontally(-1) || canScrollHorizontally(1))) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    this.s = false;
                    break;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 66 && !k()) {
            i();
            return true;
        } else if (this.x.a((View) this, keyCode, event)) {
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }

    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
        if (this.q != null) {
            this.q.a(horiz, vert);
        }
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        this.x.b();
        i();
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (!this.c) {
            boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
            com.facebook.react.views.view.b.c();
            return didTakeFocus;
        } else if (isFocused()) {
            return true;
        } else {
            setFocusableInTouchMode(true);
            boolean focused = super.requestFocus(direction, previouslyFocusedRect);
            this.a.showSoftInput(this, 0);
            return focused;
        }
    }

    public boolean performAccessibilityAction(int action, Bundle args) {
        switch (action) {
            case 16:
                h();
                break;
        }
        return super.performAccessibilityAction(action, args);
    }

    private void h() {
        this.c = true;
        requestFocus();
        this.c = false;
    }

    public void addTextChangedListener(TextWatcher watcher) {
        if (this.h == null) {
            this.h = new ArrayList();
            super.addTextChangedListener(j());
        }
        this.h.add(watcher);
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        if (this.h != null) {
            this.h.remove(watcher);
            if (this.h.isEmpty()) {
                this.h = null;
                super.removeTextChangedListener(j());
            }
        }
    }

    public void setContentSizeWatcher(a contentSizeWatcher) {
        this.p = contentSizeWatcher;
    }

    public void setScrollWatcher(l scrollWatcher) {
        this.q = scrollWatcher;
    }

    public void setSelection(int start, int end) {
        if (this.g >= this.f) {
            super.setSelection(start, end);
        }
    }

    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (this.o != null && hasFocus()) {
            this.o.a(selStart, selEnd);
        }
    }

    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && this.o != null) {
            this.o.a(getSelectionStart(), getSelectionEnd());
        }
    }

    public void setSelectionWatcher(m selectionWatcher) {
        this.o = selectionWatcher;
    }

    public void setBlurOnSubmit(boolean blurOnSubmit) {
        this.l = blurOnSubmit;
    }

    public final boolean b() {
        return this.l;
    }

    public void setDisableFullscreenUI(boolean disableFullscreenUI) {
        this.m = disableFullscreenUI;
        l();
    }

    public void setReturnKeyType(String returnKeyType) {
        this.n = returnKeyType;
        l();
    }

    final int c() {
        return this.j;
    }

    final void a(int stagedInputType) {
        this.j = stagedInputType;
    }

    final void d() {
        if (getInputType() != this.j) {
            setInputType(this.j);
        }
    }

    public void setInputType(int type) {
        Typeface tf = super.getTypeface();
        super.setInputType(type);
        this.j = type;
        super.setTypeface(tf);
        this.r.a(type);
        setKeyListener(this.r);
    }

    public final void e() {
        h();
    }

    public final int f() {
        int i = this.f + 1;
        this.f = i;
        return i;
    }

    public final void a(h reactTextUpdate) {
        this.g = reactTextUpdate.b();
        if (this.g >= this.f) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(reactTextUpdate.a());
            a(spannableStringBuilder);
            this.k = reactTextUpdate.c();
            boolean sameText = a(getText(), spannableStringBuilder, 0, length());
            this.b = true;
            getText().replace(0, length(), spannableStringBuilder);
            this.b = false;
            if (VERSION.SDK_INT >= 23 && getBreakStrategy() != reactTextUpdate.i()) {
                setBreakStrategy(reactTextUpdate.i());
            }
            if (!sameText) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService("input_method");
                if (imm != null) {
                    imm.restartInput(this);
                }
            }
        }
    }

    private void a(SpannableStringBuilder spannableStringBuilder) {
        Object[] spans = getText().getSpans(0, length(), Object.class);
        int spanIdx = 0;
        while (spanIdx < spans.length) {
            if (ForegroundColorSpan.class.isInstance(spans[spanIdx]) || BackgroundColorSpan.class.isInstance(spans[spanIdx]) || AbsoluteSizeSpan.class.isInstance(spans[spanIdx]) || com.facebook.react.views.text.b.class.isInstance(spans[spanIdx]) || com.facebook.react.views.text.e.class.isInstance(spans[spanIdx])) {
                getText().removeSpan(spans[spanIdx]);
            }
            if ((getText().getSpanFlags(spans[spanIdx]) & 33) == 33) {
                Object span = spans[spanIdx];
                int spanStart = getText().getSpanStart(spans[spanIdx]);
                int spanEnd = getText().getSpanEnd(spans[spanIdx]);
                int spanFlags = getText().getSpanFlags(spans[spanIdx]);
                getText().removeSpan(spans[spanIdx]);
                if (a(getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(span, spanStart, spanEnd, spanFlags);
                }
            }
            spanIdx++;
        }
    }

    private static boolean a(Editable oldText, SpannableStringBuilder newText, int start, int end) {
        if (start > newText.length() || end > newText.length()) {
            return false;
        }
        for (int charIdx = start; charIdx < end; charIdx++) {
            if (oldText.charAt(charIdx) != newText.charAt(charIdx)) {
                return false;
            }
        }
        return true;
    }

    private void i() {
        this.a.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private b j() {
        if (this.i == null) {
            this.i = new b();
        }
        return this.i;
    }

    private boolean k() {
        return (getInputType() & 131072) != 0;
    }

    final void b(int gravityHorizontal) {
        if (gravityHorizontal == 0) {
            gravityHorizontal = this.d;
        }
        setGravity(((getGravity() & -8) & -8388616) | gravityHorizontal);
    }

    final void c(int gravityVertical) {
        if (gravityVertical == 0) {
            gravityVertical = this.e;
        }
        setGravity((getGravity() & -113) | gravityVertical);
    }

    private void l() {
        int returnKeyFlag = 6;
        if (this.n != null) {
            String str = this.n;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1273775369:
                    if (str.equals("previous")) {
                        obj = 3;
                        break;
                    }
                    break;
                case -906336856:
                    if (str.equals("search")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 3304:
                    if (str.equals("go")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3089282:
                    if (str.equals("done")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 3377907:
                    if (str.equals("next")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3387192:
                    if (str.equals("none")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 3526536:
                    if (str.equals("send")) {
                        obj = 5;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    returnKeyFlag = 2;
                    break;
                case 1:
                    returnKeyFlag = 5;
                    break;
                case 2:
                    returnKeyFlag = 1;
                    break;
                case 3:
                    returnKeyFlag = 7;
                    break;
                case 4:
                    returnKeyFlag = 3;
                    break;
                case 5:
                    returnKeyFlag = 4;
                    break;
                case 6:
                    returnKeyFlag = 6;
                    break;
            }
        }
        if (this.m) {
            setImeOptions(33554432 | returnKeyFlag);
        } else {
            setImeOptions(returnKeyFlag);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        int i = 0;
        if (this.k) {
            Spanned text = getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                if (kVarArr[i].a() == drawable) {
                    return true;
                }
                i++;
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        int i = 0;
        if (this.k) {
            Spanned text = getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                if (kVarArr[i].a() == drawable) {
                    invalidate();
                }
                i++;
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        int i = 0;
        super.onDetachedFromWindow();
        if (this.k) {
            Spanned text = getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].b();
                i++;
            }
        }
    }

    public void onStartTemporaryDetach() {
        int i = 0;
        super.onStartTemporaryDetach();
        if (this.k) {
            Spanned text = getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].c();
                i++;
            }
        }
    }

    public void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (this.k) {
            Spanned text = getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].d();
                i++;
            }
        }
    }

    public void onFinishTemporaryDetach() {
        int i = 0;
        super.onFinishTemporaryDetach();
        if (this.k) {
            Spanned text = getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].e();
                i++;
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.w != null) {
            m().a(color);
        }
    }

    public void setBorderWidth(int position, float width) {
        m().a(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        m().a(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        m().a(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        m().a(borderRadius, position);
    }

    public void setBorderStyle(@Nullable String style) {
        m().a(style);
    }

    private e m() {
        if (this.w == null) {
            this.w = new e();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.w);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.w, backgroundDrawable}));
            }
        }
        return this.w;
    }

    public void setFontSize(float fontSize) {
        this.t = fontSize;
        n();
    }

    private void n() {
        float f;
        float f2 = this.t;
        if (this.u == 0.0f || this.u >= 1.0f) {
            f = this.u;
        } else {
            f = UIManagerModule.getMaxContentSizeMultiplierInternal();
        }
        setTextSize(0, (float) ((int) Math.ceil((double) p.a(f2, f))));
    }

    public void setMaxContentSizeMultiplier(float maxContentSizeMultiplier) {
        if (maxContentSizeMultiplier == this.u) {
            return;
        }
        if (maxContentSizeMultiplier == 0.0f || maxContentSizeMultiplier >= 1.0f) {
            this.u = maxContentSizeMultiplier;
            n();
            return;
        }
        throw new n("maxContentSizeMultiplier must be NaN, 0, or >= 1");
    }

    public final com.facebook.react.b a() {
        return this.v;
    }
}
