package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public final class af extends Fragment {
    private ag a;

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new af(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    static af b(Activity activity) {
        return (af) activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(j.ON_CREATE);
    }

    public final void onStart() {
        super.onStart();
        ag agVar = this.a;
        if (agVar != null) {
            agVar.a();
        }
        a(j.ON_START);
    }

    public final void onResume() {
        super.onResume();
        ag agVar = this.a;
        if (agVar != null) {
            agVar.b();
        }
        a(j.ON_RESUME);
    }

    public final void onPause() {
        super.onPause();
        a(j.ON_PAUSE);
    }

    public final void onStop() {
        super.onStop();
        a(j.ON_STOP);
    }

    public final void onDestroy() {
        super.onDestroy();
        a(j.ON_DESTROY);
        this.a = null;
    }

    private void a(j jVar) {
        Activity activity = getActivity();
        if (activity instanceof r) {
            ((r) activity).a().a(jVar);
            return;
        }
        if (activity instanceof o) {
            i lifecycle = ((o) activity).getLifecycle();
            if (lifecycle instanceof p) {
                ((p) lifecycle).a(jVar);
            }
        }
    }

    final void a(ag agVar) {
        this.a = agVar;
    }
}
