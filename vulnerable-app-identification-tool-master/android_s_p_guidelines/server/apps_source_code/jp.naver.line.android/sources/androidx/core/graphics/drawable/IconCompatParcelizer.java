package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Parcelable;
import androidx.versionedparcelable.a;
import java.nio.charset.Charset;
import org.apache.http.protocol.HTTP;

public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.a = aVar.b(iconCompat.a, 1);
        iconCompat.c = aVar.c(iconCompat.c);
        iconCompat.d = aVar.b(iconCompat.d, 3);
        iconCompat.e = aVar.b(iconCompat.e, 4);
        iconCompat.f = aVar.b(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) aVar.b(iconCompat.g, 6);
        iconCompat.j = aVar.c(iconCompat.j);
        iconCompat.i = Mode.valueOf(iconCompat.j);
        int i = iconCompat.a;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    if (iconCompat.d == null) {
                        iconCompat.b = iconCompat.c;
                        iconCompat.a = 3;
                        iconCompat.e = 0;
                        iconCompat.f = iconCompat.c.length;
                        break;
                    }
                    iconCompat.b = iconCompat.d;
                    break;
                case 2:
                case 4:
                    iconCompat.b = new String(iconCompat.c, Charset.forName(HTTP.UTF_16));
                    break;
                case 3:
                    iconCompat.b = iconCompat.c;
                    break;
            }
        } else if (iconCompat.d != null) {
            iconCompat.b = iconCompat.d;
        } else {
            throw new IllegalArgumentException("Invalid icon");
        }
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        iconCompat.j = iconCompat.i.name();
        int i = iconCompat.a;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    iconCompat.d = (Parcelable) iconCompat.b;
                    break;
                case 2:
                    iconCompat.c = ((String) iconCompat.b).getBytes(Charset.forName(HTTP.UTF_16));
                    break;
                case 3:
                    iconCompat.c = (byte[]) iconCompat.b;
                    break;
                case 4:
                    iconCompat.c = iconCompat.b.toString().getBytes(Charset.forName(HTTP.UTF_16));
                    break;
            }
        }
        iconCompat.d = (Parcelable) iconCompat.b;
        aVar.a(iconCompat.a, 1);
        aVar.b(iconCompat.c);
        aVar.a(iconCompat.d, 3);
        aVar.a(iconCompat.e, 4);
        aVar.a(iconCompat.f, 5);
        aVar.a(iconCompat.g, 6);
        aVar.b(iconCompat.j);
    }
}
