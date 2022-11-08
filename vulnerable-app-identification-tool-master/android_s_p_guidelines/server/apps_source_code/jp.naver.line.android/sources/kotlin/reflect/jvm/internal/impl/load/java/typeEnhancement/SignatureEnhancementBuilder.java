package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acno;
import defpackage.acns;
import defpackage.acoe;
import defpackage.acom;
import defpackage.acqr;
import defpackage.actx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.u;
import kotlin.y;

final class SignatureEnhancementBuilder {
    private final Map<String, PredefinedFunctionEnhancementInfo> signatures = new LinkedHashMap();

    public final class ClassEnhancementBuilder {
        private final String className;

        public final class FunctionEnhancementBuilder {
            private final String functionName;
            private final List<m<String, TypeEnhancementInfo>> parameters = new ArrayList();
            private m<String, TypeEnhancementInfo> returnType = u.a("V", null);

            public FunctionEnhancementBuilder(String str) {
                this.functionName = str;
            }

            public final void parameter(String str, JavaTypeQualifiers... javaTypeQualifiersArr) {
                Object obj;
                Collection collection = this.parameters;
                if ((javaTypeQualifiersArr.length == 0 ? 1 : null) != null) {
                    obj = null;
                } else {
                    Iterable<acoe> i = acno.i(javaTypeQualifiersArr);
                    Map linkedHashMap = new LinkedHashMap(actx.c(acom.a(acns.a((Iterable) i, 10)), 16));
                    for (acoe acoe : i) {
                        linkedHashMap.put(Integer.valueOf(acoe.a()), (JavaTypeQualifiers) acoe.b());
                    }
                    obj = new TypeEnhancementInfo(linkedHashMap);
                }
                collection.add(u.a(str, obj));
            }

            public final void returns(String str, JavaTypeQualifiers... javaTypeQualifiersArr) {
                Iterable<acoe> i = acno.i(javaTypeQualifiersArr);
                Map linkedHashMap = new LinkedHashMap(actx.c(acom.a(acns.a((Iterable) i, 10)), 16));
                for (acoe acoe : i) {
                    linkedHashMap.put(Integer.valueOf(acoe.a()), (JavaTypeQualifiers) acoe.b());
                }
                this.returnType = u.a(str, new TypeEnhancementInfo(linkedHashMap));
            }

            public final void returns(JvmPrimitiveType jvmPrimitiveType) {
                this.returnType = u.a(jvmPrimitiveType.getDesc(), null);
            }

            public final m<String, PredefinedFunctionEnhancementInfo> build() {
                SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
                String className = ClassEnhancementBuilder.this.getClassName();
                String str = this.functionName;
                Iterable<m> iterable = this.parameters;
                Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
                for (m a : iterable) {
                    arrayList.add((String) a.a());
                }
                String signature = signatureBuildingComponents.signature(className, signatureBuildingComponents.jvmDescriptor(str, (List) arrayList, (String) this.returnType.a()));
                TypeEnhancementInfo typeEnhancementInfo = (TypeEnhancementInfo) this.returnType.b();
                Iterable<m> iterable2 = this.parameters;
                Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable2, 10));
                for (m b : iterable2) {
                    arrayList2.add((TypeEnhancementInfo) b.b());
                }
                return u.a(signature, new PredefinedFunctionEnhancementInfo(typeEnhancementInfo, (List) arrayList2));
            }
        }

        public ClassEnhancementBuilder(String str) {
            this.className = str;
        }

        public final String getClassName() {
            return this.className;
        }

        public final void function(String str, acqr<? super FunctionEnhancementBuilder, y> acqr) {
            Map access$getSignatures$p = SignatureEnhancementBuilder.this.signatures;
            FunctionEnhancementBuilder functionEnhancementBuilder = new FunctionEnhancementBuilder(str);
            acqr.invoke(functionEnhancementBuilder);
            m build = functionEnhancementBuilder.build();
            access$getSignatures$p.put(build.a(), build.b());
        }
    }

    public final Map<String, PredefinedFunctionEnhancementInfo> build() {
        return this.signatures;
    }
}
