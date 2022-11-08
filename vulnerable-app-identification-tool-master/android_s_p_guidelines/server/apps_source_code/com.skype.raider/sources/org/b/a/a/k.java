package org.b.a.a;

import java.util.LinkedHashMap;

public final class k {
    protected LinkedHashMap<String, Integer> a = new LinkedHashMap();
    protected int b = -1;

    public final int a(String s) {
        Integer I = (Integer) this.a.get(s);
        if (I != null) {
            return I.intValue();
        }
        this.b++;
        this.a.put(s, Integer.valueOf(this.b));
        return this.b;
    }

    public final String[] a() {
        String[] a = new String[this.a.size()];
        int i = 0;
        for (String s : this.a.keySet()) {
            int i2 = i + 1;
            a[i] = s;
            i = i2;
        }
        return a;
    }
}
