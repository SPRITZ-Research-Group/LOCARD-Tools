package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import androidx.appcompat.widget.Toolbar;
import defpackage.ao;
import defpackage.ap;

public abstract class j {
    private static int a = -1;

    public abstract <T extends View> T a(int i);

    public abstract ActionBar a();

    public abstract ao a(ap apVar);

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, LayoutParams layoutParams);

    public abstract void a(Toolbar toolbar);

    public abstract void a(CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(int i);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, LayoutParams layoutParams);

    public abstract void c();

    public abstract boolean c(int i);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract c i();

    public abstract void j();

    public abstract boolean k();

    public static j a(Activity activity, i iVar) {
        return new AppCompatDelegateImpl(activity, activity.getWindow(), iVar);
    }

    public static j a(Dialog dialog, i iVar) {
        return new AppCompatDelegateImpl(dialog.getContext(), dialog.getWindow(), iVar);
    }

    j() {
    }

    public static int l() {
        return a;
    }
}
