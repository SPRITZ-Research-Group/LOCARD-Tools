package com.facebook.imagepipeline.f;

import com.facebook.common.e.a;
import com.facebook.common.e.g;
import com.facebook.common.i.d;
import com.facebook.common.internal.b;
import com.facebook.common.internal.h;
import com.facebook.common.internal.l;
import com.facebook.imagepipeline.image.e;
import java.io.IOException;
import java.io.InputStream;

public final class f {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private boolean g;
    private final a h;

    public f(a byteArrayPool) {
        this.h = (a) h.a((Object) byteArrayPool);
    }

    public final boolean a(e encodedImage) {
        if (this.a == 6) {
            return false;
        }
        if (encodedImage.l() <= this.c) {
            return false;
        }
        InputStream bufferedDataStream = new g(encodedImage.c(), (byte[]) this.h.a(16384), this.h);
        boolean a;
        try {
            d.a(bufferedDataStream, (long) this.c);
            a = a(bufferedDataStream);
            return a;
        } catch (IOException e) {
            a = e;
            l.b(a);
            return false;
        } finally {
            b.a(bufferedDataStream);
        }
    }

    private boolean a(InputStream inputStream) {
        int oldBestScanNumber = this.e;
        while (this.a != 6) {
            int nextByte = inputStream.read();
            if (nextByte != -1) {
                this.c++;
                if (this.g) {
                    this.a = 6;
                    this.g = false;
                    return false;
                }
                switch (this.a) {
                    case 0:
                        if (nextByte != 255) {
                            this.a = 6;
                            break;
                        }
                        try {
                            this.a = 1;
                            break;
                        } catch (Throwable e) {
                            l.b(e);
                            break;
                        }
                    case 1:
                        if (nextByte != 216) {
                            this.a = 6;
                            break;
                        }
                        this.a = 2;
                        break;
                    case 2:
                        if (nextByte == 255) {
                            this.a = 3;
                            break;
                        }
                        break;
                    case 3:
                        if (nextByte != 255) {
                            if (nextByte != 0) {
                                if (nextByte != 217) {
                                    boolean z;
                                    if (nextByte == 218) {
                                        a(this.c - 2);
                                    }
                                    if (nextByte == 1 || ((nextByte >= 208 && nextByte <= 215) || nextByte == 217 || nextByte == 216)) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    if (!z) {
                                        this.a = 2;
                                        break;
                                    }
                                    this.a = 4;
                                    break;
                                }
                                this.g = true;
                                a(this.c - 2);
                                this.a = 2;
                                break;
                            }
                            this.a = 2;
                            break;
                        }
                        this.a = 3;
                        break;
                        break;
                    case 4:
                        this.a = 5;
                        break;
                    case 5:
                        int bytesToSkip = ((this.b << 8) + nextByte) - 2;
                        d.a(inputStream, (long) bytesToSkip);
                        this.c += bytesToSkip;
                        this.a = 2;
                        break;
                    default:
                        h.b(false);
                        break;
                }
                this.b = nextByte;
            } else if (this.a == 6 && this.e != oldBestScanNumber) {
                return true;
            }
        }
        return this.a == 6 ? false : false;
    }

    private void a(int offset) {
        if (this.d > 0) {
            this.f = offset;
        }
        int i = this.d;
        this.d = i + 1;
        this.e = i;
    }

    public final int a() {
        return this.f;
    }

    public final int b() {
        return this.e;
    }

    public final boolean c() {
        return this.g;
    }
}
