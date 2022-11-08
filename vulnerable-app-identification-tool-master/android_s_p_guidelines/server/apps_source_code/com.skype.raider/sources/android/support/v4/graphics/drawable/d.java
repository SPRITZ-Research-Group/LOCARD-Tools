package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class d extends c {

    private static class a extends a {
        a(@Nullable a orig) {
            super(orig);
        }

        public final Drawable newDrawable(@Nullable Resources res) {
            return new d(this, res);
        }
    }

    d(Drawable drawable) {
        super(drawable);
    }

    d(a state, Resources resources) {
        super(state, resources);
    }

    public void setAutoMirrored(boolean mirrored) {
        this.c.setAutoMirrored(mirrored);
    }

    public boolean isAutoMirrored() {
        return this.c.isAutoMirrored();
    }

    @NonNull
    a b() {
        return new a(this.b);
    }
}
