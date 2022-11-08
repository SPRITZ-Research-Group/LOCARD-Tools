package com.airbnb.lottie.d;

import android.graphics.Rect;
import android.support.v4.util.m;
import android.util.JsonReader;
import com.airbnb.lottie.c.b.n;
import com.airbnb.lottie.c.c;
import com.airbnb.lottie.c.c.d.a;
import com.airbnb.lottie.c.d;
import com.airbnb.lottie.e;
import com.airbnb.lottie.e.f;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class t {
    public static e a(JsonReader reader) throws IOException {
        float scale = f.a();
        float startFrame = 0.0f;
        float endFrame = 0.0f;
        float frameRate = 0.0f;
        android.support.v4.util.f layerMap = new android.support.v4.util.f();
        List layers = new ArrayList();
        int width = 0;
        int height = 0;
        Map precomps = new HashMap();
        Map images = new HashMap();
        Map<String, c> fonts = new HashMap();
        m<d> characters = new m();
        e composition = new e();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case -1408207997:
                    if (nextName.equals("assets")) {
                        obj = 7;
                        break;
                    }
                    break;
                case -1109732030:
                    if (nextName.equals("layers")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 104:
                    if (nextName.equals("h")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 118:
                    if (nextName.equals("v")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 119:
                    if (nextName.equals("w")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3276:
                    if (nextName.equals("fr")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 3367:
                    if (nextName.equals("ip")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 3553:
                    if (nextName.equals("op")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 94623709:
                    if (nextName.equals("chars")) {
                        obj = 9;
                        break;
                    }
                    break;
                case 97615364:
                    if (nextName.equals("fonts")) {
                        obj = 8;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    width = reader.nextInt();
                    break;
                case 1:
                    height = reader.nextInt();
                    break;
                case 2:
                    startFrame = (float) reader.nextDouble();
                    break;
                case 3:
                    endFrame = (float) reader.nextDouble();
                    break;
                case 4:
                    frameRate = (float) reader.nextDouble();
                    break;
                case 5:
                    String[] versions = reader.nextString().split("\\.");
                    if (!f.a(Integer.parseInt(versions[0]), Integer.parseInt(versions[1]), Integer.parseInt(versions[2]))) {
                        composition.a("Lottie only supports bodymovin >= 4.4.0");
                        break;
                    }
                    break;
                case 6:
                    a(reader, composition, layers, layerMap);
                    break;
                case 7:
                    a(reader, composition, precomps, images);
                    break;
                case 8:
                    a(reader, fonts);
                    break;
                case 9:
                    a(reader, composition, characters);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        composition.a(new Rect(0, 0, (int) (((float) width) * scale), (int) (((float) height) * scale)), startFrame, endFrame, frameRate, layers, layerMap, precomps, images, characters, fonts);
        return composition;
    }

    private static void a(JsonReader reader, e composition, List<com.airbnb.lottie.c.c.d> layers, android.support.v4.util.f<com.airbnb.lottie.c.c.d> layerMap) throws IOException {
        int imageCount = 0;
        reader.beginArray();
        while (reader.hasNext()) {
            com.airbnb.lottie.c.c.d layer = s.a(reader, composition);
            if (layer.k() == a.Image) {
                imageCount++;
            }
            layers.add(layer);
            layerMap.a(layer.e(), layer);
            if (imageCount > 4) {
                new StringBuilder("You have ").append(imageCount).append(" images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        reader.endArray();
    }

    private static void a(JsonReader reader, e composition, Map<String, List<com.airbnb.lottie.c.c.d>> precomps, Map<String, com.airbnb.lottie.f> images) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            String id = null;
            List<com.airbnb.lottie.c.c.d> layers = new ArrayList();
            android.support.v4.util.f<com.airbnb.lottie.c.c.d> layerMap = new android.support.v4.util.f();
            int width = 0;
            int height = 0;
            String imageFileName = null;
            String relativeFolder = null;
            reader.beginObject();
            while (reader.hasNext()) {
                String nextName = reader.nextName();
                Object obj = -1;
                switch (nextName.hashCode()) {
                    case -1109732030:
                        if (nextName.equals("layers")) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 104:
                        if (nextName.equals("h")) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 112:
                        if (nextName.equals("p")) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 117:
                        if (nextName.equals("u")) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 119:
                        if (nextName.equals("w")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 3355:
                        if (nextName.equals("id")) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        id = reader.nextString();
                        break;
                    case 1:
                        reader.beginArray();
                        while (reader.hasNext()) {
                            com.airbnb.lottie.c.c.d layer = s.a(reader, composition);
                            layerMap.a(layer.e(), layer);
                            layers.add(layer);
                        }
                        reader.endArray();
                        break;
                    case 2:
                        width = reader.nextInt();
                        break;
                    case 3:
                        height = reader.nextInt();
                        break;
                    case 4:
                        imageFileName = reader.nextString();
                        break;
                    case 5:
                        relativeFolder = reader.nextString();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
            if (imageFileName != null) {
                com.airbnb.lottie.f image = new com.airbnb.lottie.f(width, height, id, imageFileName, relativeFolder);
                images.put(image.a(), image);
            } else {
                precomps.put(id, layers);
            }
        }
        reader.endArray();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(JsonReader reader, Map<String, c> fonts) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            Object obj;
            String nextName = reader.nextName();
            switch (nextName.hashCode()) {
                case 3322014:
                    if (nextName.equals("list")) {
                        obj = null;
                        break;
                    }
                default:
                    obj = -1;
                    break;
            }
            switch (obj) {
                case null:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        float f = 0.0f;
                        reader.beginObject();
                        String str = null;
                        String str2 = null;
                        String str3 = null;
                        while (reader.hasNext()) {
                            Object obj2;
                            String nextName2 = reader.nextName();
                            switch (nextName2.hashCode()) {
                                case -1866931350:
                                    if (nextName2.equals("fFamily")) {
                                        obj2 = null;
                                        break;
                                    }
                                case -1408684838:
                                    if (nextName2.equals("ascent")) {
                                        obj2 = 3;
                                        break;
                                    }
                                case -1294566165:
                                    if (nextName2.equals("fStyle")) {
                                        obj2 = 2;
                                        break;
                                    }
                                case 96619537:
                                    if (nextName2.equals("fName")) {
                                        obj2 = 1;
                                        break;
                                    }
                                default:
                                    obj2 = -1;
                                    break;
                            }
                            switch (obj2) {
                                case null:
                                    str3 = reader.nextString();
                                    break;
                                case 1:
                                    str2 = reader.nextString();
                                    break;
                                case 2:
                                    str = reader.nextString();
                                    break;
                                case 3:
                                    f = (float) reader.nextDouble();
                                    break;
                                default:
                                    reader.skipValue();
                                    break;
                            }
                        }
                        reader.endObject();
                        c font = new c(str3, str2, str, f);
                        fonts.put(font.b(), font);
                    }
                    reader.endArray();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
    }

    private static void a(JsonReader reader, e composition, m<d> characters) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            double d = 0.0d;
            List arrayList = new ArrayList();
            reader.beginObject();
            String str = null;
            String str2 = null;
            int i = 0;
            char c = 0;
            while (reader.hasNext()) {
                String nextName = reader.nextName();
                int i2 = -1;
                switch (nextName.hashCode()) {
                    case -1866931350:
                        if (nextName.equals("fFamily")) {
                            i2 = 4;
                            break;
                        }
                        break;
                    case 119:
                        if (nextName.equals("w")) {
                            i2 = 2;
                            break;
                        }
                        break;
                    case 3173:
                        if (nextName.equals("ch")) {
                            i2 = 0;
                            break;
                        }
                        break;
                    case 3076010:
                        if (nextName.equals("data")) {
                            i2 = 5;
                            break;
                        }
                        break;
                    case 3530753:
                        if (nextName.equals("size")) {
                            i2 = 1;
                            break;
                        }
                        break;
                    case 109780401:
                        if (nextName.equals("style")) {
                            i2 = 3;
                            break;
                        }
                        break;
                }
                switch (i2) {
                    case 0:
                        c = reader.nextString().charAt(0);
                        break;
                    case 1:
                        i = reader.nextInt();
                        break;
                    case 2:
                        d = reader.nextDouble();
                        break;
                    case 3:
                        str2 = reader.nextString();
                        break;
                    case 4:
                        str = reader.nextString();
                        break;
                    case 5:
                        reader.beginObject();
                        while (reader.hasNext()) {
                            if ("shapes".equals(reader.nextName())) {
                                reader.beginArray();
                                while (reader.hasNext()) {
                                    arrayList.add((n) i.a(reader, composition));
                                }
                                reader.endArray();
                            } else {
                                reader.skipValue();
                            }
                        }
                        reader.endObject();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
            d character = new d(arrayList, c, i, d, str2, str);
            characters.a(character.hashCode(), character);
        }
        reader.endArray();
    }
}
