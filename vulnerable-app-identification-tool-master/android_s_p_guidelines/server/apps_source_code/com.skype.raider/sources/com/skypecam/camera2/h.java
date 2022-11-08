package com.skypecam.camera2;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/skypecam/camera2/FlashMode;", "", "intValue", "", "(Ljava/lang/String;II)V", "getIntValue", "()I", "OFF", "ON", "AUTO", "Companion", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public enum h {
    ;
    
    public static final a d = null;
    private final int f;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/skypecam/camera2/FlashMode$Companion;", "", "()V", "valueOf", "Lcom/skypecam/camera2/FlashMode;", "intValue", "", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        @NotNull
        public static h a(int intValue) {
            for (h hVar : h.values()) {
                if ((hVar.a() == intValue ? 1 : null) != null) {
                    break;
                }
            }
            h hVar2 = null;
            return hVar2 == null ? h.a : hVar2;
        }
    }

    private h(int intValue) {
        this.f = intValue;
    }

    public final int a() {
        return this.f;
    }

    static {
        d = new a();
    }
}
