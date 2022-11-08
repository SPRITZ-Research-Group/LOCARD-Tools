package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;

public final class ProtoBufUtilKt {
    public static final <M extends ExtendableMessage<M>, T> T getExtensionOrNull(ExtendableMessage<M> extendableMessage, GeneratedExtension<M, T> generatedExtension) {
        return extendableMessage.hasExtension(generatedExtension) ? extendableMessage.getExtension(generatedExtension) : null;
    }

    public static final <M extends ExtendableMessage<M>, T> T getExtensionOrNull(ExtendableMessage<M> extendableMessage, GeneratedExtension<M, List<T>> generatedExtension, int i) {
        return i < extendableMessage.getExtensionCount(generatedExtension) ? extendableMessage.getExtension(generatedExtension, i) : null;
    }
}
