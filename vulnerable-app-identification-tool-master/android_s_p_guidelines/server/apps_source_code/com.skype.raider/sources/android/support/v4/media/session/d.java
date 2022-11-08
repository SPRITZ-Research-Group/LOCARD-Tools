package android.support.v4.media.session;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaSession.Callback;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
final class d {

    interface a {
        void a(Object obj);

        void a(String str, Bundle bundle);

        void a(String str, Bundle bundle, ResultReceiver resultReceiver);

        boolean a(Intent intent);
    }

    static class b<T extends a> extends Callback {
        protected final T a;

        public b(T callback) {
            this.a = callback;
        }

        public void onCommand(String command, Bundle args, ResultReceiver cb) {
            this.a.a(command, args, cb);
        }

        public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
            return this.a.a(mediaButtonIntent) || super.onMediaButtonEvent(mediaButtonIntent);
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String mediaId, Bundle extras) {
        }

        public void onPlayFromSearch(String search, Bundle extras) {
        }

        public void onSkipToQueueItem(long id) {
        }

        public void onPause() {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onFastForward() {
        }

        public void onRewind() {
        }

        public void onStop() {
        }

        public void onSeekTo(long pos) {
        }

        public void onSetRating(Rating rating) {
            this.a.a((Object) rating);
        }

        public void onCustomAction(String action, Bundle extras) {
            this.a.a(action, extras);
        }
    }
}
