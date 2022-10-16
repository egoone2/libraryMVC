package ru.osokin.springapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Min(value = 1900, message = "Enter correct year of birth")
    @Max(value = 2022, message = "Enter correct year of birth")
    private int birthYear;
}
