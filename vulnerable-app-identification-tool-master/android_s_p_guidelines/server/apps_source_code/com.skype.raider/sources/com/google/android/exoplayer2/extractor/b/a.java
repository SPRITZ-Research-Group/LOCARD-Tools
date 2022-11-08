package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.m;
import java.io.EOFException;
import java.io.IOException;

final class a implements f {
    private final e a = new e();
    private final long b;
    private final long c;
    private final h d;
    private int e;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;

    private class a implements m {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        /* synthetic */ a(a x0, byte b) {
            this(x0);
        }

        public final boolean i_() {
            return true;
        }

        public final long a(long timeUs) {
            if (timeUs == 0) {
                return this.a.b;
            }
            return a.a(this.a, this.a.b, this.a.d.b(timeUs));
        }

        public final long b() {
            return this.a.d.a(this.a.f);
        }
    }

    public a(long startPosition, long endPosition, h streamReader, int firstPayloadPageSize, long firstPayloadPageGranulePosition) {
        boolean z = startPosition >= 0 && endPosition > startPosition;
        com.google.android.exoplayer2.d.a.a(z);
        this.d = streamReader;
        this.b = startPosition;
        this.c = endPosition;
        if (((long) firstPayloadPageSize) == endPosition - startPosition) {
            this.f = firstPayloadPageGranulePosition;
            this.e = 3;
            return;
        }
        this.e = 0;
    }

    public final long a(g input) throws IOException, InterruptedException {
        switch (this.e) {
            case 0:
                this.g = input.c();
                this.e = 1;
                long lastPageSearchPosition = this.c - 65307;
                if (lastPageSearchPosition > this.g) {
                    return lastPageSearchPosition;
                }
                break;
            case 1:
                break;
            case 2:
                long currentGranule;
                if (this.h == 0) {
                    currentGranule = 0;
                } else {
                    long position;
                    long j = this.h;
                    if (this.i == this.j) {
                        position = -(this.k + 2);
                    } else {
                        long c = input.c();
                        if (a(input, this.j)) {
                            this.a.a(input, false);
                            input.a();
                            long j2 = j - this.a.c;
                            int i = this.a.i + this.a.h;
                            if (j2 < 0 || j2 > 72000) {
                                if (j2 < 0) {
                                    this.j = c;
                                    this.l = this.a.c;
                                } else {
                                    this.i = input.c() + ((long) i);
                                    this.k = this.a.c;
                                    if ((this.j - this.i) + ((long) i) < 100000) {
                                        input.b(i);
                                        position = -(this.k + 2);
                                    }
                                }
                                if (this.j - this.i < 100000) {
                                    this.j = this.i;
                                    position = this.i;
                                } else {
                                    position = Math.min(Math.max((input.c() - ((long) ((j2 <= 0 ? 2 : 1) * i))) + (((this.j - this.i) * j2) / (this.l - this.k)), this.i), this.j - 1);
                                }
                            } else {
                                input.b(i);
                                position = -(this.a.c + 2);
                            }
                        } else if (this.i == c) {
                            throw new IOException("No ogg page can be found.");
                        } else {
                            position = this.i;
                        }
                    }
                    if (position >= 0) {
                        return position;
                    }
                    currentGranule = a(input, this.h, -(2 + position));
                }
                this.e = 3;
                return -(2 + currentGranule);
            case 3:
                return -1;
            default:
                throw new IllegalStateException();
        }
        if (a(input, this.c)) {
            this.a.a();
            while ((this.a.b & 4) != 4 && input.c() < this.c) {
                this.a.a(input, false);
                input.b(this.a.h + this.a.i);
            }
            this.f = this.a.c;
            this.e = 3;
            return this.g;
        }
        throw new EOFException();
    }

    public final long a_(long timeUs) {
        boolean z = this.e == 3 || this.e == 2;
        com.google.android.exoplayer2.d.a.a(z);
        this.h = timeUs == 0 ? 0 : this.d.b(timeUs);
        this.e = 2;
        this.i = this.b;
        this.j = this.c;
        this.k = 0;
        this.l = this.f;
        return this.h;
    }

    private boolean a(g input, long until) throws IOException, InterruptedException {
        until = Math.min(3 + until, this.c);
        byte[] buffer = new byte[2048];
        int peekLength = 2048;
        while (true) {
            if (input.c() + ((long) peekLength) > until) {
                peekLength = (int) (until - input.c());
                if (peekLength < 4) {
                    return false;
                }
            }
            input.b(buffer, 0, peekLength, false);
            int i = 0;
            while (i < peekLength - 3) {
                if (buffer[i] == (byte) 79 && buffer[i + 1] == (byte) 103 && buffer[i + 2] == (byte) 103 && buffer[i + 3] == (byte) 83) {
                    input.b(i);
                    return true;
                }
                i++;
            }
            input.b(peekLength - 3);
        }
    }

    private long a(g input, long targetGranule, long currentGranule) throws IOException, InterruptedException {
        this.a.a(input, false);
        while (this.a.c < targetGranule) {
            input.b(this.a.h + this.a.i);
            currentGranule = this.a.c;
            this.a.a(input, false);
        }
        input.a();
        return currentGranule;
    }

    public final /* synthetic */ m a() {
        return this.f != 0 ? new a() : null;
    }

    static /* synthetic */ long a(a x0, long x1, long x2) {
        long j = ((((x0.c - x0.b) * x2) / x0.f) - 30000) + x1;
        if (j < x0.b) {
            j = x0.b;
        }
        return j >= x0.c ? x0.c - 1 : j;
    }
}
