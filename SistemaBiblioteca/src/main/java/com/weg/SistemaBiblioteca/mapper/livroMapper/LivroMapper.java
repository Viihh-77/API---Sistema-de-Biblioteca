package com.weg.SistemaBiblioteca.mapper.livroMapper;

import com.weg.SistemaBiblioteca.dto.livroDto.LivroRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.livroDto.LivroRespostaDto;
import com.weg.SistemaBiblioteca.model.Livro;

public class LivroMapper {

    public Livro paraEntidade(LivroRequisicaoDto livroRequisicaoDto) {
        return new Livro(
                livroRequisicaoDto.autor(),
                livroRequisicaoDto.titulo(),
                livroRequisicaoDto.anoPublicacao()
        );
    }

    public LivroRespostaDto paraRespostaDto(Livro livro) {
        return new LivroRespostaDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAnoPublicacao()
        );
    }
}
