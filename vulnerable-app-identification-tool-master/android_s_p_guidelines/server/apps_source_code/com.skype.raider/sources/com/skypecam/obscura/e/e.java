package com.skypecam.obscura.e;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class e {
    public final File a;
    public final int b;
    public final int c;
    private Future<String> d;
    private boolean e = true;

    public e(File file, int width, int height) {
        this.a = file;
        this.b = width;
        this.c = height;
    }

    public final String a() {
        Exception e;
        h a = g.a();
        String str = "CapturedRecordingData";
        StringBuilder stringBuilder = new StringBuilder("getThumbnail done:");
        boolean z = this.d != null && this.d.isDone();
        a.b(str, stringBuilder.append(z).toString());
        String result = "";
        if (this.d != null) {
            try {
                result = (String) this.d.get();
            } catch (InterruptedException e2) {
                e = e2;
                g.a().a("CapturedRecordingData", "getThumbnail", e);
                g.a().b("CapturedRecordingData", "getThumbnail returned " + result);
                return result;
            } catch (ExecutionException e3) {
                e = e3;
                g.a().a("CapturedRecordingData", "getThumbnail", e);
                g.a().b("CapturedRecordingData", "getThumbnail returned " + result);
                return result;
            }
        }
        g.a().b("CapturedRecordingData", "getThumbnail returned " + result);
        return result;
    }

    public final void a(File thumbnailFile, float videoThumbnailMaxDimension, int videoThumbnailCompressionRate) {
        g.a().b("CapturedRecordingData", "generateThumbnail");
        this.d = f.b().submit(new l(thumbnailFile.getPath(), videoThumbnailCompressionRate, videoThumbnailMaxDimension));
    }

    public final void a(int sampleCount) {
        if (sampleCount > 0) {
            try {
                new k(this.a).a();
                return;
            } catch (Throwable e) {
                g.a().a("CapturedRecordingData", "makeStreamable", e);
                return;
            }
        }
        this.e = false;
    }

    public final boolean b() {
        return this.e;
    }
}
