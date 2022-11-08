package com.skype;

import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;

public interface ExampleInMemoryObject {

    public interface ExampleInMemoryObjectIListener {
        void onChildrenChanged(ExampleInMemoryObject exampleInMemoryObject, int[] iArr);

        void onNameChanged(ExampleInMemoryObject exampleInMemoryObject, String str, String str2);
    }

    public static class GetName_Result {
        public String m_name;
        public boolean m_return;

        public void init(byte[] name, boolean funcRet) {
            this.m_name = NativeStringConvert.ConvertFromNativeBytes(name);
            this.m_return = funcRet;
        }
    }

    void addListener(ExampleInMemoryObjectIListener exampleInMemoryObjectIListener);

    IN_MEMORY_OBJECTTYPE getInMemObjectType();

    GetName_Result getName();

    void removeListener(ExampleInMemoryObjectIListener exampleInMemoryObjectIListener);

    void setChildren(int[] iArr);

    void setName(String str);
}
