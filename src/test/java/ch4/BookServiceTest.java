package ch4;

import net.bytebuddy.ByteBuddy;
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookServiceTest {

    //class로더, 어떤 클래스인지 배열, proxy의 메서드가 호출이 될때 어떻게 처리할지(invocationHandler)
    //유연하지 않다. -> spring aop의 등장 이용
    
    //아래의 경우 가장 큰 제약사항: 클래스기반 프록시를 만들지 못함. 반드시 인터페이스여야한다. ==> CGlib, ByteBuddy 사용(바이트코드 조작)
    BookService bookService = (BookService) Proxy.newProxyInstance(ch3.di.BookService.class.getClassLoader(), new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("rent")) {
                        System.out.println("aaaa");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("aaaa");
                        return invoke;
                    }
                    return method.invoke(bookService, args);
                }
            });

    @Test
    public void di() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //cglib사용
//        MethodInterceptor handler = new MethodInterceptor() {
//            BookService bookService = new DefaultBookService();
//            @Override
//            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                System.out.println("cccc");
//                Object invoke = method.invoke(bookService, args);
//                System.out.println("dddd");
//                return invoke;
//            }
//        };
//        BookService bookService1 = (BookService) Enhancer.create(BookService.class, handler);
        Class<? extends DefaultBookService2> proxyClass = new ByteBuddy().subclass(DefaultBookService2.class)
                .method(ElementMatchers.is("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    DefaultBookService2 bookService = new DefaultBookService2();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("cccc");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("dddd");
                        return invoke;
                    }
                }))
                .make().load(DefaultBookService2.class.getClassLoader()).getLoaded();

        DefaultBookService2 bookService1 = (DefaultBookService2) proxyClass.getConstructor(null).newInstance();
        Book book = new Book();
        book.setTitle("spring");
        bookService1.rent(book);
        bookService1.returnBook(book);
    }
}
