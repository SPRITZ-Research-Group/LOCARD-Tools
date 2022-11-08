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

public class ClientToCollectorRequest implements c, d {
    private ArrayList<DataPackage> DataPackages;
    private int RequestRetryCount;
    private HashMap<String, ArrayList<DataPackage>> TokenToDataPackagesMap;

    public static class Schema {
        private static final h DataPackages_metadata;
        private static final h RequestRetryCount_metadata;
        private static final h TokenToDataPackagesMap_metadata;
        public static final h metadata;
        public static final o schemaDef;

        static {
            h hVar = new h();
            metadata = hVar;
            hVar.a("ClientToCollectorRequest");
            metadata.b("com.microsoft.applications.telemetry.datamodels.ClientToCollectorRequest");
            hVar = new h();
            DataPackages_metadata = hVar;
            hVar.a("DataPackages");
            hVar = new h();
            RequestRetryCount_metadata = hVar;
            hVar.a("RequestRetryCount");
            RequestRetryCount_metadata.a().a(0);
            hVar = new h();
            TokenToDataPackagesMap_metadata = hVar;
            hVar.a("TokenToDataPackagesMap");
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
            field.a(DataPackages_metadata);
            field.b().a(a.BT_LIST);
            field.b().a(new q());
            field.b().a(com.microsoft.applications.telemetry.datamodels.DataPackage.Schema.getTypeDef(schema));
            structDef.b().add(field);
            field = new g();
            field.a((short) 2);
            field.a(RequestRetryCount_metadata);
            field.b().a(a.BT_INT32);
            structDef.b().add(field);
            field = new g();
            field.a((short) 3);
            field.a(TokenToDataPackagesMap_metadata);
            field.b().a(a.BT_MAP);
            field.b().b(new q());
            field.b().a(new q());
            field.b().b().a(a.BT_STRING);
            field.b().a().a(a.BT_LIST);
            field.b().a().a(new q());
            field.b().a().a(com.microsoft.applications.telemetry.datamodels.DataPackage.Schema.getTypeDef(schema));
            structDef.b().add(field);
            return pos;
        }
    }

    public d clone() {
        return null;
    }

    public final ArrayList<DataPackage> getDataPackages() {
        return this.DataPackages;
    }

    public final void setDataPackages(ArrayList<DataPackage> value) {
        this.DataPackages = value;
    }

    public final int getRequestRetryCount() {
        return this.RequestRetryCount;
    }

    public final void setRequestRetryCount(int value) {
        this.RequestRetryCount = value;
    }

    public final HashMap<String, ArrayList<DataPackage>> getTokenToDataPackagesMap() {
        return this.TokenToDataPackagesMap;
    }

    public final void setTokenToDataPackagesMap(HashMap<String, ArrayList<DataPackage>> value) {
        this.TokenToDataPackagesMap = value;
    }

    public Object getField(g fieldDef) {
        switch (fieldDef.a()) {
            case (short) 1:
                return this.DataPackages;
            case (short) 2:
                return Integer.valueOf(this.RequestRetryCount);
            case (short) 3:
                return this.TokenToDataPackagesMap;
            default:
                return null;
        }
    }

    public void setField(g fieldDef, Object value) {
        switch (fieldDef.a()) {
            case (short) 1:
                this.DataPackages = (ArrayList) value;
                return;
            case (short) 2:
                this.RequestRetryCount = ((Integer) value).intValue();
                return;
            case (short) 3:
                this.TokenToDataPackagesMap = (HashMap) value;
                return;
            default:
                return;
        }
    }

    public c createInstance(p structDef) {
        if (com.microsoft.applications.telemetry.datamodels.DataPackage.Schema.metadata == structDef.a()) {
            return new DataPackage();
        }
        return null;
    }

    public o getSchema() {
        return getRuntimeSchema();
    }

    public static o getRuntimeSchema() {
        return Schema.schemaDef;
    }

    public ClientToCollectorRequest() {
        reset();
    }

    public void reset() {
        reset("ClientToCollectorRequest", "com.microsoft.applications.telemetry.datamodels.ClientToCollectorRequest");
    }

    protected void reset(String name, String qualifiedName) {
        if (this.DataPackages == null) {
            this.DataPackages = new ArrayList();
        } else {
            this.DataPackages.clear();
        }
        this.RequestRetryCount = 0;
        if (this.TokenToDataPackagesMap == null) {
            this.TokenToDataPackagesMap = new HashMap();
        } else {
            this.TokenToDataPackagesMap.clear();
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
        readFieldImpl_DataPackages(reader, a.BT_LIST);
        this.RequestRetryCount = reader.o();
        readFieldImpl_TokenToDataPackagesMap(reader, a.BT_MAP);
    }

    protected boolean readTagged(k reader, boolean isBase) throws IOException {
        k.a fieldTag;
        while (true) {
            fieldTag = reader.a();
            if (fieldTag.b != a.BT_STOP && fieldTag.b != a.BT_STOP_BASE) {
                switch (fieldTag.a) {
                    case 1:
                        readFieldImpl_DataPackages(reader, fieldTag.b);
                        break;
                    case 2:
                        this.RequestRetryCount = b.e(reader, fieldTag.b);
                        break;
                    case 3:
                        readFieldImpl_TokenToDataPackagesMap(reader, fieldTag.b);
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

    private void readFieldImpl_DataPackages(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        this.DataPackages.ensureCapacity(tag1.a);
        for (int i3 = 0; i3 < tag1.a; i3++) {
            DataPackage element2 = new DataPackage();
            element2.readNested(reader);
            this.DataPackages.add(element2);
        }
    }

    private void readFieldImpl_TokenToDataPackagesMap(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        k.c tag1 = reader.c();
        b.a(tag1.c, a.BT_LIST);
        for (int i2 = 0; i2 < tag1.a; i2++) {
            ArrayList<DataPackage> val4 = new ArrayList();
            String key3 = b.b(reader, tag1.b);
            k.b tag5 = reader.b();
            b.a(tag5.b, a.BT_STRUCT);
            val4.ensureCapacity(tag5.a);
            for (int i7 = 0; i7 < tag5.a; i7++) {
                DataPackage element6 = new DataPackage();
                element6.readNested(reader);
                val4.add(element6);
            }
            this.TokenToDataPackagesMap.put(key3, val4);
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
        int size1 = this.DataPackages.size();
        if (canOmitFields && size1 == 0) {
            aVar = a.BT_LIST;
            Schema.DataPackages_metadata;
        } else {
            aVar = a.BT_LIST;
            Schema.DataPackages_metadata;
            writer.a(aVar, 1);
            writer.a(size1, a.BT_STRUCT);
            it = this.DataPackages.iterator();
            while (it.hasNext()) {
                ((DataPackage) it.next()).writeNested(writer, false);
            }
        }
        if (canOmitFields && ((long) this.RequestRetryCount) == Schema.RequestRetryCount_metadata.a().c()) {
            aVar = a.BT_INT32;
            Schema.RequestRetryCount_metadata;
        } else {
            aVar = a.BT_INT32;
            Schema.RequestRetryCount_metadata;
            writer.a(aVar, 2);
            writer.a(this.RequestRetryCount);
        }
        int size4 = this.TokenToDataPackagesMap.size();
        if (canOmitFields && size4 == 0) {
            aVar = a.BT_MAP;
            Schema.TokenToDataPackagesMap_metadata;
        } else {
            aVar = a.BT_MAP;
            Schema.TokenToDataPackagesMap_metadata;
            writer.a(aVar, 3);
            writer.a(this.TokenToDataPackagesMap.size(), a.BT_STRING, a.BT_LIST);
            for (Entry<String, ArrayList<DataPackage>> e5 : this.TokenToDataPackagesMap.entrySet()) {
                writer.a((String) e5.getKey());
                writer.a(((ArrayList) e5.getValue()).size(), a.BT_STRUCT);
                Iterator it2 = ((ArrayList) e5.getValue()).iterator();
                while (it2.hasNext()) {
                    ((DataPackage) it2.next()).writeNested(writer, false);
                }
            }
        }
        writer.a(isBase);
    }

    public boolean memberwiseCompare(Object obj) {
        if (obj == null) {
            return false;
        }
        ClientToCollectorRequest that = (ClientToCollectorRequest) obj;
        if (memberwiseCompareQuick(that) && memberwiseCompareDeep(that)) {
            return true;
        }
        return false;
    }

    protected boolean memberwiseCompareQuick(ClientToCollectorRequest that) {
        boolean z;
        if (((this.DataPackages == null) == (that.DataPackages == null)) && (this.DataPackages == null || this.DataPackages.size() == that.DataPackages.size())) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.RequestRetryCount == that.RequestRetryCount) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.TokenToDataPackagesMap == null) {
                z = true;
            } else {
                z = false;
            }
            if (z == (that.TokenToDataPackagesMap == null)) {
                z = true;
                if (z || (this.TokenToDataPackagesMap != null && this.TokenToDataPackagesMap.size() != that.TokenToDataPackagesMap.size())) {
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

    protected boolean memberwiseCompareDeep(ClientToCollectorRequest that) {
        Object obj;
        boolean equals = true;
        if (this.DataPackages != null && this.DataPackages.size() != 0) {
            for (int i1 = 0; i1 < this.DataPackages.size(); i1++) {
                DataPackage val2 = (DataPackage) this.DataPackages.get(i1);
                DataPackage val3 = (DataPackage) that.DataPackages.get(i1);
                if (equals) {
                    if ((val2 == null ? 1 : null) == (val3 == null ? 1 : null)) {
                        obj = 1;
                        equals = obj == null && (val2 == null || val2.memberwiseCompare(val3));
                        if (equals) {
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                }
                if (equals) {
                    break;
                }
            }
        }
        if (equals && this.TokenToDataPackagesMap != null && this.TokenToDataPackagesMap.size() != 0) {
            for (Entry<String, ArrayList<DataPackage>> e6 : this.TokenToDataPackagesMap.entrySet()) {
                ArrayList<DataPackage> val4 = (ArrayList) e6.getValue();
                ArrayList<DataPackage> val5 = (ArrayList) that.TokenToDataPackagesMap.get(e6.getKey());
                equals = equals && that.TokenToDataPackagesMap.containsKey(e6.getKey());
                if (equals) {
                    int i7;
                    DataPackage val8;
                    DataPackage val9;
                    if (equals) {
                        if ((val4 == null ? 1 : null) == (val5 == null ? 1 : null)) {
                            obj = 1;
                            equals = obj == null && (val4 == null || val4.size() == val5.size());
                            if (equals && val4 != null && val4.size() != 0) {
                                for (i7 = 0; i7 < val4.size(); i7++) {
                                    val8 = (DataPackage) val4.get(i7);
                                    val9 = (DataPackage) val5.get(i7);
                                    if (equals) {
                                        if ((val8 != null ? 1 : null) == (val9 != null ? 1 : null)) {
                                            obj = 1;
                                            equals = obj == null && (val8 == null || val8.memberwiseCompare(val9));
                                            if (!equals) {
                                                continue;
                                                break;
                                            }
                                        }
                                    }
                                    obj = null;
                                    if (obj == null) {
                                    }
                                    if (!equals) {
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
                    for (i7 = 0; i7 < val4.size(); i7++) {
                        val8 = (DataPackage) val4.get(i7);
                        val9 = (DataPackage) val5.get(i7);
                        if (equals) {
                            if (val8 != null) {
                            }
                            if (val9 != null) {
                            }
                            if ((val8 != null ? 1 : null) == (val9 != null ? 1 : null)) {
                                obj = 1;
                                if (obj == null) {
                                }
                                if (!equals) {
                                    continue;
                                    break;
                                }
                            }
                        }
                        obj = null;
                        if (obj == null) {
                        }
                        if (!equals) {
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
