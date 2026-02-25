package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.dto.livroDto.LivroRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.livroDto.LivroRespostaDto;
import com.weg.SistemaBiblioteca.mapper.livroMapper.LivroMapper;
import com.weg.SistemaBiblioteca.mapper.usuarioMapper.UsuarioMapper;
import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public LivroRespostaDto cadastroLivro(LivroRequisicaoDto livroRequisicaoDto) throws SQLException {
        Livro livro = livroMapper.paraEntidade(livroRequisicaoDto);
        return livroMapper.paraRespostaDto(livroRepository.cadastroLivro(livro));
    }

    public List<LivroRespostaDto> listarLivros() throws SQLException {
        List<Livro> livros = livroRepository.listarLivros();
        return livroMapper.paraListaResposta(livros);
    }

    public LivroRespostaDto buscarPorId(int id) throws SQLException {
        Livro livro = livroRepository.buscarPorId(id);
        return livroMapper.paraRespostaDto(livro);
    }

    public LivroRespostaDto atualizaLivro(LivroRequisicaoDto livroRequisicaoDto, int id) throws SQLException {
        Livro livro = livroMapper.paraEntidade(livroRequisicaoDto);
        livro.setId(id);
        livroRepository.atualizaLivro(livro);
        return livroMapper.paraRespostaDto(livro);
    }

    public boolean deletaLivro(int id) throws SQLException {
        return livroRepository.deletaLivro(id);
    }
}
