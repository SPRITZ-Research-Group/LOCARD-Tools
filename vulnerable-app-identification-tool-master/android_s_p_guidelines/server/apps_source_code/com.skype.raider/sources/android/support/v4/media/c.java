package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(24)
final class c {

    interface a extends c {
        void b(List<?> list);
    }

    static class b<T extends a> extends d<T> {
        public b(T callback) {
            super(callback);
        }

        public final void onChildrenLoaded(@NonNull String parentId, List<MediaItem> children, @NonNull Bundle options) {
            ((a) this.a).b(children);
        }

        public final void onError(@NonNull String parentId, @NonNull Bundle options) {
        }
    }
}
