package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.a;

@Syntax("RegEx")
@Documented
@TypeQualifierNickname
@Retention(RetentionPolicy.RUNTIME)
public @interface RegEx {
    a when() default a.ALWAYS;
}
