package ch3;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE}) //타입과 필드에만 붙일 수 있는 어노테이션이됨
@Inherited //상속이 되는 애노테이션 (부모에 붙어있는 애노테이션까지 포함)
public @interface MyAnnotation {
    String name() default "sunju"; //기본값 안주면 애노테이션 설정한 곳에 값을 주어야함
    String value() default "value"; //value는 애노테이션이서 바로 적기만하면됨. 이름 생략가능 @MyAnnotation("value")
}
