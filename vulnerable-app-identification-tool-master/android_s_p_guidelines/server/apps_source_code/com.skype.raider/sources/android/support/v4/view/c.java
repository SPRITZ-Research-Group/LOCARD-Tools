package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.adjust.sdk.Constants;

public final class c {
    private final a a;

    interface a {
        boolean a(MotionEvent motionEvent);
    }

    static class b implements a {
        private static final int j = ViewConfiguration.getLongPressTimeout();
        private static final int k = ViewConfiguration.getTapTimeout();
        private static final int l = ViewConfiguration.getDoubleTapTimeout();
        final OnGestureListener a;
        OnDoubleTapListener b;
        boolean c;
        boolean d;
        MotionEvent e;
        private int f;
        private int g;
        private int h;
        private int i;
        private final Handler m = new a(this);
        private boolean n;
        private boolean o;
        private boolean p;
        private MotionEvent q;
        private boolean r;
        private float s;
        private float t;
        private float u;
        private float v;
        private boolean w;
        private VelocityTracker x;

        private class a extends Handler {
            final /* synthetic */ b a;

            a(b bVar) {
                this.a = bVar;
            }

            public final void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        this.a.a.onShowPress(this.a.e);
                        return;
                    case 2:
                        this.a.a();
                        return;
                    case 3:
                        if (this.a.b == null) {
                            return;
                        }
                        if (this.a.c) {
                            this.a.d = true;
                            return;
                        } else {
                            this.a.b.onSingleTapConfirmed(this.a.e);
                            return;
                        }
                    default:
                        throw new RuntimeException("Unknown message " + msg);
                }
            }
        }

        public b(Context context, OnGestureListener listener) {
            this.a = listener;
            if (listener instanceof OnDoubleTapListener) {
                this.b = (OnDoubleTapListener) listener;
            }
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            } else if (this.a == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            } else {
                this.w = true;
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
                this.h = viewConfiguration.getScaledMinimumFlingVelocity();
                this.i = viewConfiguration.getScaledMaximumFlingVelocity();
                this.f = scaledTouchSlop * scaledTouchSlop;
                this.g = scaledDoubleTapSlop * scaledDoubleTapSlop;
            }
        }

        public final boolean a(MotionEvent ev) {
            int i;
            int div;
            int action = ev.getAction();
            if (this.x == null) {
                this.x = VelocityTracker.obtain();
            }
            this.x.addMovement(ev);
            boolean pointerUp = (action & 255) == 6;
            int skipIndex = pointerUp ? ev.getActionIndex() : -1;
            float sumX = 0.0f;
            float sumY = 0.0f;
            int count = ev.getPointerCount();
            for (i = 0; i < count; i++) {
                if (skipIndex != i) {
                    sumX += ev.getX(i);
                    sumY += ev.getY(i);
                }
            }
            if (pointerUp) {
                div = count - 1;
            } else {
                div = count;
            }
            float focusX = sumX / ((float) div);
            float focusY = sumY / ((float) div);
            boolean handled = false;
            switch (action & 255) {
                case 0:
                    if (this.b != null) {
                        boolean hadTapMessage = this.m.hasMessages(3);
                        if (hadTapMessage) {
                            this.m.removeMessages(3);
                        }
                        if (!(this.e == null || this.q == null || !hadTapMessage)) {
                            Object obj;
                            MotionEvent motionEvent = this.e;
                            MotionEvent motionEvent2 = this.q;
                            if (this.p && ev.getEventTime() - motionEvent2.getEventTime() <= ((long) l)) {
                                int x = ((int) motionEvent.getX()) - ((int) ev.getX());
                                int y = ((int) motionEvent.getY()) - ((int) ev.getY());
                                if ((y * y) + (x * x) < this.g) {
                                    obj = 1;
                                    if (obj != null) {
                                        this.r = true;
                                        handled = (this.b.onDoubleTap(this.e) | 0) | this.b.onDoubleTapEvent(ev);
                                    }
                                }
                            }
                            obj = null;
                            if (obj != null) {
                                this.r = true;
                                handled = (this.b.onDoubleTap(this.e) | 0) | this.b.onDoubleTapEvent(ev);
                            }
                        }
                        this.m.sendEmptyMessageDelayed(3, (long) l);
                    }
                    this.s = focusX;
                    this.u = focusX;
                    this.t = focusY;
                    this.v = focusY;
                    if (this.e != null) {
                        this.e.recycle();
                    }
                    this.e = MotionEvent.obtain(ev);
                    this.o = true;
                    this.p = true;
                    this.c = true;
                    this.n = false;
                    this.d = false;
                    if (this.w) {
                        this.m.removeMessages(2);
                        this.m.sendEmptyMessageAtTime(2, (this.e.getDownTime() + ((long) k)) + ((long) j));
                    }
                    this.m.sendEmptyMessageAtTime(1, this.e.getDownTime() + ((long) k));
                    return handled | this.a.onDown(ev);
                case 1:
                    this.c = false;
                    MotionEvent currentUpEvent = MotionEvent.obtain(ev);
                    if (this.r) {
                        handled = this.b.onDoubleTapEvent(ev) | 0;
                    } else if (this.n) {
                        this.m.removeMessages(3);
                        this.n = false;
                    } else if (this.o) {
                        handled = this.a.onSingleTapUp(ev);
                        if (this.d && this.b != null) {
                            this.b.onSingleTapConfirmed(ev);
                        }
                    } else {
                        VelocityTracker velocityTracker = this.x;
                        int pointerId = ev.getPointerId(0);
                        velocityTracker.computeCurrentVelocity(Constants.ONE_SECOND, (float) this.i);
                        float velocityY = velocityTracker.getYVelocity(pointerId);
                        float velocityX = velocityTracker.getXVelocity(pointerId);
                        if (Math.abs(velocityY) > ((float) this.h) || Math.abs(velocityX) > ((float) this.h)) {
                            handled = this.a.onFling(this.e, ev, velocityX, velocityY);
                        }
                    }
                    if (this.q != null) {
                        this.q.recycle();
                    }
                    this.q = currentUpEvent;
                    if (this.x != null) {
                        this.x.recycle();
                        this.x = null;
                    }
                    this.r = false;
                    this.d = false;
                    this.m.removeMessages(1);
                    this.m.removeMessages(2);
                    return handled;
                case 2:
                    if (this.n) {
                        return false;
                    }
                    float scrollX = this.s - focusX;
                    float scrollY = this.t - focusY;
                    if (this.r) {
                        return this.b.onDoubleTapEvent(ev) | 0;
                    }
                    if (this.o) {
                        int deltaX = (int) (focusX - this.u);
                        int deltaY = (int) (focusY - this.v);
                        int distance = (deltaX * deltaX) + (deltaY * deltaY);
                        if (distance > this.f) {
                            handled = this.a.onScroll(this.e, ev, scrollX, scrollY);
                            this.s = focusX;
                            this.t = focusY;
                            this.o = false;
                            this.m.removeMessages(3);
                            this.m.removeMessages(1);
                            this.m.removeMessages(2);
                        }
                        if (distance <= this.f) {
                            return handled;
                        }
                        this.p = false;
                        return handled;
                    } else if (Math.abs(scrollX) < 1.0f && Math.abs(scrollY) < 1.0f) {
                        return false;
                    } else {
                        handled = this.a.onScroll(this.e, ev, scrollX, scrollY);
                        this.s = focusX;
                        this.t = focusY;
                        return handled;
                    }
                case 3:
                    this.m.removeMessages(1);
                    this.m.removeMessages(2);
                    this.m.removeMessages(3);
                    this.x.recycle();
                    this.x = null;
                    this.r = false;
                    this.c = false;
                    this.o = false;
                    this.p = false;
                    this.d = false;
                    if (!this.n) {
                        return false;
                    }
                    this.n = false;
                    return false;
                case 5:
                    this.s = focusX;
                    this.u = focusX;
                    this.t = focusY;
                    this.v = focusY;
                    this.m.removeMessages(1);
                    this.m.removeMessages(2);
                    this.m.removeMessages(3);
                    this.r = false;
                    this.o = false;
                    this.p = false;
                    this.d = false;
                    if (!this.n) {
                        return false;
                    }
                    this.n = false;
                    return false;
                case 6:
                    this.s = focusX;
                    this.u = focusX;
                    this.t = focusY;
                    this.v = focusY;
                    this.x.computeCurrentVelocity(Constants.ONE_SECOND, (float) this.i);
                    int upIndex = ev.getActionIndex();
                    int id1 = ev.getPointerId(upIndex);
                    float x1 = this.x.getXVelocity(id1);
                    float y1 = this.x.getYVelocity(id1);
                    for (i = 0; i < count; i++) {
                        if (i != upIndex) {
                            int id2 = ev.getPointerId(i);
                            if ((x1 * this.x.getXVelocity(id2)) + (y1 * this.x.getYVelocity(id2)) < 0.0f) {
                                this.x.clear();
                                return false;
                            }
                        }
                    }
                    return false;
                default:
                    return false;
            }
        }

        final void a() {
            this.m.removeMessages(3);
            this.d = false;
            this.n = true;
            this.a.onLongPress(this.e);
        }
    }

    static class c implements a {
        private final GestureDetector a;

        public c(Context context, OnGestureListener listener) {
            this.a = new GestureDetector(context, listener, null);
        }

        public final boolean a(MotionEvent ev) {
            return this.a.onTouchEvent(ev);
        }
    }

    public c(Context context, OnGestureListener listener) {
        this(context, listener, (byte) 0);
    }

    private c(Context context, OnGestureListener listener, byte b) {
        if (VERSION.SDK_INT > 17) {
            this.a = new c(context, listener);
        } else {
            this.a = new b(context, listener);
        }
    }

    public final boolean a(MotionEvent event) {
        return this.a.a(event);
    }
}
