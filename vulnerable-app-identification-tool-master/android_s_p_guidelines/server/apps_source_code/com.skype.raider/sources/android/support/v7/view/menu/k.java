package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.view.b.b;
import android.view.ActionProvider;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

@RequiresApi(16)
@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
final class k extends j {

    class a extends a implements VisibilityListener {
        b c;
        final /* synthetic */ k d;

        public a(k this$0, Context context, ActionProvider inner) {
            this.d = this$0;
            super(this$0, context, inner);
        }

        public final View a(MenuItem forItem) {
            return this.a.onCreateActionView(forItem);
        }

        public final boolean b() {
            return this.a.overridesItemVisibility();
        }

        public final boolean c() {
            return this.a.isVisible();
        }

        public final void a(b listener) {
            this.c = listener;
            this.a.setVisibilityListener(this);
        }

        public final void onActionProviderVisibilityChanged(boolean isVisible) {
            if (this.c != null) {
                this.c.a();
            }
        }
    }

    k(Context context, android.support.v4.internal.view.b object) {
        super(context, object);
    }

    final a a(ActionProvider provider) {
        return new a(this, this.a, provider);
    }
}
