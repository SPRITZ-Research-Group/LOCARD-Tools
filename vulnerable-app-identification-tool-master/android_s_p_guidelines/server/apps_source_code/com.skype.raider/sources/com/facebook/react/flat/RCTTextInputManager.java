package com.facebook.react.flat;

import com.facebook.react.views.textinput.ReactTextInputManager;

public class RCTTextInputManager extends ReactTextInputManager {
    static final String REACT_CLASS = "AndroidTextInput";

    public ad createShadowNodeInstance() {
        return new ad();
    }

    public Class<ad> getShadowNodeClass() {
        return ad.class;
    }
}
