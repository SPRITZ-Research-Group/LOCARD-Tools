package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(24)
final class f {

    public interface a extends android.support.v4.media.session.e.a {
    }

    static class b<T extends a> extends b<T> {
        public b(T callback) {
            super(callback);
        }

        public final void onPrepare() {
        }

        public final void onPrepareFromMediaId(String mediaId, Bundle extras) {
        }

        public final void onPrepareFromSearch(String query, Bundle extras) {
        }

        public final void onPrepareFromUri(Uri uri, Bundle extras) {
        }
    }
}
