package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementBuilder.ClassEnhancementBuilder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;

public final class PredefinedEnhancementInfoKt {
    private static final JavaTypeQualifiers NOT_NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, true, false, 8, null);
    private static final JavaTypeQualifiers NOT_PLATFORM = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, false, false, 8, null);
    private static final JavaTypeQualifiers NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NULLABLE, null, false, false, 8, null);
    private static final Map<String, PredefinedFunctionEnhancementInfo> PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;

    static {
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String javaLang = signatureBuildingComponents.javaLang("Object");
        String javaFunction = signatureBuildingComponents.javaFunction("Predicate");
        String javaFunction2 = signatureBuildingComponents.javaFunction("Function");
        String javaFunction3 = signatureBuildingComponents.javaFunction("Consumer");
        String javaFunction4 = signatureBuildingComponents.javaFunction("BiFunction");
        String javaFunction5 = signatureBuildingComponents.javaFunction("BiConsumer");
        String javaFunction6 = signatureBuildingComponents.javaFunction("UnaryOperator");
        String javaUtil = signatureBuildingComponents.javaUtil("stream/Stream");
        String javaUtil2 = signatureBuildingComponents.javaUtil("Optional");
        SignatureEnhancementBuilder signatureEnhancementBuilder = new SignatureEnhancementBuilder();
        SignatureBuildingComponents signatureBuildingComponents2 = signatureBuildingComponents;
        String str = javaFunction3;
        String str2 = javaFunction;
        String str3 = javaFunction;
        String str4 = javaUtil;
        String str5 = javaFunction3;
        ClassEnhancementBuilder classEnhancementBuilder = new ClassEnhancementBuilder(signatureBuildingComponents.javaUtil("Iterator"));
        String str6 = javaFunction6;
        String str7 = javaFunction6;
        SignatureEnhancementBuilder signatureEnhancementBuilder2 = signatureEnhancementBuilder;
        String str8 = javaFunction5;
        String str9 = javaUtil2;
        javaUtil2 = javaLang;
        String str10 = javaFunction4;
        String str11 = javaFunction2;
        String str12 = str9;
        classEnhancementBuilder.function("forEachRemaining", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$1(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        str = str5;
        str2 = str3;
        str6 = str7;
        new ClassEnhancementBuilder(signatureBuildingComponents.javaLang("Iterable")).function("spliterator", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$2(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder = new ClassEnhancementBuilder(signatureBuildingComponents.javaUtil("Collection"));
        classEnhancementBuilder.function("removeIf", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("stream", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$4(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("parallelStream", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$5(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        new ClassEnhancementBuilder(signatureBuildingComponents.javaUtil("List")).function("replaceAll", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$6(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder = new ClassEnhancementBuilder(signatureBuildingComponents.javaUtil("Map"));
        classEnhancementBuilder.function("forEach", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$7(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("putIfAbsent", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$8(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("replace", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$9(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("replace", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$10(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("replaceAll", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$11(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("compute", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$12(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("computeIfAbsent", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$13(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("computeIfPresent", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$14(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder.function("merge", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$15(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        javaFunction3 = str9;
        ClassEnhancementBuilder classEnhancementBuilder2 = new ClassEnhancementBuilder(javaFunction3);
        SignatureEnhancementBuilder signatureEnhancementBuilder3 = signatureEnhancementBuilder2;
        javaUtil2 = javaLang;
        str12 = javaFunction3;
        classEnhancementBuilder2.function("empty", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$16(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder2.function("of", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$17(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder2.function("ofNullable", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$18(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder2.function("get", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$19(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        classEnhancementBuilder2.function("ifPresent", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$20(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        SignatureEnhancementBuilder signatureEnhancementBuilder4 = signatureEnhancementBuilder3;
        String str13 = javaFunction5;
        SignatureEnhancementBuilder signatureEnhancementBuilder5 = signatureEnhancementBuilder4;
        new ClassEnhancementBuilder(signatureBuildingComponents.javaLang("ref/Reference")).function("get", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$21(signatureBuildingComponents2, str, str2, str4, str6, str8, javaLang, str10, str11, str12));
        javaFunction6 = str3;
        str2 = javaFunction6;
        str8 = str13;
        String str14 = javaFunction6;
        javaUtil2 = javaLang;
        new ClassEnhancementBuilder(javaFunction6).function("test", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$22(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        str2 = str14;
        new ClassEnhancementBuilder(signatureBuildingComponents.javaFunction("BiPredicate")).function("test", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$23(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        javaFunction6 = str5;
        String str15 = javaFunction6;
        new ClassEnhancementBuilder(javaFunction6).function("accept", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$24(signatureBuildingComponents2, javaFunction6, str2, str4, str6, str8, javaLang, str10, str11, str12));
        javaFunction6 = str13;
        str = str15;
        str8 = javaFunction6;
        String str16 = javaFunction6;
        javaUtil2 = javaLang;
        new ClassEnhancementBuilder(javaFunction6).function("accept", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$25(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        str8 = str16;
        new ClassEnhancementBuilder(javaFunction2).function("apply", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$26(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        new ClassEnhancementBuilder(javaFunction4).function("apply", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$27(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        new ClassEnhancementBuilder(signatureBuildingComponents.javaFunction("Supplier")).function("get", new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$28(signatureBuildingComponents2, str, str2, str4, str6, str8, javaUtil2, str10, str11, str12));
        PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = signatureEnhancementBuilder5.build();
    }

    public static final Map<String, PredefinedFunctionEnhancementInfo> getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE() {
        return PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    }
}
