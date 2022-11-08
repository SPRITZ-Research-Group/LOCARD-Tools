package com.skypecam.camera2;

import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J,\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lcom/skypecam/camera2/VideoCapturedCallback;", "", "onVideoRecordingCanceled", "", "onVideoRecordingCompleted", "file", "Ljava/io/File;", "thumbnailFile", "width", "", "height", "onVideoRecordingFailed", "message", "", "onVideoRecordingStateChanged", "recording", "", "view", "Lcom/skypecam/camera2/CameraView;", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public interface o {
    void a(@Nullable File file, @Nullable File file2, int i, int i2);

    void a(@NotNull String str);

    void a(boolean z, @NotNull CameraView cameraView);
}
