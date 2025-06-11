import com.example.biblioteca_spring.model.Livro;
import com.example.biblioteca_spring.repository.LivroRepository;
import com.example.biblioteca_spring.service.EmprestimoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmprestimoServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void emprestarLivroDisponivel() {
        Livro livro = new Livro("Titulo", "Autor");
        when(livroRepository.findByTitulo("Titulo")).thenReturn(Optional.of(livro));

        String resultado = emprestimoService.emprestarLivro("Titulo");

        assertEquals("Livro emprestado: Titulo", resultado);
        assertFalse(livro.isDisponivel());
        verify(livroRepository).save(livro);
    }

    @Test
    void falhaAoEmprestarLivroJaEmprestado() {
        Livro livro = new Livro("Titulo", "Autor");
        livro.setDisponivel(false);
        when(livroRepository.findByTitulo("Titulo")).thenReturn(Optional.of(livro));

        String resultado = emprestimoService.emprestarLivro("Titulo");

        assertEquals("Livro já foi emprestado.", resultado);
        verify(livroRepository, never()).save(any());
    }

    @Test
    void devolverLivroEmprestado() {
        Livro livro = new Livro("Titulo", "Autor");
        livro.setDisponivel(false);
        when(livroRepository.findByTitulo("Titulo")).thenReturn(Optional.of(livro));

        String resultado = emprestimoService.devolverLivro("Titulo");

        assertEquals("Livro devolvido: Titulo", resultado);
        assertTrue(livro.isDisponivel());
        verify(livroRepository).save(livro);
    }
}
