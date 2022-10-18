package ru.osokin.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.osokin.springapp.models.Book;
import ru.osokin.springapp.models.Person;
import ru.osokin.springapp.rowMappers.BookRowMapper;
import ru.osokin.springapp.rowMappers.PersonRowMapper;

import java.util.List;
import java.util.Optional;

@Component
public class    BookDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from Book WHERE book_id=?", new Object[]{id}, new BookRowMapper())
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getOwner(int bookId) {
        return jdbcTemplate.query("select person.person_id, name, birthyear from person join book b on person.person_id = b.person_id where book_id = ?",
                new Object[]{bookId}, new PersonRowMapper()).stream().findAny();
    }

    public void addOwner(int bookId, Person owner) {
        jdbcTemplate.update("UPDATE Book SET person_id=? where book_id=?", bookId, owner.getId());
    }

    public void removeOwner(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", bookId);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT into Book(book_name, book_author, year) values(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book set book_name=?, book_author=?, year=? where book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Book where book_id=?", id);
    }

}
