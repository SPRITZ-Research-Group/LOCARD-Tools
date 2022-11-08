package androidx.appcompat.app;

import android.content.Context;
import android.widget.ArrayAdapter;

final class f extends ArrayAdapter<CharSequence> {
    public final long getItemId(int i) {
        return (long) i;
    }

    public final boolean hasStableIds() {
        return true;
    }

    public f(Context context, int i, CharSequence[] charSequenceArr) {
        super(context, i, 16908308, charSequenceArr);
    }
}
