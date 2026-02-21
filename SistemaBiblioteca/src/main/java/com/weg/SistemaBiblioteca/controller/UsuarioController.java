package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.dto.usuarioDto.UsuarioRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.usuarioDto.UsuarioRespostaDto;
import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/SistemaBiblioteca")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    public UsuarioRespostaDto cadastroUsuario(@RequestBody UsuarioRequisicaoDto usuarioRequisicaoDto) {

        try {
            return usuarioService.cadastroUsuario(usuarioRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public List<UsuarioRespostaDto> listarUsuarios() {

        try {
            return usuarioService.listarUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioRespostaDto buscarPorId(@PathVariable long id) {

        try {
            return usuarioService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/usuarios/{id}")
    public UsuarioRespostaDto atualizaUsuario(@PathVariable long id, @RequestBody UsuarioRequisicaoDto usuarioRequisicaoDto) {

        try {
            return usuarioService.atualizaUsuario(usuarioRequisicaoDto,id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public boolean deletaUsuario(@PathVariable long id) {

        try {
            return usuarioService.deletaUsuario(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
