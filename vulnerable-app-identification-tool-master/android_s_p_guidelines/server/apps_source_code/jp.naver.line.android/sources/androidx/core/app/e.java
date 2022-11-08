package androidx.core.app;

import android.app.SharedElementCallback;
import android.app.SharedElementCallback.OnSharedElementsReadyListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;
import java.util.Map;

final class e extends SharedElementCallback {
    private final ar a;

    public final void onMapSharedElements(List<String> list, Map<String, View> map) {
    }

    public final void onRejectSharedElements(List<View> list) {
    }

    public final void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
    }

    public final void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
    }

    e(ar arVar) {
        this.a = arVar;
    }

    public final Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
        return this.a.a(view, matrix, rectF);
    }

    public final void onSharedElementsArrived(List<String> list, List<View> list2, final OnSharedElementsReadyListener onSharedElementsReadyListener) {
        new as(this) {
            final /* synthetic */ e b;

            public final void a() {
                onSharedElementsReadyListener.onSharedElementsReady();
            }
        }.a();
    }

    public final View onCreateSnapshotView(Context context, Parcelable parcelable) {
        View view = null;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Bitmap bitmap = (Bitmap) bundle.getParcelable("sharedElement:snapshot:bitmap");
            if (bitmap == null) {
                return null;
            }
            view = new ImageView(context);
            view.setImageBitmap(bitmap);
            view.setScaleType(ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
            if (view.getScaleType() == ScaleType.MATRIX) {
                float[] floatArray = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
                Matrix matrix = new Matrix();
                matrix.setValues(floatArray);
                view.setImageMatrix(matrix);
            }
        } else if (parcelable instanceof Bitmap) {
            Bitmap bitmap2 = (Bitmap) parcelable;
            view = new ImageView(context);
            view.setImageBitmap(bitmap2);
        }
        return view;
    }
}
