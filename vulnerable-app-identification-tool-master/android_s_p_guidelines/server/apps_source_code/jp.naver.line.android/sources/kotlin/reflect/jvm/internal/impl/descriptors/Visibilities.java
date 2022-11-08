package kotlin.reflect.jvm.internal.impl.descriptors;

import com.google.android.gms.common.internal.ImagesContract;
import defpackage.acot;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.SuperCallReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper.EMPTY;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.apache.cordova.networkinformation.NetworkManager;

public class Visibilities {
    public static final ReceiverValue ALWAYS_SUITABLE_RECEIVER = new ReceiverValue() {
        public final KotlinType getType() {
            throw new IllegalStateException("This method should not be called");
        }
    };
    public static final Visibility DEFAULT_VISIBILITY = PUBLIC;
    @Deprecated
    public static final ReceiverValue FALSE_IF_PROTECTED = new ReceiverValue() {
        public final KotlinType getType() {
            throw new IllegalStateException("This method should not be called");
        }
    };
    public static final Visibility INHERITED = new Visibility("inherited", false) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            throw new IllegalStateException("Visibility is unknown yet");
        }
    };
    public static final Visibility INTERNAL = new Visibility("internal", false) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            if (DescriptorUtils.getContainingModule(declarationDescriptor).shouldSeeInternalsOf(DescriptorUtils.getContainingModule(declarationDescriptorWithVisibility))) {
                return Visibilities.MODULE_VISIBILITY_HELPER.isInFriendModule(declarationDescriptorWithVisibility, declarationDescriptor);
            }
            return false;
        }
    };
    public static final Visibility INVISIBLE_FAKE = new Visibility("invisible_fake", false) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return false;
        }
    };
    public static final Set<Visibility> INVISIBLE_FROM_OTHER_MODULES = Collections.unmodifiableSet(acot.a((Object[]) new Visibility[]{PRIVATE, PRIVATE_TO_THIS, INTERNAL, LOCAL}));
    private static final ReceiverValue IRRELEVANT_RECEIVER = new ReceiverValue() {
        public final KotlinType getType() {
            throw new IllegalStateException("This method should not be called");
        }
    };
    public static final Visibility LOCAL = new Visibility(ImagesContract.LOCAL, false) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            throw new IllegalStateException("This method shouldn't be invoked for LOCAL visibility");
        }
    };
    private static final ModuleVisibilityHelper MODULE_VISIBILITY_HELPER;
    private static final Map<Visibility, Integer> ORDERED_VISIBILITIES;
    public static final Visibility PRIVATE = new Visibility("private", false) {
        private boolean hasContainingSourceFile(DeclarationDescriptor declarationDescriptor) {
            return DescriptorUtils.getContainingSourceFile(declarationDescriptor) != SourceFile.NO_SOURCE_FILE;
        }

        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            if (DescriptorUtils.isTopLevelDeclaration(declarationDescriptorWithVisibility) && hasContainingSourceFile(declarationDescriptor)) {
                return Visibilities.inSameFile(declarationDescriptorWithVisibility, declarationDescriptor);
            }
            DeclarationDescriptor containingDeclaration;
            if (declarationDescriptorWithVisibility instanceof ConstructorDescriptor) {
                DeclarationDescriptor containingDeclaration2 = ((ConstructorDescriptor) declarationDescriptorWithVisibility).getContainingDeclaration();
                if (DescriptorUtils.isSealedClass(containingDeclaration2) && DescriptorUtils.isTopLevelDeclaration(containingDeclaration2) && (declarationDescriptor instanceof ConstructorDescriptor) && DescriptorUtils.isTopLevelDeclaration(declarationDescriptor.getContainingDeclaration()) && Visibilities.inSameFile(declarationDescriptorWithVisibility, declarationDescriptor)) {
                    return true;
                }
            }
            while (containingDeclaration != null) {
                containingDeclaration = containingDeclaration.getContainingDeclaration();
                if (!(containingDeclaration instanceof ClassDescriptor) || DescriptorUtils.isCompanionObject(containingDeclaration)) {
                    if (containingDeclaration instanceof PackageFragmentDescriptor) {
                        break;
                    }
                }
                break;
            }
            if (containingDeclaration == null) {
                return false;
            }
            while (declarationDescriptor != null) {
                if (containingDeclaration == declarationDescriptor) {
                    return true;
                }
                if (!(declarationDescriptor instanceof PackageFragmentDescriptor)) {
                    declarationDescriptor = declarationDescriptor.getContainingDeclaration();
                } else if ((containingDeclaration instanceof PackageFragmentDescriptor) && ((PackageFragmentDescriptor) containingDeclaration).getFqName().equals(((PackageFragmentDescriptor) declarationDescriptor).getFqName()) && DescriptorUtils.areInSameModule(declarationDescriptor, containingDeclaration)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    };
    public static final Visibility PRIVATE_TO_THIS = new Visibility("private_to_this", false) {
        public final String getDisplayName() {
            return "private/*private to this*/";
        }

        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            if (Visibilities.PRIVATE.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor)) {
                if (receiverValue == Visibilities.ALWAYS_SUITABLE_RECEIVER) {
                    return true;
                }
                if (receiverValue == Visibilities.IRRELEVANT_RECEIVER) {
                    return false;
                }
                DeclarationDescriptor parentOfType = DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class);
                if (parentOfType != null && (receiverValue instanceof ThisClassReceiver)) {
                    return ((ThisClassReceiver) receiverValue).getClassDescriptor().getOriginal().equals(parentOfType.getOriginal());
                }
            }
            return false;
        }
    };
    public static final Visibility PROTECTED = new Visibility("protected", true) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            while (true) {
                ClassDescriptor classDescriptor = (ClassDescriptor) DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class);
                ClassDescriptor classDescriptor2 = (ClassDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, ClassDescriptor.class, false);
                if (classDescriptor2 == null) {
                    return false;
                }
                if (classDescriptor != null && DescriptorUtils.isCompanionObject(classDescriptor)) {
                    classDescriptor = (ClassDescriptor) DescriptorUtils.getParentOfType(classDescriptor, ClassDescriptor.class);
                    if (classDescriptor != null && DescriptorUtils.isSubclass(classDescriptor2, classDescriptor)) {
                        return true;
                    }
                }
                Object unwrapFakeOverrideToAnyDeclaration = DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility);
                ClassDescriptor classDescriptor3 = (ClassDescriptor) DescriptorUtils.getParentOfType(unwrapFakeOverrideToAnyDeclaration, ClassDescriptor.class);
                if (classDescriptor3 == null) {
                    return false;
                }
                if (DescriptorUtils.isSubclass(classDescriptor2, classDescriptor3) && doesReceiverFitForProtectedVisibility(receiverValue, unwrapFakeOverrideToAnyDeclaration, classDescriptor2)) {
                    return true;
                }
                declarationDescriptor = classDescriptor2.getContainingDeclaration();
            }
        }

        private boolean doesReceiverFitForProtectedVisibility(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, ClassDescriptor classDescriptor) {
            if (receiverValue == Visibilities.FALSE_IF_PROTECTED) {
                return false;
            }
            if (!(declarationDescriptorWithVisibility instanceof CallableMemberDescriptor) || (declarationDescriptorWithVisibility instanceof ConstructorDescriptor) || receiverValue == Visibilities.ALWAYS_SUITABLE_RECEIVER) {
                return true;
            }
            if (receiverValue == Visibilities.IRRELEVANT_RECEIVER || receiverValue == null) {
                return false;
            }
            KotlinType thisType = receiverValue instanceof SuperCallReceiverValue ? ((SuperCallReceiverValue) receiverValue).getThisType() : receiverValue.getType();
            if (DescriptorUtils.isSubtypeOfClass(thisType, classDescriptor) || DynamicTypesKt.isDynamic(thisType)) {
                return true;
            }
            return false;
        }
    };
    public static final Visibility PUBLIC = new Visibility("public", true) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return true;
        }
    };
    public static final Visibility UNKNOWN = new Visibility(NetworkManager.TYPE_UNKNOWN, false) {
        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return false;
        }
    };

    static {
        Map newHashMapWithExpectedSize = CollectionsKt.newHashMapWithExpectedSize(4);
        newHashMapWithExpectedSize.put(PRIVATE_TO_THIS, Integer.valueOf(0));
        newHashMapWithExpectedSize.put(PRIVATE, Integer.valueOf(0));
        newHashMapWithExpectedSize.put(INTERNAL, Integer.valueOf(1));
        newHashMapWithExpectedSize.put(PROTECTED, Integer.valueOf(1));
        newHashMapWithExpectedSize.put(PUBLIC, Integer.valueOf(2));
        ORDERED_VISIBILITIES = Collections.unmodifiableMap(newHashMapWithExpectedSize);
        Iterator it = ServiceLoader.load(ModuleVisibilityHelper.class, ModuleVisibilityHelper.class.getClassLoader()).iterator();
        MODULE_VISIBILITY_HELPER = it.hasNext() ? (ModuleVisibilityHelper) it.next() : EMPTY.INSTANCE;
    }

    public static boolean isVisibleIgnoringReceiver(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
        return findInvisibleMember(ALWAYS_SUITABLE_RECEIVER, declarationDescriptorWithVisibility, declarationDescriptor) == null;
    }

    public static boolean inSameFile(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        SourceFile containingSourceFile = DescriptorUtils.getContainingSourceFile(declarationDescriptor2);
        return containingSourceFile != SourceFile.NO_SOURCE_FILE ? containingSourceFile.equals(DescriptorUtils.getContainingSourceFile(declarationDescriptor)) : false;
    }

    public static DeclarationDescriptorWithVisibility findInvisibleMember(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
        Object obj = (DeclarationDescriptorWithVisibility) declarationDescriptorWithVisibility.getOriginal();
        while (obj != null && obj.getVisibility() != LOCAL) {
            if (!obj.getVisibility().isVisible(receiverValue, obj, declarationDescriptor)) {
                return obj;
            }
            DeclarationDescriptorWithVisibility obj2 = (DeclarationDescriptorWithVisibility) DescriptorUtils.getParentOfType(obj2, DeclarationDescriptorWithVisibility.class);
        }
        if (declarationDescriptorWithVisibility instanceof TypeAliasConstructorDescriptor) {
            DeclarationDescriptorWithVisibility findInvisibleMember = findInvisibleMember(receiverValue, ((TypeAliasConstructorDescriptor) declarationDescriptorWithVisibility).getUnderlyingConstructorDescriptor(), declarationDescriptor);
            if (findInvisibleMember != null) {
                return findInvisibleMember;
            }
        }
        return null;
    }

    static Integer compareLocal(Visibility visibility, Visibility visibility2) {
        if (visibility == visibility2) {
            return Integer.valueOf(0);
        }
        Integer num = (Integer) ORDERED_VISIBILITIES.get(visibility);
        Integer num2 = (Integer) ORDERED_VISIBILITIES.get(visibility2);
        return (num == null || num2 == null || num.equals(num2)) ? null : Integer.valueOf(num.intValue() - num2.intValue());
    }

    public static Integer compare(Visibility visibility, Visibility visibility2) {
        Integer compareTo = visibility.compareTo(visibility2);
        if (compareTo != null) {
            return compareTo;
        }
        Integer compareTo2 = visibility2.compareTo(visibility);
        return compareTo2 != null ? Integer.valueOf(-compareTo2.intValue()) : null;
    }

    public static boolean isPrivate(Visibility visibility) {
        return visibility == PRIVATE || visibility == PRIVATE_TO_THIS;
    }
}
