package com.skype.coachmark;

import android.app.FragmentManager;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.common.e.a;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import java.util.Map;

public class CoachMarkModule extends ReactContextBaseJavaModule {
    private static final String ERROR_UNABLE_TO_ADD_FRAGMENT = "E_UNABLE_TO_ADD_FRAGMENT";
    private static final String ERROR_UNABLE_TO_REMOVE_FRAGMENT = "E_UNABLE_TO_REMOVE_FRAGMENT";
    private static final String ON_USER_EVENT = "OnUserEvent";
    public static final String REACT_CLASS = "CoachMarkNativeEvents";
    private CoachMarkFragment coachMarkFragment;

    public CoachMarkModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Map<String, Object> getConstants() {
        a<String, Object> builder = e.a();
        builder.a(ON_USER_EVENT, ON_USER_EVENT);
        return builder.a();
    }

    @ReactMethod
    public void subscribeToUserEvents(ae p) {
        if (isActivityAlive()) {
            try {
                addFragment(getCoachMarkFragment());
            } catch (Exception e) {
                p.a(ERROR_UNABLE_TO_ADD_FRAGMENT, e.getMessage());
            }
            p.a(Boolean.valueOf(true));
            return;
        }
        p.a(Boolean.valueOf(false));
    }

    @ReactMethod
    public void unsubscribeFromUserEvents(ae p) {
        if (this.coachMarkFragment == null) {
            p.a(null);
            return;
        }
        try {
            removeFragment(this.coachMarkFragment);
        } catch (Exception e) {
            p.a(ERROR_UNABLE_TO_REMOVE_FRAGMENT, e.getMessage());
        }
        p.a(null);
    }

    public void emitNativeUserEvent(ar params) {
        getEventEmitter().emit(ON_USER_EVENT, params);
    }

    private void addFragment(CoachMarkFragment fragment) {
        getFragmentManager().beginTransaction().add(16908290, fragment).commit();
    }

    private void removeFragment(CoachMarkFragment fragment) {
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private CoachMarkFragment getCoachMarkFragment() {
        if (this.coachMarkFragment != null && this.coachMarkFragment.isAdded()) {
            removeFragment(this.coachMarkFragment);
        }
        this.coachMarkFragment = new CoachMarkFragment();
        this.coachMarkFragment.a(this);
        return this.coachMarkFragment;
    }

    private RCTNativeAppEventEmitter getEventEmitter() {
        return (RCTNativeAppEventEmitter) getReactApplicationContext().a(RCTNativeAppEventEmitter.class);
    }

    private FragmentManager getFragmentManager() {
        return getReactApplicationContext().j().getFragmentManager();
    }

    private boolean isActivityAlive() {
        return getReactApplicationContext().j() != null;
    }
}
