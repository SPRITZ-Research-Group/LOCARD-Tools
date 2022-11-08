package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.media.session.PlaybackState.CustomAction.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
    public static final Creator<PlaybackStateCompat> CREATOR = new Creator<PlaybackStateCompat>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }
    };
    final int a;
    final long b;
    final long c;
    final float d;
    final long e;
    final int f;
    final CharSequence g;
    final long h;
    List<CustomAction> i;
    final long j;
    final Bundle k;
    private Object l;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions {
    }

    public static final class CustomAction implements Parcelable {
        public static final Creator<CustomAction> CREATOR = new Creator<CustomAction>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new CustomAction[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }
        };
        private final String a;
        private final CharSequence b;
        private final int c;
        private final Bundle d;
        private Object e;

        private CustomAction(String action, CharSequence name, int icon, Bundle extras) {
            this.a = action;
            this.b = name;
            this.c = icon;
            this.d = extras;
        }

        CustomAction(Parcel in) {
            this.a = in.readString();
            this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
            this.c = in.readInt();
            this.d = in.readBundle();
        }

        public final void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.a);
            TextUtils.writeToParcel(this.b, dest, flags);
            dest.writeInt(this.c);
            dest.writeBundle(this.d);
        }

        public final int describeContents() {
            return 0;
        }

        public static CustomAction a(Object customActionObj) {
            if (customActionObj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(((android.media.session.PlaybackState.CustomAction) customActionObj).getAction(), ((android.media.session.PlaybackState.CustomAction) customActionObj).getName(), ((android.media.session.PlaybackState.CustomAction) customActionObj).getIcon(), ((android.media.session.PlaybackState.CustomAction) customActionObj).getExtras());
            customAction.e = customActionObj;
            return customAction;
        }

        public final Object a() {
            if (this.e != null || VERSION.SDK_INT < 21) {
                return this.e;
            }
            String str = this.a;
            CharSequence charSequence = this.b;
            int i = this.c;
            Bundle bundle = this.d;
            Builder builder = new Builder(str, charSequence, i);
            builder.setExtras(bundle);
            this.e = builder.build();
            return this.e;
        }

        public final String toString() {
            return "Action:mName='" + this.b + ", mIcon=" + this.c + ", mExtras=" + this.d;
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaKeyAction {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShuffleMode {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public static final class a {
        private final List<CustomAction> a = new ArrayList();
        private int b;
        private long c;
        private long d;
        private float e;
        private long f;
        private int g;
        private CharSequence h;
        private long i;
        private long j = -1;
        private Bundle k;

        public a(PlaybackStateCompat source) {
            this.b = source.a;
            this.c = source.b;
            this.e = source.d;
            this.i = source.h;
            this.d = source.c;
            this.f = source.e;
            this.g = source.f;
            this.h = source.g;
            if (source.i != null) {
                this.a.addAll(source.i);
            }
            this.j = source.j;
            this.k = source.k;
        }

        public final a a() {
            return a(0, 0, 1.0f, SystemClock.elapsedRealtime());
        }

        public final a a(int state, long position, float playbackSpeed, long updateTime) {
            this.b = state;
            this.c = position;
            this.i = updateTime;
            this.e = playbackSpeed;
            return this;
        }

        public final PlaybackStateCompat b() {
            return new PlaybackStateCompat(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.a, this.j, this.k);
        }
    }

    PlaybackStateCompat(int state, long position, long bufferedPosition, float rate, long actions, int errorCode, CharSequence errorMessage, long updateTime, List<CustomAction> customActions, long activeItemId, Bundle extras) {
        this.a = state;
        this.b = position;
        this.c = bufferedPosition;
        this.d = rate;
        this.e = actions;
        this.f = errorCode;
        this.g = errorMessage;
        this.h = updateTime;
        this.i = new ArrayList(customActions);
        this.j = activeItemId;
        this.k = extras;
    }

    PlaybackStateCompat(Parcel in) {
        this.a = in.readInt();
        this.b = in.readLong();
        this.d = in.readFloat();
        this.h = in.readLong();
        this.c = in.readLong();
        this.e = in.readLong();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.i = in.createTypedArrayList(CustomAction.CREATOR);
        this.j = in.readLong();
        this.k = in.readBundle();
        this.f = in.readInt();
    }

    public final String toString() {
        StringBuilder bob = new StringBuilder("PlaybackState {");
        bob.append("state=").append(this.a);
        bob.append(", position=").append(this.b);
        bob.append(", buffered position=").append(this.c);
        bob.append(", speed=").append(this.d);
        bob.append(", updated=").append(this.h);
        bob.append(", actions=").append(this.e);
        bob.append(", error code=").append(this.f);
        bob.append(", error message=").append(this.g);
        bob.append(", custom actions=").append(this.i);
        bob.append(", active item id=").append(this.j);
        bob.append("}");
        return bob.toString();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a);
        dest.writeLong(this.b);
        dest.writeFloat(this.d);
        dest.writeLong(this.h);
        dest.writeLong(this.c);
        dest.writeLong(this.e);
        TextUtils.writeToParcel(this.g, dest, flags);
        dest.writeTypedList(this.i);
        dest.writeLong(this.j);
        dest.writeBundle(this.k);
        dest.writeInt(this.f);
    }

    public static PlaybackStateCompat a(Object stateObj) {
        if (stateObj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        Bundle extras;
        List<Object> customActionObjs = ((PlaybackState) stateObj).getCustomActions();
        List<CustomAction> customActions = null;
        if (customActionObjs != null) {
            customActions = new ArrayList(customActionObjs.size());
            for (Object customActionObj : customActionObjs) {
                customActions.add(CustomAction.a(customActionObj));
            }
        }
        if (VERSION.SDK_INT >= 22) {
            extras = ((PlaybackState) stateObj).getExtras();
        } else {
            extras = null;
        }
        PlaybackStateCompat state = new PlaybackStateCompat(((PlaybackState) stateObj).getState(), ((PlaybackState) stateObj).getPosition(), ((PlaybackState) stateObj).getBufferedPosition(), ((PlaybackState) stateObj).getPlaybackSpeed(), ((PlaybackState) stateObj).getActions(), 0, ((PlaybackState) stateObj).getErrorMessage(), ((PlaybackState) stateObj).getLastPositionUpdateTime(), customActions, ((PlaybackState) stateObj).getActiveQueueItemId(), extras);
        state.l = stateObj;
        return state;
    }

    public final Object a() {
        if (this.l == null && VERSION.SDK_INT >= 21) {
            List<Object> customActions = null;
            if (this.i != null) {
                customActions = new ArrayList(this.i.size());
                for (CustomAction customAction : this.i) {
                    customActions.add(customAction.a());
                }
            }
            if (VERSION.SDK_INT >= 22) {
                this.l = h.a(this.a, this.b, this.c, this.d, this.e, this.g, this.h, customActions, this.j, this.k);
            } else {
                this.l = g.a(this.a, this.b, this.c, this.d, this.e, this.g, this.h, customActions, this.j);
            }
        }
        return this.l;
    }
}
