package org.example.dao

import org.example.Vaga
import java.sql.*

class VagaDAO {

    void inserir(Connection conn, Vaga vaga) {
        try {
            def stmt = conn.prepareStatement("""
                INSERT INTO Vaga(id_empresa, nome_vaga, desc_vaga, localizacao)
                VALUES (?, ?, ?, ?) RETURNING id_vaga
            """)
            stmt.setInt(1, vaga.idEmpresa)
            stmt.setString(2, vaga.nomeVaga)
            stmt.setString(3, vaga.descricaoVaga)
            stmt.setString(4, vaga.localizacao ?: "Remoto") // Usando "Remoto" como padrão
            def rs = stmt.executeQuery()
            rs.next()
            vaga.id = rs.getInt("id_vaga")
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao inserir vaga: ${e.message}"
        }
    }

    List<Vaga> listar(Connection conn) {
        List<Vaga> lista = []
        try {
            def rs = conn.createStatement().executeQuery("SELECT * FROM Vaga")
            while (rs.next()) {
                Vaga v = new Vaga(
                        rs.getString("nome_vaga"),
                        rs.getString("desc_vaga"),
                        [],  // Você pode ajustar isso para buscar competências associadas, caso necessário
                        rs.getInt("id_empresa")
                )
                v.id = rs.getInt("id_vaga")
                lista.add(v)
            }
        } catch (SQLException e) {
            println "Erro ao listar vagas: ${e.message}"
        }
        return lista
    }

    void atualizar(Connection conn, Vaga v) {
        try {
            def stmt = conn.prepareStatement("""
                UPDATE Vaga 
                SET nome_vaga=?, desc_vaga=?, localizacao=? 
                WHERE id_vaga=?
            """)
            stmt.setString(1, v.nomeVaga)
            stmt.setString(2, v.descricaoVaga)
            stmt.setString(3, v.localizacao ?: "Remoto")
            stmt.setInt(4, v.id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao atualizar vaga: ${e.message}"
        }
    }

    void deletar(Connection conn, int id) {
        try {
            def stmt = conn.prepareStatement("DELETE FROM Vaga WHERE id_vaga=?")
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao deletar vaga: ${e.message}"
        }
    }
}



