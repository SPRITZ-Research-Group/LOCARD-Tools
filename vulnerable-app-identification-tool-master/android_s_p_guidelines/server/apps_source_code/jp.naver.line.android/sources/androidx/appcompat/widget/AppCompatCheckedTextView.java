package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;
import androidx.core.widget.l;
import defpackage.w;

public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] a = new int[]{16843016};
    private final ak b;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(br.a(context), attributeSet, i);
        this.b = new ak(this);
        this.b.a(attributeSet, i);
        this.b.a();
        bu a = bu.a(getContext(), attributeSet, a, i, 0);
        setCheckMarkDrawable(a.a(0));
        a.a();
    }

    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(w.b(getContext(), i));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.b != null) {
            this.b.a(context, i);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.a();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return ae.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(l.a((TextView) this, callback));
    }
}
