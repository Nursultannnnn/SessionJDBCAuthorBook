package peaksoft;

import peaksoft.dao.AuthorDao;
import peaksoft.dao.daoImpl.AuthorDaoImpl;
import peaksoft.models.Author;
import peaksoft.service.AuthorService;
import peaksoft.service.serviceImpl.AuthorServiceImpl;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AuthorService authorService = new AuthorServiceImpl();

//        authorService.createTable();
//        authorService.saveAuthor(new Author("nurs","suyunbekov","n@gmail.com","KG",
//        LocalDate.of(2007,1,29)));
//        authorService.saveAuthor(new Author("aktan","suyunbekov","a@gmail.com","KG",
//                LocalDate.of(2009,6,20)));
//        System.out.println(authorService.getById(4L));
//        System.out.println(authorService.deleteAuthor(2l));

//        System.out.println(authorService.getAllAuthors());
//        System.out.println(authorService.updateAuthor(3L, new Author("Muha", "Umetbekov", "m@gmail.com", "USA",
//                LocalDate.of(2006, 5, 5))));

//        System.out.println(authorService.sortByBirthDate());
//        System.out.println(authorService.groupByCountry());

//        BookService bookService = new BookServiceImpl();
//        bookService.createBook();
//        bookService.saveBook(new Book("Book1",1990,1234,2L));
//        bookService.saveBook(new Book("Book2",1997,5800,3L));
//        bookService.saveBook(new Book("Book3",1992,8000,4L));
//        bookService.saveBook(new Book("Book4",1991,2043,1L));
//        bookService.saveBook(new Book("Book5",1981,3000,5L));
//
//        System.out.println(bookService.getAll());
//        System.out.println(bookService.getById(2L));
//        System.out.println(bookService.getBooksByCountry("KG"));
//        System.out.println(bookService.getMaxPrice());
//        System.out.println(bookService.getBooksByAuthorId(6L));
//        System.out.println(bookService.sumBookByAuthorId(6L));
    }
}
