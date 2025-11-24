package plmDay07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plmDay07.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> { }
