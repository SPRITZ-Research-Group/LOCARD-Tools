package com.airbnb.lottie.a.a;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.os.Build.VERSION;
import com.airbnb.lottie.c.b.h;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@TargetApi(19)
public final class k implements i, l {
    private final Path a = new Path();
    private final Path b = new Path();
    private final Path c = new Path();
    private final String d;
    private final List<l> e = new ArrayList();
    private final h f;

    public k(h mergePaths) {
        if (VERSION.SDK_INT < 19) {
            throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
        }
        this.d = mergePaths.a();
        this.f = mergePaths;
    }

    public final void a(ListIterator<b> contents) {
        while (contents.hasPrevious()) {
            if (contents.previous() == this) {
                break;
            }
        }
        while (contents.hasPrevious()) {
            b content = (b) contents.previous();
            if (content instanceof l) {
                this.e.add((l) content);
                contents.remove();
            }
        }
    }

    public final void a(List<b> contentsBefore, List<b> contentsAfter) {
        for (int i = 0; i < this.e.size(); i++) {
            ((l) this.e.get(i)).a(contentsBefore, contentsAfter);
        }
    }

    public final Path e() {
        this.c.reset();
        switch (this.f.b()) {
            case Merge:
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.e.size()) {
                        break;
                    }
                    this.c.addPath(((l) this.e.get(i2)).e());
                    i = i2 + 1;
                }
            case Add:
                a(Op.UNION);
                break;
            case Subtract:
                a(Op.REVERSE_DIFFERENCE);
                break;
            case Intersect:
                a(Op.INTERSECT);
                break;
            case ExcludeIntersections:
                a(Op.XOR);
                break;
        }
        return this.c;
    }

    public final String b() {
        return this.d;
    }

    @TargetApi(19)
    private void a(Op op) {
        List<l> pathList;
        int j;
        Path path;
        this.b.reset();
        this.a.reset();
        for (int i = this.e.size() - 1; i > 0; i--) {
            l content = (l) this.e.get(i);
            if (content instanceof c) {
                pathList = ((c) content).c();
                for (j = pathList.size() - 1; j >= 0; j--) {
                    path = ((l) pathList.get(j)).e();
                    path.transform(((c) content).d());
                    this.b.addPath(path);
                }
            } else {
                this.b.addPath(content.e());
            }
        }
        l lastContent = (l) this.e.get(0);
        if (lastContent instanceof c) {
            pathList = ((c) lastContent).c();
            for (j = 0; j < pathList.size(); j++) {
                path = ((l) pathList.get(j)).e();
                path.transform(((c) lastContent).d());
                this.a.addPath(path);
            }
        } else {
            this.a.set(lastContent.e());
        }
        this.c.op(this.a, this.b, op);
    }
}
