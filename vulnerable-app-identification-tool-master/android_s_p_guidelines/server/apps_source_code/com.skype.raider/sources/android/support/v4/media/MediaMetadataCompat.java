package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class MediaMetadataCompat implements Parcelable {
    public static final Creator<MediaMetadataCompat> CREATOR = new Creator<MediaMetadataCompat>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new MediaMetadataCompat[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new MediaMetadataCompat(parcel);
        }
    };
    static final android.support.v4.util.a<String, Integer> a;
    private static final String[] c = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
    private static final String[] d = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
    private static final String[] e = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
    final Bundle b;
    private Object f;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BitmapKey {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LongKey {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RatingKey {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextKey {
    }

    public static final class a {
        private final Bundle a;

        public a() {
            this.a = new Bundle();
        }

        private a(MediaMetadataCompat source) {
            this.a = new Bundle(source.b);
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public a(MediaMetadataCompat source, int maxBitmapSize) {
            this(source);
            for (String key : this.a.keySet()) {
                Bitmap value = this.a.get(key);
                if (value instanceof Bitmap) {
                    Bitmap bmp = value;
                    if (bmp.getHeight() > maxBitmapSize || bmp.getWidth() > maxBitmapSize) {
                        float f = (float) maxBitmapSize;
                        f = Math.min(f / ((float) bmp.getWidth()), f / ((float) bmp.getHeight()));
                        Parcelable createScaledBitmap = Bitmap.createScaledBitmap(bmp, (int) (f * ((float) bmp.getWidth())), (int) (((float) bmp.getHeight()) * f), true);
                        if (!MediaMetadataCompat.a.containsKey(key) || ((Integer) MediaMetadataCompat.a.get(key)).intValue() == 2) {
                            this.a.putParcelable(key, createScaledBitmap);
                        } else {
                            throw new IllegalArgumentException("The " + key + " key cannot be used to put a Bitmap");
                        }
                    }
                }
            }
        }

        public final MediaMetadataCompat a() {
            return new MediaMetadataCompat(this.a);
        }
    }

    static {
        android.support.v4.util.a aVar = new android.support.v4.util.a();
        a = aVar;
        aVar.put("android.media.metadata.TITLE", Integer.valueOf(1));
        a.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        a.put("android.media.metadata.DURATION", Integer.valueOf(0));
        a.put("android.media.metadata.ALBUM", Integer.valueOf(1));
        a.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        a.put("android.media.metadata.WRITER", Integer.valueOf(1));
        a.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        a.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
        a.put("android.media.metadata.DATE", Integer.valueOf(1));
        a.put("android.media.metadata.YEAR", Integer.valueOf(0));
        a.put("android.media.metadata.GENRE", Integer.valueOf(1));
        a.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        a.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
        a.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        a.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        a.put("android.media.metadata.ART", Integer.valueOf(2));
        a.put("android.media.metadata.ART_URI", Integer.valueOf(1));
        a.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
        a.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
        a.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
        a.put("android.media.metadata.RATING", Integer.valueOf(3));
        a.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
        a.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
        a.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
        a.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
        a.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
        a.put("android.media.metadata.MEDIA_ID", Integer.valueOf(1));
        a.put("android.media.metadata.BT_FOLDER_TYPE", Integer.valueOf(0));
        a.put("android.media.metadata.MEDIA_URI", Integer.valueOf(1));
        a.put("android.media.metadata.ADVERTISEMENT", Integer.valueOf(0));
        a.put("android.media.metadata.DOWNLOAD_STATUS", Integer.valueOf(0));
    }

    MediaMetadataCompat(Bundle bundle) {
        this.b = new Bundle(bundle);
    }

    MediaMetadataCompat(Parcel in) {
        this.b = in.readBundle();
    }

    public final boolean a(String key) {
        return this.b.containsKey(key);
    }

    public final long b(String key) {
        return this.b.getLong(key, 0);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.b);
    }

    public final Bundle a() {
        return this.b;
    }

    public static MediaMetadataCompat a(Object metadataObj) {
        if (metadataObj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        Parcel p = Parcel.obtain();
        ((MediaMetadata) metadataObj).writeToParcel(p, 0);
        p.setDataPosition(0);
        MediaMetadataCompat metadata = (MediaMetadataCompat) CREATOR.createFromParcel(p);
        p.recycle();
        metadata.f = metadataObj;
        return metadata;
    }
}
