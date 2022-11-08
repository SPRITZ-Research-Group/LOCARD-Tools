package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class d extends BaseAdapter implements a, Filterable {
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected boolean a = false;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected boolean b = true;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected Cursor c = null;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected Context d;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected int e;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected a f;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected DataSetObserver g;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected e h;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected FilterQueryProvider i;

    private class a extends ContentObserver {
        final /* synthetic */ d a;

        a(d dVar) {
            this.a = dVar;
            super(new Handler());
        }

        public final boolean deliverSelfNotifications() {
            return true;
        }

        public final void onChange(boolean selfChange) {
            this.a.b();
        }
    }

    private class b extends DataSetObserver {
        final /* synthetic */ d a;

        b(d dVar) {
            this.a = dVar;
        }

        public final void onChanged() {
            this.a.a = true;
            this.a.notifyDataSetChanged();
        }

        public final void onInvalidated() {
            this.a.a = false;
            this.a.notifyDataSetInvalidated();
        }
    }

    public abstract View a(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract void a(View view, Cursor cursor);

    public d(Context context) {
        this.d = context;
        this.e = -1;
        this.f = new a(this);
        this.g = new b(this);
    }

    public final Cursor a() {
        return this.c;
    }

    public int getCount() {
        if (!this.a || this.c == null) {
            return 0;
        }
        return this.c.getCount();
    }

    public Object getItem(int position) {
        if (!this.a || this.c == null) {
            return null;
        }
        this.c.moveToPosition(position);
        return this.c;
    }

    public long getItemId(int position) {
        if (this.a && this.c != null && this.c.moveToPosition(position)) {
            return this.c.getLong(this.e);
        }
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (!this.a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.c.moveToPosition(position)) {
            View v;
            if (convertView == null) {
                v = a(this.d, this.c, parent);
            } else {
                v = convertView;
            }
            a(v, this.c);
            return v;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (!this.a) {
            return null;
        }
        View v;
        this.c.moveToPosition(position);
        if (convertView == null) {
            v = b(this.d, this.c, parent);
        } else {
            v = convertView;
        }
        a(v, this.c);
        return v;
    }

    public View b(Context context, Cursor cursor, ViewGroup parent) {
        return a(context, cursor, parent);
    }

    public CharSequence b(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    public Cursor a(CharSequence constraint) {
        if (this.i != null) {
            return this.i.runQuery(constraint);
        }
        return this.c;
    }

    public Filter getFilter() {
        if (this.h == null) {
            this.h = new e(this);
        }
        return this.h;
    }

    protected final void b() {
        if (this.b && this.c != null && !this.c.isClosed()) {
            this.a = this.c.requery();
        }
    }

    public void a(Cursor cursor) {
        Cursor old;
        if (cursor == this.c) {
            old = null;
        } else {
            old = this.c;
            if (old != null) {
                if (this.f != null) {
                    old.unregisterContentObserver(this.f);
                }
                if (this.g != null) {
                    old.unregisterDataSetObserver(this.g);
                }
            }
            this.c = cursor;
            if (cursor != null) {
                if (this.f != null) {
                    cursor.registerContentObserver(this.f);
                }
                if (this.g != null) {
                    cursor.registerDataSetObserver(this.g);
                }
                this.e = cursor.getColumnIndexOrThrow("_id");
                this.a = true;
                notifyDataSetChanged();
            } else {
                this.e = -1;
                this.a = false;
                notifyDataSetInvalidated();
            }
        }
        if (old != null) {
            old.close();
        }
    }
}
