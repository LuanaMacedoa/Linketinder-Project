package org.example
import java.time.LocalDate



class Pessoa {
    String nome
    String email
    String cep
    String senha
}


class PessoaFisica extends Pessoa {
    String cpf
    String sobrenome
    String pais
    String descricaoPessoal
    List<String> competencias
    LocalDate dataNasc
    String senha
    int id

    PessoaFisica(String nome, String sobrenome, String email, String pais, String cep,
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

class PessoaJuridica extends Pessoa {
    String cnpj
    String pais
    String descricaoEmpresa
    List<String> competencias
    String senha
    int id

    PessoaJuridica(String nome, String email, String cep,
                   String cnpj, String pais, String descricaoEmpresa, List<String> competencias,
                   String senha) {
        this.nome = nome
        this.email = email
        this.cep = cep
        this.cnpj = cnpj
        this.pais = pais
        this.descricaoEmpresa = descricaoEmpresa
        this.competencias = competencias
        this.senha = senha
    }

    String toString() {
        return """Nome: $nome
        Email: $email
        CNPJ: $cnpj
        CEP: $cep
        País: $pais
        Descrição: $descricaoEmpresa
        Competências: $competencias
        Senha: $senha
        """
    }
}






