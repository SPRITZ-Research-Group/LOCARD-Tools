package com.microsoft.urlrequest;

import android.os.Bundle;
import com.microsoft.react.videofxp.VideoFXPModule;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class a {
    static final Map<String, String> a;

    static {
        Map<String, String> kind = new HashMap();
        kind.put("eventKindResponse", "eventKindResponse");
        kind.put("eventKindClientFailure", "eventKindClientFailure");
        kind.put("eventKindProgress", "eventKindProgress");
        kind.put("eventKindAllBackgroundRequestsFinished", "eventKindAllBackgroundRequestsFinished");
        a = Collections.unmodifiableMap(kind);
    }

    static Bundle a(String id, int statusCode, HashMap<String, String> headers, String body) {
        Bundle bundle = new Bundle();
        bundle.putString("kind", "eventKindResponse");
        bundle.putString("id", id);
        bundle.putInt("responseCode", statusCode);
        bundle.putSerializable("responseHeaders", headers);
        bundle.putString("response", body);
        return bundle;
    }

    static Bundle a(String id, String error) {
        Bundle bundle = new Bundle();
        bundle.putString("kind", "eventKindClientFailure");
        bundle.putString("id", id);
        bundle.putInt("responseCode", 0);
        bundle.putSerializable("responseHeaders", new HashMap());
        bundle.putString("error", error);
        return bundle;
    }

    static Bundle a(String id, double progress) {
        Bundle bundle = new Bundle();
        bundle.putString("kind", "eventKindProgress");
        bundle.putString("id", id);
        bundle.putDouble(VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, progress);
        return bundle;
    }

    static Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("kind", "eventKindAllBackgroundRequestsFinished");
        return bundle;
    }
}
