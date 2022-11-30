package ru.osokin.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.osokin.springapp.models.Book;
import ru.osokin.springapp.models.Person;
import ru.osokin.springapp.services.BooksService;
import ru.osokin.springapp.services.PeopleService;
import ru.osokin.springapp.util.PersonValidator;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/people")
@Controller
public class PeopleController {
    private final PeopleService peopleService;
    private final BooksService booksService;
    private final PersonValidator validator;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService, PersonValidator validator) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.validator = validator;
    }

    @GetMapping()
    public String index(@RequestParam(value = "order", required = false) String order, Model model) {
        System.out.println(order);
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        List<Book> checkedBookList = booksService.checkBookListIfExpired(peopleService.findBooksByPersonId(id));
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", checkedBookList);
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }


}
