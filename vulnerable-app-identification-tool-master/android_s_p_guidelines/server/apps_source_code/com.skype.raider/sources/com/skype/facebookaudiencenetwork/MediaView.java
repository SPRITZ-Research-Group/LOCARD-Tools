package com.skype.facebookaudiencenetwork;

import android.content.Context;
import android.view.View.MeasureSpec;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class MediaView extends com.facebook.ads.MediaView {
    private final Runnable a = new Runnable(this) {
        final /* synthetic */ MediaView a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.measure(MeasureSpec.makeMeasureSpec(this.a.getWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(this.a.getHeight(), ErrorDialogData.SUPPRESSED));
            this.a.layout(this.a.getLeft(), this.a.getTop(), this.a.getRight(), this.a.getBottom());
        }
    };

    public MediaView(Context context) {
        super(context);
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.a);
    }
}
