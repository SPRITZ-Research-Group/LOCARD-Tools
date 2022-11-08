package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.a.h;
import com.airbnb.lottie.c.b.g;
import com.airbnb.lottie.c.b.g.a;
import com.airbnb.lottie.e;
import java.io.IOException;

final class u {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static g a(JsonReader reader, e composition) throws IOException {
        a maskMode = null;
        h maskPath = null;
        d opacity = null;
        reader.beginObject();
        while (reader.hasNext()) {
            int i;
            Object i2;
            String mode = reader.nextName();
            switch (mode.hashCode()) {
                case 111:
                    if (mode.equals("o")) {
                        i2 = 2;
                        break;
                    }
                case 3588:
                    if (mode.equals("pt")) {
                        i2 = 1;
                        break;
                    }
                case 3357091:
                    if (mode.equals("mode")) {
                        i2 = null;
                        break;
                    }
                default:
                    i2 = -1;
                    break;
            }
            switch (i2) {
                case null:
                    String nextString = reader.nextString();
                    switch (nextString.hashCode()) {
                        case 97:
                            if (nextString.equals("a")) {
                                i2 = null;
                                break;
                            }
                        case 105:
                            if (nextString.equals("i")) {
                                i2 = 2;
                                break;
                            }
                        case 115:
                            if (nextString.equals("s")) {
                                i2 = 1;
                                break;
                            }
                        default:
                            i2 = -1;
                            break;
                    }
                    switch (i2) {
                        case null:
                            maskMode = a.MaskModeAdd;
                            break;
                        case 1:
                            maskMode = a.MaskModeSubtract;
                            break;
                        case 2:
                            maskMode = a.MaskModeIntersect;
                            break;
                        default:
                            new StringBuilder("Unknown mask mode ").append(mode).append(". Defaulting to Add.");
                            maskMode = a.MaskModeAdd;
                            break;
                    }
                case 1:
                    maskPath = d.e(reader, composition);
                    break;
                case 2:
                    opacity = d.b(reader, composition);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new g(maskMode, maskPath, opacity);
    }
}
