package com.example.biblioteca_spring.controller;

import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import com.example.biblioteca_spring.service.EmprestimoService;
import com.example.biblioteca_spring.service.EmprestimoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BibliotecaController {

    private final LivroRepository livroRepository;
    private final EmprestimoService emprestimoService;

    @Autowired
    public BibliotecaController(LivroRepository livroRepository, EmprestimoService emprestimoService) {
        this.livroRepository = livroRepository;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarLivrosDisponiveis() {
        List<Livro> livros = livroRepository.findByDisponivelTrue();
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/emprestar")
    public ResponseEntity<String> emprestarLivro(@RequestParam String titulo) {
        EmprestimoStatus status = emprestimoService.emprestarLivro(titulo);

        return switch (status) {
            case SUCESSO -> ResponseEntity.ok("Livro emprestado: " + titulo);
            case LIVRO_NAO_ENCONTRADO -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Livro não encontrado.");
            case LIVRO_JA_EMPRESTADO -> ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Livro já foi emprestado.");
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        };
    }

    @PostMapping("/devolver")
    public ResponseEntity<String> devolverLivro(@RequestParam String titulo) {
        EmprestimoStatus status = emprestimoService.devolverLivro(titulo);

        return switch (status) {
            case SUCESSO -> ResponseEntity.ok("Livro devolvido: " + titulo);
            case LIVRO_NAO_ENCONTRADO -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Livro não encontrado.");
            case LIVRO_JA_DEVOLVIDO -> ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Livro já foi devolvido.");
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        };
    }
}
