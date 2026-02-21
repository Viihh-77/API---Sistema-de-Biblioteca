package com.weg.SistemaBiblioteca.mapper.usuarioMapper;

import com.weg.SistemaBiblioteca.dto.usuarioDto.UsuarioRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.usuarioDto.UsuarioRespostaDto;
import com.weg.SistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    public Usuario paraEntidade(UsuarioRequisicaoDto usuarioRequisicaoDto) {
        return new Usuario(
                usuarioRequisicaoDto.nome(),
                usuarioRequisicaoDto.email()
        );
    }

    public UsuarioRespostaDto paraRespostaDto(Usuario usuario) {
        return new UsuarioRespostaDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public List<UsuarioRespostaDto> paraListaRespostaDto(List<Usuario> usuarios) {
        List<UsuarioRespostaDto> listaResposta = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            listaResposta.add(paraRespostaDto(usuario));
        }
        return listaResposta;
    }
}
