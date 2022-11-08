package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class f extends Fragment {
    private a a;

    interface a {
    }

    public static void a(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        if (manager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            manager.beginTransaction().add(new f(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            manager.executePendingTransactions();
        }
    }

    public final void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        a(android.arch.lifecycle.b.a.ON_CREATE);
    }

    public final void onStart() {
        super.onStart();
        a(android.arch.lifecycle.b.a.ON_START);
    }

    public final void onResume() {
        super.onResume();
        a(android.arch.lifecycle.b.a.ON_RESUME);
    }

    public final void onPause() {
        super.onPause();
        a(android.arch.lifecycle.b.a.ON_PAUSE);
    }

    public final void onStop() {
        super.onStop();
        a(android.arch.lifecycle.b.a.ON_STOP);
    }

    public final void onDestroy() {
        super.onDestroy();
        a(android.arch.lifecycle.b.a.ON_DESTROY);
        this.a = null;
    }

    private void a(android.arch.lifecycle.b.a event) {
        Activity activity = getActivity();
        if (activity instanceof e) {
            ((e) activity).a().a(event);
        } else if (activity instanceof c) {
            b lifecycle = ((c) activity).getLifecycle();
            if (lifecycle instanceof d) {
                ((d) lifecycle).a(event);
            }
        }
    }
}
