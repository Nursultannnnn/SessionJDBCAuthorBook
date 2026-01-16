package peaksoft.dao.daoImpl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.BookDao;
import peaksoft.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookDaoImpl implements BookDao {
private final Connection connection = JdbcConfig.getConnection();
    @Override
    public void createBook() {

        String sql = """
                create table if not exists books(
                id serial primary key,
                name varchar(255),
                published_year int,
                price int,
                author_id int references authors(id))
                """;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String saveBook(Book book) {
        String sql = """
                insert into books (name, published_year, price, author_id)
                values(?,?,?,?)
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,book.getName());
            preparedStatement.setInt(2,book.getPublishedYear());
            preparedStatement.setInt(3,book.getPrice());
            preparedStatement.setLong(4,book.getAuthorId());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
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
