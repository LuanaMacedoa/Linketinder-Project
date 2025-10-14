package org.example

class InterfaceUsuario {
    Dados dados

    InterfaceUsuario(Dados dados){
        this.dados = dados
    }

    def mostrarCandidatos(){
        println "\nCANDIDATOS"
        for (i in dados.listarCandidatos()){
            println i
        }
    }

    def adicionarCandidato(BufferedReader reader){
        println "Digite nome: "
        def nome = reader.readLine()
        println "Digite sobrenome: "
        def sobrenome = reader.readLine()
        println "Digite email: "
        def email = reader.readLine()
        println "Digite estado: "
        def estado = reader.readLine()
        println "Digite cep: "
        def cep = reader.readLine()
        println "Digite CPF: "
        def cpf = reader.readLine()
        println "Digite idade: "
        def idade = reader.readLine().toInteger()
        println "Digite descrição pessoal: "
        def descP = reader.readLine()
        println "Digite senha: "
        def senha = reader.readLine()
        println "Digite competências (separadas por vírgula): "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()
        println "Digite data de nascimento (AAAA-MM-DD): "
        def dataNasc = java.time.LocalDate.parse(reader.readLine())

        def candidato = new PessoaFisica(nome,sobrenome,email,estado,cep,cpf,idade,descP,competencias,senha,dataNasc)
        dados.inserirCandidato(candidato)
        println "Candidato adicionado com sucesso!"
    }
}


