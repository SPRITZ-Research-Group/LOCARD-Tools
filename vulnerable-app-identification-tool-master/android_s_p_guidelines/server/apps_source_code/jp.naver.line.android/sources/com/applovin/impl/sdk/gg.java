package com.applovin.impl.sdk;

import android.util.Xml;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class gg {
    private final AppLovinLogger a;
    private Stack<gi> b;
    private StringBuilder c;
    private long d;
    private gi e;

    gg(AppLovinSdk appLovinSdk) {
        if (appLovinSdk != null) {
            this.a = appLovinSdk.getLogger();
            return;
        }
        throw new IllegalArgumentException("No sdk specified.");
    }

    static gf a(String str, AppLovinSdk appLovinSdk) throws SAXException {
        return new gg(appLovinSdk).a(str);
    }

    private Map<String, String> a(Attributes attributes) {
        if (attributes == null) {
            return Collections.emptyMap();
        }
        int length = attributes.getLength();
        Map<String, String> hashMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            hashMap.put(attributes.getQName(i), attributes.getValue(i));
        }
        return hashMap;
    }

    public gf a(String str) throws SAXException {
        if (str != null) {
            this.c = new StringBuilder();
            this.b = new Stack();
            this.e = null;
            Xml.parse(str, new gh(this));
            if (this.e != null) {
                return this.e;
            }
            throw new SAXException("Unable to parse XML into node");
        }
        throw new IllegalArgumentException("Unable to parse. No XML specified.");
    }
}
