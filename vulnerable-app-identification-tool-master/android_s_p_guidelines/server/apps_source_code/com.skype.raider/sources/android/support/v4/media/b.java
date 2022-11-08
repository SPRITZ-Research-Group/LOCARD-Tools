package android.support.v4.media;

import android.media.browse.MediaBrowser.ConnectionCallback;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.browse.MediaBrowser.SubscriptionCallback;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(21)
final class b {

    interface a {
        void a();

        void b();

        void c();
    }

    interface c {
        void a(List<?> list);
    }

    static class b<T extends a> extends ConnectionCallback {
        protected final T a;

        public b(T connectionCallback) {
            this.a = connectionCallback;
        }

        public final void onConnected() {
            this.a.a();
        }

        public final void onConnectionSuspended() {
            this.a.b();
        }

        public final void onConnectionFailed() {
            this.a.c();
        }
    }

    static class d<T extends c> extends SubscriptionCallback {
        protected final T a;

        public d(T callback) {
            this.a = callback;
        }

        public void onChildrenLoaded(@NonNull String parentId, List<MediaItem> children) {
            this.a.a(children);
        }

        public void onError(@NonNull String parentId) {
        }
    }
}
