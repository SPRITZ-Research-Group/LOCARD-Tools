package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.f;
import com.google.android.gms.internal.vision.zzc;
import com.google.android.gms.internal.vision.zzk;
import com.google.android.gms.vision.b;
import com.skype.Defines;

public final class a extends com.google.android.gms.vision.a<Barcode> {
    private final f a;

    public static class a {
        private Context a;
        private zzc b = new zzc();

        public a(Context context) {
            this.a = context;
        }

        public final a a() {
            this.b.a = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
            return this;
        }

        public final a b() {
            return new a(new f(this.a, this.b), (byte) 0);
        }
    }

    private a() {
        throw new IllegalStateException("Default constructor called");
    }

    private a(f fVar) {
        this.a = fVar;
    }

    /* synthetic */ a(f fVar, byte b) {
        this(fVar);
    }

    public final SparseArray<Barcode> a(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        Barcode[] a;
        zzk a2 = zzk.a(bVar);
        if (bVar.c() != null) {
            a = this.a.a(bVar.c(), a2);
            if (a == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        }
        a = this.a.a(bVar.b(), a2);
        SparseArray<Barcode> sparseArray = new SparseArray(a.length);
        for (Barcode barcode : a) {
            sparseArray.append(barcode.b.hashCode(), barcode);
        }
        return sparseArray;
    }

    public final boolean a() {
        return this.a.a();
    }
}
