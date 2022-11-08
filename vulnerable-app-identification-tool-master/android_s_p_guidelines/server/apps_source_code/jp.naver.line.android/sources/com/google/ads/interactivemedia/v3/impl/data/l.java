package com.google.ads.interactivemedia.v3.impl.data;

import android.util.Log;
import com.google.obf.mb;
import com.google.obf.md;
import com.linecorp.linethings.devicemanagement.w;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class l {
    public double adBreakDuration;
    public String adBreakTime;
    public List<Float> adCuePoints;
    public b adData;
    public int adPosition;
    public long adTimeUpdateMs;
    public String adUiStyle;
    public double bufferedTime;
    public String clickThroughUrl;
    public Map<String, CompanionData> companions;
    public List<j> cuepoints;
    public double currentTime;
    public double duration;
    public int errorCode;
    public String errorMessage;
    public String eventId;
    public String innerError;
    public SortedSet<Float> internalCuePoints;
    public String ln;
    public a logData;
    public String m;
    public String minutes;
    public boolean monitorAppLifecycle;
    public String n;
    public String queryId;
    public String seconds;
    public String streamId;
    public String streamUrl;
    public List<HashMap<String, String>> subtitles;
    public int totalAds;
    public String translation;
    public String vastEvent;
    public String videoUrl;

    public class a {
        public int errorCode;
        public String errorMessage;
        public String innerError;
        public String type;

        public Map<String, String> constructMap() {
            Map<String, String> hashMap = new HashMap();
            hashMap.put("type", this.type);
            hashMap.put("errorCode", String.valueOf(this.errorCode));
            hashMap.put(w.DATA_KEY_ERROR_MESSAGE, this.errorMessage);
            if (this.innerError != null) {
                hashMap.put("innerError", this.innerError);
            }
            return hashMap;
        }

        public String toString() {
            return String.format("Log[type=%s, errorCode=%s, errorMessage=%s, innerError=%s]", new Object[]{this.type, Integer.valueOf(this.errorCode), this.errorMessage, this.innerError});
        }
    }

    public boolean equals(Object obj) {
        return mb.a((Object) this, obj, new String[0]);
    }

    public int hashCode() {
        return md.a(this, new String[0]);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JavaScriptMsgData[");
        for (Field field : l.class.getFields()) {
            try {
                Object obj = field.get(this);
                stringBuilder.append(field.getName());
                stringBuilder.append(":");
                stringBuilder.append(obj);
                stringBuilder.append(",");
            } catch (Throwable e) {
                Log.e("IMASDK", "IllegalArgumentException occurred", e);
            } catch (Throwable e2) {
                Log.e("IMASDK", "IllegalAccessException occurred", e2);
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
