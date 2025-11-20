package org.example

import org.example.views.InterfaceCandidato
import org.example.views.InterfaceEmpresa
import org.example.views.InterfaceVaga
import org.example.connection.ConnectionFactorySingleton

import java.sql.Connection

class Main {

    static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))

        InterfaceCandidato interfaceCandidato = new InterfaceCandidato()
        InterfaceEmpresa interfaceEmpresa = new InterfaceEmpresa()
        InterfaceVaga interfaceVaga = new InterfaceVaga()

        while (true) {
            println "\n===== MENU PRINCIPAL ====="
            println "1 - Gerenciar Candidatos"
            println "2 - Gerenciar Empresas"
            println "3 - Gerenciar Vagas"
            println "0 - Sair"
            print "Escolha uma opção: "
            String opcao = reader.readLine()

            switch (opcao) {
                case "1":
                    menuCandidatos(reader, interfaceCandidato)
                    break
                case "2":
                    menuEmpresas(reader, interfaceEmpresa)
                    break
                case "3":
                    menuVagas(reader, interfaceVaga)
                    break
                case "0":
                    println "Saindo..."
                    System.exit(0)
                    break
                default:
                    println "Opção inválida!"
            }
        }
    }

    static void menuCandidatos(BufferedReader reader, InterfaceCandidato ic) {
        while (true) {
            println "\n--- CANDIDATOS ---"
            println "1 - Listar candidatos"
            println "2 - Adicionar candidato"
            println "3 - Atualizar candidato"
            println "4 - Remover candidato"
            println "0 - Voltar"
            print "Escolha: "
            String opcao = reader.readLine()

            Connection conn = ConnectionFactorySingleton.getInstance().getConnection()
            try {
                switch (opcao) {
                    case "1": ic.mostrarCandidatos(); break
                    case "2": ic.adicionarCandidato(reader); break
                    case "3": ic.atualizarCandidato(reader); break
                    case "4": ic.removerCandidato(reader); break
                    case "0": return
                    default: println "Opção inválida!"
                }
            } finally {
                conn.close()
            }
        }
    }

    static void menuEmpresas(BufferedReader reader, InterfaceEmpresa ie) {
        while (true) {
            println "\n--- EMPRESAS ---"
            println "1 - Listar empresas"
            println "2 - Adicionar empresa"
            println "3 - Atualizar empresa"
            println "4 - Remover empresa"
            println "0 - Voltar"
            print "Escolha: "
            String opcao = reader.readLine()

            try {
                switch (opcao) {
                    case "1":
                        ie.mostrarEmpresas()
                        break
                    case "2":
                        ie.adicionarEmpresa(reader)
                        break
                    case "3":
                        ie.atualizarEmpresa(reader)
                        break
                    case "4":
                        ie.removerEmpresa(reader)
                        break
                    case "0":
                        return
                    default:
                        println "Opção inválida!"
                }
            } catch (Exception e) {
                println "Erro ao processar a operação: ${e.message}"
            }
        }
    }

    static void menuVagas(BufferedReader reader, InterfaceVaga iv) {
        while (true) {
            println "\n--- VAGAS ---"
            println "1 - Listar vagas"
            println "2 - Adicionar vaga"
            println "3 - Atualizar vaga"
            println "4 - Remover vaga"
            println "0 - Voltar"
            print "Escolha: "
            String opcao = reader.readLine()

            try {
                switch (opcao) {
                    case "1":
                        iv.mostrarVagas()  // Corrigido para o método correto
                        break
                    case "2":
                        iv.cadastrarVaga(reader)
                        break
                    case "3":
                        iv.atualizarVaga(reader)
                        break
                    case "4":
                        iv.deletarVaga(reader)
                        break
                    case "0":
                        return
                    default:
                        println "Opção inválida!"
                }
            } catch (Exception e) {
                println "Erro ao processar a operação: ${e.message}"
            }
        }
    }
}
