package jp.naver.gallery.android.model;

import defpackage.acry;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003JU\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006\""}, d2 = {"Ljp/naver/gallery/android/model/GalleryPlaceSearchInfo;", "", "name", "", "address", "latitude", "", "isSetLatitude", "", "longitude", "isSetLongitude", "categoryId", "(Ljava/lang/String;Ljava/lang/String;DZDZLjava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getCategoryId", "()Z", "getLatitude", "()D", "getLongitude", "getName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "common-libs_release"}, k = 1, mv = {1, 1, 13})
public final class b {
    private final String a;
    private final String b;
    private final double c;
    private final boolean d;
    private final double e;
    private final boolean f;
    private final String g;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (acry.a(this.a, bVar.a) && acry.a(this.b, bVar.b) && Double.compare(this.c, bVar.c) == 0) {
                    if ((this.d == bVar.d ? 1 : null) != null && Double.compare(this.e, bVar.e) == 0) {
                        if ((this.f == bVar.f ? 1 : null) == null || !acry.a(this.g, bVar.g)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.c);
        hashCode = (hashCode + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        int i2 = this.d;
        if (i2 != 0) {
            i2 = 1;
        }
        hashCode = (hashCode + i2) * 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.e);
        hashCode = (hashCode + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
        i2 = this.f;
        if (i2 != 0) {
            i2 = 1;
        }
        hashCode = (hashCode + i2) * 31;
        str2 = this.g;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("GalleryPlaceSearchInfo(name=");
        stringBuilder.append(this.a);
        stringBuilder.append(", address=");
        stringBuilder.append(this.b);
        stringBuilder.append(", latitude=");
        stringBuilder.append(this.c);
        stringBuilder.append(", isSetLatitude=");
        stringBuilder.append(this.d);
        stringBuilder.append(", longitude=");
        stringBuilder.append(this.e);
        stringBuilder.append(", isSetLongitude=");
        stringBuilder.append(this.f);
        stringBuilder.append(", categoryId=");
        stringBuilder.append(this.g);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public b(String str, String str2, double d, boolean z, double d2, boolean z2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = d;
        this.d = z;
        this.e = d2;
        this.f = z2;
        this.g = str3;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }
}
