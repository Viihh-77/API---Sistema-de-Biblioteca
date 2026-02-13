package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SistemaBiblioteca")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    public Usuario cadastroUsuario(@RequestBody Usuario usuario) {

        try {
            return usuario = usuarioService.cadastroUsuario(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        try {
            return usuarios = usuarioService.listarUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuarios/{id}")
    public Usuario buscarPorId(@PathVariable long id) {

        try {
            return usuarioService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/usuarios/{id}")
    public Usuario atualizaUsuario(@PathVariable long id, @RequestBody Usuario usuario) {

        try {
            return usuarioService.atualizaUsuario(usuario,id);
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
