package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.al;
import com.facebook.react.d.a;
import com.facebook.react.d.d;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import java.util.Map;
import javax.annotation.Nullable;

@ReactPropertyHolder
public abstract class ViewManager<T extends View, C extends w> extends BaseJavaModule {
    public abstract C createShadowNodeInstance();

    protected abstract T createViewInstance(ae aeVar);

    public abstract String getName();

    public abstract Class<? extends C> getShadowNodeClass();

    public abstract void updateExtraData(T t, Object obj);

    public final void updateProperties(T viewToUpdate, x props) {
        ao.a(this, viewToUpdate, props);
        onAfterUpdateTransaction(viewToUpdate);
    }

    public final T createView(ae reactContext, a jsResponderHandler) {
        T view = createViewInstance(reactContext);
        addEventEmitters(reactContext, view);
        if (view instanceof d) {
            ((d) view).setOnInterceptTouchEventListener(jsResponderHandler);
        }
        return view;
    }

    public void onDropViewInstance(T t) {
    }

    protected void addEventEmitters(ae reactContext, T t) {
    }

    protected void onAfterUpdateTransaction(T t) {
    }

    public void receiveCommand(T t, int commandId, @Nullable al args) {
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    public Map<String, String> getNativeProps() {
        return ao.a(getClass(), getShadowNodeClass());
    }

    protected void onHostDestroy() {
    }
}
