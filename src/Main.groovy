static void main(String[] args) {
    def dados = new Dados()
    dados.inicializar()

    println """
        ===================================
             BEM-VINDO AO LINKETINDER
        ===================================
        Digite 1:  para ver os candidatos cadastrados
        Digite 2:  para ver as empresas cadastradas
      
        """

    def reader = System.in.newReader()
    def numStr = reader.readLine()
    def num = numStr.toInteger()
    print(num)

    switch (num) {
        case 1:
            println "CANDIDATOS"
            for (i in dados.candidatos){
                println i
            }
            break
        case 2:
            println "EMPRESAS"
            for ( i in dados.empresas){
                println i
            }
    }


}