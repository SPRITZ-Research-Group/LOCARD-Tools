package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class g extends h {
    private static final int a = s.e("Opus");
    private static final byte[] b = new byte[]{(byte) 79, (byte) 112, (byte) 117, (byte) 115, (byte) 72, (byte) 101, (byte) 97, (byte) 100};
    private boolean c;

    g() {
    }

    public static boolean b(k data) {
        if (data.b() < b.length) {
            return false;
        }
        byte[] header = new byte[b.length];
        data.a(header, 0, b.length);
        return Arrays.equals(header, b);
    }

    protected final void a(boolean headerData) {
        super.a(headerData);
        if (headerData) {
            this.c = false;
        }
    }

    protected final long a(k packet) {
        int i;
        byte[] bArr = packet.a;
        int i2 = bArr[0] & 255;
        switch (i2 & 3) {
            case 0:
                i = 1;
                break;
            case 1:
            case 2:
                i = 2;
                break;
            default:
                i = bArr[1] & 63;
                break;
        }
        int i3 = i2 >> 3;
        i2 = i3 & 3;
        if (i3 >= 16) {
            i3 = 2500 << i2;
        } else if (i3 >= 12) {
            i3 = 10000 << (i2 & 1);
        } else if (i2 == 3) {
            i3 = 60000;
        } else {
            i3 = 10000 << i2;
        }
        return b((long) (i3 * i));
    }

    protected final boolean a(k packet, long position, a setupData) throws IOException, InterruptedException {
        if (this.c) {
            boolean headerPacket = packet.n() == a;
            packet.c(0);
            return headerPacket;
        }
        byte[] metadata = Arrays.copyOf(packet.a, packet.c());
        int channelCount = metadata[9] & 255;
        int preskip = ((metadata[11] & 255) << 8) | (metadata[10] & 255);
        List<byte[]> initializationData = new ArrayList(3);
        initializationData.add(metadata);
        a(initializationData, preskip);
        a(initializationData, 3840);
        setupData.a = Format.a(null, "audio/opus", -1, -1, channelCount, 48000, initializationData, null, null);
        this.c = true;
        return true;
    }

    private static void a(List<byte[]> initializationData, int samples) {
        initializationData.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((((long) samples) * 1000000000) / 48000).array());
    }
}
