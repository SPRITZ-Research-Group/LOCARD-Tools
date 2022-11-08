package okhttp3.internal.http2;

import c.c;
import c.e;
import c.f;
import c.l;
import c.t;
import com.adjust.sdk.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.Util;

final class Hpack {
    static final Map<f, Integer> NAME_TO_FIRST_INDEX = nameToFirstIndex();
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    static final Header[] STATIC_HEADER_TABLE = new Header[]{new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, Constants.SCHEME), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};

    static final class Reader {
        Header[] dynamicTable;
        int dynamicTableByteCount;
        int headerCount;
        private final List<Header> headerList;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;
        int nextHeaderIndex;
        private final e source;

        Reader(int headerTableSizeSetting, t source) {
            this(headerTableSizeSetting, headerTableSizeSetting, source);
        }

        Reader(int headerTableSizeSetting, int maxDynamicTableByteCount, t source) {
            this.headerList = new ArrayList();
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = headerTableSizeSetting;
            this.maxDynamicTableByteCount = maxDynamicTableByteCount;
            this.source = l.a(source);
        }

        final int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        private void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount >= this.dynamicTableByteCount) {
                return;
            }
            if (this.maxDynamicTableByteCount == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int bytesToRecover) {
            int entriesToEvict = 0;
            if (bytesToRecover > 0) {
                for (int j = this.dynamicTable.length - 1; j >= this.nextHeaderIndex && bytesToRecover > 0; j--) {
                    bytesToRecover -= this.dynamicTable[j].hpackSize;
                    this.dynamicTableByteCount -= this.dynamicTable[j].hpackSize;
                    this.headerCount--;
                    entriesToEvict++;
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, (this.nextHeaderIndex + 1) + entriesToEvict, this.headerCount);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        final void readHeaders() throws IOException {
            while (!this.source.e()) {
                int b = this.source.h() & 255;
                if (b == 128) {
                    throw new IOException("index == 0");
                } else if ((b & 128) == 128) {
                    readIndexedHeader(readInt(b, Hpack.PREFIX_7_BITS) - 1);
                } else if (b == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((b & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(b, 63) - 1);
                } else if ((b & 32) == 32) {
                    this.maxDynamicTableByteCount = readInt(b, 31);
                    if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting) {
                        throw new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount);
                    }
                    adjustDynamicTableByteCount();
                } else if (b == 16 || b == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(b, 15) - 1);
                }
            }
        }

        public final List<Header> getAndResetHeaderList() {
            List<Header> result = new ArrayList(this.headerList);
            this.headerList.clear();
            return result;
        }

        private void readIndexedHeader(int index) throws IOException {
            if (isStaticHeader(index)) {
                this.headerList.add(Hpack.STATIC_HEADER_TABLE[index]);
                return;
            }
            int dynamicTableIndex = dynamicTableIndex(index - Hpack.STATIC_HEADER_TABLE.length);
            if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
                throw new IOException("Header index too large " + (index + 1));
            }
            this.headerList.add(this.dynamicTable[dynamicTableIndex]);
        }

        private int dynamicTableIndex(int index) {
            return (this.nextHeaderIndex + 1) + index;
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
            this.headerList.add(new Header(getName(index), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            this.headerList.add(new Header(Hpack.checkLowercase(readByteString()), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
            insertIntoDynamicTable(-1, new Header(getName(nameIndex), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            insertIntoDynamicTable(-1, new Header(Hpack.checkLowercase(readByteString()), readByteString()));
        }

        private f getName(int index) throws IOException {
            if (isStaticHeader(index)) {
                return Hpack.STATIC_HEADER_TABLE[index].name;
            }
            int dynamicTableIndex = dynamicTableIndex(index - Hpack.STATIC_HEADER_TABLE.length);
            if (dynamicTableIndex >= 0 && dynamicTableIndex < this.dynamicTable.length) {
                return this.dynamicTable[dynamicTableIndex].name;
            }
            throw new IOException("Header index too large " + (index + 1));
        }

        private boolean isStaticHeader(int index) {
            return index >= 0 && index <= Hpack.STATIC_HEADER_TABLE.length - 1;
        }

        private void insertIntoDynamicTable(int index, Header entry) {
            this.headerList.add(entry);
            int delta = entry.hpackSize;
            if (index != -1) {
                delta -= this.dynamicTable[dynamicTableIndex(index)].hpackSize;
            }
            if (delta > this.maxDynamicTableByteCount) {
                clearDynamicTable();
                return;
            }
            int entriesEvicted = evictToRecoverBytes((this.dynamicTableByteCount + delta) - this.maxDynamicTableByteCount);
            if (index == -1) {
                if (this.headerCount + 1 > this.dynamicTable.length) {
                    Header[] doubled = new Header[(this.dynamicTable.length * 2)];
                    System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = doubled;
                }
                index = this.nextHeaderIndex;
                this.nextHeaderIndex = index - 1;
                this.dynamicTable[index] = entry;
                this.headerCount++;
            } else {
                this.dynamicTable[index + (dynamicTableIndex(index) + entriesEvicted)] = entry;
            }
            this.dynamicTableByteCount += delta;
        }

        private int readByte() throws IOException {
            return this.source.h() & 255;
        }

        final int readInt(int firstByte, int prefixMask) throws IOException {
            int prefix = firstByte & prefixMask;
            if (prefix < prefixMask) {
                return prefix;
            }
            int result = prefixMask;
            int shift = 0;
            while (true) {
                int b = readByte();
                if ((b & 128) == 0) {
                    return result + (b << shift);
                }
                result += (b & Hpack.PREFIX_7_BITS) << shift;
                shift += 7;
            }
        }

        final f readByteString() throws IOException {
            int firstByte = readByte();
            boolean huffmanDecode = (firstByte & 128) == 128;
            int length = readInt(firstByte, Hpack.PREFIX_7_BITS);
            if (huffmanDecode) {
                return f.a(Huffman.get().decode(this.source.g((long) length)));
            }
            return this.source.d((long) length);
        }
    }

    static final class Writer {
        private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
        private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
        Header[] dynamicTable;
        int dynamicTableByteCount;
        private boolean emitDynamicTableSizeUpdate;
        int headerCount;
        int headerTableSizeSetting;
        int maxDynamicTableByteCount;
        int nextHeaderIndex;
        private final c out;
        private int smallestHeaderTableSizeSetting;
        private final boolean useCompression;

        Writer(c out) {
            this(SETTINGS_HEADER_TABLE_SIZE, true, out);
        }

        Writer(int headerTableSizeSetting, boolean useCompression, c out) {
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = headerTableSizeSetting;
            this.maxDynamicTableByteCount = headerTableSizeSetting;
            this.useCompression = useCompression;
            this.out = out;
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int bytesToRecover) {
            int entriesToEvict = 0;
            if (bytesToRecover > 0) {
                for (int j = this.dynamicTable.length - 1; j >= this.nextHeaderIndex && bytesToRecover > 0; j--) {
                    bytesToRecover -= this.dynamicTable[j].hpackSize;
                    this.dynamicTableByteCount -= this.dynamicTable[j].hpackSize;
                    this.headerCount--;
                    entriesToEvict++;
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, (this.nextHeaderIndex + 1) + entriesToEvict, this.headerCount);
                Arrays.fill(this.dynamicTable, this.nextHeaderIndex + 1, (this.nextHeaderIndex + 1) + entriesToEvict, null);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        private void insertIntoDynamicTable(Header entry) {
            int delta = entry.hpackSize;
            if (delta > this.maxDynamicTableByteCount) {
                clearDynamicTable();
                return;
            }
            evictToRecoverBytes((this.dynamicTableByteCount + delta) - this.maxDynamicTableByteCount);
            if (this.headerCount + 1 > this.dynamicTable.length) {
                Header[] doubled = new Header[(this.dynamicTable.length * 2)];
                System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = doubled;
            }
            int index = this.nextHeaderIndex;
            this.nextHeaderIndex = index - 1;
            this.dynamicTable[index] = entry;
            this.headerCount++;
            this.dynamicTableByteCount += delta;
        }

        final void writeHeaders(List<Header> headerBlock) throws IOException {
            if (this.emitDynamicTableSizeUpdate) {
                if (this.smallestHeaderTableSizeSetting < this.maxDynamicTableByteCount) {
                    writeInt(this.smallestHeaderTableSizeSetting, 31, 32);
                }
                this.emitDynamicTableSizeUpdate = false;
                this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                writeInt(this.maxDynamicTableByteCount, 31, 32);
            }
            int size = headerBlock.size();
            for (int i = 0; i < size; i++) {
                Header header = (Header) headerBlock.get(i);
                f name = header.name.g();
                f value = header.value;
                int headerIndex = -1;
                int headerNameIndex = -1;
                Integer staticIndex = (Integer) Hpack.NAME_TO_FIRST_INDEX.get(name);
                if (staticIndex != null) {
                    headerNameIndex = staticIndex.intValue() + 1;
                    if (headerNameIndex > 1 && headerNameIndex < 8) {
                        if (Util.equal(Hpack.STATIC_HEADER_TABLE[headerNameIndex - 1].value, value)) {
                            headerIndex = headerNameIndex;
                        } else if (Util.equal(Hpack.STATIC_HEADER_TABLE[headerNameIndex].value, value)) {
                            headerIndex = headerNameIndex + 1;
                        }
                    }
                }
                if (headerIndex == -1) {
                    int length = this.dynamicTable.length;
                    for (int j = this.nextHeaderIndex + 1; j < length; j++) {
                        if (Util.equal(this.dynamicTable[j].name, name)) {
                            if (Util.equal(this.dynamicTable[j].value, value)) {
                                headerIndex = (j - this.nextHeaderIndex) + Hpack.STATIC_HEADER_TABLE.length;
                                break;
                            } else if (headerNameIndex == -1) {
                                headerNameIndex = (j - this.nextHeaderIndex) + Hpack.STATIC_HEADER_TABLE.length;
                            }
                        }
                    }
                }
                if (headerIndex != -1) {
                    writeInt(headerIndex, Hpack.PREFIX_7_BITS, 128);
                } else if (headerNameIndex == -1) {
                    this.out.b(64);
                    writeByteString(name);
                    writeByteString(value);
                    insertIntoDynamicTable(header);
                } else {
                    f fVar = Header.PSEUDO_PREFIX;
                    if (!name.a(0, fVar, 0, fVar.h()) || Header.TARGET_AUTHORITY.equals(name)) {
                        writeInt(headerNameIndex, 63, 64);
                        writeByteString(value);
                        insertIntoDynamicTable(header);
                    } else {
                        writeInt(headerNameIndex, 15, 0);
                        writeByteString(value);
                    }
                }
            }
        }

        final void writeInt(int value, int prefixMask, int bits) {
            if (value < prefixMask) {
                this.out.b(bits | value);
                return;
            }
            this.out.b(bits | prefixMask);
            value -= prefixMask;
            while (value >= 128) {
                this.out.b((value & Hpack.PREFIX_7_BITS) | 128);
                value >>>= 7;
            }
            this.out.b(value);
        }

        final void writeByteString(f data) throws IOException {
            if (!this.useCompression || Huffman.get().encodedLength(data) >= data.h()) {
                writeInt(data.h(), Hpack.PREFIX_7_BITS, 0);
                this.out.a(data);
                return;
            }
            c huffmanBuffer = new c();
            Huffman.get().encode(data, huffmanBuffer);
            f huffmanBytes = huffmanBuffer.p();
            writeInt(huffmanBytes.h(), Hpack.PREFIX_7_BITS, 128);
            this.out.a(huffmanBytes);
        }

        final void setHeaderTableSizeSetting(int headerTableSizeSetting) {
            this.headerTableSizeSetting = headerTableSizeSetting;
            int effectiveHeaderTableSize = Math.min(headerTableSizeSetting, SETTINGS_HEADER_TABLE_SIZE_LIMIT);
            if (this.maxDynamicTableByteCount != effectiveHeaderTableSize) {
                if (effectiveHeaderTableSize < this.maxDynamicTableByteCount) {
                    this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, effectiveHeaderTableSize);
                }
                this.emitDynamicTableSizeUpdate = true;
                this.maxDynamicTableByteCount = effectiveHeaderTableSize;
                adjustDynamicTableByteCount();
            }
        }

        private void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount >= this.dynamicTableByteCount) {
                return;
            }
            if (this.maxDynamicTableByteCount == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
            }
        }
    }

    private Hpack() {
    }

    private static Map<f, Integer> nameToFirstIndex() {
        Map<f, Integer> result = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        for (int i = 0; i < STATIC_HEADER_TABLE.length; i++) {
            if (!result.containsKey(STATIC_HEADER_TABLE[i].name)) {
                result.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(result);
    }

    static f checkLowercase(f name) throws IOException {
        int i = 0;
        int length = name.h();
        while (i < length) {
            byte c = name.a(i);
            if (c < (byte) 65 || c > (byte) 90) {
                i++;
            } else {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + name.a());
            }
        }
        return name;
    }
}
