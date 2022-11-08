package defpackage;

import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: jwz */
public final class jwz implements Serializable {
    private static final long serialVersionUID = 2016729075877751252L;
    public final String a;
    public final String b;
    public final ArrayList<jwv> c;
    public final jwv d;
    public final ArrayList<jwv> e;
    public final boolean f;
    public final String g;
    public final int h;

    public jwz(String str, String str2, ArrayList<jwv> arrayList, jwv jwv, ArrayList<jwv> arrayList2, boolean z, String str3, int i) {
        this.a = str;
        this.b = str2;
        this.c = arrayList;
        this.d = jwv;
        this.e = arrayList2;
        this.f = z;
        this.g = str3;
        this.h = i;
    }

    public final String a() {
        if (this.c != null) {
            int i = 1;
            if (!this.c.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(((jwv) this.c.get(0)).b);
                while (i < this.c.size()) {
                    stringBuilder.append(", ");
                    stringBuilder.append(((jwv) this.c.get(i)).b);
                    i++;
                }
                return stringBuilder.toString();
            }
        }
        return "";
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("MusicMetadata{");
        stringBuilder.append("musicId='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", trackTitle='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", trackArtistList=");
        stringBuilder.append(this.c);
        stringBuilder.append(", album=");
        stringBuilder.append(this.d);
        stringBuilder.append(", albumArtistList=");
        stringBuilder.append(this.e);
        stringBuilder.append(", isLocalMusic=");
        stringBuilder.append(this.f);
        stringBuilder.append(", displayType='");
        stringBuilder.append(this.g);
        stringBuilder.append('\'');
        stringBuilder.append(", version=");
        stringBuilder.append(this.h);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
