package com.skype;

public interface ObjectInterface {

    public static class GetFilenameProperty_Result {
        public boolean m_return;
        public String m_value;

        public void init(byte[] value, boolean funcRet) {
            this.m_value = NativeStringConvert.ConvertFromNativeBytes(value);
            this.m_return = funcRet;
        }
    }

    public interface ObjectInterfaceIListener {
        void onPropertyChange(ObjectInterface objectInterface, PROPKEY propkey);
    }

    void addListener(ObjectInterfaceIListener objectInterfaceIListener);

    byte[] getBinProperty(PROPKEY propkey);

    GetFilenameProperty_Result getFilenameProperty(PROPKEY propkey);

    long getInt64Property(PROPKEY propkey);

    long getInt64Property(PROPKEY propkey, int i);

    int getIntProperty(PROPKEY propkey);

    int getIntProperty(PROPKEY propkey, int i);

    int getObjectID();

    boolean getProperty(PROPKEY propkey, Metatag metatag);

    boolean getPropertyOrDefault(PROPKEY propkey, Metatag metatag);

    String getStrProperty(PROPKEY propkey);

    String getStrProperty(PROPKEY propkey, String str);

    String getStrPropertyWithXmlStripped(PROPKEY propkey, String str);

    void removeListener(ObjectInterfaceIListener objectInterfaceIListener);

    boolean setExtendedProperty(Metatag metatag);

    boolean setExtendedProperty(PROPKEY propkey, int i);

    boolean setExtendedProperty(PROPKEY propkey, String str);

    void unlink();
}
