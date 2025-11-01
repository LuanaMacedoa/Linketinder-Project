package org.example

class InterfaceEmpresa {
    DAO dados

    InterfaceEmpresa(DAO dados){
        this.dados = dados
    }

    def mostrarEmpresas(){
        println "\nEMPRESAS"
        for (i in dados.listarEmpresas()){
            println i
        }
    }

    def adicionarEmpresa(BufferedReader reader){
        println "Digite nome: "
        def nome = reader.readLine()
        println "Digite email: "
        def email = reader.readLine()
        println "Digite estado: "
        def estado = reader.readLine()
        println "Digite cep: "
        def cep = reader.readLine()
        println "Digite CNPJ: "
        def cnpj = reader.readLine()
        println "Digite País: "
        def pais = reader.readLine()
        println "Digite Descrição da empresa: "
        def descE = reader.readLine()
        println "Digite senha: "
        def senha = reader.readLine()
        println "Digite competências (separadas por vírgula): "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()

        def empresa = new PessoaJuridica(nome,email,estado,cep,cnpj,pais,descE,competencias,senha)
        dados.inserirEmpresa(empresa)
        println "Empresa adicionada com sucesso!"
    }
}

