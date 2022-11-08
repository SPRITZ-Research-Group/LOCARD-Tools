package android.support.v4.print;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PrintHelper {

    @Retention(RetentionPolicy.SOURCE)
    private @interface ColorMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface ScaleMode {
    }
}
