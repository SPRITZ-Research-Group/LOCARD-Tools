package com.applovin.impl.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class aa {
    static String a(Collection<String> collection, int i) {
        return a(collection, ",", i);
    }

    static String a(Collection<String> collection, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("No glue specified");
        } else if (collection == null || collection.size() <= 0) {
            return "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            int i2 = 0;
            for (String str2 : collection) {
                if (i2 >= i) {
                    break;
                }
                i2++;
                stringBuilder.append(str2);
                stringBuilder.append(str);
            }
            if (stringBuilder.length() > str.length()) {
                stringBuilder.setLength(stringBuilder.length() - str.length());
            }
            return stringBuilder.toString();
        }
    }

    public static List<String> a(String str) {
        return a(str, ",\\s*");
    }

    public static List<String> a(String str, String str2) {
        return TextUtils.isEmpty(str) ? Collections.emptyList() : Arrays.asList(str.split(str2));
    }

    static List<String> a(List<String> list) {
        if (list == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String trim : list) {
            CharSequence trim2 = trim.trim();
            if (!TextUtils.isEmpty(trim2)) {
                arrayList.add(trim2);
            }
        }
        return arrayList;
    }
}
