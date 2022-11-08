package androidx.preference.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import androidx.preference.l;
import com.google.android.gms.common.api.Api.BaseClientBuilder;

public class PreferenceImageView extends ImageView {
    private int a;
    private int b;

    public PreferenceImageView(Context context) {
        this(context, null);
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = BaseClientBuilder.API_PRIORITY_OTHER;
        this.b = BaseClientBuilder.API_PRIORITY_OTHER;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.PreferenceImageView, i, 0);
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(l.PreferenceImageView_maxWidth, BaseClientBuilder.API_PRIORITY_OTHER));
        setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(l.PreferenceImageView_maxHeight, BaseClientBuilder.API_PRIORITY_OTHER));
        obtainStyledAttributes.recycle();
    }

    public void setMaxWidth(int i) {
        this.a = i;
        super.setMaxWidth(i);
    }

    public int getMaxWidth() {
        return this.a;
    }

    public void setMaxHeight(int i) {
        this.b = i;
        super.setMaxHeight(i);
    }

    public int getMaxHeight() {
        return this.b;
    }

    protected void onMeasure(int i, int i2) {
        int size;
        int maxWidth;
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            size = MeasureSpec.getSize(i);
            maxWidth = getMaxWidth();
            if (maxWidth != BaseClientBuilder.API_PRIORITY_OTHER && (maxWidth < size || mode == 0)) {
                i = MeasureSpec.makeMeasureSpec(maxWidth, Integer.MIN_VALUE);
            }
        }
        mode = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            size = MeasureSpec.getSize(i2);
            maxWidth = getMaxHeight();
            if (maxWidth != BaseClientBuilder.API_PRIORITY_OTHER && (maxWidth < size || mode == 0)) {
                i2 = MeasureSpec.makeMeasureSpec(maxWidth, Integer.MIN_VALUE);
            }
        }
        super.onMeasure(i, i2);
    }
}
