package com.microsoft.skypemessagetextinput.view;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.c;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.microsoft.skypemessagetextinput.a.b;
import com.microsoft.skypemessagetextinput.b.d;
import com.microsoft.skypemessagetextinput.b.e;
import com.microsoft.skypemessagetextinput.b.f;
import com.microsoft.skypemessagetextinput.b.h;
import com.microsoft.skypemessagetextinput.c.a;
import com.microsoft.skypemessagetextinput.module.RNModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RNView extends EditText implements c, b, com.microsoft.skypemessagetextinput.d.b, a {
    private static final Pattern b = Pattern.compile("^[\\s\\n]*$");
    private d A;
    private e B;
    private f C;
    private h D;
    private com.facebook.react.views.view.b E = new com.facebook.react.views.view.b();
    private final com.facebook.react.b F = new com.facebook.react.views.textinput.c(this);
    TextPaint a = new TextPaint();
    private ae c;
    private b d;
    private d e;
    private c f;
    private int g = 0;
    private int h = 0;
    private String i = "monospace";
    private int j = 400;
    private int k = -7829368;
    private int l = -7829368;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private InputMethodManager p;
    private a q = new a();
    private com.microsoft.skypemessagetextinput.a.e r;
    private Integer s;
    private String t = null;
    private String u = "";
    private List<am> v = new ArrayList();
    private List<am> w = new ArrayList();
    private com.microsoft.skypemessagetextinput.b.a x;
    private com.microsoft.skypemessagetextinput.b.b y;
    private com.microsoft.skypemessagetextinput.b.c z;

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.E.a((View) this, keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.E.a((View) this);
    }

    private static void a(a.a eventCallback, Map<String, Map> toMap) {
        toMap.put(eventCallback.name(), com.facebook.react.common.e.a("registrationName", eventCallback.name()));
    }

    public RNView(ae context, c shadowNode) {
        super(context);
        this.c = context;
        this.f = shadowNode;
        setPadding(10, 10, 10, 10);
        setGravity(48);
        setFocusableInTouchMode(false);
        this.p = (InputMethodManager) com.facebook.infer.annotation.a.a(context.getSystemService("input_method"));
        setInputType(180225);
        this.d = new b();
        addTextChangedListener(this.d);
        this.r = new com.microsoft.skypemessagetextinput.a.e(this);
        addTextChangedListener(this.r);
        this.x = new com.microsoft.skypemessagetextinput.b.a(this, this.q);
        addTextChangedListener(this.x);
        this.y = new com.microsoft.skypemessagetextinput.b.b(this);
        addTextChangedListener(this.y);
        this.z = new com.microsoft.skypemessagetextinput.b.c(this, this, context);
        addTextChangedListener(this.z);
        this.A = new d(this);
        addTextChangedListener(this.A);
        this.B = new e(this);
        addTextChangedListener(this.B);
        this.C = new f(this);
        setOnFocusChangeListener(this.C);
        addTextChangedListener(this.C);
        this.D = new h(this);
        addTextChangedListener(this.D);
        this.e = new d(this, this, this.z);
        addTextChangedListener(this.e);
        final RNView _this = this;
        setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ RNView b;

            public final boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
                InputDevice inputDevice = keyEvent.getDevice();
                if (inputDevice == null || keyEvent.getAction() != 0 || inputDevice.isVirtual()) {
                    return false;
                }
                switch (keyCode) {
                    case 66:
                        boolean shiftOrCtrlPressed = keyEvent.isShiftPressed() || keyEvent.isCtrlPressed();
                        if ((!_this.m || shiftOrCtrlPressed) && (_this.m || !shiftOrCtrlPressed)) {
                            _this.dispatchKeyEvent(new KeyEvent(0, 0, 0, 66, 0));
                            _this.dispatchKeyEvent(new KeyEvent(0, 0, 1, 66, 0));
                            return true;
                        }
                        ar event = new WritableNativeMap();
                        event.putMap("content", _this.k());
                        _this.a(a.a.onNewContentCommitted, event);
                        return true;
                    case 111:
                        _this.a(a.a.onNewContentAborted, new WritableNativeMap());
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public final void i() {
        setOnKeyListener(null);
        if (this.e != null) {
            this.e.b();
            removeTextChangedListener(this.e);
            this.e = null;
        }
        if (this.D != null) {
            removeTextChangedListener(this.D);
            this.D = null;
        }
        if (this.C != null) {
            this.C.a();
            setOnFocusChangeListener(null);
            removeTextChangedListener(this.C);
            this.C = null;
        }
        if (this.B != null) {
            removeTextChangedListener(this.B);
            this.B = null;
        }
        if (this.A != null) {
            removeTextChangedListener(this.A);
            this.A = null;
        }
        if (this.z != null) {
            removeTextChangedListener(this.z);
            this.z = null;
        }
        if (this.y != null) {
            removeTextChangedListener(this.y);
            this.y.a();
            this.y = null;
        }
        if (this.x != null) {
            this.x.a();
            removeTextChangedListener(this.x);
            this.x = null;
        }
        if (this.r != null) {
            removeTextChangedListener(this.r);
            this.r = null;
        }
        if (this.d != null) {
            removeTextChangedListener(this.d);
            this.d = null;
        }
        n();
        getText().delete(0, getText().length());
        this.v.clear();
        this.w.clear();
    }

    public final boolean j() {
        return this.r == null;
    }

    public void setLineHeight(int lineHeight) {
        this.h = lineHeight;
        setLineSpacing((float) lineHeight, 0.0f);
    }

    public void setMaxHeight(int maxHeight) {
        this.z.a(maxHeight);
        this.e.a(maxHeight - 20);
    }

    public void setMaxLength(@Nullable Integer maxLength) {
        this.s = maxLength;
        InputFilter[] inputFilterArr = new InputFilter[0];
        if (this.s != null) {
            inputFilterArr = new InputFilter[]{new LengthFilter(this.s.intValue())};
        }
        setFilters(inputFilterArr);
    }

    public void setEnterKeyOnExtKeyboardSendsMessage(@Nullable Boolean enterKeyOnExtKeyboardSendsMessage) {
        this.m = enterKeyOnExtKeyboardSendsMessage.booleanValue();
    }

    public void setFontFamily(String family) {
        this.i = family;
        m();
    }

    public void setFontWeight(int weight) {
        this.j = weight;
        m();
    }

    public void setFontSize(int fontSize) {
        setTextSize(2, (float) fontSize);
        this.a.setTextSize(getTextSize());
    }

    public void setAgnosticContentBackgroundColor(int color) {
        this.k = color;
    }

    public void setAtMentionBackgroundColor(int color) {
        this.l = color;
    }

    public void setIntermediateContentUpdatedEventInterval(@Nullable Integer interval) {
        this.C.a(interval);
    }

    public void setDelayedTriggersWaitTime(@Nullable Integer delay) {
        this.x.a(delay);
    }

    public void setConvenienceSpaceReplacingChars(@Nullable String value) {
        if (this.r != null) {
            this.r.a(value);
        }
    }

    public final void a(int commandType, @Nullable al args) {
        boolean z;
        com.facebook.infer.annotation.a.a((Object) args);
        com.facebook.infer.annotation.a.a(args.size() > 0);
        com.facebook.infer.annotation.a.a(args.getType(0) == ReadableType.Array);
        if (args.size() < 2 || args.getType(1) == ReadableType.Number) {
            z = true;
        } else {
            z = false;
        }
        com.facebook.infer.annotation.a.a(z);
        ar event = new WritableNativeMap();
        switch (a.b.values()[commandType]) {
            case GetContent:
                event.putMap("returnValue", k());
                break;
            case SetContent:
                setContent(args.getArray(0).getMap(0));
                break;
            case InsertContent:
                am map = args.getArray(0).getMap(0);
                this.x.b();
                this.r.a();
                this.o = true;
                int selectionStart = getSelectionStart();
                int selectionEnd = getSelectionEnd();
                int i = selectionStart >= 0 ? selectionStart : 0;
                if (selectionEnd < 0) {
                    selectionEnd = 0;
                }
                setCaretPosition(b(i, selectionEnd, map) + selectionStart);
                this.r.b();
                this.o = false;
                break;
            case NotifyBackspacePressedExternally:
                dispatchKeyEvent(new KeyEvent(0, 0, 0, 67, 0));
                dispatchKeyEvent(new KeyEvent(0, 0, 1, 67, 0));
                break;
            case Focus:
                l();
                break;
            case Blur:
                clearFocus();
                break;
            case IsFocused:
                event.putBoolean("returnValue", hasFocus());
                break;
            case DismissKeyboard:
                this.p.hideSoftInputFromWindow(getWindowToken(), 0);
                break;
            case AutoComplete:
                this.x.a(args.getArray(0).getMap(0));
                break;
            case CancelAutoComplete:
                this.x.b(args.getArray(0).getMap(0));
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
        if (args.size() >= 2) {
            event.putInt("promiseId", args.getInt(1));
            a(a.a.onNativeCallCompleted, event);
        }
    }

    public void setCustomContext(@Nullable String customContext) {
        this.t = customContext;
    }

    public void setAutoCompletionTriggers(@Nullable am autoCompletionTriggers) {
        if (autoCompletionTriggers != null) {
            if (autoCompletionTriggers.hasKey("instantTriggers")) {
                this.q.a(autoCompletionTriggers.getArray("instantTriggers"));
            }
            if (autoCompletionTriggers.hasKey("delayedTriggers")) {
                this.q.b(autoCompletionTriggers.getArray("delayedTriggers"));
                return;
            }
            return;
        }
        al emptyArray = new WritableNativeArray();
        this.q.a(emptyArray);
        this.q.b(emptyArray);
    }

    public void setContent(am editableContent) {
        this.x.b();
        this.D.c();
        this.y.c();
        this.C.b();
        this.r.a();
        n();
        this.v.clear();
        this.w.clear();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        al entities = editableContent.getArray("entities");
        for (int entityIndex = 0; entityIndex < entities.size(); entityIndex++) {
            a(entities.getMap(entityIndex), builder.length(), builder);
        }
        this.u = builder.toString();
        this.g = editableContent.getInt("downStreamDataVersion");
        setText(builder);
        this.r.b();
        this.C.c();
        this.y.d();
        this.D.d();
        this.D.a();
        this.y.b();
        setCaretPosition(this.u.length());
    }

    public final com.microsoft.skypemessagetextinput.d.e b() {
        int contentWidth;
        int contentHeight;
        if (getLayout() != null) {
            contentWidth = (getCompoundPaddingLeft() + getLayout().getWidth()) + getCompoundPaddingRight();
            contentHeight = (getCompoundPaddingTop() + Math.max(getLayout().getHeight(), a(getLayout().getWidth()))) + getCompoundPaddingBottom();
        } else {
            contentWidth = getWidth();
            contentHeight = Math.max(getHeight(), a((getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight()));
        }
        return new com.microsoft.skypemessagetextinput.d.e(contentWidth, contentHeight);
    }

    public final int c() {
        return this.d.a();
    }

    public final boolean d() {
        return b.matcher(getText()).matches();
    }

    public final boolean a(Editable editable) {
        return editable.toString().equals(this.u);
    }

    public final Integer e() {
        if (getSelectionStart() < 0 || getSelectionStart() != getSelectionEnd()) {
            return null;
        }
        return Integer.valueOf(getSelectionStart());
    }

    public void setCaretPosition(int pos) {
        if (this.s != null && this.s.intValue() < pos) {
            pos = this.s.intValue();
        }
        setSelection(pos);
        this.e.d();
    }

    public final int a(int start, int end, am byEditableEntity) {
        return b(start, end, byEditableEntity);
    }

    public final void a(com.microsoft.skypemessagetextinput.e.f span) {
        Editable editable = getEditableText();
        int start = editable.getSpanStart(span);
        int end = editable.getSpanEnd(span);
        if (start != -1 && end != -1) {
            this.o = true;
            span.a(editable);
            String displayText = span.a();
            editable.replace(start, end, displayText);
            span.a(editable, start, displayText.length() + start);
            this.o = false;
        }
    }

    public final void f() {
        if (this.t != null && !this.t.isEmpty()) {
            ar event = new WritableNativeMap();
            event.putString("forCustomContext", this.t);
            event.putMap("newContent", k());
            RNModule.sharedInstance().sendGlobalDataUpdateEvent(event);
        }
    }

    public final void a(a.a eventCallback, ar eventData) {
        ((RCTEventEmitter) this.c.a(RCTEventEmitter.class)).receiveEvent(getId(), eventCallback.name(), eventData);
    }

    public final void a(Runnable task) {
        post(task);
    }

    public boolean isLayoutRequested() {
        return true;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.z.b();
        this.e.c();
    }

    public boolean onTextContextMenuItem(int id) {
        switch (id) {
            case 16908320:
                e.a(getEditableText(), getSelectionStart(), getSelectionEnd(), this.c);
                if (getSelectionStart() < getSelectionEnd()) {
                    dispatchKeyEvent(new KeyEvent(0, 0, 0, 67, 0));
                    dispatchKeyEvent(new KeyEvent(0, 0, 1, 67, 0));
                }
                return true;
            case 16908321:
                e.a(getEditableText(), getSelectionStart(), getSelectionEnd(), this.c);
                return true;
            case 16908322:
                return super.onTextContextMenuItem(16908337);
            default:
                return super.onTextContextMenuItem(id);
        }
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (!this.n) {
            boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
            com.facebook.react.views.view.b.c();
            return didTakeFocus;
        } else if (isFocused()) {
            return true;
        } else {
            setFocusableInTouchMode(true);
            boolean focused = super.requestFocus(direction, previouslyFocusedRect);
            com.facebook.react.views.view.b.c();
            this.p.showSoftInput(this, 0);
            return focused;
        }
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        this.E.b();
        this.p.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    public boolean performAccessibilityAction(int action, Bundle args) {
        switch (action) {
            case 16:
                l();
                break;
        }
        return super.performAccessibilityAction(action, args);
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        if (!this.o) {
            super.sendAccessibilityEventUnchecked(event);
        }
    }

    private int b(int start, int end, am byEditableEntity) {
        this.x.b();
        this.r.a();
        Editable content = getEditableText();
        if (start != end) {
            content.delete(start, end);
        }
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        int entityLength = a(byEditableEntity, start, builder);
        setText(builder);
        this.r.b();
        return entityLength;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(am editableEntity, int atPos, SpannableStringBuilder toBuilder) {
        int i;
        int i2 = 0;
        int initialLength = toBuilder.length();
        String string = editableEntity.getString("type");
        switch (string.hashCode()) {
            case -606856649:
                if (string.equals("atMention")) {
                    i = 1;
                    break;
                }
            case 3321850:
                if (string.equals("link")) {
                    i = 3;
                    break;
                }
            case 1172029062:
                if (string.equals("emoticon")) {
                    i = 2;
                    break;
                }
            case 1727734818:
                if (string.equals("agnostic")) {
                    i = 0;
                    break;
                }
            case 1972280855:
                if (string.equals("plainText")) {
                    i = 4;
                    break;
                }
            default:
                i = -1;
                break;
        }
        switch (i) {
            case 0:
                string = editableEntity.getString("position");
                switch (string.hashCode()) {
                    case -1546436206:
                        if (string.equals("tailing")) {
                            i2 = 2;
                            break;
                        }
                    case 50359046:
                        break;
                    case 1925008098:
                        if (string.equals("inPlace")) {
                            i2 = 1;
                            break;
                        }
                    default:
                        i2 = -1;
                        break;
                }
                switch (i2) {
                    case 0:
                        this.v.add(editableEntity);
                        break;
                    case 1:
                        new com.microsoft.skypemessagetextinput.e.a(editableEntity, this.k).a((Editable) toBuilder, atPos, " ");
                        break;
                    case 2:
                        this.w.add(editableEntity);
                        break;
                }
                break;
            case 1:
                new com.microsoft.skypemessagetextinput.e.b(editableEntity, this.l).a((Editable) toBuilder, atPos, " ");
                break;
            case 2:
                Editable editable = getText();
                if (((com.microsoft.skypemessagetextinput.e.d[]) editable.getSpans(0, editable.length(), com.microsoft.skypemessagetextinput.e.d.class)).length < 50) {
                    new com.microsoft.skypemessagetextinput.e.d(editableEntity, this, this.c).a((Editable) toBuilder, atPos, "");
                    break;
                }
                break;
            case 3:
                new com.microsoft.skypemessagetextinput.e.e(editableEntity).a((Editable) toBuilder, atPos, " ");
                break;
            case 4:
                toBuilder.insert(atPos, editableEntity.getString("displayText"));
                break;
            default:
                new com.microsoft.skypemessagetextinput.e.a(editableEntity, this.k).a((Editable) toBuilder, atPos, " ");
                break;
        }
        return toBuilder.length() - initialLength;
    }

    private void l() {
        this.n = true;
        requestFocus();
        this.n = false;
    }

    private void m() {
        setTypeface(Typeface.create(this.i, this.j >= 700 ? 1 : 0));
    }

    private void n() {
        int i = 0;
        Editable editable = getText();
        com.microsoft.skypemessagetextinput.e.f[] fVarArr = (com.microsoft.skypemessagetextinput.e.f[]) editable.getSpans(0, editable.length(), com.microsoft.skypemessagetextinput.e.f.class);
        int length = fVarArr.length;
        while (i < length) {
            fVarArr[i].c();
            i++;
        }
    }

    private int a(int forContentWidth) {
        if (getHint() == null || getText().length() > 0 || forContentWidth <= 0) {
            return 0;
        }
        return new StaticLayout(getHint(), this.a, forContentWidth, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false).getHeight();
    }

    public final com.facebook.react.b a() {
        return this.F;
    }

    public static Map<String, Integer> g() {
        Map<String, Integer> map = new HashMap();
        map.put("GetContent", Integer.valueOf(a.b.GetContent.ordinal()));
        map.put("SetContent", Integer.valueOf(a.b.SetContent.ordinal()));
        map.put("InsertContent", Integer.valueOf(a.b.InsertContent.ordinal()));
        map.put("NotifyBackspacePressedExternally", Integer.valueOf(a.b.NotifyBackspacePressedExternally.ordinal()));
        map.put("Focus", Integer.valueOf(a.b.Focus.ordinal()));
        map.put("Blur", Integer.valueOf(a.b.Blur.ordinal()));
        map.put("IsFocused", Integer.valueOf(a.b.IsFocused.ordinal()));
        map.put("DismissKeyboard", Integer.valueOf(a.b.DismissKeyboard.ordinal()));
        map.put("AutoComplete", Integer.valueOf(a.b.AutoComplete.ordinal()));
        map.put("CancelAutoComplete", Integer.valueOf(a.b.CancelAutoComplete.ordinal()));
        return map;
    }

    public static Map h() {
        Map map = new HashMap();
        a(a.a.onNewContentCommitted, map);
        a(a.a.onNewContentAborted, map);
        a(a.a.onEmptyIndicationChanged, map);
        a(a.a.onUncommittedChangesIndicationChanged, map);
        a(a.a.onEqualsInitialContentChanged, map);
        a(a.a.onComposingActive, map);
        a(a.a.onComposingInactive, map);
        a(a.a.onAutoCompletionRequested, map);
        a(a.a.onAutoCompletionRequestAborted, map);
        a(a.a.onAutoCompletionNavigationKey, map);
        a(a.a.onFocus2, map);
        a(a.a.onBlur2, map);
        a(a.a.onEmoticonLoadCompleted, map);
        a(a.a.onNativeCallCompleted, map);
        a(a.a.onContentSizeChanged, map);
        return map;
    }

    public final ar k() {
        ar writableEntity;
        ar entity;
        aq entities = new WritableNativeArray();
        final Editable source = getEditableText();
        for (am readableEntity : this.v) {
            writableEntity = new WritableNativeMap();
            writableEntity.merge(readableEntity);
            entities.pushMap(writableEntity);
        }
        List<com.microsoft.skypemessagetextinput.e.f> spans = Arrays.asList(source.getSpans(0, source.length(), com.microsoft.skypemessagetextinput.e.f.class));
        Collections.sort(spans, new Comparator<com.microsoft.skypemessagetextinput.e.f>(this) {
            final /* synthetic */ RNView b;

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                com.microsoft.skypemessagetextinput.e.f fVar = (com.microsoft.skypemessagetextinput.e.f) obj;
                com.microsoft.skypemessagetextinput.e.f fVar2 = (com.microsoft.skypemessagetextinput.e.f) obj2;
                if (source.getSpanStart(fVar) == source.getSpanStart(fVar2)) {
                    return 0;
                }
                return source.getSpanStart(fVar) < source.getSpanStart(fVar2) ? -1 : 1;
            }
        });
        int startText = 0;
        for (com.microsoft.skypemessagetextinput.e.f span : spans) {
            int spanStartPos = source.getSpanStart(span);
            int spanEndPos = source.getSpanEnd(span);
            if (startText < spanStartPos) {
                entity = new WritableNativeMap();
                entity.putString("type", "plainText");
                entity.putString("displayText", source.subSequence(startText, spanStartPos).toString());
                entities.pushMap(entity);
            }
            span.a(source, entities);
            startText = spanEndPos;
        }
        if (startText < source.length()) {
            entity = new WritableNativeMap();
            entity.putString("type", "plainText");
            entity.putString("displayText", source.subSequence(startText, source.length()).toString());
            entities.pushMap(entity);
        }
        for (am readableEntity2 : this.w) {
            writableEntity = new WritableNativeMap();
            writableEntity.merge(readableEntity2);
            entities.pushMap(writableEntity);
        }
        ar editableContent = new WritableNativeMap();
        editableContent.putInt("downStreamDataVersion", this.g);
        editableContent.putArray("entities", entities);
        return editableContent;
    }
}
