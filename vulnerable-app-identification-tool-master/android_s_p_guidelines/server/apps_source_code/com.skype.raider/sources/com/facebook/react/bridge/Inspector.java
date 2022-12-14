package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DoNotStrip
public class Inspector {
    private final HybridData mHybridData;

    @DoNotStrip
    public static class LocalConnection {
        private final HybridData mHybridData;

        public native void disconnect();

        public native void sendMessage(String str);

        private LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }

    @DoNotStrip
    public static class Page {
        private final int mId;
        private final String mTitle;

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String toString() {
            return "Page{mId=" + this.mId + ", mTitle='" + this.mTitle + '\'' + '}';
        }

        @DoNotStrip
        private Page(int id, String title) {
            this.mId = id;
            this.mTitle = title;
        }
    }

    @DoNotStrip
    public interface RemoteConnection {
        void onDisconnect();

        void onMessage(String str);
    }

    private native LocalConnection connectNative(int i, RemoteConnection remoteConnection);

    private native Page[] getPagesNative();

    private static native Inspector instance();

    static {
        ah.a();
    }

    public static boolean isSupported() {
        try {
            instance().getPagesNative();
            return true;
        } catch (UnsatisfiedLinkError e) {
            return false;
        }
    }

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (Throwable e) {
            FLog.e("React", "Inspector doesn't work in open source yet", e);
            return Collections.emptyList();
        }
    }

    public static LocalConnection connect(int pageId, RemoteConnection remote) {
        try {
            return instance().connectNative(pageId, remote);
        } catch (Throwable e) {
            FLog.e("React", "Inspector doesn't work in open source yet", e);
            throw new RuntimeException(e);
        }
    }

    private Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
