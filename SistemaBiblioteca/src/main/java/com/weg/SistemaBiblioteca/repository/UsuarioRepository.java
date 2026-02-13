package com.weg.SistemaBiblioteca.repository;

import com.weg.SistemaBiblioteca.dao.Conexao;
import com.weg.SistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    public Usuario cadastroUsuario(Usuario usuario) throws SQLException {

        String query = """
                INSERT INTO usuario
                (nome, email)
                VALUES
                (?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                usuario.setId(rs.getLong(1));
            }
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        String query = """
                SELECT   id
                        ,nome
                        ,email
                FROM usuario
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }
        }
        return usuarios;
    }

    public Usuario buscaPorId(Long id) throws SQLException {

        String query = """
                SELECT   id
                        ,nome
                        ,email
                FROM usuario
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                );
            }
        }
        return null;
    }

    public void atualizaUsuario(Usuario usuario) throws SQLException {

        String query = """
                UPDATE usuario
                SET nome = ?, email = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaUsuario(long id) throws SQLException {

        String query = """
                DELETE FROM usuario
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
}
