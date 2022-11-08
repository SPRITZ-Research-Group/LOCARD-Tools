package net.hockeyapp.android.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.microsoft.tokenshare.AccountInfo;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import net.hockeyapp.android.h.d;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class k {
    private ArrayList<JSONObject> a;
    private JSONObject b;
    private Context c;
    private net.hockeyapp.android.k d;
    private int e;

    public k(Context context, String infoJSON, net.hockeyapp.android.k listener) {
        this.c = context;
        this.d = listener;
        a(infoJSON);
        Collections.sort(this.a, new Comparator<JSONObject>(this) {
            final /* synthetic */ k a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return AnonymousClass1.a((JSONObject) obj, (JSONObject) obj2);
            }

            private static int a(JSONObject object1, JSONObject object2) {
                try {
                    return object1.getInt(AccountInfo.VERSION_KEY) > object2.getInt(AccountInfo.VERSION_KEY) ? 0 : 0;
                } catch (JSONException e) {
                } catch (NullPointerException e2) {
                }
            }
        });
    }

    private void a(String infoJSON) {
        this.b = new JSONObject();
        this.a = new ArrayList();
        this.e = this.d.getCurrentVersionCode();
        try {
            JSONArray versions = new JSONArray(infoJSON);
            int versionCode = this.e;
            for (int index = 0; index < versions.length(); index++) {
                boolean largerVersionCode;
                JSONObject entry = versions.getJSONObject(index);
                if (entry.getInt(AccountInfo.VERSION_KEY) > versionCode) {
                    largerVersionCode = true;
                } else {
                    largerVersionCode = false;
                }
                boolean newerApkFile;
                if (entry.getInt(AccountInfo.VERSION_KEY) == versionCode && a(this.c, entry.getLong("timestamp"))) {
                    newerApkFile = true;
                } else {
                    newerApkFile = false;
                }
                if (largerVersionCode || newerApkFile) {
                    this.b = entry;
                    versionCode = entry.getInt(AccountInfo.VERSION_KEY);
                }
                this.a.add(entry);
            }
        } catch (JSONException e) {
        } catch (NullPointerException e2) {
        }
    }

    public final String a() {
        return a(this.b, "shortversion", "") + " (" + a(this.b, AccountInfo.VERSION_KEY, "") + ")";
    }

    @SuppressLint({"SimpleDateFormat"})
    public final String b() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(1000 * a(this.b, "timestamp")));
    }

    public final long c() {
        boolean external = Boolean.valueOf(a(this.b, "external", "false")).booleanValue();
        long appSize = a(this.b, "appsize");
        return (external && appSize == 0) ? -1 : appSize;
    }

    private static String a(JSONObject json, String name, String defaultValue) {
        try {
            return json.getString(name);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    private static long a(JSONObject json, String name) {
        try {
            return json.getLong(name);
        } catch (JSONException e) {
            return 0;
        }
    }

    public final String d() {
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<body style='padding: 0px 0px 20px 0px'>");
        int count = 0;
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            JSONObject version = (JSONObject) it.next();
            if (count > 0) {
                result.append("<hr style='border-top: 1px solid #c8c8c8; border-bottom: 0px; margin: 40px 10px 0px 10px;' />");
            }
            StringBuilder stringBuilder = new StringBuilder();
            int a = a(this.b);
            int a2 = a(version);
            String b = b(version);
            stringBuilder.append("<div style='padding: 20px 10px 10px;'><strong>");
            if (count == 0) {
                stringBuilder.append(this.c.getString(d.hockeyapp_update_newest_version)).append(':');
            } else {
                b = String.format(this.c.getString(d.hockeyapp_update_version), new Object[]{b});
                stringBuilder.append(String.format("%s (%s): ", new Object[]{b, Integer.valueOf(a2)}));
                if (a2 != a && a2 == this.e) {
                    this.e = -1;
                    stringBuilder.append(String.format("[%s]", new Object[]{this.c.getString(d.hockeyapp_update_already_installed)}));
                }
            }
            stringBuilder.append("</strong></div>");
            result.append(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            String a3 = a(version, "notes", "");
            stringBuilder.append("<div style='padding: 0px 10px;'>");
            if (a3.trim().length() == 0) {
                stringBuilder.append(String.format("<em>%s</em>", new Object[]{this.c.getString(d.hockeyapp_update_no_info)}));
            } else {
                stringBuilder.append(a3);
            }
            stringBuilder.append("</div>");
            result.append(stringBuilder.toString());
            count++;
        }
        result.append("</body>");
        result.append("</html>");
        return result.toString();
    }

    private static int a(JSONObject version) {
        int versionCode = 0;
        try {
            return version.getInt(AccountInfo.VERSION_KEY);
        } catch (JSONException e) {
            return versionCode;
        }
    }

    private static String b(JSONObject version) {
        String versionName = "";
        try {
            return version.getString("shortversion");
        } catch (JSONException e) {
            return versionName;
        }
    }

    public static int a(String left, String right) {
        if (left == null || right == null) {
            return 0;
        }
        try {
            Scanner leftScanner = new Scanner(left.replaceAll("\\-.*", ""));
            Scanner rightScanner = new Scanner(right.replaceAll("\\-.*", ""));
            leftScanner.useDelimiter("\\.");
            rightScanner.useDelimiter("\\.");
            while (leftScanner.hasNextInt() && rightScanner.hasNextInt()) {
                int leftValue = leftScanner.nextInt();
                int rightValue = rightScanner.nextInt();
                if (leftValue < rightValue) {
                    return -1;
                }
                if (leftValue > rightValue) {
                    return 1;
                }
            }
            if (leftScanner.hasNextInt()) {
                return 1;
            }
            if (rightScanner.hasNextInt()) {
                return -1;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean a(Context context, long timestamp) {
        if (context == null) {
            return false;
        }
        try {
            if (timestamp > (new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified() / 1000) + 1800) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            e.f();
            return false;
        }
    }
}
