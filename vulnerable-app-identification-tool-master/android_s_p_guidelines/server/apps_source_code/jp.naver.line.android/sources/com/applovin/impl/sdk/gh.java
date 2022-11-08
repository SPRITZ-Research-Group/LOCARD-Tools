package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class gh implements ContentHandler {
    final /* synthetic */ gg a;

    gh(gg ggVar) {
        this.a = ggVar;
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        String trim = new String(Arrays.copyOfRange(cArr, 0, i2)).trim();
        if (AppLovinSdkUtils.isValidString(trim)) {
            this.a.c.append(trim);
        }
    }

    public void endDocument() throws SAXException {
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - this.a.d;
        StringBuilder stringBuilder = new StringBuilder("Finished parsing in ");
        stringBuilder.append(toSeconds);
        stringBuilder.append(" seconds");
        this.a.a.d("XmlParser", stringBuilder.toString());
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        this.a.e = (gi) this.a.b.pop();
        this.a.e.d(this.a.c.toString().trim());
        this.a.c.setLength(0);
    }

    public void endPrefixMapping(String str) throws SAXException {
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public void processingInstruction(String str, String str2) throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String str) throws SAXException {
    }

    public void startDocument() throws SAXException {
        this.a.a.d("XmlParser", "Begin parsing...");
        this.a.d = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        gf gfVar = null;
        try {
            if (!this.a.b.isEmpty()) {
                gfVar = (gi) this.a.b.peek();
            }
            gf giVar = new gi(str2, this.a.a(attributes), gfVar);
            if (gfVar != null) {
                gfVar.a(giVar);
            }
            this.a.b.push(giVar);
        } catch (Throwable e) {
            AppLovinLogger a = this.a.a;
            StringBuilder stringBuilder = new StringBuilder("Unable to process element <");
            stringBuilder.append(str2);
            stringBuilder.append(">");
            a.e("XmlParser", stringBuilder.toString(), e);
            throw new SAXException("Failed to start element", e);
        }
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
    }
}
