package org.b.a.c;

import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.Map;
import org.b.a.d;
import org.b.a.e;
import org.b.a.f;

public class i implements e {
    public Object a(d interp, f self, Object o, Object property, String propertyName) throws r {
        String value;
        Map<?, ?> map = (Map) o;
        if (property != null) {
            if (property.equals("keys")) {
                value = map.keySet();
            } else if (property.equals("values")) {
                value = map.values();
            } else if (map.containsKey(property)) {
                value = map.get(property);
            } else if (map.containsKey(propertyName)) {
                value = map.get(propertyName);
            }
            if (value != PropertiesEntry.COLUMN_NAME_KEY) {
                return property;
            }
            return value;
        }
        value = map.get("default");
        if (value != PropertiesEntry.COLUMN_NAME_KEY) {
            return value;
        }
        return property;
    }
}
