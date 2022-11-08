package com.facebook.ads.internal.p.a;

import com.adjust.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class p implements Map<String, String> {
    private Map<String, String> a = new HashMap();

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.a.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(str);
            String str2 = (String) this.a.get(str2);
            if (str2 != null) {
                stringBuilder.append("=");
                try {
                    stringBuilder.append(URLEncoder.encode(str2, Constants.ENCODING));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    public final String a(String str, String str2) {
        return (String) this.a.put(str, str2);
    }

    public final byte[] a() {
        byte[] bArr = null;
        try {
            return b().getBytes(Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public final void clear() {
        this.a.clear();
    }

    public final boolean containsKey(Object obj) {
        return this.a.containsKey(obj);
    }

    public final boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    public final Set<Entry<String, String>> entrySet() {
        return this.a.entrySet();
    }

    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    public final Set<String> keySet() {
        return this.a.keySet();
    }

    public final /* synthetic */ Object put(Object obj, Object obj2) {
        return a((String) obj, (String) obj2);
    }

    public final void putAll(Map<? extends String, ? extends String> map) {
        this.a.putAll(map);
    }

    public final int size() {
        return this.a.size();
    }

    public final Collection<String> values() {
        return this.a.values();
    }

    public final /* synthetic */ Object remove(Object obj) {
        return (String) this.a.remove(obj);
    }

    public final /* synthetic */ Object get(Object obj) {
        return (String) this.a.get(obj);
    }
}
