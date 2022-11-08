package com.google.android.exoplayer2.metadata.emsg;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import java.util.Arrays;

public final class EventMessage implements Entry {
    public static final Creator<EventMessage> CREATOR = new Creator<EventMessage>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new EventMessage[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new EventMessage(parcel);
        }
    };
    public final String a;
    public final String b;
    public final long c;
    public final long d;
    public final byte[] e;
    private int f;

    public EventMessage(String schemeIdUri, String value, long durationMs, long id, byte[] messageData) {
        this.a = schemeIdUri;
        this.b = value;
        this.c = durationMs;
        this.d = id;
        this.e = messageData;
    }

    EventMessage(Parcel in) {
        this.a = in.readString();
        this.b = in.readString();
        this.c = in.readLong();
        this.d = in.readLong();
        this.e = in.createByteArray();
    }

    public final int hashCode() {
        int i = 0;
        if (this.f == 0) {
            int hashCode = ((this.a != null ? this.a.hashCode() : 0) + 527) * 31;
            if (this.b != null) {
                i = this.b.hashCode();
            }
            this.f = ((((((hashCode + i) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + Arrays.hashCode(this.e);
        }
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventMessage other = (EventMessage) obj;
        if (this.c == other.c && this.d == other.d && s.a(this.a, other.a) && s.a(this.b, other.b) && Arrays.equals(this.e, other.e)) {
            return true;
        }
        return false;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeString(this.b);
        dest.writeLong(this.c);
        dest.writeLong(this.d);
        dest.writeByteArray(this.e);
    }
}
