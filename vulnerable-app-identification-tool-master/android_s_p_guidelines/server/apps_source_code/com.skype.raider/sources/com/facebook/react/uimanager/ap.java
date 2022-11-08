package com.facebook.react.uimanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ap {
    private final Map<String, ViewManager> a = new HashMap();

    public ap(List<ViewManager> viewManagerList) {
        for (ViewManager viewManager : viewManagerList) {
            this.a.put(viewManager.getName(), viewManager);
        }
    }

    public final ViewManager a(String className) {
        ViewManager viewManager = (ViewManager) this.a.get(className);
        if (viewManager != null) {
            return viewManager;
        }
        throw new f("No ViewManager defined for class " + className);
    }

    final Set<String> a() {
        return this.a.keySet();
    }
}
