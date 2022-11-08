package com.skypecam.camera2;

import kotlin.Metadata;
import kotlin.jvm.b.c;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\b\u0001\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0015"}, d2 = {"Lcom/skypecam/camera2/HardwareLevel;", "", "index", "", "nativeValue", "(Ljava/lang/String;IILjava/lang/Integer;)V", "getIndex", "()I", "getNativeValue", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "isGreaterOrEqualThan", "", "otherLevel", "UNKNOWN", "LEGACY", "LIMITED", "FULL", "LEVEL_3", "LEVEL_FUTURE", "Companion", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public enum j {
    ;
    
    public static final a g = null;
    private final int i;
    @Nullable
    private final Integer j;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/skypecam/camera2/HardwareLevel$Companion;", "", "()V", "fromIndex", "Lcom/skypecam/camera2/HardwareLevel;", "value", "", "fromNativeValue", "nativeValue", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }
    }

    private j(int index, Integer nativeValue) {
        this.i = index;
        this.j = nativeValue;
    }

    public final int a() {
        return this.i;
    }

    @Nullable
    public final Integer b() {
        return this.j;
    }

    static {
        g = new a();
    }

    public final boolean a(@NotNull j otherLevel) {
        c.b(otherLevel, "otherLevel");
        return this.i >= otherLevel.i;
    }
}
