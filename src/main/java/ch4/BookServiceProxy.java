package ch4;

public class BookServiceProxy implements BookService{
    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public void rent(Book book) {
        System.out.println("aaaaa");
        System.out.println(book.getTitle());
        System.out.println("bbbbb");
    }

    @Override
    public void returnBook(Book book) {
        //이렇게 다른 메서드와 비슷한 코드가 중복될 가능성이 있으므로 다이나믹 프록시가 나오게 되었다.
        System.out.println("aaaaa");
        System.out.println(book.getTitle());
        System.out.println("bbbbb");
    }
}
