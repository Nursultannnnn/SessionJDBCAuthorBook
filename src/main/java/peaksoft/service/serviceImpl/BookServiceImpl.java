package peaksoft.service.serviceImpl;

import peaksoft.dao.BookDao;
import peaksoft.dao.daoImpl.BookDaoImpl;
import peaksoft.models.Book;
import peaksoft.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private  final BookDao bookDao = new BookDaoImpl();
    @Override
    public void createBook() {
bookDao.createBook();
    }

    @Override
    public String saveBook(Book book) {
        bookDao.saveBook(book);

        return "";
    }

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public String update(Long id, Book newBook) {
        return "";
    }

    @Override
    public String delete(Long id) {
        return "";
    }

    @Override
    public List<Book> getBooksByAuthorId(Long id) {
        return List.of();
    }

    @Override
    public List<String> getBooksByCountry(String country) {
        return List.of();
    }

    @Override
    public List<String> getMaxPrice() {
        return List.of();
    }

    @Override
    public List<String> sumBookByAuthorId(Long id) {
        return List.of();
    }
}
