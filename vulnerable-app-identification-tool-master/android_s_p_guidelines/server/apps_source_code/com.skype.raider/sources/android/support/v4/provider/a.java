package android.support.v4.provider;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.k;
import android.util.Base64;
import java.util.List;

public final class a {
    private final String a;
    private final String b;
    private final String c;
    private final List<List<byte[]>> d;
    private final int e = 0;
    private final String f = new StringBuilder(this.a).append("-").append(this.b).append("-").append(this.c).toString();

    public a(@NonNull String providerAuthority, @NonNull String providerPackage, @NonNull String query, @NonNull List<List<byte[]>> certificates) {
        this.a = (String) k.a(providerAuthority);
        this.b = (String) k.a(providerPackage);
        this.c = (String) k.a(query);
        this.d = (List) k.a(certificates);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    @Nullable
    public final List<List<byte[]>> d() {
        return this.d;
    }

    @ArrayRes
    public final int e() {
        return this.e;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final String f() {
        return this.f;
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FontRequest {mProviderAuthority: " + this.a + ", mProviderPackage: " + this.b + ", mQuery: " + this.c + ", mCertificates:");
        for (int i = 0; i < this.d.size(); i++) {
            builder.append(" [");
            List<byte[]> set = (List) this.d.get(i);
            for (int j = 0; j < set.size(); j++) {
                builder.append(" \"");
                builder.append(Base64.encodeToString((byte[]) set.get(j), 0));
                builder.append("\"");
            }
            builder.append(" ]");
        }
        builder.append("}");
        builder.append("mCertificatesArray: " + this.e);
        return builder.toString();
    }
}
