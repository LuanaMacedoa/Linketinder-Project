package org.example.connection
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class PostgreSQLConnectionFactory implements ConnectionFactory {

        @Override
        public Connection getConnection(){
            try{
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/linketinder",
                        "postgres",
                        "senha"
                );
            }catch (ClassNotFoundException | SQLException e){
                throw new RuntimeException("Erro ao conectar ao banco de dados PostgreSQL", e);
            }
        }

}
