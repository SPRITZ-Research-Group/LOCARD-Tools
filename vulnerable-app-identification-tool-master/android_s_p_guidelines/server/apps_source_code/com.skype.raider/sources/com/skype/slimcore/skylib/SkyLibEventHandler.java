package com.skype.slimcore.skylib;

import com.microsoft.skype.b.a;
import com.skype.CallHandler.MEDIA_DIRECTION;
import com.skype.CallHandler.MEDIA_STREAM_STATE;
import com.skype.PROPKEY;
import com.skype.SkyLib.MEDIASTATUS;
import com.skype.SkyLib.OBJECTTYPE;
import com.skype.SkyLib.PUSHHANDLINGRESULT;
import com.skype.SkyLib.QUALITY_MEDIATYPE;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;

public interface SkyLibEventHandler {
    void a(int i, int i2, String str, String str2, String str3);

    void a(int i, MEDIA_DIRECTION media_direction, MEDIA_STREAM_STATE media_stream_state, String str);

    void a(int i, PUSHHANDLINGRESULT pushhandlingresult);

    void a(int i, QualityEventType qualityEventType, QualityLevel qualityLevel, QUALITY_MEDIATYPE quality_mediatype, String str);

    void a(int i, String str, int i2, String str2);

    void a(int i, String[] strArr, String str);

    void a(MEDIASTATUS mediastatus);

    void a(OBJECTTYPE objecttype, PROPKEY propkey, int i, a<String, Integer> aVar, String str);

    void a(String str);

    void a(String str, String str2);

    void b(int i, String[] strArr, String str);
}
