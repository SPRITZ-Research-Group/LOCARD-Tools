package com.facebook.react.views.textinput;

import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.adjust.sdk.Constants;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.scroll.e;
import com.facebook.react.views.scroll.f;
import com.facebook.react.views.text.k;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidTextInput")
public class ReactTextInputManager extends BaseViewManager<ReactEditText, h> {
    private static final int BLUR_TEXT_INPUT = 2;
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    protected static final String REACT_CLASS = "AndroidTextInput";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};
    private static final int UNSET = -1;

    private class a implements a {
        final /* synthetic */ ReactTextInputManager a;
        private ReactEditText b;
        private com.facebook.react.uimanager.events.c c;
        private int d = 0;
        private int e = 0;

        public a(ReactTextInputManager reactTextInputManager, ReactEditText editText) {
            this.a = reactTextInputManager;
            this.b = editText;
            this.c = ((UIManagerModule) ((ai) editText.getContext()).b(UIManagerModule.class)).getEventDispatcher();
        }

        public final void a() {
            int contentWidth = this.b.getWidth();
            int contentHeight = this.b.getHeight();
            if (this.b.getLayout() != null) {
                contentWidth = (this.b.getCompoundPaddingLeft() + this.b.getLayout().getWidth()) + this.b.getCompoundPaddingRight();
                contentHeight = (this.b.getCompoundPaddingTop() + this.b.getLayout().getHeight()) + this.b.getCompoundPaddingBottom();
            }
            if (contentWidth != this.d || contentHeight != this.e) {
                this.e = contentHeight;
                this.d = contentWidth;
                this.c.a(new b(this.b.getId(), p.b((float) contentWidth), p.b((float) contentHeight)));
            }
        }
    }

    private class b implements l {
        final /* synthetic */ ReactTextInputManager a;
        private ReactEditText b;
        private com.facebook.react.uimanager.events.c c;
        private int d;
        private int e;

        public b(ReactTextInputManager reactTextInputManager, ReactEditText editText) {
            this.a = reactTextInputManager;
            this.b = editText;
            this.c = ((UIManagerModule) ((ai) editText.getContext()).b(UIManagerModule.class)).getEventDispatcher();
        }

        public final void a(int horiz, int vert) {
            if (this.d != horiz || this.e != vert) {
                this.c.a(e.a(this.b.getId(), f.SCROLL, horiz, vert, 0, 0, this.b.getWidth(), this.b.getHeight()));
                this.d = horiz;
                this.e = vert;
            }
        }
    }

    private class c implements m {
        final /* synthetic */ ReactTextInputManager a;
        private ReactEditText b;
        private com.facebook.react.uimanager.events.c c;
        private int d;
        private int e;

        public c(ReactTextInputManager reactTextInputManager, ReactEditText editText) {
            this.a = reactTextInputManager;
            this.b = editText;
            this.c = ((UIManagerModule) ((ai) editText.getContext()).b(UIManagerModule.class)).getEventDispatcher();
        }

        public final void a(int start, int end) {
            if (this.d != start || this.e != end) {
                this.c.a(new i(this.b.getId(), start, end));
                this.d = start;
                this.e = end;
            }
        }
    }

    private class d implements TextWatcher {
        final /* synthetic */ ReactTextInputManager a;
        private com.facebook.react.uimanager.events.c b;
        private ReactEditText c;
        private String d = null;

        public d(ReactTextInputManager reactTextInputManager, ai reactContext, ReactEditText editText) {
            this.a = reactTextInputManager;
            this.b = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
            this.c = editText;
        }

        public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
            this.d = s.toString();
        }

        public final void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count != 0 || before != 0) {
                com.facebook.infer.annotation.a.a(this.d);
                String newText = s.toString().substring(start, start + count);
                String oldText = this.d.substring(start, start + before);
                if (count != before || !newText.equals(oldText)) {
                    this.b.a(new d(this.c.getId(), s.toString(), this.c.f()));
                    this.b.a(new g(this.c.getId(), newText, oldText, start, start + before));
                }
            }
        }

        public final void afterTextChanged(Editable s) {
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactEditText createViewInstance(ae context) {
        ReactEditText editText = new ReactEditText(context);
        editText.setInputType(-131073 & editText.getInputType());
        editText.setReturnKeyType("done");
        editText.setFontSize(14.0f);
        return editText;
    }

    public h createShadowNodeInstance() {
        return new j();
    }

    public Class<? extends h> getShadowNodeClass() {
        return j.class;
    }

    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return com.facebook.react.common.e.a().a("topSubmitEditing", com.facebook.react.common.e.a("phasedRegistrationNames", com.facebook.react.common.e.a("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).a("topEndEditing", com.facebook.react.common.e.a("phasedRegistrationNames", com.facebook.react.common.e.a("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).a("topTextInput", com.facebook.react.common.e.a("phasedRegistrationNames", com.facebook.react.common.e.a("bubbled", "onTextInput", "captured", "onTextInputCapture"))).a("topFocus", com.facebook.react.common.e.a("phasedRegistrationNames", com.facebook.react.common.e.a("bubbled", "onFocus", "captured", "onFocusCapture"))).a("topBlur", com.facebook.react.common.e.a("phasedRegistrationNames", com.facebook.react.common.e.a("bubbled", "onBlur", "captured", "onBlurCapture"))).a();
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return com.facebook.react.common.e.a("focusTextInput", Integer.valueOf(1), "blurTextInput", Integer.valueOf(2));
    }

    public void receiveCommand(ReactEditText reactEditText, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                reactEditText.e();
                return;
            case 2:
                reactEditText.clearFocus();
                return;
            default:
                return;
        }
    }

    public void updateExtraData(ReactEditText view, Object extraData) {
        if (extraData instanceof com.facebook.react.views.text.h) {
            com.facebook.react.views.text.h update = (com.facebook.react.views.text.h) extraData;
            view.setPadding((int) update.d(), (int) update.e(), (int) update.f(), (int) update.g());
            if (update.c()) {
                k.a(update.a(), view);
            }
            view.a(update);
        }
    }

    @ReactProp(defaultFloat = 14.0f, name = "fontSize")
    public void setFontSize(ReactEditText view, float fontSize) {
        view.setFontSize(fontSize);
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(ReactEditText view, String fontFamily) {
        int style = 0;
        if (view.getTypeface() != null) {
            style = view.getTypeface().getStyle();
        }
        view.setTypeface(com.facebook.react.views.text.d.a().a(fontFamily, style, view.getContext().getAssets()));
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxContentSizeMultiplier")
    public void setMaxContentSizeMultiplier(ReactEditText view, float maxContentSizeMultiplier) {
        view.setMaxContentSizeMultiplier(maxContentSizeMultiplier);
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(ReactEditText view, @Nullable String fontWeightString) {
        int fontWeightNumeric;
        if (fontWeightString != null) {
            fontWeightNumeric = parseNumericFontWeight(fontWeightString);
        } else {
            fontWeightNumeric = -1;
        }
        int fontWeight = -1;
        if (fontWeightNumeric >= 500 || "bold".equals(fontWeightString)) {
            fontWeight = 1;
        } else if (Constants.NORMAL.equals(fontWeightString) || (fontWeightNumeric != -1 && fontWeightNumeric < 500)) {
            fontWeight = 0;
        }
        Typeface currentTypeface = view.getTypeface();
        if (currentTypeface == null) {
            currentTypeface = Typeface.DEFAULT;
        }
        if (fontWeight != currentTypeface.getStyle()) {
            view.setTypeface(currentTypeface, fontWeight);
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(ReactEditText view, @Nullable String fontStyleString) {
        int fontStyle = -1;
        if ("italic".equals(fontStyleString)) {
            fontStyle = 2;
        } else if (Constants.NORMAL.equals(fontStyleString)) {
            fontStyle = 0;
        }
        Typeface currentTypeface = view.getTypeface();
        if (currentTypeface == null) {
            currentTypeface = Typeface.DEFAULT;
        }
        if (fontStyle != currentTypeface.getStyle()) {
            view.setTypeface(currentTypeface, fontStyle);
        }
    }

    @ReactProp(name = "selection")
    public void setSelection(ReactEditText view, @Nullable am selection) {
        if (selection != null && selection.hasKey("start") && selection.hasKey("end")) {
            view.setSelection(selection.getInt("start"), selection.getInt("end"));
        }
    }

    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText view, boolean onSelectionChange) {
        if (onSelectionChange) {
            view.setSelectionWatcher(new c(this, view));
        } else {
            view.setSelectionWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = true, name = "blurOnSubmit")
    public void setBlurOnSubmit(ReactEditText view, boolean blurOnSubmit) {
        view.setBlurOnSubmit(blurOnSubmit);
    }

    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText view, boolean onContentSizeChange) {
        if (onContentSizeChange) {
            view.setContentSizeWatcher(new a(this, view));
        } else {
            view.setContentSizeWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onScroll")
    public void setOnScroll(ReactEditText view, boolean onScroll) {
        if (onScroll) {
            view.setScrollWatcher(new b(this, view));
        } else {
            view.setScrollWatcher(null);
        }
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(ReactEditText view, @Nullable String placeholder) {
        view.setHint(placeholder);
    }

    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText view, @Nullable Integer color) {
        if (color == null) {
            view.setHintTextColor(com.facebook.react.views.text.c.a(view.getContext()));
        } else {
            view.setHintTextColor(color.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText view, @Nullable Integer color) {
        if (color == null) {
            view.setHighlightColor(com.facebook.react.views.text.c.c(view.getContext()));
        } else {
            view.setHighlightColor(color.intValue());
        }
        setCursorColor(view, color);
    }

    private void setCursorColor(ReactEditText view, @Nullable Integer color) {
        try {
            Field cursorDrawableResField = TextView.class.getDeclaredField("mCursorDrawableRes");
            cursorDrawableResField.setAccessible(true);
            Drawable drawable = android.support.v4.content.a.a(view.getContext(), cursorDrawableResField.getInt(view));
            if (color != null) {
                drawable.setColorFilter(color.intValue(), Mode.SRC_IN);
            }
            Drawable[] drawables = new Drawable[]{drawable, drawable};
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editor = editorField.get(view);
            Field cursorDrawableField = editor.getClass().getDeclaredField("mCursorDrawable");
            cursorDrawableField.setAccessible(true);
            cursorDrawableField.set(editor, drawables);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e2) {
        }
    }

    @ReactProp(defaultBoolean = false, name = "caretHidden")
    public void setCaretHidden(ReactEditText view, boolean caretHidden) {
        view.setCursorVisible(!caretHidden);
    }

    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText view, boolean selectTextOnFocus) {
        view.setSelectAllOnFocus(selectTextOnFocus);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactEditText view, @Nullable Integer color) {
        if (color == null) {
            view.setTextColor(com.facebook.react.views.text.c.b(view.getContext()));
        } else {
            view.setTextColor(color.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText view, @Nullable Integer underlineColor) {
        Drawable drawableToMutate;
        Drawable background = view.getBackground();
        if (background.getConstantState() != null) {
            drawableToMutate = background.mutate();
        } else {
            drawableToMutate = background;
        }
        if (underlineColor == null) {
            drawableToMutate.clearColorFilter();
        } else {
            drawableToMutate.setColorFilter(underlineColor.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(ReactEditText view, @Nullable String textAlign) {
        if (textAlign == null || "auto".equals(textAlign)) {
            view.b(0);
        } else if ("left".equals(textAlign)) {
            view.b(3);
        } else if ("right".equals(textAlign)) {
            view.b(5);
        } else if ("center".equals(textAlign)) {
            view.b(1);
        } else if ("justify".equals(textAlign)) {
            view.b(3);
        } else {
            throw new n("Invalid textAlign: " + textAlign);
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactEditText view, @Nullable String textAlignVertical) {
        if (textAlignVertical == null || "auto".equals(textAlignVertical)) {
            view.c(0);
        } else if ("top".equals(textAlignVertical)) {
            view.c(48);
        } else if ("bottom".equals(textAlignVertical)) {
            view.c(80);
        } else if ("center".equals(textAlignVertical)) {
            view.c(16);
        } else {
            throw new n("Invalid textAlignVertical: " + textAlignVertical);
        }
    }

    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText view, @Nullable String resource) {
        view.setCompoundDrawablesWithIntrinsicBounds(com.facebook.react.views.a.c.a().a(view.getContext(), resource), 0, 0, 0);
    }

    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText view, int padding) {
        view.setCompoundDrawablePadding(padding);
    }

    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText view, boolean editable) {
        view.setEnabled(editable);
    }

    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public void setNumLines(ReactEditText view, int numLines) {
        view.setLines(numLines);
    }

    @ReactProp(name = "maxLength")
    public void setMaxLength(ReactEditText view, @Nullable Integer maxLength) {
        InputFilter[] currentFilters = view.getFilters();
        InputFilter[] newFilters = EMPTY_FILTERS;
        int i;
        if (maxLength == null) {
            if (currentFilters.length > 0) {
                LinkedList<InputFilter> list = new LinkedList();
                for (i = 0; i < currentFilters.length; i++) {
                    if (!(currentFilters[i] instanceof LengthFilter)) {
                        list.add(currentFilters[i]);
                    }
                }
                if (!list.isEmpty()) {
                    newFilters = (InputFilter[]) list.toArray(new InputFilter[list.size()]);
                }
            }
        } else if (currentFilters.length > 0) {
            newFilters = currentFilters;
            boolean replaced = false;
            for (i = 0; i < currentFilters.length; i++) {
                if (currentFilters[i] instanceof LengthFilter) {
                    currentFilters[i] = new LengthFilter(maxLength.intValue());
                    replaced = true;
                }
            }
            if (!replaced) {
                newFilters = new InputFilter[(currentFilters.length + 1)];
                System.arraycopy(currentFilters, 0, newFilters, 0, currentFilters.length);
                currentFilters[currentFilters.length] = new LengthFilter(maxLength.intValue());
            }
        } else {
            newFilters = new InputFilter[]{new LengthFilter(maxLength.intValue())};
        }
        view.setFilters(newFilters);
    }

    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText view, @Nullable Boolean autoCorrect) {
        int i = autoCorrect != null ? autoCorrect.booleanValue() ? 32768 : 524288 : 0;
        updateStagedInputTypeFlag(view, 557056, i);
    }

    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText view, boolean multiline) {
        int i;
        int i2 = 131072;
        if (multiline) {
            i = 0;
        } else {
            i = 131072;
        }
        if (!multiline) {
            i2 = 0;
        }
        updateStagedInputTypeFlag(view, i, i2);
    }

    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText view, boolean password) {
        int i = 0;
        int i2 = password ? 0 : 144;
        if (password) {
            i = 128;
        }
        updateStagedInputTypeFlag(view, i2, i);
        checkPasswordType(view);
    }

    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText view, int autoCapitalize) {
        updateStagedInputTypeFlag(view, 28672, autoCapitalize);
    }

    @ReactProp(name = "keyboardType")
    public void setKeyboardType(ReactEditText view, @Nullable String keyboardType) {
        int flagsToSet = 1;
        if (KEYBOARD_TYPE_NUMERIC.equalsIgnoreCase(keyboardType)) {
            flagsToSet = INPUT_TYPE_KEYBOARD_NUMBERED;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(keyboardType)) {
            flagsToSet = 33;
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(keyboardType)) {
            flagsToSet = 3;
        }
        updateStagedInputTypeFlag(view, 12323, flagsToSet);
        checkPasswordType(view);
    }

    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText view, String returnKeyType) {
        view.setReturnKeyType(returnKeyType);
    }

    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText view, boolean disableFullscreenUI) {
        view.setDisableFullscreenUI(disableFullscreenUI);
    }

    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText view, String returnKeyLabel) {
        view.setImeActionLabel(returnKeyLabel, IME_ACTION_ID);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactEditText view, int index, float borderRadius) {
        if (!com.facebook.yoga.a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactEditText view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactEditText view, int index, float width) {
        if (!com.facebook.yoga.a.a(width)) {
            width = p.a(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactEditText view, int index, Integer color) {
        float alphaComponent = Float.NaN;
        float rgbComponent = color == null ? Float.NaN : (float) (color.intValue() & 16777215);
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    protected void onAfterUpdateTransaction(ReactEditText view) {
        super.onAfterUpdateTransaction(view);
        view.d();
    }

    private static void checkPasswordType(ReactEditText view) {
        if ((view.c() & INPUT_TYPE_KEYBOARD_NUMBERED) != 0 && (view.c() & 128) != 0) {
            updateStagedInputTypeFlag(view, 128, 16);
        }
    }

    private static int parseNumericFontWeight(String fontWeightString) {
        return (fontWeightString.length() != 3 || !fontWeightString.endsWith("00") || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') ? -1 : (fontWeightString.charAt(0) - 48) * 100;
    }

    private static void updateStagedInputTypeFlag(ReactEditText view, int flagsToUnset, int flagsToSet) {
        view.a((view.c() & (flagsToUnset ^ -1)) | flagsToSet);
    }

    protected void addEventEmitters(final ae reactContext, final ReactEditText editText) {
        editText.addTextChangedListener(new d(this, reactContext, editText));
        editText.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ ReactTextInputManager c;

            public final void onFocusChange(View v, boolean hasFocus) {
                com.facebook.react.uimanager.events.c eventDispatcher = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
                if (hasFocus) {
                    eventDispatcher.a(new h(editText.getId()));
                    return;
                }
                eventDispatcher.a(new e(editText.getId()));
                eventDispatcher.a(new f(editText.getId(), editText.getText().toString()));
            }
        });
        editText.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ ReactTextInputManager c;

            public final boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if ((actionId & 255) > 0 || actionId == 0) {
                    ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher().a(new k(editText.getId(), editText.getText().toString()));
                    if (!(editText.b() || (editText.getInputType() & 131072) == 0)) {
                        return false;
                    }
                }
                if (editText.b()) {
                    editText.clearFocus();
                }
                return true;
            }
        });
    }

    @Nullable
    public Map getExportedViewConstants() {
        return com.facebook.react.common.e.a("AutoCapitalizationType", com.facebook.react.common.e.a("none", Integer.valueOf(0), "characters", Integer.valueOf(4096), "words", Integer.valueOf(8192), "sentences", Integer.valueOf(16384)));
    }
}
