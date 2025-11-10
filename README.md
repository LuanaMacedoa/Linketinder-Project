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


# LinkeTinder - Entrega Clean Code

## Alterações principais

- **Modularização**: DAOs separados por entidade (`CandidatoDAO`, `EmpresaDAO`, `VagaDAO`, `CompetenciaDAO`).
- **Classe de Conexão**: `ConexaoDB` centraliza conexão com o banco e fechamento seguro.
- **Redução de repetição**: Métodos reutilizáveis, especialmente para competências.
- **Nomes claros**: Métodos e variáveis descritivos e consistentes.
- **Tratamento de exceções**: Conexão e queries protegidas com try/catch.
- **Separação de responsabilidades**: 
  - DAOs → CRUD  
  - Interfaces → interação com usuário  
  - Main → menu e fluxo de execução  
  - ConexaoDB → conexão com banco
- **Formatação consistente**: código mais legível e padronizado.

**Resultado:** código mais limpo, modular e fácil de manter.

## Princípios SOLID Aplicados

1. **Responsabilidade Única (SRP)**: A lógica de persistência foi movida para classes DAO (como `EmpresaDAO`), deixando as classes de interface com responsabilidades únicas.
2. **Aberto/Fechado (OCP)**: O código foi estruturado para ser extensível sem modificar as classes existentes, como a separação das operações de CRUD.
3. **Substituição de Liskov (LSP)**: As subclasses `PessoaFisica` e `PessoaJuridica` podem substituir `Pessoa` sem problemas, mantendo o comportamento esperado.
4. **Segregação de Interface (ISP)**: Métodos foram organizados para que cada classe ou interface tenha apenas o que precisa, evitando dependências desnecessárias.
5. **Inversão de Dependência (DIP)**: As dependências, como `EmpresaDAO`, são injetadas nas classes de interface, reduzindo o acoplamento.

## Principais Mudanças

- **Refatoração das classes de interface** (ex: `InterfaceEmpresa`), delegando responsabilidades de persistência para o `EmpresaDAO`.
- **Injeção de dependência** para reduzir o acoplamento direto entre as classes de lógica de negócios e persistência.
- **Estrutura modular** e mais **testável**, seguindo os princípios SOLID.

  
## Mudanças com Design Patterns

- Implementado **Singleton** para gerenciar a conexão com o banco de dados (`ConnectionFactorySingleton`), garantindo apenas uma instância de conexão.
- Refatorado código para aplicar **DAO (Data Access Object)**, separando a lógica de acesso a dados das classes de negócio.
- Essas mudanças melhoram a manutenção, facilitam testes e permitem trocar facilmente o tipo de banco de dados no futuro.
