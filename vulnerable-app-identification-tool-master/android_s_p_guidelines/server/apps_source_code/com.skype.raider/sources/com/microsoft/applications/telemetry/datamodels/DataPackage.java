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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class DataPackage implements c, d {
    private String DataPackageId;
    private HashMap<String, String> Ids;
    private ArrayList<Record> Records;
    private int SchemaVersion;
    private String Source;
    private long Timestamp;
    private String Type;
    private String Version;

    public static class Schema {
        private static final h DataPackageId_metadata;
        private static final h Ids_metadata;
        private static final h Records_metadata;
        private static final h SchemaVersion_metadata;
        private static final h Source_metadata;
        private static final h Timestamp_metadata;
        private static final h Type_metadata;
        private static final h Version_metadata;
        public static final h metadata;
        public static final o schemaDef;

        static {
            h hVar = new h();
            metadata = hVar;
            hVar.a("DataPackage");
            metadata.b("DataPackage");
            hVar = new h();
            Type_metadata = hVar;
            hVar.a("Type");
            Type_metadata.a().f();
            hVar = new h();
            Source_metadata = hVar;
            hVar.a("Source");
            Source_metadata.a().f();
            hVar = new h();
            Version_metadata = hVar;
            hVar.a("Version");
            Version_metadata.a().f();
            hVar = new h();
            Ids_metadata = hVar;
            hVar.a("Ids");
            hVar = new h();
            DataPackageId_metadata = hVar;
            hVar.a("DataPackageId");
            DataPackageId_metadata.a().f();
            hVar = new h();
            Timestamp_metadata = hVar;
            hVar.a("Timestamp");
            Timestamp_metadata.a().a(0);
            hVar = new h();
            SchemaVersion_metadata = hVar;
            hVar.a("SchemaVersion");
            SchemaVersion_metadata.a().a(0);
            hVar = new h();
            Records_metadata = hVar;
            hVar.a("Records");
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
            field.a(Type_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 2);
            field.a(Source_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 3);
            field.a(Version_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 4);
            field.a(Ids_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 5);
            field.a(DataPackageId_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            field = new g();
            field.a((short) 6);
            field.a(Timestamp_metadata);
            field.b().a(a.BT_INT64);
            structDef.b().add(field);
            field = new g();
            field.a((short) 7);
            field.a(SchemaVersion_metadata);
            field.b().a(a.BT_INT32);
            structDef.b().add(field);
            field = new g();
            field.a((short) 8);
            field.a(Records_metadata);
            field.b().a(a.BT_LIST);
            field.b().a(new q());
            field.b().a(com.microsoft.applications.telemetry.datamodels.Record.Schema.getTypeDef(schema));
            structDef.b().add(field);
            return pos;
        }
    }

    public d clone() {
        return null;
    }

    public final String getType() {
        return this.Type;
    }

    public final void setType(String value) {
        this.Type = value;
    }

    public final String getSource() {
        return this.Source;
    }

    public final void setSource(String value) {
        this.Source = value;
    }

    public final String getVersion() {
        return this.Version;
    }

    public final void setVersion(String value) {
        this.Version = value;
    }

    public final HashMap<String, String> getIds() {
        return this.Ids;
    }

    public final void setIds(HashMap<String, String> value) {
        this.Ids = value;
    }

    public final String getDataPackageId() {
        return this.DataPackageId;
    }

    public final void setDataPackageId(String value) {
        this.DataPackageId = value;
    }

    public final long getTimestamp() {
        return this.Timestamp;
    }

    public final void setTimestamp(long value) {
        this.Timestamp = value;
    }

    public final int getSchemaVersion() {
        return this.SchemaVersion;
    }

    public final void setSchemaVersion(int value) {
        this.SchemaVersion = value;
    }

    public final ArrayList<Record> getRecords() {
        return this.Records;
    }

    public final void setRecords(ArrayList<Record> value) {
        this.Records = value;
    }

    public Object getField(g fieldDef) {
        switch (fieldDef.a()) {
            case (short) 1:
                return this.Type;
            case (short) 2:
                return this.Source;
            case (short) 3:
                return this.Version;
            case (short) 4:
                return this.Ids;
            case (short) 5:
                return this.DataPackageId;
            case (short) 6:
                return Long.valueOf(this.Timestamp);
            case (short) 7:
                return Integer.valueOf(this.SchemaVersion);
            case (short) 8:
                return this.Records;
            default:
                return null;
        }
    }

    public void setField(g fieldDef, Object value) {
        switch (fieldDef.a()) {
            case (short) 1:
                this.Type = (String) value;
                return;
            case (short) 2:
                this.Source = (String) value;
                return;
            case (short) 3:
                this.Version = (String) value;
                return;
            case (short) 4:
                this.Ids = (HashMap) value;
                return;
            case (short) 5:
                this.DataPackageId = (String) value;
                return;
            case (short) 6:
                this.Timestamp = ((Long) value).longValue();
                return;
            case (short) 7:
                this.SchemaVersion = ((Integer) value).intValue();
                return;
            case (short) 8:
                this.Records = (ArrayList) value;
                return;
            default:
                return;
        }
    }

    public c createInstance(p structDef) {
        if (com.microsoft.applications.telemetry.datamodels.Record.Schema.metadata == structDef.a()) {
            return new Record();
        }
        return null;
    }

    public o getSchema() {
        return getRuntimeSchema();
    }

    public static o getRuntimeSchema() {
        return Schema.schemaDef;
    }

    public DataPackage() {
        reset();
    }

    public void reset() {
        reset("DataPackage", "DataPackage");
    }

    protected void reset(String name, String qualifiedName) {
        this.Type = null;
        this.Source = null;
        this.Version = null;
        if (this.Ids == null) {
            this.Ids = new HashMap();
        } else {
            this.Ids.clear();
        }
        this.DataPackageId = null;
        this.Timestamp = 0;
        this.SchemaVersion = 0;
        if (this.Records == null) {
            this.Records = new ArrayList();
        } else {
            this.Records.clear();
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
        this.Type = reader.e();
        this.Source = reader.e();
        this.Version = reader.e();
        readFieldImpl_Ids(reader, a.BT_MAP);
        this.DataPackageId = reader.e();
        this.Timestamp = reader.p();
        this.SchemaVersion = reader.o();
        readFieldImpl_Records(reader, a.BT_LIST);
    }

    protected boolean readTagged(k reader, boolean isBase) throws IOException {
        k.a fieldTag;
        while (true) {
            fieldTag = reader.a();
            if (fieldTag.b != a.BT_STOP && fieldTag.b != a.BT_STOP_BASE) {
                switch (fieldTag.a) {
                    case 1:
                        this.Type = b.b(reader, fieldTag.b);
                        break;
                    case 2:
                        this.Source = b.b(reader, fieldTag.b);
                        break;
                    case 3:
                        this.Version = b.b(reader, fieldTag.b);
                        break;
                    case 4:
                        readFieldImpl_Ids(reader, fieldTag.b);
                        break;
                    case 5:
                        this.DataPackageId = b.b(reader, fieldTag.b);
                        break;
                    case 6:
                        this.Timestamp = b.f(reader, fieldTag.b);
                        break;
                    case 7:
                        this.SchemaVersion = b.e(reader, fieldTag.b);
                        break;
                    case 8:
                        readFieldImpl_Records(reader, fieldTag.b);
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

    private void readFieldImpl_Ids(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.Ids.put(b.b(reader, tag1.b), b.b(reader, tag1.c));
        }
    }

    private void readFieldImpl_Records(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        this.Records.ensureCapacity(tag1.a);
        for (int i3 = 0; i3 < tag1.a; i3++) {
            Record element2 = new Record();
            element2.readNested(reader);
            this.Records.add(element2);
        }
    }

    public void marshal(n writer) throws IOException {
    }

    public void write(n writer) throws IOException {
        writeNested(writer, false);
    }

    public void writeNested(n writer, boolean isBase) throws IOException {
        a aVar;
        Iterator it;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = Schema.metadata;
        if (canOmitFields && this.Type == null) {
            aVar = a.BT_STRING;
            Schema.Type_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.Type_metadata;
            writer.a(aVar, 1);
            writer.a(this.Type);
        }
        if (canOmitFields && this.Source == null) {
            aVar = a.BT_STRING;
            Schema.Source_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.Source_metadata;
            writer.a(aVar, 2);
            writer.a(this.Source);
        }
        if (canOmitFields && this.Version == null) {
            aVar = a.BT_STRING;
            Schema.Version_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.Version_metadata;
            writer.a(aVar, 3);
            writer.a(this.Version);
        }
        int size4 = this.Ids.size();
        if (canOmitFields && size4 == 0) {
            aVar = a.BT_MAP;
            Schema.Ids_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.Ids_metadata;
            writer.a(aVar, 4);
            writer.a(this.Ids.size(), a.BT_STRING, a.BT_STRING);
            for (Entry<String, String> e5 : this.Ids.entrySet()) {
                writer.a((String) e5.getKey());
                writer.a((String) e5.getValue());
            }
        }
        if (canOmitFields && this.DataPackageId == null) {
            aVar = a.BT_STRING;
            Schema.DataPackageId_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.DataPackageId_metadata;
            writer.a(aVar, 5);
            writer.a(this.DataPackageId);
        }
        if (canOmitFields && this.Timestamp == Schema.Timestamp_metadata.a().c()) {
            aVar = a.BT_INT64;
            Schema.Timestamp_metadata;
        } else {
            aVar = a.BT_INT64;
            Schema.Timestamp_metadata;
            writer.a(aVar, 6);
            writer.b(this.Timestamp);
        }
        if (canOmitFields && ((long) this.SchemaVersion) == Schema.SchemaVersion_metadata.a().c()) {
            aVar = a.BT_INT32;
            Schema.SchemaVersion_metadata;
        } else {
            aVar = a.BT_INT32;
            Schema.SchemaVersion_metadata;
            writer.a(aVar, 7);
            writer.a(this.SchemaVersion);
        }
        int size9 = this.Records.size();
        if (canOmitFields && size9 == 0) {
            aVar = a.BT_LIST;
            Schema.Records_metadata;
        } else {
            aVar = a.BT_LIST;
            Schema.Records_metadata;
            writer.a(aVar, 8);
            writer.a(size9, a.BT_STRUCT);
            it = this.Records.iterator();
            while (it.hasNext()) {
                ((Record) it.next()).writeNested(writer, false);
            }
        }
        writer.a(isBase);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        DataPackage that = (DataPackage) obj;
        if (memberwiseCompareQuick(that) && memberwiseCompareDeep(that)) {
            return true;
        }
        return false;
    }

    protected boolean memberwiseCompareQuick(DataPackage that) {
        boolean z;
        if (((this.Type == null) == (that.Type == null)) && (this.Type == null || this.Type.length() == that.Type.length())) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.Source == null) {
                z = true;
            } else {
                z = false;
            }
            if (z == (that.Source == null)) {
                z = true;
                if (z || !(this.Source == null || this.Source.length() == that.Source.length())) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    if (this.Version != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z == (that.Version != null)) {
                        z = true;
                        if (z || !(this.Version == null || this.Version.length() == that.Version.length())) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (z) {
                            if (this.Ids != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z == (that.Ids != null)) {
                                z = true;
                                if (z || !(this.Ids == null || this.Ids.size() == that.Ids.size())) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (z) {
                                    if (this.DataPackageId != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (z == (that.DataPackageId != null)) {
                                        z = true;
                                        if (z || !(this.DataPackageId == null || this.DataPackageId.length() == that.DataPackageId.length())) {
                                            z = false;
                                        } else {
                                            z = true;
                                        }
                                        if (z || this.Timestamp != that.Timestamp) {
                                            z = false;
                                        } else {
                                            z = true;
                                        }
                                        if (z || this.SchemaVersion != that.SchemaVersion) {
                                            z = false;
                                        } else {
                                            z = true;
                                        }
                                        if (z) {
                                            if (this.Records != null) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (z == (that.Records != null)) {
                                                z = true;
                                                if (z || (this.Records != null && this.Records.size() != that.Records.size())) {
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
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.Records != null) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (that.Records != null) {
                                    }
                                    if (z == (that.Records != null)) {
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
                            if (this.DataPackageId != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.DataPackageId != null) {
                            }
                            if (z == (that.DataPackageId != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.Records != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.Records != null) {
                                    }
                                    if (z == (that.Records != null)) {
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
                        }
                        z = false;
                        if (z) {
                            if (this.Records != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.Records != null) {
                            }
                            if (z == (that.Records != null)) {
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
                    if (this.Ids != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.Ids != null) {
                    }
                    if (z == (that.Ids != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.DataPackageId != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.DataPackageId != null) {
                            }
                            if (z == (that.DataPackageId != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.Records != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.Records != null) {
                                    }
                                    if (z == (that.Records != null)) {
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
                        }
                        z = false;
                        if (z) {
                            if (this.Records != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.Records != null) {
                            }
                            if (z == (that.Records != null)) {
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
                    if (this.DataPackageId != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.DataPackageId != null) {
                    }
                    if (z == (that.DataPackageId != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.Records != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.Records != null) {
                            }
                            if (z == (that.Records != null)) {
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
                }
                z = false;
                if (z) {
                    if (this.Records != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.Records != null) {
                    }
                    if (z == (that.Records != null)) {
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
            if (this.Version != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.Version != null) {
            }
            if (z == (that.Version != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.Ids != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.Ids != null) {
                    }
                    if (z == (that.Ids != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.DataPackageId != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.DataPackageId != null) {
                            }
                            if (z == (that.DataPackageId != null)) {
                                z = true;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                }
                                z = false;
                                if (z) {
                                    if (this.Records != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (that.Records != null) {
                                    }
                                    if (z == (that.Records != null)) {
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
                        }
                        z = false;
                        if (z) {
                            if (this.Records != null) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (that.Records != null) {
                            }
                            if (z == (that.Records != null)) {
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
                    if (this.DataPackageId != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.DataPackageId != null) {
                    }
                    if (z == (that.DataPackageId != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.Records != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.Records != null) {
                            }
                            if (z == (that.Records != null)) {
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
                }
                z = false;
                if (z) {
                    if (this.Records != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.Records != null) {
                    }
                    if (z == (that.Records != null)) {
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
            if (this.Ids != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.Ids != null) {
            }
            if (z == (that.Ids != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.DataPackageId != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.DataPackageId != null) {
                    }
                    if (z == (that.DataPackageId != null)) {
                        z = true;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                        }
                        z = false;
                        if (z) {
                            if (this.Records != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (that.Records != null) {
                            }
                            if (z == (that.Records != null)) {
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
                }
                z = false;
                if (z) {
                    if (this.Records != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (that.Records != null) {
                    }
                    if (z == (that.Records != null)) {
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
            if (this.DataPackageId != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.DataPackageId != null) {
            }
            if (z == (that.DataPackageId != null)) {
                z = true;
                if (z) {
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                }
                z = false;
                if (z) {
                    if (this.Records != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (that.Records != null) {
                    }
                    if (z == (that.Records != null)) {
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
        }
        z = false;
        if (z) {
            if (this.Records != null) {
                z = false;
            } else {
                z = true;
            }
            if (that.Records != null) {
            }
            if (z == (that.Records != null)) {
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

    protected boolean memberwiseCompareDeep(DataPackage that) {
        Object obj;
        boolean equals;
        Object obj2;
        if (this.Type == null || this.Type.equals(that.Type)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null || !(this.Source == null || this.Source.equals(that.Source))) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null || !(this.Version == null || this.Version.equals(that.Version))) {
            equals = false;
        } else {
            equals = true;
        }
        if (equals && this.Ids != null && this.Ids.size() != 0) {
            for (Entry<String, String> e3 : this.Ids.entrySet()) {
                String val1 = (String) e3.getValue();
                String val2 = (String) that.Ids.get(e3.getKey());
                if (that.Ids.containsKey(e3.getKey())) {
                    equals = true;
                } else {
                    equals = false;
                }
                if (equals) {
                    if (val1 == null) {
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    if ((obj2 == (val2 == null ? 1 : null) ? 1 : null) == null || !(val1 == null || val1.length() == val2.length())) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
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
        if (equals && (this.DataPackageId == null || this.DataPackageId.equals(that.DataPackageId))) {
            equals = true;
        } else {
            equals = false;
        }
        if (equals && this.Records != null && this.Records.size() != 0) {
            for (int i4 = 0; i4 < this.Records.size(); i4++) {
                Record val5 = (Record) this.Records.get(i4);
                Record val6 = (Record) that.Records.get(i4);
                if (equals) {
                    if (val5 == null) {
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    if (obj2 == (val6 == null ? 1 : null)) {
                        obj = 1;
                        if (obj == null && (val5 == null || val5.memberwiseCompare(val6))) {
                            equals = true;
                        } else {
                            equals = false;
                        }
                        if (equals) {
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                }
                equals = false;
                if (equals) {
                    break;
                }
            }
        }
        return equals;
    }
}
