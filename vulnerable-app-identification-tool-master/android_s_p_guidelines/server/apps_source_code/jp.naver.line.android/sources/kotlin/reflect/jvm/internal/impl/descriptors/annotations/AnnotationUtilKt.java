package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.applovin.sdk.AppLovinEventTypes;
import defpackage.acob;
import defpackage.acom;
import jp.naver.android.npush.common.NPushIntent;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.u;

public final class AnnotationUtilKt {
    private static final Name DEPRECATED_LEVEL_NAME = Name.identifier(AppLovinEventTypes.USER_COMPLETED_LEVEL);
    private static final Name DEPRECATED_MESSAGE_NAME = Name.identifier(NPushIntent.PARAM_MESSAGE);
    private static final Name DEPRECATED_REPLACE_WITH_NAME = Name.identifier("replaceWith");
    private static final Name REPLACE_WITH_EXPRESSION_NAME = Name.identifier("expression");
    private static final Name REPLACE_WITH_IMPORTS_NAME = Name.identifier("imports");

    public static /* synthetic */ AnnotationDescriptor createDeprecatedAnnotation$default(KotlinBuiltIns kotlinBuiltIns, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "WARNING";
        }
        return createDeprecatedAnnotation(kotlinBuiltIns, str, str2, str3);
    }

    public static final AnnotationDescriptor createDeprecatedAnnotation(KotlinBuiltIns kotlinBuiltIns, String str, String str2, String str3) {
        BuiltInAnnotationDescriptor builtInAnnotationDescriptor = new BuiltInAnnotationDescriptor(kotlinBuiltIns, KotlinBuiltIns.FQ_NAMES.replaceWith, acom.a(u.a(REPLACE_WITH_EXPRESSION_NAME, new StringValue(str2)), u.a(REPLACE_WITH_IMPORTS_NAME, new ArrayValue(acob.a, new AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1(kotlinBuiltIns)))));
        return new BuiltInAnnotationDescriptor(kotlinBuiltIns, KotlinBuiltIns.FQ_NAMES.deprecated, acom.a(u.a(DEPRECATED_MESSAGE_NAME, new StringValue(str)), u.a(DEPRECATED_REPLACE_WITH_NAME, new AnnotationValue(builtInAnnotationDescriptor)), u.a(DEPRECATED_LEVEL_NAME, new EnumValue(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.deprecationLevel), Name.identifier(str3)))));
    }
}
