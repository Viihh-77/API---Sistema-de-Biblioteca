package com.weg.SistemaBiblioteca.dto.emprestimoDto;

import java.time.LocalDate;

public record EmprestimoRequisicaoDto(
        long livroId,
        long usuarioId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
