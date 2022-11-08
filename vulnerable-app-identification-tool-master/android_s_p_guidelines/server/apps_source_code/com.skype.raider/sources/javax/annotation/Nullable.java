package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.a;

@Documented
@Nonnull(when = a.UNKNOWN)
@TypeQualifierNickname
@Retention(RetentionPolicy.RUNTIME)
public @interface Nullable {
}
