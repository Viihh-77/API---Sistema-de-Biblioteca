package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.dto.livroDto.LivroRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.livroDto.LivroRespostaDto;
import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SistemaBiblioteca")
public class LivroController {

    private final LivroService livroService;
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/livros")
    public LivroRespostaDto cadastroLivro(@RequestBody LivroRequisicaoDto livroRequisicaoDto) {

        try {
            return livroService.cadastroLivro(livroRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/livros")
    public List<LivroRespostaDto> listarLivros() {

        List<Livro> livros = new ArrayList<>();

        try {
            return livroService.listarLivros();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/livros/{id}")
    public LivroRespostaDto buscaPorId(@PathVariable int id) {

        try {
            return livroService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/livros/{id}")
    public LivroRespostaDto atualizaLivro(@PathVariable int id, @RequestBody LivroRequisicaoDto livroRequisicaoDto) {

        try {
            return livroService.atualizaLivro(livroRequisicaoDto, id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/livros/{id}")
    public boolean deletaLivro(@PathVariable int id) {

        try {
            return livroService.deletaLivro(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
