package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;

final class e {
    private static final int A = s.e("sosn");
    private static final int B = s.e("tvsh");
    private static final int C = s.e("----");
    private static final String[] D = new String[]{"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    private static final int a = s.e("nam");
    private static final int b = s.e("trk");
    private static final int c = s.e("cmt");
    private static final int d = s.e("day");
    private static final int e = s.e("ART");
    private static final int f = s.e("too");
    private static final int g = s.e("alb");
    private static final int h = s.e("com");
    private static final int i = s.e("wrt");
    private static final int j = s.e("lyr");
    private static final int k = s.e("gen");
    private static final int l = s.e("covr");
    private static final int m = s.e("gnre");
    private static final int n = s.e("grp");
    private static final int o = s.e("disk");
    private static final int p = s.e("trkn");
    private static final int q = s.e("tmpo");
    private static final int r = s.e("cpil");
    private static final int s = s.e("aART");
    private static final int t = s.e("sonm");
    private static final int u = s.e("soal");
    private static final int v = s.e("soar");
    private static final int w = s.e("soaa");
    private static final int x = s.e("soco");
    private static final int y = s.e("rtng");
    private static final int z = s.e("pgap");

    public static Entry a(k ilst) {
        Entry entry = null;
        int endPosition = ilst.d() + ilst.n();
        int type = ilst.n();
        int typeTopByte = (type >> 24) & 255;
        int n;
        String f;
        if (typeTopByte == 169 || typeTopByte == 65533) {
            int shortType = type & 16777215;
            try {
                if (shortType == c) {
                    n = ilst.n();
                    if (ilst.n() == a.aF) {
                        ilst.d(8);
                        f = ilst.f(n - 16);
                        entry = new CommentFrame("und", f, f);
                    } else {
                        new StringBuilder("Failed to parse comment attribute: ").append(a.c(type));
                    }
                    ilst.c(endPosition);
                } else if (shortType == a || shortType == b) {
                    entry = a(type, "TIT2", ilst);
                    ilst.c(endPosition);
                } else if (shortType == h || shortType == i) {
                    entry = a(type, "TCOM", ilst);
                    ilst.c(endPosition);
                } else if (shortType == d) {
                    entry = a(type, "TDRC", ilst);
                    ilst.c(endPosition);
                } else if (shortType == e) {
                    entry = a(type, "TPE1", ilst);
                    ilst.c(endPosition);
                } else if (shortType == f) {
                    entry = a(type, "TSSE", ilst);
                    ilst.c(endPosition);
                } else if (shortType == g) {
                    entry = a(type, "TALB", ilst);
                    ilst.c(endPosition);
                } else if (shortType == j) {
                    entry = a(type, "USLT", ilst);
                    ilst.c(endPosition);
                } else if (shortType == k) {
                    entry = a(type, "TCON", ilst);
                    ilst.c(endPosition);
                } else {
                    if (shortType == n) {
                        entry = a(type, "TIT1", ilst);
                        ilst.c(endPosition);
                    }
                    new StringBuilder("Skipped unknown metadata entry: ").append(a.c(type));
                    ilst.c(endPosition);
                }
            } catch (Throwable th) {
                ilst.c(endPosition);
            }
        } else if (type == m) {
            n = b(ilst);
            f = (n <= 0 || n > D.length) ? null : D[n - 1];
            if (f != null) {
                entry = new TextInformationFrame("TCON", null, f);
            }
            ilst.c(endPosition);
        } else if (type == o) {
            entry = b(type, "TPOS", ilst);
            ilst.c(endPosition);
        } else if (type == p) {
            entry = b(type, "TRCK", ilst);
            ilst.c(endPosition);
        } else if (type == q) {
            entry = a(type, "TBPM", ilst, true, false);
            ilst.c(endPosition);
        } else if (type == r) {
            entry = a(type, "TCMP", ilst, true, true);
            ilst.c(endPosition);
        } else if (type == l) {
            int n2 = ilst.n();
            if (ilst.n() == a.aF) {
                n = a.b(ilst.n());
                f = n == 13 ? "image/jpeg" : n == 14 ? "image/png" : null;
                if (f != null) {
                    ilst.d(4);
                    byte[] bArr = new byte[(n2 - 16)];
                    ilst.a(bArr, 0, bArr.length);
                    entry = new ApicFrame(f, null, 3, bArr);
                }
            }
            ilst.c(endPosition);
        } else if (type == s) {
            entry = a(type, "TPE2", ilst);
            ilst.c(endPosition);
        } else if (type == t) {
            entry = a(type, "TSOT", ilst);
            ilst.c(endPosition);
        } else if (type == u) {
            entry = a(type, "TSO2", ilst);
            ilst.c(endPosition);
        } else if (type == v) {
            entry = a(type, "TSOA", ilst);
            ilst.c(endPosition);
        } else if (type == w) {
            entry = a(type, "TSOP", ilst);
            ilst.c(endPosition);
        } else if (type == x) {
            entry = a(type, "TSOC", ilst);
            ilst.c(endPosition);
        } else if (type == y) {
            entry = a(type, "ITUNESADVISORY", ilst, false, false);
            ilst.c(endPosition);
        } else if (type == z) {
            entry = a(type, "ITUNESGAPLESS", ilst, false, true);
            ilst.c(endPosition);
        } else if (type == A) {
            entry = a(type, "TVSHOWSORT", ilst);
            ilst.c(endPosition);
        } else if (type == B) {
            entry = a(type, "TVSHOW", ilst);
            ilst.c(endPosition);
        } else {
            if (type == C) {
                entry = a(ilst, endPosition);
                ilst.c(endPosition);
            }
            new StringBuilder("Skipped unknown metadata entry: ").append(a.c(type));
            ilst.c(endPosition);
        }
        return entry;
    }

    private static TextInformationFrame a(int type, String id, k data) {
        int atomSize = data.n();
        if (data.n() == a.aF) {
            data.d(8);
            return new TextInformationFrame(id, null, data.f(atomSize - 16));
        }
        new StringBuilder("Failed to parse text attribute: ").append(a.c(type));
        return null;
    }

    private static Id3Frame a(int type, String id, k data, boolean isTextInformationFrame, boolean isBoolean) {
        int value = b(data);
        if (isBoolean) {
            value = Math.min(1, value);
        }
        if (value < 0) {
            new StringBuilder("Failed to parse uint8 attribute: ").append(a.c(type));
            return null;
        } else if (isTextInformationFrame) {
            return new TextInformationFrame(id, null, Integer.toString(value));
        } else {
            return new CommentFrame("und", id, Integer.toString(value));
        }
    }

    private static TextInformationFrame b(int type, String attributeName, k data) {
        int atomSize = data.n();
        if (data.n() == a.aF && atomSize >= 22) {
            data.d(10);
            int index = data.h();
            if (index > 0) {
                String value = String.valueOf(index);
                int count = data.h();
                if (count > 0) {
                    value = value + "/" + count;
                }
                return new TextInformationFrame(attributeName, null, value);
            }
        }
        new StringBuilder("Failed to parse index/count attribute: ").append(a.c(type));
        return null;
    }

    private static Id3Frame a(k data, int endPosition) {
        String domain = null;
        String name = null;
        int dataAtomPosition = -1;
        int dataAtomSize = -1;
        while (data.d() < endPosition) {
            int atomPosition = data.d();
            int atomSize = data.n();
            int atomType = data.n();
            data.d(4);
            if (atomType == a.aD) {
                domain = data.f(atomSize - 12);
            } else if (atomType == a.aE) {
                name = data.f(atomSize - 12);
            } else {
                if (atomType == a.aF) {
                    dataAtomPosition = atomPosition;
                    dataAtomSize = atomSize;
                }
                data.d(atomSize - 12);
            }
        }
        if (!"com.apple.iTunes".equals(domain) || !"iTunSMPB".equals(name) || dataAtomPosition == -1) {
            return null;
        }
        data.c(dataAtomPosition);
        data.d(16);
        return new CommentFrame("und", name, data.f(dataAtomSize - 16));
    }

    private static int b(k data) {
        data.d(4);
        if (data.n() != a.aF) {
            return -1;
        }
        data.d(8);
        return data.g();
    }
}
