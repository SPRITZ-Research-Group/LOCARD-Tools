package a.a;

public enum fr {
    ANDROID_VERSION("os_version"),
    CARRIER("carrier"),
    MODEL("model"),
    RESOLUTION("resolution"),
    LOCALE("locale"),
    TIMEZONE("time_zone"),
    NOTIFICATIONS_ENABLED("remote_notification_enabled");
    
    private String h;

    private fr(String str) {
        this.h = str;
    }

    public final String a() {
        return this.h;
    }
}
