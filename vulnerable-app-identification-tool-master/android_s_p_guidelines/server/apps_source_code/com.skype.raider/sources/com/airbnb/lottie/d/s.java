package com.airbnb.lottie.d;

import android.graphics.Color;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.j;
import com.airbnb.lottie.c.a.k;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.b.g;
import com.airbnb.lottie.c.c.d;
import com.airbnb.lottie.c.c.d.b;
import com.airbnb.lottie.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class s {
    public static d a(JsonReader reader, e composition) throws IOException {
        List<a<Float>> list;
        String layerName = null;
        d.a layerType = null;
        String refId = null;
        long layerId = 0;
        int solidWidth = 0;
        int solidHeight = 0;
        int solidColor = 0;
        int preCompWidth = 0;
        int preCompHeight = 0;
        long parentId = -1;
        float timeStretch = 1.0f;
        float startFrame = 0.0f;
        float inFrame = 0.0f;
        float outFrame = 0.0f;
        String cl = null;
        b matteType = b.None;
        l transform = null;
        j text = null;
        k textProperties = null;
        com.airbnb.lottie.c.a.b timeRemapping = null;
        List<g> masks = new ArrayList();
        List<com.airbnb.lottie.c.b.b> shapes = new ArrayList();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case -995424086:
                    if (nextName.equals("parent")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -903568142:
                    if (nextName.equals("shapes")) {
                        obj = 11;
                        break;
                    }
                    break;
                case 104:
                    if (nextName.equals("h")) {
                        obj = 17;
                        break;
                    }
                    break;
                case 116:
                    if (nextName.equals("t")) {
                        obj = 12;
                        break;
                    }
                    break;
                case 119:
                    if (nextName.equals("w")) {
                        obj = 16;
                        break;
                    }
                    break;
                case 3177:
                    if (nextName.equals("cl")) {
                        obj = 21;
                        break;
                    }
                    break;
                case 3233:
                    if (nextName.equals("ef")) {
                        obj = 13;
                        break;
                    }
                    break;
                case 3367:
                    if (nextName.equals("ip")) {
                        obj = 18;
                        break;
                    }
                    break;
                case 3432:
                    if (nextName.equals("ks")) {
                        obj = 8;
                        break;
                    }
                    break;
                case 3519:
                    if (nextName.equals("nm")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3553:
                    if (nextName.equals("op")) {
                        obj = 19;
                        break;
                    }
                    break;
                case 3664:
                    if (nextName.equals("sc")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 3669:
                    if (nextName.equals("sh")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 3679:
                    if (nextName.equals("sr")) {
                        obj = 14;
                        break;
                    }
                    break;
                case 3681:
                    if (nextName.equals("st")) {
                        obj = 15;
                        break;
                    }
                    break;
                case 3684:
                    if (nextName.equals("sw")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 3705:
                    if (nextName.equals("tm")) {
                        obj = 20;
                        break;
                    }
                    break;
                case 3712:
                    if (nextName.equals("tt")) {
                        obj = 9;
                        break;
                    }
                    break;
                case 3717:
                    if (nextName.equals("ty")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 104415:
                    if (nextName.equals("ind")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 108390670:
                    if (nextName.equals("refId")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1441620890:
                    if (nextName.equals("masksProperties")) {
                        obj = 10;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    layerName = reader.nextString();
                    break;
                case 1:
                    layerId = (long) reader.nextInt();
                    break;
                case 2:
                    refId = reader.nextString();
                    break;
                case 3:
                    int layerTypeInt = reader.nextInt();
                    if (layerTypeInt >= d.a.Unknown.ordinal()) {
                        layerType = d.a.Unknown;
                        break;
                    }
                    layerType = d.a.values()[layerTypeInt];
                    break;
                case 4:
                    parentId = (long) reader.nextInt();
                    break;
                case 5:
                    solidWidth = (int) (((float) reader.nextInt()) * f.a());
                    break;
                case 6:
                    solidHeight = (int) (((float) reader.nextInt()) * f.a());
                    break;
                case 7:
                    solidColor = Color.parseColor(reader.nextString());
                    break;
                case 8:
                    transform = c.a(reader, composition);
                    break;
                case 9:
                    matteType = b.values()[reader.nextInt()];
                    break;
                case 10:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        masks.add(u.a(reader, composition));
                    }
                    reader.endArray();
                    break;
                case 11:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.airbnb.lottie.c.b.b shape = i.a(reader, composition);
                        if (shape != null) {
                            shapes.add(shape);
                        }
                    }
                    reader.endArray();
                    break;
                case 12:
                    reader.beginObject();
                    while (reader.hasNext()) {
                        nextName = reader.nextName();
                        obj = -1;
                        switch (nextName.hashCode()) {
                            case 97:
                                if (nextName.equals("a")) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 100:
                                if (nextName.equals("d")) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                                text = d.f(reader, composition);
                                break;
                            case 1:
                                reader.beginArray();
                                if (reader.hasNext()) {
                                    textProperties = b.a(reader, composition);
                                }
                                while (reader.hasNext()) {
                                    reader.skipValue();
                                }
                                reader.endArray();
                                break;
                            default:
                                reader.skipValue();
                                break;
                        }
                    }
                    reader.endObject();
                    break;
                case 13:
                    reader.beginArray();
                    List<String> effectNames = new ArrayList();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        while (reader.hasNext()) {
                            nextName = reader.nextName();
                            obj = -1;
                            switch (nextName.hashCode()) {
                                case 3519:
                                    if (nextName.equals("nm")) {
                                        obj = null;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj) {
                                case null:
                                    effectNames.add(reader.nextString());
                                    break;
                                default:
                                    reader.skipValue();
                                    break;
                            }
                        }
                        reader.endObject();
                    }
                    reader.endArray();
                    composition.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + effectNames);
                    break;
                case 14:
                    timeStretch = (float) reader.nextDouble();
                    break;
                case 15:
                    startFrame = (float) reader.nextDouble();
                    break;
                case 16:
                    preCompWidth = (int) (((float) reader.nextInt()) * f.a());
                    break;
                case 17:
                    preCompHeight = (int) (((float) reader.nextInt()) * f.a());
                    break;
                case 18:
                    inFrame = (float) reader.nextDouble();
                    break;
                case 19:
                    outFrame = (float) reader.nextDouble();
                    break;
                case 20:
                    timeRemapping = d.a(reader, composition, false);
                    break;
                case 21:
                    cl = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        inFrame /= timeStretch;
        outFrame /= timeStretch;
        List<a<Float>> inOutKeyframes = new ArrayList();
        if (inFrame > 0.0f) {
            list = inOutKeyframes;
            list.add(new a(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(inFrame)));
        }
        if (outFrame <= 0.0f) {
            outFrame = composition.e();
        }
        outFrame += 1.0f;
        list = inOutKeyframes;
        list.add(new a(composition, Float.valueOf(1.0f), Float.valueOf(1.0f), null, inFrame, Float.valueOf(outFrame)));
        list = inOutKeyframes;
        list.add(new a(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, outFrame, Float.valueOf(Float.MAX_VALUE)));
        if (layerName.endsWith(".ai") || "ai".equals(cl)) {
            composition.a("Convert your Illustrator layers to shape layers.");
        }
        return new d(shapes, composition, layerName, layerId, layerType, parentId, refId, masks, transform, solidWidth, solidHeight, solidColor, timeStretch, startFrame, preCompWidth, preCompHeight, text, textProperties, inOutKeyframes, matteType, timeRemapping);
    }
}
