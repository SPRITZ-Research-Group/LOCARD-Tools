package com.skype.reactnativesprites;

import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.core.g;
import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.k.b.a;
import com.facebook.imagepipeline.k.c;
import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.skype.reactnativesprites.SpriteViewProperties.Builder;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import javax.annotation.Nullable;

public class SpriteViewManager extends SimpleViewManager<SpriteView> implements SpritesConstants {
    public static final String REACT_CLASS = "SpriteView";
    private final HashMap<String, SoftReference<SpriteAnimation>> animationMap = new HashMap();
    private g imagePipeline;
    private Builder propertyBuilder;
    private final SameThreadAssert sameThreadAssert = new SameThreadAssert();

    public SpriteViewManager(g imagePipeline) {
        this.imagePipeline = imagePipeline;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public SpriteView createViewInstance(ae context) {
        return new SpriteView(context);
    }

    @ReactProp(name = "url")
    public void setUrl(SpriteView view, @Nullable String url) {
        getOrCreatePropertyBuilder(view).a(url);
    }

    @ReactProp(name = "staticUrl")
    public void setStaticUrl(SpriteView view, @Nullable String staticUrl) {
        getOrCreatePropertyBuilder(view).b(staticUrl);
    }

    @ReactProp(name = "name")
    public void setName(SpriteView view, @Nullable String name) {
        getOrCreatePropertyBuilder(view).c(name);
    }

    @ReactProp(name = "sourceFramesCount")
    public void setSourceFramesCount(SpriteView view, int framesCount) {
        getOrCreatePropertyBuilder(view).a(framesCount);
    }

    @ReactProp(name = "firstFrame")
    public void setFirstFrame(SpriteView view, int firstFrame) {
        getOrCreatePropertyBuilder(view).b(firstFrame);
    }

    @ReactProp(name = "frameSequence")
    public void setFrameSequence(SpriteView view, al frameSequence) {
        getOrCreatePropertyBuilder(view).a(frameSequence);
    }

    @ReactProp(name = "fps")
    public void setFps(SpriteView view, float fps) {
        getOrCreatePropertyBuilder(view).a(fps);
    }

    @ReactProp(defaultBoolean = false, name = "synchronized")
    public void setSynchronized(SpriteView view, boolean synchronizeAnimation) {
        getOrCreatePropertyBuilder(view).a(synchronizeAnimation);
    }

    public void onDropViewInstance(SpriteView view) {
        if (view.b() != null) {
            view.b().h();
        }
    }

    private Builder getOrCreatePropertyBuilder(SpriteView view) {
        if (this.propertyBuilder == null) {
            this.propertyBuilder = new Builder(view.a(), this.sameThreadAssert);
        }
        return this.propertyBuilder;
    }

    protected void onAfterUpdateTransaction(final SpriteView view) {
        SpriteViewProperties properties = this.propertyBuilder.a();
        this.propertyBuilder = null;
        view.setProperties(properties);
        if (properties.a() == null && properties.b() == null) {
            FLog.w("ReactSprites", "ignoring there is no staticUrl nor animationUrl");
            return;
        }
        String url = properties.a();
        if (url == null) {
            view.a(this.imagePipeline);
            return;
        }
        SpriteAnimation spriteAnimation = null;
        if (this.animationMap.containsKey(url)) {
            spriteAnimation = (SpriteAnimation) ((SoftReference) this.animationMap.get(url)).get();
        }
        if (spriteAnimation == null) {
            spriteAnimation = new SpriteAnimation();
            this.animationMap.put(url, new SoftReference(spriteAnimation));
        }
        view.setSpriteAnimation(spriteAnimation);
        if (!spriteAnimation.a(view)) {
            final b animationImageRequest = c.a(Uri.parse(properties.a())).a(b.b.FULL_FETCH).a(a.SMALL).q();
            final com.facebook.datasource.c<Boolean> hasAnimationInCache = this.imagePipeline.b(animationImageRequest);
            hasAnimationInCache.a(new com.facebook.datasource.b<Boolean>(this) {
                final /* synthetic */ SpriteViewManager d;

                protected final void e(com.facebook.datasource.c<Boolean> dataSource) {
                    if (dataSource.b()) {
                        Boolean result = (Boolean) dataSource.d();
                        if (result == null || !result.booleanValue()) {
                            view.a(this.d.imagePipeline);
                        }
                        view.a(this.d.imagePipeline, animationImageRequest);
                        hasAnimationInCache.h();
                    }
                }

                protected final void f(com.facebook.datasource.c<Boolean> cVar) {
                    view.a(this.d.imagePipeline);
                    hasAnimationInCache.h();
                }
            }, com.facebook.common.b.a.a());
        }
    }
}
