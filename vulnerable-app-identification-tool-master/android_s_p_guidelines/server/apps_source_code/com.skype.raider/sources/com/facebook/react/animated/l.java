package com.facebook.react.animated;

import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ai;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;
import com.facebook.react.uimanager.events.d;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import javax.annotation.Nullable;

final class l implements d {
    private final SparseArray<b> a = new SparseArray();
    private final SparseArray<d> b = new SparseArray();
    private final SparseArray<b> c = new SparseArray();
    private final Map<String, List<EventAnimationDriver>> d = new HashMap();
    private final Map<String, Map<String, String>> e;
    private final ai f;
    private int g = 0;
    private final List<b> h = new LinkedList();

    public l(UIManagerModule uiManager) {
        this.f = uiManager.getUIImplementation();
        uiManager.getEventDispatcher().a((d) this);
        this.e = (Map) ((Map) a.a(uiManager.getConstants())).get("customDirectEventTypes");
    }

    @Nullable
    final b a(int id) {
        return (b) this.a.get(id);
    }

    public final boolean a() {
        return this.b.size() > 0 || this.c.size() > 0;
    }

    public final void a(int tag, am config) {
        if (this.a.get(tag) != null) {
            throw new n("Animated node with tag " + tag + " already exists");
        }
        b node;
        String type = config.getString("type");
        if ("style".equals(type)) {
            node = new o(config, this);
        } else if (PropertiesEntry.COLUMN_NAME_VALUE.equals(type)) {
            node = new q(config);
        } else if ("props".equals(type)) {
            node = new m(config, this);
        } else if ("interpolation".equals(type)) {
            node = new i(config);
        } else if ("addition".equals(type)) {
            node = new a(config, this);
        } else if ("division".equals(type)) {
            node = new g(config, this);
        } else if ("multiplication".equals(type)) {
            node = new k(config, this);
        } else if ("modulus".equals(type)) {
            node = new j(config, this);
        } else if ("diffclamp".equals(type)) {
            node = new f(config, this);
        } else if ("transform".equals(type)) {
            node = new p(config, this);
        } else {
            throw new n("Unsupported node type: " + type);
        }
        node.d = tag;
        this.a.put(tag, node);
        this.c.put(tag, node);
    }

    public final void b(int tag) {
        this.a.remove(tag);
        this.c.remove(tag);
    }

    public final void a(int tag, c listener) {
        b node = (b) this.a.get(tag);
        if (node == null || !(node instanceof q)) {
            throw new n("Animated node with tag " + tag + " does not exists or is not a 'value' node");
        }
        ((q) node).a(listener);
    }

    public final void c(int tag) {
        b node = (b) this.a.get(tag);
        if (node == null || !(node instanceof q)) {
            throw new n("Animated node with tag " + tag + " does not exists or is not a 'value' node");
        }
        ((q) node).a(null);
    }

    public final void a(int tag, double value) {
        b node = (b) this.a.get(tag);
        if (node == null || !(node instanceof q)) {
            throw new n("Animated node with tag " + tag + " does not exists or is not a 'value' node");
        }
        ((q) node).e = value;
        this.c.put(tag, node);
    }

    public final void b(int tag, double offset) {
        b node = (b) this.a.get(tag);
        if (node == null || !(node instanceof q)) {
            throw new n("Animated node with tag " + tag + " does not exists or is not a 'value' node");
        }
        ((q) node).f = offset;
        this.c.put(tag, node);
    }

    public final void d(int tag) {
        b node = (b) this.a.get(tag);
        if (node == null || !(node instanceof q)) {
            throw new n("Animated node with tag " + tag + " does not exists or is not a 'value' node");
        }
        q qVar = (q) node;
        qVar.e += qVar.f;
        qVar.f = 0.0d;
    }

    public final void e(int tag) {
        b node = (b) this.a.get(tag);
        if (node == null || !(node instanceof q)) {
            throw new n("Animated node with tag " + tag + " does not exists or is not a 'value' node");
        }
        q qVar = (q) node;
        qVar.f += qVar.e;
        qVar.e = 0.0d;
    }

    public final void a(int animationId, int animatedNodeTag, am animationConfig, com.facebook.react.bridge.d endCallback) {
        b node = (b) this.a.get(animatedNodeTag);
        if (node == null) {
            throw new n("Animated node with tag " + animatedNodeTag + " does not exists");
        } else if (node instanceof q) {
            d animation;
            String type = animationConfig.getString("type");
            if ("frames".equals(type)) {
                animation = new h(animationConfig);
            } else if ("spring".equals(type)) {
                animation = new n(animationConfig);
            } else if ("decay".equals(type)) {
                animation = new e(animationConfig);
            } else {
                throw new n("Unsupported animation type: " + type);
            }
            animation.d = animationId;
            animation.c = endCallback;
            animation.b = (q) node;
            this.b.put(animationId, animation);
        } else {
            throw new n("Animated node should be of type " + q.class.getName());
        }
    }

    public final void f(int animationId) {
        for (int i = 0; i < this.b.size(); i++) {
            d animation = (d) this.b.valueAt(i);
            if (animation.d == animationId) {
                new WritableNativeMap().putBoolean("finished", false);
                animation.c.invoke(endCallbackResponse);
                this.b.removeAt(i);
                return;
            }
        }
    }

    public final void a(int parentNodeTag, int childNodeTag) {
        b parentNode = (b) this.a.get(parentNodeTag);
        if (parentNode == null) {
            throw new n("Animated node with tag " + parentNodeTag + " does not exists");
        }
        b childNode = (b) this.a.get(childNodeTag);
        if (childNode == null) {
            throw new n("Animated node with tag " + childNodeTag + " does not exists");
        }
        if (parentNode.a == null) {
            parentNode.a = new ArrayList(1);
        }
        ((List) a.a(parentNode.a)).add(childNode);
        childNode.a(parentNode);
        this.c.put(childNodeTag, childNode);
    }

    public final void b(int parentNodeTag, int childNodeTag) {
        b parentNode = (b) this.a.get(parentNodeTag);
        if (parentNode == null) {
            throw new n("Animated node with tag " + parentNodeTag + " does not exists");
        }
        b childNode = (b) this.a.get(childNodeTag);
        if (childNode == null) {
            throw new n("Animated node with tag " + childNodeTag + " does not exists");
        }
        if (parentNode.a != null) {
            childNode.b(parentNode);
            parentNode.a.remove(childNode);
        }
        this.c.put(childNodeTag, childNode);
    }

    public final void c(int animatedNodeTag, int viewTag) {
        b node = (b) this.a.get(animatedNodeTag);
        if (node == null) {
            throw new n("Animated node with tag " + animatedNodeTag + " does not exists");
        } else if (node instanceof m) {
            m propsAnimatedNode = (m) node;
            if (propsAnimatedNode.e != -1) {
                throw new n("Animated node " + animatedNodeTag + " is already attached to a view");
            }
            propsAnimatedNode.e = viewTag;
            this.c.put(animatedNodeTag, node);
        } else {
            throw new n("Animated node connected to view should beof type " + m.class.getName());
        }
    }

    public final void d(int animatedNodeTag, int viewTag) {
        b node = (b) this.a.get(animatedNodeTag);
        if (node == null) {
            throw new n("Animated node with tag " + animatedNodeTag + " does not exists");
        } else if (node instanceof m) {
            m propsAnimatedNode = (m) node;
            if (propsAnimatedNode.e != viewTag) {
                throw new n("Attempting to disconnect view that has not been connected with the given animated node");
            }
            propsAnimatedNode.e = -1;
        } else {
            throw new n("Animated node connected to view should beof type " + m.class.getName());
        }
    }

    public final void a(int viewTag, String eventName, am eventMapping) {
        int nodeTag = eventMapping.getInt("animatedValueTag");
        b node = (b) this.a.get(nodeTag);
        if (node == null) {
            throw new n("Animated node with tag " + nodeTag + " does not exists");
        } else if (node instanceof q) {
            al path = eventMapping.getArray("nativeEventPath");
            List<String> pathList = new ArrayList(path.size());
            for (int i = 0; i < path.size(); i++) {
                pathList.add(path.getString(i));
            }
            EventAnimationDriver event = new EventAnimationDriver(pathList, (q) node);
            String key = viewTag + eventName;
            if (this.d.containsKey(key)) {
                ((List) this.d.get(key)).add(event);
                return;
            }
            List<EventAnimationDriver> drivers = new ArrayList(1);
            drivers.add(event);
            this.d.put(key, drivers);
        } else {
            throw new n("Animated node connected to event should beof type " + q.class.getName());
        }
    }

    public final void a(int viewTag, String eventName, int animatedValueTag) {
        String key = viewTag + eventName;
        if (this.d.containsKey(key)) {
            List<EventAnimationDriver> driversForKey = (List) this.d.get(key);
            if (driversForKey.size() == 1) {
                this.d.remove(viewTag + eventName);
                return;
            }
            ListIterator<EventAnimationDriver> it = driversForKey.listIterator();
            while (it.hasNext()) {
                if (((EventAnimationDriver) it.next()).mValueNode.d == animatedValueTag) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public final void a(b event) {
        if (ap.a() && !this.d.isEmpty()) {
            String eventName = event.a();
            Map<String, String> customEventType = (Map) this.e.get(eventName);
            if (customEventType != null) {
                eventName = (String) customEventType.get("registrationName");
            }
            List<EventAnimationDriver> driversForKey = (List) this.d.get(event.d() + eventName);
            if (driversForKey != null) {
                for (RCTEventEmitter driver : driversForKey) {
                    event.a(driver);
                    this.h.add(driver.mValueNode);
                }
                a(this.h);
                this.h.clear();
            }
        }
    }

    public final void a(long frameTimeNanos) {
        int i;
        d animation;
        ap.b();
        boolean hasFinishedAnimations = false;
        for (i = 0; i < this.c.size(); i++) {
            this.h.add((b) this.c.valueAt(i));
        }
        this.c.clear();
        for (i = 0; i < this.b.size(); i++) {
            animation = (d) this.b.valueAt(i);
            animation.a(frameTimeNanos);
            this.h.add(animation.b);
            if (animation.a) {
                hasFinishedAnimations = true;
            }
        }
        a(this.h);
        this.h.clear();
        if (hasFinishedAnimations) {
            for (i = this.b.size() - 1; i >= 0; i--) {
                animation = (d) this.b.valueAt(i);
                if (animation.a) {
                    new WritableNativeMap().putBoolean("finished", true);
                    animation.c.invoke(endCallbackResponse);
                    this.b.removeAt(i);
                }
            }
        }
    }

    private void a(List<b> nodes) {
        b nextNode;
        int i;
        b child;
        int activeNodesCount = 0;
        int updatedNodesCount = 0;
        this.g++;
        if (this.g == 0) {
            this.g++;
        }
        Queue<b> nodesQueue = new ArrayDeque();
        for (b node : nodes) {
            if (node.c != this.g) {
                node.c = this.g;
                activeNodesCount++;
                nodesQueue.add(node);
            }
        }
        while (!nodesQueue.isEmpty()) {
            nextNode = (b) nodesQueue.poll();
            if (nextNode.a != null) {
                for (i = 0; i < nextNode.a.size(); i++) {
                    child = (b) nextNode.a.get(i);
                    child.b++;
                    if (child.c != this.g) {
                        child.c = this.g;
                        activeNodesCount++;
                        nodesQueue.add(child);
                    }
                }
            }
        }
        this.g++;
        if (this.g == 0) {
            this.g++;
        }
        for (b node2 : nodes) {
            if (node2.b == 0 && node2.c != this.g) {
                node2.c = this.g;
                updatedNodesCount++;
                nodesQueue.add(node2);
            }
        }
        while (!nodesQueue.isEmpty()) {
            nextNode = (b) nodesQueue.poll();
            nextNode.a();
            if (nextNode instanceof m) {
                try {
                    ((m) nextNode).a(this.f);
                } catch (Throwable e) {
                    FLog.e("React", "Native animation workaround, frame lost as result of race condition", e);
                }
            }
            if (nextNode instanceof q) {
                ((q) nextNode).c();
            }
            if (nextNode.a != null) {
                for (i = 0; i < nextNode.a.size(); i++) {
                    child = (b) nextNode.a.get(i);
                    child.b--;
                    if (child.c != this.g && child.b == 0) {
                        child.c = this.g;
                        updatedNodesCount++;
                        nodesQueue.add(child);
                    }
                }
            }
        }
        if (activeNodesCount != updatedNodesCount) {
            throw new IllegalStateException("Looks like animated nodes graph has cycles, there are " + activeNodesCount + " but toposort visited only " + updatedNodesCount);
        }
    }
}
