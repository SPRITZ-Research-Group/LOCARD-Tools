package com.microsoft.react.push;

import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class c implements d, Serializable {
    private Map<String, List<b>> b = new HashMap();

    static c a(al array) {
        c actions = new c();
        for (int i = 0; i < array.size(); i++) {
            if (array.getType(i) == ReadableType.Map) {
                am map = array.getMap(i);
                if (map.hasKey("identifier")) {
                    String category = map.getString("identifier");
                    if (map.hasKey("actions") && map.getType("actions") == ReadableType.Array) {
                        al array2 = map.getArray("actions");
                        int i2 = 0;
                        while (true) {
                            int i3 = i2;
                            if (i3 >= array2.size()) {
                                break;
                            }
                            if (array2.getType(i3) == ReadableType.Map) {
                                b a = b.a(array2.getMap(i3));
                                List list = (List) actions.b.get(category);
                                if (list == null) {
                                    list = new ArrayList();
                                    actions.b.put(category, list);
                                }
                                list.add(a);
                            }
                            i2 = i3 + 1;
                        }
                    }
                }
            }
        }
        return actions;
    }

    final List<b> a(String notificationCategory) {
        return (List) this.b.get(notificationCategory);
    }
}
