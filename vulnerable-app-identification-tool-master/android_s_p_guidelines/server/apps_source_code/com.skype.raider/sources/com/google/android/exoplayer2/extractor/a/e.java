package com.google.android.exoplayer2.extractor.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.i;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.a.d.a;
import com.google.android.exoplayer2.extractor.n;
import com.skype.android.video.hw.utils.CodecUtils;

final class e extends d {
    private final k b = new k(i.a);
    private final k c = new k(4);
    private int d;
    private boolean e;
    private int f;

    public e(n output) {
        super(output);
    }

    protected final boolean a(k data) throws a {
        int header = data.g();
        int frameType = (header >> 4) & 15;
        int videoCodec = header & 15;
        if (videoCodec != 7) {
            throw new a("Video format not supported: " + videoCodec);
        }
        this.f = frameType;
        return frameType != 5;
    }

    protected final void a(k data, long timeUs) throws com.google.android.exoplayer2.k {
        int packetType = data.g();
        timeUs += ((long) data.k()) * 1000;
        if (packetType == 0 && !this.e) {
            k kVar = new k(new byte[data.b()]);
            data.a(kVar.a, 0, data.b());
            com.google.android.exoplayer2.video.a avcConfig = com.google.android.exoplayer2.video.a.a(kVar);
            this.d = avcConfig.b;
            this.a.a(Format.a(null, CodecUtils.MEDIA_TYPE, avcConfig.c, avcConfig.d, avcConfig.a, avcConfig.e));
            this.e = true;
        } else if (packetType == 1 && this.e) {
            byte[] nalLengthData = this.c.a;
            nalLengthData[0] = (byte) 0;
            nalLengthData[1] = (byte) 0;
            nalLengthData[2] = (byte) 0;
            int nalUnitLengthFieldLengthDiff = 4 - this.d;
            int bytesWritten = 0;
            while (data.b() > 0) {
                data.a(this.c.a, nalUnitLengthFieldLengthDiff, this.d);
                this.c.c(0);
                int bytesToWrite = this.c.t();
                this.b.c(0);
                this.a.a(this.b, 4);
                bytesWritten += 4;
                this.a.a(data, bytesToWrite);
                bytesWritten += bytesToWrite;
            }
            this.a.a(timeUs, this.f == 1 ? 1 : 0, bytesWritten, 0, null);
        }
    }
}
