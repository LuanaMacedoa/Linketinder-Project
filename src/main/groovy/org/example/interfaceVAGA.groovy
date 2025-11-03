package org.example

import org.example.dao.ConexaoDB

class InterfaceVaga {
    ConexaoDB dados

    InterfaceVaga(ConexaoDB dados) {
        this.dados = dados
    }

    def mostrarVagas() {
        println "\n===== LISTA DE VAGAS ====="
        def lista = dados.listarVagas()
        if (lista.isEmpty()) {
            println "Nenhuma vaga cadastrada."
        } else {
            lista.each { println it }
        }
    }

    def adicionarVaga(BufferedReader reader) {
        println "\n=== CADASTRO DE VAGA ==="
        print "Nome da vaga: "
        def nomeVaga = reader.readLine()
        print "Descrição da vaga: "
        def descricao = reader.readLine()
        print "ID da empresa: "
        def idEmpresa = reader.readLine().toInteger()
        print "Competências exigidas (separadas por vírgula): "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()

        def vaga = new Vaga(nomeVaga, descricao, competencias, idEmpresa)
        dados.inserirVaga(vaga)
        println "\nVaga '${vaga.nomeVaga}' cadastrada com sucesso!"
    }

    def removerVaga(BufferedReader reader) {
        print "Digite o ID da vaga que deseja remover: "
        def id = reader.readLine().toInteger()
        dados.deletarVaga(id)
        println "Vaga removida com sucesso!"
    }

    def atualizarVaga(BufferedReader reader) {
        print "Digite o ID da vaga que deseja atualizar: "
        def id = reader.readLine().toInteger()
        print "Novo nome da vaga: "
        def nomeVaga = reader.readLine()
        print "Nova descrição da vaga: "
        def descricao = reader.readLine()
        print "Novas competências (separadas por vírgula): "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()

        def vaga = new Vaga(nomeVaga, descricao, competencias, 0) // empresaId não será atualizado aqui
        vaga.id = id
        dados.atualizarVaga(vaga)
        println "Vaga ID '${id}' atualizada com sucesso!"
    }
}
