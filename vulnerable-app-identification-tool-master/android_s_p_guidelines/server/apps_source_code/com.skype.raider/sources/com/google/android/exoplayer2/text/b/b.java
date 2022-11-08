package com.google.android.exoplayer2.text.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region.Op;
import android.util.SparseArray;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.text.Cue;
import com.skype.Defines;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class b {
    private static final byte[] a = new byte[]{(byte) 0, (byte) 7, (byte) 8, (byte) 15};
    private static final byte[] b = new byte[]{(byte) 0, (byte) 119, (byte) -120, (byte) -1};
    private static final byte[] c = new byte[]{(byte) 0, (byte) 17, (byte) 34, (byte) 51, (byte) 68, (byte) 85, (byte) 102, (byte) 119, (byte) -120, (byte) -103, (byte) -86, (byte) -69, (byte) -52, (byte) -35, (byte) -18, (byte) -1};
    private final Paint d = new Paint();
    private final Paint e;
    private final Canvas f;
    private final b g;
    private final a h;
    private final h i;
    private Bitmap j;

    private static final class a {
        public final int a;
        public final int[] b;
        public final int[] c;
        public final int[] d;

        public a(int id, int[] clutEntries2Bit, int[] clutEntries4Bit, int[] clutEntries8bit) {
            this.a = id;
            this.b = clutEntries2Bit;
            this.c = clutEntries4Bit;
            this.d = clutEntries8bit;
        }
    }

    private static final class b {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public b(int width, int height, int horizontalPositionMinimum, int horizontalPositionMaximum, int verticalPositionMinimum, int verticalPositionMaximum) {
            this.a = width;
            this.b = height;
            this.c = horizontalPositionMinimum;
            this.d = horizontalPositionMaximum;
            this.e = verticalPositionMinimum;
            this.f = verticalPositionMaximum;
        }
    }

    private static final class c {
        public final int a;
        public final boolean b;
        public final byte[] c;
        public final byte[] d;

        public c(int id, boolean nonModifyingColorFlag, byte[] topFieldData, byte[] bottomFieldData) {
            this.a = id;
            this.b = nonModifyingColorFlag;
            this.c = topFieldData;
            this.d = bottomFieldData;
        }
    }

    private static final class d {
        public final int a;
        public final int b;
        public final int c;
        public final SparseArray<e> d;

        public d(int timeoutSecs, int version, int state, SparseArray<e> regions) {
            this.a = timeoutSecs;
            this.b = version;
            this.c = state;
            this.d = regions;
        }
    }

    private static final class e {
        public final int a;
        public final int b;

        public e(int horizontalAddress, int verticalAddress) {
            this.a = horizontalAddress;
            this.b = verticalAddress;
        }
    }

    private static final class f {
        public final int a;
        public final boolean b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;
        public final int j;
        public final SparseArray<g> k;

        public f(int id, boolean fillFlag, int width, int height, int levelOfCompatibility, int depth, int clutId, int pixelCode8Bit, int pixelCode4Bit, int pixelCode2Bit, SparseArray<g> regionObjects) {
            this.a = id;
            this.b = fillFlag;
            this.c = width;
            this.d = height;
            this.e = levelOfCompatibility;
            this.f = depth;
            this.g = clutId;
            this.h = pixelCode8Bit;
            this.i = pixelCode4Bit;
            this.j = pixelCode2Bit;
            this.k = regionObjects;
        }

        public final void a(f otherRegionComposition) {
            if (otherRegionComposition != null) {
                SparseArray<g> otherRegionObjects = otherRegionComposition.k;
                for (int i = 0; i < otherRegionObjects.size(); i++) {
                    this.k.put(otherRegionObjects.keyAt(i), otherRegionObjects.valueAt(i));
                }
            }
        }
    }

    private static final class g {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public g(int type, int provider, int horizontalPosition, int verticalPosition, int foregroundPixelCode, int backgroundPixelCode) {
            this.a = type;
            this.b = provider;
            this.c = horizontalPosition;
            this.d = verticalPosition;
            this.e = foregroundPixelCode;
            this.f = backgroundPixelCode;
        }
    }

    private static final class h {
        public final int a;
        public final int b;
        public final SparseArray<f> c = new SparseArray();
        public final SparseArray<a> d = new SparseArray();
        public final SparseArray<c> e = new SparseArray();
        public final SparseArray<a> f = new SparseArray();
        public final SparseArray<c> g = new SparseArray();
        public b h;
        public d i;

        public h(int subtitlePageId, int ancillaryPageId) {
            this.a = subtitlePageId;
            this.b = ancillaryPageId;
        }
    }

    public b(int subtitlePageId, int ancillaryPageId) {
        this.d.setStyle(Style.FILL_AND_STROKE);
        this.d.setXfermode(new PorterDuffXfermode(Mode.SRC));
        this.d.setPathEffect(null);
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setXfermode(new PorterDuffXfermode(Mode.DST_OVER));
        this.e.setPathEffect(null);
        this.f = new Canvas();
        this.g = new b(719, 575, 0, 719, 0, 575);
        this.h = new a(0, b(), c(), d());
        this.i = new h(subtitlePageId, ancillaryPageId);
    }

    public final void a() {
        h hVar = this.i;
        hVar.c.clear();
        hVar.d.clear();
        hVar.e.clear();
        hVar.f.clear();
        hVar.g.clear();
        hVar.h = null;
        hVar.i = null;
    }

    public final List<Cue> a(byte[] data, int limit) {
        int c;
        int c2;
        int c3;
        j jVar = new j(data, limit);
        while (jVar.a() >= 48 && jVar.c(8) == 15) {
            h hVar = this.i;
            int c4 = jVar.c(8);
            int c5 = jVar.c(16);
            c = jVar.c(16);
            int c6 = jVar.c() + c;
            if (c * 8 > jVar.a()) {
                jVar.b(jVar.a());
            } else {
                int c7;
                d dVar;
                switch (c4) {
                    case 16:
                        if (c5 == hVar.a) {
                            d dVar2 = hVar.i;
                            c2 = jVar.c(8);
                            c3 = jVar.c(4);
                            c7 = jVar.c(2);
                            jVar.b(2);
                            c4 = c - 2;
                            SparseArray sparseArray = new SparseArray();
                            while (c4 > 0) {
                                int c8 = jVar.c(8);
                                jVar.b(8);
                                c4 -= 6;
                                sparseArray.put(c8, new e(jVar.c(16), jVar.c(16)));
                            }
                            dVar = new d(c2, c3, c7, sparseArray);
                            if (dVar.c == 0) {
                                if (!(dVar2 == null || dVar2.b == dVar.b)) {
                                    hVar.i = dVar;
                                    break;
                                }
                            }
                            hVar.i = dVar;
                            hVar.c.clear();
                            hVar.d.clear();
                            hVar.e.clear();
                            break;
                        }
                        break;
                    case 17:
                        dVar = hVar.i;
                        if (c5 == hVar.a && dVar != null) {
                            f a = a(jVar, c);
                            if (dVar.c == 0) {
                                a.a((f) hVar.c.get(a.a));
                            }
                            hVar.c.put(a.a, a);
                            break;
                        }
                    case 18:
                        a b;
                        if (c5 != hVar.a) {
                            if (c5 == hVar.b) {
                                b = b(jVar, c);
                                hVar.f.put(b.a, b);
                                break;
                            }
                        }
                        b = b(jVar, c);
                        hVar.d.put(b.a, b);
                        break;
                        break;
                    case 19:
                        c a2;
                        if (c5 != hVar.a) {
                            if (c5 == hVar.b) {
                                a2 = a(jVar);
                                hVar.g.put(a2.a, a2);
                                break;
                            }
                        }
                        a2 = a(jVar);
                        hVar.e.put(a2.a, a2);
                        break;
                        break;
                    case 20:
                        if (c5 == hVar.a) {
                            int c9;
                            jVar.b(4);
                            boolean d = jVar.d();
                            jVar.b(3);
                            c5 = jVar.c(16);
                            c = jVar.c(16);
                            if (d) {
                                c2 = jVar.c(16);
                                c3 = jVar.c(16);
                                c9 = jVar.c(16);
                                c7 = jVar.c(16);
                            } else {
                                c2 = 0;
                                c9 = 0;
                                c7 = c;
                                c3 = c5;
                            }
                            hVar.h = new b(c5, c, c2, c3, c9, c7);
                            break;
                        }
                        break;
                }
                jVar.d(c6 - jVar.c());
            }
        }
        if (this.i.i == null) {
            return Collections.emptyList();
        }
        b displayDefinition;
        if (this.i.h != null) {
            displayDefinition = this.i.h;
        } else {
            displayDefinition = this.g;
        }
        if (!(this.j != null && displayDefinition.a + 1 == this.j.getWidth() && displayDefinition.b + 1 == this.j.getHeight())) {
            this.j = Bitmap.createBitmap(displayDefinition.a + 1, displayDefinition.b + 1, Config.ARGB_8888);
            this.f.setBitmap(this.j);
        }
        List<Cue> cues = new ArrayList();
        SparseArray<e> pageRegions = this.i.i.d;
        for (int i = 0; i < pageRegions.size(); i++) {
            e pageRegion = (e) pageRegions.valueAt(i);
            f regionComposition = (f) this.i.c.get(pageRegions.keyAt(i));
            int baseHorizontalAddress = pageRegion.a + displayDefinition.c;
            int baseVerticalAddress = pageRegion.b + displayDefinition.e;
            this.f.clipRect((float) baseHorizontalAddress, (float) baseVerticalAddress, (float) Math.min(regionComposition.c + baseHorizontalAddress, displayDefinition.d), (float) Math.min(regionComposition.d + baseVerticalAddress, displayDefinition.f), Op.REPLACE);
            a clutDefinition = (a) this.i.d.get(regionComposition.g);
            if (clutDefinition == null) {
                clutDefinition = (a) this.i.f.get(regionComposition.g);
                if (clutDefinition == null) {
                    clutDefinition = this.h;
                }
            }
            SparseArray<g> regionObjects = regionComposition.k;
            for (int j = 0; j < regionObjects.size(); j++) {
                int objectId = regionObjects.keyAt(j);
                g regionObject = (g) regionObjects.valueAt(j);
                c objectData = (c) this.i.e.get(objectId);
                if (objectData == null) {
                    objectData = (c) this.i.g.get(objectId);
                }
                if (objectData != null) {
                    Paint paint;
                    int[] iArr;
                    if (objectData.b) {
                        paint = null;
                    } else {
                        paint = this.d;
                    }
                    c = regionComposition.f;
                    c2 = baseHorizontalAddress + regionObject.c;
                    c3 = baseVerticalAddress + regionObject.d;
                    Canvas canvas = this.f;
                    if (c == 3) {
                        iArr = clutDefinition.d;
                    } else if (c == 2) {
                        iArr = clutDefinition.c;
                    } else {
                        iArr = clutDefinition.b;
                    }
                    a(objectData.c, iArr, c, c2, c3, paint, canvas);
                    a(objectData.d, iArr, c, c2, c3 + 1, paint, canvas);
                }
            }
            if (regionComposition.b) {
                int color;
                if (regionComposition.f == 3) {
                    color = clutDefinition.d[regionComposition.h];
                } else if (regionComposition.f == 2) {
                    color = clutDefinition.c[regionComposition.i];
                } else {
                    color = clutDefinition.b[regionComposition.j];
                }
                this.e.setColor(color);
                this.f.drawRect((float) baseHorizontalAddress, (float) baseVerticalAddress, (float) (regionComposition.c + baseHorizontalAddress), (float) (regionComposition.d + baseVerticalAddress), this.e);
            }
            List<Cue> list = cues;
            list.add(new Cue(Bitmap.createBitmap(this.j, baseHorizontalAddress, baseVerticalAddress, regionComposition.c, regionComposition.d), ((float) baseHorizontalAddress) / ((float) displayDefinition.a), ((float) baseVerticalAddress) / ((float) displayDefinition.b), ((float) regionComposition.c) / ((float) displayDefinition.a), ((float) regionComposition.d) / ((float) displayDefinition.b)));
            this.f.drawColor(0, Mode.CLEAR);
        }
        return cues;
    }

    private static f a(j data, int length) {
        int id = data.c(8);
        data.b(4);
        boolean fillFlag = data.d();
        data.b(3);
        int width = data.c(16);
        int height = data.c(16);
        int levelOfCompatibility = data.c(3);
        int depth = data.c(3);
        data.b(2);
        int clutId = data.c(8);
        int pixelCode8Bit = data.c(8);
        int pixelCode4Bit = data.c(4);
        int pixelCode2Bit = data.c(2);
        data.b(2);
        int remainingLength = length - 10;
        SparseArray<g> regionObjects = new SparseArray();
        while (remainingLength > 0) {
            int objectId = data.c(16);
            int objectType = data.c(2);
            int objectProvider = data.c(2);
            int objectHorizontalPosition = data.c(12);
            data.b(4);
            int objectVerticalPosition = data.c(12);
            remainingLength -= 6;
            int foregroundPixelCode = 0;
            int backgroundPixelCode = 0;
            if (objectType == 1 || objectType == 2) {
                foregroundPixelCode = data.c(8);
                backgroundPixelCode = data.c(8);
                remainingLength -= 2;
            }
            regionObjects.put(objectId, new g(objectType, objectProvider, objectHorizontalPosition, objectVerticalPosition, foregroundPixelCode, backgroundPixelCode));
        }
        return new f(id, fillFlag, width, height, levelOfCompatibility, depth, clutId, pixelCode8Bit, pixelCode4Bit, pixelCode2Bit, regionObjects);
    }

    private static a b(j data, int length) {
        int clutId = data.c(8);
        data.b(8);
        int remainingLength = length - 2;
        int[] clutEntries2Bit = b();
        int[] clutEntries4Bit = c();
        int[] clutEntries8Bit = d();
        while (remainingLength > 0) {
            int[] clutEntries;
            int y;
            int cr;
            int cb;
            int t;
            int entryId = data.c(8);
            int entryFlags = data.c(8);
            remainingLength -= 2;
            if ((entryFlags & 128) != 0) {
                clutEntries = clutEntries2Bit;
            } else if ((entryFlags & 64) != 0) {
                clutEntries = clutEntries4Bit;
            } else {
                clutEntries = clutEntries8Bit;
            }
            if ((entryFlags & 1) != 0) {
                y = data.c(8);
                cr = data.c(8);
                cb = data.c(8);
                t = data.c(8);
                remainingLength -= 4;
            } else {
                y = data.c(6) << 2;
                cr = data.c(4) << 4;
                cb = data.c(4) << 4;
                t = data.c(2) << 6;
                remainingLength -= 2;
            }
            if (y == 0) {
                cr = 0;
                cb = 0;
                t = 255;
            }
            int g = (int) ((((double) y) - (0.34414d * ((double) (cb - 128)))) - (0.71414d * ((double) (cr - 128))));
            int b = (int) (((double) y) + (1.772d * ((double) (cb - 128))));
            clutEntries[entryId] = a((byte) (255 - (t & 255)), s.a((int) (((double) y) + (1.402d * ((double) (cr - 128)))), 0, 255), s.a(g, 0, 255), s.a(b, 0, 255));
        }
        return new a(clutId, clutEntries2Bit, clutEntries4Bit, clutEntries8Bit);
    }

    private static c a(j data) {
        int objectId = data.c(16);
        data.b(4);
        int objectCodingMethod = data.c(2);
        boolean nonModifyingColorFlag = data.d();
        data.b(1);
        byte[] topFieldData = null;
        byte[] bottomFieldData = null;
        if (objectCodingMethod == 1) {
            data.b(data.c(8) * 16);
        } else if (objectCodingMethod == 0) {
            int topFieldDataLength = data.c(16);
            int bottomFieldDataLength = data.c(16);
            if (topFieldDataLength > 0) {
                topFieldData = new byte[topFieldDataLength];
                data.b(topFieldData, topFieldDataLength);
            }
            if (bottomFieldDataLength > 0) {
                bottomFieldData = new byte[bottomFieldDataLength];
                data.b(bottomFieldData, bottomFieldDataLength);
            } else {
                bottomFieldData = topFieldData;
            }
        }
        return new c(objectId, nonModifyingColorFlag, topFieldData, bottomFieldData);
    }

    private static int[] b() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] c() {
        int[] entries = new int[16];
        entries[0] = 0;
        for (int i = 1; i < 16; i++) {
            if (i < 8) {
                int i2;
                if ((i & 1) != 0) {
                    i2 = 255;
                } else {
                    i2 = 0;
                }
                entries[i] = a(255, i2, (i & 2) != 0 ? 255 : 0, (i & 4) != 0 ? 255 : 0);
            } else {
                entries[i] = a(255, (i & 1) != 0 ? 127 : 0, (i & 2) != 0 ? 127 : 0, (i & 4) != 0 ? 127 : 0);
            }
        }
        return entries;
    }

    private static int[] d() {
        int[] entries = new int[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
        entries[0] = 0;
        for (int i = 0; i < Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE; i++) {
            if (i >= 8) {
                switch (i & 136) {
                    case 0:
                        entries[i] = a(255, ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0), ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0), ((i & 64) != 0 ? 170 : 0) + ((i & 4) != 0 ? 85 : 0));
                        break;
                    case 8:
                        entries[i] = a(127, ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0), ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0), ((i & 64) != 0 ? 170 : 0) + ((i & 4) != 0 ? 85 : 0));
                        break;
                    case 128:
                        entries[i] = a(255, (((i & 1) != 0 ? 43 : 0) + 127) + ((i & 16) != 0 ? 85 : 0), (((i & 2) != 0 ? 43 : 0) + 127) + ((i & 32) != 0 ? 85 : 0), ((i & 64) != 0 ? 85 : 0) + (((i & 4) != 0 ? 43 : 0) + 127));
                        break;
                    case 136:
                        entries[i] = a(255, ((i & 1) != 0 ? 43 : 0) + ((i & 16) != 0 ? 85 : 0), ((i & 2) != 0 ? 43 : 0) + ((i & 32) != 0 ? 85 : 0), ((i & 64) != 0 ? 85 : 0) + ((i & 4) != 0 ? 43 : 0));
                        break;
                    default:
                        break;
                }
            }
            int i2;
            if ((i & 1) != 0) {
                i2 = 255;
            } else {
                i2 = 0;
            }
            entries[i] = a(63, i2, (i & 2) != 0 ? 255 : 0, (i & 4) != 0 ? 255 : 0);
        }
        return entries;
    }

    private static int a(int a, int r, int g, int b) {
        return (((a << 24) | (r << 16)) | (g << 8)) | b;
    }

    private static void a(byte[] pixelData, int[] clutEntries, int regionDepth, int horizontalAddress, int verticalAddress, Paint paint, Canvas canvas) {
        j data = new j(pixelData);
        int column = horizontalAddress;
        int line = verticalAddress;
        byte[] clutMapTable2To4 = null;
        byte[] clutMapTable2To8 = null;
        while (data.a() != 0) {
            Object obj;
            int c;
            int i;
            Object obj2;
            int i2;
            int obj22;
            switch (data.c(8)) {
                case 16:
                    byte[] clutMapTable2ToX = regionDepth == 3 ? clutMapTable2To8 == null ? b : clutMapTable2To8 : regionDepth == 2 ? clutMapTable2To4 == null ? a : clutMapTable2To4 : null;
                    obj = null;
                    while (true) {
                        c = data.c(2);
                        if (!data.d()) {
                            i = 1;
                            obj22 = obj;
                            i2 = c;
                        } else if (data.d()) {
                            i = data.c(3) + 3;
                            obj22 = obj;
                            i2 = data.c(2);
                        } else {
                            if (!data.d()) {
                                switch (data.c(2)) {
                                    case 0:
                                        i = 0;
                                        obj22 = 1;
                                        i2 = 0;
                                        break;
                                    case 1:
                                        i = 2;
                                        obj22 = obj;
                                        i2 = 0;
                                        break;
                                    case 2:
                                        i = data.c(4) + 12;
                                        obj22 = obj;
                                        i2 = data.c(2);
                                        break;
                                    case 3:
                                        i = data.c(8) + 29;
                                        obj22 = obj;
                                        i2 = data.c(2);
                                        break;
                                }
                            }
                            i = 0;
                            obj22 = obj;
                            i2 = 0;
                        }
                        if (!(i == 0 || paint == null)) {
                            if (clutMapTable2ToX != null) {
                                i2 = clutMapTable2ToX[i2];
                            }
                            paint.setColor(clutEntries[i2]);
                            canvas.drawRect((float) column, (float) line, (float) (column + i), (float) (line + 1), paint);
                        }
                        column += i;
                        if (obj22 != null) {
                            data.e();
                            break;
                        }
                        obj = obj22;
                    }
                case 17:
                    byte[] clutMapTable4ToX;
                    if (regionDepth == 3) {
                        clutMapTable4ToX = c;
                    } else {
                        clutMapTable4ToX = null;
                    }
                    obj = null;
                    while (true) {
                        c = data.c(4);
                        if (c == 0) {
                            if (data.d()) {
                                if (data.d()) {
                                    switch (data.c(2)) {
                                        case 0:
                                            i = 1;
                                            obj22 = obj;
                                            i2 = 0;
                                            break;
                                        case 1:
                                            i = 2;
                                            obj22 = obj;
                                            i2 = 0;
                                            break;
                                        case 2:
                                            i = data.c(4) + 9;
                                            obj22 = obj;
                                            i2 = data.c(4);
                                            break;
                                        case 3:
                                            i = data.c(8) + 25;
                                            obj22 = obj;
                                            i2 = data.c(4);
                                            break;
                                        default:
                                            i = 0;
                                            obj22 = obj;
                                            i2 = 0;
                                            break;
                                    }
                                }
                                i = data.c(2) + 4;
                                obj22 = obj;
                                i2 = data.c(4);
                            } else {
                                c = data.c(3);
                                if (c != 0) {
                                    i = c + 2;
                                    obj22 = obj;
                                    i2 = 0;
                                } else {
                                    i = 0;
                                    obj22 = 1;
                                    i2 = 0;
                                }
                            }
                        } else {
                            i = 1;
                            obj22 = obj;
                            i2 = c;
                        }
                        if (!(i == 0 || paint == null)) {
                            if (clutMapTable4ToX != null) {
                                i2 = clutMapTable4ToX[i2];
                            }
                            paint.setColor(clutEntries[i2]);
                            canvas.drawRect((float) column, (float) line, (float) (column + i), (float) (line + 1), paint);
                        }
                        column += i;
                        if (obj22 != null) {
                            data.e();
                            break;
                        }
                        obj = obj22;
                    }
                case 18:
                    obj = null;
                    while (true) {
                        c = data.c(8);
                        if (c != 0) {
                            i = 1;
                            obj22 = obj;
                            i2 = c;
                        } else if (data.d()) {
                            i = data.c(7);
                            obj22 = obj;
                            i2 = data.c(8);
                        } else {
                            int c2 = data.c(7);
                            if (c2 != 0) {
                                i = c2;
                                obj22 = obj;
                                i2 = 0;
                            } else {
                                i = 0;
                                obj22 = 1;
                                i2 = 0;
                            }
                        }
                        if (!(i == 0 || paint == null)) {
                            paint.setColor(clutEntries[i2]);
                            canvas.drawRect((float) column, (float) line, (float) (column + i), (float) (line + 1), paint);
                        }
                        column += i;
                        if (obj22 != null) {
                            break;
                        }
                        obj = obj22;
                    }
                case 32:
                    clutMapTable2To4 = a(4, 4, data);
                    break;
                case 33:
                    clutMapTable2To8 = a(4, 8, data);
                    break;
                case 34:
                    clutMapTable2To8 = a(16, 8, data);
                    break;
                case 240:
                    column = horizontalAddress;
                    line += 2;
                    break;
                default:
                    break;
            }
        }
    }

    private static byte[] a(int length, int bitsPerEntry, j data) {
        byte[] clutMapTable = new byte[length];
        for (int i = 0; i < length; i++) {
            clutMapTable[i] = (byte) data.c(bitsPerEntry);
        }
        return clutMapTable;
    }
}
