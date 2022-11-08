package com.skype.ink;

import android.graphics.Canvas;
import android.graphics.Path;
import com.facebook.react.bridge.al;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class DrawCommandStack {
    private final Deque<DrawCommand> a = new ArrayDeque();
    private DrawCommand b = null;
    private final float c;
    private int d = 1;
    private int e = 0;

    DrawCommandStack(float scale) {
        this.c = scale;
    }

    public final void a(al args) {
        float[] pathData = PathUtils.a(args.getArray(0));
        boolean closePath = args.getBoolean(4);
        Path path = PathUtils.a(pathData, this.c);
        if (this.b == null) {
            this.b = new DrawCommand(path, PathUtils.a(args.getArray(1)), (float) args.getDouble(2), PathUtils.a(args.getArray(3)), this.c);
            this.a.push(this.b);
            if (this.e < this.d) {
                this.e++;
            }
        } else {
            this.b.a(path);
        }
        if (closePath) {
            this.b = null;
        }
    }

    public final boolean a() {
        boolean succeed = !this.a.isEmpty() && this.e > 0;
        if (succeed) {
            this.a.pop();
            this.e--;
        }
        return succeed;
    }

    public final void a(int limit) {
        this.d = limit;
        this.e = Math.min(this.d, this.e);
    }

    final void a(Canvas canvas) {
        Iterator<DrawCommand> iterator = this.a.descendingIterator();
        while (iterator.hasNext()) {
            ((DrawCommand) iterator.next()).a(canvas);
        }
    }
}
