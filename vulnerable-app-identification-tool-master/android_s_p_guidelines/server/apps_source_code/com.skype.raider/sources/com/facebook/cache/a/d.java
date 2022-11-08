package com.facebook.cache.a;

import com.adjust.sdk.Constants;
import com.facebook.common.i.c;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class d {
    public static List<String> a(c key) {
        try {
            List<String> ids;
            if (key instanceof e) {
                List<c> keys = ((e) key).a;
                ids = new ArrayList(keys.size());
                for (int i = 0; i < keys.size(); i++) {
                    ids.add(c((c) keys.get(i)));
                }
                return ids;
            }
            ids = new ArrayList(1);
            ids.add(c(key));
            return ids;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String b(c key) {
        try {
            if (key instanceof e) {
                return c((c) ((e) key).a.get(0));
            }
            return c(key);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String c(c key) throws UnsupportedEncodingException {
        return c.a(key.a().getBytes(Constants.ENCODING));
    }
}
