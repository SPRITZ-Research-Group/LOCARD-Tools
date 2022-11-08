package android.support.v4.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<a> a = new ArrayList();
    private FrameLayout b;
    private Context c;
    private i d;
    private int e;
    private OnTabChangeListener f;
    private a g;
    private boolean h;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        String a;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel in) {
            super(in);
            this.a = in.readString();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.a);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.a + "}";
        }
    }

    static final class a {
        @NonNull
        final String a;
        @NonNull
        final Class<?> b;
        @Nullable
        final Bundle c;
        Fragment d;
    }

    public FragmentTabHost(Context context) {
        super(context, null);
        a(context, null);
    }

    public FragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        a(context, attrs);
    }

    private void a(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{16842995}, 0, 0);
        this.e = a.getResourceId(0, 0);
        a.recycle();
        super.setOnTabChangedListener(this);
    }

    private void a(Context context) {
        if (findViewById(16908307) == null) {
            LinearLayout ll = new LinearLayout(context);
            ll.setOrientation(1);
            addView(ll, new LayoutParams(-1, -1));
            TabWidget tw = new TabWidget(context);
            tw.setId(16908307);
            tw.setOrientation(0);
            ll.addView(tw, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            FrameLayout fl = new FrameLayout(context);
            fl.setId(16908305);
            ll.addView(fl, new LinearLayout.LayoutParams(0, 0, 0.0f));
            fl = new FrameLayout(context);
            this.b = fl;
            this.b.setId(this.e);
            ll.addView(fl, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, i manager) {
        a(context);
        super.setup();
        this.c = context;
        this.d = manager;
        a();
    }

    public void setup(Context context, i manager, int containerId) {
        a(context);
        super.setup();
        this.c = context;
        this.d = manager;
        this.e = containerId;
        a();
        this.b.setId(containerId);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = (FrameLayout) findViewById(this.e);
            if (this.b == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.e);
            }
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener l) {
        this.f = l;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTag = getCurrentTabTag();
        FragmentTransaction ft = null;
        int count = this.a.size();
        for (int i = 0; i < count; i++) {
            a tab = (a) this.a.get(i);
            tab.d = this.d.a(tab.a);
            if (!(tab.d == null || tab.d.isDetached())) {
                if (tab.a.equals(currentTag)) {
                    this.g = tab;
                } else {
                    if (ft == null) {
                        ft = this.d.a();
                    }
                    ft.b(tab.d);
                }
            }
        }
        this.h = true;
        ft = a(currentTag, ft);
        if (ft != null) {
            ft.b();
            this.d.b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.a = getCurrentTabTag();
        return ss;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            setCurrentTabByTag(ss.a);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void onTabChanged(String tabId) {
        if (this.h) {
            FragmentTransaction ft = a(tabId, null);
            if (ft != null) {
                ft.b();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(tabId);
        }
    }

    @Nullable
    private FragmentTransaction a(@Nullable String tag, @Nullable FragmentTransaction ft) {
        a newTab;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.a.get(i);
            if (aVar.a.equals(tag)) {
                newTab = aVar;
                break;
            }
        }
        newTab = null;
        if (this.g != newTab) {
            if (ft == null) {
                ft = this.d.a();
            }
            if (!(this.g == null || this.g.d == null)) {
                ft.b(this.g.d);
            }
            if (newTab != null) {
                if (newTab.d == null) {
                    newTab.d = Fragment.instantiate(this.c, newTab.b.getName(), newTab.c);
                    ft.a(this.e, newTab.d, newTab.a);
                } else {
                    ft.c(newTab.d);
                }
            }
            this.g = newTab;
        }
        return ft;
    }
}
