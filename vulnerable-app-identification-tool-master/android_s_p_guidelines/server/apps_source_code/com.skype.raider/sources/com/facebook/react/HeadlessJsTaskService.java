package com.facebook.react;

import android.app.Service;
import com.facebook.react.b.b;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class HeadlessJsTaskService extends Service implements b {
    private final Set<Integer> a = new CopyOnWriteArraySet();
}
