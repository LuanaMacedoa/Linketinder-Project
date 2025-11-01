/*def dados = new Dados()
dados.inicializar()

// TESTES CANDIDATO

def tamanhoAntes = dados.candidatos.size()
def candidatoTeste = new PessoaFisica("Teste candidato", "teste@teste.com", "SP", "00000-000", "111.222.333.44", 25, "Descrição teste", ["Java", "Groovy"])

dados.adicionarCandidato(candidatoTeste)

//Verifica se o objeto foi criado (teste 1)

assert candidatoTeste != null : "O objeto candidato não foi criado"

//verifica o tamanho da lista (teste 2)
assert  dados.candidatos.size() == tamanhoAntes + 1 :"A lista de candidatos não aumentou:"

// verifica se os atributos do último objetos são iguais aos dados passados

def ultimo = dados.candidatos[-1]

def verificarCandidato(PessoaFisica esperando, PessoaFisica atual){
    assert  atual.nome == esperando.nome
    assert  atual.email == esperando.email
    assert  atual.estado == esperando.estado
    assert  atual.cep == esperando.cep
    assert  atual.cpf == esperando.cpf
    assert  atual.idade == esperando.idade
    assert  atual.descricaoPessoal == esperando.descricaoPessoal
    assert  atual.competencias == esperando.competencias
}

verificarCandidato(candidatoTeste,dados.candidatos[-1])

// TESTES EMPRESAS

def tamanhoAntesEmp = dados.empresas.size()
def empresaTeste =  new PessoaJuridica("Teste Empresa", "contaato@teste.com", "rj", "11110-000", "99.888.777/0001", "Brasil", "Descrição teste empresa", ["Pyhton", "React"])
dados.adicionarEmpresa(empresaTeste)

// verifica se o objeto foi criado (teste 1)
assert empresaTeste != null : "O objeto empresa não foi criado"

// verifica o tamanho da lista (teste 2)
assert dados.empresas.size() == tamanhoAntesEmp +1 : "A lista de empresas não aumentou de tamanho"

// verifica se os atributos do último objetos são iguais aos dados passados (teste3

def ultimoEmp = dados.empresas[-1]

def verificarEmpresa(PessoaJuridica esperando, PessoaJuridica atual){
    assert  atual.nome == esperando.nome
    assert  atual.email == esperando.email
    assert  atual.estado == esperando.estado
    assert  atual.cep == esperando.cep
    assert  atual.cnpj == esperando.cnpj
    assert  atual.pais == esperando.pais
    assert  atual.descricaoEmpresa == esperando.descricaoEmpresa
    assert  atual.competencias == esperando.competencias
}

verificarEmpresa(empresaTeste,dados.empresas[-1])
*/
/*def dados = new Dados()
def candidatoTeste = new PessoaFisica(
        "Nome",
        "Sobrenome",
        "teste@teste.com",
        "Brasil",
        "00000-000",
        "111.222.333.44",
        25,
        "Descrição teste",
        ["Java", "Groovy"],
        LocalDate.of(1998, 10, 14),
        "senha123"
)

     def dados = new Dados()

        // ================== TESTE EMPRESA ==================
        def empresa = new PessoaJuridica(
                "Empresa II",
                "contatooo@empresa.com",
                "58000-000",
                "10.342.678/0002-90",
                "brasil",
                "Empresa de testes",
                ["Gestão", "TI"],
                "senha123"
        )

        dados.inserirEmpresa(empresa)
        println " Empresa inserida com ID: ${empresa.id}"

        def empresas = dados.listarEmpresas()
        println " Lista de empresas:"
        empresas.each { e ->
            println "ID: ${e.id}, Nome: ${e.nome}, Email: ${e.email}, Competências: ${e.competencias}"
        }

        // Atualizar empresa
        empresa.nome = "Empresa Atualizada"
        empresa.descricaoEmpresa = "Descrição atualizada"
        dados.atualizarEmpresa(empresa)
        println " Empresa atualizada!"

        // Verificando a atualização
        empresas = dados.listarEmpresas()
        println " Lista de empresas após update:"
        empresas.each { e ->
            println "ID: ${e.id}, Nome: ${e.nome}, Descrição: ${e.descricaoEmpresa}"
        }

        // Deletar empresa
        //dados.deletarEmpresa(empresa.id)
        //println " Empresa deletada!"

        // ================== TESTE VAGA ==================
        def vaga = new Vaga(
                "Desenvolvedor Java",
                "Vaga para programador Java",
                ["Java", "SQL"],
                empresa.id // você pode usar qualquer empresa existente
        )

        dados.inserirVaga(vaga)
        println " Vaga inserida com ID: ${vaga.id}"

        def vagas = dados.listarVagas()
        println " Lista de vagas:"
        vagas.each { v ->
            println "ID: ${v.id}, Nome: ${v.nomeVaga}, Empresa ID: ${v.idEmpresa}"
        }

        // Atualizar vaga
        vaga.nomeVaga = "Desenvolvedor Java Atualizado"
        vaga.descricaoVaga = "Descrição atualizada"
        dados.atualizarVaga(vaga)
        println " Vaga atualizada!"

        // Verificando a atualização
        vagas = dados.listarVagas()
        println " Lista de vagas após update:"
        vagas.each { v ->
            println "ID: ${v.id}, Nome: ${v.nomeVaga}, Empresa ID: ${v.idEmpresa}"
        }

        // Deletar vaga
        //dados.deletarVaga(vaga.id)
        //println " Vaga deletada!"

        // ================== TESTE COMPETÊNCIA ==================
        def competencia = "Python Avançado"
        dados.inserirCompetencia(competencia)
        println " Competência '$competencia' inserida!"

        def idComp = dados.buscarCompetenciaPorNome(competencia)
        println " ID da competência '$competencia': $idComp"

        // Você pode deletar diretamente usando SQL se quiser, mas inserir/buscar já testa a lógica
}*/