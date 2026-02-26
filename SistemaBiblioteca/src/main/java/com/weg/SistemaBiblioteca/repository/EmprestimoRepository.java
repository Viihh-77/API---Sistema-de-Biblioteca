package com.weg.SistemaBiblioteca.repository;

import com.weg.SistemaBiblioteca.dao.Conexao;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepository {

    public Emprestimo registroEmprestimo(Emprestimo emprestimo) throws SQLException {

        String query = """
            INSERT INTO emprestimo
            (livro_id, usuario_id, data_emprestimo, data_devolucao)
            VALUES
            (?,?,?,?)
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, emprestimo.getLivroId());
            stmt.setLong(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));

            if (emprestimo.getDataDevolucao() != null) {
                stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                emprestimo.setId(rs.getLong(1));
            }
        }

        return emprestimo;
    }


    public List<Emprestimo> listarEmprestimo() throws SQLException {

        List<Emprestimo> emprestimos = new ArrayList<>();

        String query = """
            SELECT   id,
                     livro_id,
                     usuario_id,
                     data_emprestimo,
                     data_devolucao
            FROM emprestimo
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Date dataDevolucao = rs.getDate("data_devolucao");

                emprestimos.add(new Emprestimo(
                        rs.getLong("id"),
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        dataDevolucao != null ? dataDevolucao.toLocalDate() : null
                ));
            }
        }

        return emprestimos;
    }

    public Emprestimo buscaPorId(Long id) throws SQLException {

        String query = """
            SELECT   id,
                     livro_id,
                     usuario_id,
                     data_emprestimo,
                     data_devolucao
            FROM emprestimo
            WHERE id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Date dataDevolucao = rs.getDate("data_devolucao");

                return new Emprestimo(
                        rs.getLong("id"),
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        dataDevolucao != null ? dataDevolucao.toLocalDate() : null
                );
            }
        }

        return null;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {

        String query = """
                UPDATE emprestimo
                SET livro_id = ?, usuario_id = ?, data_emprestimo = ?, data_devolucao = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, emprestimo.getLivroId());
            stmt.setLong(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setLong(5, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaEmprestimo(Long id) throws SQLException {

        String query = """
                DELETE FROM emprestimo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return true;
            }
        }
        return false;
    }

    public void registrarDevolucao(Long id, Date dataDevolucao) throws SQLException {

        String query = """
            UPDATE emprestimo
            SET data_devolucao = ?
            WHERE id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, dataDevolucao);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public boolean livroJaEmprestado(Long livroId) throws SQLException {

        String query = """
            SELECT COUNT(*) 
            FROM emprestimo
            WHERE livro_id = ?
            AND data_devolucao IS NULL
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, livroId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }

        return false;
    }

    public List<Emprestimo> listarPorUsuario(Long usuarioId) throws SQLException {

        List<Emprestimo> emprestimos = new ArrayList<>();

        String query = """
            SELECT id,
                   livro_id,
                   usuario_id,
                   data_emprestimo,
                   data_devolucao
            FROM emprestimo
            WHERE usuario_id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Date dataDevolucao = rs.getDate("data_devolucao");

                emprestimos.add(new Emprestimo(
                        rs.getLong("id"),
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        dataDevolucao != null ? dataDevolucao.toLocalDate() : null
                ));
            }
        }

        return emprestimos;
    }

}
