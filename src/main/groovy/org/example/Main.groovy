package org.example

class Main {
    static void main(String[] args) {

        def dados = new DAO()
        def reader = new BufferedReader(new InputStreamReader(System.in))

        while (true) {
            println "\n=== MENU ==="
            println "1. Listar Empresas"
            println "2. Deletar Empresa"
            println "3. Listar Candidatos"
            println "4. Deletar Candidato"
            println "5. Listar Vagas"
            println "6. Deletar Vaga"
            println "7. Sair"
            print "Escolha uma opção: "
            def opcao = reader.readLine()

            switch (opcao) {
                case "1":
                    println(dados.listarEmpresas())
                    break
                case "2":
                    def id = reader.readLine().toInteger()
                    println(dados.deletarEmpresa(id))
                    break
                case "3":
                    println(dados.listarCandidatos())
                    break
                case "4":
                    def id = reader.readLine().toInteger()
                    println(dados.deletarCandidato(id))
                    break
                case "5":
                    println(dados.listarVagas())
                    break
                case "6":
                    def id = reader.readLine().toInteger()
                    println(dados.deletarVagas(id))
                    break
                case "7":
                    println "Saindo..."
                    return
                    break
                default:
                    println "Opção inválida. Tente novamente."
            }
        }


}
}



