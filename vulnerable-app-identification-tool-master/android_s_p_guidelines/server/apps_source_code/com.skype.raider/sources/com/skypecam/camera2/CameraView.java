package com.skypecam.camera2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import kotlin.Metadata;
import kotlin.e;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.b.c;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 92\u00020\u0001:\u00019B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\u0012\u0010.\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u0018\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0007H\u0002J\b\u00104\u001a\u00020+H\u0002J\u0006\u00105\u001a\u00020+J\u001c\u00106\u001a\n 7*\u0004\u0018\u00010000*\u00020\u001b2\u0006\u00108\u001a\u00020\u0007H\u0002R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0011\u0010\"\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u001a\u0010$\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006:"}, d2 = {"Lcom/skypecam/camera2/CameraView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "debugInfoText", "Landroid/widget/TextView;", "getDebugInfoText", "()Landroid/widget/TextView;", "setDebugInfoText", "(Landroid/widget/TextView;)V", "displayDebugInfo", "", "getDisplayDebugInfo", "()Z", "focusTargetPaint", "Landroid/graphics/Paint;", "getFocusTargetPaint", "()Landroid/graphics/Paint;", "setFocusTargetPaint", "(Landroid/graphics/Paint;)V", "scaledFocusTargetSize", "", "getScaledFocusTargetSize", "()F", "setScaledFocusTargetSize", "(F)V", "targetLineSize", "getTargetLineSize", "targetRoundCornerRadius", "getTargetRoundCornerRadius", "textureView", "Landroid/view/TextureView;", "getTextureView", "()Landroid/view/TextureView;", "setTextureView", "(Landroid/view/TextureView;)V", "dispatchDraw", "", "canvas", "Landroid/graphics/Canvas;", "drawFocusTarget", "getRotatedDimension", "", "size", "Landroid/util/Size;", "rotation", "refreshDebugInfo", "refreshLayout", "format", "kotlin.jvm.PlatformType", "digits", "Companion", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class CameraView extends RelativeLayout {
    public static final a a = new a();
    @NotNull
    private TextureView b;
    @NotNull
    private TextView c;
    @NotNull
    private Paint d;
    private float e;
    private final float f;
    private final float g;
    private final boolean h;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/skypecam/camera2/CameraView$Companion;", "", "()V", "refreshPreviewTransform", "", "view", "Landroid/view/TextureView;", "inputSize", "Landroid/util/Size;", "displayRotation", "", "scaleTypeFullScreen", "", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a {

        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 10})
        static final class a implements Runnable {
            final /* synthetic */ TextureView a;
            final /* synthetic */ Matrix b;

            a(TextureView textureView, Matrix matrix) {
                this.a = textureView;
                this.b = matrix;
            }

            public final void run() {
                this.a.setTransform(this.b);
            }
        }

        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        public static void a(@Nullable TextureView view, @NotNull Size inputSize, int displayRotation) {
            c.b(inputSize, "inputSize");
            if (view != null) {
                float scaleX;
                float scaleY;
                TextureView $receiver = view;
                Matrix matrix = new Matrix();
                RectF viewRect = new RectF(0.0f, 0.0f, (float) $receiver.getWidth(), (float) $receiver.getHeight());
                RectF inputRect = new RectF(0.0f, 0.0f, (float) inputSize.getHeight(), (float) inputSize.getWidth());
                float viewCenterX = viewRect.centerX();
                float viewCenterY = viewRect.centerY();
                switch (displayRotation) {
                    case 0:
                    case 2:
                        scaleX = ((float) $receiver.getHeight()) / inputRect.height();
                        scaleY = ((float) $receiver.getWidth()) / inputRect.width();
                        break;
                    default:
                        scaleX = ((float) $receiver.getHeight()) / inputRect.width();
                        scaleY = ((float) $receiver.getWidth()) / inputRect.height();
                        break;
                }
                float scale = Math.max(scaleX, scaleY);
                inputRect.offset(viewCenterX - inputRect.centerX(), viewCenterY - inputRect.centerY());
                Matrix $receiver2 = matrix;
                matrix.setRectToRect(viewRect, inputRect, ScaleToFit.FILL);
                $receiver2.postScale(scale, scale, viewCenterX, viewCenterY);
                com.skypecam.camera2.a.a.b bVar = com.skypecam.camera2.a.a.a;
                if (!com.skypecam.camera2.a.a.w) {
                    switch (displayRotation) {
                        case 1:
                        case 3:
                            matrix.postRotate((float) ((displayRotation - 2) * 90), viewCenterX, viewCenterY);
                            break;
                        case 2:
                            matrix.postRotate(180.0f, viewCenterX, viewCenterY);
                            break;
                    }
                }
                $receiver.post(new a($receiver, matrix));
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "com/skypecam/camera2/CameraView$refreshLayout$1$1"}, k = 3, mv = {1, 1, 10})
    static final class b implements Runnable {
        final /* synthetic */ TextureView a;
        final /* synthetic */ CameraView b;

        b(TextureView textureView, CameraView cameraView) {
            this.a = textureView;
            this.b = cameraView;
        }

        public final void run() {
            this.a.measure(MeasureSpec.makeMeasureSpec(this.b.getWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(this.b.getHeight(), ErrorDialogData.SUPPRESSED));
            this.a.layout(0, 0, this.b.getWidth(), this.b.getHeight());
        }
    }

    @JvmOverloads
    public CameraView(@NotNull Context context) {
        this(context, null, 0, 14);
    }

    @JvmOverloads
    public CameraView(@NotNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 12);
    }

    @JvmOverloads
    public CameraView(@NotNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        this(context, attrs, defStyle, 8);
    }

    @JvmOverloads
    private /* synthetic */ CameraView(Context context, AttributeSet attributeSet, int i, int i2) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i, (byte) 0);
    }

    @JvmOverloads
    private CameraView(@NotNull Context context, @Nullable AttributeSet attrs, int defStyle, byte defStyleRes) {
        c.b(context, "context");
        super(context, attrs, defStyle, 0);
        LayoutInflater.from(context).inflate(com.skypecam.camera2.n.b.camera_view, this, true);
        View findViewById = findViewById(com.skypecam.camera2.n.a.c2_texture_view);
        if (findViewById == null) {
            throw new e("null cannot be cast to non-null type android.view.TextureView");
        }
        this.b = (TextureView) findViewById;
        findViewById = findViewById(com.skypecam.camera2.n.a.c2_debug_info_view);
        if (findViewById == null) {
            throw new e("null cannot be cast to non-null type android.widget.TextView");
        }
        this.c = (TextView) findViewById;
        Object resources = context.getResources();
        c.a(resources, "context.resources");
        float density = resources.getDisplayMetrics().density;
        Paint $receiver = new Paint();
        $receiver.setStrokeWidth(1.5f * density);
        $receiver.setStyle(Style.STROKE);
        com.skypecam.camera2.i.a aVar = i.a;
        $receiver.setColor(i.d);
        this.d = $receiver;
        this.e = 25.0f * density;
        this.g = 6.0f * density;
        this.f = 8.0f * density;
        this.b.setEnabled(false);
        if (!this.h) {
            removeView(this.c);
        }
    }

    @NotNull
    public final TextureView a() {
        return this.b;
    }

    public final void setTextureView(@NotNull TextureView <set-?>) {
        c.b(<set-?>, "<set-?>");
        this.b = <set-?>;
    }

    public final void setDebugInfoText(@NotNull TextView <set-?>) {
        c.b(<set-?>, "<set-?>");
        this.c = <set-?>;
    }

    public final void setFocusTargetPaint(@NotNull Paint <set-?>) {
        c.b(<set-?>, "<set-?>");
        this.d = <set-?>;
    }

    public final void setScaledFocusTargetSize(float <set-?>) {
        this.e = <set-?>;
    }

    public final void b() {
        post(new b(this.b, this));
    }

    protected final void dispatchDraw(@Nullable Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.h) {
            TextView textView = this.c;
            StringBuilder stringBuilder = new StringBuilder("Camera2 ");
            Object b = e.b();
            if (b == null) {
                b = "";
            }
            stringBuilder.append(b);
            TextureView textureView = this.b;
            if (textureView != null) {
                stringBuilder.append("\nView Size: " + textureView.getWidth() + 'x' + textureView.getHeight() + ' ' + '(' + a(((float) textureView.getWidth()) / ((float) textureView.getHeight())) + ')');
            }
            com.skypecam.camera2.a.a a = e.a();
            if (a != null) {
                stringBuilder.append("\nPreview Size: " + a(a.f(), a.d()));
                stringBuilder.append("\nCapture Size: " + a(a.g(), a.d()));
                stringBuilder.append("\nVideo Size: " + a(a.i(), a.d()));
                stringBuilder.append("\nRotation: " + a.d());
            }
            toString();
            textView.setText(stringBuilder);
        }
        i c = e.c();
        if (c != null) {
            if (canvas != null) {
                canvas.save();
                TextureView textureView2 = this.b;
                if (textureView2 != null) {
                    canvas.clipRect(new Rect(textureView2.getLeft(), textureView2.getTop(), textureView2.getRight(), textureView2.getBottom()));
                }
                Canvas canvas2 = canvas;
                canvas2.drawRoundRect(c.b().x - this.e, c.b().y - this.e, this.e + c.b().x, this.e + c.b().y, this.f, this.f, this.d);
                switch (d.a[c.a().ordinal()]) {
                    case 2:
                        canvas2 = canvas;
                        canvas2.drawLine(c.b().x, c.b().y - this.e, c.b().x, this.g + (c.b().y - this.e), this.d);
                        canvas2 = canvas;
                        canvas2.drawLine(c.b().x, this.e + c.b().y, c.b().x, (c.b().y + this.e) - this.g, this.d);
                        canvas2 = canvas;
                        canvas2.drawLine(c.b().x - this.e, c.b().y, this.g + (c.b().x - this.e), c.b().y, this.d);
                        canvas2 = canvas;
                        canvas2.drawLine(this.e + c.b().x, c.b().y, (c.b().x + this.e) - this.g, c.b().y, this.d);
                        break;
                }
                canvas.restore();
            }
            if (c.a() == com.skypecam.camera2.i.a.a.a) {
                invalidate();
            }
        }
    }

    private static String a(float $receiver) {
        return String.format("%.2f", new Object[]{Float.valueOf($receiver)});
    }

    private static String a(Size size, int rotation) {
        Size rotatedSize;
        switch (rotation) {
            case 0:
            case 180:
                rotatedSize = size;
                break;
            default:
                rotatedSize = new Size(size.getHeight(), size.getWidth());
                break;
        }
        return rotatedSize.getWidth() + 'x' + rotatedSize.getHeight() + ' ' + '(' + a(((float) rotatedSize.getWidth()) / ((float) rotatedSize.getHeight())) + ')';
    }
}
