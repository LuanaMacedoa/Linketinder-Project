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


## MODELO DIAGRAMA MER
- utilizado: brmodelo
<img width="1510" height="827" alt="Captura de tela 2025-10-12 123853" src="https://github.com/user-attachments/assets/044d922b-235e-4c57-9972-5d4266cc956b" />

## MODELO DIAGRAMA DER
- utilizado: dbdiagram
<img width="1658" height="750" alt="Captura de tela 2025-10-12 195055" src="https://github.com/user-attachments/assets/e48c1e62-800e-4d99-9615-24f5981cdd43" />



