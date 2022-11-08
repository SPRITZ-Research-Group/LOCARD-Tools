package com.google.android.gms.location;

import com.adjust.sdk.Constants;
import com.google.android.gms.common.api.Status;

@Deprecated
public final class g {
    public static int a(int i) {
        return (i < 0 || i > 1) ? (Constants.ONE_SECOND > i || i > 1002) ? 1 : i : i;
    }

    public static Status b(int i) {
        switch (i) {
            case 1:
                i = 13;
                break;
        }
        return new Status(i);
    }
}
