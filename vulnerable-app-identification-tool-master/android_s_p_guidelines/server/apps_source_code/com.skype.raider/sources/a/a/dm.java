package a.a;

import com.appboy.e.b.a;
import com.appboy.f.c;
import java.util.Date;
import org.json.JSONObject;

public class dm implements di {
    private static final String a = c.a(dm.class);
    private dz b;
    private String c;
    private int d;
    private Object e;

    public final /* synthetic */ Object h() {
        return a();
    }

    private dm(dz dzVar, String str, int i) {
        this.b = dzVar;
        this.c = str;
        this.d = i;
    }

    protected dm(JSONObject jSONObject) {
        this((dz) cv.a(jSONObject, "property_type", dz.class, dz.UNKNOWN), jSONObject.getString("property_key"), jSONObject.getInt("comparator"));
        if (!jSONObject.has("property_value")) {
            return;
        }
        if (this.b.equals(dz.STRING)) {
            this.e = jSONObject.getString("property_value");
        } else if (this.b.equals(dz.BOOLEAN)) {
            this.e = Boolean.valueOf(jSONObject.getBoolean("property_value"));
        } else if (this.b.equals(dz.NUMBER)) {
            this.e = Double.valueOf(jSONObject.getDouble("property_value"));
        } else if (this.b.equals(dz.DATE)) {
            this.e = Long.valueOf(jSONObject.getLong("property_value"));
        }
    }

    public final boolean a(ed edVar) {
        if (!(edVar instanceof ee)) {
            return false;
        }
        a f = ((ee) edVar).f();
        Object obj = null;
        if (f != null) {
            try {
                obj = f.b().opt(this.c);
            } catch (Throwable e) {
                c.d(a, "Caught exception checking property filter condition.", e);
                return false;
            }
        }
        if (obj == null) {
            return this.d == 12 || this.d == 17 || this.d == 2;
        } else {
            if (this.d == 11) {
                return true;
            }
            if (this.d == 12) {
                return false;
            }
            switch (this.b) {
                case STRING:
                    if (!(obj instanceof String)) {
                        return this.d == 2 || this.d == 17;
                    } else {
                        switch (this.d) {
                            case 1:
                                return obj.equals(this.e);
                            case 2:
                                return !obj.equals(this.e);
                            case 10:
                                return ((String) obj).matches((String) this.e);
                            case 17:
                                return !((String) obj).matches((String) this.e);
                            default:
                                return false;
                        }
                    }
                case BOOLEAN:
                    if (!(obj instanceof Boolean)) {
                        return this.d == 2;
                    } else {
                        switch (this.d) {
                            case 1:
                                return obj.equals(this.e);
                            case 2:
                                return !obj.equals(this.e);
                            default:
                                return false;
                        }
                    }
                case DATE:
                    Date a;
                    long c = edVar.c();
                    if (obj instanceof String) {
                        a = co.a((String) obj, fq.LONG);
                    } else {
                        a = null;
                    }
                    if (a == null) {
                        return this.d == 2;
                    } else {
                        long a2 = co.a(a);
                        long longValue = ((Number) this.e).longValue();
                        switch (this.d) {
                            case 1:
                                return a2 == longValue;
                            case 2:
                                return a2 != longValue;
                            case 3:
                                return a2 > longValue;
                            case 4:
                                return a2 >= c - longValue;
                            case 5:
                                return a2 < longValue;
                            case 6:
                                return a2 <= c - longValue;
                            case 15:
                                return a2 < longValue + c;
                            case 16:
                                return a2 > longValue + c;
                            default:
                                return false;
                        }
                    }
                case NUMBER:
                    if (!(obj instanceof Integer) && !(obj instanceof Double)) {
                        return this.d == 2;
                    } else {
                        double doubleValue = ((Number) obj).doubleValue();
                        double doubleValue2 = ((Number) this.e).doubleValue();
                        switch (this.d) {
                            case 1:
                                return doubleValue == doubleValue2;
                            case 2:
                                return doubleValue != doubleValue2;
                            case 3:
                                return doubleValue > doubleValue2;
                            case 5:
                                return doubleValue < doubleValue2;
                            default:
                                return false;
                        }
                    }
                default:
                    return false;
            }
        }
    }

    private JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!this.b.equals(dz.UNKNOWN)) {
                jSONObject.put("property_type", this.b.toString());
            }
            jSONObject.put("property_key", this.c);
            jSONObject.put("comparator", this.d);
            jSONObject.put("property_value", this.e);
        } catch (Throwable e) {
            c.d(a, "Caught exception creating property filter Json.", e);
        }
        return jSONObject;
    }
}
