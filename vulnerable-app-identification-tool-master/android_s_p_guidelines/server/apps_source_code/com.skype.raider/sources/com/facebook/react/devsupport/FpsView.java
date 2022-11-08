package com.facebook.react.devsupport;

import android.annotation.TargetApi;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.debug.b;
import java.util.Locale;

@TargetApi(16)
public class FpsView extends FrameLayout {
    private final TextView a;
    private final b b;
    private final a c;

    private class a implements Runnable {
        final /* synthetic */ FpsView a;
        private boolean b;
        private int c;
        private int d;

        public final void run() {
            if (!this.b) {
                this.c += this.a.b.i() - this.a.b.h();
                this.d += this.a.b.j();
                FpsView.a(this.a, this.a.b.f(), this.a.b.g(), this.c, this.d);
                this.a.b.k();
                this.a.postDelayed(this, 500);
            }
        }

        public final void a() {
            this.b = false;
            this.a.post(this);
        }

        public final void b() {
            this.b = true;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b.k();
        this.b.c();
        this.c.a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.e();
        this.c.b();
    }

    static /* synthetic */ void a(FpsView x0, double x1, double x2, int x3, int x4) {
        String format = String.format(Locale.US, "UI: %.1f fps\n%d dropped so far\n%d stutters (4+) so far\nJS: %.1f fps", new Object[]{Double.valueOf(x1), Integer.valueOf(x3), Integer.valueOf(x4), Double.valueOf(x2)});
        x0.a.setText(format);
        FLog.d("React", format);
    }
}
