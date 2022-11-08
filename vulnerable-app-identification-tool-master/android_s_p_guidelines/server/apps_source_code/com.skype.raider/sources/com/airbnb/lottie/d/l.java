package com.airbnb.lottie.d;

import android.graphics.Color;
import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.c.b.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class l implements ah<c> {
    private int a;

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        int i = 0;
        List arrayList = new ArrayList();
        int i2 = jsonReader.peek() == JsonToken.BEGIN_ARRAY ? 1 : 0;
        if (i2 != 0) {
            jsonReader.beginArray();
        }
        while (jsonReader.hasNext()) {
            arrayList.add(Float.valueOf((float) jsonReader.nextDouble()));
        }
        if (i2 != 0) {
            jsonReader.endArray();
        }
        if (this.a == -1) {
            this.a = arrayList.size() / 4;
        }
        float[] fArr = new float[this.a];
        int[] iArr = new int[this.a];
        if (arrayList.size() != this.a * 4) {
            new StringBuilder("Unexpected gradient length: ").append(arrayList.size()).append(". Expected ").append(this.a * 4).append(". This may affect the appearance of the gradient. Make sure to save your After Effects file before exporting an animation with gradients.");
        }
        i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i;
            if (i2 < this.a * 4) {
                int i5 = i2 / 4;
                double floatValue = (double) ((Float) arrayList.get(i2)).floatValue();
                switch (i2 % 4) {
                    case 0:
                        fArr[i5] = (float) floatValue;
                        break;
                    case 1:
                        i4 = (int) (floatValue * 255.0d);
                        break;
                    case 2:
                        i3 = (int) (floatValue * 255.0d);
                        break;
                    case 3:
                        iArr[i5] = Color.argb(255, i4, i3, (int) (floatValue * 255.0d));
                        break;
                    default:
                        break;
                }
                i = i2 + 1;
            } else {
                c cVar = new c(fArr, iArr);
                a(cVar, arrayList);
                return cVar;
            }
        }
    }

    public l(int colorPoints) {
        this.a = colorPoints;
    }

    private void a(c gradientColor, List<Float> array) {
        int startIndex = this.a * 4;
        if (array.size() > startIndex) {
            int i;
            int opacityStops = (array.size() - startIndex) / 2;
            double[] positions = new double[opacityStops];
            double[] opacities = new double[opacityStops];
            int j = 0;
            for (i = startIndex; i < array.size(); i++) {
                if (i % 2 == 0) {
                    positions[j] = (double) ((Float) array.get(i)).floatValue();
                } else {
                    opacities[j] = (double) ((Float) array.get(i)).floatValue();
                    j++;
                }
            }
            for (i = 0; i < gradientColor.c(); i++) {
                double d;
                int color = gradientColor.b()[i];
                double d2 = (double) gradientColor.a()[i];
                for (int i2 = 1; i2 < opacityStops; i2++) {
                    d = positions[i2 - 1];
                    double d3 = positions[i2];
                    if (positions[i2] >= d2) {
                        d2 = (d2 - d) / (d3 - d);
                        d = 255.0d;
                        d3 = opacities[i2 - 1];
                        d2 = (d2 * (opacities[i2] - d3)) + d3;
                        break;
                    }
                }
                d2 = 255.0d;
                d = opacities[opacityStops - 1];
                gradientColor.b()[i] = Color.argb((int) (d2 * d), Color.red(color), Color.green(color), Color.blue(color));
            }
        }
    }
}
