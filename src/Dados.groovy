class Dados {

    def candidatos = []
    def empresas = []

    void inicializar() {

        //candidatos
        def candidato1 = new PessoaFisica(
                "Ana Silva", "ana@gmail.com", "SP", "01000-000",
                "123.456.789-00", 28, "Desenvolvedora Java com 5 anos de experiência.",
                ["Java", "Spring Boot", "SQL"]
        )

        def candidato2 = new PessoaFisica(
                "Carlos Souza", "carlos@gmail.com", "RJ", "20000-000",
                "987.654.321-00", 35, "Engenheiro de software apaixonado por back-end.",
                ["Node.js", "MongoDB", "Docker"]
        )

        def candidato3 = new PessoaFisica(
                "Mariana Lima", "mariana@gmail.com", "MG", "30000-000",
                "111.222.333-44", 24, "Recém-formada buscando oportunidade como front-end.",
                ["HTML", "CSS", "JavaScript", "React"]
        )

        def candidato4 = new PessoaFisica(
                "João Pedro", "joao@gmail.com", "RS", "90000-000",
                "555.666.777-88", 40, "Analista de dados com foco em BI.",
                ["Power BI", "SQL", "Python"]
        )

        def candidato5 = new PessoaFisica(
                "Beatriz Rocha", "beatriz@gmail.com", "BA", "40000-000",
                "999.888.777-66", 31, "Product Owner com experiência em Scrum.",
                ["Scrum", "Kanban", "Gestão de Produto"]
        )

        candidatos << candidato1 << candidato2 << candidato3 << candidato4 << candidato5




        //Empresa
        def empresa1 = new PessoaJuridica(
                "Tech Solutions", "contato@tech.com", "SP", "01111-000",
                "12.345.678/0001-90", "Brasil", "Empresa de tecnologia focada em soluções web.",
                ["Java", "Angular", "PostgreSQL"]
        )

        def empresa2 = new PessoaJuridica(
                "HealthCorp", "rh@healthcorp.com", "RJ", "22000-000",
                "98.765.432/0001-10", "Brasil", "Startup da área de saúde com foco em IA.",
                ["Python", "Machine Learning", "Django"]
        )

        def empresa3 = new PessoaJuridica(
                "EduTech", "vagas@edutech.com", "MG", "31000-000",
                "33.444.555/0001-22", "Brasil", "Plataforma de educação digital.",
                ["React", "Node.js", "MongoDB"]
        )

        def empresa4 = new PessoaJuridica(
                "AgroDigital", "contato@agrodigital.com", "MT", "78000-000",
                "55.666.777/0001-33", "Brasil", "Tecnologia para o agronegócio.",
                ["IoT", "Big Data", "Java"]
        )

        def empresa5 = new PessoaJuridica(
                "FinServ", "recrutamento@finserv.com", "PR", "80000-000",
                "77.888.999/0001-44", "Brasil", "Serviços financeiros e bancários digitais.",
                ["Kotlin", "Android", "Segurança da Informação"]
        )

        empresas << empresa1 << empresa2 << empresa3  << empresa4 << empresa5

    }

    // adicionar novo candidato
    def adicionarCandidato(PessoaFisica candidato){
        candidatos << candidato
    }

    // adicionar nova empresa
    def adicionarEmpresa(PessoaJuridica empresa){
        empresas << empresa
    }
}

