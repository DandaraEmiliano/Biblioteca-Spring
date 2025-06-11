package com.example.biblioteca_spring.repository;

import com.example.biblioteca_spring.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloIgnoreCase(String titulo);

    List<Livro> findByDisponivelTrue();
}
