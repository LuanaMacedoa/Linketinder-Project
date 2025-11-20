package org.example.controller


import org.example.model.Candidato
import org.example.connection.ConnectionFactorySingleton
import org.example.model.CandidatoDAO

class CandidatoController {
    private final CandidatoDAO dao = new CandidatoDAO()

    void cadastrar(Candidato candidato) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.inserir(conn, candidato)
            println "Candidato cadastrado com sucesso!"
        } finally {
            conn.close()
        }
    }

    def listar() {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.listar(conn).each { println it }
        } finally {
            conn.close()
        }
    }

    void atualizar(Candidato candidato) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.atualizar(conn, candidato)
            println " Atualizado com sucesso!"
        } finally {
            conn.close()
        }
    }

    void deletar(int id) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.deletar(conn, id)
            println "️ Candidato removido!"
        } finally {
            conn.close()
        }
    }
}

