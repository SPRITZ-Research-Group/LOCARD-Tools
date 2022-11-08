package com.google.android.exoplayer2;

import com.google.android.exoplayer2.d.b;
import com.google.android.exoplayer2.source.g;
import java.io.IOException;

public interface m extends b {
    int a();

    void a(int i);

    void a(long j) throws ExoPlaybackException;

    void a(long j, long j2) throws ExoPlaybackException;

    void a(o oVar, Format[] formatArr, g gVar, long j, boolean z, long j2) throws ExoPlaybackException;

    void a(Format[] formatArr, g gVar, long j) throws ExoPlaybackException;

    n b();

    com.google.android.exoplayer2.d.g c();

    int d();

    void e() throws ExoPlaybackException;

    g f();

    boolean g();

    void h();

    boolean i();

    void j() throws IOException;

    void k() throws ExoPlaybackException;

    void l();

    boolean t();

    boolean u();
}
