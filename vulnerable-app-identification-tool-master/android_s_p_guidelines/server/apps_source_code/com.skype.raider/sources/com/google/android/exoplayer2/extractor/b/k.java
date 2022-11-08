package com.google.android.exoplayer2.extractor.b;

final class k {

    public static final class a {
        public final int a;
        public final int b;
        public final long[] c;
        public final int d;
        public final boolean e;

        public a(int dimensions, int entries, long[] lengthMap, int lookupType, boolean isOrdered) {
            this.a = dimensions;
            this.b = entries;
            this.c = lengthMap;
            this.d = lookupType;
            this.e = isOrdered;
        }
    }

    public static final class b {
        public final String a;
        public final String[] b;
        public final int c;

        public b(String vendor, String[] comments, int length) {
            this.a = vendor;
            this.b = comments;
            this.c = length;
        }
    }

    public static final class c {
        public final boolean a;
        public final int b;
        public final int c;
        public final int d;

        public c(boolean blockFlag, int windowType, int transformType, int mapping) {
            this.a = blockFlag;
            this.b = windowType;
            this.c = transformType;
            this.d = mapping;
        }
    }

    public static final class d {
        public final long a;
        public final int b;
        public final long c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final boolean i;
        public final byte[] j;

        public d(long version, int channels, long sampleRate, int bitrateMax, int bitrateNominal, int bitrateMin, int blockSize0, int blockSize1, boolean framingFlag, byte[] data) {
            this.a = version;
            this.b = channels;
            this.c = sampleRate;
            this.d = bitrateMax;
            this.e = bitrateNominal;
            this.f = bitrateMin;
            this.g = blockSize0;
            this.h = blockSize1;
            this.i = framingFlag;
            this.j = data;
        }
    }

    public static int a(int x) {
        int val = 0;
        while (x > 0) {
            val++;
            x >>>= 1;
        }
        return val;
    }

    public static boolean a(int headerType, com.google.android.exoplayer2.d.k header, boolean quiet) throws com.google.android.exoplayer2.k {
        if (header.b() < 7) {
            if (quiet) {
                return false;
            }
            throw new com.google.android.exoplayer2.k("too short header: " + header.b());
        } else if (header.g() != headerType) {
            if (quiet) {
                return false;
            }
            throw new com.google.android.exoplayer2.k("expected header type " + Integer.toHexString(headerType));
        } else if (header.g() == 118 && header.g() == 111 && header.g() == 114 && header.g() == 98 && header.g() == 105 && header.g() == 115) {
            return true;
        } else {
            if (quiet) {
                return false;
            }
            throw new com.google.android.exoplayer2.k("expected characters 'vorbis'");
        }
    }

    public static c[] a(com.google.android.exoplayer2.d.k headerData, int channels) throws com.google.android.exoplayer2.k {
        int i;
        a(5, headerData, false);
        int numberOfBooks = headerData.g() + 1;
        i bitArray = new i(headerData.a);
        bitArray.b(headerData.d() * 8);
        for (i = 0; i < numberOfBooks; i++) {
            if (bitArray.a(24) != 5653314) {
                throw new com.google.android.exoplayer2.k("expected code book to start with [0x56, 0x43, 0x42] at " + bitArray.b());
            }
            int a;
            int i2;
            int a2 = bitArray.a(16);
            int a3 = bitArray.a(24);
            long[] jArr = new long[a3];
            boolean a4 = bitArray.a();
            if (a4) {
                a = bitArray.a(5) + 1;
                i2 = 0;
                while (i2 < jArr.length) {
                    int a5 = bitArray.a(a(a3 - i2));
                    int i3 = 0;
                    while (i3 < a5 && i2 < jArr.length) {
                        jArr[i2] = (long) a;
                        i3++;
                        i2++;
                    }
                    a++;
                }
            } else {
                boolean a6 = bitArray.a();
                for (i2 = 0; i2 < jArr.length; i2++) {
                    if (!a6 || bitArray.a()) {
                        jArr[i2] = (long) (bitArray.a(5) + 1);
                    } else {
                        jArr[i2] = 0;
                    }
                }
            }
            a = bitArray.a(4);
            if (a > 2) {
                throw new com.google.android.exoplayer2.k("lookup type greater than 2 not decodable: " + a);
            }
            if (a == 1 || a == 2) {
                long j;
                bitArray.b(32);
                bitArray.b(32);
                i2 = bitArray.a(4) + 1;
                bitArray.b(1);
                if (a != 1) {
                    j = (long) (a3 * a2);
                } else if (a2 != 0) {
                    j = (long) Math.floor(Math.pow((double) ((long) a3), 1.0d / ((double) ((long) a2))));
                } else {
                    j = 0;
                }
                bitArray.b((int) (j * ((long) i2)));
            }
            a aVar = new a(a2, a3, jArr, a, a4);
        }
        int timeCount = bitArray.a(6) + 1;
        for (i = 0; i < timeCount; i++) {
            if (bitArray.a(16) != 0) {
                throw new com.google.android.exoplayer2.k("placeholder of time domain transforms not zeroed out");
            }
        }
        c(bitArray);
        b(bitArray);
        a(channels, bitArray);
        c[] modes = a(bitArray);
        if (bitArray.a()) {
            return modes;
        }
        throw new com.google.android.exoplayer2.k("framing bit after modes not set as expected");
    }

    private static c[] a(i bitArray) {
        int modeCount = bitArray.a(6) + 1;
        c[] modes = new c[modeCount];
        for (int i = 0; i < modeCount; i++) {
            modes[i] = new c(bitArray.a(), bitArray.a(16), bitArray.a(16), bitArray.a(8));
        }
        return modes;
    }

    private static void a(int channels, i bitArray) throws com.google.android.exoplayer2.k {
        int mappingsCount = bitArray.a(6) + 1;
        for (int i = 0; i < mappingsCount; i++) {
            switch (bitArray.a(16)) {
                case 0:
                    int submaps;
                    int j;
                    if (bitArray.a()) {
                        submaps = bitArray.a(4) + 1;
                    } else {
                        submaps = 1;
                    }
                    if (bitArray.a()) {
                        int couplingSteps = bitArray.a(8) + 1;
                        for (j = 0; j < couplingSteps; j++) {
                            bitArray.b(a(channels - 1));
                            bitArray.b(a(channels - 1));
                        }
                    }
                    if (bitArray.a(2) == 0) {
                        if (submaps > 1) {
                            for (j = 0; j < channels; j++) {
                                bitArray.b(4);
                            }
                        }
                        for (j = 0; j < submaps; j++) {
                            bitArray.b(8);
                            bitArray.b(8);
                            bitArray.b(8);
                        }
                        break;
                    }
                    throw new com.google.android.exoplayer2.k("to reserved bits must be zero after mapping coupling steps");
                default:
                    break;
            }
        }
    }

    private static void b(i bitArray) throws com.google.android.exoplayer2.k {
        int residueCount = bitArray.a(6) + 1;
        for (int i = 0; i < residueCount; i++) {
            if (bitArray.a(16) > 2) {
                throw new com.google.android.exoplayer2.k("residueType greater than 2 is not decodable");
            }
            int j;
            bitArray.b(24);
            bitArray.b(24);
            bitArray.b(24);
            int classifications = bitArray.a(6) + 1;
            bitArray.b(8);
            int[] cascade = new int[classifications];
            for (j = 0; j < classifications; j++) {
                int highBits = 0;
                int lowBits = bitArray.a(3);
                if (bitArray.a()) {
                    highBits = bitArray.a(5);
                }
                cascade[j] = (highBits * 8) + lowBits;
            }
            for (j = 0; j < classifications; j++) {
                for (int k = 0; k < 8; k++) {
                    if ((cascade[j] & (1 << k)) != 0) {
                        bitArray.b(8);
                    }
                }
            }
        }
    }

    private static void c(i bitArray) throws com.google.android.exoplayer2.k {
        int floorCount = bitArray.a(6) + 1;
        for (int i = 0; i < floorCount; i++) {
            int floorType = bitArray.a(16);
            int j;
            switch (floorType) {
                case 0:
                    bitArray.b(8);
                    bitArray.b(16);
                    bitArray.b(16);
                    bitArray.b(6);
                    bitArray.b(8);
                    int floorNumberOfBooks = bitArray.a(4) + 1;
                    for (j = 0; j < floorNumberOfBooks; j++) {
                        bitArray.b(8);
                    }
                    break;
                case 1:
                    int k;
                    int partitions = bitArray.a(5);
                    int maximumClass = -1;
                    int[] partitionClassList = new int[partitions];
                    for (j = 0; j < partitions; j++) {
                        partitionClassList[j] = bitArray.a(4);
                        if (partitionClassList[j] > maximumClass) {
                            maximumClass = partitionClassList[j];
                        }
                    }
                    int[] classDimensions = new int[(maximumClass + 1)];
                    for (j = 0; j < classDimensions.length; j++) {
                        classDimensions[j] = bitArray.a(3) + 1;
                        int classSubclasses = bitArray.a(2);
                        if (classSubclasses > 0) {
                            bitArray.b(8);
                        }
                        for (k = 0; k < (1 << classSubclasses); k++) {
                            bitArray.b(8);
                        }
                    }
                    bitArray.b(2);
                    int rangeBits = bitArray.a(4);
                    int count = 0;
                    k = 0;
                    for (j = 0; j < partitions; j++) {
                        count += classDimensions[partitionClassList[j]];
                        while (k < count) {
                            bitArray.b(rangeBits);
                            k++;
                        }
                    }
                    break;
                default:
                    throw new com.google.android.exoplayer2.k("floor type greater than 1 not decodable: " + floorType);
            }
        }
    }
}
