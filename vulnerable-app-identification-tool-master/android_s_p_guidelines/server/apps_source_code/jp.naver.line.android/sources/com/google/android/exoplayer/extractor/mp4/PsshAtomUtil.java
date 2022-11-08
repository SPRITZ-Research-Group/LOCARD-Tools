package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class PsshAtomUtil {
    private PsshAtomUtil() {
    }

    public static byte[] buildPsshAtom(UUID uuid, byte[] bArr) {
        int length = bArr.length + 32;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        allocate.putInt(Atom.TYPE_pssh);
        allocate.putInt(0);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        allocate.putInt(bArr.length);
        allocate.put(bArr);
        return allocate.array();
    }

    public static UUID parseUuid(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        if (!isPsshAtom(parsableByteArray, null)) {
            return null;
        }
        parsableByteArray.setPosition(12);
        return new UUID(parsableByteArray.readLong(), parsableByteArray.readLong());
    }

    public static byte[] parseSchemeSpecificData(byte[] bArr, UUID uuid) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        if (!isPsshAtom(parsableByteArray, uuid)) {
            return null;
        }
        parsableByteArray.setPosition(28);
        int readInt = parsableByteArray.readInt();
        byte[] bArr2 = new byte[readInt];
        parsableByteArray.readBytes(bArr2, 0, readInt);
        return bArr2;
    }

    private static boolean isPsshAtom(ParsableByteArray parsableByteArray, UUID uuid) {
        if (parsableByteArray.limit() < 32) {
            return false;
        }
        parsableByteArray.setPosition(0);
        if (parsableByteArray.readInt() != parsableByteArray.bytesLeft() + 4 || parsableByteArray.readInt() != Atom.TYPE_pssh) {
            return false;
        }
        parsableByteArray.setPosition(12);
        if (uuid == null) {
            parsableByteArray.skipBytes(16);
        } else if (!(parsableByteArray.readLong() == uuid.getMostSignificantBits() && parsableByteArray.readLong() == uuid.getLeastSignificantBits())) {
            return false;
        }
        if (parsableByteArray.readInt() != parsableByteArray.bytesLeft()) {
            return false;
        }
        return true;
    }
}
