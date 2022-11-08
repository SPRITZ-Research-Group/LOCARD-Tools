package androidx.appcompat.widget;

import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

final class ai implements ListAdapter, SpinnerAdapter {
    private SpinnerAdapter a;
    private ListAdapter b;

    public final int getItemViewType(int i) {
        return 0;
    }

    public final int getViewTypeCount() {
        return 1;
    }

    public ai(SpinnerAdapter spinnerAdapter, Theme theme) {
        this.a = spinnerAdapter;
        if (spinnerAdapter instanceof ListAdapter) {
            this.b = (ListAdapter) spinnerAdapter;
        }
        if (theme != null && VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
            ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
            if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                themedSpinnerAdapter.setDropDownViewTheme(theme);
            }
        }
    }

    public final int getCount() {
        return this.a == null ? 0 : this.a.getCount();
    }

    public final Object getItem(int i) {
        return this.a == null ? null : this.a.getItem(i);
    }

    public final long getItemId(int i) {
        return this.a == null ? -1 : this.a.getItemId(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        return getDropDownView(i, view, viewGroup);
    }

    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (this.a == null) {
            return null;
        }
        return this.a.getDropDownView(i, view, viewGroup);
    }

    public final boolean hasStableIds() {
        return this.a != null && this.a.hasStableIds();
    }

    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.a != null) {
            this.a.registerDataSetObserver(dataSetObserver);
        }
    }

    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.a != null) {
            this.a.unregisterDataSetObserver(dataSetObserver);
        }
    }

    public final boolean areAllItemsEnabled() {
        ListAdapter listAdapter = this.b;
        return listAdapter != null ? listAdapter.areAllItemsEnabled() : true;
    }

    public final boolean isEnabled(int i) {
        ListAdapter listAdapter = this.b;
        return listAdapter != null ? listAdapter.isEnabled(i) : true;
    }

    public final boolean isEmpty() {
        return getCount() == 0;
    }
}
