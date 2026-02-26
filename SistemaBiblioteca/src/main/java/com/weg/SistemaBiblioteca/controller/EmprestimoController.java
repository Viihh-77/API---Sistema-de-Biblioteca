package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.dto.emprestimoDto.EmprestimoRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.emprestimoDto.EmprestimoRespostaDto;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SistemaBiblioteca")

public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/emprestimos")
    public EmprestimoRespostaDto registroEmprestimo(@RequestBody EmprestimoRequisicaoDto emprestimoRequisicaoDto) {

        try {
            return emprestimoService.registroEmprestimo(emprestimoRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/emprestimos")
    public List<EmprestimoRespostaDto> listarEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        try {
            return emprestimoService.listarEmprestimos();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/emprestimos/{id}")
    public EmprestimoRespostaDto buscaPorId(@PathVariable Long id) {

        try {
            return emprestimoService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/emprestimos/{id}")
    public EmprestimoRespostaDto atualizarEmprestimo(@RequestBody EmprestimoRequisicaoDto emprestimoRequisicaoDto, @PathVariable Long id) {

        try {
            return emprestimoService.atualizarEmprestimo(emprestimoRequisicaoDto, id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/emprestimos/{id}")
    public boolean deletaEmprestimo(@PathVariable Long id) {

        try {
            return emprestimoService.deletaEmprestimo(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/emprestimos/{id}/devolucao")
    public EmprestimoRespostaDto registrarDevolucao(@PathVariable Long id) {

        try {
            return emprestimoService.registrarDevolucao(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuarios/{id}/emprestimos")
    public List<EmprestimoRespostaDto> listarPorUsuario(@PathVariable Long id) {

        List<Emprestimo> emprestimos = new ArrayList<>();

        try {
            return emprestimoService.listarEmprestimosPorUsuario(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
