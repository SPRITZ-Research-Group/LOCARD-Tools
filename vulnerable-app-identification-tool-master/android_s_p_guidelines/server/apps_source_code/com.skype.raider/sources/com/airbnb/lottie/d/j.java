package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.b;
import java.io.IOException;

public final class j implements ah<b> {
    public static final j a = new j();

    private j() {
    }

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        String str = null;
        String str2 = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z = true;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 102:
                    if (nextName.equals("f")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 106:
                    if (nextName.equals("j")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 115:
                    if (nextName.equals("s")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 116:
                    if (nextName.equals("t")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3261:
                    if (nextName.equals("fc")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 3452:
                    if (nextName.equals("lh")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 3463:
                    if (nextName.equals("ls")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 3543:
                    if (nextName.equals("of")) {
                        obj = 10;
                        break;
                    }
                    break;
                case 3664:
                    if (nextName.equals("sc")) {
                        obj = 8;
                        break;
                    }
                    break;
                case 3684:
                    if (nextName.equals("sw")) {
                        obj = 9;
                        break;
                    }
                    break;
                case 3710:
                    if (nextName.equals("tr")) {
                        obj = 4;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    str = jsonReader.nextString();
                    break;
                case 1:
                    str2 = jsonReader.nextString();
                    break;
                case 2:
                    d = jsonReader.nextDouble();
                    break;
                case 3:
                    i = jsonReader.nextInt();
                    break;
                case 4:
                    i2 = jsonReader.nextInt();
                    break;
                case 5:
                    d2 = jsonReader.nextDouble();
                    break;
                case 6:
                    d3 = jsonReader.nextDouble();
                    break;
                case 7:
                    i3 = p.a(jsonReader);
                    break;
                case 8:
                    i4 = p.a(jsonReader);
                    break;
                case 9:
                    i5 = jsonReader.nextInt();
                    break;
                case 10:
                    z = jsonReader.nextBoolean();
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return new b(str, str2, d, i, i2, d2, d3, i3, i4, i5, z);
    }
}
