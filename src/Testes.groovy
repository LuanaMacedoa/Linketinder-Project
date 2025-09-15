def dados = new Dados()
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

