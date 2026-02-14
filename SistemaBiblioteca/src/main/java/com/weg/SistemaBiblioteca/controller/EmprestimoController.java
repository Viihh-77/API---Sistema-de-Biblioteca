package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/SistemaBiblioteca")

public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/emprestimos")
    public Emprestimo registroEmprestimo(@RequestBody Emprestimo emprestimo) {

        try {
            return emprestimo = emprestimoService.registroEmprestimo(emprestimo);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/emprestimos")
    public List<Emprestimo> listarEmprestimos() {

        try {
            return emprestimoService.listarEmprestimos();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/emprestimos/{id}")
    public Emprestimo buscaPorId(@PathVariable Long id) {

        try {
            return emprestimoService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/emprestimos/{id}")
    public Emprestimo atualizarEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable Long id) {

        try {
            return emprestimoService.atualizarEmprestimo(emprestimo, id);
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
    public Emprestimo registrarDevolucao(@PathVariable Long id) {

        try {
            return emprestimoService.registrarDevolucao(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuarios/{id}/emprestimos")
    public List<Emprestimo> listarPorUsuario(@PathVariable Long id) {

        try {
            return emprestimoService.listarEmprestimosPorUsuario(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
