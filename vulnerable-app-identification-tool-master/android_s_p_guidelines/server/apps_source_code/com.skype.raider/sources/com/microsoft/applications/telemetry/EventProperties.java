package com.microsoft.applications.telemetry;

import com.microsoft.applications.telemetry.core.BuildConfig;
import com.microsoft.applications.telemetry.core.Log;
import com.microsoft.applications.telemetry.core.Preconditions;
import com.microsoft.applications.telemetry.core.TraceHelper;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONObject;

public class EventProperties {
    private static final String LOG_TAG = ("[ACT]:" + EventProperties.class.getSimpleName());
    private EventPriority eventPriority;
    private boolean hasCustomProperties;
    private String name;
    private HashMap<String, PiiKind> pii;
    private HashMap<String, String> properties;
    private HashMap<String, Boolean> propertiesBoolean;
    private HashMap<String, Date> propertiesDate;
    private HashMap<String, Double> propertiesDouble;
    private HashMap<String, PropertyType> propertiesKey;
    private HashMap<String, Long> propertiesLong;
    private HashMap<String, UUID> propertiesUUID;
    private Object propertyLock;
    private Date timestamp;
    private String type;

    private enum PropertyType {
        STRING(0),
        LONG(1),
        DOUBLE(2),
        BOOLEAN(3),
        DATE(4),
        UUID(5);
        
        private final int type;

        private PropertyType(int value) {
            this.type = value;
        }

        public final int getValue() {
            return this.type;
        }
    }

    public EventProperties(String eventName) {
        this.propertyLock = new Object();
        this.hasCustomProperties = false;
        this.properties = new HashMap();
        this.propertiesDouble = new HashMap();
        this.propertiesLong = new HashMap();
        this.propertiesBoolean = new HashMap();
        this.propertiesDate = new HashMap();
        this.propertiesUUID = new HashMap();
        this.propertiesKey = new HashMap();
        this.pii = new HashMap();
        this.eventPriority = EventPriority.NORMAL;
        setName(eventName);
    }

    public EventProperties(String eventName, Map<String, String> customProperties) {
        this(eventName);
        if (customProperties != null) {
            for (Entry<String, String> kvp : customProperties.entrySet()) {
                setProperty((String) kvp.getKey(), (String) kvp.getValue());
            }
        }
    }

    public EventProperties(String eventName, Map<String, String> customStringProperties, Map<String, Double> customDoubleProperties) {
        this(eventName, customStringProperties);
        if (customDoubleProperties != null) {
            for (Entry<String, Double> kvp : customDoubleProperties.entrySet()) {
                setProperty((String) kvp.getKey(), ((Double) kvp.getValue()).doubleValue());
            }
        }
    }

    public EventProperties(String eventName, Map<String, String> customStringProperties, Map<String, Double> customDoubleProperties, Map<String, Long> customLongProperties, Map<String, Boolean> customBooleanProperties, Map<String, Date> customDateProperties, Map<String, UUID> customUUIDProperties) {
        this(eventName, customStringProperties, customDoubleProperties);
        if (customLongProperties != null) {
            for (Entry<String, Long> kvp : customLongProperties.entrySet()) {
                setProperty((String) kvp.getKey(), ((Long) kvp.getValue()).longValue());
            }
        }
        if (customBooleanProperties != null) {
            for (Entry<String, Boolean> kvp2 : customBooleanProperties.entrySet()) {
                setProperty((String) kvp2.getKey(), ((Boolean) kvp2.getValue()).booleanValue());
            }
        }
        if (customDateProperties != null) {
            for (Entry<String, Date> kvp3 : customDateProperties.entrySet()) {
                setProperty((String) kvp3.getKey(), (Date) kvp3.getValue());
            }
        }
        if (customUUIDProperties != null) {
            for (Entry<String, UUID> kvp4 : customUUIDProperties.entrySet()) {
                setProperty((String) kvp4.getKey(), (UUID) kvp4.getValue());
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setTimestamp(Date timestamp) {
        try {
            Preconditions.isNotNull(timestamp, "timestamp cannot be null");
            this.timestamp = timestamp;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setPriority(EventPriority priority) {
        if (priority == null || priority == EventPriority.UNSPECIFIED) {
            this.eventPriority = EventPriority.NORMAL;
        } else {
            this.eventPriority = priority;
        }
    }

    public EventPriority getPriority() {
        return this.eventPriority;
    }

    public void setProperty(String key, double value) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, PiiKind.NONE)) {
                removeKeysFromMaps(key);
                this.propertiesDouble.put(key, Double.valueOf(value));
                this.pii.remove(key);
                this.propertiesKey.put(key, PropertyType.DOUBLE);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, double value, PiiKind piiKind) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, piiKind)) {
                removeKeysFromMaps(key);
                this.propertiesDouble.put(key, Double.valueOf(value));
                if (piiKind != PiiKind.NONE) {
                    this.pii.put(key, piiKind);
                } else {
                    this.pii.remove(key);
                }
                this.propertiesKey.put(key, PropertyType.DOUBLE);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, String value) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, PiiKind.NONE)) {
                removeKeysFromMaps(key);
                this.properties.put(key, value);
                this.pii.remove(key);
                this.propertiesKey.put(key, PropertyType.STRING);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, String value, PiiKind piiKind) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, piiKind)) {
                removeKeysFromMaps(key);
                this.properties.put(key, value);
                if (piiKind != PiiKind.NONE) {
                    this.pii.put(key, piiKind);
                } else {
                    this.pii.remove(key);
                }
                this.propertiesKey.put(key, PropertyType.STRING);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, long value) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, PiiKind.NONE)) {
                removeKeysFromMaps(key);
                this.propertiesLong.put(key, Long.valueOf(value));
                this.pii.remove(key);
                this.propertiesKey.put(key, PropertyType.LONG);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, long value, PiiKind piiKind) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, piiKind)) {
                removeKeysFromMaps(key);
                this.propertiesLong.put(key, Long.valueOf(value));
                if (piiKind != PiiKind.NONE) {
                    this.pii.put(key, piiKind);
                } else {
                    this.pii.remove(key);
                }
                this.propertiesKey.put(key, PropertyType.LONG);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, boolean value) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, PiiKind.NONE)) {
                removeKeysFromMaps(key);
                this.propertiesBoolean.put(key, Boolean.valueOf(value));
                this.pii.remove(key);
                this.propertiesKey.put(key, PropertyType.BOOLEAN);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, boolean value, PiiKind piiKind) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, piiKind)) {
                removeKeysFromMaps(key);
                this.propertiesBoolean.put(key, Boolean.valueOf(value));
                if (piiKind != PiiKind.NONE) {
                    this.pii.put(key, piiKind);
                } else {
                    this.pii.remove(key);
                }
                this.propertiesKey.put(key, PropertyType.BOOLEAN);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, Date value) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, PiiKind.NONE) && isObjectNotNull(value)) {
                removeKeysFromMaps(key);
                this.propertiesDate.put(key, value);
                this.pii.remove(key);
                this.propertiesKey.put(key, PropertyType.DATE);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, Date value, PiiKind piiKind) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, piiKind) && isObjectNotNull(value)) {
                removeKeysFromMaps(key);
                this.propertiesDate.put(key, value);
                if (piiKind != PiiKind.NONE) {
                    this.pii.put(key, piiKind);
                } else {
                    this.pii.remove(key);
                }
                this.propertiesKey.put(key, PropertyType.DATE);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, UUID value) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, PiiKind.NONE) && isObjectNotNull(value)) {
                removeKeysFromMaps(key);
                this.propertiesUUID.put(key, value);
                this.pii.remove(key);
                this.propertiesKey.put(key, PropertyType.UUID);
                this.hasCustomProperties = true;
            }
        }
    }

    public void setProperty(String key, UUID value, PiiKind piiKind) {
        synchronized (this.propertyLock) {
            if (isValidKeyAndPiiKind(key, piiKind) && isObjectNotNull(value)) {
                removeKeysFromMaps(key);
                this.propertiesUUID.put(key, value);
                if (piiKind != PiiKind.NONE) {
                    this.pii.put(key, piiKind);
                } else {
                    this.pii.remove(key);
                }
                this.propertiesKey.put(key, PropertyType.UUID);
                this.hasCustomProperties = true;
            }
        }
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(this.properties);
    }

    public Map<String, Double> getPropertiesDouble() {
        return Collections.unmodifiableMap(this.propertiesDouble);
    }

    public Map<String, Long> getPropertiesLong() {
        return Collections.unmodifiableMap(this.propertiesLong);
    }

    public Map<String, Boolean> getPropertiesBoolean() {
        return Collections.unmodifiableMap(this.propertiesBoolean);
    }

    public Map<String, Date> getPropertiesDate() {
        return Collections.unmodifiableMap(this.propertiesDate);
    }

    public Map<String, UUID> getPropertiesUUID() {
        return Collections.unmodifiableMap(this.propertiesUUID);
    }

    public Map<String, PiiKind> getPII() {
        return Collections.unmodifiableMap(this.pii);
    }

    public boolean hasCustomProperties() {
        return this.hasCustomProperties;
    }

    public String removeProperty(String key) {
        String result = (String) this.properties.get(key);
        if (result == null) {
            return "";
        }
        this.properties.remove(key);
        if (!this.pii.containsKey(key)) {
            return result;
        }
        this.pii.remove(key);
        return result;
    }

    public JSONObject toJSONObject() {
        JSONObject result = new JSONObject(this.properties);
        try {
            result.put("name", this.name);
        } catch (Exception ex) {
            Log.wtf(LOG_TAG, "JSON exception:", ex);
        }
        return result;
    }

    public static EventProperties from(JSONObject obj) {
        Exception ex;
        EventProperties result = null;
        try {
            Log.d(LOG_TAG, "... stripping name");
            String name = obj.getString("name");
            obj.remove("name");
            Log.d(LOG_TAG, "... creating EventProperties");
            EventProperties result2 = new EventProperties(name);
            try {
                if (obj.names() != null) {
                    for (int i = 0; i < obj.names().length(); i++) {
                        String key = obj.names().getString(i);
                        String val = obj.getString(key);
                        if (!(key == null || key.startsWith("pii."))) {
                            Log.d(LOG_TAG, "... setProperty " + key + "=" + val);
                            result2.setProperty(key, val, PiiKind.fromValue(obj.optInt("pii." + key, 0)));
                        }
                    }
                    return result2;
                }
                Log.w(LOG_TAG, "Object contains no mappings other than name!");
                return result2;
            } catch (Exception e) {
                ex = e;
                result = result2;
                Log.wtf(LOG_TAG, "Unable to convert json to EventProperties:", ex);
                return result;
            }
        } catch (Exception e2) {
            ex = e2;
            Log.wtf(LOG_TAG, "Unable to convert json to EventProperties:", ex);
            return result;
        }
    }

    public void mergeProperties(EventProperties properties) {
        for (Entry<String, String> kvp : properties.getProperties().entrySet()) {
            if (!this.propertiesKey.containsKey(kvp.getKey())) {
                if (properties.getPII().containsKey(kvp.getKey())) {
                    setProperty((String) kvp.getKey(), (String) kvp.getValue(), (PiiKind) properties.getPII().get(kvp.getKey()));
                } else {
                    setProperty((String) kvp.getKey(), (String) kvp.getValue());
                }
            }
        }
        for (Entry<String, Double> kvp2 : properties.getPropertiesDouble().entrySet()) {
            if (!this.propertiesKey.containsKey(kvp2.getKey())) {
                if (properties.getPII().containsKey(kvp2.getKey())) {
                    setProperty((String) kvp2.getKey(), ((Double) kvp2.getValue()).doubleValue(), (PiiKind) properties.getPII().get(kvp2.getKey()));
                } else {
                    setProperty((String) kvp2.getKey(), ((Double) kvp2.getValue()).doubleValue());
                }
            }
        }
        for (Entry<String, Long> kvp3 : properties.getPropertiesLong().entrySet()) {
            if (!this.propertiesKey.containsKey(kvp3.getKey())) {
                if (properties.getPII().containsKey(kvp3.getKey())) {
                    setProperty((String) kvp3.getKey(), ((Long) kvp3.getValue()).longValue(), (PiiKind) properties.getPII().get(kvp3.getKey()));
                } else {
                    setProperty((String) kvp3.getKey(), ((Long) kvp3.getValue()).longValue());
                }
            }
        }
        for (Entry<String, Boolean> kvp4 : properties.getPropertiesBoolean().entrySet()) {
            if (!this.propertiesKey.containsKey(kvp4.getKey())) {
                if (properties.getPII().containsKey(kvp4.getKey())) {
                    setProperty((String) kvp4.getKey(), ((Boolean) kvp4.getValue()).booleanValue(), (PiiKind) properties.getPII().get(kvp4.getKey()));
                } else {
                    setProperty((String) kvp4.getKey(), ((Boolean) kvp4.getValue()).booleanValue());
                }
            }
        }
        for (Entry<String, Date> kvp5 : properties.getPropertiesDate().entrySet()) {
            if (!this.propertiesKey.containsKey(kvp5.getKey())) {
                if (properties.getPII().containsKey(kvp5.getKey())) {
                    setProperty((String) kvp5.getKey(), (Date) kvp5.getValue(), (PiiKind) properties.getPII().get(kvp5.getKey()));
                } else {
                    setProperty((String) kvp5.getKey(), (Date) kvp5.getValue());
                }
            }
        }
        for (Entry<String, UUID> kvp6 : properties.getPropertiesUUID().entrySet()) {
            if (!this.propertiesKey.containsKey(kvp6.getKey())) {
                if (properties.getPII().containsKey(kvp6.getKey())) {
                    setProperty((String) kvp6.getKey(), (UUID) kvp6.getValue(), (PiiKind) properties.getPII().get(kvp6.getKey()));
                } else {
                    setProperty((String) kvp6.getKey(), (UUID) kvp6.getValue());
                }
            }
        }
    }

    private boolean isValidKeyAndPiiKind(String key, PiiKind piiKind) {
        try {
            Preconditions.validateKey(key, "key is invalid. it does not match the regex^[a-zA-Z0-9](([a-zA-Z0-9|_|\\.]){0,98}[a-zA-Z0-9])?$");
            Preconditions.isNotNull(piiKind, "piiKind cannot be null.");
            return true;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
            return false;
        }
    }

    private boolean isObjectNotNull(Object object) {
        try {
            Preconditions.isNotNull(object, "Value cannot be null.");
            return true;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
            return false;
        }
    }

    private void removeKeysFromMaps(String key) {
        if (this.propertiesKey.containsKey(key)) {
            switch ((PropertyType) this.propertiesKey.get(key)) {
                case STRING:
                    this.properties.remove(key);
                    break;
                case LONG:
                    this.propertiesLong.remove(key);
                    break;
                case DOUBLE:
                    this.propertiesDouble.remove(key);
                    break;
                case BOOLEAN:
                    this.propertiesBoolean.remove(key);
                    break;
                case DATE:
                    this.propertiesDate.remove(key);
                    break;
                case UUID:
                    this.propertiesUUID.remove(key);
                    break;
            }
            this.propertiesKey.remove(key);
        }
    }
}
