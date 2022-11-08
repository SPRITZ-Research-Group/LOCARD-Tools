package com.microsoft.react.mediapicker;

import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class MediaPickerViewManager extends BaseViewManager<MediaPickerView, c> {
    public static final int GET_SELECTED_IMAGES = 1;
    public static final String IndexReplacementKey = "__INDEX__";
    public static final String REACT_CLASS = "MediaPickerView";
    public static final String RECENT_ASSET_THUMBNAIL_CHANGED_EVENT_NAME = "RecentAssetThumbnailChangedEvent";
    public static final int SCROLL_TO_TOP = 2;
    public static final String SELECT_IMAGE_EVENT_NAME = "evtSelectImages";
    public static final String SEND_PHOTOS_LOADED = "evtSendPhotosLoaded";
    public static final String SEND_SCROLLED_TO_TOP = "evtSendScolledToTop";
    public static final String SEND_SELECTED_IMAGES = "evtSendSelectedImages";
    public static final String TotalReplacementKey = "__TOTAL__";
    public static final String TypeReplacementKey = "__TYPE__";
    private ae context;

    public String getName() {
        return REACT_CLASS;
    }

    public c createShadowNodeInstance() {
        return new c();
    }

    public Class<? extends c> getShadowNodeClass() {
        return c.class;
    }

    public MediaPickerView createViewInstance(ae context) {
        this.context = context;
        return new MediaPickerView(context);
    }

    public void updateExtraData(MediaPickerView root, Object extraData) {
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.a(SELECT_IMAGE_EVENT_NAME, e.a("registrationName", "onSelectImages"), SEND_SELECTED_IMAGES, e.a("registrationName", "onSendSelectedImages"), SEND_SCROLLED_TO_TOP, e.a("registrationName", "onScrolledToTop"), SEND_PHOTOS_LOADED, e.a("registrationName", "onPhotosLoaded"));
    }

    @ReactProp(name = "allowMultipleSelections")
    public void setAllowMultipleSelections(MediaPickerView view, boolean allow) {
        view.setAllowMultipleSelection(allow);
    }

    @ReactProp(name = "maxSelectionCount")
    public void setMaxSelectionCount(MediaPickerView view, int count) {
        view.setMaxSelectionCount(count);
    }

    @ReactProp(name = "allowVideo")
    public void setAllowVideo(MediaPickerView view, boolean allow) {
        view.setAllowVideo(allow);
    }

    @ReactProp(name = "disableGifs")
    public void setDisableGifs(MediaPickerView view, boolean disableGifs) {
        view.setDisableGifs(disableGifs);
    }

    @ReactProp(name = "gridPadding")
    public void setGridPadding(MediaPickerView view, int padding) {
        view.setGridPadding(padding);
    }

    @ReactProp(name = "maxThumbnailSize")
    public void setMaxThumbnailSize(MediaPickerView view, int maxSize) {
        view.setMaxThumbnailSize(maxSize);
    }

    @ReactProp(name = "album")
    public void setAlbum(MediaPickerView view, String album) {
        view.setAlbum(album);
    }

    @ReactProp(name = "disableScrolling")
    public void setDisableScrolling(MediaPickerView view, boolean disableScrolling) {
        view.setDisableScrolling(disableScrolling);
    }

    @ReactProp(name = "accessibilityLabelDefault")
    public void setAccessibilityLabelDefault(MediaPickerView view, String label) {
        view.setAccessibilityLabelDefault(label);
    }

    @ReactProp(name = "accessibilityLabelSelected")
    public void setAccessibilityLabelSelected(MediaPickerView view, String label) {
        view.setAccessibilityLabelSelected(label);
    }

    @ReactProp(name = "typeVideoAccessibilityLabel")
    public void setVideoAccessibilityLabel(MediaPickerView view, String label) {
        view.setVideoAccessibilityLabel(label);
    }

    @ReactProp(name = "typePhotoAccessibilityLabel")
    public void setPhotoAccessibilityLabel(MediaPickerView view, String label) {
        view.setPhotoAccessibilityLabel(label);
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("getSelectedImages", Integer.valueOf(1), "scrollToTop", Integer.valueOf(2));
    }

    @Nullable
    public Map getExportedViewConstants() {
        return e.a("IndexReplacementKey", IndexReplacementKey, "TotalReplacementKey", TotalReplacementKey, "TypeReplacementKey", TypeReplacementKey, RECENT_ASSET_THUMBNAIL_CHANGED_EVENT_NAME, RECENT_ASSET_THUMBNAIL_CHANGED_EVENT_NAME);
    }

    public void receiveCommand(MediaPickerView view, int commandType, al args) {
        switch (commandType) {
            case 1:
                view.t();
                return;
            case 2:
                view.e().e(0);
                return;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }

    public static void sendEvent(ai context, String eventName, MediaPickerView view, ar event) {
        ((RCTEventEmitter) context.a(RCTEventEmitter.class)).receiveEvent(view.getId(), eventName, event);
    }
}
