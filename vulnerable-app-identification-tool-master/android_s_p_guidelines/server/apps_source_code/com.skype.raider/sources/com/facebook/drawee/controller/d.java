package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class d<INFO> implements ControllerListener<INFO> {
    private final List<ControllerListener<? super INFO>> a = new ArrayList(2);

    public final synchronized void a(ControllerListener<? super INFO> listener) {
        this.a.add(listener);
    }

    public final synchronized void b(ControllerListener<? super INFO> listener) {
        int index = this.a.indexOf(listener);
        if (index != -1) {
            this.a.set(index, null);
        }
    }

    public final synchronized void a() {
        this.a.clear();
    }

    private synchronized void b() {
    }

    public synchronized void onSubmit(String id, Object callerContext) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ControllerListener<? super INFO> listener = (ControllerListener) this.a.get(i);
                if (listener != null) {
                    listener.onSubmit(id, callerContext);
                }
            } catch (Exception e) {
                b();
            }
        }
    }

    public synchronized void onFinalImageSet(String id, @Nullable INFO imageInfo, @Nullable Animatable animatable) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ControllerListener<? super INFO> listener = (ControllerListener) this.a.get(i);
                if (listener != null) {
                    listener.onFinalImageSet(id, imageInfo, animatable);
                }
            } catch (Exception e) {
                b();
            }
        }
    }

    public void onIntermediateImageSet(String id, @Nullable INFO imageInfo) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ControllerListener<? super INFO> listener = (ControllerListener) this.a.get(i);
                if (listener != null) {
                    listener.onIntermediateImageSet(id, imageInfo);
                }
            } catch (Exception e) {
                b();
            }
        }
    }

    public void onIntermediateImageFailed(String id, Throwable throwable) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ControllerListener<? super INFO> listener = (ControllerListener) this.a.get(i);
                if (listener != null) {
                    listener.onIntermediateImageFailed(id, throwable);
                }
            } catch (Exception e) {
                b();
            }
        }
    }

    public synchronized void onFailure(String id, Throwable throwable) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ControllerListener<? super INFO> listener = (ControllerListener) this.a.get(i);
                if (listener != null) {
                    listener.onFailure(id, throwable);
                }
            } catch (Exception e) {
                b();
            }
        }
    }

    public synchronized void onRelease(String id) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ControllerListener<? super INFO> listener = (ControllerListener) this.a.get(i);
                if (listener != null) {
                    listener.onRelease(id);
                }
            } catch (Exception e) {
                b();
            }
        }
    }
}
