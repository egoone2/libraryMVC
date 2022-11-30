package ru.osokin.springapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @NonNull
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Name should be in this format: \"Surname Name\"")
    @Column(name = "name")
    private String name;
    @NonNull
    @Min(value = 1900, message = "Enter correct year of birth")
    @Max(value = 2022, message = "Enter correct year of birth")
    @Column(name = "birthYear")
    private int birthYear;
    @OneToMany(mappedBy = "holder", cascade = CascadeType.REMOVE)
    private List<Book> books;
}
