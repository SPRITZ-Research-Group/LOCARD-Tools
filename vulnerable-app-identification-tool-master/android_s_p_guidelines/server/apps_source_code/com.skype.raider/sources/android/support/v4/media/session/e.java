package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
final class e {

    public interface a extends a {
    }

    static class b<T extends a> extends b<T> {
        public b(T callback) {
            super(callback);
        }

        public void onPlayFromUri(Uri uri, Bundle extras) {
        }
    }
}
