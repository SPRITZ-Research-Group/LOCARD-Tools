package jp.naver.line.android.analytics.ga;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import jp.naver.line.android.common.e;

public class GACustomDimensions extends SparseArray implements Parcelable {
    public static final Creator<GACustomDimensions> CREATOR = new Creator<GACustomDimensions>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new GACustomDimensions[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new GACustomDimensions(parcel);
        }
    };

    public int describeContents() {
        return 0;
    }

    public GACustomDimensions(Parcel parcel) {
        SparseArray readSparseArray = parcel.readSparseArray(e.c().getClassLoader());
        for (int i = 0; i < readSparseArray.size(); i++) {
            put(readSparseArray.keyAt(i), readSparseArray.valueAt(i));
        }
    }

    public final GACustomDimensions a(int i, String str) {
        put(i, str);
        return this;
    }

    public final GACustomDimensions a() {
        return (GACustomDimensions) super.clone();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSparseArray(this);
    }

    public java.lang.String toString() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.analytics.ga.GACustomDimensions.toString():java.lang.String. bs: []
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
        r4 = this;
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = 0;
    L_0x0006:
        r2 = r4.size();
        if (r1 >= r2) goto L_0x0039;
    L_0x000c:
        r2 = r4.keyAt(r1);
        r3 = 2;
        if (r2 == r3) goto L_0x0036;
    L_0x0013:
        r2 = r4.keyAt(r1);
        r3 = 8;
        if (r2 == r3) goto L_0x0036;
    L_0x001b:
        r2 = r4.keyAt(r1);	 Catch:{ JSONException -> 0x0033 }
        r2 = java.lang.Integer.toString(r2);	 Catch:{ JSONException -> 0x0033 }
        r3 = r4.keyAt(r1);	 Catch:{ JSONException -> 0x0033 }
        r3 = r4.get(r3);	 Catch:{ JSONException -> 0x0033 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0033 }
        r0.put(r2, r3);	 Catch:{ JSONException -> 0x0033 }
        goto L_0x0036;
    L_0x0033:
        r0 = "{}";
        return r0;
    L_0x0036:
        r1 = r1 + 1;
        goto L_0x0006;
    L_0x0039:
        r0 = r0.toString();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.analytics.ga.GACustomDimensions.toString():java.lang.String");
    }
}
