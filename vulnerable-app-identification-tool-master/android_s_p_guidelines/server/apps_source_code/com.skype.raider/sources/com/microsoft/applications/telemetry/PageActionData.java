package com.microsoft.applications.telemetry;

public class PageActionData {
    public ActionType actionType;
    public String destinationUri = "";
    public InputDeviceType inputDeviceType;
    public final String pageViewId;
    public RawActionType rawActionType;
    public String targetItemCategory = "";
    public String targetItemCollection = "";
    public String targetItemId = "";
    public String targetItemLayoutContainer = "";
    public String targetItemName = "";
    public int targetItemRank = 0;

    public PageActionData(String pageViewId) {
        this.pageViewId = pageViewId;
    }

    public PageActionData(String pageViewId, ActionType actionType) {
        this.pageViewId = pageViewId;
        this.actionType = actionType;
    }

    public String toString() {
        return String.format("{pageViewId: %s|actionType: %s|rawActionType: %s|inputDeviceType: %s|destinationUri: %s|targetItemId: %s|targetItemName: %s|targetItemCategory: %s|targetItemCollection: %s|targetItemLayoutContainer: %s|targetItemRank: %d}", new Object[]{this.pageViewId, Integer.valueOf(this.actionType.getValue()), Integer.valueOf(this.rawActionType.getValue()), Integer.valueOf(this.inputDeviceType.getValue()), this.destinationUri, this.targetItemId, this.targetItemName, this.targetItemCategory, this.targetItemCollection, this.targetItemLayoutContainer, Integer.valueOf(this.targetItemRank)});
    }
}
