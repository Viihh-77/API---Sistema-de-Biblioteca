package com.weg.SistemaBiblioteca.model;

import java.time.LocalDate;

public class Emprestimo {

    private long id;
    private long livroId;
    private long usuarioId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(long id, long livroId, long usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(long livroId, long usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public long getId() {
        return id;
    }

    public long getLivroId() {
        return livroId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLivroId(long livroId) {
        this.livroId = livroId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
