package org.example.model

import org.example.model.dao.CompetenciaDAO
import java.sql.*

class CandidatoDAO {

    void inserir(Connection conn, Candidato candidato) {
        try {
            def sql = """INSERT INTO Candidato(nome, sobrenome, cpf, email, pais, cep, data_nasc, desc_pessoal, senha)
                         VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_candidato"""
            def stmt = conn.prepareStatement(sql)
            stmt.setString(1, candidato.nome)
            stmt.setString(2, candidato.sobrenome)
            stmt.setString(3, candidato.cpf)
            stmt.setString(4, candidato.email)
            stmt.setString(5, candidato.pais)
            stmt.setString(6, candidato.cep)
            stmt.setDate(7, java.sql.Date.valueOf(candidato.dataNasc ?: java.time.LocalDate.now()))
            stmt.setString(8, candidato.descricaoPessoal)
            stmt.setString(9, candidato.senha ?: "1234")

            def rs = stmt.executeQuery()
            rs.next()
            candidato.id = rs.getInt("id_candidato")
            stmt.close()

            def compDao = new CompetenciaDAO()
            candidato.competencias.each { comp ->
                int compId = compDao.buscarOuInserir(conn, comp)
                def stmtRel = conn.prepareStatement(
                        "INSERT INTO Candidato_competencia(id_candidato, id_competencia) VALUES (?, ?)"
                )
                stmtRel.setInt(1, candidato.id)
                stmtRel.setInt(2, compId)
                stmtRel.executeUpdate()
                stmtRel.close()
            }

        } catch (SQLException e) {
            println "Erro ao inserir candidato: ${e.message}"
        }
    }

    List<Candidato> listar(Connection conn) {
        List<Candidato> lista = []
        try {
            def rs = conn.createStatement().executeQuery("SELECT * FROM Candidato")
            while (rs.next()) {
                Candidato c = new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("cpf"),
                        rs.getString("desc_pessoal"),
                        [],
                        rs.getDate("data_nasc")?.toLocalDate(),
                        rs.getString("senha")
                )
                c.id = rs.getInt("id_candidato")
                lista.add(c)
            }
        } catch (SQLException e) {
            println "Erro ao listar candidatos: ${e.message}"
        }
        return lista
    }

    void atualizar(Connection conn, Candidato c) {
        try {
            def sql = "UPDATE Candidato SET nome=?, email=?, cep=?, desc_pessoal=? WHERE id_candidato=?"
            def stmt = conn.prepareStatement(sql)
            stmt.setString(1, c.nome)
            stmt.setString(2, c.email)
            stmt.setString(3, c.cep)
            stmt.setString(4, c.descricaoPessoal)
            stmt.setInt(5, c.id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao atualizar candidato: ${e.message}"
        }
    }

    void deletar(Connection conn, int id) {
        try {
            def stmt = conn.prepareStatement("DELETE FROM Candidato WHERE id_candidato=?")
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao deletar candidato: ${e.message}"
        }
    }

    Candidato buscarPorId(Connection conn, int id) {
        Candidato candidato = null
        try {
            String sql = "SELECT * FROM Candidato WHERE id_candidato = ?"
            PreparedStatement stmt = conn.prepareStatement(sql)
            stmt.setInt(1, id)
            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                candidato = new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("cpf"),
                        rs.getString("desc_pessoal"),
                        [],
                        rs.getDate("data_nasc")?.toLocalDate(),
                        rs.getString("senha")
                )
                candidato.id = rs.getInt("id_candidato")
            }

        } catch (SQLException e) {
            println "Erro ao buscar candidato por ID: ${e.message}"
        }
        return candidato
    }
}
