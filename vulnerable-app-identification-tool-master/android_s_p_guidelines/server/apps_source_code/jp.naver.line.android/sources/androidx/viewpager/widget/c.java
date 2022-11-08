package androidx.viewpager.widget;

import android.content.Context;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import java.util.Locale;

final class c extends SingleLineTransformationMethod {
    private Locale a;

    c(Context context) {
        this.a = context.getResources().getConfiguration().locale;
    }

    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        charSequence = super.getTransformation(charSequence, view);
        return charSequence != null ? charSequence.toString().toUpperCase(this.a) : null;
    }
}
