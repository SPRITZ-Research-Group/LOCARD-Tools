package a.a;

import com.appboy.e.e;
import java.util.HashMap;
import java.util.Map;

public enum fs implements e<String> {
    LOCATION_RECORDED("lr"),
    CUSTOM_EVENT("ce"),
    PURCHASE("p"),
    PUSH_STORY_PAGE_CLICK("cic"),
    PUSH_NOTIFICATION_TRACKING("pc"),
    PUSH_NOTIFICATION_ACTION_TRACKING("ca"),
    INTERNAL("i"),
    INTERNAL_ERROR("ie"),
    CARD_IMPRESSION("ci"),
    CARD_CLICK("cc"),
    GEOFENCE("g"),
    INCREMENT("inc"),
    ADD_TO_CUSTOM_ATTRIBUTE_ARRAY("add"),
    REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY("rem"),
    SET_CUSTOM_ATTRIBUTE_ARRAY("set"),
    INAPP_MESSAGE_IMPRESSION("si"),
    INAPP_MESSAGE_CONTROL_IMPRESSION("iec"),
    INAPP_MESSAGE_CLICK("sc"),
    INAPP_MESSAGE_BUTTON_CLICK("sbc"),
    INAPP_MESSAGE_DISPLAY_FAILURE("sfe"),
    USER_ALIAS("uae"),
    SESSION_START("ss"),
    SESSION_END("se"),
    PUSH_DELIVERY("pd");
    
    private static final Map<String, fs> z = null;
    private final String y;

    static {
        Map hashMap = new HashMap();
        fs[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            fs fsVar = values[i];
            hashMap.put(fsVar.y, fsVar);
            i++;
        }
        z = new HashMap(hashMap);
    }

    private fs(String str) {
        this.y = str;
    }

    public static fs a(String str) {
        if (z.containsKey(str)) {
            return (fs) z.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public static boolean b(fs fsVar) {
        return fsVar.equals(PUSH_NOTIFICATION_TRACKING);
    }

    public final String a() {
        return this.y;
    }

    public static boolean a(fs fsVar) {
        return fsVar.equals(PUSH_NOTIFICATION_TRACKING) || fsVar.equals(PUSH_NOTIFICATION_ACTION_TRACKING) || fsVar.equals(PUSH_STORY_PAGE_CLICK);
    }
}
