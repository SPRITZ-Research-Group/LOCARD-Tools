package com.skype4life;

import android.support.annotation.NonNull;
import com.slowpath.hockeyapp.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class d implements c {
    private List<b> a;

    public d(b... providers) {
        this.a = Arrays.asList(providers);
    }

    @NonNull
    public final List<File> a() {
        List<File> result = new ArrayList();
        for (b a : this.a) {
            File file = a.a();
            if (file != null) {
                result.add(file);
            }
        }
        return result;
    }
}
