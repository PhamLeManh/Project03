package plmDay07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plmDay07.entity.BookAuthor;
import org.springframework.transaction.annotation.Transactional;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    @Transactional
    void deleteByBookId(Long bookId);
}
