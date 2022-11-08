package android.support.v4.app;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class FrameMetricsAggregator {

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MetricType {
    }
}
