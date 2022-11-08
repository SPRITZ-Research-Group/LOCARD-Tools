package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

public abstract class i extends ConstantState {
    int a;
    ConstantState b;
    ColorStateList c = null;
    Mode d = h.a;

    public abstract Drawable newDrawable(Resources resources);

    i(i iVar) {
        if (iVar != null) {
            this.a = iVar.a;
            this.b = iVar.b;
            this.c = iVar.c;
            this.d = iVar.d;
        }
    }

    public Drawable newDrawable() {
        return newDrawable(null);
    }

    public int getChangingConfigurations() {
        return this.a | (this.b != null ? this.b.getChangingConfigurations() : 0);
    }
}
