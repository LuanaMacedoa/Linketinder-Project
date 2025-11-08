package org.example
import org.example.dao.ConexaoDB
import org.example.dao.VagaDAO
import java.io.BufferedReader
import java.sql.Connection

class InterfaceVaga {

    VagaDAO vagaDao

    InterfaceVaga() {
        this.vagaDao = new VagaDAO()
    }

    def mostrarVagas(Connection conn) {
        println "\n===== LISTA DE VAGAS ====="
        def lista = vagaDao.listar(conn)
        if (lista.isEmpty()) {
            println "Nenhuma vaga cadastrada."
        } else {
            lista.each { println it }
        }
    }

    def adicionarVaga(BufferedReader reader, Connection conn) {
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
        vagaDao.inserir(conn, vaga)
        println "\nVaga '${vaga.nomeVaga}' cadastrada com sucesso!"
    }

    def removerVaga(BufferedReader reader, Connection conn) {
        print "Digite o ID da vaga que deseja remover: "
        def id = reader.readLine().toInteger()
        vagaDao.deletar(conn, id)
        println "Vaga removida com sucesso!"
    }

    def atualizarVaga(BufferedReader reader, Connection conn) {
        print "Digite o ID da vaga que deseja atualizar: "
        def id = reader.readLine().toInteger()
        print "Novo nome da vaga: "
        def nomeVaga = reader.readLine()
        print "Nova descrição da vaga: "
        def descricao = reader.readLine()
        print "Novas competências (separadas por vírgula): "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()


        def vaga = new Vaga(nomeVaga, descricao, competencias, 0)
        vaga.id = id
        vagaDao.atualizar(conn, vaga)
        println "Vaga ID '${id}' atualizada com sucesso!"
    }
}

