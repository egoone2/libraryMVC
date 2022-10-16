package ru.osokin.springapp.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import ru.osokin.springapp.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("book_id"));
        book.setAuthor(rs.getString("book_author"));
        book.setYear(rs.getInt("year"));
        book.setTitle(rs.getString("book_name"));

        return book;
    }
}
