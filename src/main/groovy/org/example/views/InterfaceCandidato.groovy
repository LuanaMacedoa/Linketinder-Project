package org.example.views

import org.example.controller.CandidatoController
import org.example.model.Candidato
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class InterfaceCandidato {
    private final CandidatoController controller = new CandidatoController()

    void mostrarCandidatos() {
        controller.listar()
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

        Candidato candidato = new Candidato(
                nome, sobrenome, email, pais, cep, cpf, descricao, competencias, dataNasc, senha
        )

        controller.cadastrar(candidato)
    }

    void removerCandidato(BufferedReader reader) {
        print "Digite o ID do candidato que deseja remover: "
        int id = Integer.parseInt(reader.readLine())
        controller.deletar(id)
    }

}



