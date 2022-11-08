package com.microsoft.applications.telemetry.datamodels;

import com.microsoft.applications.telemetry.core.IEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class EventBase implements IEvent {
    private String EventType;
    private HashMap<String, String> Extension = new HashMap();
    private String Id;
    private HashMap<String, PII> PIIExtensions = new HashMap();
    private long Timestamp;
    private String Type;
    private HashMap<String, Boolean> TypedExtensionBoolean = new HashMap();
    private HashMap<String, Long> TypedExtensionDateTime = new HashMap();
    private HashMap<String, Double> TypedExtensionDouble = new HashMap();
    private HashMap<String, ArrayList<Byte>> TypedExtensionGuid = new HashMap();
    private HashMap<String, Long> TypedExtensionInt64 = new HashMap();

    public final String getId() {
        return this.Id;
    }

    public final void setId(String value) {
        this.Id = value;
    }

    public final long getTimestamp() {
        return this.Timestamp;
    }

    public final void setTimestamp(long value) {
        this.Timestamp = value;
    }

    public final String getType() {
        return this.Type;
    }

    public final void setType(String value) {
        this.Type = value;
    }

    public final String getEventType() {
        return this.EventType;
    }

    public final void setEventType(String value) {
        this.EventType = value;
    }

    public final HashMap<String, String> getExtension() {
        return this.Extension;
    }

    public final void setExtension(HashMap<String, String> value) {
        this.Extension = value;
    }

    public final HashMap<String, ArrayList<Byte>> getTypedExtensionGuid() {
        return this.TypedExtensionGuid;
    }

    public final void setTypedExtensionGuid(HashMap<String, ArrayList<Byte>> typedExtensionGuid) {
        this.TypedExtensionGuid = typedExtensionGuid;
    }

    public final void setTypedExtensionBoolean(HashMap<String, Boolean> typedExtensionBoolean) {
        this.TypedExtensionBoolean = typedExtensionBoolean;
    }

    public final HashMap<String, Boolean> getTypedExtensionBoolean() {
        return this.TypedExtensionBoolean;
    }

    public final void setTypedExtensionDateTime(HashMap<String, Long> typedExtensionDateTime) {
        this.TypedExtensionDateTime = typedExtensionDateTime;
    }

    public final HashMap<String, Long> getTypedExtensionDateTime() {
        return this.TypedExtensionDateTime;
    }

    public final void setTypedExtensionDouble(HashMap<String, Double> typedExtensionDouble) {
        this.TypedExtensionDouble = typedExtensionDouble;
    }

    public final HashMap<String, Double> getTypedExtensionDouble() {
        return this.TypedExtensionDouble;
    }

    public final void setTypedExtensionInt64(HashMap<String, Long> typedExtensionInt64) {
        this.TypedExtensionInt64 = typedExtensionInt64;
    }

    public final HashMap<String, Long> getTypedExtensionInt64() {
        return this.TypedExtensionInt64;
    }

    public final void setPIIExtensions(HashMap<String, PII> value) {
        this.PIIExtensions = value;
    }

    public final HashMap<String, PII> getPIIExtensions() {
        return this.PIIExtensions;
    }
}
