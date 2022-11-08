package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.b.k.b;
import com.google.android.exoplayer2.extractor.b.k.c;
import com.google.android.exoplayer2.extractor.b.k.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

final class j extends h {
    private a a;
    private int b;
    private boolean c;
    private d d;
    private b e;

    static final class a {
        public final d a;
        public final b b;
        public final byte[] c;
        public final c[] d;
        public final int e;

        public a(d idHeader, b commentHeader, byte[] setupHeaderData, c[] modes, int iLogModes) {
            this.a = idHeader;
            this.b = commentHeader;
            this.c = setupHeaderData;
            this.d = modes;
            this.e = iLogModes;
        }
    }

    j() {
    }

    public static boolean b(k data) {
        try {
            return k.a(1, data, true);
        } catch (com.google.android.exoplayer2.k e) {
            return false;
        }
    }

    protected final void a(boolean headerData) {
        super.a(headerData);
        if (headerData) {
            this.a = null;
            this.d = null;
            this.e = null;
        }
        this.b = 0;
        this.c = false;
    }

    protected final void c(long currentGranule) {
        boolean z;
        int i = 0;
        super.c(currentGranule);
        if (currentGranule != 0) {
            z = true;
        } else {
            z = false;
        }
        this.c = z;
        if (this.d != null) {
            i = this.d.g;
        }
        this.b = i;
    }

    protected final long a(k packet) {
        int samplesInPacket = 0;
        if ((packet.a[0] & 1) == 1) {
            return -1;
        }
        int packetBlockSize;
        byte b = packet.a[0];
        a aVar = this.a;
        if (aVar.d[(b >> 1) & (255 >>> (8 - aVar.e))].a) {
            packetBlockSize = aVar.a.h;
        } else {
            packetBlockSize = aVar.a.g;
        }
        if (this.c) {
            samplesInPacket = (this.b + packetBlockSize) / 4;
        }
        long j = (long) samplesInPacket;
        packet.b(packet.c() + 4);
        packet.a[packet.c() - 4] = (byte) ((int) (j & 255));
        packet.a[packet.c() - 3] = (byte) ((int) ((j >>> 8) & 255));
        packet.a[packet.c() - 2] = (byte) ((int) ((j >>> 16) & 255));
        packet.a[packet.c() - 1] = (byte) ((int) ((j >>> 24) & 255));
        this.c = true;
        this.b = packetBlockSize;
        return (long) samplesInPacket;
    }

    protected final boolean a(k packet, long position, a setupData) throws IOException, InterruptedException {
        if (this.a != null) {
            return false;
        }
        a aVar;
        long m;
        int g;
        if (this.d == null) {
            k.a(1, packet, false);
            long m2 = packet.m();
            int g2 = packet.g();
            m = packet.m();
            int o = packet.o();
            int o2 = packet.o();
            int o3 = packet.o();
            g = packet.g();
            this.d = new d(m2, g2, m, o, o2, o3, (int) Math.pow(2.0d, (double) (g & 15)), (int) Math.pow(2.0d, (double) ((g & 240) >> 4)), (packet.g() & 1) > 0, Arrays.copyOf(packet.a, packet.c()));
            aVar = null;
        } else if (this.e == null) {
            k.a(3, packet, false);
            String e = packet.e((int) packet.m());
            g = e.length() + 11;
            m = packet.m();
            String[] strArr = new String[((int) m)];
            int i = g + 4;
            for (g = 0; ((long) g) < m; g++) {
                i += 4;
                strArr[g] = packet.e((int) packet.m());
                i += strArr[g].length();
            }
            if ((packet.g() & 1) == 0) {
                throw new com.google.android.exoplayer2.k("framing bit expected to be set");
            }
            this.e = new b(e, strArr, i + 1);
            aVar = null;
        } else {
            Object obj = new byte[packet.c()];
            System.arraycopy(packet.a, 0, obj, 0, packet.c());
            c[] a = k.a(packet, this.d.b);
            aVar = new a(this.d, this.e, obj, a, k.a(a.length - 1));
        }
        this.a = aVar;
        if (this.a == null) {
            return true;
        }
        ArrayList<byte[]> codecInitialisationData = new ArrayList();
        codecInitialisationData.add(this.a.a.j);
        codecInitialisationData.add(this.a.c);
        setupData.a = Format.a(null, "audio/vorbis", this.a.a.e, -1, this.a.a.b, (int) this.a.a.c, codecInitialisationData, null, null);
        return true;
    }
}
