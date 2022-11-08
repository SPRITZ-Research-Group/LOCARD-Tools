package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class j extends d {
    private int j;
    private int k;
    private LayoutInflater l;

    @Deprecated
    public j(Context context, int layout) {
        super(context);
        this.k = layout;
        this.j = layout;
        this.l = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View a(Context context, Cursor cursor, ViewGroup parent) {
        return this.l.inflate(this.j, parent, false);
    }

    public final View b(Context context, Cursor cursor, ViewGroup parent) {
        return this.l.inflate(this.k, parent, false);
    }
}
