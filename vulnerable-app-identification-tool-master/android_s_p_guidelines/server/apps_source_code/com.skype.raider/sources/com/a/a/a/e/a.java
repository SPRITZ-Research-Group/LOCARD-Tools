package com.a.a.a.e;

public final class a {
    protected final byte[][] a = new byte[a.values().length][];
    protected final char[][] b = new char[b.values().length][];

    public enum a {
        READ_IO_BUFFER(4000),
        WRITE_ENCODING_BUFFER(4000),
        WRITE_CONCAT_BUFFER(2000),
        BASE64_CODEC_BUFFER(2000);
        
        protected final int e;

        private a(int i) {
            this.e = i;
        }
    }

    public enum b {
        TOKEN_BUFFER(2000),
        CONCAT_BUFFER(2000),
        TEXT_BUFFER(200),
        NAME_COPY_BUFFER(200);
        
        protected final int e;

        private b(int i) {
            this.e = i;
        }
    }

    public final char[] a(b bVar, int i) {
        if (bVar.e > 0) {
            i = bVar.e;
        }
        int ordinal = bVar.ordinal();
        char[] cArr = this.b[ordinal];
        if (cArr == null || cArr.length < i) {
            return new char[i];
        }
        this.b[ordinal] = null;
        return cArr;
    }

    public final void a(b bVar, char[] cArr) {
        this.b[bVar.ordinal()] = cArr;
    }
}
