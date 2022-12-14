package kotlin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.annotation.a;
import kotlin.annotation.b;

@MustBeDocumented
@Target(allowedTargets = {b.CLASS, b.PROPERTY, b.FIELD, b.CONSTRUCTOR, b.FUNCTION, b.PROPERTY_GETTER, b.PROPERTY_SETTER, b.TYPEALIAS})
@Retention(a.BINARY)
@Documented
@java.lang.annotation.Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\t\u0010\u0002\u001a\u00020\u0003¢\u0006\u0000¨\u0006\u0004"}, d2 = {"Lkotlin/SinceKotlin;", "", "version", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 10})
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
public @interface SinceKotlin {
    String version();
}
