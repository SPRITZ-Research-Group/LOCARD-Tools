package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class f {

    private static class a extends RuntimeException {
        a(String why) {
            super(why);
        }
    }

    public static String[] a(File elfFile) throws IOException {
        FileInputStream is = new FileInputStream(elfFile);
        try {
            String[] a = a(is.getChannel());
            return a;
        } finally {
            is.close();
        }
    }

    private static String[] a(FileChannel fc) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        if (b(fc, bb, 0) != 1179403647) {
            throw new a("file is not ELF");
        }
        long e_phoff;
        long e_phnum;
        int e_phentsize;
        int nr_DT_NEEDED;
        long dyn;
        long ptr_DT_STRTAB;
        long d_tag;
        long off_DT_STRTAB;
        int i;
        long p_vaddr;
        long p_memsz;
        long a;
        String[] needed;
        long d_val;
        StringBuilder stringBuilder;
        long j;
        short d;
        boolean is32 = d(fc, bb, 4) == (short) 1;
        if (d(fc, bb, 5) == (short) 2) {
            bb.order(ByteOrder.BIG_ENDIAN);
        }
        if (is32) {
            e_phoff = b(fc, bb, 28);
        } else {
            e_phoff = a(fc, bb, 32);
        }
        if (is32) {
            e_phnum = (long) c(fc, bb, 44);
        } else {
            e_phnum = (long) c(fc, bb, 56);
        }
        if (is32) {
            e_phentsize = c(fc, bb, 42);
        } else {
            e_phentsize = c(fc, bb, 54);
        }
        if (e_phnum == 65535) {
            long e_shoff;
            if (is32) {
                e_shoff = b(fc, bb, 32);
            } else {
                e_shoff = a(fc, bb, 40);
            }
            if (is32) {
                e_phnum = b(fc, bb, 28 + e_shoff);
            } else {
                e_phnum = b(fc, bb, 44 + e_shoff);
            }
        }
        long dynStart = 0;
        long phdr = e_phoff;
        for (long i2 = 0; i2 < e_phnum; i2++) {
            if (b(fc, bb, 0 + phdr) == 2) {
                if (is32) {
                    dynStart = b(fc, bb, 4 + phdr);
                } else {
                    dynStart = a(fc, bb, 8 + phdr);
                }
                if (dynStart != 0) {
                    throw new a("ELF file does not contain dynamic linking information");
                }
                nr_DT_NEEDED = 0;
                dyn = dynStart;
                ptr_DT_STRTAB = 0;
                do {
                    if (is32) {
                        d_tag = a(fc, bb, 0 + dyn);
                    } else {
                        d_tag = b(fc, bb, 0 + dyn);
                    }
                    if (d_tag != 1) {
                        if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                            throw new a("malformed DT_NEEDED section");
                        }
                        nr_DT_NEEDED++;
                    } else if (d_tag == 5) {
                        if (is32) {
                            ptr_DT_STRTAB = a(fc, bb, 8 + dyn);
                        } else {
                            ptr_DT_STRTAB = b(fc, bb, 4 + dyn);
                        }
                    }
                    dyn += is32 ? 8 : 16;
                } while (d_tag != 0);
                if (ptr_DT_STRTAB != 0) {
                    throw new a("Dynamic section string-table not found");
                }
                off_DT_STRTAB = 0;
                phdr = e_phoff;
                for (i = 0; ((long) i) < e_phnum; i++) {
                    if (b(fc, bb, 0 + phdr) != 1) {
                        if (is32) {
                            p_vaddr = a(fc, bb, 16 + phdr);
                        } else {
                            p_vaddr = b(fc, bb, 8 + phdr);
                        }
                        if (is32) {
                            p_memsz = a(fc, bb, 40 + phdr);
                        } else {
                            p_memsz = b(fc, bb, 20 + phdr);
                        }
                        if (p_vaddr <= ptr_DT_STRTAB && ptr_DT_STRTAB < p_vaddr + p_memsz) {
                            if (is32) {
                                a = a(fc, bb, 8 + phdr);
                            } else {
                                a = b(fc, bb, 4 + phdr);
                            }
                            off_DT_STRTAB = a + (ptr_DT_STRTAB - p_vaddr);
                            if (off_DT_STRTAB != 0) {
                                throw new a("did not find file offset of DT_STRTAB table");
                            }
                            needed = new String[nr_DT_NEEDED];
                            nr_DT_NEEDED = 0;
                            dyn = dynStart;
                            do {
                                if (is32) {
                                    d_tag = a(fc, bb, 0 + dyn);
                                } else {
                                    d_tag = b(fc, bb, 0 + dyn);
                                }
                                if (d_tag == 1) {
                                    if (is32) {
                                        d_val = a(fc, bb, 8 + dyn);
                                    } else {
                                        d_val = b(fc, bb, 4 + dyn);
                                    }
                                    a = off_DT_STRTAB + d_val;
                                    stringBuilder = new StringBuilder();
                                    while (true) {
                                        j = 1 + a;
                                        d = d(fc, bb, a);
                                        if (d == (short) 0) {
                                            break;
                                        }
                                        stringBuilder.append((char) d);
                                        a = j;
                                    }
                                    needed[nr_DT_NEEDED] = stringBuilder.toString();
                                    if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                        throw new a("malformed DT_NEEDED section");
                                    }
                                    nr_DT_NEEDED++;
                                }
                                dyn += is32 ? 8 : 16;
                            } while (d_tag != 0);
                            if (nr_DT_NEEDED == needed.length) {
                                return needed;
                            }
                            throw new a("malformed DT_NEEDED section");
                        }
                    }
                    phdr += (long) e_phentsize;
                }
                if (off_DT_STRTAB != 0) {
                    needed = new String[nr_DT_NEEDED];
                    nr_DT_NEEDED = 0;
                    dyn = dynStart;
                    do {
                        if (is32) {
                            d_tag = a(fc, bb, 0 + dyn);
                        } else {
                            d_tag = b(fc, bb, 0 + dyn);
                        }
                        if (d_tag == 1) {
                            if (is32) {
                                d_val = a(fc, bb, 8 + dyn);
                            } else {
                                d_val = b(fc, bb, 4 + dyn);
                            }
                            a = off_DT_STRTAB + d_val;
                            stringBuilder = new StringBuilder();
                            while (true) {
                                j = 1 + a;
                                d = d(fc, bb, a);
                                if (d == (short) 0) {
                                    break;
                                }
                                stringBuilder.append((char) d);
                                a = j;
                            }
                            needed[nr_DT_NEEDED] = stringBuilder.toString();
                            if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                nr_DT_NEEDED++;
                            } else {
                                throw new a("malformed DT_NEEDED section");
                            }
                        }
                        if (is32) {
                        }
                        dyn += is32 ? 8 : 16;
                    } while (d_tag != 0);
                    if (nr_DT_NEEDED == needed.length) {
                        return needed;
                    }
                    throw new a("malformed DT_NEEDED section");
                }
                throw new a("did not find file offset of DT_STRTAB table");
            }
            phdr += (long) e_phentsize;
        }
        if (dynStart != 0) {
            nr_DT_NEEDED = 0;
            dyn = dynStart;
            ptr_DT_STRTAB = 0;
            do {
                if (is32) {
                    d_tag = a(fc, bb, 0 + dyn);
                } else {
                    d_tag = b(fc, bb, 0 + dyn);
                }
                if (d_tag != 1) {
                    if (d_tag == 5) {
                        if (is32) {
                            ptr_DT_STRTAB = a(fc, bb, 8 + dyn);
                        } else {
                            ptr_DT_STRTAB = b(fc, bb, 4 + dyn);
                        }
                    }
                } else if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                    nr_DT_NEEDED++;
                } else {
                    throw new a("malformed DT_NEEDED section");
                }
                if (is32) {
                }
                dyn += is32 ? 8 : 16;
            } while (d_tag != 0);
            if (ptr_DT_STRTAB != 0) {
                off_DT_STRTAB = 0;
                phdr = e_phoff;
                for (i = 0; ((long) i) < e_phnum; i++) {
                    if (b(fc, bb, 0 + phdr) != 1) {
                        if (is32) {
                            p_vaddr = a(fc, bb, 16 + phdr);
                        } else {
                            p_vaddr = b(fc, bb, 8 + phdr);
                        }
                        if (is32) {
                            p_memsz = a(fc, bb, 40 + phdr);
                        } else {
                            p_memsz = b(fc, bb, 20 + phdr);
                        }
                        if (is32) {
                            a = a(fc, bb, 8 + phdr);
                        } else {
                            a = b(fc, bb, 4 + phdr);
                        }
                        off_DT_STRTAB = a + (ptr_DT_STRTAB - p_vaddr);
                        if (off_DT_STRTAB != 0) {
                            throw new a("did not find file offset of DT_STRTAB table");
                        }
                        needed = new String[nr_DT_NEEDED];
                        nr_DT_NEEDED = 0;
                        dyn = dynStart;
                        do {
                            if (is32) {
                                d_tag = b(fc, bb, 0 + dyn);
                            } else {
                                d_tag = a(fc, bb, 0 + dyn);
                            }
                            if (d_tag == 1) {
                                if (is32) {
                                    d_val = b(fc, bb, 4 + dyn);
                                } else {
                                    d_val = a(fc, bb, 8 + dyn);
                                }
                                a = off_DT_STRTAB + d_val;
                                stringBuilder = new StringBuilder();
                                while (true) {
                                    j = 1 + a;
                                    d = d(fc, bb, a);
                                    if (d == (short) 0) {
                                        break;
                                    }
                                    stringBuilder.append((char) d);
                                    a = j;
                                }
                                needed[nr_DT_NEEDED] = stringBuilder.toString();
                                if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                    throw new a("malformed DT_NEEDED section");
                                }
                                nr_DT_NEEDED++;
                            }
                            if (is32) {
                            }
                            dyn += is32 ? 8 : 16;
                        } while (d_tag != 0);
                        if (nr_DT_NEEDED == needed.length) {
                            return needed;
                        }
                        throw new a("malformed DT_NEEDED section");
                    }
                    phdr += (long) e_phentsize;
                }
                if (off_DT_STRTAB != 0) {
                    needed = new String[nr_DT_NEEDED];
                    nr_DT_NEEDED = 0;
                    dyn = dynStart;
                    do {
                        if (is32) {
                            d_tag = a(fc, bb, 0 + dyn);
                        } else {
                            d_tag = b(fc, bb, 0 + dyn);
                        }
                        if (d_tag == 1) {
                            if (is32) {
                                d_val = a(fc, bb, 8 + dyn);
                            } else {
                                d_val = b(fc, bb, 4 + dyn);
                            }
                            a = off_DT_STRTAB + d_val;
                            stringBuilder = new StringBuilder();
                            while (true) {
                                j = 1 + a;
                                d = d(fc, bb, a);
                                if (d == (short) 0) {
                                    break;
                                }
                                stringBuilder.append((char) d);
                                a = j;
                            }
                            needed[nr_DT_NEEDED] = stringBuilder.toString();
                            if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                nr_DT_NEEDED++;
                            } else {
                                throw new a("malformed DT_NEEDED section");
                            }
                        }
                        if (is32) {
                        }
                        dyn += is32 ? 8 : 16;
                    } while (d_tag != 0);
                    if (nr_DT_NEEDED == needed.length) {
                        return needed;
                    }
                    throw new a("malformed DT_NEEDED section");
                }
                throw new a("did not find file offset of DT_STRTAB table");
            }
            throw new a("Dynamic section string-table not found");
        }
        throw new a("ELF file does not contain dynamic linking information");
    }

    private static void a(FileChannel fc, ByteBuffer bb, int sz, long offset) throws IOException {
        bb.position(0);
        bb.limit(sz);
        while (bb.remaining() > 0) {
            int numBytesRead = fc.read(bb, offset);
            if (numBytesRead == -1) {
                break;
            }
            offset += (long) numBytesRead;
        }
        if (bb.remaining() > 0) {
            throw new a("ELF file truncated");
        }
        bb.position(0);
    }

    private static long a(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        a(fc, bb, 8, offset);
        return bb.getLong();
    }

    private static long b(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        a(fc, bb, 4, offset);
        return ((long) bb.getInt()) & 4294967295L;
    }

    private static int c(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        a(fc, bb, 2, offset);
        return bb.getShort() & 65535;
    }

    private static short d(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        a(fc, bb, 1, offset);
        return (short) (bb.get() & 255);
    }
}
