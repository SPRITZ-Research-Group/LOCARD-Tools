package android.support.v4.media;

import android.media.Rating;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RatingCompat implements Parcelable {
    public static final Creator<RatingCompat> CREATOR = new Creator<RatingCompat>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new RatingCompat[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }
    };
    private final int a;
    private final float b;
    private Object c;

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StarStyle {
    }

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    RatingCompat(int ratingStyle, float rating) {
        this.a = ratingStyle;
        this.b = rating;
    }

    public final String toString() {
        String str;
        StringBuilder append = new StringBuilder("Rating:style=").append(this.a).append(" rating=");
        if (this.b < 0.0f) {
            str = "unrated";
        } else {
            str = String.valueOf(this.b);
        }
        return append.append(str).toString();
    }

    public final int describeContents() {
        return this.a;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a);
        dest.writeFloat(this.b);
    }

    public static RatingCompat a(Object ratingObj) {
        float f = 1.0f;
        RatingCompat rating = null;
        if (ratingObj != null && VERSION.SDK_INT >= 19) {
            int ratingStyle = ((Rating) ratingObj).getRatingStyle();
            if (!((Rating) ratingObj).isRated()) {
                switch (ratingStyle) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        rating = new RatingCompat(ratingStyle, -1.0f);
                        break;
                }
            }
            float f2;
            switch (ratingStyle) {
                case 1:
                    rating = new RatingCompat(1, ((Rating) ratingObj).hasHeart() ? 1.0f : 0.0f);
                    break;
                case 2:
                    if (!((Rating) ratingObj).isThumbUp()) {
                        f = 0.0f;
                    }
                    rating = new RatingCompat(2, f);
                    break;
                case 3:
                case 4:
                case 5:
                    f = ((Rating) ratingObj).getStarRating();
                    switch (ratingStyle) {
                        case 3:
                            f2 = 3.0f;
                            break;
                        case 4:
                            f2 = 4.0f;
                            break;
                        case 5:
                            f2 = 5.0f;
                            break;
                        default:
                            new StringBuilder("Invalid rating style (").append(ratingStyle).append(") for a star rating");
                            break;
                    }
                    if (f >= 0.0f && f <= f2) {
                        rating = new RatingCompat(ratingStyle, f);
                        break;
                    }
                case 6:
                    f2 = ((Rating) ratingObj).getPercentRating();
                    if (f2 >= 0.0f && f2 <= 100.0f) {
                        rating = new RatingCompat(6, f2);
                        break;
                    }
            }
            rating.c = ratingObj;
        }
        return rating;
    }
}
