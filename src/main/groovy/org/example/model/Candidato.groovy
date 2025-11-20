package org.example.model

import java.time.LocalDate

public class Candidato {
    String nome
    String email
    String cep
    String cpf
    String sobrenome
    String pais
    String descricaoPessoal
    List<String> competencias
    LocalDate dataNasc
    String senha
    int id

    Candidato(String nome, String sobrenome, String email, String pais, String cep,
              String cpf,  String descricaoPessoal, List<String> competencias,
              LocalDate dataNasc, String senha) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.email = email
        this.pais = pais
        this.cep = cep
        this.cpf = cpf
        this.descricaoPessoal = descricaoPessoal
        this.competencias = competencias
        this.dataNasc = dataNasc
        this.senha = senha
    }

    String toString() {
        return """Nome: $nome $sobrenome
        Email: $email
        CPF: $cpf
        Descrição: $descricaoPessoal
        Competências: $competencias
        Data de Nasc: $dataNasc
        Senha: $senha
        """
    }

}