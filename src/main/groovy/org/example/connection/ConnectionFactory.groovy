package org.example.connection
import java.sql.Connection;

interface ConnectionFactory {
    Connection getConnection();

}
