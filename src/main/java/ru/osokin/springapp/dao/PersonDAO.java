package ru.osokin.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.osokin.springapp.models.Book;
import ru.osokin.springapp.models.Person;
import ru.osokin.springapp.rowMappers.BookRowMapper;
import ru.osokin.springapp.rowMappers.PersonRowMapper;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",new PersonRowMapper());
    }

    public List<Book> getBookList(int id) {
        return jdbcTemplate.query("select * from book where person_id=?",new Object[]{id},
                new BookRowMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person where person_id=?",new Object[]{id}, new PersonRowMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, birthYear) VALUES (?, ?)",
                person.getName(), person.getBirthYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person set name=?, birthYear=? where person_id=?",
                updatedPerson.getName(), updatedPerson.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Person where person_id=?",id);
    }
}
