package com.microsoft.applications.telemetry.datamodels;

import com.microsoft.a.a;
import com.microsoft.a.a.b;
import com.microsoft.a.c;
import com.microsoft.a.d;
import com.microsoft.a.g;
import com.microsoft.a.h;
import com.microsoft.a.j;
import com.microsoft.a.k;
import com.microsoft.a.n;
import com.microsoft.a.o;
import com.microsoft.a.p;
import com.microsoft.a.q;
import com.microsoft.applications.telemetry.core.IEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Record implements c, d, IEvent {
    private String EventType;
    private HashMap<String, String> Extension;
    private String Id;
    private HashMap<String, PII> PIIExtensions;
    private RecordType RecordType;
    private long Timestamp;
    private String Type;
    private HashMap<String, Boolean> TypedExtensionBoolean;
    private HashMap<String, Long> TypedExtensionDateTime;
    private HashMap<String, Double> TypedExtensionDouble;
    private HashMap<String, ArrayList<Byte>> TypedExtensionGuid;
    private HashMap<String, Long> TypedExtensionInt64;

    public static class Schema {
        private static final h EventType_metadata;
        private static final h Extension_metadata;
        private static final h Id_metadata;
        private static final h PIIExtensions_metadata;
        private static final h RecordType_metadata;
        private static final h Timestamp_metadata;
        private static final h Type_metadata;
        private static final h TypedExtensionBoolean_metadata;
        private static final h TypedExtensionDateTime_metadata;
        private static final h TypedExtensionDouble_metadata;
        private static final h TypedExtensionGuid_metadata;
        private static final h TypedExtensionInt64_metadata;
        public static final h metadata;
        public static final o schemaDef;

        static {
            h hVar = new h();
            metadata = hVar;
            hVar.a("Record");
            metadata.b("com.microsoft.applications.telemetry.datamodels.Record");
            hVar = new h();
            Id_metadata = hVar;
            hVar.a("Id");
            Id_metadata.a().f();
            hVar = new h();
            Timestamp_metadata = hVar;
            hVar.a("Timestamp");
            Timestamp_metadata.a().a(0);
            hVar = new h();
            Type_metadata = hVar;
            hVar.a("Type");
            Type_metadata.a().f();
            hVar = new h();
            EventType_metadata = hVar;
            hVar.a("EventType");
            EventType_metadata.a().f();
            hVar = new h();
            Extension_metadata = hVar;
            hVar.a("Extension");
            hVar = new h();
            RecordType_metadata = hVar;
            hVar.a("RecordType");
            RecordType_metadata.a().a((long) RecordType.NotSet.getValue());
            hVar = new h();
            PIIExtensions_metadata = hVar;
            hVar.a("PIIExtensions");
            PIIExtensions_metadata.a().f();
            hVar = new h();
            TypedExtensionBoolean_metadata = hVar;
            hVar.a("TypedExtensionBoolean");
            hVar = new h();
            TypedExtensionDateTime_metadata = hVar;
            hVar.a("TypedExtensionDateTime");
            hVar = new h();
            TypedExtensionInt64_metadata = hVar;
            hVar.a("TypedExtensionInt64");
            hVar = new h();
            TypedExtensionDouble_metadata = hVar;
            hVar.a("TypedExtensionDouble");
            hVar = new h();
            TypedExtensionGuid_metadata = hVar;
            hVar.a("TypedExtensionGuid");
            o oVar = new o();
            schemaDef = oVar;
            oVar.a(getTypeDef(oVar));
        }

        public static q getTypeDef(o schema) {
            q type = new q();
            type.a(a.BT_STRUCT);
            type.a(getStructDef(schema));
            return type;
        }

        private static short getStructDef(o schema) {
            short pos = (short) 0;
            while (pos < schema.a().size()) {
                if (((p) schema.a().get(pos)).a() == metadata) {
                    break;
                }
                pos = (short) (pos + 1);
            }
            p structDef = new p();
            schema.a().add(structDef);
            structDef.a(metadata);
            g field = new g();
            field.a((short) 1);
            field.a(Id_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 3);
            field.a(Timestamp_metadata);
            field.b().a(a.BT_INT64);
            structDef.b().add(field);
            field = new g();
            field.a((short) 5);
            field.a(Type_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 6);
            field.a(EventType_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 13);
            field.a(Extension_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 24);
            field.a(RecordType_metadata);
            field.b().a(a.BT_INT32);
            structDef.b().add(field);
            field = new g();
            field.a((short) 30);
            field.a(PIIExtensions_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a(com.microsoft.applications.telemetry.datamodels.PII.Schema.getTypeDef(schema));
            structDef.b().add(field);
            field = new g();
            field.a((short) 31);
            field.a(TypedExtensionBoolean_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_BOOL);
            structDef.b().add(field);
            field = new g();
            field.a((short) 32);
            field.a(TypedExtensionDateTime_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_INT64);
            structDef.b().add(field);
            field = new g();
            field.a((short) 33);
            field.a(TypedExtensionInt64_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_INT64);
            structDef.b().add(field);
            field = new g();
            field.a((short) 34);
            field.a(TypedExtensionDouble_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_DOUBLE);
            structDef.b().add(field);
            field = new g();
            field.a((short) 35);
            field.a(TypedExtensionGuid_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_LIST);
            field.b().a().a(new q());
            field.b().a().a().a(a.BT_UINT8);
            structDef.b().add(field);
            return pos;
        }
    }

    public d clone() {
        return null;
    }

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

    public final RecordType getRecordType() {
        return this.RecordType;
    }

    public final void setRecordType(RecordType value) {
        this.RecordType = value;
    }

    public final HashMap<String, PII> getPIIExtensions() {
        return this.PIIExtensions;
    }

    public final void setPIIExtensions(HashMap<String, PII> value) {
        this.PIIExtensions = value;
    }

    public final HashMap<String, Boolean> getTypedExtensionBoolean() {
        return this.TypedExtensionBoolean;
    }

    public final void setTypedExtensionBoolean(HashMap<String, Boolean> value) {
        this.TypedExtensionBoolean = value;
    }

    public final HashMap<String, Long> getTypedExtensionDateTime() {
        return this.TypedExtensionDateTime;
    }

    public final void setTypedExtensionDateTime(HashMap<String, Long> value) {
        this.TypedExtensionDateTime = value;
    }

    public final HashMap<String, Long> getTypedExtensionInt64() {
        return this.TypedExtensionInt64;
    }

    public final void setTypedExtensionInt64(HashMap<String, Long> value) {
        this.TypedExtensionInt64 = value;
    }

    public final HashMap<String, Double> getTypedExtensionDouble() {
        return this.TypedExtensionDouble;
    }

    public final void setTypedExtensionDouble(HashMap<String, Double> value) {
        this.TypedExtensionDouble = value;
    }

    public final HashMap<String, ArrayList<Byte>> getTypedExtensionGuid() {
        return this.TypedExtensionGuid;
    }

    public final void setTypedExtensionGuid(HashMap<String, ArrayList<Byte>> value) {
        this.TypedExtensionGuid = value;
    }

    public Object getField(g fieldDef) {
        switch (fieldDef.a()) {
            case (short) 1:
                return this.Id;
            case (short) 3:
                return Long.valueOf(this.Timestamp);
            case (short) 5:
                return this.Type;
            case (short) 6:
                return this.EventType;
            case (short) 13:
                return this.Extension;
            case (short) 24:
                return this.RecordType;
            case (short) 30:
                return this.PIIExtensions;
            case (short) 31:
                return this.TypedExtensionBoolean;
            case (short) 32:
                return this.TypedExtensionDateTime;
            case (short) 33:
                return this.TypedExtensionInt64;
            case (short) 34:
                return this.TypedExtensionDouble;
            case (short) 35:
                return this.TypedExtensionGuid;
            default:
                return null;
        }
    }

    public void setField(g fieldDef, Object value) {
        switch (fieldDef.a()) {
            case (short) 1:
                this.Id = (String) value;
                return;
            case (short) 3:
                this.Timestamp = ((Long) value).longValue();
                return;
            case (short) 5:
                this.Type = (String) value;
                return;
            case (short) 6:
                this.EventType = (String) value;
                return;
            case (short) 13:
                this.Extension = (HashMap) value;
                return;
            case (short) 24:
                this.RecordType = (RecordType) value;
                return;
            case (short) 30:
                this.PIIExtensions = (HashMap) value;
                return;
            case (short) 31:
                this.TypedExtensionBoolean = (HashMap) value;
                return;
            case (short) 32:
                this.TypedExtensionDateTime = (HashMap) value;
                return;
            case (short) 33:
                this.TypedExtensionInt64 = (HashMap) value;
                return;
            case (short) 34:
                this.TypedExtensionDouble = (HashMap) value;
                return;
            case (short) 35:
                this.TypedExtensionGuid = (HashMap) value;
                return;
            default:
                return;
        }
    }

    public c createInstance(p structDef) {
        if (com.microsoft.applications.telemetry.datamodels.PII.Schema.metadata == structDef.a()) {
            return new PII();
        }
        return null;
    }

    public o getSchema() {
        return getRuntimeSchema();
    }

    public static o getRuntimeSchema() {
        return Schema.schemaDef;
    }

    public Record() {
        reset();
    }

    public void reset() {
        reset("Record", "com.microsoft.applications.telemetry.datamodels.Record");
    }

    protected void reset(String name, String qualifiedName) {
        this.Id = null;
        this.Timestamp = 0;
        this.Type = null;
        this.EventType = null;
        if (this.Extension == null) {
            this.Extension = new HashMap();
        } else {
            this.Extension.clear();
        }
        this.RecordType = RecordType.NotSet;
        this.PIIExtensions = null;
        if (this.TypedExtensionBoolean == null) {
            this.TypedExtensionBoolean = new HashMap();
        } else {
            this.TypedExtensionBoolean.clear();
        }
        if (this.TypedExtensionDateTime == null) {
            this.TypedExtensionDateTime = new HashMap();
        } else {
            this.TypedExtensionDateTime.clear();
        }
        if (this.TypedExtensionInt64 == null) {
            this.TypedExtensionInt64 = new HashMap();
        } else {
            this.TypedExtensionInt64.clear();
        }
        if (this.TypedExtensionDouble == null) {
            this.TypedExtensionDouble = new HashMap();
        } else {
            this.TypedExtensionDouble.clear();
        }
        if (this.TypedExtensionGuid == null) {
            this.TypedExtensionGuid = new HashMap();
        } else {
            this.TypedExtensionGuid.clear();
        }
    }

    public void unmarshal(InputStream input) throws IOException {
    }

    public void unmarshal(InputStream input, d schema) throws IOException {
    }

    public void read(k reader) throws IOException {
        readNested(reader);
    }

    public void readNested(k reader) throws IOException {
        if (!reader.a(j.TAGGED)) {
            readUntagged(reader, false);
        } else if (readTagged(reader, false)) {
            b.a(reader);
        }
    }

    public void read(k reader, d schema) throws IOException {
    }

    protected void readUntagged(k reader, boolean isBase) throws IOException {
        reader.a(j.CAN_OMIT_FIELDS);
        this.Id = reader.e();
        this.Timestamp = reader.p();
        this.Type = reader.e();
        this.EventType = reader.e();
        readFieldImpl_Extension(reader, a.BT_MAP);
        this.RecordType = RecordType.fromValue(reader.o());
        readFieldImpl_PIIExtensions(reader, a.BT_MAP);
        readFieldImpl_TypedExtensionBoolean(reader, a.BT_MAP);
        readFieldImpl_TypedExtensionDateTime(reader, a.BT_MAP);
        readFieldImpl_TypedExtensionInt64(reader, a.BT_MAP);
        readFieldImpl_TypedExtensionDouble(reader, a.BT_MAP);
        readFieldImpl_TypedExtensionGuid(reader, a.BT_MAP);
    }

    protected boolean readTagged(k reader, boolean isBase) throws IOException {
        k.a fieldTag;
        while (true) {
            fieldTag = reader.a();
            if (fieldTag.b != a.BT_STOP && fieldTag.b != a.BT_STOP_BASE) {
                switch (fieldTag.a) {
                    case 1:
                        this.Id = b.b(reader, fieldTag.b);
                        break;
                    case 3:
                        this.Timestamp = b.f(reader, fieldTag.b);
                        break;
                    case 5:
                        this.Type = b.b(reader, fieldTag.b);
                        break;
                    case 6:
                        this.EventType = b.b(reader, fieldTag.b);
                        break;
                    case 13:
                        readFieldImpl_Extension(reader, fieldTag.b);
                        break;
                    case 24:
                        this.RecordType = RecordType.fromValue(b.e(reader, fieldTag.b));
                        break;
                    case 30:
                        readFieldImpl_PIIExtensions(reader, fieldTag.b);
                        break;
                    case 31:
                        readFieldImpl_TypedExtensionBoolean(reader, fieldTag.b);
                        break;
                    case 32:
                        readFieldImpl_TypedExtensionDateTime(reader, fieldTag.b);
                        break;
                    case 33:
                        readFieldImpl_TypedExtensionInt64(reader, fieldTag.b);
                        break;
                    case 34:
                        readFieldImpl_TypedExtensionDouble(reader, fieldTag.b);
                        break;
                    case 35:
                        readFieldImpl_TypedExtensionGuid(reader, fieldTag.b);
                        break;
                    default:
                        reader.a(fieldTag.b);
                        break;
                }
            }
        }
        if (fieldTag.b == a.BT_STOP_BASE) {
            return true;
        }
        return false;
    }

    private void readFieldImpl_Extension(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.Extension.put(b.b(reader, tag1.b), b.b(reader, tag1.c));
        }
    }

    private void readFieldImpl_PIIExtensions(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        if (this.PIIExtensions == null) {
            this.PIIExtensions = new HashMap();
        }
        k.c tag1 = reader.c();
        b.a(tag1.c, a.BT_STRUCT);
        for (int i2 = 0; i2 < tag1.a; i2++) {
            PII val4 = new PII();
            String key3 = b.b(reader, tag1.b);
            val4.readNested(reader);
            this.PIIExtensions.put(key3, val4);
        }
    }

    private void readFieldImpl_TypedExtensionBoolean(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.TypedExtensionBoolean.put(b.b(reader, tag1.b), Boolean.valueOf(b.a(reader, tag1.c)));
        }
    }

    private void readFieldImpl_TypedExtensionDateTime(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.TypedExtensionDateTime.put(b.b(reader, tag1.b), Long.valueOf(b.f(reader, tag1.c)));
        }
    }

    private void readFieldImpl_TypedExtensionInt64(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.TypedExtensionInt64.put(b.b(reader, tag1.b), Long.valueOf(b.f(reader, tag1.c)));
        }
    }

    private void readFieldImpl_TypedExtensionDouble(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.TypedExtensionDouble.put(b.b(reader, tag1.b), Double.valueOf(b.c(reader, tag1.c)));
        }
    }

    private void readFieldImpl_TypedExtensionGuid(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        b.a(tag1.c, a.BT_LIST);
        for (int i2 = 0; i2 < tag1.a; i2++) {
            ArrayList<Byte> val4 = new ArrayList();
            String key3 = b.b(reader, tag1.b);
            k.b tag5 = reader.b();
            val4.ensureCapacity(tag5.a);
            for (int i7 = 0; i7 < tag5.a; i7++) {
                b.a(tag5.b, a.BT_UINT8);
                val4.add(Byte.valueOf(reader.i()));
            }
            this.TypedExtensionGuid.put(key3, val4);
        }
    }

    public void marshal(n writer) throws IOException {
    }

    public void write(n writer) throws IOException {
        writeNested(writer, false);
    }

    public void writeNested(n writer, boolean isBase) throws IOException {
        a aVar;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = Schema.metadata;
        if (canOmitFields && this.Id == null) {
            aVar = a.BT_STRING;
            Schema.Id_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.Id_metadata;
            writer.a(aVar, 1);
            writer.a(this.Id);
        }
        if (canOmitFields && this.Timestamp == Schema.Timestamp_metadata.a().c()) {
            aVar = a.BT_INT64;
            Schema.Timestamp_metadata;
        } else {
            aVar = a.BT_INT64;
            Schema.Timestamp_metadata;
            writer.a(aVar, 3);
            writer.b(this.Timestamp);
        }
        if (canOmitFields && this.Type == null) {
            aVar = a.BT_STRING;
            Schema.Type_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.Type_metadata;
            writer.a(aVar, 5);
            writer.a(this.Type);
        }
        if (canOmitFields && this.EventType == null) {
            aVar = a.BT_STRING;
            Schema.EventType_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.EventType_metadata;
            writer.a(aVar, 6);
            writer.a(this.EventType);
        }
        int size5 = this.Extension.size();
        if (canOmitFields && size5 == 0) {
            aVar = a.BT_MAP;
            Schema.Extension_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.Extension_metadata;
            writer.a(aVar, 13);
            writer.a(this.Extension.size(), a.BT_STRING, a.BT_STRING);
            for (Entry<String, String> e6 : this.Extension.entrySet()) {
                writer.a((String) e6.getKey());
                writer.a((String) e6.getValue());
            }
        }
        if (canOmitFields && ((long) this.RecordType.getValue()) == Schema.RecordType_metadata.a().c()) {
            aVar = a.BT_INT32;
            Schema.RecordType_metadata;
        } else {
            aVar = a.BT_INT32;
            Schema.RecordType_metadata;
            writer.a(aVar, 24);
            writer.a(this.RecordType.getValue());
        }
        if (this.PIIExtensions != null) {
            this.PIIExtensions.size();
        }
        if (canOmitFields && this.PIIExtensions == null) {
            aVar = a.BT_MAP;
            Schema.PIIExtensions_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.PIIExtensions_metadata;
            writer.a(aVar, 30);
            writer.a(this.PIIExtensions.size(), a.BT_STRING, a.BT_STRUCT);
            for (Entry<String, PII> e9 : this.PIIExtensions.entrySet()) {
                writer.a((String) e9.getKey());
                ((PII) e9.getValue()).writeNested(writer, false);
            }
        }
        int size10 = this.TypedExtensionBoolean.size();
        if (canOmitFields && size10 == 0) {
            aVar = a.BT_MAP;
            Schema.TypedExtensionBoolean_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.TypedExtensionBoolean_metadata;
            writer.a(aVar, 31);
            writer.a(this.TypedExtensionBoolean.size(), a.BT_STRING, a.BT_BOOL);
            for (Entry<String, Boolean> e11 : this.TypedExtensionBoolean.entrySet()) {
                writer.a((String) e11.getKey());
                writer.b(((Boolean) e11.getValue()).booleanValue());
            }
        }
        int size12 = this.TypedExtensionDateTime.size();
        if (canOmitFields && size12 == 0) {
            aVar = a.BT_MAP;
            Schema.TypedExtensionDateTime_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.TypedExtensionDateTime_metadata;
            writer.a(aVar, 32);
            writer.a(this.TypedExtensionDateTime.size(), a.BT_STRING, a.BT_INT64);
            for (Entry<String, Long> e13 : this.TypedExtensionDateTime.entrySet()) {
                writer.a((String) e13.getKey());
                writer.b(((Long) e13.getValue()).longValue());
            }
        }
        int size14 = this.TypedExtensionInt64.size();
        if (canOmitFields && size14 == 0) {
            aVar = a.BT_MAP;
            Schema.TypedExtensionInt64_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.TypedExtensionInt64_metadata;
            writer.a(aVar, 33);
            writer.a(this.TypedExtensionInt64.size(), a.BT_STRING, a.BT_INT64);
            for (Entry<String, Long> e15 : this.TypedExtensionInt64.entrySet()) {
                writer.a((String) e15.getKey());
                writer.b(((Long) e15.getValue()).longValue());
            }
        }
        int size16 = this.TypedExtensionDouble.size();
        if (canOmitFields && size16 == 0) {
            aVar = a.BT_MAP;
            Schema.TypedExtensionDouble_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.TypedExtensionDouble_metadata;
            writer.a(aVar, 34);
            writer.a(this.TypedExtensionDouble.size(), a.BT_STRING, a.BT_DOUBLE);
            for (Entry<String, Double> e17 : this.TypedExtensionDouble.entrySet()) {
                writer.a((String) e17.getKey());
                writer.a(((Double) e17.getValue()).doubleValue());
            }
        }
        int size18 = this.TypedExtensionGuid.size();
        if (canOmitFields && size18 == 0) {
            aVar = a.BT_MAP;
            Schema.TypedExtensionGuid_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.TypedExtensionGuid_metadata;
            writer.a(aVar, 35);
            writer.a(this.TypedExtensionGuid.size(), a.BT_STRING, a.BT_LIST);
            for (Entry<String, ArrayList<Byte>> e19 : this.TypedExtensionGuid.entrySet()) {
                writer.a((String) e19.getKey());
                writer.a(((ArrayList) e19.getValue()).size(), a.BT_UINT8);
                Iterator it = ((ArrayList) e19.getValue()).iterator();
                while (it.hasNext()) {
                    writer.a(((Byte) it.next()).byteValue());
                }
            }
        }
        writer.a(isBase);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        Record that = (Record) obj;
        if (memberwiseCompareQuick(that) && memberwiseCompareDeep(that)) {
            return true;
        }
        return false;
    }

    protected boolean memberwiseCompareQuick(Record that) {
        boolean z;
        if (((this.Id == null) == (that.Id == null)) && (this.Id == null || this.Id.length() == that.Id.length())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.Timestamp == that.Timestamp) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.Type == null) {
                z = true;
            } else {
                z = false;
            }
            if (z == (that.Type == null)) {
                z = true;
                if (z || !(this.Type == null || this.Type.length() == that.Type.length())) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    if (this.EventType != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z == (that.EventType != null)) {
                        z = true;
                        if (z || !(this.EventType == null || this.EventType.length() == that.EventType.length())) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (z) {
                            if (this.Extension != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z == (that.Extension != null)) {
                                z = true;
                                if (z || !(this.Extension == null || this.Extension.size() == that.Extension.size())) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (z || this.RecordType != that.RecordType) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (z) {
                                    if (this.PIIExtensions != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (z == (that.PIIExtensions != null)) {
                                        z = true;
                                        if (z || !(this.PIIExtensions == null || this.PIIExtensions.size() == that.PIIExtensions.size())) {
                                            z = false;
                                        } else {
                                            z = true;
                                        }
                                        if (z) {
                                            if (this.TypedExtensionBoolean != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (z == (that.TypedExtensionBoolean != null)) {
                                                z = true;
                                                if (z || !(this.TypedExtensionBoolean == null || this.TypedExtensionBoolean.size() == that.TypedExtensionBoolean.size())) {
                                                    z = false;
                                                } else {
                                                    z = true;
                                                }
                                                if (z) {
                                                    if (this.TypedExtensionDateTime != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (z == (that.TypedExtensionDateTime != null)) {
                                                        z = true;
                                                        if (z || !(this.TypedExtensionDateTime == null || this.TypedExtensionDateTime.size() == that.TypedExtensionDateTime.size())) {
                                                            z = false;
                                                        } else {
                                                            z = true;
                                                        }
                                                        if (z) {
                                                            if (this.TypedExtensionInt64 != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                                z = true;
                                                                if (z || !(this.TypedExtensionInt64 == null || this.TypedExtensionInt64.size() == that.TypedExtensionInt64.size())) {
                                                                    z = false;
                                                                } else {
                                                                    z = true;
                                                                }
                                                                if (z) {
                                                                    if (this.TypedExtensionDouble != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                                        z = true;
                                                                        if (z || !(this.TypedExtensionDouble == null || this.TypedExtensionDouble.size() == that.TypedExtensionDouble.size())) {
                                                                            z = false;
                                                                        } else {
                                                                            z = true;
                                                                        }
                                                                        if (z) {
                                                                            if (this.TypedExtensionGuid != null) {
                                                                                z = true;
                                                                            } else {
                                                                                z = false;
                                                                            }
                                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                                z = true;
                                                                                if (z || (this.TypedExtensionGuid != null && this.TypedExtensionGuid.size() != that.TypedExtensionGuid.size())) {
                                                                                    return false;
                                                                                }
                                                                                return true;
                                                                            }
                                                                        }
                                                                        z = false;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = false;
                                                                    } else {
                                                                        z = true;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionInt64 != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionInt64 != null) {
                                                    }
                                                    if (z == (that.TypedExtensionInt64 != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDateTime != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDateTime != null) {
                                            }
                                            if (z == (that.TypedExtensionDateTime != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionInt64 != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionInt64 != null) {
                                                    }
                                                    if (z == (that.TypedExtensionInt64 != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionBoolean != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionBoolean != null) {
                                    }
                                    if (z == (that.TypedExtensionBoolean != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDateTime != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDateTime != null) {
                                            }
                                            if (z == (that.TypedExtensionDateTime != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionInt64 != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionInt64 != null) {
                                                    }
                                                    if (z == (that.TypedExtensionInt64 != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.PIIExtensions != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.PIIExtensions != null) {
                            }
                            if (z == (that.PIIExtensions != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionBoolean != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionBoolean != null) {
                                    }
                                    if (z == (that.TypedExtensionBoolean != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDateTime != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDateTime != null) {
                                            }
                                            if (z == (that.TypedExtensionDateTime != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionInt64 != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionInt64 != null) {
                                                    }
                                                    if (z == (that.TypedExtensionInt64 != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionBoolean != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionBoolean != null) {
                            }
                            if (z == (that.TypedExtensionBoolean != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.Extension != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.Extension != null) {
                    }
                    if (z == (that.Extension != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.PIIExtensions != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.PIIExtensions != null) {
                            }
                            if (z == (that.PIIExtensions != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionBoolean != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionBoolean != null) {
                                    }
                                    if (z == (that.TypedExtensionBoolean != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDateTime != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDateTime != null) {
                                            }
                                            if (z == (that.TypedExtensionDateTime != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionInt64 != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionInt64 != null) {
                                                    }
                                                    if (z == (that.TypedExtensionInt64 != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionBoolean != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionBoolean != null) {
                            }
                            if (z == (that.TypedExtensionBoolean != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.PIIExtensions != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.PIIExtensions != null) {
                    }
                    if (z == (that.PIIExtensions != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionBoolean != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionBoolean != null) {
                            }
                            if (z == (that.TypedExtensionBoolean != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionBoolean != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionBoolean != null) {
                    }
                    if (z == (that.TypedExtensionBoolean != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDateTime != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDateTime != null) {
                    }
                    if (z == (that.TypedExtensionDateTime != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionInt64 != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionInt64 != null) {
                    }
                    if (z == (that.TypedExtensionInt64 != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.EventType != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.EventType != null) {
            }
            if (z == (that.EventType != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.Extension != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.Extension != null) {
                    }
                    if (z == (that.Extension != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.PIIExtensions != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.PIIExtensions != null) {
                            }
                            if (z == (that.PIIExtensions != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionBoolean != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionBoolean != null) {
                                    }
                                    if (z == (that.TypedExtensionBoolean != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDateTime != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDateTime != null) {
                                            }
                                            if (z == (that.TypedExtensionDateTime != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionInt64 != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionInt64 != null) {
                                                    }
                                                    if (z == (that.TypedExtensionInt64 != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionDouble != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionDouble != null) {
                                                            }
                                                            if (z == (that.TypedExtensionDouble != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                    if (this.TypedExtensionGuid != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (that.TypedExtensionGuid != null) {
                                                                    }
                                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                                        z = true;
                                                                        if (z) {
                                                                        }
                                                                        return false;
                                                                    }
                                                                }
                                                                z = false;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = false;
                                                            } else {
                                                                z = true;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionBoolean != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionBoolean != null) {
                            }
                            if (z == (that.TypedExtensionBoolean != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.PIIExtensions != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.PIIExtensions != null) {
                    }
                    if (z == (that.PIIExtensions != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionBoolean != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionBoolean != null) {
                            }
                            if (z == (that.TypedExtensionBoolean != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionBoolean != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionBoolean != null) {
                    }
                    if (z == (that.TypedExtensionBoolean != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDateTime != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDateTime != null) {
                    }
                    if (z == (that.TypedExtensionDateTime != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionInt64 != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionInt64 != null) {
                    }
                    if (z == (that.TypedExtensionInt64 != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.Extension != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.Extension != null) {
            }
            if (z == (that.Extension != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.PIIExtensions != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.PIIExtensions != null) {
                    }
                    if (z == (that.PIIExtensions != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionBoolean != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionBoolean != null) {
                            }
                            if (z == (that.TypedExtensionBoolean != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDateTime != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDateTime != null) {
                                    }
                                    if (z == (that.TypedExtensionDateTime != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionInt64 != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionInt64 != null) {
                                            }
                                            if (z == (that.TypedExtensionInt64 != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionDouble != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionDouble != null) {
                                                    }
                                                    if (z == (that.TypedExtensionDouble != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        z = false;
                                                        if (z) {
                                                            if (this.TypedExtensionGuid != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            if (that.TypedExtensionGuid != null) {
                                                            }
                                                            if (z == (that.TypedExtensionGuid != null)) {
                                                                z = true;
                                                                if (z) {
                                                                }
                                                                return false;
                                                            }
                                                        }
                                                        z = false;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = false;
                                                    } else {
                                                        z = true;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionBoolean != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionBoolean != null) {
                    }
                    if (z == (that.TypedExtensionBoolean != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDateTime != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDateTime != null) {
                    }
                    if (z == (that.TypedExtensionDateTime != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionInt64 != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionInt64 != null) {
                    }
                    if (z == (that.TypedExtensionInt64 != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.PIIExtensions != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.PIIExtensions != null) {
            }
            if (z == (that.PIIExtensions != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionBoolean != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.TypedExtensionBoolean != null) {
                    }
                    if (z == (that.TypedExtensionBoolean != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDateTime != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDateTime != null) {
                            }
                            if (z == (that.TypedExtensionDateTime != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionInt64 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionInt64 != null) {
                                    }
                                    if (z == (that.TypedExtensionInt64 != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionDouble != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionDouble != null) {
                                            }
                                            if (z == (that.TypedExtensionDouble != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                z = false;
                                                if (z) {
                                                    if (this.TypedExtensionGuid != null) {
                                                        z = true;
                                                    } else {
                                                        z = false;
                                                    }
                                                    if (that.TypedExtensionGuid != null) {
                                                    }
                                                    if (z == (that.TypedExtensionGuid != null)) {
                                                        z = true;
                                                        if (z) {
                                                        }
                                                        return false;
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDateTime != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDateTime != null) {
                    }
                    if (z == (that.TypedExtensionDateTime != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionInt64 != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionInt64 != null) {
                    }
                    if (z == (that.TypedExtensionInt64 != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.TypedExtensionBoolean != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.TypedExtensionBoolean != null) {
            }
            if (z == (that.TypedExtensionBoolean != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDateTime != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.TypedExtensionDateTime != null) {
                    }
                    if (z == (that.TypedExtensionDateTime != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionInt64 != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionInt64 != null) {
                            }
                            if (z == (that.TypedExtensionInt64 != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionDouble != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionDouble != null) {
                                    }
                                    if (z == (that.TypedExtensionDouble != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        z = false;
                                        if (z) {
                                            if (this.TypedExtensionGuid != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (that.TypedExtensionGuid != null) {
                                            }
                                            if (z == (that.TypedExtensionGuid != null)) {
                                                z = true;
                                                if (z) {
                                                }
                                                return false;
                                            }
                                        }
                                        z = false;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionInt64 != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionInt64 != null) {
                    }
                    if (z == (that.TypedExtensionInt64 != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.TypedExtensionDateTime != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.TypedExtensionDateTime != null) {
            }
            if (z == (that.TypedExtensionDateTime != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionInt64 != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.TypedExtensionInt64 != null) {
                    }
                    if (z == (that.TypedExtensionInt64 != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionDouble != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionDouble != null) {
                            }
                            if (z == (that.TypedExtensionDouble != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.TypedExtensionGuid != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.TypedExtensionGuid != null) {
                                    }
                                    if (z == (that.TypedExtensionGuid != null)) {
                                        z = true;
                                        if (z) {
                                        }
                                        return false;
                                    }
                                }
                                z = false;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.TypedExtensionInt64 != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.TypedExtensionInt64 != null) {
            }
            if (z == (that.TypedExtensionInt64 != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionDouble != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.TypedExtensionDouble != null) {
                    }
                    if (z == (that.TypedExtensionDouble != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.TypedExtensionGuid != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.TypedExtensionGuid != null) {
                            }
                            if (z == (that.TypedExtensionGuid != null)) {
                                z = true;
                                if (z) {
                                }
                                return false;
                            }
                        }
                        z = false;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.TypedExtensionDouble != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.TypedExtensionDouble != null) {
            }
            if (z == (that.TypedExtensionDouble != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.TypedExtensionGuid != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.TypedExtensionGuid != null) {
                    }
                    if (z == (that.TypedExtensionGuid != null)) {
                        z = true;
                        if (z) {
                        }
                        return false;
                    }
                }
                z = false;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        z = false;
        if (z) {
            if (this.TypedExtensionGuid != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.TypedExtensionGuid != null) {
            }
            if (z == (that.TypedExtensionGuid != null)) {
                z = true;
                if (z) {
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        return false;
    }

    protected boolean memberwiseCompareDeep(Record that) {
        Object obj = (this.Id == null || this.Id.equals(that.Id)) ? 1 : null;
        obj = (obj == null || !(this.Type == null || this.Type.equals(that.Type))) ? null : 1;
        boolean equals = obj != null && (this.EventType == null || this.EventType.equals(that.EventType));
        if (equals && this.Extension != null && this.Extension.size() != 0) {
            for (Entry<String, String> e3 : this.Extension.entrySet()) {
                String val1 = (String) e3.getValue();
                String val2 = (String) that.Extension.get(e3.getKey());
                equals = that.Extension.containsKey(e3.getKey());
                if (equals) {
                    obj = (((val1 == null ? 1 : null) == (val2 == null ? 1 : null) ? 1 : null) == null || !(val1 == null || val1.length() == val2.length())) ? null : 1;
                    if (obj == null || !(val1 == null || val1.equals(val2))) {
                        equals = false;
                        continue;
                    } else {
                        equals = true;
                        continue;
                    }
                }
                if (!equals) {
                    break;
                }
            }
        }
        if (equals && this.PIIExtensions != null && this.PIIExtensions.size() != 0) {
            for (Entry<String, PII> e6 : this.PIIExtensions.entrySet()) {
                PII val4 = (PII) e6.getValue();
                PII val5 = (PII) that.PIIExtensions.get(e6.getKey());
                equals = equals && that.PIIExtensions.containsKey(e6.getKey());
                if (equals) {
                    if (equals) {
                        if ((val4 == null ? 1 : null) == (val5 == null ? 1 : null)) {
                            obj = 1;
                            if (obj == null && (val4 == null || val4.memberwiseCompare(val5))) {
                                equals = true;
                                continue;
                            } else {
                                equals = false;
                                continue;
                            }
                        }
                    }
                    obj = null;
                    if (obj == null) {
                    }
                    equals = false;
                    continue;
                }
                if (!equals) {
                    break;
                }
            }
        }
        if (equals && this.TypedExtensionBoolean != null && this.TypedExtensionBoolean.size() != 0) {
            for (Entry<String, Boolean> e9 : this.TypedExtensionBoolean.entrySet()) {
                Boolean val7 = (Boolean) e9.getValue();
                Boolean val8 = (Boolean) that.TypedExtensionBoolean.get(e9.getKey());
                equals = equals && that.TypedExtensionBoolean.containsKey(e9.getKey());
                if (equals) {
                    if (!equals || (val7 != val8 && (val7 == null || !val7.equals(val8)))) {
                        equals = false;
                        continue;
                    } else {
                        equals = true;
                        continue;
                    }
                }
                if (!equals) {
                    break;
                }
            }
        }
        if (equals && this.TypedExtensionDateTime != null && this.TypedExtensionDateTime.size() != 0) {
            for (Entry<String, Long> e12 : this.TypedExtensionDateTime.entrySet()) {
                Long val10 = (Long) e12.getValue();
                Long val11 = (Long) that.TypedExtensionDateTime.get(e12.getKey());
                equals = equals && that.TypedExtensionDateTime.containsKey(e12.getKey());
                if (equals) {
                    if (!equals || (val10 != val11 && (val10 == null || !val10.equals(val11)))) {
                        equals = false;
                        continue;
                    } else {
                        equals = true;
                        continue;
                    }
                }
                if (!equals) {
                    break;
                }
            }
        }
        if (equals && this.TypedExtensionInt64 != null && this.TypedExtensionInt64.size() != 0) {
            for (Entry<String, Long> e15 : this.TypedExtensionInt64.entrySet()) {
                Long val13 = (Long) e15.getValue();
                Long val14 = (Long) that.TypedExtensionInt64.get(e15.getKey());
                equals = equals && that.TypedExtensionInt64.containsKey(e15.getKey());
                if (equals) {
                    if (!equals || (val13 != val14 && (val13 == null || !val13.equals(val14)))) {
                        equals = false;
                        continue;
                    } else {
                        equals = true;
                        continue;
                    }
                }
                if (!equals) {
                    break;
                }
            }
        }
        if (equals && this.TypedExtensionDouble != null && this.TypedExtensionDouble.size() != 0) {
            for (Entry<String, Double> e18 : this.TypedExtensionDouble.entrySet()) {
                Double val16 = (Double) e18.getValue();
                Double val17 = (Double) that.TypedExtensionDouble.get(e18.getKey());
                equals = equals && that.TypedExtensionDouble.containsKey(e18.getKey());
                if (equals) {
                    if (equals) {
                        if (Double.isNaN(val16.doubleValue())) {
                        }
                        equals = true;
                        continue;
                    }
                    equals = false;
                    continue;
                }
                if (!equals) {
                    break;
                }
            }
        }
        if (equals && this.TypedExtensionGuid != null && this.TypedExtensionGuid.size() != 0) {
            for (Entry<String, ArrayList<Byte>> e21 : this.TypedExtensionGuid.entrySet()) {
                ArrayList<Byte> val19 = (ArrayList) e21.getValue();
                ArrayList<Byte> val20 = (ArrayList) that.TypedExtensionGuid.get(e21.getKey());
                equals = equals && that.TypedExtensionGuid.containsKey(e21.getKey());
                if (equals) {
                    int i22;
                    Byte val23;
                    Byte val24;
                    if (equals) {
                        if ((val19 == null ? 1 : null) == (val20 == null ? 1 : null)) {
                            obj = 1;
                            equals = obj == null && (val19 == null || val19.size() == val20.size());
                            if (equals && val19 != null && val19.size() != 0) {
                                for (i22 = 0; i22 < val19.size(); i22++) {
                                    val23 = (Byte) val19.get(i22);
                                    val24 = (Byte) val20.get(i22);
                                    equals = equals && (val23 == val24 || (val23 != null && val23.equals(val24)));
                                    if (equals) {
                                        continue;
                                        break;
                                    }
                                }
                                continue;
                            }
                        }
                    }
                    obj = null;
                    if (obj == null) {
                    }
                    for (i22 = 0; i22 < val19.size(); i22++) {
                        val23 = (Byte) val19.get(i22);
                        val24 = (Byte) val20.get(i22);
                        if (!equals) {
                        }
                        if (equals) {
                            continue;
                            break;
                        }
                    }
                    continue;
                }
                if (!equals) {
                    break;
                }
            }
        }
        return equals;
    }
}
