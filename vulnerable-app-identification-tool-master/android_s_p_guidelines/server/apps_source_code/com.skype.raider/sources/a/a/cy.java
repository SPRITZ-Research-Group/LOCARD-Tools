package a.a;

import com.adjust.sdk.Constants;
import com.appboy.f.c;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cy {
    private static final String a = c.a(cy.class);

    public static String a(String str, String str2) {
        String readLine;
        Throwable e;
        Throwable th;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"/system/bin/getprop", str});
            bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream(), Constants.ENCODING));
            try {
                readLine = bufferedReader2.readLine();
                try {
                    exec.destroy();
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e2) {
                        c.d(a, "Caught exception while trying to close system properties reader.", e2);
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        c.d(a, "Caught exception while trying to read Braze logger tag from system properties.", e2);
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e22) {
                                c.d(a, "Caught exception while trying to close system properties reader.", e22);
                            }
                        }
                        return readLine;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e222) {
                                c.d(a, "Caught exception while trying to close system properties reader.", e222);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                e222 = th3;
                readLine = str2;
            }
        } catch (Throwable th32) {
            bufferedReader2 = null;
            e222 = th32;
            readLine = str2;
            c.d(a, "Caught exception while trying to read Braze logger tag from system properties.", e222);
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return readLine;
        } catch (Throwable th4) {
            th32 = th4;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th32;
        }
        return readLine;
    }
}
