package com.weg.SistemaBiblioteca.dto.emprestimoDto;

import java.time.LocalDate;

public record EmprestimoRespostaDto(
        long id,
        long livroId,
        long usuarioId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
