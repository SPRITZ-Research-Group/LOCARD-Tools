package com.google.android.exoplayer.extractor.mp4;

import android.util.Pair;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AtomParsers {

    final class StsdDataHolder {
        public MediaFormat mediaFormat;
        public int nalUnitLengthFieldLength = -1;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdDataHolder(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    public static Track parseTrak(ContainerAtom containerAtom, LeafAtom leafAtom) {
        ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int parseHdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (parseHdlr != Track.TYPE_AUDIO && parseHdlr != Track.TYPE_VIDEO && parseHdlr != Track.TYPE_TEXT && parseHdlr != Track.TYPE_SUBTITLE) {
            return null;
        }
        Pair parseTkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        int intValue = ((Integer) parseTkhd.first).intValue();
        long longValue = ((Long) parseTkhd.second).longValue();
        long parseMvhd = parseMvhd(leafAtom.data);
        long j = -1;
        if (longValue != -1) {
            j = Util.scaleLargeTimestamp(longValue, C.MICROS_PER_SECOND, parseMvhd);
        }
        long j2 = j;
        containerAtom = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        longValue = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdDataHolder parseStsd = parseStsd(containerAtom.getLeafAtomOfType(Atom.TYPE_stsd).data, j2);
        if (parseStsd.mediaFormat == null) {
            return null;
        }
        return new Track(intValue, parseHdlr, longValue, j2, parseStsd.mediaFormat, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength);
    }

    public static TrackSampleTable parseStbl(Track track, ContainerAtom containerAtom) {
        ContainerAtom containerAtom2 = containerAtom;
        ParsableByteArray parsableByteArray = containerAtom2.getLeafAtomOfType(Atom.TYPE_stsz).data;
        LeafAtom leafAtomOfType = containerAtom2.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType == null) {
            leafAtomOfType = containerAtom2.getLeafAtomOfType(Atom.TYPE_co64);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType.data;
        ParsableByteArray parsableByteArray3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray4 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stts).data;
        LeafAtom leafAtomOfType2 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray5 = null;
        ParsableByteArray parsableByteArray6 = leafAtomOfType2 != null ? leafAtomOfType2.data : null;
        LeafAtom leafAtomOfType3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_ctts);
        if (leafAtomOfType3 != null) {
            parsableByteArray5 = leafAtomOfType3.data;
        }
        parsableByteArray.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        int readUnsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt2];
        int[] iArr = new int[readUnsignedIntToInt2];
        long[] jArr2 = new long[readUnsignedIntToInt2];
        int[] iArr2 = new int[readUnsignedIntToInt2];
        if (readUnsignedIntToInt2 == 0) {
            return new TrackSampleTable(jArr, iArr, jArr2, iArr2);
        }
        int readUnsignedIntToInt3;
        int readUnsignedIntToInt4;
        int readUnsignedIntToInt5;
        int readInt;
        boolean z;
        long readUnsignedInt;
        parsableByteArray2.setPosition(12);
        int readUnsignedIntToInt6 = parsableByteArray2.readUnsignedIntToInt();
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt7 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int i = readUnsignedIntToInt6;
        int[] iArr3 = iArr2;
        Assertions.checkState(parsableByteArray3.readInt() == 1, "stsc first chunk must be 1");
        int readUnsignedIntToInt8 = parsableByteArray3.readUnsignedIntToInt();
        parsableByteArray3.skipBytes(4);
        int i2 = -1;
        if (readUnsignedIntToInt7 > 0) {
            readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt() - 1;
            readUnsignedIntToInt6 = 12;
        } else {
            readUnsignedIntToInt6 = 12;
            readUnsignedIntToInt3 = -1;
        }
        parsableByteArray4.setPosition(readUnsignedIntToInt6);
        int readUnsignedIntToInt9 = parsableByteArray4.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt10 = parsableByteArray4.readUnsignedIntToInt();
        int readUnsignedIntToInt11 = parsableByteArray4.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(readUnsignedIntToInt6);
            readUnsignedIntToInt4 = parsableByteArray5.readUnsignedIntToInt() - 1;
            readUnsignedIntToInt5 = parsableByteArray5.readUnsignedIntToInt();
            readInt = parsableByteArray5.readInt();
        } else {
            readUnsignedIntToInt4 = 0;
            readUnsignedIntToInt5 = 0;
            readInt = 0;
        }
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(readUnsignedIntToInt6);
            readUnsignedIntToInt6 = parsableByteArray6.readUnsignedIntToInt();
            if (readUnsignedIntToInt6 <= 0) {
                return new TrackSampleTable(new long[0], new int[0], new long[0], new int[0]);
            }
            z = false;
            i2 = parsableByteArray6.readUnsignedIntToInt() - 1;
        } else {
            z = false;
            readUnsignedIntToInt6 = 0;
        }
        int i3 = readUnsignedIntToInt8;
        if (leafAtomOfType.type == Atom.TYPE_stco) {
            readUnsignedInt = parsableByteArray2.readUnsignedInt();
        } else {
            readUnsignedInt = parsableByteArray2.readUnsignedLongToLong();
        }
        long j = 0;
        ParsableByteArray parsableByteArray7 = parsableByteArray3;
        int i4 = readUnsignedIntToInt3;
        int i5 = readUnsignedIntToInt11;
        readUnsignedIntToInt8 = readInt;
        readUnsignedIntToInt11 = i3;
        int i6 = 0;
        readUnsignedIntToInt3 = readUnsignedIntToInt7;
        readUnsignedIntToInt7 = i2;
        i2 = 0;
        while (i6 < readUnsignedIntToInt2) {
            ParsableByteArray parsableByteArray8;
            LeafAtom leafAtom;
            ParsableByteArray parsableByteArray9;
            jArr[i6] = readUnsignedInt;
            iArr[i6] = readUnsignedIntToInt == 0 ? parsableByteArray.readUnsignedIntToInt() : readUnsignedIntToInt;
            int i7 = readUnsignedIntToInt;
            int i8 = readUnsignedIntToInt2;
            jArr2[i6] = j + ((long) readUnsignedIntToInt8);
            iArr3[i6] = parsableByteArray6 == null ? 1 : 0;
            if (i6 == readUnsignedIntToInt7) {
                iArr3[i6] = 1;
                readUnsignedIntToInt6--;
                if (readUnsignedIntToInt6 > 0) {
                    readUnsignedIntToInt7 = parsableByteArray6.readUnsignedIntToInt() - 1;
                }
            }
            j += (long) i5;
            readUnsignedIntToInt10--;
            if (readUnsignedIntToInt10 == 0 && readUnsignedIntToInt9 > 0) {
                readUnsignedIntToInt9--;
                readUnsignedIntToInt10 = parsableByteArray4.readUnsignedIntToInt();
                i5 = parsableByteArray4.readUnsignedIntToInt();
            }
            if (parsableByteArray5 != null) {
                readUnsignedIntToInt5--;
                if (readUnsignedIntToInt5 == 0 && readUnsignedIntToInt4 > 0) {
                    readUnsignedIntToInt4--;
                    readUnsignedIntToInt5 = parsableByteArray5.readUnsignedIntToInt();
                    readUnsignedIntToInt8 = parsableByteArray5.readInt();
                }
            }
            i3--;
            int i9;
            if (i3 == 0) {
                ParsableByteArray parsableByteArray10;
                readUnsignedIntToInt = i2 + 1;
                readUnsignedIntToInt2 = i;
                if (readUnsignedIntToInt < readUnsignedIntToInt2) {
                    parsableByteArray8 = parsableByteArray;
                    leafAtom = leafAtomOfType;
                    if (leafAtomOfType.type == Atom.TYPE_stco) {
                        readUnsignedInt = parsableByteArray2.readUnsignedInt();
                    } else {
                        readUnsignedInt = parsableByteArray2.readUnsignedLongToLong();
                    }
                } else {
                    parsableByteArray8 = parsableByteArray;
                    leafAtom = leafAtomOfType;
                }
                int i10 = i4;
                if (readUnsignedIntToInt == i10) {
                    readUnsignedIntToInt11 = parsableByteArray7.readUnsignedIntToInt();
                    i9 = i10;
                    parsableByteArray10 = parsableByteArray7;
                    parsableByteArray10.skipBytes(4);
                    readUnsignedIntToInt3--;
                    if (readUnsignedIntToInt3 > 0) {
                        i9 = parsableByteArray10.readUnsignedIntToInt() - 1;
                        if (readUnsignedIntToInt >= readUnsignedIntToInt2) {
                            parsableByteArray9 = parsableByteArray10;
                            i2 = readUnsignedIntToInt;
                            i3 = readUnsignedIntToInt11;
                        } else {
                            parsableByteArray9 = parsableByteArray10;
                            i2 = readUnsignedIntToInt;
                        }
                        i4 = i9;
                    }
                } else {
                    i9 = i10;
                    parsableByteArray10 = parsableByteArray7;
                }
                if (readUnsignedIntToInt >= readUnsignedIntToInt2) {
                    parsableByteArray9 = parsableByteArray10;
                    i2 = readUnsignedIntToInt;
                } else {
                    parsableByteArray9 = parsableByteArray10;
                    i2 = readUnsignedIntToInt;
                    i3 = readUnsignedIntToInt11;
                }
                i4 = i9;
            } else {
                parsableByteArray8 = parsableByteArray;
                leafAtom = leafAtomOfType;
                readUnsignedIntToInt2 = i;
                i9 = i4;
                parsableByteArray9 = parsableByteArray7;
                readUnsignedInt += (long) iArr[i6];
            }
            i6++;
            i = readUnsignedIntToInt2;
            readUnsignedIntToInt = i7;
            readUnsignedIntToInt2 = i8;
            parsableByteArray = parsableByteArray8;
            leafAtomOfType = leafAtom;
            parsableByteArray7 = parsableByteArray9;
        }
        Util.scaleLargeTimestampsInPlace(jArr2, C.MICROS_PER_SECOND, track.timescale);
        Assertions.checkArgument(readUnsignedIntToInt6 == 0);
        Assertions.checkArgument(readUnsignedIntToInt10 == 0);
        Assertions.checkArgument(i3 == 0);
        Assertions.checkArgument(readUnsignedIntToInt9 == 0);
        if (readUnsignedIntToInt4 == 0) {
            z = true;
        }
        Assertions.checkArgument(z);
        return new TrackSampleTable(jArr, iArr, jArr2, iArr3);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static Pair<Integer, Long> parseTkhd(ParsableByteArray parsableByteArray) {
        long j;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        Object obj = null;
        for (int i2 = 0; i2 < i; i2++) {
            if (parsableByteArray.data[position + i2] != (byte) -1) {
                break;
            }
        }
        obj = 1;
        if (obj != null) {
            parsableByteArray.skipBytes(i);
            j = -1;
        } else {
            j = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
        }
        return Pair.create(Integer.valueOf(readInt), Long.valueOf(j));
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static long parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static StsdDataHolder parseStsd(ParsableByteArray parsableByteArray, long j) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        long j2 = j;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdDataHolder stsdDataHolder = new StsdDataHolder(readInt);
        for (int i = 0; i < readInt; i++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263) {
                parseVideoSampleEntry(parsableByteArray, position, readInt2, j, stsdDataHolder, i);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, j, stsdDataHolder, i);
            } else if (readInt3 == Atom.TYPE_TTML) {
                stsdDataHolder.mediaFormat = MediaFormat.createTextFormat(MimeTypes.APPLICATION_TTML, j2);
            } else if (readInt3 == Atom.TYPE_tx3g) {
                stsdDataHolder.mediaFormat = MediaFormat.createTextFormat(MimeTypes.APPLICATION_TX3G, j2);
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdDataHolder;
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, long j, StsdDataHolder stsdDataHolder, int i3) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i4 = i2;
        StsdDataHolder stsdDataHolder2 = stsdDataHolder;
        parsableByteArray.setPosition(i + 8);
        parsableByteArray.skipBytes(24);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        String str = null;
        List list = null;
        float f = 1.0f;
        while (position - i < i4) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0 && parsableByteArray.getPosition() - i == i4) {
                break;
            }
            boolean z = true;
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            Pair parseAvcCFromParent;
            if (readInt2 == Atom.TYPE_avcC) {
                if (str != null) {
                    z = false;
                }
                Assertions.checkState(z);
                str = MimeTypes.VIDEO_H264;
                parseAvcCFromParent = parseAvcCFromParent(parsableByteArray, position2);
                list = (List) parseAvcCFromParent.first;
                stsdDataHolder2.nalUnitLengthFieldLength = ((Integer) parseAvcCFromParent.second).intValue();
            } else if (readInt2 == Atom.TYPE_hvcC) {
                if (str != null) {
                    z = false;
                }
                Assertions.checkState(z);
                str = MimeTypes.VIDEO_H265;
                parseAvcCFromParent = parseHvcCFromParent(parsableByteArray, position2);
                list = (List) parseAvcCFromParent.first;
                stsdDataHolder2.nalUnitLengthFieldLength = ((Integer) parseAvcCFromParent.second).intValue();
            } else if (readInt2 == Atom.TYPE_d263) {
                if (str != null) {
                    z = false;
                }
                Assertions.checkState(z);
                str = MimeTypes.VIDEO_H263;
            } else if (readInt2 == Atom.TYPE_esds) {
                if (str != null) {
                    z = false;
                }
                Assertions.checkState(z);
                Pair parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                String str2 = (String) parseEsdsFromParent.first;
                list = Collections.singletonList(parseEsdsFromParent.second);
                str = str2;
            } else if (readInt2 == Atom.TYPE_sinf) {
                stsdDataHolder2.trackEncryptionBoxes[i3] = parseSinfFromParent(parsableByteArray, position2, readInt);
            } else if (readInt2 == Atom.TYPE_pasp) {
                f = parsePaspFromParent(parsableByteArray, position2);
            }
            position += readInt;
        }
        if (str != null) {
            stsdDataHolder2.mediaFormat = MediaFormat.createVideoFormat(str, -1, j, readUnsignedShort, readUnsignedShort2, f, list);
        }
    }

    private static Pair<List<byte[]>, Integer> parseAvcCFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition((i + 8) + 4);
        i = (parsableByteArray.readUnsignedByte() & 3) + 1;
        if (i != 3) {
            List arrayList = new ArrayList();
            int readUnsignedByte = parsableByteArray.readUnsignedByte() & 31;
            for (int i2 = 0; i2 < readUnsignedByte; i2++) {
                arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
            }
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            for (int i3 = 0; i3 < readUnsignedByte; i3++) {
                arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
            }
            return Pair.create(arrayList, Integer.valueOf(i));
        }
        throw new IllegalStateException();
    }

    private static Pair<List<byte[]>, Integer> parseHvcCFromParent(ParsableByteArray parsableByteArray, int i) {
        int i2;
        int readUnsignedShort;
        Object obj;
        parsableByteArray.setPosition((i + 8) + 21);
        i = parsableByteArray.readUnsignedByte() & 3;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int position = parsableByteArray.getPosition();
        int i3 = 0;
        int i4 = 0;
        while (i3 < readUnsignedByte) {
            parsableByteArray.skipBytes(1);
            int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
            i2 = i4;
            for (i4 = 0; i4 < readUnsignedShort2; i4++) {
                readUnsignedShort = parsableByteArray.readUnsignedShort();
                i2 += readUnsignedShort + 4;
                parsableByteArray.skipBytes(readUnsignedShort);
            }
            i3++;
            i4 = i2;
        }
        parsableByteArray.setPosition(position);
        Object obj2 = new byte[i4];
        i3 = 0;
        i2 = 0;
        while (i3 < readUnsignedByte) {
            parsableByteArray.skipBytes(1);
            readUnsignedShort = parsableByteArray.readUnsignedShort();
            int i5 = i2;
            for (i2 = 0; i2 < readUnsignedShort; i2++) {
                int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, obj2, i5, NalUnitUtil.NAL_START_CODE.length);
                i5 += NalUnitUtil.NAL_START_CODE.length;
                System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), obj2, i5, readUnsignedShort3);
                i5 += readUnsignedShort3;
                parsableByteArray.skipBytes(readUnsignedShort3);
            }
            i3++;
            i2 = i5;
        }
        if (i4 == 0) {
            obj = null;
        } else {
            obj = Collections.singletonList(obj2);
        }
        return Pair.create(obj, Integer.valueOf(i + 1));
    }

    private static TrackEncryptionBox parseSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        TrackEncryptionBox trackEncryptionBox = null;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                parsableByteArray.readInt();
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                parsableByteArray.readInt();
                parsableByteArray.readInt();
            } else if (readInt2 == Atom.TYPE_schi) {
                trackEncryptionBox = parseSchiFromParent(parsableByteArray, i3, readInt);
            }
            i3 += readInt;
        }
        return trackEncryptionBox;
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                parsableByteArray.skipBytes(4);
                i = parsableByteArray.readInt();
                boolean z = true;
                if ((i >> 8) != 1) {
                    z = false;
                }
                i &= 255;
                byte[] bArr = new byte[16];
                parsableByteArray.readBytes(bArr, 0, 16);
                return new TrackEncryptionBox(z, i, bArr);
            }
            i3 += readInt;
        }
        return null;
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, long j, StsdDataHolder stsdDataHolder, int i4) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdDataHolder stsdDataHolder2 = stsdDataHolder;
        parsableByteArray2.setPosition(i2 + 8);
        parsableByteArray2.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(4);
        int readUnsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
        String str = i5 == Atom.TYPE_ac_3 ? MimeTypes.AUDIO_AC3 : i5 == Atom.TYPE_ec_3 ? MimeTypes.AUDIO_EC3 : null;
        int position = parsableByteArray.getPosition();
        int i6 = readUnsignedFixedPoint1616;
        String str2 = str;
        Object obj = null;
        while (position - i2 < i3) {
            parsableByteArray2.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (i5 == Atom.TYPE_mp4a || i5 == Atom.TYPE_enca) {
                if (readInt2 == Atom.TYPE_esds) {
                    Pair parseEsdsFromParent = parseEsdsFromParent(parsableByteArray2, position2);
                    str = (String) parseEsdsFromParent.first;
                    Object obj2 = (byte[]) parseEsdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str)) {
                        Pair parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(obj2);
                        i6 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                        readUnsignedShort = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                    }
                    String str3 = str;
                    obj = obj2;
                    str2 = str3;
                } else if (readInt2 == Atom.TYPE_sinf) {
                    stsdDataHolder2.trackEncryptionBoxes[i4] = parseSinfFromParent(parsableByteArray2, position2, readInt);
                }
            } else if (i5 == Atom.TYPE_ac_3 && readInt2 == Atom.TYPE_dac3) {
                parsableByteArray2.setPosition(position2 + 8);
                stsdDataHolder2.mediaFormat = Ac3Util.parseAnnexFAc3Format(parsableByteArray);
                return;
            } else if (i5 == Atom.TYPE_ec_3 && readInt2 == Atom.TYPE_dec3) {
                parsableByteArray2.setPosition(position2 + 8);
                stsdDataHolder2.mediaFormat = Ac3Util.parseAnnexFEAc3Format(parsableByteArray);
                return;
            }
            position += readInt;
        }
        if (str2 != null) {
            stsdDataHolder2.mediaFormat = MediaFormat.createAudioFormat(str2, readUnsignedShort2, j, readUnsignedShort, i6, obj == null ? null : Collections.singletonList(obj));
        }
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition((i + 8) + 4);
        parsableByteArray.skipBytes(1);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        while (readUnsignedByte > 127) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
        }
        parsableByteArray.skipBytes(2);
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte2 & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte2 & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte2 & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        readUnsignedByte = parsableByteArray.readUnsignedByte();
        while (readUnsignedByte > 127) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
        }
        Object obj = null;
        switch (parsableByteArray.readUnsignedByte()) {
            case 32:
                obj = MimeTypes.VIDEO_MP4V;
                break;
            case 33:
                obj = MimeTypes.VIDEO_H264;
                break;
            case 35:
                obj = MimeTypes.VIDEO_H265;
                break;
            case 64:
                obj = MimeTypes.AUDIO_AAC;
                break;
            case 107:
                return Pair.create(MimeTypes.AUDIO_MPEG, null);
            case 165:
                obj = MimeTypes.AUDIO_AC3;
                break;
            case 166:
                obj = MimeTypes.AUDIO_EC3;
                break;
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        i = parsableByteArray.readUnsignedByte();
        readUnsignedByte = i & 127;
        while (i > 127) {
            i = parsableByteArray.readUnsignedByte();
            readUnsignedByte = (readUnsignedByte << 8) | (i & 127);
        }
        Object obj2 = new byte[readUnsignedByte];
        parsableByteArray.readBytes(obj2, 0, readUnsignedByte);
        return Pair.create(obj, obj2);
    }

    private AtomParsers() {
    }
}
