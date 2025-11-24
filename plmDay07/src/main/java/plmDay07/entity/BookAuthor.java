package plmDay07.entity;

import jakarta.persistence.*;

@Entity
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private Boolean isMainAuthor;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
    public Boolean getIsMainAuthor() { return isMainAuthor; }
    public void setIsMainAuthor(Boolean isMainAuthor) { this.isMainAuthor = isMainAuthor; }
}
