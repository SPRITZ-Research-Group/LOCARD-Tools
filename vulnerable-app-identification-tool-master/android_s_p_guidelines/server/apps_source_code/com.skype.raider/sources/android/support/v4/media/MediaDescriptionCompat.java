package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.media.MediaDescription.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    public static final Creator<MediaDescriptionCompat> CREATOR = new Creator<MediaDescriptionCompat>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            if (VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(parcel);
            }
            return MediaDescriptionCompat.a(MediaDescription.CREATOR.createFromParcel(parcel));
        }
    };
    private final String a;
    private final CharSequence b;
    private final CharSequence c;
    private final CharSequence d;
    private final Bitmap e;
    private final Uri f;
    private final Bundle g;
    private final Uri h;
    private Object i;

    public static final class a {
        private String a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public final a a(@Nullable String mediaId) {
            this.a = mediaId;
            return this;
        }

        public final a a(@Nullable CharSequence title) {
            this.b = title;
            return this;
        }

        public final a b(@Nullable CharSequence subtitle) {
            this.c = subtitle;
            return this;
        }

        public final a c(@Nullable CharSequence description) {
            this.d = description;
            return this;
        }

        public final a a(@Nullable Bitmap icon) {
            this.e = icon;
            return this;
        }

        public final a a(@Nullable Uri iconUri) {
            this.f = iconUri;
            return this;
        }

        public final a a(@Nullable Bundle extras) {
            this.g = extras;
            return this;
        }

        public final a b(@Nullable Uri mediaUri) {
            this.h = mediaUri;
            return this;
        }

        public final MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }
    }

    MediaDescriptionCompat(String mediaId, CharSequence title, CharSequence subtitle, CharSequence description, Bitmap icon, Uri iconUri, Bundle extras, Uri mediaUri) {
        this.a = mediaId;
        this.b = title;
        this.c = subtitle;
        this.d = description;
        this.e = icon;
        this.f = iconUri;
        this.g = extras;
        this.h = mediaUri;
    }

    MediaDescriptionCompat(Parcel in) {
        this.a = in.readString();
        this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.e = (Bitmap) in.readParcelable(null);
        this.f = (Uri) in.readParcelable(null);
        this.g = in.readBundle();
        this.h = (Uri) in.readParcelable(null);
    }

    @Nullable
    public final String a() {
        return this.a;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        if (VERSION.SDK_INT < 21) {
            dest.writeString(this.a);
            TextUtils.writeToParcel(this.b, dest, flags);
            TextUtils.writeToParcel(this.c, dest, flags);
            TextUtils.writeToParcel(this.d, dest, flags);
            dest.writeParcelable(this.e, flags);
            dest.writeParcelable(this.f, flags);
            dest.writeBundle(this.g);
            dest.writeParcelable(this.h, flags);
            return;
        }
        Object obj;
        if (this.i != null || VERSION.SDK_INT < 21) {
            obj = this.i;
        } else {
            Bundle bundle;
            Builder builder = new Builder();
            builder.setMediaId(this.a);
            builder.setTitle(this.b);
            builder.setSubtitle(this.c);
            builder.setDescription(this.d);
            builder.setIconBitmap(this.e);
            builder.setIconUri(this.f);
            Bundle bundle2 = this.g;
            if (VERSION.SDK_INT >= 23 || this.h == null) {
                bundle = bundle2;
            } else {
                if (bundle2 == null) {
                    bundle2 = new Bundle();
                    bundle2.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
                }
                bundle2.putParcelable("android.support.v4.media.description.MEDIA_URI", this.h);
                bundle = bundle2;
            }
            builder.setExtras(bundle);
            if (VERSION.SDK_INT >= 23) {
                builder.setMediaUri(this.h);
            }
            this.i = builder.build();
            obj = this.i;
        }
        ((MediaDescription) obj).writeToParcel(dest, flags);
    }

    public final String toString() {
        return this.b + ", " + this.c + ", " + this.d;
    }

    public static MediaDescriptionCompat a(Object descriptionObj) {
        if (descriptionObj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        Uri mediaUri;
        a bob = new a();
        bob.a(((MediaDescription) descriptionObj).getMediaId());
        bob.a(((MediaDescription) descriptionObj).getTitle());
        bob.b(((MediaDescription) descriptionObj).getSubtitle());
        bob.c(((MediaDescription) descriptionObj).getDescription());
        bob.a(((MediaDescription) descriptionObj).getIconBitmap());
        bob.a(((MediaDescription) descriptionObj).getIconUri());
        Bundle extras = ((MediaDescription) descriptionObj).getExtras();
        if (extras == null) {
            mediaUri = null;
        } else {
            mediaUri = (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI");
        }
        if (mediaUri != null) {
            if (extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && extras.size() == 2) {
                extras = null;
            } else {
                extras.remove("android.support.v4.media.description.MEDIA_URI");
                extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            }
        }
        bob.a(extras);
        if (mediaUri != null) {
            bob.b(mediaUri);
        } else if (VERSION.SDK_INT >= 23) {
            bob.b(((MediaDescription) descriptionObj).getMediaUri());
        }
        MediaDescriptionCompat descriptionCompat = bob.a();
        descriptionCompat.i = descriptionObj;
        return descriptionCompat;
    }
}
