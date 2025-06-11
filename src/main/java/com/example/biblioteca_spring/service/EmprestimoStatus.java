package com.example.biblioteca_spring.service;

/**
 * Representa o resultado de uma operação de empréstimo ou devolução de livro.
 */
public enum EmprestimoStatus {
    SUCESSO,
    LIVRO_NAO_ENCONTRADO,
    LIVRO_JA_EMPRESTADO,
    LIVRO_JA_DEVOLVIDO
}
