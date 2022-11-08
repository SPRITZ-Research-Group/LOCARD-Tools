package androidx.media;

import android.media.AudioAttributes;
import androidx.versionedparcelable.a;

public final class AudioAttributesImplApi21Parcelizer {
    public static b read(a aVar) {
        b bVar = new b();
        bVar.a = (AudioAttributes) aVar.b(bVar.a, 1);
        bVar.b = aVar.b(bVar.b, 2);
        return bVar;
    }

    public static void write(b bVar, a aVar) {
        aVar.a(bVar.a, 1);
        aVar.a(bVar.b, 2);
    }
}
