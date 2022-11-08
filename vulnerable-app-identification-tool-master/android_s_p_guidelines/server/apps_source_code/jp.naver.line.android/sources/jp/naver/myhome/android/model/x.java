package jp.naver.myhome.android.model;

import android.text.TextUtils;
import defpackage.qcc;

public enum x {
    GROUPHOME,
    GROUPHOME_END,
    GROUPS,
    LINE_MORE,
    LINE_PROFILE,
    LINE_PROFILE_COVER,
    LINE_SHARE,
    MYHOME,
    MYHOME_END,
    MYHOME_LIKE_END,
    NOTICENTER,
    PUSH,
    TALKROOM,
    TALKROOM_HOME,
    TIMELINE,
    TIMELINE_MERGE_END,
    PHOTOVIEWER,
    SHAREDPOSTLIST,
    POSTS_BY_HASHTAG,
    PROFILE_SETTING,
    RELAY_END,
    RELAY_VIEWER,
    SQUARE_POST_LIST,
    SQUARE_ANNOUNCEMENT_LIST,
    POST_END,
    UNDEFINED;

    public static x a(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNDEFINED;
        }
        return (x) qcc.b(x.class, str, UNDEFINED);
    }
}
