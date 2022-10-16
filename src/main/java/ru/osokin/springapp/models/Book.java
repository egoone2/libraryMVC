package ru.osokin.springapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Book {
    private int id;
    @NotEmpty(message = "Enter book name")
    private String title;
    private String author;
    @Max(value = 2022, message = "Enter correct year of writing")
    private int year;

}
