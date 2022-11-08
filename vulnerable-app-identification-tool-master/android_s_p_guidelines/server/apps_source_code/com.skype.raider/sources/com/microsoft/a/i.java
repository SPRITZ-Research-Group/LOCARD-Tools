package com.microsoft.a;

public enum i {
    Optional(0),
    Required(1),
    RequiredOptional(2),
    __INVALID_ENUM_VALUE(3);
    
    private final int e;

    private i(int value) {
        this.e = value;
    }

    public final int a() {
        return this.e;
    }

    public static i a(int value) {
        switch (value) {
            case 0:
                return Optional;
            case 1:
                return Required;
            case 2:
                return RequiredOptional;
            default:
                return __INVALID_ENUM_VALUE;
        }
    }
}
