package ch4;

public class DefaultBookService2{
    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }
    public void returnBook(Book book) {
        System.out.println("return: " + book.getTitle());
    }
}
