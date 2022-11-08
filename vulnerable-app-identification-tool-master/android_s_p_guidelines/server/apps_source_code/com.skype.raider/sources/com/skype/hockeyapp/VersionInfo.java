package com.skype.hockeyapp;

import net.hockeyapp.android.a;

public class VersionInfo {
    private String a;
    private String b;

    public VersionInfo() {
        this(a.b, a.a);
    }

    public VersionInfo(String versionName, String versionCode) {
        this.a = versionName;
        this.b = versionCode;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }
}
