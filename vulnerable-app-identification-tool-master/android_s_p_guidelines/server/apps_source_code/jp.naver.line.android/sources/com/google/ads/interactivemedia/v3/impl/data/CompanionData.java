package com.google.ads.interactivemedia.v3.impl.data;

import com.google.obf.km;

@km(a = f.class)
public abstract class CompanionData {
    private String companionId;

    public enum a {
        Html,
        Static,
        IFrame
    }

    public abstract String clickThroughUrl();

    public abstract String size();

    public abstract String src();

    public abstract a type();

    public static CompanionData create(String str, String str2, String str3, String str4, a aVar) {
        CompanionData create = create(str2, str3, str4, aVar);
        create.companionId = str;
        return create;
    }

    private static CompanionData create(String str, String str2, String str3, a aVar) {
        return new f(str, str2, str3, aVar);
    }

    public String companionId() {
        return this.companionId;
    }

    public String toString() {
        String companionId = companionId();
        String size = size();
        String src = src();
        String clickThroughUrl = clickThroughUrl();
        String valueOf = String.valueOf(type());
        StringBuilder stringBuilder = new StringBuilder(((((String.valueOf(companionId).length() + 66) + String.valueOf(size).length()) + String.valueOf(src).length()) + String.valueOf(clickThroughUrl).length()) + String.valueOf(valueOf).length());
        stringBuilder.append("CompanionData [companionId=");
        stringBuilder.append(companionId);
        stringBuilder.append(", size=");
        stringBuilder.append(size);
        stringBuilder.append(", src=");
        stringBuilder.append(src);
        stringBuilder.append(", clickThroughUrl=");
        stringBuilder.append(clickThroughUrl);
        stringBuilder.append(", type=");
        stringBuilder.append(valueOf);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
