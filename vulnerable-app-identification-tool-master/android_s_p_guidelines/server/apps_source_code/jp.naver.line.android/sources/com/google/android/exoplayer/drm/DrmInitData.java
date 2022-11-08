package com.google.android.exoplayer.drm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class DrmInitData {
    public final String mimeType;

    public final class Mapped extends DrmInitData {
        private final Map<UUID, byte[]> schemeData = new HashMap();

        public Mapped(String str) {
            super(str);
        }

        public final byte[] get(UUID uuid) {
            return (byte[]) this.schemeData.get(uuid);
        }

        public final void put(UUID uuid, byte[] bArr) {
            this.schemeData.put(uuid, bArr);
        }
    }

    public final class Universal extends DrmInitData {
        private byte[] data;

        public Universal(String str, byte[] bArr) {
            super(str);
            this.data = bArr;
        }

        public final byte[] get(UUID uuid) {
            return this.data;
        }
    }

    public abstract byte[] get(UUID uuid);

    public DrmInitData(String str) {
        this.mimeType = str;
    }
}
