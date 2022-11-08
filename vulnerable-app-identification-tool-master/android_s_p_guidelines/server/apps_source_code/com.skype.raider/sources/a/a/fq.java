package a.a;

public enum fq {
    SHORT("yyyy-MM-dd"),
    LONG("yyyy-MM-dd kk:mm:ss"),
    ANDROID_LOGCAT("MM-dd kk:mm:ss.SSS");
    
    private final String d;

    private fq(String str) {
        this.d = str;
    }

    public final String a() {
        return this.d;
    }
}
