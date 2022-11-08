package com.appboy.b.a;

public enum e implements com.appboy.e.e<String> {
    IMAGE_DOWNLOAD,
    TEMPLATE_REQUEST,
    ZIP_ASSET_DOWNLOAD,
    DISPLAY_VIEW_GENERATION;

    public final String a() {
        switch (this) {
            case IMAGE_DOWNLOAD:
                return "if";
            case TEMPLATE_REQUEST:
                return "tf";
            case ZIP_ASSET_DOWNLOAD:
                return "zf";
            case DISPLAY_VIEW_GENERATION:
                return "vf";
            default:
                return null;
        }
    }
}
