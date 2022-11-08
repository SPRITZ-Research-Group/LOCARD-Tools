package a.a;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import com.amazon.device.messaging.development.ADMManifest;
import com.appboy.f.c;

public class aa {
    private static final String c = c.a(aa.class);
    private final Context a;
    private final aq b;

    public aa(Context context, aq aqVar) {
        this.a = context;
        this.b = aqVar;
    }

    public final void a() {
        if (this.b.a() != null) {
            c.d(c, "The device is already registered with the ADM server and is eligible to receive ADM messages.");
            c.d(c, "ADM registration id: " + this.b.a());
            this.b.a(this.b.a());
            return;
        }
        ADM adm = new ADM(this.a);
        if (adm.isSupported()) {
            c.d(c, "Registering with ADM server...");
            adm.startRegister();
        }
    }

    public static boolean a(Context context) {
        return b() && b(context);
    }

    private static boolean b() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return true;
        } catch (Exception e) {
            c.d(c, "com.amazon.device.messaging.ADM not found");
            return false;
        }
    }

    private static boolean b(Context context) {
        try {
            ADMManifest.checkManifestAuthoredProperly(context);
            return true;
        } catch (Throwable e) {
            c.d(c, "Manifest not authored properly to support ADM.");
            c.b(c, "ADM manifest exception: ", e);
            return false;
        }
    }
}
