package com.facebook.ads.internal.m;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.e.c;
import com.facebook.ads.internal.e.d;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.q.a.r;
import com.microsoft.applications.telemetry.LogConfiguration;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g implements a {
    private static final String a = g.class.getSimpleName();
    private Context b;
    private d c;

    @VisibleForTesting
    public g(Context context, d dVar) {
        this.b = context;
        this.c = dVar;
    }

    private static JSONArray a(JSONArray jSONArray, JSONArray jSONArray2, int i) {
        if (jSONArray == null) {
            return jSONArray2;
        }
        if (jSONArray2 == null) {
            return jSONArray;
        }
        int length = jSONArray.length();
        int length2 = jSONArray2.length();
        JSONArray jSONArray3 = new JSONArray();
        Object obj = null;
        double d = Double.MAX_VALUE;
        double d2 = Double.MAX_VALUE;
        Object obj2 = null;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if ((i3 < length || i2 < length2) && i > 0) {
                int i4;
                JSONObject jSONObject;
                double d3;
                Object obj3;
                int i5;
                if (i3 >= length || obj != null) {
                    i4 = i3;
                } else {
                    try {
                        jSONObject = jSONArray.getJSONObject(i3);
                        d3 = jSONObject.getDouble("time");
                    } catch (JSONException e) {
                        jSONObject = null;
                        d3 = Double.MAX_VALUE;
                    }
                    d = d3;
                    i4 = i3 + 1;
                    obj = jSONObject;
                }
                if (i2 >= length2 || obj2 != null) {
                    d3 = d2;
                    obj3 = obj2;
                    i5 = i2;
                } else {
                    try {
                        jSONObject = jSONArray2.getJSONObject(i2);
                        d3 = jSONObject.getDouble("time");
                    } catch (JSONException e2) {
                        jSONObject = null;
                        d3 = Double.MAX_VALUE;
                    }
                    obj3 = jSONObject;
                    i5 = i2 + 1;
                }
                if (obj == null && obj3 == null) {
                    d2 = d3;
                    i2 = i5;
                    obj2 = obj3;
                    i3 = i4;
                } else {
                    Object obj4;
                    Object obj5;
                    double d4;
                    if (obj == null || d3 < d) {
                        jSONArray3.put(obj3);
                        d3 = Double.MAX_VALUE;
                        double d5 = d;
                        obj4 = null;
                        obj5 = obj;
                        d4 = d5;
                    } else {
                        jSONArray3.put(obj);
                        d4 = Double.MAX_VALUE;
                        obj5 = null;
                        obj4 = obj3;
                    }
                    i--;
                    d2 = d3;
                    i2 = i5;
                    i3 = i4;
                    obj2 = obj4;
                    obj = obj5;
                    d = d4;
                }
            }
        }
        if (i > 0) {
            if (obj != null) {
                jSONArray3.put(obj);
            } else if (obj2 != null) {
                jSONArray3.put(obj2);
            }
        }
        return jSONArray3;
    }

    private JSONObject a(int i) {
        Cursor a;
        Cursor cursor;
        Throwable th;
        Cursor d;
        try {
            d = this.c.d();
            try {
                a = this.c.a(i);
                try {
                    JSONArray jSONArray;
                    Object obj;
                    JSONArray a2;
                    Object a3;
                    JSONObject jSONObject;
                    if (a.getCount() > 0) {
                        JSONObject a4 = a(a);
                        jSONArray = new JSONArray();
                        a.moveToPosition(-1);
                        while (a.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("id", a.getString(2));
                            jSONObject2.put("token_id", a.getString(0));
                            jSONObject2.put("type", a.getString(4));
                            jSONObject2.put("time", r.a(a.getDouble(5)));
                            jSONObject2.put("session_time", r.a(a.getDouble(6)));
                            jSONObject2.put("session_id", a.getString(7));
                            String string = a.getString(8);
                            jSONObject2.put("data", string != null ? new JSONObject(string) : new JSONObject());
                            jSONObject2.put("attempt", a.getString(9));
                            jSONArray.put(jSONObject2);
                        }
                        obj = a4;
                    } else {
                        jSONArray = null;
                        obj = null;
                    }
                    if (a.h(this.b)) {
                        a2 = e.a(this.b, i);
                        if (a2.length() > 0) {
                            a3 = a(a2, jSONArray, i);
                            if (a3 == null) {
                                jSONObject = new JSONObject();
                                if (obj != null) {
                                    jSONObject.put("tokens", obj);
                                }
                                jSONObject.put(EventsEntry.TABLE_NAME, a3);
                            } else {
                                jSONObject = null;
                            }
                            if (d != null) {
                                d.close();
                            }
                            if (a != null) {
                                return jSONObject;
                            }
                            a.close();
                            return jSONObject;
                        }
                    }
                    a2 = jSONArray;
                    if (a3 == null) {
                        jSONObject = null;
                    } else {
                        jSONObject = new JSONObject();
                        if (obj != null) {
                            jSONObject.put("tokens", obj);
                        }
                        jSONObject.put(EventsEntry.TABLE_NAME, a3);
                    }
                    if (d != null) {
                        d.close();
                    }
                    if (a != null) {
                        return jSONObject;
                    }
                    a.close();
                    return jSONObject;
                } catch (JSONException e) {
                    cursor = a;
                    a = d;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (JSONException e2) {
                cursor = null;
                a = d;
            } catch (Throwable th3) {
                th = th3;
                a = null;
                if (d != null) {
                    d.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (JSONException e3) {
            cursor = null;
            a = null;
        } catch (Throwable th4) {
            th = th4;
            a = null;
            d = null;
            if (d != null) {
                d.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
        if (a != null) {
            a.close();
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    private static JSONObject a(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        while (cursor.moveToNext()) {
            jSONObject.put(cursor.getString(0), cursor.getString(1));
        }
        return jSONObject;
    }

    private void a(String str) {
        if (e.c(str)) {
            e.a(str);
        } else {
            this.c.a(str);
        }
    }

    private JSONObject d() {
        Cursor e;
        Cursor cursor;
        Throwable th;
        Cursor f;
        try {
            f = this.c.f();
            try {
                e = this.c.e();
                try {
                    JSONArray jSONArray;
                    Object obj;
                    Object a;
                    JSONObject jSONObject;
                    if (f.getCount() <= 0 || e.getCount() <= 0) {
                        jSONArray = null;
                        obj = null;
                    } else {
                        JSONObject a2 = a(f);
                        jSONArray = new JSONArray();
                        e.moveToPosition(-1);
                        while (e.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("id", e.getString(c.a.a));
                            jSONObject2.put("token_id", e.getString(c.b.a));
                            jSONObject2.put("type", e.getString(c.d.a));
                            jSONObject2.put("time", r.a(e.getDouble(c.e.a)));
                            jSONObject2.put("session_time", r.a(e.getDouble(c.f.a)));
                            jSONObject2.put("session_id", e.getString(c.g.a));
                            String string = e.getString(c.h.a);
                            jSONObject2.put("data", string != null ? new JSONObject(string) : new JSONObject());
                            jSONObject2.put("attempt", e.getString(c.i.a));
                            jSONArray.put(jSONObject2);
                        }
                        obj = a2;
                    }
                    if (a.h(this.b)) {
                        JSONArray a3 = e.a(this.b);
                        if (a3 != null && a3.length() > 0) {
                            int i = 0;
                            if (a3 != null) {
                                i = a3.length() + 0;
                            }
                            if (jSONArray != null) {
                                i += jSONArray.length();
                            }
                            a = a(a3, jSONArray, i);
                            if (a == null) {
                                jSONObject = new JSONObject();
                                if (obj != null) {
                                    jSONObject.put("tokens", obj);
                                }
                                jSONObject.put(EventsEntry.TABLE_NAME, a);
                            } else {
                                jSONObject = null;
                            }
                            if (f != null) {
                                f.close();
                            }
                            if (e != null) {
                                return jSONObject;
                            }
                            e.close();
                            return jSONObject;
                        }
                    }
                    JSONArray a4 = jSONArray;
                    if (a4 == null) {
                        jSONObject = null;
                    } else {
                        jSONObject = new JSONObject();
                        if (obj != null) {
                            jSONObject.put("tokens", obj);
                        }
                        jSONObject.put(EventsEntry.TABLE_NAME, a4);
                    }
                    if (f != null) {
                        f.close();
                    }
                    if (e != null) {
                        return jSONObject;
                    }
                    e.close();
                    return jSONObject;
                } catch (JSONException e2) {
                    cursor = e;
                    e = f;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (JSONException e3) {
                cursor = null;
                e = f;
            } catch (Throwable th3) {
                th = th3;
                e = null;
                if (f != null) {
                    f.close();
                }
                if (e != null) {
                    e.close();
                }
                throw th;
            }
        } catch (JSONException e4) {
            cursor = null;
            e = null;
        } catch (Throwable th4) {
            th = th4;
            e = null;
            f = null;
            if (f != null) {
                f.close();
            }
            if (e != null) {
                e.close();
            }
            throw th;
        }
        if (e != null) {
            e.close();
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public final JSONObject a() {
        int n = a.n(this.b);
        return n > 0 ? a(n) : d();
    }

    public final boolean a(JSONArray jSONArray) {
        boolean h = a.h(this.b);
        boolean z = true;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                int i2 = jSONObject.getInt("code");
                if (i2 == 1) {
                    if (!this.c.b(string) && h) {
                        e.b(string);
                    }
                } else if (i2 >= Constants.ONE_SECOND && i2 < 2000) {
                    a(string);
                    z = false;
                } else if (i2 >= 2000 && i2 < LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS && !this.c.b(string) && h) {
                    e.b(string);
                }
            } catch (JSONException e) {
            }
        }
        return z;
    }

    public final void b() {
        this.c.g();
        this.c.b();
        e.c(this.b);
    }

    public final void b(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                a(jSONArray.getJSONObject(i).getString("id"));
            } catch (JSONException e) {
            }
        }
    }

    public final boolean c() {
        boolean z = false;
        int n = a.n(this.b);
        if (n > 0) {
            Cursor cursor = null;
            try {
                cursor = this.c.d();
                if ((cursor.moveToFirst() ? cursor.getInt(0) : 0) + e.b(this.b) > n) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return z;
    }
}
