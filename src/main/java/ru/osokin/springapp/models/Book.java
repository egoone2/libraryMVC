package ru.osokin.springapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(of = {"title", "author", "year"})
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private UUID UUID;
    @NonNull
    @NotEmpty(message = "Enter book name")
    @Column(name = "name")
    private String title;
    @NonNull
    @Column(name = "author")
    private String author;
    @NonNull
    @Max(value = 2022, message = "Enter correct year of writing")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person holder;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "when_taken")
    private Date whenTaken;
    @Transient
    private boolean isExpired;


}
