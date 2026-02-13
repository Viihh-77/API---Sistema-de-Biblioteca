package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastroLivro(Livro livro) throws SQLException {
        return livroRepository.cadastroLivro(livro);
    }

    public List<Livro> listarLivros() throws SQLException {
        return livroRepository.listarLivros();
    }

    public Livro buscarPorId(int id) throws SQLException {
        return livroRepository.buscarPorId(id);
    }

    public Livro atualizaLivro(Livro livro, int id) throws SQLException {
        livro.setId(id);
        livroRepository.atualizaLivro(livro);

        return livro;
    }

    public boolean deletaLivro(int id) throws SQLException {
        return livroRepository.deletaLivro(id);
    }
}
