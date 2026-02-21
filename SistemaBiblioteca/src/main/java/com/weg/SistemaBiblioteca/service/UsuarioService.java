package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.dto.usuarioDto.UsuarioRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.usuarioDto.UsuarioRespostaDto;
import com.weg.SistemaBiblioteca.mapper.usuarioMapper.UsuarioMapper;
import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioRespostaDto cadastroUsuario(UsuarioRequisicaoDto usuarioRequisicaoDto) throws SQLException {
        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequisicaoDto);
        return usuarioMapper.paraRespostaDto(usuarioRepository.cadastroUsuario(usuario));
    }

    public List<UsuarioRespostaDto> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        return usuarioMapper.paraListaRespostaDto(usuarios);
    }

    public UsuarioRespostaDto buscaPorId(long id) throws SQLException {
        Usuario usuario = usuarioRepository.buscaPorId(id);
        return usuarioMapper.paraRespostaDto(usuario);
    }

    public UsuarioRespostaDto atualizaUsuario(UsuarioRequisicaoDto usuarioRequisicaoDto, long id) throws SQLException {
        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequisicaoDto);
        usuario.setId(id);
        usuarioRepository.atualizaUsuario(usuario);
        return usuarioMapper.paraRespostaDto(usuario);
    }

    public boolean deletaUsuario(long id) throws SQLException {
        return usuarioRepository.deletaUsuario(id);
    }
}
