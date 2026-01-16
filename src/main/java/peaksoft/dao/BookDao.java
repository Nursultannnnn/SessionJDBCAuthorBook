package peaksoft.dao;

import peaksoft.models.Book;

import java.util.List;

public interface BookDao {
    void createBook();
    String saveBook (Book book);
    List<Book> getAll();
    Book getById(Long id);
    String update(Long id,Book newBook);
    String delete(Long id);
    //    Dop methods
    List<Book> getBooksByAuthorId(Long id);
    //    country KG ga barabar bolgon authorlordyn kitepterin alyp chykkyla
    List<String> getBooksByCountry(String country);
    //    en kymbat kitepti authorynyn aty menen chygar
    List<String> getMaxPrice();
    //    authordun kitepterinin obshii summasyn chygar
    List<String> sumBookByAuthorId(Long id);

}
