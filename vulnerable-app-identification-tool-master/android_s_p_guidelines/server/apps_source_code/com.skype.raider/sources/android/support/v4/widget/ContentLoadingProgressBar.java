package android.support.v4.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar extends ProgressBar {
    long a;
    boolean b;
    boolean c;
    boolean d;
    private final Runnable e;
    private final Runnable f;

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.a = -1;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = new Runnable(this) {
            final /* synthetic */ ContentLoadingProgressBar a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b = false;
                this.a.a = -1;
                this.a.setVisibility(8);
            }
        };
        this.f = new Runnable(this) {
            final /* synthetic */ ContentLoadingProgressBar a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.c = false;
                if (!this.a.d) {
                    this.a.a = System.currentTimeMillis();
                    this.a.setVisibility(0);
                }
            }
        };
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    private void a() {
        removeCallbacks(this.e);
        removeCallbacks(this.f);
    }
}
