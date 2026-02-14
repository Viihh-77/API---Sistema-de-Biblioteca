package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo registroEmprestimo(Emprestimo emprestimo) throws SQLException {

        boolean emprestado = emprestimoRepository.livroJaEmprestado(emprestimo.getLivroId());

        if (emprestado) {
            throw new RuntimeException("Livro já emprestado!");
        }

        return emprestimoRepository.registroEmprestimo(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        return emprestimoRepository.listarEmprestimo();
    }

    public Emprestimo buscaPorId(long id) throws SQLException {
        return emprestimoRepository.buscaPorId(id);
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo, Long id) throws SQLException {
        emprestimo.setId(id);
        emprestimoRepository.atualizarEmprestimo(emprestimo, id);

        return emprestimo;
    }

    public boolean deletaEmprestimo(Long id) throws SQLException {
        return emprestimoRepository.deletaEmprestimo(id);
    }

    public Emprestimo registrarDevolucao(Long id) throws SQLException {

        Emprestimo emprestimo = emprestimoRepository.buscaPorId(id);

        if (emprestimo == null) {
            throw new RuntimeException("Empréstimo não encontrado");
        }

        java.sql.Date hoje = java.sql.Date.valueOf(java.time.LocalDate.now());

        emprestimoRepository.registrarDevolucao(id, hoje);

        emprestimo.setDataDevolucao(java.time.LocalDate.now());

        return emprestimo;
    }

    public List<Emprestimo> listarEmprestimosPorUsuario(Long usuarioId) throws SQLException {

        return emprestimoRepository.listarPorUsuario(usuarioId);
    }

}
