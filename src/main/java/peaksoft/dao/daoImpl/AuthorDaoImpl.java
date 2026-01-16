package peaksoft.dao.daoImpl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.AuthorDao;
import peaksoft.models.Author;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "drop table if exists authors cascade";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table authors dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cleanTable() {
        String sql= "truncate table authors";
        try( Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

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
        List<Author> authors = new ArrayList<>();
        String sql = """
                select * from authors;
                """;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setEmail(resultSet.getString("email"));
                author.setCountry(resultSet.getString("country"));
                author.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                authors.add(author);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return authors;
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
        String sql = """
                update authors\s
                            set first_name = ?,\s
                                last_name = ?,\s
                                email = ?,\s
                                country = ?,\s
                                birth_date = ?\s
                            where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newAuthor.getFirstName());
            preparedStatement.setString(2, newAuthor.getLastName());
            preparedStatement.setString(3, newAuthor.getEmail());
            preparedStatement.setString(4, newAuthor.getCountry());
            preparedStatement.setDate(5, Date.valueOf(newAuthor.getBirthDate()));

            preparedStatement.setLong(6, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Success";
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
        String sql ="select * from authors order by birth_date";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            List<Author>authors=new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Author author=new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setCountry(resultSet.getString("country"));
                author.setEmail(resultSet.getString("email"));
                author.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                authors.add(author);
            }
            return authors;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Author> groupByCountry() {
        String sql = """
        SELECT country, COUNT(*) AS author_count
        FROM authors
        GROUP BY country
        ORDER BY country
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Author> authors = new ArrayList<>();

            while (rs.next()) {
                Author author = new Author();
                author.setCountry(rs.getString("country"));
                author.setId((long) rs.getInt("author_count"));
                authors.add(author);
            }

            return authors;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }
}
