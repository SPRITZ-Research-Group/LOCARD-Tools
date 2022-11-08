package com.google.android.exoplayer2.metadata.scte35;

import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.b;
import com.google.android.exoplayer2.metadata.d;
import java.nio.ByteBuffer;

public final class a implements com.google.android.exoplayer2.metadata.a {
    private final k a = new k();
    private final j b = new j();
    private q c;

    public final Metadata a(d inputBuffer) throws b {
        if (this.c == null || inputBuffer.d != this.c.c()) {
            this.c = new q(inputBuffer.c);
            this.c.b(inputBuffer.c - inputBuffer.d);
        }
        ByteBuffer buffer = inputBuffer.b;
        byte[] data = buffer.array();
        int size = buffer.limit();
        this.a.a(data, size);
        this.b.a(data, size);
        this.b.b(39);
        long ptsAdjustment = (((long) this.b.c(1)) << 32) | ((long) this.b.c(32));
        this.b.b(20);
        int spliceCommandLength = this.b.c(12);
        int spliceCommandType = this.b.c(8);
        SpliceCommand command = null;
        this.a.d(14);
        switch (spliceCommandType) {
            case 0:
                command = new SpliceNullCommand();
                break;
            case 4:
                command = SpliceScheduleCommand.a(this.a);
                break;
            case 5:
                command = SpliceInsertCommand.a(this.a, ptsAdjustment, this.c);
                break;
            case 6:
                command = TimeSignalCommand.a(this.a, ptsAdjustment, this.c);
                break;
            case 255:
                command = PrivateCommand.a(this.a, spliceCommandLength, ptsAdjustment);
                break;
        }
        if (command == null) {
            return new Metadata(new Entry[0]);
        }
        return new Metadata(command);
    }
}
