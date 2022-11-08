package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class a {
    public static final int A = s.e("sidx");
    public static final int B = s.e("moov");
    public static final int C = s.e("mvhd");
    public static final int D = s.e("trak");
    public static final int E = s.e("mdia");
    public static final int F = s.e("minf");
    public static final int G = s.e("stbl");
    public static final int H = s.e("avcC");
    public static final int I = s.e("hvcC");
    public static final int J = s.e("esds");
    public static final int K = s.e("moof");
    public static final int L = s.e("traf");
    public static final int M = s.e("mvex");
    public static final int N = s.e("mehd");
    public static final int O = s.e("tkhd");
    public static final int P = s.e("edts");
    public static final int Q = s.e("elst");
    public static final int R = s.e("mdhd");
    public static final int S = s.e("hdlr");
    public static final int T = s.e("stsd");
    public static final int U = s.e("pssh");
    public static final int V = s.e("sinf");
    public static final int W = s.e("schm");
    public static final int X = s.e("schi");
    public static final int Y = s.e("tenc");
    public static final int Z = s.e("encv");
    public static final int a = s.e("ftyp");
    public static final int aA = s.e("udta");
    public static final int aB = s.e("meta");
    public static final int aC = s.e("ilst");
    public static final int aD = s.e("mean");
    public static final int aE = s.e("name");
    public static final int aF = s.e("data");
    public static final int aG = s.e("emsg");
    public static final int aH = s.e("st3d");
    public static final int aI = s.e("sv3d");
    public static final int aJ = s.e("proj");
    public static final int aK = s.e("vp08");
    public static final int aL = s.e("vp09");
    public static final int aM = s.e("vpcC");
    public static final int aN = s.e("camm");
    public static final int aO = s.e("alac");
    public static final int aa = s.e("enca");
    public static final int ab = s.e("frma");
    public static final int ac = s.e("saiz");
    public static final int ad = s.e("saio");
    public static final int ae = s.e("sbgp");
    public static final int af = s.e("sgpd");
    public static final int ag = s.e("uuid");
    public static final int ah = s.e("senc");
    public static final int ai = s.e("pasp");
    public static final int aj = s.e("TTML");
    public static final int ak = s.e("vmhd");
    public static final int al = s.e("mp4v");
    public static final int am = s.e("stts");
    public static final int an = s.e("stss");
    public static final int ao = s.e("ctts");
    public static final int ap = s.e("stsc");
    public static final int aq = s.e("stsz");
    public static final int ar = s.e("stz2");
    public static final int as = s.e("stco");
    public static final int at = s.e("co64");
    public static final int au = s.e("tx3g");
    public static final int av = s.e("wvtt");
    public static final int aw = s.e("stpp");
    public static final int ax = s.e("c608");
    public static final int ay = s.e("samr");
    public static final int az = s.e("sawb");
    public static final int b = s.e("avc1");
    public static final int c = s.e("avc3");
    public static final int d = s.e("hvc1");
    public static final int e = s.e("hev1");
    public static final int f = s.e("s263");
    public static final int g = s.e("d263");
    public static final int h = s.e("mdat");
    public static final int i = s.e("mp4a");
    public static final int j = s.e(".mp3");
    public static final int k = s.e("wave");
    public static final int l = s.e("lpcm");
    public static final int m = s.e("sowt");
    public static final int n = s.e("ac-3");
    public static final int o = s.e("dac3");
    public static final int p = s.e("ec-3");
    public static final int q = s.e("dec3");
    public static final int r = s.e("dtsc");
    public static final int s = s.e("dtsh");
    public static final int t = s.e("dtsl");
    public static final int u = s.e("dtse");
    public static final int v = s.e("ddts");
    public static final int w = s.e("tfdt");
    public static final int x = s.e("tfhd");
    public static final int y = s.e("trex");
    public static final int z = s.e("trun");
    public final int aP;

    static final class a extends a {
        public final long aQ;
        public final List<b> aR = new ArrayList();
        public final List<a> aS = new ArrayList();

        public a(int type, long endPosition) {
            super(type);
            this.aQ = endPosition;
        }

        public final void a(b atom) {
            this.aR.add(atom);
        }

        public final void a(a atom) {
            this.aS.add(atom);
        }

        public final b d(int type) {
            int childrenSize = this.aR.size();
            for (int i = 0; i < childrenSize; i++) {
                b atom = (b) this.aR.get(i);
                if (atom.aP == type) {
                    return atom;
                }
            }
            return null;
        }

        public final a e(int type) {
            int childrenSize = this.aS.size();
            for (int i = 0; i < childrenSize; i++) {
                a atom = (a) this.aS.get(i);
                if (atom.aP == type) {
                    return atom;
                }
            }
            return null;
        }

        public final String toString() {
            return a.c(this.aP) + " leaves: " + Arrays.toString(this.aR.toArray()) + " containers: " + Arrays.toString(this.aS.toArray());
        }
    }

    static final class b extends a {
        public final k aQ;

        public b(int type, k data) {
            super(type);
            this.aQ = data;
        }
    }

    public a(int type) {
        this.aP = type;
    }

    public String toString() {
        return c(this.aP);
    }

    public static int a(int fullAtomInt) {
        return (fullAtomInt >> 24) & 255;
    }

    public static int b(int fullAtomInt) {
        return 16777215 & fullAtomInt;
    }

    public static String c(int type) {
        return ((char) ((type >> 24) & 255)) + ((char) ((type >> 16) & 255)) + ((char) ((type >> 8) & 255)) + ((char) (type & 255));
    }
}
