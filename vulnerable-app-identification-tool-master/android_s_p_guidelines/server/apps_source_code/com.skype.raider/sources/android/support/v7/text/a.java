package android.support.v7.text;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class a implements TransformationMethod {
    private Locale a;

    public a(Context context) {
        this.a = context.getResources().getConfiguration().locale;
    }

    public final CharSequence getTransformation(CharSequence source, View view) {
        return source != null ? source.toString().toUpperCase(this.a) : null;
    }

    public final void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {
    }
}
