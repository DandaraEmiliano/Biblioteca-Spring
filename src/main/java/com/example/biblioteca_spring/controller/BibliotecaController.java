package com.example.biblioteca_spring.controller;

import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import com.example.biblioteca_spring.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Livro> listarLivrosDisponiveis() {
        return livroRepository.findByDisponivelTrue();
    }

    @PostMapping("/emprestar")
    public String emprestarLivro(@RequestParam String titulo) {
        return emprestimoService.emprestarLivro(titulo);
    }

    @PostMapping("/devolver")
    public String devolverLivro(@RequestParam String titulo) {
        return emprestimoService.devolverLivro(titulo);
    }
}
