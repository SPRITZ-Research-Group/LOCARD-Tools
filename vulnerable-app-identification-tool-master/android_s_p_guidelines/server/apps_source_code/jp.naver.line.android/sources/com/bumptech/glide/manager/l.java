package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.d;
import com.bumptech.glide.w;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public final class l extends Fragment {
    private final a a;
    private final p b;
    private final Set<l> c;
    private w d;
    private l e;
    private Fragment f;

    public l() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    private l(a aVar) {
        this.b = new m(this);
        this.c = new HashSet();
        this.a = aVar;
    }

    public final void a(w wVar) {
        this.d = wVar;
    }

    final a a() {
        return this.a;
    }

    public final w b() {
        return this.d;
    }

    public final p c() {
        return this.b;
    }

    private void a(l lVar) {
        this.c.add(lVar);
    }

    private void b(l lVar) {
        this.c.remove(lVar);
    }

    final void a(Fragment fragment) {
        this.f = fragment;
        if (fragment != null && fragment.getActivity() != null) {
            a(fragment.getActivity());
        }
    }

    private void a(Activity activity) {
        d();
        this.e = d.a((Context) activity).f().b(activity);
        if (!equals(this.e)) {
            this.e.a(this);
        }
    }

    private void d() {
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            a(activity);
        } catch (Throwable e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public final void onDetach() {
        super.onDetach();
        d();
    }

    public final void onStart() {
        super.onStart();
        this.a.a();
    }

    public final void onStop() {
        super.onStop();
        this.a.b();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.a.c();
        d();
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("{parent=");
        Object parentFragment = VERSION.SDK_INT >= 17 ? getParentFragment() : null;
        if (parentFragment == null) {
            parentFragment = this.f;
        }
        stringBuilder.append(parentFragment);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
