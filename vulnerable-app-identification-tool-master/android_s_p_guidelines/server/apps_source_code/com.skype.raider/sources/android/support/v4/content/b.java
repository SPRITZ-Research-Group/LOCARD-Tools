package android.support.v4.content;

import android.support.v4.util.d;
import java.io.PrintWriter;

public final class b<D> {
    int a;
    b<D> b;
    a<D> c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;

    public interface a<D> {
    }

    public interface b<D> {
    }

    public final void a(int id, b<D> listener) {
        if (this.b != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.b = listener;
        this.a = id;
    }

    public final void a(b<D> listener) {
        if (this.b == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.b != listener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.b = null;
        }
    }

    public final void a(a<D> listener) {
        if (this.c != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.c = listener;
    }

    public final void b(a<D> listener) {
        if (this.c == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.c != listener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.c = null;
        }
    }

    public final void a() {
        this.d = true;
        this.f = false;
        this.e = false;
    }

    public final void b() {
        this.d = false;
    }

    public final void c() {
        this.f = true;
        this.d = false;
        this.e = false;
        this.g = false;
        this.h = false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        d.a(this, sb);
        sb.append(" id=");
        sb.append(this.a);
        sb.append("}");
        return sb.toString();
    }

    public final void a(String prefix, PrintWriter writer) {
        writer.print(prefix);
        writer.print("mId=");
        writer.print(this.a);
        writer.print(" mListener=");
        writer.println(this.b);
        if (this.d || this.g || this.h) {
            writer.print(prefix);
            writer.print("mStarted=");
            writer.print(this.d);
            writer.print(" mContentChanged=");
            writer.print(this.g);
            writer.print(" mProcessingChange=");
            writer.println(this.h);
        }
        if (this.e || this.f) {
            writer.print(prefix);
            writer.print("mAbandoned=");
            writer.print(this.e);
            writer.print(" mReset=");
            writer.println(this.f);
        }
    }
}
