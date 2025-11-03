package org.example

import org.example.dao.EmpresaDAO
import org.example.dao.ConexaoDB
import java.io.BufferedReader
import java.sql.Connection

class InterfaceEmpresa {
    EmpresaDAO empresaDao

    InterfaceEmpresa() {
        this.empresaDao = new EmpresaDAO()
    }

    void mostrarEmpresas() {
        Connection conn = null
        try {
            conn = ConexaoDB.conectar()
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

        } finally {
            ConexaoDB.fechar(conn)
        }
    }

    void adicionarEmpresa(BufferedReader reader) {
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

        Connection conn = null
        try {
            conn = ConexaoDB.conectar()
            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }
            empresaDao.inserir(conn, empresa)
            println "\nEmpresa '${empresa.nome}' cadastrada com sucesso!"
        } finally {
            ConexaoDB.fechar(conn)
        }
    }
}



