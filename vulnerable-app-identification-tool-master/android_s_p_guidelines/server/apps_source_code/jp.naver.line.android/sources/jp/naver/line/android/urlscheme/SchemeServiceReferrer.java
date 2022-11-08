package jp.naver.line.android.urlscheme;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import defpackage.acom;
import defpackage.acry;
import java.util.Map;
import jp.naver.line.android.model.h;
import kotlin.Metadata;
import kotlin.u;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\n\u0006\u0007\b\t\n\u000b\f\r\u000e\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004\u0001\t\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018¨\u0006\u0019"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "Landroid/os/Parcelable;", "()V", "isPushNotification", "", "shouldSendMessageDirectly", "AppShortcut", "Chat", "Companion", "InAppBrowser", "Liff", "PushNotification", "Square", "ThingsDevice", "Unknown", "WalletTab", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Chat;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Square;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$ThingsDevice;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$InAppBrowser;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Liff;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$PushNotification;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$AppShortcut;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$WalletTab;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Unknown;", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public abstract class SchemeServiceReferrer implements Parcelable {
    public static final s a = new s();
    private static final Map<String, SchemeServiceReferrer> b = acom.a(u.a("PUSH_NOTIFICATION", PushNotification.b), u.a("APP_SHORTCUT", AppShortcut.b));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Unknown;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class Unknown extends SchemeServiceReferrer {
        public static final Creator CREATOR = new y();
        public static final Unknown b = new Unknown();

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(1);
        }

        private Unknown() {
            super();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$AppShortcut;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class AppShortcut extends SchemeServiceReferrer {
        public static final Creator CREATOR = new q();
        public static final AppShortcut b = new AppShortcut();

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(1);
        }

        private AppShortcut() {
            super();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001e"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Chat;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "chatId", "", "squareId", "chatType", "Ljp/naver/line/android/model/ChatData$ChatType;", "(Ljava/lang/String;Ljava/lang/String;Ljp/naver/line/android/model/ChatData$ChatType;)V", "getChatId", "()Ljava/lang/String;", "getChatType", "()Ljp/naver/line/android/model/ChatData$ChatType;", "getSquareId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class Chat extends SchemeServiceReferrer {
        public static final Creator CREATOR = new r();
        private final String b;
        private final String c;
        private final h d;

        public final int describeContents() {
            return 0;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Chat) {
                    Chat chat = (Chat) obj;
                    if (acry.a(this.b, chat.b)) {
                        if (acry.a(this.c, chat.c)) {
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            String str = this.b;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.c;
            hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            h hVar = this.d;
            if (hVar != null) {
                i = hVar.hashCode();
            }
            return hashCode + i;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("Chat(chatId=");
            stringBuilder.append(this.b);
            stringBuilder.append(", squareId=");
            stringBuilder.append(this.c);
            stringBuilder.append(", chatType=");
            stringBuilder.append(this.d);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d.name());
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }

        public final h d() {
            return this.d;
        }

        public Chat(String str, String str2, h hVar) {
            super();
            this.b = str;
            this.c = str2;
            this.d = hVar;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$InAppBrowser;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class InAppBrowser extends SchemeServiceReferrer {
        public static final Creator CREATOR = new t();
        public static final InAppBrowser b = new InAppBrowser();

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(1);
        }

        private InAppBrowser() {
            super();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Liff;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class Liff extends SchemeServiceReferrer {
        public static final Creator CREATOR = new u();
        public static final Liff b = new Liff();

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(1);
        }

        private Liff() {
            super();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$PushNotification;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class PushNotification extends SchemeServiceReferrer {
        public static final Creator CREATOR = new v();
        public static final PushNotification b = new PushNotification();

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(1);
        }

        private PushNotification() {
            super();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$Square;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "squareId", "", "(Ljava/lang/String;)V", "getSquareId", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class Square extends SchemeServiceReferrer {
        public static final Creator CREATOR = new w();
        private final String b;

        public final int describeContents() {
            return 0;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Square) {
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            String str = this.b;
            return str != null ? str.hashCode() : 0;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("Square(squareId=");
            stringBuilder.append(this.b);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.b);
        }

        public final String b() {
            return this.b;
        }

        public Square(String str) {
            super();
            this.b = str;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$ThingsDevice;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "deviceId", "", "hardwareId", "(Ljava/lang/String;Ljava/lang/String;)V", "getDeviceId", "()Ljava/lang/String;", "getHardwareId", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class ThingsDevice extends SchemeServiceReferrer {
        public static final Creator CREATOR = new x();
        private final String b;
        private final String c;

        public final int describeContents() {
            return 0;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof ThingsDevice) {
                    ThingsDevice thingsDevice = (ThingsDevice) obj;
                    if (acry.a(this.b, thingsDevice.b)) {
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            String str = this.b;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.c;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("ThingsDevice(deviceId=");
            stringBuilder.append(this.b);
            stringBuilder.append(", hardwareId=");
            stringBuilder.append(this.c);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.b);
            parcel.writeString(this.c);
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }

        public ThingsDevice(String str, String str2) {
            super();
            this.b = str;
            this.c = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Ljp/naver/line/android/urlscheme/SchemeServiceReferrer$WalletTab;", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class WalletTab extends SchemeServiceReferrer {
        public static final Creator CREATOR = new z();
        public static final WalletTab b = new WalletTab();

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(1);
        }

        private WalletTab() {
            super();
        }
    }

    private SchemeServiceReferrer() {
    }

    public /* synthetic */ SchemeServiceReferrer(byte b) {
        this();
    }

    public final boolean a() {
        return acry.a((Object) this, PushNotification.b);
    }
}
