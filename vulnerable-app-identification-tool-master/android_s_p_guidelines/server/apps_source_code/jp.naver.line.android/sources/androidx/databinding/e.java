package androidx.databinding;

import android.view.View;
import java.util.Collections;
import java.util.List;

public abstract class e {
    public abstract String convertBrIdToString(int i);

    public abstract ViewDataBinding getDataBinder(g gVar, View view, int i);

    public abstract ViewDataBinding getDataBinder(g gVar, View[] viewArr, int i);

    public abstract int getLayoutId(String str);

    public List<e> collectDependencies() {
        return Collections.emptyList();
    }
}
