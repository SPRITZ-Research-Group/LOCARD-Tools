package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceScheduleCommand extends SpliceCommand {
    public static final Creator<SpliceScheduleCommand> CREATOR = new Creator<SpliceScheduleCommand>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SpliceScheduleCommand[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SpliceScheduleCommand(parcel, (byte) 0);
        }
    };
    public final List<b> a;

    public static final class a {
        public final int a;
        public final long b;

        /* synthetic */ a(int x0, long x1, byte b) {
            this(x0, x1);
        }

        private a(int componentTag, long utcSpliceTime) {
            this.a = componentTag;
            this.b = utcSpliceTime;
        }
    }

    public static final class b {
        public final long a;
        public final boolean b;
        public final boolean c;
        public final boolean d;
        public final long e;
        public final List<a> f;
        public final boolean g;
        public final long h;
        public final int i;
        public final int j;
        public final int k;

        private b(long spliceEventId, boolean spliceEventCancelIndicator, boolean outOfNetworkIndicator, boolean programSpliceFlag, List<a> componentSpliceList, long utcSpliceTime, boolean autoReturn, long breakDuration, int uniqueProgramId, int availNum, int availsExpected) {
            this.a = spliceEventId;
            this.b = spliceEventCancelIndicator;
            this.c = outOfNetworkIndicator;
            this.d = programSpliceFlag;
            this.f = Collections.unmodifiableList(componentSpliceList);
            this.e = utcSpliceTime;
            this.g = autoReturn;
            this.h = breakDuration;
            this.i = uniqueProgramId;
            this.j = availNum;
            this.k = availsExpected;
        }

        private b(Parcel in) {
            boolean z;
            boolean z2 = true;
            this.a = in.readLong();
            if (in.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            this.b = z;
            if (in.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            this.c = z;
            if (in.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            this.d = z;
            int componentSpliceListLength = in.readInt();
            ArrayList<a> componentSpliceList = new ArrayList(componentSpliceListLength);
            for (int i = 0; i < componentSpliceListLength; i++) {
                componentSpliceList.add(new a(in.readInt(), in.readLong()));
            }
            this.f = Collections.unmodifiableList(componentSpliceList);
            this.e = in.readLong();
            if (in.readByte() != (byte) 1) {
                z2 = false;
            }
            this.g = z2;
            this.h = in.readLong();
            this.i = in.readInt();
            this.j = in.readInt();
            this.k = in.readInt();
        }

        static /* synthetic */ b a(k x0) {
            long j;
            boolean z;
            long l = x0.l();
            boolean z2 = (x0.g() & 128) != 0;
            boolean z3 = false;
            long j2 = -9223372036854775807L;
            List arrayList = new ArrayList();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            boolean z4 = false;
            long j3 = -9223372036854775807L;
            if (z2) {
                j = -9223372036854775807L;
                z4 = false;
                z = false;
            } else {
                Object obj;
                long l2;
                ArrayList arrayList2;
                int g = x0.g();
                boolean z5 = (g & 128) != 0;
                boolean z6 = (g & 64) != 0;
                if ((g & 32) != 0) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (z6) {
                    l2 = x0.l();
                } else {
                    l2 = -9223372036854775807L;
                }
                if (z6) {
                    arrayList2 = arrayList;
                } else {
                    int g2 = x0.g();
                    arrayList2 = new ArrayList(g2);
                    for (g = 0; g < g2; g++) {
                        arrayList2.add(new a(x0.g(), x0.l(), (byte) 0));
                    }
                }
                if (obj != null) {
                    long g3 = (long) x0.g();
                    z4 = (128 & g3) != 0;
                    j3 = ((g3 & 1) << 32) | x0.l();
                }
                i = x0.h();
                i2 = x0.g();
                i3 = x0.g();
                arrayList = arrayList2;
                z3 = z6;
                j2 = l2;
                j = j3;
                z = z4;
                z4 = z5;
            }
            return new b(l, z2, z4, z3, arrayList, j2, z, j, i, i2, i3);
        }
    }

    /* synthetic */ SpliceScheduleCommand(Parcel x0, byte b) {
        this(x0);
    }

    private SpliceScheduleCommand(List<b> events) {
        this.a = Collections.unmodifiableList(events);
    }

    private SpliceScheduleCommand(Parcel in) {
        int eventsSize = in.readInt();
        ArrayList<b> events = new ArrayList(eventsSize);
        for (int i = 0; i < eventsSize; i++) {
            events.add(new b(in));
        }
        this.a = Collections.unmodifiableList(events);
    }

    static SpliceScheduleCommand a(k sectionData) {
        int spliceCount = sectionData.g();
        List events = new ArrayList(spliceCount);
        for (int i = 0; i < spliceCount; i++) {
            events.add(b.a(sectionData));
        }
        return new SpliceScheduleCommand(events);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        int eventsSize = this.a.size();
        dest.writeInt(eventsSize);
        for (int i = 0; i < eventsSize; i++) {
            int i2;
            b bVar = (b) this.a.get(i);
            dest.writeLong(bVar.a);
            dest.writeByte((byte) (bVar.b ? 1 : 0));
            if (bVar.c) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            dest.writeByte((byte) i2);
            if (bVar.d) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            dest.writeByte((byte) i2);
            int size = bVar.f.size();
            dest.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                a aVar = (a) bVar.f.get(i3);
                dest.writeInt(aVar.a);
                dest.writeLong(aVar.b);
            }
            dest.writeLong(bVar.e);
            dest.writeByte((byte) (bVar.g ? 1 : 0));
            dest.writeLong(bVar.h);
            dest.writeInt(bVar.i);
            dest.writeInt(bVar.j);
            dest.writeInt(bVar.k);
        }
    }
}
