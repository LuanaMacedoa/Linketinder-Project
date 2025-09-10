Luana Gabriella: 
# Linketinder - MVP

## Descrição
MVP de sistema de contratação inspirado no **LinkedIn** e **Tinder**, permitindo que empresas encontrem candidatos com competências específicas de forma prática.

## Funcionalidades
- Listar candidatos (5 pré-cadastrados) com:
  - Nome, E-mail, CPF, Idade, Estado, CEP, Descrição e Competências
- Listar empresas (5 pré-cadastradas) com:
  - Nome, E-mail, CNPJ, País, Estado, CEP, Descrição e Competências
- Menu simples no terminal para visualizar os dados

## Estrutura
- `Pessoa.groovy` → classe base  
- `PessoaFisica.groovy` → candidatos  
- `PessoaJuridica.groovy` → empresas  
- `Dados.groovy` → arrays com objetos pré-cadastrados  
- `Main.groovy` → menu e interação com usuário
