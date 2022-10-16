package ru.osokin.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.osokin.springapp.models.Book;
import ru.osokin.springapp.rowMappers.BookRowMapper;

import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT into Book(book_name, book_author, year) values(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

}
