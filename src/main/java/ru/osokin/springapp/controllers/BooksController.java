package ru.osokin.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.osokin.springapp.dao.BookDAO;
import ru.osokin.springapp.dao.PersonDAO;
import ru.osokin.springapp.models.Book;
import ru.osokin.springapp.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books",bookDAO.index());
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.show(id);
        Person person = bookDAO.getOwner(id).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("person", person);
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.removeOwner(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/addOwner")
    public String assign(@PathVariable("id") int id, Person person) {
        bookDAO.addOwner(id, person);
        return "redirect:books/{id}";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/books/new";
        bookDAO.create(book);
        return "redirect:/books";
    }

}
