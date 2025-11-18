package plmDay06.controller;

import plmDay06.entity.Author;
import plmDay06.entity.Book;
import plmDay06.repository.AuthorRepository;
import plmDay06.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        initializeAuthors();
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute Book book,
                             @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                             RedirectAttributes redirectAttributes) {

        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> selectedAuthors = new ArrayList<>();
            for (Long authorId : authorIds) {
                authorRepository.findById(authorId).ifPresent(selectedAuthors::add);
            }
            book.setAuthors(selectedAuthors);
        }

        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("success", "Book created successfully!");
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute Book book,
                             @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                             RedirectAttributes redirectAttributes) {

        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> selectedAuthors = new ArrayList<>();
            for (Long authorId : authorIds) {
                authorRepository.findById(authorId).ifPresent(selectedAuthors::add);
            }
            book.setAuthors(selectedAuthors);
        } else {
            book.setAuthors(new ArrayList<>());
        }

        bookService.updateBook(id, book);
        redirectAttributes.addFlashAttribute("success", "Book updated successfully!");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("success", "Book deleted successfully!");
        return "redirect:/books";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalBooks", bookService.getAllBooks().size());
        model.addAttribute("totalAuthors", authorRepository.count());
        return "index";
    }

    private void initializeAuthors() {
        List<Author> authors = Arrays.asList(
                new Author(1L, "Chung Trịnh", true),
                new Author(2L, "David John", true),
                new Author(3L, "Adam Smith", true)
        );

        for (Author author : authors) {
            if (authorRepository.findByName(author.getName()) == null) {
                authorRepository.save(author);
            }
        }
    }
}