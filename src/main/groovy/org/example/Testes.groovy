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

    dados.inserirCandidato(candidatoTeste)
    println "✅ Candidato inserido com sucesso!"

def dados = new Dados()

        int empresaIdExistente = 5 // substitua pelo ID real da empresa

        def vagaTeste = new Vaga(
                "Desenvolvedor java",
                "Desenvolvimento backend com Java",
                [ "Java", "SQL"],
                empresaIdExistente
        )

        dados.inserirVaga(vagaTeste)

        println "Vaga '${vagaTeste.nomeVaga}' inserida para empresa id ${empresaIdExistente}"

        package org.example

class Main {
    static void main(String[] args) {
        def dados = new Dados()

        // === CREATE: Inserir nova empresa ===
        def novaEmpresa = new Empresa(
            "12.345.678/0001-99",
            "Empresa Teste",
            "contato@empresateste.com",
            "Descrição da empresa teste",
            "Brasil",
            "12345-678",
            "senhaSegura"
        )
        inserirEmpresa(dados, novaEmpresa)

        // === READ: Buscar empresa por ID ===
        def empresa = buscarEmpresaPorId(dados, 1) // Alterar ID conforme banco
        if (empresa) {
            println "🏢 Empresa encontrada: ${empresa.nomeEmp}"
        }

        // === UPDATE: Atualizar nome da empresa ===
        if (empresa) {
            empresa.nomeEmp = "Empresa Atualizada"
            atualizarEmpresa(dados, empresa)
        }
    }

    static void inserirEmpresa(Dados dados, Empresa empresa) {
        dados.conectar()
        try {
            def sql = """
                INSERT INTO Empresa (cnpj, nome_emp, email_corporativo, desc_empresa, pais, cep, senha)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """
            def stmt = dados.conn.prepareStatement(sql)
            stmt.setString(1, empresa.cnpj)
            stmt.setString(2, empresa.nomeEmp)
            stmt.setString(3, empresa.emailCorporativo)
            stmt.setString(4, empresa.descEmpresa)
            stmt.setString(5, empresa.pais)
            stmt.setString(6, empresa.cep)
            stmt.setString(7, empresa.senha)
            stmt.executeUpdate()
            stmt.close()
            println "✅ Empresa '${empresa.nomeEmp}' inserida com sucesso!"
        } finally {
            dados.desconectar()
        }
    }

    static Empresa buscarEmpresaPorId(Dados dados, int id) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("SELECT * FROM Empresa WHERE id_empresa = ?")
            stmt.setInt(1, id)
            def rs = stmt.executeQuery()
            if (rs.next()) {
                return new Empresa(
                    rs.getString("cnpj"),
                    rs.getString("nome_emp"),
                    rs.getString("email_corporativo"),
                    rs.getString("desc_empresa"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getString("senha")
                ).tap { it.idEmpresa = id }
            }
            return null
        } finally {
            dados.desconectar()
        }
    }

    static void atualizarEmpresa(Dados dados, Empresa empresa) {
        dados.conectar()
        try {
            def sql = """
                UPDATE Empresa SET
                    cnpj = ?, nome_emp = ?, email_corporativo = ?, desc_empresa = ?, pais = ?, cep = ?, senha = ?
                WHERE id_empresa = ?
            """
            def stmt = dados.conn.prepareStatement(sql)
            stmt.setString(1, empresa.cnpj)
            stmt.setString(2, empresa.nomeEmp)
            stmt.setString(3, empresa.emailCorporativo)
            stmt.setString(4, empresa.descEmpresa)
            stmt.setString(5, empresa.pais)
            stmt.setString(6, empresa.cep)
            stmt.setString(7, empresa.senha)
            stmt.setInt(8, empresa.idEmpresa)
            stmt.executeUpdate()
            stmt.close()
            println "Empresa '${empresa.nomeEmp}' atualizada com sucesso!"
        } finally {
            dados.desconectar()
        }
    }
}
     def dados = new Dados()

        int idParaBuscar = 6 // substitua pelo ID real da empresa

        // READ - Buscar pessoa jurídica por ID
        def empresa = buscarPessoaPorId(dados, idParaBuscar)

        if (empresa) {
            println " Empresa encontrada: ${empresa.nome}, ID: ${idParaBuscar}"

            // UPDATE - Atualizar descrição da empresa
            empresa.descricaoEmpresa = "Descrição atualizada pelo teste"
            atualizarPessoaJuridica(dados, empresa)
            println "✏ Empresa atualizada com sucesso!"
        } else {
            println " Nenhuma empresa encontrada com ID $idParaBuscar"
        }
    }

    static PessoaJuridica buscarPessoaPorId(Dados dados, int id) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("SELECT * FROM Empresa WHERE id_empresa = ?")
            stmt.setInt(1, id)
            def rs = stmt.executeQuery()
            if (rs.next()) {
                return new PessoaJuridica(
                        rs.getString("nome_emp"),
                        rs.getString("email_corporativo"),
                        rs.getString("cep"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("desc_empresa"),
                        [], // competências vazias pra simplificar
                        rs.getString("senha")
                ).tap { it.id = id }
            }
            return null
        } finally {
            dados.desconectar()
        }
    }

    static void atualizarPessoaJuridica(Dados dados, PessoaJuridica pj) {
        dados.conectar()
        try {
            def sql = """
            UPDATE Empresa SET nome_emp = ?, email_corporativo = ?, desc_empresa = ?, pais = ?, cep = ?, senha = ?
            WHERE id_empresa = ?
            """
            def stmt = dados.conn.prepareStatement(sql)
            stmt.setString(1, pj.nome)
            stmt.setString(2, pj.email)
            stmt.setString(3, pj.descricaoEmpresa)
            stmt.setString(4, pj.pais)
            stmt.setString(5, pj.cep)
            stmt.setString(6, pj.senha)
            stmt.setInt(7, pj.id)
            stmt.executeUpdate()
            stmt.close()
        } finally {
            dados.desconectar()
        }

        def dados = new Dados()

                // === CREATE: Inserir nova vaga ===
                def novaVaga = new Vaga(
                        "Desenvolvedor Full Stack",
                        "Atuar no desenvolvimento web completo.",
                        ["Java", "Angular", "SQL"],
                        1 // ID da empresa já existente no banco
                )
                dados.inserirVaga(novaVaga)

                // === READ: Buscar vaga por ID ===
                def vaga = buscarVagaPorId(dados, 1) // Altere o ID conforme seu banco
                if (vaga) {
                    println " Vaga encontrada: ${vaga.nomeVaga} - ${vaga.descricaoVaga}"
                }

                // === UPDATE: Atualizar descrição da vaga ===
                if (vaga) {
                    vaga.descricaoVaga = "Descrição atualizada para a vaga de Full Stack."
                    atualizarVaga(dados, vaga)
                }
            }

            static def buscarVagaPorId(Dados dados, int id) {
                dados.conectar()
                try {
                    def stmt = dados.conn.prepareStatement("SELECT * FROM vaga WHERE id_vaga = ?")
                    stmt.setInt(1, id)
                    def rs = stmt.executeQuery()
                    if (rs.next()) {
                        return new Vaga(
                                rs.getString("nome_vaga"),
                                rs.getString("desc_vaga"),
                                [], // competências não carregadas neste exemplo
                                rs.getInt("id_empresa")
                        ).tap { it.id = rs.getInt("id_vaga") }
                    }
                    return null
                } finally {
                    dados.desconectar()
                }
            }

            static void atualizarVaga(Dados dados, Vaga vaga) {
                dados.conectar()
                try {
                    def stmt = dados.conn.prepareStatement("""
                UPDATE vaga SET nome_vaga = ?, desc_vaga = ?, localizacao = ?
                WHERE id_vaga = ?
            """)
                    stmt.setString(1, vaga.nomeVaga)
                    stmt.setString(2, vaga.descricaoVaga)
                    stmt.setString(3, "Remoto") // ou outro valor se quiser
                    stmt.setInt(4, vaga.id)
                    stmt.executeUpdate()
                    stmt.close()
                    println "Vaga '${vaga.nomeVaga}' atualizada com sucesso!"
                } finally {
                    dados.desconectar()
                }


                       def dados = new Dados()

        // === CREATE: Inserir competência ===
        String novaCompetencia = "Kotlin"
        inserirCompetencia(dados, novaCompetencia)

        // === READ: Buscar competência por nome ===
        def compId = buscarCompetenciaPorNome(dados, novaCompetencia)
        if (compId) {
            println "Competência encontrada: '${novaCompetencia}' com ID ${compId}"
        }

        // === UPDATE: Atualizar nome da competência ===
        if (compId) {
            atualizarCompetencia(dados, compId, "Kotlin Avançado")
        }
    }

    static void inserirCompetencia(Dados dados, String nome) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("""
                INSERT INTO Competencia(nome_competencia)
                VALUES (?)
                ON CONFLICT (nome_competencia) DO NOTHING
            """)
            stmt.setString(1, nome)
            stmt.executeUpdate()
            stmt.close()
            println "Competência '$nome' inserida com sucesso!"
        } finally {
            dados.desconectar()
        }
    }

    static Integer buscarCompetenciaPorNome(Dados dados, String nome) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("SELECT id_competencia FROM Competencia WHERE nome_competencia = ?")
            stmt.setString(1, nome)
            def rs = stmt.executeQuery()
            if (rs.next()) {
                return rs.getInt("id_competencia")
            }
            return null
        } finally {
            dados.desconectar()
        }
    }

    static void atualizarCompetencia(Dados dados, int id, String novoNome) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("UPDATE Competencia SET nome_competencia = ? WHERE id_competencia = ?")
            stmt.setString(1, novoNome)
            stmt.setInt(2, id)
            stmt.executeUpdate()
            stmt.close()
            println " Competência ID ${id} atualizada para '${novoNome}' com sucesso!"
        } finally {
            dados.desconectar()
        }
    }

    def dados = new Dados()

        // === DELETE VAGA ===
        deletarVaga(dados, 3) // Altere o ID conforme necessário

        // === DELETE COMPETÊNCIA ===
        deletarCompetencia(dados, 8) // Altere o ID conforme necessário
    }

    static void deletarVaga(Dados dados, int id) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("DELETE FROM Vaga WHERE id_vaga = ?")
            stmt.setInt(1, id)
            int rows = stmt.executeUpdate()
            stmt.close()
            if (rows > 0) {
                println " Vaga com ID ${id} deletada com sucesso!"
            } else {
                println " Nenhuma vaga encontrada com ID ${id}."
            }
        } finally {
            dados.desconectar()
        }
    }

    static void deletarCompetencia(Dados dados, int id) {
        dados.conectar()
        try {
            def stmt = dados.conn.prepareStatement("DELETE FROM Competencia WHERE id_competencia = ?")
            stmt.setInt(1, id)
            int rows = stmt.executeUpdate()
            stmt.close()
            if (rows > 0) {
                println " Competência com ID ${id} deletada com sucesso!"
            } else {
                println "⚠Nenhuma competência encontrada com ID ${id}."
            }
        } finally {
            dados.desconectar()
        }
}*/