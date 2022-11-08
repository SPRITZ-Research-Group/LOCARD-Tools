package com.projectseptember.RNGL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Map;

public final class h {
    final i a;
    final Map<String, Integer> b;
    final Map<String, Float> c;
    final Map<String, IntBuffer> d;
    final Map<String, FloatBuffer> e;
    final Map<String, l> f;
    final Integer g;
    final Integer h;
    final Integer i;
    final List<h> j;
    final List<h> k;
    final int l;

    public h(i shader, Map<String, Integer> uniformsInteger, Map<String, Float> uniformsFloat, Map<String, IntBuffer> uniformsIntBuffer, Map<String, FloatBuffer> uniformsFloatBuffer, Map<String, l> textures, Integer width, Integer height, Integer fboId, List<h> contextChildren, List<h> children, int causeId) {
        this.a = shader;
        this.b = uniformsInteger;
        this.c = uniformsFloat;
        this.d = uniformsIntBuffer;
        this.e = uniformsFloatBuffer;
        this.f = textures;
        this.g = width;
        this.h = height;
        this.i = fboId;
        this.j = contextChildren;
        this.k = children;
        this.l = causeId;
    }
}
