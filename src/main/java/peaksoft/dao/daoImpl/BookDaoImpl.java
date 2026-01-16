package peaksoft.dao.daoImpl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.BookDao;
import peaksoft.models.Book;

import java.sql.*;
import java.util.ArrayList;
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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String saveBook(Book book) {
        String sql = """
                insert into books (name, published_year, price, author_id)
                values(?,?,?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getPublishedYear());
            preparedStatement.setInt(3, book.getPrice());
            preparedStatement.setLong(4, book.getAuthorId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public List<Book> getAll() {

        List<Book> books = new ArrayList<>();
        String sql = "select * from books";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setPublishedYear(resultSet.getInt("published_year"));
                book.setPrice(resultSet.getInt("price"));
                book.setAuthorId(resultSet.getLong("author_id"));

                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public Book getById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setPublishedYear(rs.getInt("published_year"));
                book.setPrice(rs.getInt("price"));
                book.setAuthorId(rs.getLong("author_id"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(Long id, Book newBook) {
        String sql = "UPDATE books SET name=?, published_year=?, price=?, author_id=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newBook.getName());
            ps.setInt(2, newBook.getPublishedYear());
            ps.setInt(3, newBook.getPrice());
            ps.setLong(4, newBook.getAuthorId());
            ps.setLong(5, id);
            ps.executeUpdate();
            return "Обновлено";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return "Удалено";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Book> getBooksByAuthorId(Long id) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("published_year"),
                        rs.getInt("price"),
                        rs.getLong("author_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public List<String> getBooksByCountry(String country) {
        List<String> result = new ArrayList<>();

        String sql = """
                SELECT a.first_name, a.last_name, b.name
                FROM books b
                JOIN authors a ON b.author_id = a.id
                WHERE a.country = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(
                        rs.getString("first_name") + " " +
                                rs.getString("last_name") + " - " +
                                rs.getString("name")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<String> getMaxPrice() {
        List<String> result = new ArrayList<>();

        String sql = """
                SELECT a.first_name, a.last_name, b.name, b.price
                FROM books b
                JOIN authors a ON b.author_id = a.id
                WHERE b.price = (SELECT MAX(price) FROM books)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(
                        rs.getString("first_name") + " " +
                                rs.getString("last_name") + " - " +
                                rs.getString("name") + " : " +
                                rs.getInt("price")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<String> sumBookByAuthorId(Long id) {
        List<String> result = new ArrayList<>();

        String sql = """
                SELECT a.first_name,
                       a.last_name,
                       SUM(b.price) AS total_sum
                FROM books b
                JOIN authors a ON b.author_id = a.id
                WHERE a.id = ?
                GROUP BY a.id
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String info =
                        rs.getString("first_name") + " " +
                                rs.getString("last_name") + " - " +
                                rs.getInt("total_sum");

                result.add(info);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}

