package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

final class e extends Filter {
    a a;

    interface a {
        Cursor a();

        Cursor a(CharSequence charSequence);

        void a(Cursor cursor);

        CharSequence b(Cursor cursor);
    }

    e(a client) {
        this.a = client;
    }

    public final CharSequence convertResultToString(Object resultValue) {
        return this.a.b((Cursor) resultValue);
    }

    protected final FilterResults performFiltering(CharSequence constraint) {
        Cursor cursor = this.a.a(constraint);
        FilterResults results = new FilterResults();
        if (cursor != null) {
            results.count = cursor.getCount();
            results.values = cursor;
        } else {
            results.count = 0;
            results.values = null;
        }
        return results;
    }

    protected final void publishResults(CharSequence constraint, FilterResults results) {
        Cursor oldCursor = this.a.a();
        if (results.values != null && results.values != oldCursor) {
            this.a.a((Cursor) results.values);
        }
    }
}
