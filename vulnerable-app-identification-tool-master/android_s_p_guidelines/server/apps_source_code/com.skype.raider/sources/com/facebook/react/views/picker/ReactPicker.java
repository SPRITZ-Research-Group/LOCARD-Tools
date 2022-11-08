package com.facebook.react.views.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import javax.annotation.Nullable;

public class ReactPicker extends Spinner {
    private int a = 0;
    @Nullable
    private Integer b;
    private boolean c;
    @Nullable
    private a d;
    @Nullable
    private Integer e;
    private final Runnable f = new Runnable(this) {
        final /* synthetic */ ReactPicker a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.measure(MeasureSpec.makeMeasureSpec(this.a.getWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(this.a.getHeight(), ErrorDialogData.SUPPRESSED));
            this.a.layout(this.a.getLeft(), this.a.getTop(), this.a.getRight(), this.a.getBottom());
        }
    };

    public interface a {
        void a(int i);
    }

    public ReactPicker(Context context) {
        super(context);
    }

    public ReactPicker(Context context, int mode) {
        super(context, mode);
        this.a = mode;
    }

    public ReactPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.f);
    }

    public void setOnSelectListener(@Nullable a onSelectListener) {
        if (getOnItemSelectedListener() == null) {
            this.c = true;
            setOnItemSelectedListener(new OnItemSelectedListener(this) {
                final /* synthetic */ ReactPicker a;

                {
                    this.a = this$0;
                }

                public final void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    if (!(this.a.c || this.a.d == null)) {
                        this.a.d.a(position);
                    }
                    this.a.c = false;
                }

                public final void onNothingSelected(AdapterView<?> adapterView) {
                    if (!(this.a.c || this.a.d == null)) {
                        this.a.d.a(-1);
                    }
                    this.a.c = false;
                }
            });
        }
        this.d = onSelectListener;
    }

    public void setStagedSelection(int selection) {
        this.e = Integer.valueOf(selection);
    }

    public final void a() {
        if (this.e != null) {
            int intValue = this.e.intValue();
            if (intValue != getSelectedItemPosition()) {
                this.c = true;
                setSelection(intValue);
            }
            this.e = null;
        }
    }

    @Nullable
    public final Integer b() {
        return this.b;
    }

    public void setPrimaryColor(@Nullable Integer primaryColor) {
        this.b = primaryColor;
    }
}
