package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;

class MediaDescriptionCompatApi23 {

    class Builder {
        public static void setMediaUri(Object obj, Uri uri) {
            ((android.media.MediaDescription.Builder) obj).setMediaUri(uri);
        }

        private Builder() {
        }
    }

    public static Uri getMediaUri(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }

    private MediaDescriptionCompatApi23() {
    }
}
