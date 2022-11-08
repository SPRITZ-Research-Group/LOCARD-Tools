package com.skype.slimcore.skylib;

public class InitializerConfiguration {
    private String a;
    private String b;
    private VideoHostInitializer c;
    private boolean d;
    private boolean e;

    public final String a() {
        return this.a;
    }

    public final void a(String version) {
        this.a = version;
    }

    public final String b() {
        return this.b;
    }

    public final void b(String dbPath) {
        this.b = dbPath;
    }

    public final VideoHostInitializer c() {
        return this.c;
    }

    public final void a(VideoHostInitializer videoHostInitializer) {
        this.c = videoHostInitializer;
    }

    public final boolean d() {
        return this.d;
    }

    public final void a(boolean saveLogs) {
        this.d = saveLogs;
    }

    public final boolean e() {
        return this.e;
    }

    public final void b(boolean encryptLogs) {
        this.e = encryptLogs;
    }
}
