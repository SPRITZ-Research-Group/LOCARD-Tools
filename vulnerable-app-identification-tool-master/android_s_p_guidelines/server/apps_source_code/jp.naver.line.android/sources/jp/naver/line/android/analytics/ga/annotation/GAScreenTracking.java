package jp.naver.line.android.analytics.ga.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GAScreenTracking {
    String a() default "";

    boolean b() default true;

    String c() default "";
}
