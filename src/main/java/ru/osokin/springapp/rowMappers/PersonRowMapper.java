package ru.osokin.springapp.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import ru.osokin.springapp.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("person_id"));
        person.setName(rs.getString("name"));
        person.setBirthYear(rs.getInt("birthyear"));

        return person;
    }
}
