package com.example.biblioteca_spring.service;

import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço responsável por emprestar e devolver livros.
 */

@Service
public class EmprestimoService {

    private final LivroRepository livroRepository;

    @Autowired
    public EmprestimoService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    /**
     * Tenta emprestar um livro pelo título.
     */
    public EmprestimoStatus emprestarLivro(String titulo) {
        Optional<Livro> livroOptional = livroRepository.findByTitulo(titulo);

        if (livroOptional.isEmpty()) {
            return EmprestimoStatus.LIVRO_NAO_ENCONTRADO;
        }

        Livro livro = livroOptional.get();
        if (!livro.isDisponivel()) {
            return EmprestimoStatus.LIVRO_JA_EMPRESTADO;
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);
        return EmprestimoStatus.SUCESSO;
    }

    /**
     * Tenta devolver um livro pelo título.
     */
    public EmprestimoStatus devolverLivro(String titulo) {
        Optional<Livro> livroOptional = livroRepository.findByTitulo(titulo);

        if (livroOptional.isEmpty()) {
            return EmprestimoStatus.LIVRO_NAO_ENCONTRADO;
        }

        Livro livro = livroOptional.get();
        if (livro.isDisponivel()) {
            return EmprestimoStatus.LIVRO_JA_DEVOLVIDO;
        }

        livro.setDisponivel(true);
        livroRepository.save(livro);
        return EmprestimoStatus.SUCESSO;
    }
}
