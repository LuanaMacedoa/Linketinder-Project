package org.example.model.dao

import org.example.model.Empresa

import java.sql.*

class EmpresaDAO {

    void inserir(Connection conn, Empresa empresa) {
        try {
            def sql = """INSERT INTO empresa(cnpj, nome_emp, email_corporativo, cep, desc_empresa, pais, senha)
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

    List<Empresa> listar(Connection conn) {
        List<Empresa> lista = []
        try {
            def rs = conn.createStatement().executeQuery("SELECT * FROM empresa")
            while (rs.next()) {
                Empresa e = new Empresa(
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

    void atualizar(Connection conn, Empresa e) {
        try {
            def sql = """UPDATE empresa
                         SET nome_emp=?, email_corporativo=?, cep=?, desc_empresa=?, pais=?
                         WHERE id_empresa=?"""
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
            def stmt = conn.prepareStatement("DELETE FROM empresa WHERE id_empresa=?")
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            println "Erro ao deletar empresa: ${e.message}"
        }
    }


    void mostrarEmpresas(Connection conn) {
        try {
            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }

            List<Empresa> lista = listar(conn)
            println "\n===== LISTA DE EMPRESAS ====="
            if (lista.isEmpty()) {
                println "Nenhuma empresa cadastrada."
            } else {
                lista.each { println it }
            }
        } catch (Exception e) {
            println "Erro ao listar empresas: ${e.message}"
        }
    }
}

