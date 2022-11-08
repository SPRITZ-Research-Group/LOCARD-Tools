package jp.naver.line.android.common.util.io;

import java.io.IOException;

public class c extends IOException {
    private static final long serialVersionUID = 5258078851109704333L;

    public c(String str, Throwable th) {
        super(str, th);
    }

    public c(String str) {
        super(str);
    }

    public c(Throwable th) {
        super(th);
    }
}
