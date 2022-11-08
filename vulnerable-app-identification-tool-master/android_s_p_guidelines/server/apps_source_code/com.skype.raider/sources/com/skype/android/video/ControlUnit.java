package com.skype.android.video;

import java.lang.ref.WeakReference;
import java.util.Vector;

public final class ControlUnit {
    public static final int CAMERA_FRONT = 1;
    public static final int CAMERA_REAR = 0;
    public static final int CTRL_CMD_CHANGE_ORIENTATION = 2;
    public static final int STATE_PREVIEW_RESOLUTION_CHANGED = 272;
    public static final int VIEW_ROLE_PREVIEW = 0;
    public static final int VIEW_WHAT_SURFACE_HOLDER = 3;
    static Vector<WeakReference<StateListener>> listeners;

    public interface StateListener {
        void onStateChanged(int i, int i2, int i3);
    }

    public static native int registerView(Object obj, int i, int i2, int i3);

    public static native int sendControlCommand(int i, int i2, int i3, int i4);

    public static native int unregisterView(int i, int i2, int i3, int i4);

    public static boolean hasFrontCamera() {
        return DeviceProfile.getCameraIndex(1) != -1;
    }

    public static synchronized boolean hasBackCamera() {
        boolean z = false;
        synchronized (ControlUnit.class) {
            if (DeviceProfile.getCameraIndex(0) != -1) {
                z = true;
            }
        }
        return z;
    }

    public static synchronized void registerStateListener(StateListener listener) {
        synchronized (ControlUnit.class) {
            if (listeners == null) {
                listeners = new Vector();
            }
            listeners.add(new WeakReference(listener));
        }
    }

    public static synchronized boolean unregisterStateListener(StateListener listener) {
        boolean z;
        synchronized (ControlUnit.class) {
            z = listeners != null && listeners.remove(new WeakReference(listener));
        }
        return z;
    }

    public static synchronized void onStateChanged(int stateWhat, int arg1, int arg2) {
        synchronized (ControlUnit.class) {
            new StringBuilder("StateChanged!! what:").append(stateWhat).append(" arg1:").append(arg1).append(" arg2:").append(arg2);
            if (listeners != null) {
                int i = 0;
                while (i < listeners.size()) {
                    StateListener listener = (StateListener) ((WeakReference) listeners.get(i)).get();
                    if (listener != null) {
                        listener.onStateChanged(stateWhat, arg1, arg2);
                        i++;
                    } else {
                        listeners.remove(i);
                    }
                }
            }
        }
    }
}
