package com.skypecam.camera2.a;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.google.android.gms.vision.barcode.a;
import com.skypecam.camera2.m;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\u0012R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/skypecam/camera2/modules/QRCodeDetector;", "", "context", "Landroid/content/Context;", "listener", "Lcom/skypecam/camera2/QRCodeListener;", "(Landroid/content/Context;Lcom/skypecam/camera2/QRCodeListener;)V", "barcodeDetector", "Lcom/google/android/gms/vision/barcode/BarcodeDetector;", "detectionTimestep", "", "timeOfLastDetection", "", "detect", "", "bitmap", "Landroid/graphics/Bitmap;", "isReady", "", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class g {
    private a a;
    private int b = Constants.ONE_SECOND;
    private final m c;

    public g(@Nullable Context context, @Nullable m listener) {
        this.c = listener;
        this.a = new a.a(context).a().b();
    }
}
