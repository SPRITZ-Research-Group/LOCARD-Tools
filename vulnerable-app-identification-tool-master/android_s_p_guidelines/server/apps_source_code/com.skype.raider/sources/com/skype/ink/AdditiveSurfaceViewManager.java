package com.skype.ink;

import android.graphics.Bitmap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

public class AdditiveSurfaceViewManager extends BaseViewManager<AdditiveSurfaceView, AdditiveSurfaceViewShadowNode> {
    public static final int ADD_PATH = 1;
    private static final String REACT_CLASS = "AdditiveSurfaceView";
    public static final int UNDO_PATH = 2;

    public AdditiveSurfaceViewManager(ag reactContext) {
    }

    public String getName() {
        return REACT_CLASS;
    }

    public AdditiveSurfaceViewShadowNode createShadowNodeInstance() {
        return new AdditiveSurfaceViewShadowNode();
    }

    public Class<? extends AdditiveSurfaceViewShadowNode> getShadowNodeClass() {
        return AdditiveSurfaceViewShadowNode.class;
    }

    protected AdditiveSurfaceView createViewInstance(ae reactContext) {
        return new AdditiveSurfaceView(reactContext);
    }

    public void updateExtraData(AdditiveSurfaceView root, Object extraData) {
        root.setBitmap((Bitmap) extraData);
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("addPath", Integer.valueOf(1), "undoPath", Integer.valueOf(2));
    }

    public void receiveCommand(AdditiveSurfaceView surfaceView, int commandType, al args) {
        switch (commandType) {
            case 1:
                surfaceView.a(args);
                return;
            case 2:
                surfaceView.a();
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }

    @ReactProp(name = "levelsOfUndo")
    public void setLevelsOfUndo(AdditiveSurfaceView view, Integer levelsOfUndo) {
        view.setUndoLimit(levelsOfUndo.intValue());
    }
}
