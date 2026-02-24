package com.weg.SistemaBiblioteca.mapper.livroMapper;

import com.weg.SistemaBiblioteca.dto.livroDto.LivroRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.livroDto.LivroRespostaDto;
import com.weg.SistemaBiblioteca.model.Livro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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

    public List<LivroRespostaDto> paraListaResposta(List<Livro> livros) {
        List<LivroRespostaDto> listaResposta = new ArrayList<>();

        for (Livro livro : livros) {
            listaResposta.add(paraRespostaDto(livro));
        }
        return listaResposta;
    }
}
