package com.skype.smsmanager.mms.pdu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncodedStringValue implements Cloneable {
    private int a;
    private byte[] b;

    public EncodedStringValue(int charset, byte[] data) {
        if (data == null) {
            throw new NullPointerException("EncodedStringValue: Text-string is null.");
        }
        this.a = charset;
        this.b = new byte[data.length];
        System.arraycopy(data, 0, this.b, 0, data.length);
    }

    public EncodedStringValue(byte[] data) {
        this(106, data);
    }

    public EncodedStringValue(String data) {
        try {
            this.b = data.getBytes("utf-8");
            this.a = 106;
        } catch (UnsupportedEncodingException e) {
        }
    }

    public final int a() {
        return this.a;
    }

    public final byte[] b() {
        byte[] byteArray = new byte[this.b.length];
        System.arraycopy(this.b, 0, byteArray, 0, this.b.length);
        return byteArray;
    }

    public final void a(byte[] textString) {
        if (textString == null) {
            throw new NullPointerException("EncodedStringValue: Text-string is null.");
        }
        this.b = new byte[textString.length];
        System.arraycopy(textString, 0, this.b, 0, textString.length);
    }

    public final String c() {
        if (this.a == 0) {
            return new String(this.b);
        }
        try {
            return new String(this.b, CharacterSets.a(this.a));
        } catch (UnsupportedEncodingException e) {
            try {
                return new String(this.b, "iso-8859-1");
            } catch (UnsupportedEncodingException e2) {
                return new String(this.b);
            }
        }
    }

    public final void b(byte[] textString) {
        if (textString == null) {
            throw new NullPointerException("Text-string is null.");
        } else if (this.b == null) {
            this.b = new byte[textString.length];
            System.arraycopy(textString, 0, this.b, 0, textString.length);
        } else {
            ByteArrayOutputStream newTextString = new ByteArrayOutputStream();
            try {
                newTextString.write(this.b);
                newTextString.write(textString);
                this.b = newTextString.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                throw new NullPointerException("appendTextString: failed when write a new Text-string");
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        super.clone();
        int len = this.b.length;
        byte[] dstBytes = new byte[len];
        System.arraycopy(this.b, 0, dstBytes, 0, len);
        try {
            return new EncodedStringValue(this.a, dstBytes);
        } catch (Exception e) {
            new StringBuilder("failed to clone an EncodedStringValue: ").append(this);
            e.printStackTrace();
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    public static EncodedStringValue a(EncodedStringValue value) {
        if (value == null) {
            return null;
        }
        return new EncodedStringValue(value.a, value.b);
    }

    public static EncodedStringValue[] a(String[] array) {
        int count = array.length;
        if (count <= 0) {
            return null;
        }
        EncodedStringValue[] encodedStringValueArr = new EncodedStringValue[count];
        for (int i = 0; i < count; i++) {
            encodedStringValueArr[i] = new EncodedStringValue(array[i]);
        }
        return encodedStringValueArr;
    }
}
