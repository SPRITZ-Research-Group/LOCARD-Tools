package com.microsoft.dl.video.capture.impl.mock;

import com.microsoft.dl.video.capture.api.CameraCapabilities;
import java.util.Collection;

public interface MockCameraManager {
    MockCamera getOpenCamera(int i);

    void setMockCameraConfigs(Collection<CameraCapabilities> collection, int i);
}
