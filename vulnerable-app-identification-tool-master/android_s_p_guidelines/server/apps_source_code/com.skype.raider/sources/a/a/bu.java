package a.a;

import android.net.Uri;
import java.util.Iterator;
import java.util.Map;

public abstract class bu implements br {
    public final Uri a;
    private Map<String, String> b = null;

    protected bu(Uri uri) {
        this.a = Uri.parse(uri + j());
    }

    public Uri a() {
        return this.a;
    }

    private String j() {
        if (this.b == null || this.b.size() == 0) {
            return "";
        }
        String str = "?";
        Iterator it = this.b.keySet().iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2.substring(0, str2.length() - 1);
            }
            str = (String) it.next();
            str = str2 + str + "=" + ((String) this.b.get(str)) + "&";
        }
    }
}
