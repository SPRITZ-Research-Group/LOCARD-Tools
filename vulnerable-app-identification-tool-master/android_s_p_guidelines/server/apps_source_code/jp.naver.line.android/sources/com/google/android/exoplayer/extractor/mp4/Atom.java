package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Atom {
    public static final int FULL_HEADER_SIZE = 12;
    public static final int HEADER_SIZE = 8;
    public static final int LONG_HEADER_SIZE = 16;
    public static final int LONG_SIZE_PREFIX = 1;
    public static final int TYPE_TTML = Util.getIntegerCodeForString("TTML");
    public static final int TYPE_ac_3 = Util.getIntegerCodeForString("ac-3");
    public static final int TYPE_avc1 = Util.getIntegerCodeForString("avc1");
    public static final int TYPE_avc3 = Util.getIntegerCodeForString("avc3");
    public static final int TYPE_avcC = Util.getIntegerCodeForString("avcC");
    public static final int TYPE_co64 = Util.getIntegerCodeForString("co64");
    public static final int TYPE_ctts = Util.getIntegerCodeForString("ctts");
    public static final int TYPE_d263 = Util.getIntegerCodeForString("d263");
    public static final int TYPE_dac3 = Util.getIntegerCodeForString("dac3");
    public static final int TYPE_dec3 = Util.getIntegerCodeForString("dec3");
    public static final int TYPE_ec_3 = Util.getIntegerCodeForString("ec-3");
    public static final int TYPE_enca = Util.getIntegerCodeForString("enca");
    public static final int TYPE_encv = Util.getIntegerCodeForString("encv");
    public static final int TYPE_esds = Util.getIntegerCodeForString("esds");
    public static final int TYPE_frma = Util.getIntegerCodeForString("frma");
    public static final int TYPE_ftyp = Util.getIntegerCodeForString("ftyp");
    public static final int TYPE_hdlr = Util.getIntegerCodeForString("hdlr");
    public static final int TYPE_hev1 = Util.getIntegerCodeForString("hev1");
    public static final int TYPE_hvc1 = Util.getIntegerCodeForString("hvc1");
    public static final int TYPE_hvcC = Util.getIntegerCodeForString("hvcC");
    public static final int TYPE_mdat = Util.getIntegerCodeForString("mdat");
    public static final int TYPE_mdhd = Util.getIntegerCodeForString("mdhd");
    public static final int TYPE_mdia = Util.getIntegerCodeForString("mdia");
    public static final int TYPE_minf = Util.getIntegerCodeForString("minf");
    public static final int TYPE_moof = Util.getIntegerCodeForString("moof");
    public static final int TYPE_moov = Util.getIntegerCodeForString("moov");
    public static final int TYPE_mp4a = Util.getIntegerCodeForString("mp4a");
    public static final int TYPE_mp4v = Util.getIntegerCodeForString("mp4v");
    public static final int TYPE_mvex = Util.getIntegerCodeForString("mvex");
    public static final int TYPE_mvhd = Util.getIntegerCodeForString("mvhd");
    public static final int TYPE_pasp = Util.getIntegerCodeForString("pasp");
    public static final int TYPE_pssh = Util.getIntegerCodeForString("pssh");
    public static final int TYPE_s263 = Util.getIntegerCodeForString("s263");
    public static final int TYPE_saiz = Util.getIntegerCodeForString("saiz");
    public static final int TYPE_schi = Util.getIntegerCodeForString("schi");
    public static final int TYPE_schm = Util.getIntegerCodeForString("schm");
    public static final int TYPE_senc = Util.getIntegerCodeForString("senc");
    public static final int TYPE_sidx = Util.getIntegerCodeForString("sidx");
    public static final int TYPE_sinf = Util.getIntegerCodeForString("sinf");
    public static final int TYPE_smhd = Util.getIntegerCodeForString("smhd");
    public static final int TYPE_stbl = Util.getIntegerCodeForString("stbl");
    public static final int TYPE_stco = Util.getIntegerCodeForString("stco");
    public static final int TYPE_stsc = Util.getIntegerCodeForString("stsc");
    public static final int TYPE_stsd = Util.getIntegerCodeForString("stsd");
    public static final int TYPE_stss = Util.getIntegerCodeForString("stss");
    public static final int TYPE_stsz = Util.getIntegerCodeForString("stsz");
    public static final int TYPE_stts = Util.getIntegerCodeForString("stts");
    public static final int TYPE_tenc = Util.getIntegerCodeForString("tenc");
    public static final int TYPE_tfdt = Util.getIntegerCodeForString("tfdt");
    public static final int TYPE_tfhd = Util.getIntegerCodeForString("tfhd");
    public static final int TYPE_tkhd = Util.getIntegerCodeForString("tkhd");
    public static final int TYPE_traf = Util.getIntegerCodeForString("traf");
    public static final int TYPE_trak = Util.getIntegerCodeForString("trak");
    public static final int TYPE_trex = Util.getIntegerCodeForString("trex");
    public static final int TYPE_trun = Util.getIntegerCodeForString("trun");
    public static final int TYPE_tx3g = Util.getIntegerCodeForString("tx3g");
    public static final int TYPE_uuid = Util.getIntegerCodeForString("uuid");
    public static final int TYPE_vmhd = Util.getIntegerCodeForString("vmhd");
    public final int type;

    final class ContainerAtom extends Atom {
        public final List<ContainerAtom> containerChildren = new ArrayList();
        public final long endPosition;
        public final List<LeafAtom> leafChildren = new ArrayList();

        public ContainerAtom(int i, long j) {
            super(i);
            this.endPosition = j;
        }

        public final void add(LeafAtom leafAtom) {
            this.leafChildren.add(leafAtom);
        }

        public final void add(ContainerAtom containerAtom) {
            this.containerChildren.add(containerAtom);
        }

        public final LeafAtom getLeafAtomOfType(int i) {
            int size = this.leafChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                LeafAtom leafAtom = (LeafAtom) this.leafChildren.get(i2);
                if (leafAtom.type == i) {
                    return leafAtom;
                }
            }
            return null;
        }

        public final ContainerAtom getContainerAtomOfType(int i) {
            int size = this.containerChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                ContainerAtom containerAtom = (ContainerAtom) this.containerChildren.get(i2);
                if (containerAtom.type == i) {
                    return containerAtom;
                }
            }
            return null;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Atom.getAtomTypeString(this.type));
            stringBuilder.append(" leaves: ");
            stringBuilder.append(Arrays.toString(this.leafChildren.toArray(new LeafAtom[0])));
            stringBuilder.append(" containers: ");
            stringBuilder.append(Arrays.toString(this.containerChildren.toArray(new ContainerAtom[0])));
            return stringBuilder.toString();
        }
    }

    final class LeafAtom extends Atom {
        public final ParsableByteArray data;

        public LeafAtom(int i, ParsableByteArray parsableByteArray) {
            super(i);
            this.data = parsableByteArray;
        }
    }

    public static int parseFullAtomFlags(int i) {
        return i & 16777215;
    }

    public static int parseFullAtomVersion(int i) {
        return (i >> 24) & 255;
    }

    public Atom(int i) {
        this.type = i;
    }

    public String toString() {
        return getAtomTypeString(this.type);
    }

    public static String getAtomTypeString(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((char) (i >> 24));
        stringBuilder.append((char) ((i >> 16) & 255));
        stringBuilder.append((char) ((i >> 8) & 255));
        stringBuilder.append((char) (i & 255));
        return stringBuilder.toString();
    }
}
