package kotlin.jvm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.annotation.a;
import kotlin.annotation.b;

@MustBeDocumented
@Target(allowedTargets = {b.FIELD})
@Retention(a.BINARY)
@Documented
@java.lang.annotation.Target({ElementType.FIELD})
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lkotlin/jvm/JvmField;", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 10})
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
public @interface JvmField {
}
