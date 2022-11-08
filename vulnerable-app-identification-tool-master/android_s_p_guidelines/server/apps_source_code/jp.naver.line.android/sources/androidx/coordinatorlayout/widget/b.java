package androidx.coordinatorlayout.widget;

import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface b {
    Class<? extends Behavior> a();
}
