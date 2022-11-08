package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

public abstract class Visibility {
    private final boolean isPublicAPI;
    private final String name;

    public abstract boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor);

    public Visibility normalize() {
        return this;
    }

    protected Visibility(String str, boolean z) {
        this.name = str;
        this.isPublicAPI = z;
    }

    public final boolean isPublicAPI() {
        return this.isPublicAPI;
    }

    protected Integer compareTo(Visibility visibility) {
        return Visibilities.compareLocal(this, visibility);
    }

    public String getDisplayName() {
        return this.name;
    }

    public final String toString() {
        return getDisplayName();
    }
}
