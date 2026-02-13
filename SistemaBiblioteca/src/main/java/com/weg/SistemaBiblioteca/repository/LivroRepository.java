package com.weg.SistemaBiblioteca.repository;

import com.weg.SistemaBiblioteca.dao.Conexao;
import com.weg.SistemaBiblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {

    public Livro cadastroLivro(Livro livro) throws SQLException {

        String query = """
                INSERT INTO livro
                (titulo, autor, ano_publicacao)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                livro.setId(rs.getInt(1));
            }
        }
        return livro;
    }

    public List<Livro> listarLivros() throws SQLException {

        List<Livro> livros = new ArrayList<>();

        String query = """
                SELECT  id
                        ,titulo
                        ,autor
                        ,ano_publicacao
                FROM livro
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao")
                ));
            }
        }
        return livros;
    }

    public Livro buscarPorId(int id) throws SQLException {

        String query = """
                SELECT  id
                        ,titulo
                        ,autor
                        ,ano_publicacao
                FROM livro
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao")
                );
            }
        }
        return null;
    }

    public void atualizaLivro(Livro livro) throws SQLException {

        String query = """
                UPDATE livro   
                SET titulo = ?, autor = ?, ano_publicacao = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaLivro(int id) throws SQLException {

        String query = """
                DELETE FROM livro 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return true;
            }
        }
        return false;
    }

}
