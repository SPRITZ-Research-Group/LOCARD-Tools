package kotlin.jvm;

import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.annotation.a;
import kotlin.annotation.b;

@MustBeDocumented
@SinceKotlin(version = "1.2")
@Target(allowedTargets = {b.FILE})
@Retention(a.SOURCE)
@Documented
@java.lang.annotation.Target({})
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\t\u0010\u0002\u001a\u00020\u0003¢\u0006\u0000¨\u0006\u0004"}, d2 = {"Lkotlin/jvm/JvmPackageName;", "", "name", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 10})
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
public @interface JvmPackageName {
    String name();
}
