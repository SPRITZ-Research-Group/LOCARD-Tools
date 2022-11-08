package a.a;

import com.appboy.e.b;
import com.appboy.f.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class eu {
    private static final String a = c.a(eu.class);

    public static b a(JSONObject jSONObject, am amVar) {
        if (jSONObject == null) {
            try {
                c.b(a, "Templated message Json was null. Not de-serializing templated message.");
                return null;
            } catch (Throwable e) {
                c.c(a, "Encountered JSONException processing templated message: " + jSONObject, e);
                return null;
            } catch (Throwable e2) {
                c.c(a, "Encountered general exception processing templated message: " + jSONObject, e2);
                return null;
            }
        }
        String string = jSONObject.getString("type");
        if (string.equals("inapp")) {
            return cu.a(jSONObject.getJSONObject("data"), amVar);
        }
        c.f(a, "Received templated message Json with unknown type: " + string + ". Not parsing.");
        return null;
    }

    public static List<dc> a(JSONArray jSONArray, am amVar) {
        if (jSONArray == null) {
            try {
                c.b(a, "Triggered actions Json array was null. Not de-serializing triggered actions.");
                return null;
            } catch (Throwable e) {
                c.c(a, "Encountered JSONException processing triggered actions Json array: " + jSONArray, e);
                return null;
            } catch (Throwable e2) {
                c.c(a, "Failed to deserialize triggered actions Json array: " + jSONArray, e2);
                return null;
            }
        }
        List<dc> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            dc b = b(jSONArray.getJSONObject(i), amVar);
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public static dc b(JSONObject jSONObject, am amVar) {
        try {
            String string = jSONObject.getString("type");
            if (string.equals("inapp")) {
                return new dd(jSONObject, amVar);
            }
            if (string.equals("templated_iam")) {
                return new de(jSONObject, amVar);
            }
            c.d(a, "Received unknown trigger type: " + string);
            return null;
        } catch (Throwable e) {
            c.c(a, "Encountered JSONException processing triggered action Json: " + jSONObject, e);
            return null;
        } catch (Throwable e2) {
            c.c(a, "Failed to deserialize triggered action Json: " + jSONObject, e2);
            return null;
        }
    }

    public static List<dj> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<dj> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                c.f(a, "Received null or blank trigger condition Json. Not parsing.");
            } else {
                String string = optJSONObject.getString("type");
                if (string.equals("purchase")) {
                    arrayList.add(new dn(optJSONObject));
                } else if (string.equals("custom_event")) {
                    arrayList.add(new dg(optJSONObject));
                } else if (string.equals("push_click")) {
                    arrayList.add(new dp(optJSONObject));
                } else if (string.equals("open")) {
                    arrayList.add(new dl());
                } else if (string.equals("iam_click")) {
                    arrayList.add(new dk(optJSONObject));
                } else if (string.equals("test")) {
                    arrayList.add(new dq());
                } else if (string.equals("custom_event_property")) {
                    arrayList.add(new dh(optJSONObject));
                } else if (string.equals("purchase_property")) {
                    arrayList.add(new do(optJSONObject));
                } else {
                    c.f(a, "Received triggered condition Json with unknown type: " + string + ". Not parsing.");
                }
            }
        }
        return arrayList;
    }
}
