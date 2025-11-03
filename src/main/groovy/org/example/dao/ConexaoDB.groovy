package org.example.dao

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexaoDB {

    static Connection conectar() {
        String url = "jdbc:postgresql://localhost:5432/linketinder"
        String usuario = "postgres"
        String senha = "senha"

        try {
            return DriverManager.getConnection(url, usuario, senha)
        } catch (SQLException e) {
            println "Erro ao conectar ao banco: ${e.message}"
            return null
        }
    }

    static void fechar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close()
            }
        } catch (SQLException e) {
            println "Erro ao fechar conexão: ${e.message}"
        }
    }
}
