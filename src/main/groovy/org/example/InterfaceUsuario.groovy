package org.example

import org.example.dao.CandidatoDAO
import org.example.dao.ConexaoDB
import java.io.BufferedReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.sql.Connection

class InterfaceUsuario {
    CandidatoDAO candidatoDao

    InterfaceUsuario() {
        this.candidatoDao = new CandidatoDAO()
    }

    void mostrarCandidatos() {
        Connection conn = null
        try {
            conn = ConexaoDB.conectar()
            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }

            List<PessoaFisica> lista = candidatoDao.listar(conn)
            println "\n===== LISTA DE CANDIDATOS ====="
            if (lista.isEmpty()) {
                println "Nenhum candidato cadastrado."
            } else {
                lista.each { println it }
            }

        } finally {
            ConexaoDB.fechar(conn)
        }
    }

    void adicionarCandidato(BufferedReader reader) {
        print "Nome: "
        String nome = reader.readLine()
        print "Sobrenome: "
        String sobrenome = reader.readLine()
        print "Email: "
        String email = reader.readLine()
        print "País: "
        String pais = reader.readLine()
        print "CEP: "
        String cep = reader.readLine()
        print "CPF: "
        String cpf = reader.readLine()
        print "Descrição pessoal: "
        String descricao = reader.readLine()
        print "Competências (separadas por vírgula): "
        List<String> competencias = reader.readLine().split(",")*.trim()
        print "Data de nascimento (yyyy-MM-dd): "
        LocalDate dataNasc = LocalDate.parse(reader.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        print "Senha: "
        String senha = reader.readLine()

        PessoaFisica candidato = new PessoaFisica(
                nome, sobrenome, email, pais, cep, cpf, descricao, competencias, dataNasc, senha
        )

        Connection conn = null
        try {
            conn = ConexaoDB.conectar()
            if (conn == null) {
                println "Erro: não foi possível conectar ao banco."
                return
            }
            candidatoDao.inserir(conn, candidato)
            println "\nCandidato '${candidato.nome} ${candidato.sobrenome}' cadastrado com sucesso!"
        } finally {
            ConexaoDB.fechar(conn)
        }
    }
}



