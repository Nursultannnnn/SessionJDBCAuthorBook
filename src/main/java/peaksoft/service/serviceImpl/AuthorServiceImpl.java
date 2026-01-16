package peaksoft.service.serviceImpl;

import peaksoft.dao.AuthorDao;
import peaksoft.dao.daoImpl.AuthorDaoImpl;
import peaksoft.models.Author;
import peaksoft.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao = new AuthorDaoImpl();
    @Override
    public boolean createTable() {
        return authorDao.createTable();
    }

    @Override
    public void dropTable() {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public String saveAuthor(Author author) {
        return authorDao.saveAuthor(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return List.of();
    }

    @Override
    public Author getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    public String updateAuthor(Long id, Author newAuthor) {
        return "";
    }

    @Override
    public String deleteAuthor(Long id) {
        return authorDao.deleteAuthor(id);
    }

    @Override
    public List<Author> sortByBirthDate() {
        return List.of();
    }

    @Override
    public List<Author> groupByCountry() {
        return List.of();
    }
}
