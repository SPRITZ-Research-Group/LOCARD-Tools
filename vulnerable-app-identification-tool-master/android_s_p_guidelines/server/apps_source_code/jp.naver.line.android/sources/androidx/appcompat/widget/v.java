package androidx.appcompat.widget;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import com.google.android.gms.common.api.Api.BaseClientBuilder;

final class v implements OnClickListener, OnLongClickListener, OnItemClickListener, OnDismissListener {
    final /* synthetic */ ActivityChooserView a;

    v(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (((u) adapterView.getAdapter()).getItemViewType(i)) {
            case 0:
                this.a.b();
                if (!this.a.g) {
                    if (!this.a.a.f()) {
                        i++;
                    }
                    Intent b = this.a.a.e().b(i);
                    if (b != null) {
                        b.addFlags(524288);
                        this.a.getContext().startActivity(b);
                    }
                    return;
                } else if (i > 0) {
                    this.a.a.e().c(i);
                    return;
                } else {
                    return;
                }
            case 1:
                this.a.a(BaseClientBuilder.API_PRIORITY_OTHER);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final void onClick(View view) {
        if (view == this.a.c) {
            this.a.b();
            Intent b = this.a.a.e().b(this.a.a.e().a(this.a.a.b()));
            if (b != null) {
                b.addFlags(524288);
                this.a.getContext().startActivity(b);
            }
        } else if (view == this.a.b) {
            this.a.g = false;
            this.a.a(this.a.h);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final boolean onLongClick(View view) {
        if (view == this.a.c) {
            if (this.a.a.getCount() > 0) {
                this.a.g = true;
                this.a.a(this.a.h);
            }
            return true;
        }
        throw new IllegalArgumentException();
    }

    public final void onDismiss() {
        if (this.a.f != null) {
            this.a.f.onDismiss();
        }
        if (this.a.d != null) {
            this.a.d.a(false);
        }
    }
}
