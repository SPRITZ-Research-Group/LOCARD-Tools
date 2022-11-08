package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

final class aa extends s {
    @Nullable
    private String d;

    aa() {
    }

    protected final void b(SpannableStringBuilder builder) {
        if (this.d != null) {
            builder.append(this.d);
        }
    }

    protected final void a(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        builder.setSpan(this, begin, end, 17);
    }

    @ReactProp(name = "text")
    public final void setText(@Nullable String text) {
        this.d = text;
        a(true);
    }
}
