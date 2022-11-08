package a.a;

import com.appboy.f.c;
import com.appboy.f.i;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public final class co {
    private static final String a = c.a(co.class);
    private static final TimeZone b = TimeZone.getTimeZone("UTC");
    private static final EnumSet<fq> c = EnumSet.of(fq.SHORT, fq.LONG, fq.ANDROID_LOGCAT);

    public static long a() {
        return System.currentTimeMillis() / 1000;
    }

    public static double b() {
        return ((double) System.currentTimeMillis()) / 1000.0d;
    }

    public static long c() {
        return System.currentTimeMillis();
    }

    public static Date a(String str, fq fqVar) {
        Date date = null;
        if (i.c(str)) {
            c.f(a, "Null or blank date string received: " + str);
            return date;
        } else if (c.contains(fqVar)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fqVar.a(), Locale.US);
            simpleDateFormat.setTimeZone(b);
            try {
                return simpleDateFormat.parse(str);
            } catch (Throwable e) {
                c.d(a, "Exception parsing date " + str + ". Returning null", e);
                return date;
            }
        } else {
            c.f(a, "Unsupported date format. Returning null. Got date format: " + fqVar);
            return date;
        }
    }

    public static String a(Date date, fq fqVar) {
        if (!c.contains(fqVar)) {
            c.f(a, "Unsupported date format: " + fqVar + ". Defaulting to " + fq.LONG);
            fqVar = fq.LONG;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fqVar.a(), Locale.US);
        simpleDateFormat.setTimeZone(b);
        return simpleDateFormat.format(date);
    }

    public static Date a(int i, int i2, int i3) {
        Calendar gregorianCalendar = new GregorianCalendar(i, i2, i3, 0, 0, 0);
        gregorianCalendar.setTimeZone(b);
        return gregorianCalendar.getTime();
    }

    public static long a(Date date) {
        return date.getTime() / 1000;
    }
}
