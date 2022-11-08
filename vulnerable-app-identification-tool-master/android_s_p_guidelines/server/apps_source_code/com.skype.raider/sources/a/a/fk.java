package a.a;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appboy.f.c;

public final class fk implements fo {
    private static final String a = c.a(fk.class);
    private fv b = fv.UNKNOWN;
    private boolean c = false;
    private boolean d = false;

    public final fv a() {
        return this.b;
    }

    public final void a(Intent intent, ConnectivityManager connectivityManager) {
        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                if (activeNetworkInfo == null || booleanExtra) {
                    this.b = fv.NONE;
                    this.d = false;
                    this.c = false;
                    return;
                }
                this.d = activeNetworkInfo.isConnectedOrConnecting();
                this.c = activeNetworkInfo.isRoaming();
                switch (activeNetworkInfo.getType()) {
                    case 0:
                        switch (activeNetworkInfo.getSubtype()) {
                            case 3:
                                this.b = fv.THREE_G;
                                return;
                            case 13:
                                this.b = fv.FOUR_G;
                                return;
                            default:
                                this.b = fv.TWO_G;
                                return;
                        }
                    case 1:
                        this.b = fv.WIFI;
                        return;
                    case 2:
                        this.b = fv.UNKNOWN;
                        return;
                    case 3:
                        this.b = fv.UNKNOWN;
                        return;
                    case 4:
                        this.b = fv.UNKNOWN;
                        return;
                    case 5:
                        this.b = fv.UNKNOWN;
                        return;
                    case 6:
                        this.b = fv.WIFI;
                        return;
                    case 7:
                        this.b = fv.UNKNOWN;
                        return;
                    case 8:
                        this.b = fv.UNKNOWN;
                        return;
                    case 9:
                        this.b = fv.UNKNOWN;
                        return;
                    default:
                        this.b = fv.UNKNOWN;
                        return;
                }
            } catch (Throwable e) {
                c.d(a, "Failed to get active network information. Ensure the permission android.permission.ACCESS_NETWORK_STATE is defined in your AndroidManifest.xml", e);
                return;
            }
        }
        c.f(a, "Unexpected system broadcast received [" + action + "]");
    }
}
