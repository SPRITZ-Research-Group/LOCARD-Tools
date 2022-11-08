package com.applovin.impl.adview;

import android.content.Context;
import android.widget.VideoView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class AppLovinVideoView extends VideoView {
    private int a = 0;
    private int b = 0;
    private float c = BitmapDescriptorFactory.HUE_RED;

    public AppLovinVideoView(Context context) {
        super(context, null, 0);
    }

    protected void onMeasure(int i, int i2) {
        if (this.a <= 0 || this.b <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        i = getDefaultSize(this.a, i);
        i2 = getDefaultSize(this.b, i2);
        int i3 = (int) (((float) i) / this.c);
        if (i2 > i3) {
            i2 = i3;
        }
        i3 = (int) (((float) i2) * this.c);
        if (i > i3) {
            i = i3;
        }
        setMeasuredDimension(i, i2);
    }

    public void setVideoSize(int r3, int r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.adview.AppLovinVideoView.setVideoSize(int, int):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2 = this;
        r2.a = r3;
        r2.b = r4;
        r0 = r2.a;
        r0 = (float) r0;
        r1 = r2.b;
        r1 = (float) r1;
        r0 = r0 / r1;
        r2.c = r0;
        r0 = r2.getHolder();	 Catch:{ Exception -> 0x001a }
        r0.setFixedSize(r3, r4);	 Catch:{ Exception -> 0x001a }
        r2.requestLayout();	 Catch:{ Exception -> 0x001a }
        r2.invalidate();	 Catch:{ Exception -> 0x001a }
    L_0x001a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.AppLovinVideoView.setVideoSize(int, int):void");
    }
}
