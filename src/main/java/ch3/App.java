package ch3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //class 접근 방법 3가지
        //1
        Class<Book> bookClass = Book.class;
        //2
        Book book = new Book();
        Class<? extends Book> bClass = book.getClass();
        Constructor<?> constructor = bClass.getConstructor(null);
        Book book1 = (Book) constructor.newInstance();
        //OR
        constructor = bClass.getConstructor(String.class);
        book1 = (Book) constructor.newInstance("myBook");

        Field a = Book.class.getDeclaredField("A");
        System.out.println(a.get(null));//static 변수라서 넘겨줄 object가 없으므로 null
        a.set(null, "AAAAA");
        System.out.println(a.get(null));

        Field b = bClass.getDeclaredField("B");
        b.setAccessible(true);
        System.out.println(b.get( book1));//특정한 인스턴스가 있어야 쓸 수 있음
        b.set(book1, "BBBB");
        System.out.println(b.get(book1));
        bClass.getDeclaredMethod("c").invoke(book1);

        //3
        Class<?> aClass2 = Class.forName("ch3.Book");

        // 필드 등의 접근
        // 기본적으로는 public만 가져오고, 나머지도 가져오고 싶으면 여러 메서드를 사용 가능
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        //그냥 아래와 같이 하면 애노테이션이 나오지 않음
        //애노테이션은 주석과 같은 취급. 바이트코드를 읽어 올때(로딩 했을때) 이 정보가 남지 않음 - Retention을 사용하여 남게 할 수 있음
        Arrays.stream(bookClass.getAnnotations()).forEach(System.out::println);

        Arrays.stream(bookClass.getFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(an -> {
                if (an instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) an;
                    System.out.println(myAnnotation.name());
                    System.out.println(myAnnotation.value());
                }
            });
        });


    }
}
