package com.skype.hockeyapp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public interface CrashDescriptor {
    VersionInfo a();

    void a(PrintWriter printWriter) throws IOException;

    File b();
}
