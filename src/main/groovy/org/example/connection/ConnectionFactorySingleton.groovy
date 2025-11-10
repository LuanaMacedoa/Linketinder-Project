package org.example.connection

import java.sql.Connection

class ConnectionFactorySingleton {
    private static ConnectionFactorySingleton instance;
    private final ConnectionFactory factory;

    private ConnectionFactorySingleton(){
        this.factory = new PostgreSQLConnectionFactory();
    }

    public static  synchronized  ConnectionFactorySingleton getInstance(){
        if (instance == null){
            instance = new ConnectionFactorySingleton();
        }
        return instance
    }

    public Connection getConnection(){
        return factory.getConnection();
    }

}

