package org.example.dao

import org.example.PessoaJuridica

import java.sql.*

class EmpresaDAO {

    void inserir(Connection conn, PessoaJuridica empresa) {
        try {
            def sql = """INSERT INTO Empresa(cnpj, nome_emp, email_corporativo, cep, desc_empresa, pais, senha)
VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_empresa"""
            def stmt = conn.prepareStatement(sql)
            stmt.setString(1, empresa.cnpj)
            stmt.setString(2, empresa.nome)
            stmt.setString(3, empresa.email)
            stmt.setString(4, empresa.cep)
            stmt.setString(5, empresa.descricaoEmpresa)
            stmt.setString(6, empresa.pais)
            stmt.setString(7, empresa.senha ?: "1234")

            def rs = stmt.executeQuery()
            rs.next()
            empresa.id = rs.getInt("id_empresa")
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao inserir empresa: ${e.message}"
        }
    }

    List<PessoaJuridica> listar(Connection conn) {
        List<PessoaJuridica> lista = []
        try {
            def rs = conn.createStatement().executeQuery("SELECT * FROM Empresa")
            while (rs.next()) {
                PessoaJuridica e = new PessoaJuridica(
                        rs.getString("nome_emp"),
                        rs.getString("email_corporativo"),
                        rs.getString("cep"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("desc_empresa"),
                        [],
                        rs.getString("senha")
                )
                e.id = rs.getInt("id_empresa")
                lista.add(e)
            }
        } catch (SQLException e) {
            println "Erro ao listar empresas: ${e.message}"
        }
        return lista
    }

    void atualizar(Connection conn, PessoaJuridica e) {
        try {
            def sql = "UPDATE Empresa SET nome_emp=?, email_corporativo=?, cep=?, desc_empresa=?, pais=? WHERE id_empresa=?"
            def stmt = conn.prepareStatement(sql)
            stmt.setString(1, e.nome)
            stmt.setString(2, e.email)
            stmt.setString(3, e.cep)
            stmt.setString(4, e.descricaoEmpresa)
            stmt.setString(5, e.pais)
            stmt.setInt(6, e.id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException ex) {
            println "Erro ao atualizar empresa: ${ex.message}"
        }
    }

    void deletar(Connection conn, int id) {
        try {
            def stmt = conn.prepareStatement("DELETE FROM Empresa WHERE id_empresa=?")
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao deletar empresa: ${e.message}"
        }
    }
}


