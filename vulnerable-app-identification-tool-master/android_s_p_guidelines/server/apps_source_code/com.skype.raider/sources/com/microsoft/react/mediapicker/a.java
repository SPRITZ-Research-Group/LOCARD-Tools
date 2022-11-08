package com.microsoft.react.mediapicker;

import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.microsoft.react.a.c;
import com.microsoft.react.a.e;
import com.microsoft.react.a.f;
import java.util.Set;
import javax.annotation.Nullable;

final class a {
    private final ai a;
    private final MediaPickerView b;

    a(ai context, MediaPickerView view) {
        this.a = context;
        this.b = view;
    }

    private void a(String eventName, ar payload) {
        MediaPickerViewManager.sendEvent(this.a, eventName, this.b, payload);
    }

    public final void a(int count) {
        ar event = new WritableNativeMap();
        event.putInt("loadedCount", count);
        a(MediaPickerViewManager.SEND_PHOTOS_LOADED, event);
    }

    public final void a() {
        ar event = new WritableNativeMap();
        event.putBoolean("scrolledToTop", true);
        a(MediaPickerViewManager.SEND_SCROLLED_TO_TOP, event);
    }

    public final void a(Set<c> selectedMediaSet) {
        ar event = new WritableNativeMap();
        aq files = new WritableNativeArray();
        for (c media : selectedMediaSet) {
            f size;
            if (media.a.c) {
                size = e.b(media.a.a);
                media.a.h = size.b;
                media.a.i = size.a;
            }
            String fileUri = media.a.a.toString();
            if (!media.a.c && (media.a.h <= 0 || media.a.i <= 0)) {
                FLog.w("MediaPickerView.Events", "uri " + fileUri + " store width " + media.a.h + " and store height " + media.a.i);
                size = e.a(media.a.a);
                media.a.h = size.b;
                media.a.i = size.a;
                FLog.w("MediaPickerView.Events", "uri " + fileUri + " exif width " + media.a.h + " and exif height " + media.a.i);
            }
            ar file = new WritableNativeMap();
            file.putString(ReactVideoViewManager.PROP_SRC_URI, fileUri);
            file.putInt("width", media.a.h);
            file.putInt("height", media.a.i);
            file.putInt("size", (int) media.a.g);
            file.putInt("duration", (int) media.a.d);
            if (media.a.c) {
                if (media.b == null) {
                    e.a(this.a, media);
                }
                if (media.b != null) {
                    file.putString("thumbnailUri", media.b.a.toString());
                    file.putInt("thumbnailWidth", media.b.b);
                    file.putInt("thumbnailHeight", media.b.c);
                }
            }
            files.pushMap(file);
        }
        event.putArray("files", files);
        a(MediaPickerViewManager.SEND_SELECTED_IMAGES, event);
    }

    public final void a(@Nullable c mediaFile, boolean selected, int count) {
        ar event = new WritableNativeMap();
        event.putInt("count", count);
        if (mediaFile != null) {
            event.putMap("edge", e.a(this.a, mediaFile, selected));
            event.putBoolean("selected", selected);
        }
        a(MediaPickerViewManager.SELECT_IMAGE_EVENT_NAME, event);
    }
}
