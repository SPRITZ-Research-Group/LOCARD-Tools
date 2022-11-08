package com.google.android.exoplayer2.extractor.a;

import com.google.android.exoplayer2.d.k;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

final class c extends d {
    private long b = -9223372036854775807L;

    public c() {
        super(null);
    }

    public final long a() {
        return this.b;
    }

    protected final boolean a(k data) {
        return true;
    }

    private static Double b(k data) {
        return Double.valueOf(Double.longBitsToDouble(data.p()));
    }

    private static String c(k data) {
        int size = data.h();
        int position = data.d();
        data.d(size);
        return new String(data.a, position, size);
    }

    private static ArrayList<Object> d(k data) {
        int count = data.t();
        ArrayList<Object> list = new ArrayList(count);
        for (int i = 0; i < count; i++) {
            list.add(a(data, data.g()));
        }
        return list;
    }

    private static HashMap<String, Object> e(k data) {
        int count = data.t();
        HashMap<String, Object> array = new HashMap(count);
        for (int i = 0; i < count; i++) {
            array.put(c(data), a(data, data.g()));
        }
        return array;
    }

    private static Object a(k data, int type) {
        boolean z = true;
        Object hashMap;
        switch (type) {
            case 0:
                return b(data);
            case 1:
                if (data.g() != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                return c(data);
            case 3:
                hashMap = new HashMap();
                while (true) {
                    String c = c(data);
                    int g = data.g();
                    if (g == 9) {
                        return hashMap;
                    }
                    hashMap.put(c, a(data, g));
                }
            case 8:
                return e(data);
            case 10:
                return d(data);
            case 11:
                hashMap = new Date((long) b(data).doubleValue());
                data.d(2);
                return hashMap;
            default:
                return null;
        }
    }

    protected final void a(k data, long timeUs) throws com.google.android.exoplayer2.k {
        if (data.g() != 2) {
            throw new com.google.android.exoplayer2.k();
        }
        if ("onMetaData".equals(c(data)) && data.g() == 8) {
            Map<String, Object> metadata = e(data);
            if (metadata.containsKey("duration")) {
                double durationSeconds = ((Double) metadata.get("duration")).doubleValue();
                if (durationSeconds > 0.0d) {
                    this.b = (long) (1000000.0d * durationSeconds);
                }
            }
        }
    }
}
