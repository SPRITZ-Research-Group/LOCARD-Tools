package com.skype.android.gen;

import com.skype.SessionParameters.SessionParametersIListener;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;

public class SessionParametersListener implements SessionParametersIListener {
    final EventBus eventBus = EventBusInstance.a();
}
