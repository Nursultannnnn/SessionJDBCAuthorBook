package peaksoft.service;

import peaksoft.models.Author;
import peaksoft.service.serviceImpl.AuthorServiceImpl;

import java.util.List;

public interface AuthorService  {
    //TODO methods for table
    boolean createTable();

    void dropTable();

    void cleanTable();
    // TODO crud
    String saveAuthor(Author author);
    List<Author> getAllAuthors();
    Author getById(Long id);
    String updateAuthor(Long id , Author newAuthor);
    String deleteAuthor(Long id );
    List<Author>sortByBirthDate();
    List<Author>groupByCountry();
}
