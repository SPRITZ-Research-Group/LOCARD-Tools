package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import com.google.android.exoplayer2.d.k;
import java.util.UUID;

public final class g {
    public static UUID a(byte[] atom) {
        Pair<UUID, byte[]> parsedAtom = b(atom);
        if (parsedAtom == null) {
            return null;
        }
        return (UUID) parsedAtom.first;
    }

    public static byte[] a(byte[] atom, UUID uuid) {
        Pair<UUID, byte[]> parsedAtom = b(atom);
        if (parsedAtom == null) {
            return null;
        }
        if (uuid == null || uuid.equals(parsedAtom.first)) {
            return (byte[]) parsedAtom.second;
        }
        new StringBuilder("UUID mismatch. Expected: ").append(uuid).append(", got: ").append(parsedAtom.first).append(".");
        return null;
    }

    private static Pair<UUID, byte[]> b(byte[] atom) {
        k atomData = new k(atom);
        if (atomData.c() < 32) {
            return null;
        }
        atomData.c(0);
        if (atomData.n() != atomData.b() + 4 || atomData.n() != a.U) {
            return null;
        }
        int atomVersion = a.a(atomData.n());
        if (atomVersion > 1) {
            return null;
        }
        UUID uuid = new UUID(atomData.p(), atomData.p());
        if (atomVersion == 1) {
            atomData.d(atomData.t() * 16);
        }
        int dataSize = atomData.t();
        if (dataSize != atomData.b()) {
            return null;
        }
        byte[] data = new byte[dataSize];
        atomData.a(data, 0, dataSize);
        return Pair.create(uuid, data);
    }
}
