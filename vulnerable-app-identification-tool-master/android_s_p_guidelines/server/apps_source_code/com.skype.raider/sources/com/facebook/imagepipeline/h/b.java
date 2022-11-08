package com.facebook.imagepipeline.h;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public final class b implements c {
    private final List<c> a;

    public b(Set<c> requestListeners) {
        this.a = new ArrayList(requestListeners.size());
        for (c requestListener : requestListeners) {
            if (requestListener != null) {
                this.a.add(requestListener);
            }
        }
    }

    public b(c... requestListeners) {
        this.a = new ArrayList(requestListeners.length);
        for (c requestListener : requestListeners) {
            if (requestListener != null) {
                this.a.add(requestListener);
            }
        }
    }

    public final void a(c requestListener) {
        this.a.add(requestListener);
    }

    public final void a(com.facebook.imagepipeline.k.b request, Object callerContext, String requestId, boolean isPrefetch) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(request, callerContext, requestId, isPrefetch);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onRequestStart", exception);
            }
        }
    }

    public final void a(String requestId, String producerName) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(requestId, producerName);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onProducerStart", exception);
            }
        }
    }

    public final void a(String requestId, String producerName, @Nullable Map<String, String> extraMap) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(requestId, producerName, (Map) extraMap);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithSuccess", exception);
            }
        }
    }

    public final void a(String requestId, String producerName, Throwable t, @Nullable Map<String, String> extraMap) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(requestId, producerName, t, extraMap);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithFailure", exception);
            }
        }
    }

    public final void b(String requestId, String producerName, @Nullable Map<String, String> extraMap) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).b(requestId, producerName, extraMap);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithCancellation", exception);
            }
        }
    }

    public final void a(String requestId, String producerName, String producerEventName) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(requestId, producerName, producerEventName);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onIntermediateChunkStart", exception);
            }
        }
    }

    public final void a(String requestId, String producerName, boolean successful) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(requestId, producerName, successful);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithSuccess", exception);
            }
        }
    }

    public final void a(com.facebook.imagepipeline.k.b request, String requestId, boolean isPrefetch) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(request, requestId, isPrefetch);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onRequestSuccess", exception);
            }
        }
    }

    public final void a(com.facebook.imagepipeline.k.b request, String requestId, Throwable throwable, boolean isPrefetch) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a(request, requestId, throwable, isPrefetch);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onRequestFailure", exception);
            }
        }
    }

    public final void a_(String requestId) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((c) this.a.get(i)).a_(requestId);
            } catch (Throwable exception) {
                FLog.e("ForwardingRequestListener", "InternalListener exception in onRequestCancellation", exception);
            }
        }
    }

    public final boolean b(String id) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            if (((c) this.a.get(i)).b(id)) {
                return true;
            }
        }
        return false;
    }
}
