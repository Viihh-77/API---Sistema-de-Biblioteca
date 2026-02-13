package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastroUsuario(Usuario usuario) throws SQLException {
        return usuarioRepository.cadastroUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioRepository.listarUsuarios();
    }

    public Usuario buscaPorId(long id) throws SQLException {
        return usuarioRepository.buscaPorId(id);
    }

    public Usuario atualizaUsuario(Usuario usuario, long id) throws SQLException {
        usuario.setId(id);
        usuarioRepository.atualizaUsuario(usuario);

        return usuario;
    }

    public boolean deletaUsuario(long id) throws SQLException {
        return usuarioRepository.deletaUsuario(id);
    }
}
