package org.example.controller

import org.example.model.Vaga
import org.example.connection.ConnectionFactorySingleton
import org.example.model.dao.VagaDAO
import org.example.views.InterfaceVaga

class VagaController {
    private final VagaDAO dao = new VagaDAO()
    private final InterfaceVaga view


    VagaController(InterfaceVaga view) {
        this.view = view
    }

    void listarVagas() {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            def vagas = dao.listar(conn)
            view.exibirListaVagas(vagas)
        } finally {
            conn.close()
        }
    }

    void cadastrarVaga(reader) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            def (nome, descricao, idEmpresa, competencias) = view.solicitarDadosNovaVaga(reader)
            def vaga = new Vaga(nome, descricao, competencias, idEmpresa)
            dao.inserir(conn, vaga)
            println "\n Vaga '${vaga.nomeVaga}' cadastrada com sucesso!"
        } finally {
            conn.close()
        }
    }

    void deletarVaga(reader) {
        def conn = ConnectionFactorySingleton.instance.getConnection()
        try {
            def id = view.solicitarIdVaga(reader, "remover")
            dao.deletar(conn, id)
            println "🗑 Vaga removida com sucesso!"
        } finally {
            conn.close()
        }
    }
}

