package android.support.v4.media.session;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaController.Callback;
import android.media.session.MediaController.PlaybackInfo;
import android.media.session.MediaSession.QueueItem;
import android.media.session.MediaSession.Token;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(21)
final class c {

    public interface a {
        void a();

        void a(int i, int i2, int i3, int i4, int i5);

        void a(Object obj);

        void a(List<?> list);

        void b();

        void b(Object obj);

        void c();

        void d();
    }

    static class b<T extends a> extends Callback {
        protected final T a;

        public b(T callback) {
            this.a = callback;
        }

        public final void onSessionDestroyed() {
            this.a.a();
        }

        public final void onSessionEvent(String event, Bundle extras) {
            this.a.b();
        }

        public final void onPlaybackStateChanged(PlaybackState state) {
            this.a.a((Object) state);
        }

        public final void onMetadataChanged(MediaMetadata metadata) {
            this.a.b(metadata);
        }

        public final void onQueueChanged(List<QueueItem> queue) {
            this.a.a((List) queue);
        }

        public final void onQueueTitleChanged(CharSequence title) {
            this.a.c();
        }

        public final void onExtrasChanged(Bundle extras) {
            this.a.d();
        }

        public final void onAudioInfoChanged(PlaybackInfo info) {
            int i;
            a aVar = this.a;
            int playbackType = info.getPlaybackType();
            AudioAttributes audioAttributes = info.getAudioAttributes();
            if ((audioAttributes.getFlags() & 1) != 1) {
                if ((audioAttributes.getFlags() & 4) != 4) {
                    switch (audioAttributes.getUsage()) {
                        case 1:
                        case 11:
                        case 12:
                        case 14:
                            i = 3;
                            break;
                        case 2:
                            i = 0;
                            break;
                        case 3:
                            i = 8;
                            break;
                        case 4:
                            i = 4;
                            break;
                        case 5:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            i = 5;
                            break;
                        case 6:
                            i = 2;
                            break;
                        case 13:
                            i = 1;
                            break;
                        default:
                            i = 3;
                            break;
                    }
                }
                i = 6;
            } else {
                i = 7;
            }
            aVar.a(playbackType, i, info.getVolumeControl(), info.getMaxVolume(), info.getCurrentVolume());
        }
    }

    public static Object a(Context context, Object sessionToken) {
        return new MediaController(context, (Token) sessionToken);
    }
}
