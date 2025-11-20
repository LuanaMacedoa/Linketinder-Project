package org.example.controller

import org.example.connection.ConnectionFactorySingleton
import org.example.model.dao.EmpresaDAO
import org.example.model.Empresa

class EmpresaController {
    private final dao = new EmpresaDAO()

    void cadastrar(Empresa empresa) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.inserir(conn, empresa)
            println "Empresa '${empresa.nome}' cadastrada com sucesso!"
        } catch (Exception e) {
            println "Erro ao cadastrar empresa: ${e.message}"
        } finally {
            conn.close()
        }
    }

    List listar() {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            return dao.listar(conn)
        } catch (Exception e) {
            println "Erro ao listar empresas: ${e.message}"
            return []
        } finally {
            conn.close()
        }
    }

    void atualizar(Empresa empresa) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.atualizar(conn, empresa)
            println "Empresa '${empresa.id}' atualizada com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        } finally {
            conn.close()
        }
    }

    void deletar(int id) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            dao.deletar(conn, id)
            println "Empresa removida com sucesso!"
        } catch (Exception e) {
            println "Erro ao remover empresa: ${e.message}"
        } finally {
            conn.close()
        }
    }
}
