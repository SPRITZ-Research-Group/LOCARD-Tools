package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController.RecycleListView;

public final class d {
    public int A;
    public int B;
    public int C;
    public int D;
    public boolean E = false;
    public boolean[] F;
    public boolean G;
    public boolean H;
    public int I = -1;
    public OnMultiChoiceClickListener J;
    public Cursor K;
    public String L;
    public String M;
    public OnItemSelectedListener N;
    public boolean O = true;
    public final Context a;
    public final LayoutInflater b;
    public int c = 0;
    public Drawable d;
    public int e = 0;
    public CharSequence f;
    public View g;
    public CharSequence h;
    public CharSequence i;
    public Drawable j;
    public OnClickListener k;
    public CharSequence l;
    public Drawable m;
    public OnClickListener n;
    public CharSequence o;
    public Drawable p;
    public OnClickListener q;
    public boolean r;
    public OnCancelListener s;
    public OnDismissListener t;
    public OnKeyListener u;
    public CharSequence[] v;
    public ListAdapter w;
    public OnClickListener x;
    public int y;
    public View z;

    /* renamed from: androidx.appcompat.app.d$1 */
    final class AnonymousClass1 extends ArrayAdapter<CharSequence> {
        final /* synthetic */ RecycleListView a;
        final /* synthetic */ d b;

        AnonymousClass1(d dVar, Context context, int i, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
            this.b = dVar;
            this.a = recycleListView;
            super(context, i, 16908308, charSequenceArr);
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            view = super.getView(i, view, viewGroup);
            if (this.b.F != null && this.b.F[i]) {
                this.a.setItemChecked(i, true);
            }
            return view;
        }
    }

    /* renamed from: androidx.appcompat.app.d$2 */
    final class AnonymousClass2 extends CursorAdapter {
        final /* synthetic */ RecycleListView a;
        final /* synthetic */ AlertController b;
        final /* synthetic */ d c;
        private final int d;
        private final int e;

        AnonymousClass2(d dVar, Context context, Cursor cursor, RecycleListView recycleListView, AlertController alertController) {
            this.c = dVar;
            this.a = recycleListView;
            this.b = alertController;
            super(context, cursor, false);
            Cursor cursor2 = getCursor();
            this.d = cursor2.getColumnIndexOrThrow(this.c.L);
            this.e = cursor2.getColumnIndexOrThrow(this.c.M);
        }

        public final void bindView(View view, Context context, Cursor cursor) {
            ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.d));
            RecycleListView recycleListView = this.a;
            int position = cursor.getPosition();
            boolean z = true;
            if (cursor.getInt(this.e) != 1) {
                z = false;
            }
            recycleListView.setItemChecked(position, z);
        }

        public final View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return this.c.b.inflate(this.b.m, viewGroup, false);
        }
    }

    /* renamed from: androidx.appcompat.app.d$3 */
    final class AnonymousClass3 implements OnItemClickListener {
        final /* synthetic */ AlertController a;
        final /* synthetic */ d b;

        AnonymousClass3(d dVar, AlertController alertController) {
            this.b = dVar;
            this.a = alertController;
        }

        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.b.x.onClick(this.a.a, i);
            if (!this.b.H) {
                this.a.a.dismiss();
            }
        }
    }

    /* renamed from: androidx.appcompat.app.d$4 */
    final class AnonymousClass4 implements OnItemClickListener {
        final /* synthetic */ RecycleListView a;
        final /* synthetic */ AlertController b;
        final /* synthetic */ d c;

        AnonymousClass4(d dVar, RecycleListView recycleListView, AlertController alertController) {
            this.c = dVar;
            this.a = recycleListView;
            this.b = alertController;
        }

        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.c.F != null) {
                this.c.F[i] = this.a.isItemChecked(i);
            }
            this.c.J.onClick(this.b.a, i, this.a.isItemChecked(i));
        }
    }

    public d(Context context) {
        this.a = context;
        this.r = true;
        this.b = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
