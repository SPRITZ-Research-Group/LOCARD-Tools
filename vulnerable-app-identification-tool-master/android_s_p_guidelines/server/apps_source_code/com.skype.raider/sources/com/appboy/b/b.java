package com.appboy.b;

import com.skype.Defines;

public enum b {
    NOTIFICATION_EXPANDED_IMAGE(478, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE),
    NOTIFICATION_LARGE_ICON(64, 64),
    NOTIFICATION_ONE_IMAGE_STORY(Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 128),
    BASE_CARD_VIEW(512, 512),
    IN_APP_MESSAGE_MODAL(580, 580),
    IN_APP_MESSAGE_SLIDEUP(100, 100),
    NO_BOUNDS(0, 0);
    
    final int h;
    final int i;

    private b(int width, int height) {
        this.h = width;
        this.i = height;
    }

    public final int a() {
        return this.i;
    }

    public final int b() {
        return this.h;
    }
}
