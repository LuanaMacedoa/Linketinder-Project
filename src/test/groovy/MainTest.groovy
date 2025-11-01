import org.example.DAO
import org.example.PessoaJuridica

class MainTest {
    static void main(String[] args) {
        def dados = new DAO()

        def empresa = new PessoaJuridica(
                "Empresa yx",
                "contatooooo0@empresa.com",
                "59010-000",
                "11.342.698/0012-90",
                "brasil",
                "Empresa de testes",
                ["Gestão", "TI"],
                "senha123"
        )


        dados.inserirEmpresa(empresa)
        println "Empresa inserida com ID: ${empresa.id}"


        def empresas = dados.listarEmpresas()
        println "📋 Lista de empresas:"
        empresas.each { e ->
            println "ID: ${e.id}, Nome: ${e.nome}, Email: ${e.email}, Competências: ${e.competencias}"
        }


        empresa.nome = "Empresa Atualizada"
        empresa.descricaoEmpresa = "Descrição atualizada"
        dados.atualizarEmpresa(empresa)
        println "✏ Empresa atualizada!"


        empresas = dados.listarEmpresas()
        println " Lista de empresas após update:"
        empresas.each { e ->
            println "ID: ${e.id}, Nome: ${e.nome}, Descrição: ${e.descricaoEmpresa}"
        }

    /*
        // dados.deletarEmpresa(empresa.id)
        // println "Empresa deletada!"


        def vaga = new Vaga(
                "Desenvolvedor Java",
                "Vaga para programador Java",
                ["Java", "SQL"],
                empresa.id
        )

        dados.inserirVaga(vaga)
        println " Vaga inserida com ID: ${vaga.id}"

        def vagas = dados.listarVagas()
        println " Lista de vagas:"
        vagas.each { v ->
            println "ID: ${v.id}, Nome: ${v.nomeVaga}, Empresa ID: ${v.idEmpresa}"
        }


        vaga.nomeVaga = "Desenvolvedor Java Atualizado"
        vaga.descricaoVaga = "Descrição atualizada"
        dados.atualizarVaga(vaga)
        println " Vaga atualizada!"


        vagas = dados.listarVagas()
        println " Lista de vagas após update:"
        vagas.each { v ->
            println "ID: ${v.id}, Nome: ${v.nomeVaga}, Empresa ID: ${v.idEmpresa}"
        }

        // Deletar vaga
        //dados.deletarVaga(vaga.id)
        //println " Vaga deletada!"


        def competencia = "Python Avançado"
        dados.inserirCompetencia(competencia)
        println " Competência '$competencia' inserida!"

        def idComp = dados.buscarCompetenciaPorNome(competencia)
        println " ID da competência '$competencia': $idComp"

    */
    }
}