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
         return bookDao.getAll();
    }

    @Override
    public Book getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public String update(Long id, Book newBook) {
        return bookDao.update(id, newBook);
    }

    @Override
    public String delete(Long id) {
        return bookDao.delete(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(Long id) {
        return bookDao.getBooksByAuthorId(id);
    }

    @Override
    public List<String> getBooksByCountry(String country) {
        return bookDao.getBooksByCountry(country);
    }

    @Override
    public List<String> getMaxPrice() {
        return bookDao.getMaxPrice();
    }

    @Override
    public List<String> sumBookByAuthorId(Long id) {
        return bookDao.sumBookByAuthorId(id);
    }
}
