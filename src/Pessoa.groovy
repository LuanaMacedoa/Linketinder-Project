class Pessoa {
    String nome
    String email
    String estado
    String cep
}

// Classe candidato
class PessoaFisica extends Pessoa {
    String cpf
    int idade
    String descricaoPessoal
    List<String> competencias

    // Construtor
    PessoaFisica(String nome, String email, String estado, String cep,
                 String cpf, int idade, String descricaoPessoal, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.estado = estado
        this.cep = cep
        this.cpf = cpf
        this.idade = idade
        this.descricaoPessoal = descricaoPessoal
        this.competencias = competencias
    }

    String toString(){
        return """Nome: $nome
        Email: $email
        Estado: $estado
        CEP: $cep
        CPF: $cpf
        Idade: $idade
        Descrição: $descricaoPessoal
        Competências: $competencias
        """
    }
}

// Classe empresa
class PessoaJuridica extends Pessoa {
    String cnpj
    String pais
    String descricaoEmpresa
    List<String> competencias

    // Construtor
    PessoaJuridica(String nome, String email, String estado, String cep,
                   String cnpj, String pais, String descricaoEmpresa, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.estado = estado
        this.cep = cep
        this.cnpj = cnpj
        this.pais = pais
        this.descricaoEmpresa = descricaoEmpresa
        this.competencias = competencias
    }


    String toString() {
        return """Nome: $nome
        Email: $email
        Estado: $estado
        CEP: $cep
        CNPJ: $cnpj
        País: $pais
        Descrição: $descricaoEmpresa
        Competências: $competencias
        """
    }
}
