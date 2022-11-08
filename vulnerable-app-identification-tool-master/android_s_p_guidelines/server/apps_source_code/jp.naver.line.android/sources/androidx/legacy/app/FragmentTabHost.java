package androidx.legacy.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

@Deprecated
public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<a> a = new ArrayList();
    private FrameLayout b;
    private Context c;
    private FragmentManager d;
    private int e;
    private OnTabChangeListener f;
    private a g;
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

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        a(context, null);
    }

    @Deprecated
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

    @Deprecated
    public void setup(Context context, FragmentManager fragmentManager) {
        a(context);
        super.setup();
        this.c = context;
        this.d = fragmentManager;
        a();
    }

    @Deprecated
    public void setup(Context context, FragmentManager fragmentManager, int i) {
        a(context);
        super.setup();
        this.c = context;
        this.d = fragmentManager;
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

    @Deprecated
    public void setOnTabChangedListener(OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        FragmentTransaction fragmentTransaction = null;
        for (int i = 0; i < this.a.size(); i++) {
            a aVar = (a) this.a.get(i);
            aVar.d = this.d.findFragmentByTag(aVar.a);
            if (!(aVar.d == null || aVar.d.isDetached())) {
                if (aVar.a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.d.beginTransaction();
                    }
                    fragmentTransaction.detach(aVar.d);
                }
            }
        }
        this.h = true;
        FragmentTransaction a = a(currentTabTag, fragmentTransaction);
        if (a != null) {
            a.commit();
            this.d.executePendingTransactions();
        }
    }

    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    @Deprecated
    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentTabTag();
        return savedState;
    }

    @Deprecated
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setCurrentTabByTag(savedState.a);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Deprecated
    public void onTabChanged(String str) {
        if (this.h) {
            FragmentTransaction a = a(str, null);
            if (a != null) {
                a.commit();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(str);
        }
    }

    private FragmentTransaction a(String str, FragmentTransaction fragmentTransaction) {
        a aVar = null;
        for (int i = 0; i < this.a.size(); i++) {
            a aVar2 = (a) this.a.get(i);
            if (aVar2.a.equals(str)) {
                aVar = aVar2;
            }
        }
        if (aVar != null) {
            if (this.g != aVar) {
                if (fragmentTransaction == null) {
                    fragmentTransaction = this.d.beginTransaction();
                }
                if (!(this.g == null || this.g.d == null)) {
                    fragmentTransaction.detach(this.g.d);
                }
                if (aVar != null) {
                    if (aVar.d == null) {
                        aVar.d = Fragment.instantiate(this.c, aVar.b.getName(), aVar.c);
                        fragmentTransaction.add(this.e, aVar.d, aVar.a);
                    } else {
                        fragmentTransaction.attach(aVar.d);
                    }
                }
                this.g = aVar;
            }
            return fragmentTransaction;
        }
        throw new IllegalStateException("No tab known for tag ".concat(String.valueOf(str)));
    }
}
