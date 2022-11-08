package com.skypecam.obscura.b;

import android.media.MediaFormat;
import com.adjust.sdk.Constants;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skypecam.obscura.a.a.a;
import com.skypecam.obscura.a.f;
import com.skypecam.obscura.b.a.b;
import com.skypecam.obscura.c.h;
import com.skypecam.obscura.c.i;
import com.skypecam.obscura.e.d;
import com.skypecam.obscura.e.e;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.e.j;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

public final class z extends a<x, y> implements a, aa, l.a {
    private static final b l = new b() {
        public final boolean a(Object candidate) {
            return candidate instanceof j;
        }
    };
    private static final b m = new b() {
        public final boolean a(Object candidate) {
            return candidate instanceof File;
        }
    };
    private int A = 640;
    private int B = 360;
    private float C = 1300.0f;
    private int D = 2;
    private boolean E = false;
    private final h F;
    private f G;
    private i n;
    private int o;
    private int p;
    private final a.a<File> q = new a.a(this, x.FILE, m);
    private final a.a<j> r = new a.a(this, x.FORMAT, l);
    private l s;
    private File t;
    private Runnable u;
    private e v;
    private s<d> w;
    private int x = 70;
    private float y = 640.0f;
    private int z = 20000;

    public z(h codecWrapper, f audioWrapper) throws b {
        super("RecordingStateMachine", y.class, x.class, new x[0]);
        this.F = codecWrapper;
        this.G = audioWrapper;
    }

    public final void e() {
        b(x.FINISH, true);
    }

    protected final void b() {
        a(EnumSet.of(x.FRIGHT), y.IDLE);
        a(EnumSet.of(x.FILE, x.FORMAT, x.RECORD, x.FINISH, x.MIC), y.COMPLETED);
        a(EnumSet.of(x.FILE, x.FORMAT, x.LIGHTS, x.RECORD, x.MIC), y.RUNNING);
        a(EnumSet.of(x.FILE, x.FORMAT, x.MIC), y.CONFIGURED);
        a(EnumSet.noneOf(x.class), y.IDLE);
        a(y.class, new y[0]);
    }

    protected final void c() {
        a(y.IDLE, y.RUNNING, y.CONFIGURED);
        a(EnumSet.of(y.IDLE, y.CONFIGURED), y.COMPLETED, new Runnable(this) {
            final /* synthetic */ z a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b(x.RECORD, false);
            }
        });
        a(y.RUNNING, y.IDLE, y.CONFIGURED);
        a(y.IDLE, y.CONFIGURED, new Runnable(this) {
            final /* synthetic */ z a;

            {
                this.a = this$0;
            }

            public final void run() {
                g.a().b("RecordingStateMachine", "Configuring");
                try {
                    this.a.t = new File((File) this.a.q.a(), "scv_" + System.currentTimeMillis() + ".mp4");
                    z.b(this.a);
                    this.a.s = new l(this.a.o, this.a.p, ((int) this.a.C) * Constants.ONE_SECOND, this.a.D, this.a.E, this.a, this.a.F);
                    this.a.n = new i(this.a.t.getPath());
                    this.a.s.a();
                    this.a.G.a(this.a);
                } catch (IOException e) {
                    g.a().d("RecordingStateMachine", "codec configuration exception: " + e.getLocalizedMessage());
                    this.a.a(x.FRIGHT, true);
                }
            }
        });
        a(y.CONFIGURED, y.RUNNING, new Runnable(this) {
            final /* synthetic */ z a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.u = new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        g.a().b("RecordingStateMachine", "recording timeout");
                        this.a.a.e();
                    }
                };
                this.a.postDelayed(this.a.u, (long) this.a.z);
                this.a.v = new e(this.a.t, this.a.h(), this.a.i());
                this.a.w = new s<d>(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = this$1;
                    }

                    public final /* synthetic */ void a(Object obj) {
                        d dVar = (d) obj;
                        g.a().b("RecordingStateMachine", "thumbnailReporter report:" + dVar.a + " capturedData:" + (this.a.a.v != null));
                        if (this.a.a.v != null) {
                            this.a.a.v.a(dVar.a, this.a.a.y, this.a.a.x);
                        }
                    }

                    public final boolean a() {
                        return true;
                    }
                };
                this.a.G.a();
            }
        });
        a(y.RUNNING, y.COMPLETED, new Runnable(this) {
            final /* synthetic */ z a;

            {
                this.a = this$0;
            }

            public final void run() {
                z.q(this.a);
                this.a.G.b();
                this.a.s.c();
                this.a.s.a(this.a.t);
                final int samples = this.a.n.b();
                final e captured = this.a.v;
                this.a.v = null;
                com.skypecam.obscura.e.f.a().execute(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 c;

                    public final void run() {
                        this.c.a.b(x.RECORD, false);
                        g.a().b("RecordingStateMachine", "Saving file that had " + samples + " samples");
                        captured.a(samples);
                        this.c.a.a((Object) captured);
                    }
                });
            }
        });
        a(y.RUNNING, y.CONFIGURED, new Runnable(this) {
            final /* synthetic */ z a;

            {
                this.a = this$0;
            }

            public final void run() {
                z.q(this.a);
                g.a().b("RecordingStateMachine", "Canceled");
            }
        });
        a(y.COMPLETED, y.CONFIGURED, y.IDLE);
        a(y.COMPLETED, y.RUNNING, y.CONFIGURED);
        a(EnumSet.of(y.CONFIGURED, y.COMPLETED), y.IDLE, new Runnable(this) {
            final /* synthetic */ z a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.s.d();
                this.a.s = null;
                this.a.w = null;
                g.a().b("RecordingStateMachine", "Idling");
            }
        });
        a(y.class, new y[0]);
    }

    protected final void a(EnumSet flags) {
        if (!flags.contains(x.MIC)) {
            flags.remove(x.RECORD);
        }
        if (!flags.contains(x.RECORD) || !flags.contains(x.FORMAT) || !flags.contains(x.FILE)) {
            flags.remove(x.FINISH);
        }
    }

    public final void b(MediaFormat videoFormat) {
        this.n.a(videoFormat);
    }

    public final void a(com.skypecam.obscura.c.g sample) {
        if (sample.a.equals(CodecUtils.MEDIA_TYPE)) {
            this.n.a(sample.b(), sample.a(), sample.c());
        } else {
            this.n.b(sample.b(), sample.a(), sample.c());
        }
    }

    public final void d() {
        g.a().b("RecordingStateMachine", "endStream");
    }

    public final void a(j videoFormat) {
        if (videoFormat.a() == 0 || videoFormat.b() == 0) {
            g.a().c("RecordingStateMachine", "setVideoFormat " + videoFormat.a() + "x" + videoFormat.b());
        } else if (!videoFormat.equals(this.r.a())) {
            g.a().b("RecordingStateMachine", "setVideoFormat " + videoFormat.a() + "x" + videoFormat.b());
            a((Object) videoFormat, true);
        }
    }

    public final void a(boolean on) {
        b(x.LIGHTS, on);
    }

    public final boolean f() {
        return false;
    }

    public final void g() {
        this.s.c();
    }

    public final int h() {
        return this.o;
    }

    public final int i() {
        return this.p;
    }

    public final void a(int width, int height) {
        g.a().b("RecordingStateMachine", "updateDimensions not allowed, finishing recording");
        e();
    }

    public final void a() {
        this.n.a();
    }

    public final void a(MediaFormat format) {
        this.n.b(format);
    }

    public final void a(int videoThumbnailCompressionRate) {
        this.x = videoThumbnailCompressionRate;
    }

    public final void a(float videoThumbnailMaxDimension) {
        this.y = videoThumbnailMaxDimension;
    }

    public final void b(int maxRecordingMs) {
        this.z = maxRecordingMs;
    }

    public final void c(int videoDimensionShort) {
        this.B = videoDimensionShort;
        g.a().b("RecordingStateMachine", "setVideoDimensionShort " + videoDimensionShort);
    }

    public final void d(int videoDimensionLong) {
        this.A = videoDimensionLong;
        g.a().b("RecordingStateMachine", "setVideoDimensionLong " + videoDimensionLong);
    }

    public final void b(float bitrate) {
        this.C = bitrate;
    }

    public final void e(int profile) {
        if (profile > 0 && profile <= 64) {
            this.D = profile;
        }
    }

    public final void b(boolean enable) {
        this.E = enable;
    }

    public final s<d> j() {
        if (this.w == null) {
            g.a().c("RecordingStateMachine", "getThumbnailReporter: null");
        }
        return this.w;
    }

    public final /* synthetic */ Object k() {
        return this.s.b();
    }

    static /* synthetic */ void b(z x0) {
        if (((j) x0.r.a()).a() > ((j) x0.r.a()).b()) {
            x0.o = Math.max(x0.A, x0.B);
            x0.p = Math.min(x0.A, x0.B);
        } else {
            x0.o = Math.min(x0.A, x0.B);
            x0.p = Math.max(x0.A, x0.B);
        }
        g.a().b("RecordingStateMachine", "setEncodedFrameSize " + x0.o + " x " + x0.p);
    }

    static /* synthetic */ void q(z x0) {
        g.a().b("RecordingStateMachine", "clearRecordingTimeout");
        x0.removeCallbacks(x0.u);
        x0.u = null;
    }
}
