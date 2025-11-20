package org.example.views

import org.example.controller.VagaController

class InterfaceVaga {
    private final VagaController controller = new VagaController()

    def mostrarVagas() {
        controller.listar()
    }

    def solicitarDadosNovaVaga(reader) {
        println "\n=== CADASTRO DE VAGA ==="
        print "Nome da vaga: "
        def nome = reader.readLine()
        print "Descrição: "
        def descricao = reader.readLine()
        print "ID da empresa: "
        def idEmpresa = reader.readLine().toInteger()
        print "Competências (separadas por vírgula): "
        def competencias = reader.readLine().split(",")*.trim()
        return [nome, descricao, idEmpresa, competencias]
    }

    def solicitarIdVaga(reader, acao) {
        print "Digite o ID da vaga que deseja ${acao}: "
        return reader.readLine().toInteger()
    }

    def cadastrarVaga(reader) {
        controller.cadastrarVaga(reader)
    }

    def deletarVaga(reader) {
        controller.deletarVaga(reader)
    }

    def atualizarVaga(reader) {
        println "Função de atualização de vaga ainda não implementada."
    }
}
