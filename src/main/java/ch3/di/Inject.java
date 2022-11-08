package ch3.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //런타임에 참조할 애노테이션
public @interface Inject {
}
