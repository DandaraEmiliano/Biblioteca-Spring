package com.example.biblioteca_spring.service;

import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmprestimoService {

    private final LivroRepository livroRepository;

    @Autowired
    public EmprestimoService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public String emprestarLivro(String titulo) {
        Optional<Livro> livroOptional = livroRepository.findByTitulo(titulo);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            if (livro.isDisponivel()) {
                livro.setDisponivel(false);
                livroRepository.save(livro);
                return "Livro emprestado: " + titulo;
            } else {
                return "Livro já foi emprestado.";
            }
        } else {
            return "Livro não disponível ou não encontrado.";
        }
    }

    public String devolverLivro(String titulo) {
        Optional<Livro> livroOptional = livroRepository.findByTitulo(titulo);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            if (!livro.isDisponivel()) {
                livro.setDisponivel(true);
                livroRepository.save(livro);
                return "Livro devolvido: " + titulo;
            } else {
                return "Livro não está emprestado.";
            }
        } else {
            return "Livro não encontrado.";
        }
    }
}
