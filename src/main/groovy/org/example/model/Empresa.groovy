package org.example.model

class Empresa {
        String nome
        String email
        String cep
        String cnpj
        String pais
        String descricaoEmpresa
        List<String> competencias
        String senha
        int id

        Empresa(String nome, String email, String cep,
                String cnpj, String pais, String descricaoEmpresa, List<String> competencias,
                String senha) {
            this.nome = nome
            this.email = email
            this.cep = cep
            this.cnpj = cnpj
            this.pais = pais
            this.descricaoEmpresa = descricaoEmpresa
            this.competencias = competencias
            this.senha = senha
        }

        String toString() {
            return """Nome: $nome
        Email: $email
        CNPJ: $cnpj
        CEP: $cep
        País: $pais
        Descrição: $descricaoEmpresa
        Competências: $competencias
        Senha: $senha
        """
        }
}
