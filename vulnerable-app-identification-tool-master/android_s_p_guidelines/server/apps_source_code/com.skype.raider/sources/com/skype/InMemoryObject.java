package com.skype;

import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;

public interface InMemoryObject {

    public interface InMemoryObjectIListener {
        void onDummy(InMemoryObject inMemoryObject);
    }

    void addListener(InMemoryObjectIListener inMemoryObjectIListener);

    int getInMemObjectID();

    IN_MEMORY_OBJECTTYPE getInMemObjectType();

    void removeListener(InMemoryObjectIListener inMemoryObjectIListener);
}
