package com.skype;

public interface Setup {
    void delete(String str);

    byte[] getBin(String str);

    byte[] getBin(String str, byte[] bArr);

    int getInt(String str);

    int getInt(String str, int i);

    long getInt64(String str);

    long getInt64(String str, long j);

    String getStr(String str);

    String getStr(String str, String str2);

    String getSubKey(String str, int i);

    boolean isDefined(String str);

    void setBin(String str, byte[] bArr);

    void setInt(String str, int i);

    void setInt64(String str, long j);

    void setStr(String str, String str2);
}
