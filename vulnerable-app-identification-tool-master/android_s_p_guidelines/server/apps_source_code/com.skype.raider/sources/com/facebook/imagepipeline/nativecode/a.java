package com.facebook.imagepipeline.nativecode;

import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class a {
    public static final List<String> a = Collections.unmodifiableList(new ArrayList());

    public static void a() {
        SoLoader.a("imagepipeline");
    }
}
