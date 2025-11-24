package plmDay07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plmDay07.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByCode(String code);
}
