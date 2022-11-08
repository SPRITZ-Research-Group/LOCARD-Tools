package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

@RequiresApi(16)
final class d {

    interface a {
        boolean a();

        List<Object> b();

        Object c();
    }

    /* renamed from: android.support.v4.view.accessibility.d$1 */
    static class AnonymousClass1 extends AccessibilityNodeProvider {
        final /* synthetic */ a a;

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        public final AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            this.a.c();
            return null;
        }

        public final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
            return this.a.b();
        }

        public final boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return this.a.a();
        }
    }
}
