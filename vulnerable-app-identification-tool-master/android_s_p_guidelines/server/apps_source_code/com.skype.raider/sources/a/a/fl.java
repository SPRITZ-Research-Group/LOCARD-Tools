package a.a;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.appboy.a.b;

public final class fl extends b {
    public fl(Context context) {
        super(context);
    }

    public final long a() {
        return (long) (a("com_appboy_data_flush_interval_bad_network", 60) * Constants.ONE_SECOND);
    }

    public final long b() {
        return (long) (a("com_appboy_data_flush_interval_good_network", 30) * Constants.ONE_SECOND);
    }

    public final long c() {
        return (long) (a("com_appboy_data_flush_interval_great_network", 10) * Constants.ONE_SECOND);
    }
}
