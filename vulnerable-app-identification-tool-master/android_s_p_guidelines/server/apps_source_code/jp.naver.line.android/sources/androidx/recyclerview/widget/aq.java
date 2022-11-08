package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import java.util.List;

final class aq {
    boolean a = true;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h = 0;
    boolean i = false;
    int j;
    List<cb> k = null;
    boolean l;

    aq() {
    }

    final boolean a(by byVar) {
        return this.d >= 0 && this.d < byVar.b();
    }

    final View a(bs bsVar) {
        if (this.k != null) {
            int size = this.k.size();
            int i = 0;
            while (i < size) {
                View view = ((cb) this.k.get(i)).itemView;
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (layoutParams.c.isRemoved() || this.d != layoutParams.c.getLayoutPosition()) {
                    i++;
                } else {
                    a(view);
                    return view;
                }
            }
            return null;
        }
        View d = bsVar.d(this.d);
        this.d += this.e;
        return d;
    }

    public final void a(View view) {
        view = b(view);
        if (view == null) {
            this.d = -1;
        } else {
            this.d = ((LayoutParams) view.getLayoutParams()).c.getLayoutPosition();
        }
    }

    private View b(View view) {
        int size = this.k.size();
        View view2 = null;
        int i = BaseClientBuilder.API_PRIORITY_OTHER;
        for (int i2 = 0; i2 < size; i2++) {
            View view3 = ((cb) this.k.get(i2)).itemView;
            LayoutParams layoutParams = (LayoutParams) view3.getLayoutParams();
            if (!(view3 == view || layoutParams.c.isRemoved())) {
                int layoutPosition = (layoutParams.c.getLayoutPosition() - this.d) * this.e;
                if (layoutPosition >= 0 && layoutPosition < i) {
                    if (layoutPosition == 0) {
                        return view3;
                    }
                    view2 = view3;
                    i = layoutPosition;
                }
            }
        }
        return view2;
    }
}
