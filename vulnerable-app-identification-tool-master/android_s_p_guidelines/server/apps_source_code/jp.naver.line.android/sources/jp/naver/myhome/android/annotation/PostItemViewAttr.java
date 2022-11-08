package jp.naver.myhome.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostItemViewAttr {
    boolean a() default false;

    float[] b() default {};

    float[] c() default {};

    float[] d() default {};

    float[] e() default {};
}
