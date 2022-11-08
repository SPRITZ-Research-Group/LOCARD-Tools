package com.facebook.react.bridge;

import java.util.ArrayList;

public interface al {
    al getArray(int i);

    boolean getBoolean(int i);

    double getDouble(int i);

    int getInt(int i);

    am getMap(int i);

    String getString(int i);

    ReadableType getType(int i);

    boolean isNull(int i);

    int size();

    ArrayList<Object> toArrayList();
}
