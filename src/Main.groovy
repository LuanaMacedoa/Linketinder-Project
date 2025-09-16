static void main(String[] args) {
    def dados = new Dados()
    dados.inicializar()

    def usuario = new InterfaceUsuario(dados)
    def empresa = new InterfaceEmpresa(dados)
while (true) {
    println """
        ===================================
             BEM-VINDO AO LINKETINDER
        ===================================
        Digite 1:  para ver os candidatos cadastrados
        Digite 2:  para ver as empresas cadastradas
        Digite 3:  para adicionar um candidato
        Digite 4:  para adicionar uma empresa
        Digite 5:  para sair
      
        """

    def reader = System.in.newReader()
    def numStr = reader.readLine()
    def num = numStr.toInteger()
    print(num)

    switch (num) {
        case 1:
            usuario.mostrarCandidatos();
            break
        case 2:
            empresa.mostrarEmpresas()
            break
        case 3:
            usuario.adicionarCandidato(reader)
            break
        case 4:
            empresa.adicionarEmpresa(reader)
            break
        case 5:
            println("Programa encerrado! ")
            return
            break
        default:
            println "Opção inválida!"

    }
}
}
