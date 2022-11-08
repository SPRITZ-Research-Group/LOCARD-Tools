package android.support.v4.media;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.util.SparseIntArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class AudioAttributesCompat {
    private static final SparseIntArray e;
    private static boolean f;
    private static final int[] g = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    int a = 0;
    int b = 0;
    int c = 0;
    Integer d;
    private a h;

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeContentType {
    }

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeUsage {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        sparseIntArray.put(5, 1);
        e.put(6, 2);
        e.put(7, 2);
        e.put(8, 1);
        e.put(9, 1);
        e.put(10, 1);
    }

    private AudioAttributesCompat() {
    }

    @Nullable
    private Object a() {
        if (this.h != null) {
            return this.h.a();
        }
        return null;
    }

    public final int hashCode() {
        if (VERSION.SDK_INT >= 21 && !f && this.h != null) {
            return this.h.a().hashCode();
        }
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.a), this.d});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (a() != null) {
            sb.append(" audioattributes=").append(a());
        } else {
            String str;
            if (this.d != null) {
                sb.append(" stream=").append(this.d);
                sb.append(" derived");
            }
            StringBuilder append = sb.append(" usage=");
            int i = this.a;
            switch (i) {
                case 0:
                    str = new String("USAGE_UNKNOWN");
                    break;
                case 1:
                    str = new String("USAGE_MEDIA");
                    break;
                case 2:
                    str = new String("USAGE_VOICE_COMMUNICATION");
                    break;
                case 3:
                    str = new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");
                    break;
                case 4:
                    str = new String("USAGE_ALARM");
                    break;
                case 5:
                    str = new String("USAGE_NOTIFICATION");
                    break;
                case 6:
                    str = new String("USAGE_NOTIFICATION_RINGTONE");
                    break;
                case 7:
                    str = new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");
                    break;
                case 8:
                    str = new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");
                    break;
                case 9:
                    str = new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");
                    break;
                case 10:
                    str = new String("USAGE_NOTIFICATION_EVENT");
                    break;
                case 11:
                    str = new String("USAGE_ASSISTANCE_ACCESSIBILITY");
                    break;
                case 12:
                    str = new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");
                    break;
                case 13:
                    str = new String("USAGE_ASSISTANCE_SONIFICATION");
                    break;
                case 14:
                    str = new String("USAGE_GAME");
                    break;
                case 16:
                    str = new String("USAGE_ASSISTANT");
                    break;
                default:
                    str = new String("unknown usage " + i);
                    break;
            }
            append.append(str).append(" content=").append(this.b).append(" flags=0x").append(Integer.toHexString(this.c).toUpperCase());
        }
        return sb.toString();
    }

    public final boolean equals(Object o) {
        int i = 3;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AudioAttributesCompat that = (AudioAttributesCompat) o;
        if (VERSION.SDK_INT >= 21 && !f && this.h != null) {
            return this.h.a().equals(that.a());
        }
        int i2;
        int i3 = this.b;
        if (VERSION.SDK_INT < 21 || f || that.h == null) {
            i2 = that.b;
        } else {
            i2 = that.h.a().getContentType();
        }
        if (i3 == i2) {
            i3 = this.c;
            if (VERSION.SDK_INT < 21 || f || that.h == null) {
                i2 = that.c;
                if (that.d != null) {
                    i = that.d.intValue();
                } else if (VERSION.SDK_INT < 21 || f) {
                    int i4 = that.c;
                    int i5 = that.a;
                    if ((i4 & 1) != 1) {
                        if ((i4 & 4) != 4) {
                            switch (i5) {
                                case 2:
                                    i = 0;
                                    break;
                                case 3:
                                    i = 8;
                                    break;
                                case 4:
                                    i = 4;
                                    break;
                                case 5:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                    i = 5;
                                    break;
                                case 6:
                                    i = 2;
                                    break;
                                case 11:
                                    i = 10;
                                    break;
                                case 13:
                                    i = 1;
                                    break;
                            }
                        }
                        i = 6;
                    } else {
                        i = 7;
                    }
                } else {
                    i = a.a(that.h);
                }
                if (i == 6) {
                    i2 |= 4;
                } else if (i == 7) {
                    i2 |= 1;
                }
                i2 &= 273;
            } else {
                i2 = that.h.a().getFlags();
            }
            if (i3 == i2) {
                i = this.a;
                if (VERSION.SDK_INT < 21 || f || that.h == null) {
                    i2 = that.a;
                } else {
                    i2 = that.h.a().getUsage();
                }
                if (i == i2 && (this.d == null ? that.d == null : this.d.equals(that.d))) {
                    return true;
                }
            }
        }
        return false;
    }
}
