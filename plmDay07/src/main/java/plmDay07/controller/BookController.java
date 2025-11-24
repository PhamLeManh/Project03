package plmDay07.controller;

import plmDay07.entity.*;
import plmDay07.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public BookController(BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @GetMapping
    public String bookList(Model model) {
        try {
            List<Book> books = bookRepository.findAll();
            model.addAttribute("books", books);
            return "book/list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách sách: " + e.getMessage());
            return "book/list";
        }
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());

        // Lấy danh sách tác giả từ database
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            // Nếu chưa có tác giả, tạo dữ liệu mẫu
            authors = createSampleAuthors();
        }
        model.addAttribute("authors", authors);

        return "book/add";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book,
                           @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                           @RequestParam(value = "isMainAuthor", required = false) List<Boolean> isMainAuthor,
                           RedirectAttributes redirectAttributes) {

        try {
            // Validate book code uniqueness
            if (book.getId() == null && bookRepository.existsByCode(book.getCode())) {
                redirectAttributes.addFlashAttribute("error", "Mã sách đã tồn tại!");
                return "redirect:/books/add";
            }

            // Set default status if null
            if (book.getStatus() == null) {
                book.setStatus(true);
            }

            // Save book first
            Book savedBook = bookRepository.save(book);

            // Add authors if provided
            if (authorIds != null && !authorIds.isEmpty()) {
                // Clear existing authors
                bookAuthorRepository.deleteByBookId(savedBook.getId());

                // Add new authors
                for (int i = 0; i < authorIds.size(); i++) {
                    Author author = authorRepository.findById(authorIds.get(i)).orElse(null);
                    if (author != null) {
                        BookAuthor bookAuthor = new BookAuthor();
                        bookAuthor.setBook(savedBook);
                        bookAuthor.setAuthor(author);
                        bookAuthor.setIsMainAuthor(isMainAuthor != null && i < isMainAuthor.size() ? isMainAuthor.get(i) : false);
                        bookAuthorRepository.save(bookAuthor);
                    }
                }
            }

            redirectAttributes.addFlashAttribute("success", "Thêm sách thành công!");
            return "redirect:/books";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm sách: " + e.getMessage());
            return "redirect:/books/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                model.addAttribute("book", book.get());
                model.addAttribute("authors", authorRepository.findAll());
                return "book/edit";
            } else {
                return "redirect:/books";
            }
        } catch (Exception e) {
            return "redirect:/books";
        }
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute Book book,
                             @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                             @RequestParam(value = "isMainAuthor", required = false) List<Boolean> isMainAuthor,
                             RedirectAttributes redirectAttributes) {

        try {
            book.setId(id);
            Book existingBook = bookRepository.findById(id).orElse(null);

            if (existingBook != null && !existingBook.getCode().equals(book.getCode())) {
                if (bookRepository.existsByCode(book.getCode())) {
                    redirectAttributes.addFlashAttribute("error", "Mã sách đã tồn tại!");
                    return "redirect:/books/edit/" + id;
                }
            }

            // Set default status if null
            if (book.getStatus() == null) {
                book.setStatus(true);
            }

            Book savedBook = bookRepository.save(book);

            // Update authors if provided
            if (authorIds != null && !authorIds.isEmpty()) {
                bookAuthorRepository.deleteByBookId(savedBook.getId());

                for (int i = 0; i < authorIds.size(); i++) {
                    Author author = authorRepository.findById(authorIds.get(i)).orElse(null);
                    if (author != null) {
                        BookAuthor bookAuthor = new BookAuthor();
                        bookAuthor.setBook(savedBook);
                        bookAuthor.setAuthor(author);
                        bookAuthor.setIsMainAuthor(isMainAuthor != null && i < isMainAuthor.size() ? isMainAuthor.get(i) : false);
                        bookAuthorRepository.save(bookAuthor);
                    }
                }
            }

            redirectAttributes.addFlashAttribute("success", "Cập nhật sách thành công!");
            return "redirect:/books";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật sách: " + e.getMessage());
            return "redirect:/books/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa sách: " + e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/reset-data")
    public String resetData(RedirectAttributes redirectAttributes) {
        try {
            // Xóa dữ liệu cũ
            bookAuthorRepository.deleteAll();
            bookRepository.deleteAll();
            authorRepository.deleteAll();

            // Thêm dữ liệu mẫu
            createSampleData();

            redirectAttributes.addFlashAttribute("success", "Đã reset dữ liệu mẫu thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi reset dữ liệu: " + e.getMessage());
        }
        return "redirect:/books";
    }

    private List<Author> createSampleAuthors() {
        List<Author> authors = new ArrayList<>();

        Author author1 = new Author();
        author1.setName("Chung Trinh");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("David John");
        authorRepository.save(author2);

        Author author3 = new Author();
        author3.setName("Adam Smith");
        authorRepository.save(author3);

        Author author4 = new Author();
        author4.setName("devmaster");
        authorRepository.save(author4);

        Author author5 = new Author();
        author5.setName("Nguyễn Ánh");
        authorRepository.save(author5);

        return authorRepository.findAll();
    }

    private void createSampleData() {
        // Tạo authors
        Author author1 = new Author();
        author1.setName("Chung Trinh");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("David John");
        authorRepository.save(author2);

        Author author3 = new Author();
        author3.setName("Adam Smith");
        authorRepository.save(author3);

        // Tạo books
        Book book1 = new Book();
        book1.setCode("JSB001");
        book1.setName("Java springboot 3");
        book1.setDescription("Sách hướng dẫn Spring Boot 3 từ cơ bản đến nâng cao");
        book1.setQuantity(112);
        book1.setPrice(150000.0);
        book1.setStatus(true);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setCode("JSB002");
        book2.setName("Thymeleaf Tutorial");
        book2.setDescription("Hướng dẫn sử dụng Thymeleaf template engine");
        book2.setQuantity(12);
        book2.setPrice(25000.0);
        book2.setStatus(true);
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setCode("JSB003");
        book3.setName("SpringBoot 3 Tutorial");
        book3.setDescription("Tutorial Spring Boot 3 với các tính năng mới nhất");
        book3.setQuantity(122);
        book3.setPrice(25000.0);
        book3.setStatus(true);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setCode("NET001");
        book4.setName("Asp.Net Core MVC");
        book4.setDescription("Lập trình web với ASP.NET Core MVC");
        book4.setQuantity(100);
        book4.setPrice(200000.0);
        book4.setStatus(true);
        bookRepository.save(book4);

        Book book5 = new Book();
        book5.setCode("NET002");
        book5.setName("Web API NetCore");
        book5.setDescription("Xây dựng Web API với .NET Core");
        book5.setQuantity(155);
        book5.setPrice(255500.0);
        book5.setStatus(true);
        bookRepository.save(book5);

        // Tạo liên kết book-authors
        createBookAuthor(book1, author1, true);  // Chung Trinh - chủ biên
        createBookAuthor(book1, author2, false); // David John - đồng tác giả

        createBookAuthor(book2, author1, true);  // Chung Trinh - chủ biên

        createBookAuthor(book3, author2, true);  // David John - chủ biên
        createBookAuthor(book3, author3, false); // Adam Smith - đồng tác giả

        createBookAuthor(book4, author1, true);  // Chung Trinh - chủ biên

        createBookAuthor(book5, author2, true);  // David John - chủ biên
        createBookAuthor(book5, author3, false); // Adam Smith - đồng tác giả
    }

    private void createBookAuthor(Book book, Author author, boolean isMainAuthor) {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBook(book);
        bookAuthor.setAuthor(author);
        bookAuthor.setIsMainAuthor(isMainAuthor);
        bookAuthorRepository.save(bookAuthor);
    }
}