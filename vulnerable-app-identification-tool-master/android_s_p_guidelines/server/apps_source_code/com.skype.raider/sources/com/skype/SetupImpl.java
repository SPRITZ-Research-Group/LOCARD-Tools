package com.skype;

public class SetupImpl implements Setup {
    protected int m_nativeObject;

    private native void delete(byte[] bArr);

    private native byte[] getBin(byte[] bArr);

    private native byte[] getBin(byte[] bArr, byte[] bArr2);

    private native int getInt(byte[] bArr, int i);

    private native long getInt64(byte[] bArr, long j);

    private native byte[] getStrNativeString(byte[] bArr);

    private native byte[] getStrNativeString(byte[] bArr, byte[] bArr2);

    private native byte[] getSubKeyNativeString(byte[] bArr, int i);

    private native boolean isDefined(byte[] bArr);

    private native void setBin(byte[] bArr, byte[] bArr2);

    private native void setInt(byte[] bArr, int i);

    private native void setInt64(byte[] bArr, long j);

    private native void setStr(byte[] bArr, byte[] bArr2);

    protected SetupImpl(int nativeObject) {
        this(SkypeFactory.getInstance(), nativeObject);
    }

    protected SetupImpl(ObjectInterfaceFactory factory, int nativeObject) {
        this.m_nativeObject = nativeObject;
    }

    public void delete(String key) {
        delete(NativeStringConvert.ConvertToNativeBytes(key));
    }

    public byte[] getBin(String key) {
        return getBin(NativeStringConvert.ConvertToNativeBytes(key));
    }

    public byte[] getBin(String key, byte[] defaultValue) {
        return getBin(NativeStringConvert.ConvertToNativeBytes(key), defaultValue);
    }

    public long getInt64(String key, long defaultValue) {
        return getInt64(NativeStringConvert.ConvertToNativeBytes(key), defaultValue);
    }

    public long getInt64(String key) {
        return getInt64(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return getInt(NativeStringConvert.ConvertToNativeBytes(key), defaultValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public String getStr(String key) {
        return NativeStringConvert.ConvertFromNativeBytes(getStrNativeString(NativeStringConvert.ConvertToNativeBytes(key)));
    }

    public String getStr(String key, String defaultValue) {
        return NativeStringConvert.ConvertFromNativeBytes(getStrNativeString(NativeStringConvert.ConvertToNativeBytes(key), NativeStringConvert.ConvertToNativeBytes(defaultValue)));
    }

    public String getSubKey(String key, int index) {
        return NativeStringConvert.ConvertFromNativeBytes(getSubKeyNativeString(NativeStringConvert.ConvertToNativeBytes(key), index));
    }

    public boolean isDefined(String key) {
        return isDefined(NativeStringConvert.ConvertToNativeBytes(key));
    }

    public void setBin(String key, byte[] value) {
        setBin(NativeStringConvert.ConvertToNativeBytes(key), value);
    }

    public void setInt64(String key, long value) {
        setInt64(NativeStringConvert.ConvertToNativeBytes(key), value);
    }

    public void setInt(String key, int value) {
        setInt(NativeStringConvert.ConvertToNativeBytes(key), value);
    }

    public void setStr(String key, String value) {
        setStr(NativeStringConvert.ConvertToNativeBytes(key), NativeStringConvert.ConvertToNativeBytes(value));
    }
}
