package com.facebook.react.animated;

import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;
import javax.annotation.Nullable;

class EventAnimationDriver implements RCTEventEmitter {
    private List<String> mEventPath;
    q mValueNode;

    public EventAnimationDriver(List<String> eventPath, q valueNode) {
        this.mEventPath = eventPath;
        this.mValueNode = valueNode;
    }

    public void receiveEvent(int targetTag, String eventName, @Nullable ar event) {
        if (event == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        am curMap = event;
        for (int i = 0; i < this.mEventPath.size() - 1; i++) {
            curMap = curMap.getMap((String) this.mEventPath.get(i));
        }
        this.mValueNode.e = curMap.getDouble((String) this.mEventPath.get(this.mEventPath.size() - 1));
    }

    public void receiveTouches(String eventName, aq touches, aq changedIndices) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
