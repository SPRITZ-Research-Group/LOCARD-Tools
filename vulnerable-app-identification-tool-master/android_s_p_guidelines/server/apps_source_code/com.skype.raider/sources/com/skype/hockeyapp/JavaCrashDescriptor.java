package com.skype.hockeyapp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaCrashDescriptor implements CrashDescriptor {
    private File a;
    private Throwable b;

    public JavaCrashDescriptor(File crashDirectory, Throwable exception) {
        this.a = crashDirectory;
        this.b = exception;
    }

    public final VersionInfo a() {
        return new VersionInfo();
    }

    public final void a(PrintWriter writer) throws IOException {
        this.b.printStackTrace(writer);
    }

    public final File b() {
        return this.a;
    }
}
