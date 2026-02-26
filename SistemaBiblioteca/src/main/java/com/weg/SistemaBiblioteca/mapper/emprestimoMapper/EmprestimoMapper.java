package com.weg.SistemaBiblioteca.mapper.emprestimoMapper;

import com.weg.SistemaBiblioteca.dto.emprestimoDto.EmprestimoRequisicaoDto;
import com.weg.SistemaBiblioteca.dto.emprestimoDto.EmprestimoRespostaDto;
import com.weg.SistemaBiblioteca.dto.livroDto.LivroRespostaDto;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmprestimoMapper {

    public Emprestimo paraEntidade(EmprestimoRequisicaoDto emprestimoRequisicaoDto) {
        return new Emprestimo(
                emprestimoRequisicaoDto.livroId(),
                emprestimoRequisicaoDto.usuarioId(),
                emprestimoRequisicaoDto.dataEmprestimo(),
                emprestimoRequisicaoDto.dataDevolucao()
        );
    }

    public EmprestimoRespostaDto paraRespostaDto(Emprestimo emprestimo) {
        return new EmprestimoRespostaDto(
                emprestimo.getId(),
                emprestimo.getLivroId(),
                emprestimo.getUsuarioId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }

    public List<EmprestimoRespostaDto> paraListaResposta(List<Emprestimo> emprestimos) {
        List<EmprestimoRespostaDto> listaResposta = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            listaResposta.add(paraRespostaDto(emprestimo));
        }
        return listaResposta;
    }
}
