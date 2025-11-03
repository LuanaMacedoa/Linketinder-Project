package org.example.dao

import java.sql.*

class CompetenciaDAO {

    void inserir(Connection conn, String nome) {
        try {
            def stmt = conn.prepareStatement("""
                INSERT INTO Competencia(nome_competencia)
                VALUES (?) ON CONFLICT (nome_competencia) DO NOTHING
            """)
            stmt.setString(1, nome)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao inserir competência: ${e.message}"
        }
    }

    Integer buscarPorNome(Connection conn, String nome) {
        Integer id = null
        try {
            def stmt = conn.prepareStatement(
                    "SELECT id_competencia FROM Competencia WHERE nome_competencia=?"
            )
            stmt.setString(1, nome)
            def rs = stmt.executeQuery()
            if (rs.next()) {
                id = rs.getInt("id_competencia")
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao buscar competência: ${e.message}"
        }
        return id
    }

    Integer buscarOuInserir(Connection conn, String nome) {
        Integer id = buscarPorNome(conn, nome)
        if (id == null) {
            inserir(conn, nome)
            id = buscarPorNome(conn, nome)
        }
        return id
    }

    void atualizar(Connection conn, int id, String novoNome) {
        try {
            def stmt = conn.prepareStatement(
                    "UPDATE Competencia SET nome_competencia=? WHERE id_competencia=?"
            )
            stmt.setString(1, novoNome)
            stmt.setInt(2, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao atualizar competência: ${e.message}"
        }
    }

    void deletar(Connection conn, int id) {
        try {
            def stmt = conn.prepareStatement("DELETE FROM Competencia WHERE id_competencia=?")
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao deletar competência: ${e.message}"
        }
    }
}

