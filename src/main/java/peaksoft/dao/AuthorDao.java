package peaksoft.dao;

import peaksoft.models.Author;

import java.util.List;

public interface AuthorDao  {
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
