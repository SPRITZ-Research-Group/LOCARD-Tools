package androidx.appcompat.widget;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import defpackage.r;
import defpackage.s;
import defpackage.t;

final class u extends BaseAdapter {
    final /* synthetic */ ActivityChooserView a;
    private o b;
    private int c = 4;
    private boolean d;
    private boolean e;
    private boolean f;

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getViewTypeCount() {
        return 3;
    }

    u(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public final void a(o oVar) {
        o oVar2 = this.a.a.b;
        if (oVar2 != null && this.a.isShown()) {
            oVar2.unregisterObserver(this.a.e);
        }
        this.b = oVar;
        if (oVar != null && this.a.isShown()) {
            oVar.registerObserver(this.a.e);
        }
        notifyDataSetChanged();
    }

    public final int getItemViewType(int i) {
        return (this.f && i == getCount() - 1) ? 1 : 0;
    }

    public final int getCount() {
        int a = this.b.a();
        if (!(this.d || this.b.b() == null)) {
            a--;
        }
        a = Math.min(a, this.c);
        return this.f ? a + 1 : a;
    }

    public final Object getItem(int i) {
        switch (getItemViewType(i)) {
            case 0:
                if (!(this.d || this.b.b() == null)) {
                    i++;
                }
                return this.b.a(i);
            case 1:
                return null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case 0:
                if (view == null || view.getId() != r.list_item) {
                    view = LayoutInflater.from(this.a.getContext()).inflate(s.abc_activity_chooser_view_list_item, viewGroup, false);
                }
                PackageManager packageManager = this.a.getContext().getPackageManager();
                ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
                ((ImageView) view.findViewById(r.icon)).setImageDrawable(resolveInfo.loadIcon(packageManager));
                ((TextView) view.findViewById(r.title)).setText(resolveInfo.loadLabel(packageManager));
                if (this.d && i == 0 && this.e) {
                    view.setActivated(true);
                } else {
                    view.setActivated(false);
                }
                return view;
            case 1:
                if (view == null || view.getId() != 1) {
                    view = LayoutInflater.from(this.a.getContext()).inflate(s.abc_activity_chooser_view_list_item, viewGroup, false);
                    view.setId(1);
                    ((TextView) view.findViewById(r.title)).setText(this.a.getContext().getString(t.abc_activity_chooser_view_see_all));
                }
                return view;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final int a() {
        int i = this.c;
        this.c = BaseClientBuilder.API_PRIORITY_OTHER;
        int i2 = 0;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
        int count = getCount();
        View view = null;
        int i3 = 0;
        while (i2 < count) {
            view = getView(i2, view, null);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
            i2++;
        }
        this.c = i;
        return i3;
    }

    public final void a(int i) {
        if (this.c != i) {
            this.c = i;
            notifyDataSetChanged();
        }
    }

    public final ResolveInfo b() {
        return this.b.b();
    }

    public final void a(boolean z) {
        if (this.f != z) {
            this.f = z;
            notifyDataSetChanged();
        }
    }

    public final int c() {
        return this.b.a();
    }

    public final int d() {
        return this.b.c();
    }

    public final o e() {
        return this.b;
    }

    public final void a(boolean z, boolean z2) {
        if (this.d != z || this.e != z2) {
            this.d = z;
            this.e = z2;
            notifyDataSetChanged();
        }
    }

    public final boolean f() {
        return this.d;
    }
}
