package com.appboy.a;

import a.a.fi;
import android.content.Context;
import com.appboy.f.c;
import com.appboy.f.g;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static final String c = c.a(b.class);
    protected final Map<String, Object> a = Collections.synchronizedMap(new HashMap());
    protected final fi b;
    private final Context d;

    public b(Context context) {
        this.d = context;
        this.b = new fi(context);
    }

    protected final boolean a(String key, boolean defaultValue) {
        if (this.a.containsKey(key)) {
            return ((Boolean) this.a.get(key)).booleanValue();
        }
        boolean a;
        if (this.b.a(key)) {
            a = this.b.a(key, defaultValue);
            this.a.put(key, Boolean.valueOf(a));
            c.b(c, "Using runtime override value for key: " + key + " and value: " + a);
            return a;
        }
        a = b(key, defaultValue);
        this.a.put(key, Boolean.valueOf(a));
        c.b(c, "Defaulting to using xml value for key: " + key + " and value: " + a);
        return a;
    }

    protected final String a(String key, String defaultValue) {
        if (this.a.containsKey(key)) {
            return (String) this.a.get(key);
        }
        String a;
        if (this.b.a(key)) {
            a = this.b.a(key, defaultValue);
            this.a.put(key, a);
            c.b(c, "Using runtime override value for key: " + key + " and value: " + a);
            return a;
        }
        a = b(key, defaultValue);
        this.a.put(key, a);
        c.b(c, "Defaulting to using xml value for key: " + key + " and value: " + a);
        return a;
    }

    protected final int a(String key, int defaultValue) {
        if (this.a.containsKey(key)) {
            return ((Integer) this.a.get(key)).intValue();
        }
        int a;
        if (this.b.a(key)) {
            a = this.b.a(key, defaultValue);
            this.a.put(key, Integer.valueOf(a));
            c.b(c, "Using runtime override value for key: " + key + " and value: " + a);
            return a;
        }
        a = b(key, defaultValue);
        this.a.put(key, Integer.valueOf(a));
        c.b(c, "Defaulting to using xml value for key: " + key + " and value: " + a);
        return a;
    }

    private int b(String key, int defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.d.getResources().getIdentifier(key, "integer", g.a(this.d));
            if (identifier != 0) {
                return this.d.getResources().getInteger(identifier);
            }
            c.b(c, "Unable to find the xml integer configuration value with key " + key + ". Using default value '" + defaultValue + "'.");
            return defaultValue;
        } catch (Exception e) {
            c.b(c, "Unexpected exception retrieving the xml integer configuration value with key " + key + ". Using default value " + defaultValue + "'.");
            return defaultValue;
        }
    }

    private boolean b(String key, boolean defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.d.getResources().getIdentifier(key, "bool", g.a(this.d));
            if (identifier != 0) {
                return this.d.getResources().getBoolean(identifier);
            }
            c.b(c, "Unable to find the xml boolean configuration value with key " + key + ". Using default value '" + defaultValue + "'.");
            return defaultValue;
        } catch (Exception e) {
            c.b(c, "Unexpected exception retrieving the xml boolean configuration value with key " + key + ". Using default value " + defaultValue + "'.");
            return defaultValue;
        }
    }

    protected final String b(String key, String defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.d.getResources().getIdentifier(key, "string", g.a(this.d));
            if (identifier != 0) {
                return this.d.getResources().getString(identifier);
            }
            c.b(c, "Unable to find the xml string configuration value with key " + key + ". Using default value '" + defaultValue + "'.");
            return defaultValue;
        } catch (Exception e) {
            c.b(c, "Unexpected exception retrieving the xml string configuration value with key " + key + ". Using default value " + defaultValue + "'.");
            return defaultValue;
        }
    }

    protected final String[] a(String key) {
        try {
            int identifier = this.d.getResources().getIdentifier(key, "array", g.a(this.d));
            if (identifier != 0) {
                return this.d.getResources().getStringArray(identifier);
            }
            c.b(c, "Unable to find the xml string array configuration value with key " + key + ". Using default value '" + Arrays.toString(null) + "'.");
            return null;
        } catch (Exception e) {
            c.b(c, "Unexpected exception retrieving the xml string array configuration value with key " + key + ". Using default value " + Arrays.toString(null) + "'.");
            return null;
        }
    }
}
