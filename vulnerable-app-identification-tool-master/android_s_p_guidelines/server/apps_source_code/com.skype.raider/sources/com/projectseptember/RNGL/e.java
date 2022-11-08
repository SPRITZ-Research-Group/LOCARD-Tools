package com.projectseptember.RNGL;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import java.util.ArrayList;
import java.util.List;

public final class e {
    final Integer a;
    final am b;
    final Double c;
    final Double d;
    final Double e;
    final Integer f;
    final List<e> g;
    final List<e> h;

    private e(Integer shader, am uniforms, Double width, Double height, Double pixelRatio, Integer fboId, List<e> contextChildren, List<e> children) {
        this.a = shader;
        this.b = uniforms;
        this.c = width;
        this.d = height;
        this.e = pixelRatio;
        this.f = fboId;
        this.g = contextChildren;
        this.h = children;
    }

    private static List<e> a(al arr) {
        ArrayList<e> list = new ArrayList();
        for (int i = 0; i < arr.size(); i++) {
            list.add(a(arr.getMap(i)));
        }
        return list;
    }

    public static e a(am map) {
        return new e(Integer.valueOf(map.getInt("shader")), map.getMap("uniforms"), Double.valueOf(map.getDouble("width")), Double.valueOf(map.getDouble("height")), Double.valueOf(map.getDouble("pixelRatio")), Integer.valueOf(map.getInt("fboId")), a(map.getArray("contextChildren")), a(map.getArray("children")));
    }
}
