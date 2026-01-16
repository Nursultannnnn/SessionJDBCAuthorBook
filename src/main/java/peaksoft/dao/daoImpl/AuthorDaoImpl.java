package peaksoft.dao.daoImpl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.AuthorDao;
import peaksoft.models.Author;

import java.sql.*;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public boolean createTable() {
        String sql = """
                create table if not exists authors(
                id serial primary key,
                first_name varchar(255),
                last_name varchar(255),
                email varchar(255),
                country varchar(255),
                birth_date date)
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void dropTable() {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public String saveAuthor(Author author) {
        String sql = """
                insert into authors(first_name, last_name, email , country , birth_date) 
                values(?,?,?,?,?)    
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setString(4, author.getCountry());
            preparedStatement.setDate(5, Date.valueOf(author.getBirthDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Success";
    }

    @Override
    public List<Author> getAllAuthors() {
        return null;
    }

    @Override
    public Author getById(Long id) {
        Author author = new Author();
        String sql = """
                select * from authors where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setEmail(resultSet.getString("email"));
                author.setCountry(resultSet.getString("country"));
                author.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return author;
    }

    @Override
    public String updateAuthor(Long id, Author newAuthor) {
        return "";
    }

    @Override
    public String deleteAuthor(Long id) {
        String sql = "delete from authors where id = ?;";
try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
preparedStatement.setLong(1,id);
preparedStatement.executeUpdate();
}catch (SQLException e){
    System.out.println(e.getMessage());
}
        return "success";
    }

    @Override
    public List<Author> sortByBirthDate() {
        return null;
    }

    @Override
    public List<Author> groupByCountry() {
        return null;
    }
}
