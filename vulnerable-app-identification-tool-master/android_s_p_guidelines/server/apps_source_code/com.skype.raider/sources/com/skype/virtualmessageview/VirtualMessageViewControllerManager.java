package com.skype.virtualmessageview;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.p;
import java.util.Map;

public class VirtualMessageViewControllerManager extends SimpleViewManager<VirtualMessageViewController> {
    private static final int COMMAND_SCROLL_BY = 1;
    private static final String REACT_CLASS = "VirtualMessageViewController";
    public static final String SCROLL_SET_EVENT = "onControllerScrollPositionSet";

    public String getName() {
        return REACT_CLASS;
    }

    public VirtualMessageViewController createViewInstance(ae context) {
        return new VirtualMessageViewController(context);
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return e.a("scrollBy", Integer.valueOf(1));
    }

    public void receiveCommand(VirtualMessageViewController controller, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                controller.a((int) Math.floor((double) p.a((float) args.getDouble(0))), args.getMap(1), args.getBoolean(2));
                return;
            default:
                return;
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a().a(SCROLL_SET_EVENT, e.a("registrationName", SCROLL_SET_EVENT)).a();
    }
}
