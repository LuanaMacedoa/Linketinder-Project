package org.example

import java.time.LocalDate

class Main {
    static void main(String[] args) {
        def dados = new Dados()

        def candidatoTeste = new PessoaFisica(
                "Joao",
                "Silva",
                "Joao.silva@email.com",
                "Brasil",
                "12345-678",
                "987.654.321-10",
                "Profissional dedicada e com experiência em Python",
                [ "Python"],
                LocalDate.of(1995, 5, 20),
                "minhasenha"
        )

        dados.inserirCandidato(candidatoTeste)
        println "Candidato inserido com sucesso! ID: ${candidatoTeste.id}"
    }
}



