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
import com.microsoft.applications.telemetry.PiiKind;
import java.io.IOException;
import java.io.InputStream;

public class PII implements c, d {
    private PiiKind Kind;
    private String RawContent;
    private PIIScrubber ScrubType;

    public static class Schema {
        private static final h Kind_metadata;
        private static final h RawContent_metadata;
        private static final h ScrubType_metadata;
        public static final h metadata;
        public static final o schemaDef;

        static {
            h hVar = new h();
            metadata = hVar;
            hVar.a("PII");
            metadata.b("PII");
            hVar = new h();
            ScrubType_metadata = hVar;
            hVar.a("ScrubType");
            ScrubType_metadata.a().a((long) PIIScrubber.NotSet.getValue());
            hVar = new h();
            Kind_metadata = hVar;
            hVar.a("Kind");
            Kind_metadata.a().a((long) PiiKind.NONE.getValue());
            hVar = new h();
            RawContent_metadata = hVar;
            hVar.a("RawContent");
            RawContent_metadata.a().f();
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
            field.a(ScrubType_metadata);
            field.b().a(a.BT_INT32);
            structDef.b().add(field);
            field = new g();
            field.a((short) 2);
            field.a(Kind_metadata);
            field.b().a(a.BT_INT32);
            structDef.b().add(field);
            field = new g();
            field.a((short) 3);
            field.a(RawContent_metadata);
            field.b().a(a.BT_STRING);
            structDef.b().add(field);
            return pos;
        }
    }

    public d clone() {
        return null;
    }

    public final PIIScrubber getScrubType() {
        return this.ScrubType;
    }

    public final void setScrubType(PIIScrubber value) {
        this.ScrubType = value;
    }

    public final PiiKind getKind() {
        return this.Kind;
    }

    public final void setKind(PiiKind value) {
        this.Kind = value;
    }

    public final String getRawContent() {
        return this.RawContent;
    }

    public final void setRawContent(String value) {
        this.RawContent = value;
    }

    public Object getField(g fieldDef) {
        switch (fieldDef.a()) {
            case (short) 1:
                return this.ScrubType;
            case (short) 2:
                return this.Kind;
            case (short) 3:
                return this.RawContent;
            default:
                return null;
        }
    }

    public void setField(g fieldDef, Object value) {
        switch (fieldDef.a()) {
            case (short) 1:
                this.ScrubType = (PIIScrubber) value;
                return;
            case (short) 2:
                this.Kind = (PiiKind) value;
                return;
            case (short) 3:
                this.RawContent = (String) value;
                return;
            default:
                return;
        }
    }

    public c createInstance(p structDef) {
        return null;
    }

    public o getSchema() {
        return getRuntimeSchema();
    }

    public static o getRuntimeSchema() {
        return Schema.schemaDef;
    }

    public PII() {
        reset();
    }

    public void reset() {
        reset("PII", "PII");
    }

    protected void reset(String name, String qualifiedName) {
        this.ScrubType = PIIScrubber.NotSet;
        this.Kind = PiiKind.NONE;
        this.RawContent = null;
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
        this.ScrubType = PIIScrubber.fromValue(reader.o());
        this.Kind = PiiKind.fromValue(reader.o());
        this.RawContent = reader.e();
    }

    protected boolean readTagged(k reader, boolean isBase) throws IOException {
        k.a fieldTag;
        while (true) {
            fieldTag = reader.a();
            if (fieldTag.b != a.BT_STOP && fieldTag.b != a.BT_STOP_BASE) {
                switch (fieldTag.a) {
                    case 1:
                        this.ScrubType = PIIScrubber.fromValue(b.e(reader, fieldTag.b));
                        break;
                    case 2:
                        this.Kind = PiiKind.fromValue(b.e(reader, fieldTag.b));
                        break;
                    case 3:
                        this.RawContent = b.b(reader, fieldTag.b);
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

    public void marshal(n writer) throws IOException {
    }

    public void write(n writer) throws IOException {
        writeNested(writer, false);
    }

    public void writeNested(n writer, boolean isBase) throws IOException {
        a aVar;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = Schema.metadata;
        if (canOmitFields && ((long) this.ScrubType.getValue()) == Schema.ScrubType_metadata.a().c()) {
            aVar = a.BT_INT32;
            Schema.ScrubType_metadata;
        } else {
            aVar = a.BT_INT32;
            Schema.ScrubType_metadata;
            writer.a(aVar, 1);
            writer.a(this.ScrubType.getValue());
        }
        if (canOmitFields && ((long) this.Kind.getValue()) == Schema.Kind_metadata.a().c()) {
            aVar = a.BT_INT32;
            Schema.Kind_metadata;
        } else {
            aVar = a.BT_INT32;
            Schema.Kind_metadata;
            writer.a(aVar, 2);
            writer.a(this.Kind.getValue());
        }
        if (canOmitFields && this.RawContent == null) {
            aVar = a.BT_STRING;
            Schema.RawContent_metadata;
        } else {
            aVar = a.BT_STRING;
            Schema.RawContent_metadata;
            writer.a(aVar, 3);
            writer.a(this.RawContent);
        }
        writer.a(isBase);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        PII that = (PII) obj;
        if (memberwiseCompareQuick(that) && memberwiseCompareDeep(that)) {
            return true;
        }
        return false;
    }

    protected boolean memberwiseCompareQuick(PII that) {
        boolean z;
        if (this.ScrubType == that.ScrubType) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.Kind == that.Kind) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.RawContent == null) {
                z = true;
            } else {
                z = false;
            }
            if (z == (that.RawContent == null)) {
                z = true;
                if (z || (this.RawContent != null && this.RawContent.length() != that.RawContent.length())) {
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

    protected boolean memberwiseCompareDeep(PII that) {
        return this.RawContent == null || this.RawContent.equals(that.RawContent);
    }
}
