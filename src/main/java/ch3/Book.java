package ch3;

@MyAnnotation
public class Book {
    @MyAnnotation
    public static String A = "A";
    private String B = "B";

    public Book() {
    }

    public Book(String b) {
        B = b;
    }

    public void c() {
        System.out.println("c");
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
