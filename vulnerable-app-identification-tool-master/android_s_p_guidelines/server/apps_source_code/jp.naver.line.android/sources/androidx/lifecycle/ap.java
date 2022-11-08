package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public final class ap {
    private static Application a(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.");
    }

    public static al a(FragmentActivity fragmentActivity) {
        return a(fragmentActivity, null);
    }

    public static al a(FragmentActivity fragmentActivity, an anVar) {
        Application a = a((Activity) fragmentActivity);
        if (anVar == null) {
            anVar = am.a(a);
        }
        return new al(fragmentActivity.getViewModelStore(), anVar);
    }

    public static al a(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            return new al(fragment.getViewModelStore(), am.a(a(activity)));
        }
        throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
    }
}
