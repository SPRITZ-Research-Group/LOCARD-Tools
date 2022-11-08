package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.g;
import android.support.v7.appcompat.a.h;
import android.support.v7.appcompat.a.j;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.Defines;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements android.support.v7.view.c {
    static final a i = new a();
    private c A;
    private b B;
    private d C;
    private OnClickListener D;
    private boolean E;
    private boolean F;
    private boolean G;
    private CharSequence H;
    private boolean I;
    private boolean J;
    private int K;
    private boolean L;
    private CharSequence M;
    private CharSequence N;
    private boolean O;
    private int P;
    private Bundle Q;
    private final Runnable R;
    private Runnable S;
    private final WeakHashMap<String, ConstantState> T;
    private final OnClickListener U;
    private final OnEditorActionListener V;
    private final OnItemClickListener W;
    final SearchAutoComplete a;
    private final OnItemSelectedListener aa;
    private TextWatcher ab;
    final ImageView b;
    final ImageView c;
    final ImageView d;
    final ImageView e;
    OnFocusChangeListener f;
    android.support.v4.widget.d g;
    SearchableInfo h;
    OnKeyListener j;
    private final View k;
    private final View l;
    private final View m;
    private final View n;
    private e o;
    private Rect p;
    private Rect q;
    private int[] r;
    private int[] s;
    private final ImageView t;
    private final Drawable u;
    private final int v;
    private final int w;
    private final Intent x;
    private final Intent y;
    private final CharSequence z;

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        boolean b;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            this.b = ((Boolean) source.readValue(null)).booleanValue();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeValue(Boolean.valueOf(this.b));
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.b + "}";
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        final Runnable a;
        private int b;
        private SearchView c;
        private boolean d;

        static /* synthetic */ void b(SearchAutoComplete x0) {
            if (x0.d) {
                ((InputMethodManager) x0.getContext().getSystemService("input_method")).showSoftInput(x0, 0);
                x0.d = false;
            }
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attrs) {
            this(context, attrs, android.support.v7.appcompat.a.a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            this.a = new Runnable(this) {
                final /* synthetic */ SearchAutoComplete a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    SearchAutoComplete.b(this.a);
                }
            };
            this.b = getThreshold();
        }

        protected void onFinishInflate() {
            int i;
            super.onFinishInflate();
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            Configuration configuration = getResources().getConfiguration();
            int i2 = configuration.screenWidthDp;
            int i3 = configuration.screenHeightDp;
            if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
                i = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
            } else if (i2 >= 600 || (i2 >= 640 && i3 >= 480)) {
                i = 192;
            } else {
                i = 160;
            }
            setMinWidth((int) TypedValue.applyDimension(1, (float) i, metrics));
        }

        final void a(SearchView searchView) {
            this.c = searchView;
        }

        public void setThreshold(int threshold) {
            super.setThreshold(threshold);
            this.b = threshold;
        }

        protected void replaceText(CharSequence text) {
        }

        public void performCompletion() {
        }

        public void onWindowFocusChanged(boolean hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
            if (hasWindowFocus && this.c.hasFocus() && getVisibility() == 0) {
                this.d = true;
                if (SearchView.a(getContext())) {
                    SearchView.i.c(this);
                }
            }
        }

        protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
            this.c.n();
        }

        public boolean enoughToFilter() {
            return this.b <= 0 || super.enoughToFilter();
        }

        public boolean onKeyPreIme(int keyCode, KeyEvent event) {
            if (keyCode == 4) {
                DispatcherState state;
                if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                    state = getKeyDispatcherState();
                    if (state == null) {
                        return true;
                    }
                    state.startTracking(event, this);
                    return true;
                } else if (event.getAction() == 1) {
                    state = getKeyDispatcherState();
                    if (state != null) {
                        state.handleUpEvent(event);
                    }
                    if (event.isTracking() && !event.isCanceled()) {
                        this.c.clearFocus();
                        a(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(keyCode, event);
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection ic = super.onCreateInputConnection(editorInfo);
            if (this.d) {
                removeCallbacks(this.a);
                post(this.a);
            }
            return ic;
        }

        private void a(boolean visible) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService("input_method");
            if (!visible) {
                this.d = false;
                removeCallbacks(this.a);
                imm.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (imm.isActive(this)) {
                this.d = false;
                removeCallbacks(this.a);
                imm.showSoftInput(this, 0);
            } else {
                this.d = true;
            }
        }

        static /* synthetic */ boolean a(SearchAutoComplete x0) {
            return TextUtils.getTrimmedLength(x0.getText()) == 0;
        }
    }

    private static class a {
        private Method a;
        private Method b;
        private Method c;

        a() {
            try {
                this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b.setAccessible(true);
            } catch (NoSuchMethodException e2) {
            }
            try {
                this.c = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.c.setAccessible(true);
            } catch (NoSuchMethodException e3) {
            }
        }

        final void a(AutoCompleteTextView view) {
            if (this.a != null) {
                try {
                    this.a.invoke(view, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        final void b(AutoCompleteTextView view) {
            if (this.b != null) {
                try {
                    this.b.invoke(view, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        final void c(AutoCompleteTextView view) {
            if (this.c != null) {
                try {
                    this.c.invoke(view, new Object[]{Boolean.valueOf(true)});
                } catch (Exception e) {
                }
            }
        }
    }

    public interface b {
        boolean a();
    }

    public interface c {
        boolean a();
    }

    public interface d {
        boolean a();

        boolean b();
    }

    private static class e extends TouchDelegate {
        private final View a;
        private final Rect b = new Rect();
        private final Rect c = new Rect();
        private final Rect d = new Rect();
        private final int e;
        private boolean f;

        public e(Rect targetBounds, Rect actualBounds, View delegateView) {
            super(targetBounds, delegateView);
            this.e = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
            a(targetBounds, actualBounds);
            this.a = delegateView;
        }

        public final void a(Rect desiredBounds, Rect actualBounds) {
            this.b.set(desiredBounds);
            this.d.set(desiredBounds);
            this.d.inset(-this.e, -this.e);
            this.c.set(actualBounds);
        }

        public final boolean onTouchEvent(MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            boolean sendToDelegate = false;
            boolean hit = true;
            switch (event.getAction()) {
                case 0:
                    if (this.b.contains(x, y)) {
                        this.f = true;
                        sendToDelegate = true;
                        break;
                    }
                    break;
                case 1:
                case 2:
                    sendToDelegate = this.f;
                    if (sendToDelegate && !this.d.contains(x, y)) {
                        hit = false;
                        break;
                    }
                case 3:
                    sendToDelegate = this.f;
                    this.f = false;
                    break;
            }
            if (!sendToDelegate) {
                return false;
            }
            if (!hit || this.c.contains(x, y)) {
                event.setLocation((float) (x - this.c.left), (float) (y - this.c.top));
            } else {
                event.setLocation((float) (this.a.getWidth() / 2), (float) (this.a.getHeight() / 2));
            }
            return this.a.dispatchTouchEvent(event);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.a.a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.p = new Rect();
        this.q = new Rect();
        this.r = new int[2];
        this.s = new int[2];
        this.R = new Runnable(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.e();
            }
        };
        this.S = new Runnable(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (this.a.g != null && (this.a.g instanceof al)) {
                    this.a.g.a(null);
                }
            }
        };
        this.T = new WeakHashMap();
        this.U = new OnClickListener(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void onClick(View v) {
                if (v == this.a.b) {
                    this.a.h();
                } else if (v == this.a.d) {
                    this.a.g();
                } else if (v == this.a.c) {
                    this.a.f();
                } else if (v == this.a.e) {
                    this.a.i();
                } else if (v == this.a.a) {
                    this.a.p();
                }
            }
        };
        this.j = new OnKeyListener(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final boolean onKey(View v, int keyCode, KeyEvent event) {
                if (this.a.h == null) {
                    return false;
                }
                if (this.a.a.isPopupShowing() && this.a.a.getListSelection() != -1) {
                    SearchView searchView = this.a;
                    if (searchView.h == null || searchView.g == null || event.getAction() != 0 || !event.hasNoModifiers()) {
                        return false;
                    }
                    if (keyCode == 66 || keyCode == 84 || keyCode == 61) {
                        return searchView.b(searchView.a.getListSelection());
                    }
                    if (keyCode == 21 || keyCode == 22) {
                        int i;
                        if (keyCode == 21) {
                            i = 0;
                        } else {
                            i = searchView.a.length();
                        }
                        searchView.a.setSelection(i);
                        searchView.a.setListSelection(0);
                        searchView.a.clearListSelection();
                        SearchView.i.c(searchView.a);
                        return true;
                    } else if (keyCode != 19) {
                        return false;
                    } else {
                        searchView.a.getListSelection();
                        return false;
                    }
                } else if (SearchAutoComplete.a(this.a.a) || !event.hasNoModifiers() || event.getAction() != 1 || keyCode != 66) {
                    return false;
                } else {
                    v.cancelLongPress();
                    this.a.a(this.a.a.getText().toString());
                    return true;
                }
            }
        };
        this.V = new OnEditorActionListener(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                this.a.f();
                return true;
            }
        };
        this.W = new OnItemClickListener(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                this.a.b(position);
            }
        };
        this.aa = new OnItemSelectedListener(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                this.a.c(position);
            }

            public final void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.ab = new TextWatcher(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void beforeTextChanged(CharSequence s, int start, int before, int after) {
            }

            public final void onTextChanged(CharSequence s, int start, int before, int after) {
                this.a.b(s);
            }

            public final void afterTextChanged(Editable s) {
            }
        };
        aq a = aq.a(context, attrs, j.SearchView, defStyleAttr, 0);
        LayoutInflater.from(context).inflate(a.g(j.SearchView_layout, g.abc_search_view), this, true);
        this.a = (SearchAutoComplete) findViewById(f.search_src_text);
        this.a.a(this);
        this.k = findViewById(f.search_edit_frame);
        this.l = findViewById(f.search_plate);
        this.m = findViewById(f.submit_area);
        this.b = (ImageView) findViewById(f.search_button);
        this.c = (ImageView) findViewById(f.search_go_btn);
        this.d = (ImageView) findViewById(f.search_close_btn);
        this.e = (ImageView) findViewById(f.search_voice_btn);
        this.t = (ImageView) findViewById(f.search_mag_icon);
        ViewCompat.a(this.l, a.a(j.SearchView_queryBackground));
        ViewCompat.a(this.m, a.a(j.SearchView_submitBackground));
        this.b.setImageDrawable(a.a(j.SearchView_searchIcon));
        this.c.setImageDrawable(a.a(j.SearchView_goIcon));
        this.d.setImageDrawable(a.a(j.SearchView_closeIcon));
        this.e.setImageDrawable(a.a(j.SearchView_voiceIcon));
        this.t.setImageDrawable(a.a(j.SearchView_searchIcon));
        this.u = a.a(j.SearchView_searchHintIcon);
        as.a(this.b, getResources().getString(h.abc_searchview_description_search));
        this.v = a.g(j.SearchView_suggestionRowLayout, g.abc_search_dropdown_item_icons_2line);
        this.w = a.g(j.SearchView_commitIcon, 0);
        this.b.setOnClickListener(this.U);
        this.d.setOnClickListener(this.U);
        this.c.setOnClickListener(this.U);
        this.e.setOnClickListener(this.U);
        this.a.setOnClickListener(this.U);
        this.a.addTextChangedListener(this.ab);
        this.a.setOnEditorActionListener(this.V);
        this.a.setOnItemClickListener(this.W);
        this.a.setOnItemSelectedListener(this.aa);
        this.a.setOnKeyListener(this.j);
        this.a.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ SearchView a;

            {
                this.a = this$0;
            }

            public final void onFocusChange(View v, boolean hasFocus) {
                if (this.a.f != null) {
                    this.a.f.onFocusChange(this.a, hasFocus);
                }
            }
        });
        setIconifiedByDefault(a.a(j.SearchView_iconifiedByDefault, true));
        int maxWidth = a.e(j.SearchView_android_maxWidth, -1);
        if (maxWidth != -1) {
            setMaxWidth(maxWidth);
        }
        this.z = a.c(j.SearchView_defaultQueryHint);
        this.H = a.c(j.SearchView_queryHint);
        int imeOptions = a.a(j.SearchView_android_imeOptions, -1);
        if (imeOptions != -1) {
            setImeOptions(imeOptions);
        }
        int inputType = a.a(j.SearchView_android_inputType, -1);
        if (inputType != -1) {
            setInputType(inputType);
        }
        setFocusable(a.a(j.SearchView_android_focusable, true));
        a.a();
        this.x = new Intent("android.speech.action.WEB_SEARCH");
        this.x.addFlags(ErrorDialogData.BINDER_CRASH);
        this.x.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.y = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.y.addFlags(ErrorDialogData.BINDER_CRASH);
        this.n = findViewById(this.a.getDropDownAnchor());
        if (this.n != null) {
            this.n.addOnLayoutChangeListener(new OnLayoutChangeListener(this) {
                final /* synthetic */ SearchView a;

                {
                    this.a = this$0;
                }

                public final void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    this.a.o();
                }
            });
        }
        a(this.E);
        w();
    }

    final int c() {
        return this.v;
    }

    final int d() {
        return this.w;
    }

    public void setSearchableInfo(SearchableInfo searchable) {
        boolean z = true;
        this.h = searchable;
        if (this.h != null) {
            this.a.setThreshold(this.h.getSuggestThreshold());
            this.a.setImeOptions(this.h.getImeOptions());
            int inputType = this.h.getInputType();
            if ((inputType & 15) == 1) {
                inputType &= -65537;
                if (this.h.getSuggestAuthority() != null) {
                    inputType = (inputType | 65536) | 524288;
                }
            }
            this.a.setInputType(inputType);
            if (this.g != null) {
                this.g.a(null);
            }
            if (this.h.getSuggestAuthority() != null) {
                int i;
                this.g = new al(getContext(), this, this.h, this.T);
                this.a.setAdapter(this.g);
                al alVar = (al) this.g;
                if (this.I) {
                    i = 2;
                } else {
                    i = 1;
                }
                alVar.a(i);
            }
            w();
        }
        if (this.h != null && this.h.getVoiceSearchEnabled()) {
            Intent intent;
            if (this.h.getVoiceSearchLaunchWebSearch()) {
                intent = this.x;
            } else if (this.h.getVoiceSearchLaunchRecognizer()) {
                intent = this.y;
            } else {
                intent = null;
            }
            if (intent != null) {
                if (getContext().getPackageManager().resolveActivity(intent, 65536) == null) {
                    z = false;
                }
                this.L = z;
                if (this.L) {
                    this.a.setPrivateImeOptions("nm");
                }
                a(this.F);
            }
        }
        z = false;
        this.L = z;
        if (this.L) {
            this.a.setPrivateImeOptions("nm");
        }
        a(this.F);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setAppSearchData(Bundle appSearchData) {
        this.Q = appSearchData;
    }

    public void setImeOptions(int imeOptions) {
        this.a.setImeOptions(imeOptions);
    }

    public void setInputType(int inputType) {
        this.a.setInputType(inputType);
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (this.J) {
            return false;
        }
        if (!isFocusable()) {
            return false;
        }
        if (this.F) {
            return super.requestFocus(direction, previouslyFocusedRect);
        }
        boolean result = this.a.requestFocus(direction, previouslyFocusedRect);
        if (!result) {
            return result;
        }
        a(false);
        return result;
    }

    public void clearFocus() {
        this.J = true;
        super.clearFocus();
        this.a.clearFocus();
        this.a.a(false);
        this.J = false;
    }

    public void setOnQueryTextListener(c listener) {
        this.A = listener;
    }

    public void setOnCloseListener(b listener) {
        this.B = listener;
    }

    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener listener) {
        this.f = listener;
    }

    public void setOnSuggestionListener(d listener) {
        this.C = listener;
    }

    public void setOnSearchClickListener(OnClickListener listener) {
        this.D = listener;
    }

    public void setQuery(CharSequence query, boolean submit) {
        this.a.setText(query);
        if (query != null) {
            this.a.setSelection(this.a.length());
            this.N = query;
        }
        if (submit && !TextUtils.isEmpty(query)) {
            f();
        }
    }

    public void setQueryHint(@Nullable CharSequence hint) {
        this.H = hint;
        w();
    }

    public void setIconifiedByDefault(boolean iconified) {
        if (this.E != iconified) {
            this.E = iconified;
            a(iconified);
            w();
        }
    }

    public void setIconified(boolean iconify) {
        if (iconify) {
            g();
        } else {
            h();
        }
    }

    public void setSubmitButtonEnabled(boolean enabled) {
        this.G = enabled;
        a(this.F);
    }

    public void setQueryRefinementEnabled(boolean enable) {
        this.I = enable;
        if (this.g instanceof al) {
            ((al) this.g).a(enable ? 2 : 1);
        }
    }

    public void setSuggestionsAdapter(android.support.v4.widget.d adapter) {
        this.g = adapter;
        this.a.setAdapter(this.g);
    }

    public void setMaxWidth(int maxpixels) {
        this.K = maxpixels;
        requestLayout();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.F) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case Integer.MIN_VALUE:
                if (this.K <= 0) {
                    width = Math.min(q(), width);
                    break;
                } else {
                    width = Math.min(this.K, width);
                    break;
                }
            case 0:
                if (this.K <= 0) {
                    width = q();
                    break;
                } else {
                    width = this.K;
                    break;
                }
            case ErrorDialogData.SUPPRESSED /*1073741824*/:
                if (this.K > 0) {
                    width = Math.min(this.K, width);
                    break;
                }
                break;
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case Integer.MIN_VALUE:
                height = Math.min(r(), height);
                break;
            case 0:
                height = r();
                break;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(height, ErrorDialogData.SUPPRESSED));
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            View view = this.a;
            Rect rect = this.p;
            view.getLocationInWindow(this.r);
            getLocationInWindow(this.s);
            int i = this.r[1] - this.s[1];
            int i2 = this.r[0] - this.s[0];
            rect.set(i2, i, view.getWidth() + i2, view.getHeight() + i);
            this.q.set(this.p.left, 0, this.p.right, bottom - top);
            if (this.o == null) {
                this.o = new e(this.q, this.p, this.a);
                setTouchDelegate(this.o);
                return;
            }
            this.o.a(this.q, this.p);
        }
    }

    private int q() {
        return getContext().getResources().getDimensionPixelSize(android.support.v7.appcompat.a.d.abc_search_view_preferred_width);
    }

    private int r() {
        return getContext().getResources().getDimensionPixelSize(android.support.v7.appcompat.a.d.abc_search_view_preferred_height);
    }

    private void a(boolean collapsed) {
        int visCollapsed;
        boolean hasText;
        int iconVisibility;
        int i = 8;
        boolean z = true;
        this.F = collapsed;
        if (collapsed) {
            visCollapsed = 0;
        } else {
            visCollapsed = 8;
        }
        if (TextUtils.isEmpty(this.a.getText())) {
            hasText = false;
        } else {
            hasText = true;
        }
        this.b.setVisibility(visCollapsed);
        b(hasText);
        View view = this.k;
        if (!collapsed) {
            i = 0;
        }
        view.setVisibility(i);
        if (this.t.getDrawable() == null || this.E) {
            iconVisibility = 8;
        } else {
            iconVisibility = 0;
        }
        this.t.setVisibility(iconVisibility);
        u();
        if (hasText) {
            z = false;
        }
        c(z);
        t();
    }

    private boolean s() {
        return (this.G || this.L) && !this.F;
    }

    private void b(boolean hasText) {
        int visibility = 8;
        if (this.G && s() && hasFocus() && (hasText || !this.L)) {
            visibility = 0;
        }
        this.c.setVisibility(visibility);
    }

    private void t() {
        int visibility = 8;
        if (s() && (this.c.getVisibility() == 0 || this.e.getVisibility() == 0)) {
            visibility = 0;
        }
        this.m.setVisibility(visibility);
    }

    private void u() {
        boolean hasText;
        boolean showClose = true;
        int i = 0;
        if (TextUtils.isEmpty(this.a.getText())) {
            hasText = false;
        } else {
            hasText = true;
        }
        if (!hasText && (!this.E || this.O)) {
            showClose = false;
        }
        ImageView imageView = this.d;
        if (!showClose) {
            i = 8;
        }
        imageView.setVisibility(i);
        Drawable closeButtonImg = this.d.getDrawable();
        if (closeButtonImg != null) {
            closeButtonImg.setState(hasText ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void v() {
        post(this.R);
    }

    final void e() {
        int[] stateSet = this.a.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable searchPlateBg = this.l.getBackground();
        if (searchPlateBg != null) {
            searchPlateBg.setState(stateSet);
        }
        Drawable submitAreaBg = this.m.getBackground();
        if (submitAreaBg != null) {
            submitAreaBg.setState(stateSet);
        }
        invalidate();
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.R);
        post(this.S);
        super.onDetachedFromWindow();
    }

    final void a(CharSequence queryText) {
        c(queryText);
    }

    private void w() {
        CharSequence hint;
        if (this.H != null) {
            hint = this.H;
        } else if (this.h == null || this.h.getHintId() == 0) {
            hint = this.z;
        } else {
            hint = getContext().getText(this.h.getHintId());
        }
        SearchAutoComplete searchAutoComplete = this.a;
        if (hint == null) {
            hint = "";
        }
        if (this.E && this.u != null) {
            int textSize = (int) (((double) this.a.getTextSize()) * 1.25d);
            this.u.setBounds(0, 0, textSize, textSize);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(new ImageSpan(this.u), 1, 2, 33);
            spannableStringBuilder.append(hint);
            Object hint2 = spannableStringBuilder;
        }
        searchAutoComplete.setHint(hint);
    }

    private void c(boolean empty) {
        int visibility = 8;
        if (this.L && !this.F && empty) {
            visibility = 0;
            this.c.setVisibility(8);
        }
        this.e.setVisibility(visibility);
    }

    final void b(CharSequence newText) {
        boolean hasText;
        boolean z = true;
        CharSequence text = this.a.getText();
        this.N = text;
        if (TextUtils.isEmpty(text)) {
            hasText = false;
        } else {
            hasText = true;
        }
        b(hasText);
        if (hasText) {
            z = false;
        }
        c(z);
        u();
        t();
        if (!(this.A == null || TextUtils.equals(newText, this.M))) {
            newText.toString();
        }
        this.M = newText.toString();
    }

    final void f() {
        CharSequence query = this.a.getText();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            if (this.A != null) {
                c cVar = this.A;
                query.toString();
                if (cVar.a()) {
                    return;
                }
            }
            if (this.h != null) {
                a(query.toString());
            }
            this.a.a(false);
            this.a.dismissDropDown();
        }
    }

    final void g() {
        if (!TextUtils.isEmpty(this.a.getText())) {
            this.a.setText("");
            this.a.requestFocus();
            this.a.a(true);
        } else if (!this.E) {
        } else {
            if (this.B == null || !this.B.a()) {
                clearFocus();
                a(true);
            }
        }
    }

    final void h() {
        a(false);
        this.a.requestFocus();
        this.a.a(true);
        if (this.D != null) {
            this.D.onClick(this);
        }
    }

    final void i() {
        String str = null;
        if (this.h != null) {
            SearchableInfo searchable = this.h;
            try {
                String str2;
                if (searchable.getVoiceSearchLaunchWebSearch()) {
                    Intent webSearchIntent = new Intent(this.x);
                    ComponentName searchActivity = searchable.getSearchActivity();
                    str2 = "calling_package";
                    if (searchActivity != null) {
                        str = searchActivity.flattenToShortString();
                    }
                    webSearchIntent.putExtra(str2, str);
                    getContext().startActivity(webSearchIntent);
                } else if (searchable.getVoiceSearchLaunchRecognizer()) {
                    String string;
                    String string2;
                    Intent intent = this.y;
                    ComponentName searchActivity2 = searchable.getSearchActivity();
                    Intent intent2 = new Intent("android.intent.action.SEARCH");
                    intent2.setComponent(searchActivity2);
                    Parcelable activity = PendingIntent.getActivity(getContext(), 0, intent2, ErrorDialogData.SUPPRESSED);
                    Bundle bundle = new Bundle();
                    if (this.Q != null) {
                        bundle.putParcelable("app_data", this.Q);
                    }
                    Intent appSearchIntent = new Intent(intent);
                    str2 = "free_form";
                    int i = 1;
                    Resources resources = getResources();
                    if (searchable.getVoiceLanguageModeId() != 0) {
                        string = resources.getString(searchable.getVoiceLanguageModeId());
                    } else {
                        string = str2;
                    }
                    if (searchable.getVoicePromptTextId() != 0) {
                        string2 = resources.getString(searchable.getVoicePromptTextId());
                    } else {
                        string2 = null;
                    }
                    if (searchable.getVoiceLanguageId() != 0) {
                        str2 = resources.getString(searchable.getVoiceLanguageId());
                    } else {
                        str2 = null;
                    }
                    if (searchable.getVoiceMaxResults() != 0) {
                        i = searchable.getVoiceMaxResults();
                    }
                    appSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
                    appSearchIntent.putExtra("android.speech.extra.PROMPT", string2);
                    appSearchIntent.putExtra("android.speech.extra.LANGUAGE", str2);
                    appSearchIntent.putExtra("android.speech.extra.MAX_RESULTS", i);
                    String str3 = "calling_package";
                    if (searchActivity2 != null) {
                        str = searchActivity2.flattenToShortString();
                    }
                    appSearchIntent.putExtra(str3, str);
                    appSearchIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
                    appSearchIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
                    getContext().startActivity(appSearchIntent);
                }
            } catch (ActivityNotFoundException e) {
            }
        }
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        v();
    }

    public final void b() {
        setQuery("", false);
        clearFocus();
        a(true);
        this.a.setImeOptions(this.P);
        this.O = false;
    }

    public final void a() {
        if (!this.O) {
            this.O = true;
            this.P = this.a.getImeOptions();
            this.a.setImeOptions(this.P | 33554432);
            this.a.setText("");
            setIconified(false);
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.b = this.F;
        return ss;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.a());
            a(ss.b);
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(state);
    }

    final void o() {
        if (this.n.getWidth() > 1) {
            int offset;
            Resources res = getContext().getResources();
            int anchorPadding = this.l.getPaddingLeft();
            Rect dropDownPadding = new Rect();
            boolean isLayoutRtl = ax.a(this);
            int iconOffset = this.E ? res.getDimensionPixelSize(android.support.v7.appcompat.a.d.abc_dropdownitem_icon_width) + res.getDimensionPixelSize(android.support.v7.appcompat.a.d.abc_dropdownitem_text_padding_left) : 0;
            this.a.getDropDownBackground().getPadding(dropDownPadding);
            if (isLayoutRtl) {
                offset = -dropDownPadding.left;
            } else {
                offset = anchorPadding - (dropDownPadding.left + iconOffset);
            }
            this.a.setDropDownHorizontalOffset(offset);
            this.a.setDropDownWidth((((this.n.getWidth() + dropDownPadding.left) + dropDownPadding.right) + iconOffset) - anchorPadding);
        }
    }

    final boolean b(int position) {
        if (this.C != null && this.C.b()) {
            return false;
        }
        Cursor a = this.g.a();
        if (a != null && a.moveToPosition(position)) {
            Intent a2 = a(a);
            if (a2 != null) {
                try {
                    getContext().startActivity(a2);
                } catch (RuntimeException e) {
                    new StringBuilder("Failed launch activity: ").append(a2);
                }
            }
        }
        this.a.a(false);
        this.a.dismissDropDown();
        return true;
    }

    final boolean c(int position) {
        if (this.C != null && this.C.a()) {
            return false;
        }
        CharSequence text = this.a.getText();
        Cursor a = this.g.a();
        if (a != null) {
            if (a.moveToPosition(position)) {
                CharSequence b = this.g.b(a);
                if (b != null) {
                    c(b);
                } else {
                    c(text);
                }
            } else {
                c(text);
            }
        }
        return true;
    }

    private void c(CharSequence query) {
        this.a.setText(query);
        this.a.setSelection(TextUtils.isEmpty(query) ? 0 : query.length());
    }

    final void a(String query) {
        getContext().startActivity(a("android.intent.action.SEARCH", null, null, query));
    }

    private Intent a(String action, Uri data, String extraData, String query) {
        Intent intent = new Intent(action);
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        if (data != null) {
            intent.setData(data);
        }
        intent.putExtra("user_query", this.N);
        if (query != null) {
            intent.putExtra("query", query);
        }
        if (extraData != null) {
            intent.putExtra("intent_extra_data_key", extraData);
        }
        if (this.Q != null) {
            intent.putExtra("app_data", this.Q);
        }
        intent.setComponent(this.h.getSearchActivity());
        return intent;
    }

    private Intent a(Cursor c) {
        try {
            String action = al.a(c, "suggest_intent_action");
            if (action == null) {
                action = this.h.getSuggestIntentAction();
            }
            if (action == null) {
                action = "android.intent.action.SEARCH";
            }
            String data = al.a(c, "suggest_intent_data");
            if (data == null) {
                data = this.h.getSuggestIntentData();
            }
            if (data != null) {
                String id = al.a(c, "suggest_intent_data_id");
                if (id != null) {
                    data = data + "/" + Uri.encode(id);
                }
            }
            return a(action, data == null ? null : Uri.parse(data), al.a(c, "suggest_intent_extra_data"), al.a(c, "suggest_intent_query"));
        } catch (RuntimeException e) {
            int rowNum;
            try {
                rowNum = c.getPosition();
            } catch (RuntimeException e2) {
                rowNum = -1;
            }
            new StringBuilder("Search suggestions cursor at row ").append(rowNum).append(" returned exception.");
            return null;
        }
    }

    final void p() {
        i.a(this.a);
        i.b(this.a);
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    final void n() {
        a(this.F);
        v();
        if (this.a.hasFocus()) {
            p();
        }
    }
}
