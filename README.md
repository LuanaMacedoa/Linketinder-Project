Luana Gabriella:
# Linketinder - MVP

## Descrição
MVP de sistema de contratação inspirado no **LinkedIn** e **Tinder**, permitindo que empresas encontrem candidatos com competências específicas de forma prática.

## Funcionalidades
- Listar candidatos (5 pré-cadastrados) com:
  - Nome, E-mail, CPF, Idade, Estado, CEP, Descrição e Competências
- Listar empresas (5 pré-cadastradas) com:
  - Nome, E-mail, CNPJ, País, Estado, CEP, Descrição e Competências
- Cadastrar novos **candidatos** via input do usuário
- Cadastrar novas **empresas** via input do usuário
- Testes unitários implementados seguindo a abordagem **TDD**, verificando:
  - Criação de objetos candidatos e empresas
  - Incremento no tamanho das listas após adicionar novos elementos
  - Correspondência dos atributos dos objetos adicionados com os valores esperados
- Menu simples no terminal para visualizar os dados e cadastrar novos participantes

## Estrutura
- `Pessoa.groovy` → classe base  
- `PessoaFisica.groovy` → candidatos  
- `PessoaJuridica.groovy` → empresas  
- `Dados.groovy` → arrays com objetos pré-cadastrados, métodos de cadastro e inicialização  
- `InterfaceUsuario.groovy` → interface para cadastro e interação com usuários  
- `InterfaceEmpresa.groovy` → interface para cadastro e interação com empresas  
- `Main.groovy` → menu, interação com usuário e cadastro de novos candidatos/empresas  
- `Testes.groovy` → testes TDD validando criação de objetos, listas e atributos  


