class InterfaceUsuario {
    Dados dados

    InterfaceUsuario(Dados dados){
        this.dados = dados
    }

    // Mostrar candidatos
    def mostrarCandidatos(){
        println ""
        println "CANDIDATOS"
        for (i in dados.candidatos){
            println i
        }
    }

    def adicionarCandidato(BufferedReader reader){
        println "Digite nome: "
        def nome = reader.readLine()
        println "Digite email: "
        def email = reader.readLine()
        println "Digite estado: "
        def estado = reader.readLine()
        println "Digite cep: "
        def cep = reader.readLine()
        println "Digite CPF: "
        def cpf = reader.readLine()
        println "Digite idade: "
        def idadeStr = reader.readLine()
        def idade = idadeStr.toInteger()
        println "Digite Descrição Pessoal: "
        def descP = reader.readLine()
        println "Digite competências: "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()

        def candidato = new PessoaFisica(nome,email,estado,cep,cpf,idade,descP,competencias)
        dados.adicionarCandidato(candidato)
        println "Candidato adicionado com sucesso!"
    }

}

