package com.skype.android.video.hw.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncoderAttributes implements Serializable {
    private static final Pattern parsePattern = Pattern.compile("(\\S+)\\s+(\\S+)\\s*");
    private static final Pattern replacePattern = Pattern.compile("-");
    private static final long serialVersionUID = 9100767063079886218L;
    private final Map<String, String> map = new HashMap();

    public static class AttributeException extends Exception {
        private static final long serialVersionUID = 1;

        public AttributeException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public AttributeException(String detailMessage) {
            super(detailMessage);
        }
    }

    public static class IllegalAttributeValueException extends AttributeException {
        private static final long serialVersionUID = 1;

        public IllegalAttributeValueException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }
    }

    public static class NoSuchAttributeException extends AttributeException {
        private static final long serialVersionUID = 1;

        public NoSuchAttributeException(String detailMessage) {
            super(detailMessage);
        }
    }

    private static native Object getObjFromNativePtr(long j);

    private static String convertKey(String str) {
        return replacePattern.matcher(str).replaceAll("_").toLowerCase();
    }

    public EncoderAttributes(String str) {
        Matcher matcher = parsePattern.matcher(str);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            if (value.endsWith("\"") && value.startsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }
            this.map.put(convertKey(key), value);
        }
    }

    public String put(String key, Object value) {
        return (String) this.map.put(key, String.valueOf(value));
    }

    public String getString(String key) throws NoSuchAttributeException {
        String value = (String) this.map.get(convertKey(key));
        if (value != null) {
            return value;
        }
        throw new NoSuchAttributeException(key);
    }

    public String getString(String key, String defaultVal) {
        String value = (String) this.map.get(convertKey(key));
        if (value == null || value.length() == 0) {
            return defaultVal;
        }
        return value;
    }

    public Integer getInteger(String key) throws NoSuchAttributeException, IllegalAttributeValueException {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            throw new NoSuchAttributeException(key);
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalAttributeValueException(key, e);
        }
    }

    public int getInteger(String key, int defaultVal) {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            return defaultVal;
        }
        try {
            return Integer.valueOf(value).intValue();
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public Long getLong(String key) throws NoSuchAttributeException, IllegalAttributeValueException {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            throw new NoSuchAttributeException(key);
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalAttributeValueException(key, e);
        }
    }

    public long getLong(String key, long defaultVal) {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            return defaultVal;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public Double getFloat(String key) throws NoSuchAttributeException, IllegalAttributeValueException {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            throw new NoSuchAttributeException(key);
        }
        try {
            return Double.valueOf(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            throw new IllegalAttributeValueException(key, e);
        }
    }

    public double getFloat(String key, double defaultVal) {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            return defaultVal;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public Object getPointer(String key) throws NoSuchAttributeException, IllegalAttributeValueException {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            throw new NoSuchAttributeException(key);
        }
        try {
            if (value.startsWith("0x")) {
                return getObjFromNativePtr(Long.valueOf(value.substring(2), 16).longValue());
            }
            throw new NumberFormatException("'" + value + "' does not denote a pointer");
        } catch (NumberFormatException e) {
            throw new IllegalAttributeValueException(key, e);
        }
    }

    public Object getPointer(String key, Object defaultVal) {
        String value = (String) this.map.get(convertKey(key));
        if (value == null) {
            return defaultVal;
        }
        try {
            if (value.startsWith("0x")) {
                return getObjFromNativePtr(Long.valueOf(value.substring(2), 16).longValue());
            }
            throw new NumberFormatException("'" + value + "' does not denote a pointer");
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public int hashCode() {
        return (this.map == null ? 0 : this.map.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EncoderAttributes other = (EncoderAttributes) obj;
        if (this.map == null) {
            if (other.map != null) {
                return false;
            }
            return true;
        } else if (this.map.equals(other.map)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Entry<String, String> entry : this.map.entrySet()) {
            str.append((String) entry.getKey());
            str.append(' ');
            str.append((String) entry.getValue());
            str.append(' ');
        }
        return str.toString();
    }
}
