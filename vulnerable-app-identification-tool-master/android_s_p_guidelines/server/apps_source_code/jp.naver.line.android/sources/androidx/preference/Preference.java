package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import defpackage.eo;
import java.util.List;

public class Preference implements Comparable<Preference> {
    private boolean A;
    private int B;
    private int C;
    private List<Preference> D;
    private final OnClickListener E;
    private Context a;
    private f b;
    private d c;
    private b d;
    private c e;
    private int f;
    private int g;
    private CharSequence h;
    private CharSequence i;
    private int j;
    private String k;
    private Intent l;
    private String m;
    private boolean n;
    private boolean o;
    private boolean p;
    private String q;
    private Object r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public class BaseSavedState extends AbsSavedState {
        public static final Creator<BaseSavedState> CREATOR = new Creator<BaseSavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new BaseSavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new BaseSavedState(parcel);
            }
        };

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }
    }

    protected Object a(TypedArray typedArray, int i) {
        return null;
    }

    protected void a() {
    }

    protected void onClick() {
    }

    public /* synthetic */ int compareTo(Object obj) {
        Preference preference = (Preference) obj;
        if (this.f != preference.f) {
            return this.f - preference.f;
        }
        if (this.h == preference.h) {
            return 0;
        }
        if (this.h == null) {
            return 1;
        }
        if (preference.h == null) {
            return -1;
        }
        return this.h.toString().compareToIgnoreCase(preference.h.toString());
    }

    public Preference(Context context, AttributeSet attributeSet, int i, byte b) {
        this.f = BaseClientBuilder.API_PRIORITY_OTHER;
        this.g = 0;
        this.n = true;
        this.o = true;
        this.p = true;
        this.s = true;
        this.t = true;
        this.u = true;
        this.v = true;
        this.w = true;
        this.y = true;
        this.A = true;
        this.B = k.preference;
        this.E = new OnClickListener(this) {
            final /* synthetic */ Preference a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.a(view);
            }
        };
        this.a = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.Preference, i, 0);
        this.j = eo.a(obtainStyledAttributes, l.Preference_icon, l.Preference_android_icon, 0);
        this.k = eo.b(obtainStyledAttributes, l.Preference_key, l.Preference_android_key);
        this.h = eo.c(obtainStyledAttributes, l.Preference_title, l.Preference_android_title);
        this.i = eo.c(obtainStyledAttributes, l.Preference_summary, l.Preference_android_summary);
        this.f = eo.a(obtainStyledAttributes, l.Preference_order, l.Preference_android_order);
        this.m = eo.b(obtainStyledAttributes, l.Preference_fragment, l.Preference_android_fragment);
        this.B = eo.a(obtainStyledAttributes, l.Preference_layout, l.Preference_android_layout, k.preference);
        this.C = eo.a(obtainStyledAttributes, l.Preference_widgetLayout, l.Preference_android_widgetLayout, 0);
        this.n = eo.a(obtainStyledAttributes, l.Preference_enabled, l.Preference_android_enabled, true);
        this.o = eo.a(obtainStyledAttributes, l.Preference_selectable, l.Preference_android_selectable, true);
        this.p = eo.a(obtainStyledAttributes, l.Preference_persistent, l.Preference_android_persistent, true);
        this.q = eo.b(obtainStyledAttributes, l.Preference_dependency, l.Preference_android_dependency);
        int i2 = l.Preference_allowDividerAbove;
        this.v = eo.a(obtainStyledAttributes, i2, i2, this.o);
        i2 = l.Preference_allowDividerBelow;
        this.w = eo.a(obtainStyledAttributes, i2, i2, this.o);
        if (obtainStyledAttributes.hasValue(l.Preference_defaultValue)) {
            this.r = a(obtainStyledAttributes, l.Preference_defaultValue);
        } else if (obtainStyledAttributes.hasValue(l.Preference_android_defaultValue)) {
            this.r = a(obtainStyledAttributes, l.Preference_android_defaultValue);
        }
        this.A = eo.a(obtainStyledAttributes, l.Preference_shouldDisableView, l.Preference_android_shouldDisableView, true);
        this.x = obtainStyledAttributes.hasValue(l.Preference_singleLineTitle);
        if (this.x) {
            this.y = eo.a(obtainStyledAttributes, l.Preference_singleLineTitle, l.Preference_android_singleLineTitle, true);
        }
        this.z = eo.a(obtainStyledAttributes, l.Preference_iconSpaceReserved, l.Preference_android_iconSpaceReserved, false);
        i2 = l.Preference_isPreferenceVisible;
        this.u = eo.a(obtainStyledAttributes, i2, i2, true);
        obtainStyledAttributes.recycle();
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, (byte) 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, eo.a(context, i.preferenceStyle, 16842894));
    }

    public final Intent g() {
        return this.l;
    }

    public final String h() {
        return this.m;
    }

    private d c() {
        if (this.c != null) {
            return this.c;
        }
        return this.b != null ? this.b.a() : null;
    }

    public final CharSequence i() {
        return this.h;
    }

    public CharSequence e() {
        return this.i;
    }

    public boolean j() {
        return this.n && this.s && this.t;
    }

    public final boolean k() {
        return !TextUtils.isEmpty(this.k);
    }

    private boolean d() {
        return this.b != null && this.p && k();
    }

    public final boolean l() {
        return this.d == null || this.d.a();
    }

    public final Context m() {
        return this.a;
    }

    public void a(boolean z) {
        List list = this.D;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Preference preference = (Preference) list.get(i);
                if (preference.s == z) {
                    preference.s = z ^ 1;
                    preference.a(preference.b());
                    preference.a();
                }
            }
        }
    }

    public final void b(boolean z) {
        if (this.t == z) {
            this.t = z ^ 1;
            a(b());
            a();
        }
    }

    public boolean b() {
        return !j();
    }

    private void a(Editor editor) {
        if (this.b.d()) {
            editor.apply();
        }
    }

    protected final boolean b(String str) {
        if (!d()) {
            return false;
        }
        CharSequence charSequence = null;
        if (d() && c() == null) {
            charSequence = this.b.b().getString(this.k, null);
        }
        if (TextUtils.equals(str, charSequence)) {
            return true;
        }
        if (c() == null) {
            Editor c = this.b.c();
            c.putString(this.k, str);
            a(c);
            return true;
        }
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    protected final boolean a(int i) {
        if (!d()) {
            return false;
        }
        int i2 = i ^ -1;
        if (d() && c() == null) {
            i2 = this.b.b().getInt(this.k, i2);
        }
        if (i == i2) {
            return true;
        }
        if (c() == null) {
            Editor c = this.b.c();
            c.putInt(this.k, i);
            a(c);
            return true;
        }
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    protected final boolean c(boolean z) {
        if (!d()) {
            return false;
        }
        boolean z2 = z ^ 1;
        if (d() && c() == null) {
            z2 = this.b.b().getBoolean(this.k, z2);
        }
        if (z == z2) {
            return true;
        }
        if (c() == null) {
            Editor c = this.b.c();
            c.putBoolean(this.k, z);
            a(c);
            return true;
        }
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    protected void a(View view) {
        if (j()) {
            onClick();
            if (this.e == null || !this.e.a()) {
                f fVar = this.b;
                if (fVar != null) {
                    g e = fVar.e();
                    if (e != null && e.a()) {
                        return;
                    }
                }
                if (this.l != null) {
                    this.a.startActivity(this.l);
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence charSequence = this.h;
        if (!TextUtils.isEmpty(charSequence)) {
            stringBuilder.append(charSequence);
            stringBuilder.append(' ');
        }
        charSequence = e();
        if (!TextUtils.isEmpty(charSequence)) {
            stringBuilder.append(charSequence);
            stringBuilder.append(' ');
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
