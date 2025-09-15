class InterfaceEmpresa {

    Dados dados

    InterfaceEmpresa(Dados dados){
        this.dados = dados
    }

    // Mostrar Empresas
    def mostrarEmpresas(){
        println ""
        println "EMPRESAS"
        for ( i in dados.empresas){
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
        println "Digite competências: "
        def competenciasStr = reader.readLine()
        def competencias = competenciasStr.split(",")*.trim()

        def empresa = new PessoaJuridica(nome,email,estado,cep,cnpj,pais,descE,competencias)
        dados.adicionarEmpresa(empresa)
        println "Empresa adicionada com sucesso!"
    }
}
