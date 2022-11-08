package androidx.fragment.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment.SavedState;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class k {
    public abstract SavedState a(Fragment fragment);

    public abstract Fragment a(int i);

    public abstract Fragment a(Bundle bundle, String str);

    public abstract Fragment a(String str);

    public abstract ad a();

    public abstract void a(Bundle bundle, String str, Fragment fragment);

    public abstract void a(m mVar);

    public abstract void a(n nVar);

    public abstract void a(String str, int i);

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void b(int i);

    public abstract void b(m mVar);

    public abstract boolean b();

    public abstract boolean b(String str, int i);

    public abstract l c(int i);

    public abstract void c();

    public abstract boolean d();

    public abstract int e();

    public abstract List<Fragment> f();

    public abstract boolean g();

    public abstract boolean h();
}
