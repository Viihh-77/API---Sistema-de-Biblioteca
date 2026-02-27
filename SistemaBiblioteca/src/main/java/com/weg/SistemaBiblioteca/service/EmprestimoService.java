package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.dto.emprestimoDto.EmprestimoRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.emprestimoDto.EmprestimoRespostaDto;
import com.weg.SistemaBiblioteca.mapper.emprestimoMapper.EmprestimoMapper;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    public EmprestimoRespostaDto registroEmprestimo(EmprestimoRequisicaoDto emprestimoRequisicaoDto) throws SQLException {
        Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequisicaoDto);
        boolean emprestado = emprestimoRepository.livroJaEmprestado(emprestimo.getLivroId());

        if (emprestado) {
            throw new RuntimeException("Livro já emprestado!");
        }

        return emprestimoMapper.paraRespostaDto(emprestimoRepository.registroEmprestimo(emprestimo));
    }

    public List<EmprestimoRespostaDto> listarEmprestimos() throws SQLException {
        List<Emprestimo> emprestimos = emprestimoRepository.listarEmprestimo();
        return emprestimoMapper.paraListaResposta(emprestimos);
    }

    public EmprestimoRespostaDto buscaPorId(long id) throws SQLException {
        Emprestimo emprestimo = emprestimoRepository.buscaPorId(id);
        return emprestimoMapper.paraRespostaDto(emprestimo);
    }

    public EmprestimoRespostaDto atualizarEmprestimo(EmprestimoRequisicaoDto emprestimoRequisicaoDto, Long id) throws SQLException {
        Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequisicaoDto);
        emprestimo.setId(id);
        emprestimoRepository.atualizarEmprestimo(emprestimo);
        return emprestimoMapper.paraRespostaDto(emprestimo);
    }

    public boolean deletaEmprestimo(Long id) throws SQLException {
        return emprestimoRepository.deletaEmprestimo(id);
    }

    public EmprestimoRespostaDto registrarDevolucao(Long id) throws SQLException {
        Emprestimo emprestimo = emprestimoRepository.buscaPorId(id);

        if (emprestimo == null) {
            throw new RuntimeException("Empréstimo não encontrado");
        }

        if (emprestimo.getDataDevolucao() != null) {
            throw new RuntimeException("Este livro já foi devolvido!");
        }

        java.sql.Date hoje = java.sql.Date.valueOf(java.time.LocalDate.now());
        emprestimoRepository.registrarDevolucao(id, hoje);
        emprestimo.setDataDevolucao(java.time.LocalDate.now());

        return emprestimoMapper.paraRespostaDto(emprestimo);
    }

    public List<EmprestimoRespostaDto> listarEmprestimosPorUsuario(Long usuarioId) throws SQLException {
        List<Emprestimo> emprestimos = emprestimoRepository.listarPorUsuario(usuarioId);
        return emprestimoMapper.paraListaResposta(emprestimos);
    }

}
