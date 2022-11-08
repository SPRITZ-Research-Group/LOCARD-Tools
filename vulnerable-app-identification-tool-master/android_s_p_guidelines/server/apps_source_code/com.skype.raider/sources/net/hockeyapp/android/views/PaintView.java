package net.hockeyapp.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.g;

@SuppressLint({"ViewConstructor"})
public class PaintView extends ImageView {
    private Path a = new Path();
    private Stack<Path> b = new Stack();
    private Paint c = new Paint();
    private float d;
    private float e;

    @SuppressLint({"StaticFieldLeak"})
    public PaintView(Context context, Uri imageUri, int displayWidth, int displayHeight) {
        super(context);
        this.c.setAntiAlias(true);
        this.c.setDither(true);
        this.c.setColor(-65536);
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeJoin(Join.ROUND);
        this.c.setStrokeCap(Cap.ROUND);
        this.c.setStrokeWidth(12.0f);
        new AsyncTask<Object, Void, Bitmap>(this) {
            final /* synthetic */ PaintView a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return AnonymousClass1.a(objArr);
            }

            protected final /* synthetic */ void onPostExecute(Object obj) {
                Bitmap bitmap = (Bitmap) obj;
                if (bitmap != null) {
                    this.a.setImageBitmap(bitmap);
                }
            }

            protected final void onPreExecute() {
                this.a.setAdjustViewBounds(true);
            }

            private static Bitmap a(Object... args) {
                try {
                    return g.a(args[0], args[1], args[2].intValue(), args[3].intValue());
                } catch (IOException e) {
                    e.f();
                    return null;
                }
            }
        }.execute(new Object[]{context, imageUri, Integer.valueOf(displayWidth), Integer.valueOf(displayHeight)});
    }

    public final void a() {
        this.b.clear();
        invalidate();
    }

    public final void b() {
        if (!this.b.empty()) {
            this.b.pop();
            invalidate();
        }
    }

    public final boolean c() {
        return this.b.empty();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            canvas.drawPath((Path) it.next(), this.c);
        }
        canvas.drawPath(this.a, this.c);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case 0:
                this.a.reset();
                this.a.moveTo(x, y);
                this.d = x;
                this.e = y;
                invalidate();
                break;
            case 1:
                this.a.lineTo(this.d, this.e);
                this.b.push(this.a);
                this.a = new Path();
                invalidate();
                break;
            case 2:
                float abs = Math.abs(x - this.d);
                float abs2 = Math.abs(y - this.e);
                if (abs >= 4.0f || abs2 >= 4.0f) {
                    this.a.quadTo(this.d, this.e, (this.d + x) / 2.0f, (this.e + y) / 2.0f);
                    this.d = x;
                    this.e = y;
                }
                invalidate();
                break;
        }
        return true;
    }
}
