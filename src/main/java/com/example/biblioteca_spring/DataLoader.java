package com.example.biblioteca_spring;

import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final LivroRepository livroRepository;

    public DataLoader(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        livroRepository.save(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));
        livroRepository.save(new Livro("Harry Potter", "J.K. Rowling"));
        livroRepository.save(new Livro("O Hobbit", "J.R.R. Tolkien"));
    }
}
