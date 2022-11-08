package kotlin.reflect.jvm.internal.impl.util;

import defpackage.acqr;
import defpackage.acru;
import defpackage.acry;
import defpackage.adcn;
import java.util.Arrays;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.IllegalSignature;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.SuccessCheck;

public final class Checks {
    private final acqr<FunctionDescriptor, String> additionalCheck;
    private final Check[] checks;
    private final Name name;
    private final Collection<Name> nameList;
    private final adcn regex;

    private Checks(Name name, adcn adcn, Collection<Name> collection, acqr<? super FunctionDescriptor, String> acqr, Check... checkArr) {
        this.name = name;
        this.regex = adcn;
        this.nameList = collection;
        this.additionalCheck = acqr;
        this.checks = checkArr;
    }

    public final boolean isApplicable(FunctionDescriptor functionDescriptor) {
        if (this.name != null && (acry.a(functionDescriptor.getName(), this.name) ^ 1) != 0) {
            return false;
        }
        if (this.regex != null) {
            if (!this.regex.a(functionDescriptor.getName().asString())) {
                return false;
            }
        }
        return this.nameList == null || this.nameList.contains(functionDescriptor.getName());
    }

    public final CheckResult checkAll(FunctionDescriptor functionDescriptor) {
        for (Check invoke : this.checks) {
            String invoke2 = invoke.invoke(functionDescriptor);
            if (invoke2 != null) {
                return new IllegalSignature(invoke2);
            }
        }
        String str = (String) this.additionalCheck.invoke(functionDescriptor);
        if (str != null) {
            return new IllegalSignature(str);
        }
        return SuccessCheck.INSTANCE;
    }

    public /* synthetic */ Checks(Name name, Check[] checkArr, acqr acqr, int i, acru acru) {
        if ((i & 4) != 0) {
            acqr = AnonymousClass2.INSTANCE;
        }
        this(name, checkArr, acqr);
    }

    public Checks(Name name, Check[] checkArr, acqr<? super FunctionDescriptor, String> acqr) {
        this(name, null, null, (acqr) acqr, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
    }

    public /* synthetic */ Checks(adcn adcn, Check[] checkArr, acqr acqr, int i, acru acru) {
        if ((i & 4) != 0) {
            acqr = AnonymousClass3.INSTANCE;
        }
        this(adcn, checkArr, acqr);
    }

    public Checks(adcn adcn, Check[] checkArr, acqr<? super FunctionDescriptor, String> acqr) {
        this(null, adcn, null, (acqr) acqr, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
    }

    public /* synthetic */ Checks(Collection collection, Check[] checkArr, acqr acqr, int i, acru acru) {
        if ((i & 4) != 0) {
            acqr = AnonymousClass4.INSTANCE;
        }
        this(collection, checkArr, acqr);
    }

    public Checks(Collection<Name> collection, Check[] checkArr, acqr<? super FunctionDescriptor, String> acqr) {
        this(null, null, (Collection) collection, (acqr) acqr, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
    }
}
