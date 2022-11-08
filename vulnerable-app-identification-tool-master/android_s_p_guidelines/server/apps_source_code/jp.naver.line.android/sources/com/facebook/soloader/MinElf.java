package com.facebook.soloader;

import com.google.android.gms.common.api.Api.BaseClientBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;

    class ElfError extends RuntimeException {
        ElfError(String str) {
            super(str);
        }
    }

    public static String[] extract_DT_NEEDED(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            String[] extract_DT_NEEDED = extract_DT_NEEDED(fileInputStream.getChannel());
            return extract_DT_NEEDED;
        } finally {
            fileInputStream.close();
        }
    }

    public static String[] extract_DT_NEEDED(FileChannel fileChannel) throws IOException {
        FileChannel fileChannel2 = fileChannel;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fileChannel2, allocate, 0) == 1179403647) {
            long j;
            long j2;
            int i;
            long j3;
            long j4;
            long j5;
            int i2;
            Object obj;
            long j6;
            long j7;
            long j8;
            int i3;
            long j9;
            long j10;
            String[] strArr;
            Object obj2 = 1;
            if (getu8(fileChannel2, allocate, 4) != (short) 1) {
                obj2 = null;
            }
            if (getu8(fileChannel2, allocate, 5) == (short) 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            if (obj2 != null) {
                j = getu32(fileChannel2, allocate, 28);
            } else {
                j = get64(fileChannel2, allocate, 32);
            }
            if (obj2 != null) {
                j2 = (long) getu16(fileChannel2, allocate, 44);
            } else {
                j2 = (long) getu16(fileChannel2, allocate, 56);
            }
            if (obj2 != null) {
                i = getu16(fileChannel2, allocate, 42);
            } else {
                i = getu16(fileChannel2, allocate, 54);
            }
            if (j2 == 65535) {
                long j11;
                if (obj2 != null) {
                    j11 = getu32(fileChannel2, allocate, 32);
                } else {
                    j11 = get64(fileChannel2, allocate, 40);
                }
                if (obj2 != null) {
                    j11 = getu32(fileChannel2, allocate, j11 + 28);
                } else {
                    j11 = getu32(fileChannel2, allocate, j11 + 44);
                }
                j2 = j11;
            }
            long j12 = j;
            for (j3 = 0; j3 < j2; j3++) {
                if (getu32(fileChannel2, allocate, j12 + 0) == 2) {
                    if (obj2 != null) {
                        j4 = getu32(fileChannel2, allocate, j12 + 4);
                    } else {
                        j4 = get64(fileChannel2, allocate, j12 + 8);
                    }
                    j5 = 0;
                    if (j4 == 0) {
                        j3 = j4;
                        j12 = 0;
                        i2 = 0;
                        while (true) {
                            if (obj2 == null) {
                                obj = obj2;
                                j6 = getu32(fileChannel2, allocate, j3 + j5);
                            } else {
                                obj = obj2;
                                j6 = get64(fileChannel2, allocate, j3 + j5);
                            }
                            if (j6 != 1) {
                                if (i2 == BaseClientBuilder.API_PRIORITY_OTHER) {
                                    i2++;
                                    j7 = j4;
                                } else {
                                    throw new ElfError("malformed DT_NEEDED section");
                                }
                            } else if (j6 != 5) {
                                if (obj == null) {
                                    j7 = j4;
                                    j8 = getu32(fileChannel2, allocate, j3 + 4);
                                } else {
                                    j7 = j4;
                                    j8 = get64(fileChannel2, allocate, j3 + 8);
                                }
                                j12 = j8;
                            } else {
                                j7 = j4;
                            }
                            j8 = 16;
                            j3 += obj == null ? 8 : 16;
                            j5 = 0;
                            if (j6 == 0) {
                                obj2 = obj;
                                j4 = j7;
                            } else if (j12 == 0) {
                                i3 = 0;
                                while (((long) i3) < j2) {
                                    if (getu32(fileChannel2, allocate, j + j5) != 1) {
                                        if (obj == null) {
                                            j9 = getu32(fileChannel2, allocate, j + 8);
                                        } else {
                                            j9 = get64(fileChannel2, allocate, j + j8);
                                        }
                                        if (obj == null) {
                                            j10 = j2;
                                            j2 = getu32(fileChannel2, allocate, j + 20);
                                        } else {
                                            j10 = j2;
                                            j2 = get64(fileChannel2, allocate, j + 40);
                                        }
                                        if (j9 <= j12 && j12 < r4 + j9) {
                                            if (obj == null) {
                                                j2 = getu32(fileChannel2, allocate, j + 4);
                                            } else {
                                                j2 = get64(fileChannel2, allocate, j + 8);
                                            }
                                            j2 += j12 - j9;
                                            j6 = 0;
                                            if (j2 == 0) {
                                                strArr = new String[i2];
                                                i2 = 0;
                                                while (true) {
                                                    if (obj == null) {
                                                        j8 = getu32(fileChannel2, allocate, j7 + j6);
                                                    } else {
                                                        j8 = get64(fileChannel2, allocate, j7 + j6);
                                                    }
                                                    if (j8 != 1) {
                                                        if (obj == null) {
                                                            j6 = getu32(fileChannel2, allocate, j7 + 4);
                                                        } else {
                                                            j6 = get64(fileChannel2, allocate, j7 + 8);
                                                        }
                                                        strArr[i2] = getSz(fileChannel2, allocate, j6 + j2);
                                                        if (i2 == BaseClientBuilder.API_PRIORITY_OTHER) {
                                                            i2++;
                                                        } else {
                                                            throw new ElfError("malformed DT_NEEDED section");
                                                        }
                                                    }
                                                    j7 += obj == null ? 8 : 16;
                                                    if (j8 != 0) {
                                                        j6 = 0;
                                                    } else if (i2 != strArr.length) {
                                                        return strArr;
                                                    } else {
                                                        throw new ElfError("malformed DT_NEEDED section");
                                                    }
                                                }
                                            }
                                            throw new ElfError("did not find file offset of DT_STRTAB table");
                                        }
                                    }
                                    j10 = j2;
                                    j += (long) i;
                                    i3++;
                                    j2 = j10;
                                    j8 = 16;
                                    j5 = 0;
                                }
                                j2 = 0;
                                j6 = 0;
                                if (j2 == 0) {
                                    throw new ElfError("did not find file offset of DT_STRTAB table");
                                }
                                strArr = new String[i2];
                                i2 = 0;
                                while (true) {
                                    if (obj == null) {
                                        j8 = get64(fileChannel2, allocate, j7 + j6);
                                    } else {
                                        j8 = getu32(fileChannel2, allocate, j7 + j6);
                                    }
                                    if (j8 != 1) {
                                        if (obj == null) {
                                            j6 = get64(fileChannel2, allocate, j7 + 8);
                                        } else {
                                            j6 = getu32(fileChannel2, allocate, j7 + 4);
                                        }
                                        strArr[i2] = getSz(fileChannel2, allocate, j6 + j2);
                                        if (i2 == BaseClientBuilder.API_PRIORITY_OTHER) {
                                            throw new ElfError("malformed DT_NEEDED section");
                                        }
                                        i2++;
                                    }
                                    if (obj == null) {
                                    }
                                    j7 += obj == null ? 8 : 16;
                                    if (j8 != 0) {
                                        j6 = 0;
                                    } else if (i2 != strArr.length) {
                                        return strArr;
                                    } else {
                                        throw new ElfError("malformed DT_NEEDED section");
                                    }
                                }
                            } else {
                                throw new ElfError("Dynamic section string-table not found");
                            }
                        }
                    }
                    throw new ElfError("ELF file does not contain dynamic linking information");
                }
                j12 += (long) i;
            }
            j4 = 0;
            j5 = 0;
            if (j4 == 0) {
                throw new ElfError("ELF file does not contain dynamic linking information");
            }
            j3 = j4;
            j12 = 0;
            i2 = 0;
            while (true) {
                if (obj2 == null) {
                    obj = obj2;
                    j6 = get64(fileChannel2, allocate, j3 + j5);
                } else {
                    obj = obj2;
                    j6 = getu32(fileChannel2, allocate, j3 + j5);
                }
                if (j6 != 1) {
                    if (j6 != 5) {
                        j7 = j4;
                    } else {
                        if (obj == null) {
                            j7 = j4;
                            j8 = get64(fileChannel2, allocate, j3 + 8);
                        } else {
                            j7 = j4;
                            j8 = getu32(fileChannel2, allocate, j3 + 4);
                        }
                        j12 = j8;
                    }
                } else if (i2 == BaseClientBuilder.API_PRIORITY_OTHER) {
                    throw new ElfError("malformed DT_NEEDED section");
                } else {
                    i2++;
                    j7 = j4;
                }
                j8 = 16;
                if (obj == null) {
                }
                j3 += obj == null ? 8 : 16;
                j5 = 0;
                if (j6 == 0) {
                    obj2 = obj;
                    j4 = j7;
                } else if (j12 == 0) {
                    throw new ElfError("Dynamic section string-table not found");
                } else {
                    i3 = 0;
                    while (((long) i3) < j2) {
                        if (getu32(fileChannel2, allocate, j + j5) != 1) {
                            j10 = j2;
                        } else {
                            if (obj == null) {
                                j9 = get64(fileChannel2, allocate, j + j8);
                            } else {
                                j9 = getu32(fileChannel2, allocate, j + 8);
                            }
                            if (obj == null) {
                                j10 = j2;
                                j2 = get64(fileChannel2, allocate, j + 40);
                            } else {
                                j10 = j2;
                                j2 = getu32(fileChannel2, allocate, j + 20);
                            }
                            if (obj == null) {
                                j2 = get64(fileChannel2, allocate, j + 8);
                            } else {
                                j2 = getu32(fileChannel2, allocate, j + 4);
                            }
                            j2 += j12 - j9;
                            j6 = 0;
                            if (j2 == 0) {
                                strArr = new String[i2];
                                i2 = 0;
                                while (true) {
                                    if (obj == null) {
                                        j8 = getu32(fileChannel2, allocate, j7 + j6);
                                    } else {
                                        j8 = get64(fileChannel2, allocate, j7 + j6);
                                    }
                                    if (j8 != 1) {
                                        if (obj == null) {
                                            j6 = getu32(fileChannel2, allocate, j7 + 4);
                                        } else {
                                            j6 = get64(fileChannel2, allocate, j7 + 8);
                                        }
                                        strArr[i2] = getSz(fileChannel2, allocate, j6 + j2);
                                        if (i2 == BaseClientBuilder.API_PRIORITY_OTHER) {
                                            i2++;
                                        } else {
                                            throw new ElfError("malformed DT_NEEDED section");
                                        }
                                    }
                                    if (obj == null) {
                                    }
                                    j7 += obj == null ? 8 : 16;
                                    if (j8 != 0) {
                                        j6 = 0;
                                    } else if (i2 != strArr.length) {
                                        return strArr;
                                    } else {
                                        throw new ElfError("malformed DT_NEEDED section");
                                    }
                                }
                            }
                            throw new ElfError("did not find file offset of DT_STRTAB table");
                        }
                        j += (long) i;
                        i3++;
                        j2 = j10;
                        j8 = 16;
                        j5 = 0;
                    }
                    j2 = 0;
                    j6 = 0;
                    if (j2 == 0) {
                        throw new ElfError("did not find file offset of DT_STRTAB table");
                    }
                    strArr = new String[i2];
                    i2 = 0;
                    while (true) {
                        if (obj == null) {
                            j8 = get64(fileChannel2, allocate, j7 + j6);
                        } else {
                            j8 = getu32(fileChannel2, allocate, j7 + j6);
                        }
                        if (j8 != 1) {
                            if (obj == null) {
                                j6 = get64(fileChannel2, allocate, j7 + 8);
                            } else {
                                j6 = getu32(fileChannel2, allocate, j7 + 4);
                            }
                            strArr[i2] = getSz(fileChannel2, allocate, j6 + j2);
                            if (i2 == BaseClientBuilder.API_PRIORITY_OTHER) {
                                throw new ElfError("malformed DT_NEEDED section");
                            }
                            i2++;
                        }
                        if (obj == null) {
                        }
                        j7 += obj == null ? 8 : 16;
                        if (j8 != 0) {
                            j6 = 0;
                        } else if (i2 != strArr.length) {
                            return strArr;
                        } else {
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                    }
                }
            }
        }
        throw new ElfError("file is not ELF");
    }

    private static String getSz(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(fileChannel, byteBuffer, j);
            if (u8Var == (short) 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) u8Var);
            j = j2;
        }
    }

    private static void read(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0) {
            i = fileChannel.read(byteBuffer, j);
            if (i == -1) {
                break;
            }
            j += (long) i;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    private static long get64(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long getu32(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int getu16(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & PN_XNUM;
    }

    private static short getu8(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }
}
