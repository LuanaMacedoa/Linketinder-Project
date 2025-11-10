package org.example

import org.example.dao.ConexaoDB
import org.example.dao.EmpresaDAO
import java.io.BufferedReader
import java.sql.Connection

class InterfaceEmpresa {

    EmpresaDAO empresaDao

    InterfaceEmpresa() {
        this.empresaDao = new EmpresaDAO()
    }

    void mostrarEmpresas(Connection conn) {
        try {
            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }

            List<PessoaJuridica> lista = empresaDao.listar(conn)
            println "\n===== LISTA DE EMPRESAS ====="
            if (lista.isEmpty()) {
                println "Nenhuma empresa cadastrada."
            } else {
                lista.each { println it }
            }
        } catch (Exception e) {
            println "Erro ao listar empresas: ${e.message}"
        }
    }



    void adicionarEmpresa(BufferedReader reader, Connection conn) {
        try {
            print "Nome: "
            String nome = reader.readLine()
            print "Email: "
            String email = reader.readLine()
            print "País: "
            String pais = reader.readLine()
            print "CEP: "
            String cep = reader.readLine()
            print "CNPJ: "
            String cnpj = reader.readLine()
            print "Descrição da empresa: "
            String descricao = reader.readLine()
            print "Competências (separadas por vírgula): "
            List<String> competencias = reader.readLine().split(",")*.trim()
            print "Senha: "
            String senha = reader.readLine()

            PessoaJuridica empresa = new PessoaJuridica(
                    nome, email, cep, cnpj, pais, descricao, competencias, senha
            )

            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }

            empresaDao.inserir(conn, empresa)
            println "\nEmpresa '${empresa.nome}' cadastrada com sucesso!"
        } catch (Exception e) {
            println "Erro ao adicionar empresa: ${e.message}"
        }
    }


    void atualizarEmpresa(BufferedReader reader, Connection conn) {
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

            PessoaJuridica empresa = new PessoaJuridica(
                    nome, email, cep, "", pais, descricao, [], ""
            )
            empresa.id = id

            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }

            empresaDao.atualizar(conn, empresa)
            println "Empresa ID '${id}' atualizada com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        }
    }


    void removerEmpresa(BufferedReader reader, Connection conn) {
        try {
            print "Digite o ID da empresa que deseja remover: "
            def id = reader.readLine().toInteger()

            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }

            empresaDao.deletar(conn, id)
            println "Empresa removida com sucesso!"
        } catch (Exception e) {
            println "Erro ao remover empresa: ${e.message}"
        }
    }
}
