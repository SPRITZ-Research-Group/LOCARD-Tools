package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<ac> a = new ArrayList();
    private FrameLayout b;
    private Context c;
    private k d;
    private int e;
    private OnTabChangeListener f;
    private ac g;
    private boolean h;

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        String a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.a);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("FragmentTabHost.SavedState{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" curTab=");
            stringBuilder.append(this.a);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    public FragmentTabHost(Context context) {
        super(context, null);
        a(context, null);
    }

    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.e = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    private void a(Context context) {
        if (findViewById(16908307) == null) {
            View linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new LayoutParams(-1, -1));
            View tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, BitmapDescriptorFactory.HUE_RED));
            tabWidget = new FrameLayout(context);
            tabWidget.setId(16908305);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(0, 0, BitmapDescriptorFactory.HUE_RED));
            tabWidget = new FrameLayout(context);
            this.b = tabWidget;
            this.b.setId(this.e);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, k kVar) {
        a(context);
        super.setup();
        this.c = context;
        this.d = kVar;
        a();
    }

    public void setup(Context context, k kVar, int i) {
        a(context);
        super.setup();
        this.c = context;
        this.d = kVar;
        this.e = i;
        a();
        this.b.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = (FrameLayout) findViewById(this.e);
            if (this.b == null) {
                StringBuilder stringBuilder = new StringBuilder("No tab content FrameLayout found for id ");
                stringBuilder.append(this.e);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.a.size();
        ad adVar = null;
        for (int i = 0; i < size; i++) {
            ac acVar = (ac) this.a.get(i);
            acVar.d = this.d.a(acVar.a);
            if (!(acVar.d == null || acVar.d.isDetached())) {
                if (acVar.a.equals(currentTabTag)) {
                    this.g = acVar;
                } else {
                    if (adVar == null) {
                        adVar = this.d.a();
                    }
                    adVar.d(acVar.d);
                }
            }
        }
        this.h = true;
        ad a = a(currentTabTag, adVar);
        if (a != null) {
            a.d();
            this.d.b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentTabTag();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setCurrentTabByTag(savedState.a);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void onTabChanged(String str) {
        if (this.h) {
            ad a = a(str, null);
            if (a != null) {
                a.d();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(str);
        }
    }

    private ad a(String str, ad adVar) {
        ac a = a(str);
        if (this.g != a) {
            if (adVar == null) {
                adVar = this.d.a();
            }
            if (!(this.g == null || this.g.d == null)) {
                adVar.d(this.g.d);
            }
            if (a != null) {
                if (a.d == null) {
                    a.d = Fragment.instantiate(this.c, a.b.getName(), a.c);
                    adVar.a(this.e, a.d, a.a);
                } else {
                    adVar.e(a.d);
                }
            }
            this.g = a;
        }
        return adVar;
    }

    private ac a(String str) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ac acVar = (ac) this.a.get(i);
            if (acVar.a.equals(str)) {
                return acVar;
            }
        }
        return null;
    }
}
