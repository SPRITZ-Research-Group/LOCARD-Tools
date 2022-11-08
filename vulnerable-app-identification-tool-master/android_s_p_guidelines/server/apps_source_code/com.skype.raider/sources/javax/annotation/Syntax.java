package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.a;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@TypeQualifier(applicableTo = CharSequence.class)
public @interface Syntax {
    String value();

    a when() default a.ALWAYS;
}
