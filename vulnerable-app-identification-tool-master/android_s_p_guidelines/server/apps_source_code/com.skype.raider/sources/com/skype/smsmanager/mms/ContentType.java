package com.skype.smsmanager.mms;

import java.util.ArrayList;

public class ContentType {
    private static final ArrayList<String> a = new ArrayList();
    private static final ArrayList<String> b = new ArrayList();
    private static final ArrayList<String> c = new ArrayList();
    private static final ArrayList<String> d = new ArrayList();

    static {
        a.add("text/plain");
        a.add("text/html");
        a.add("text/x-vCalendar");
        a.add("text/x-vCard");
        a.add("image/jpeg");
        a.add("image/gif");
        a.add("image/vnd.wap.wbmp");
        a.add("image/png");
        a.add("image/jpg");
        a.add("image/x-ms-bmp");
        a.add("audio/aac");
        a.add("audio/amr");
        a.add("audio/imelody");
        a.add("audio/mid");
        a.add("audio/midi");
        a.add("audio/mp3");
        a.add("audio/mp4");
        a.add("audio/mpeg3");
        a.add("audio/mpeg");
        a.add("audio/mpg");
        a.add("audio/x-mid");
        a.add("audio/x-midi");
        a.add("audio/x-mp3");
        a.add("audio/x-mpeg3");
        a.add("audio/x-mpeg");
        a.add("audio/x-mpg");
        a.add("audio/x-wav");
        a.add("audio/3gpp");
        a.add("application/ogg");
        a.add("video/3gpp");
        a.add("video/3gpp2");
        a.add("video/h263");
        a.add("video/mp4");
        a.add("application/smil");
        a.add("application/vnd.wap.xhtml+xml");
        a.add("application/xhtml+xml");
        a.add("application/vnd.oma.drm.content");
        a.add("application/vnd.oma.drm.message");
        b.add("image/jpeg");
        b.add("image/gif");
        b.add("image/vnd.wap.wbmp");
        b.add("image/png");
        b.add("image/jpg");
        b.add("image/x-ms-bmp");
        c.add("audio/aac");
        c.add("audio/amr");
        c.add("audio/imelody");
        c.add("audio/mid");
        c.add("audio/midi");
        c.add("audio/mp3");
        c.add("audio/mpeg3");
        c.add("audio/mpeg");
        c.add("audio/mpg");
        c.add("audio/mp4");
        c.add("audio/x-mid");
        c.add("audio/x-midi");
        c.add("audio/x-mp3");
        c.add("audio/x-mpeg3");
        c.add("audio/x-mpeg");
        c.add("audio/x-mpg");
        c.add("audio/x-wav");
        c.add("audio/3gpp");
        c.add("application/ogg");
        d.add("video/3gpp");
        d.add("video/3gpp2");
        d.add("video/h263");
        d.add("video/mp4");
    }

    private ContentType() {
    }

    public static boolean a(String contentType) {
        return contentType != null && contentType.startsWith("image/");
    }

    public static boolean b(String contentType) {
        return contentType != null && contentType.startsWith("audio/");
    }

    public static boolean c(String contentType) {
        return contentType != null && contentType.startsWith("video/");
    }
}
