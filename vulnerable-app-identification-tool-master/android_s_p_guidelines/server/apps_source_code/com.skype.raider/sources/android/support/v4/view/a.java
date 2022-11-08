package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.accessibility.c;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

public class a {
    private static final b a;
    private static final AccessibilityDelegate c = new AccessibilityDelegate();
    final AccessibilityDelegate b = a.a(this);

    static class b {
        b() {
        }

        public AccessibilityDelegate a(final a compat) {
            return new AccessibilityDelegate(this) {
                final /* synthetic */ b b;

                public final boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    return compat.d(host, event);
                }

                public final void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.a(host, event);
                }

                public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    compat.a(host, android.support.v4.view.accessibility.b.a(info));
                }

                public final void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.b(host, event);
                }

                public final boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                    return compat.a(host, child, event);
                }

                public final void sendAccessibilityEvent(View host, int eventType) {
                    a.a(host, eventType);
                }

                public final void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
                    a.c(host, event);
                }
            };
        }

        public c a(AccessibilityDelegate delegate, View host) {
            return null;
        }

        public boolean a(AccessibilityDelegate delegate, View host, int action, Bundle args) {
            return false;
        }
    }

    @RequiresApi(16)
    static class a extends b {
        a() {
        }

        public final AccessibilityDelegate a(final a compat) {
            return new AccessibilityDelegate(this) {
                final /* synthetic */ a b;

                public final boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    return compat.d(host, event);
                }

                public final void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.a(host, event);
                }

                public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    compat.a(host, android.support.v4.view.accessibility.b.a(info));
                }

                public final void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.b(host, event);
                }

                public final boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                    return compat.a(host, child, event);
                }

                public final void sendAccessibilityEvent(View host, int eventType) {
                    a.a(host, eventType);
                }

                public final void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
                    a.c(host, event);
                }

                public final AccessibilityNodeProvider getAccessibilityNodeProvider(View host) {
                    c provider = a.a(host);
                    return provider != null ? (AccessibilityNodeProvider) provider.a() : null;
                }

                public final boolean performAccessibilityAction(View host, int action, Bundle args) {
                    return compat.a(host, action, args);
                }
            };
        }

        public final c a(AccessibilityDelegate delegate, View host) {
            AccessibilityNodeProvider provider = delegate.getAccessibilityNodeProvider(host);
            if (provider != null) {
                return new c(provider);
            }
            return null;
        }

        public final boolean a(AccessibilityDelegate delegate, View host, int action, Bundle args) {
            return delegate.performAccessibilityAction(host, action, args);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static void a(View host, int eventType) {
        c.sendAccessibilityEvent(host, eventType);
    }

    public static void c(View host, AccessibilityEvent event) {
        c.sendAccessibilityEventUnchecked(host, event);
    }

    public boolean d(View host, AccessibilityEvent event) {
        return c.dispatchPopulateAccessibilityEvent(host, event);
    }

    public void b(View host, AccessibilityEvent event) {
        c.onPopulateAccessibilityEvent(host, event);
    }

    public void a(View host, AccessibilityEvent event) {
        c.onInitializeAccessibilityEvent(host, event);
    }

    public void a(View host, android.support.v4.view.accessibility.b info) {
        c.onInitializeAccessibilityNodeInfo(host, info.a());
    }

    public boolean a(ViewGroup host, View child, AccessibilityEvent event) {
        return c.onRequestSendAccessibilityEvent(host, child, event);
    }

    public static c a(View host) {
        return a.a(c, host);
    }

    public boolean a(View host, int action, Bundle args) {
        return a.a(c, host, action, args);
    }
}
