package com.example.biblioteca_spring;

import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import com.example.biblioteca_spring.service.EmprestimoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestimoServiceTest {

    private LivroRepository livroRepository;
    private EmprestimoService emprestimoService;

    @BeforeEach
    void setUp() {
        livroRepository = Mockito.mock(LivroRepository.class);
        emprestimoService = new EmprestimoService(livroRepository);
    }

    @Test
    void deveDevolverLivroComSucesso() {
        String titulo = "O Senhor dos Anéis";
        Livro livro = new Livro(titulo, "Autor");
        livro.setDisponivel(false);
        when(livroRepository.findByTitulo(titulo)).thenReturn(Optional.of(livro));

        String resultado = emprestimoService.devolverLivro(titulo);

        assertEquals("Livro devolvido: " + titulo, resultado);
        assertTrue(livro.isDisponivel());
        verify(livroRepository).save(livro);
    }

    @Test
    void deveInformarQuandoLivroNaoEncontrado() {
        String titulo = "Desconhecido";
        when(livroRepository.findByTitulo(titulo)).thenReturn(Optional.empty());

        String resultado = emprestimoService.devolverLivro(titulo);

        assertEquals("Livro não encontrado.", resultado);
        verify(livroRepository, never()).save(any());
    }

    @Test
    void deveInformarQuandoLivroNaoEstavaEmprestado() {
        String titulo = "O Hobbit";
        Livro livro = new Livro(titulo, "Autor");
        livro.setDisponivel(true);
        when(livroRepository.findByTitulo(titulo)).thenReturn(Optional.of(livro));

        String resultado = emprestimoService.devolverLivro(titulo);

        assertEquals("Livro não estava emprestado.", resultado);
        verify(livroRepository, never()).save(any());
    }
}
