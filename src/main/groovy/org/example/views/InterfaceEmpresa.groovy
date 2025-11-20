package org.example.views

import org.example.controller.EmpresaController
import org.example.model.Empresa

class InterfaceEmpresa {
    private final controller = new EmpresaController()

    void mostrarEmpresas() {
        try {
            def lista = controller.listar()
            println "\n===== LISTA DE EMPRESAS ====="
            if (lista.empty) {
                println "Nenhuma empresa cadastrada."
            } else {
                lista.each { println it }
            }
        } catch (Exception e) {
            println "Erro ao listar empresas: ${e.message}"
        }
    }

    void adicionarEmpresa(BufferedReader reader) {
        try {
            print "Nome: "
            def nome = reader.readLine()
            print "Email: "
            def email = reader.readLine()
            print "País: "
            def pais = reader.readLine()
            print "CEP: "
            def cep = reader.readLine()
            print "CNPJ: "
            def cnpj = reader.readLine()
            print "Descrição da empresa: "
            def descricao = reader.readLine()
            print "Competências (separadas por vírgula): "
            def competencias = reader.readLine().split(",")*.trim()
            print "Senha: "
            def senha = reader.readLine()

            def empresa = new Empresa(
                    nome, email, cep, cnpj, pais, descricao, competencias, senha
            )

            controller.cadastrar(empresa)
        } catch (Exception e) {
            println "Erro ao adicionar empresa: ${e.message}"
        }
    }

    void atualizarEmpresa(BufferedReader reader) {
        try {
            print "Digite o ID da empresa que deseja atualizar: "
            def id = reader.readLine().toInteger()
            print "Novo nome: "
            def nome = reader.readLine()
            print "Novo email: "
            def email = reader.readLine()
            print "Novo CEP: "
            def cep = reader.readLine()
            print "Nova descrição: "
            def descricao = reader.readLine()
            print "Novo país: "
            def pais = reader.readLine()

            def empresa = new Empresa(
                    nome, email, cep, "", pais, descricao, [], ""
            )
            empresa.id = id

            controller.atualizar(empresa)
        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        }
    }

    void removerEmpresa(BufferedReader reader) {
        try {
            print "Digite o ID da empresa que deseja remover: "
            def id = reader.readLine().toInteger()
            controller.deletar(id)
        } catch (Exception e) {
            println "Erro ao remover empresa: ${e.message}"
        }
    }
}
