package plmDay06.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean active = true;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    // Constructors
    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}