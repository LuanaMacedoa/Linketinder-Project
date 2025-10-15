package org.example

import java.sql.*
import java.time.LocalDate

class Dados {
    Connection conn

    void conectar() {
        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/linketinder",
                "postgres",
                "senha"
        )
    }

    void desconectar() {
        if (conn != null) conn.close()
    }

    void inserirCandidato(PessoaFisica candidato, String senha = null, LocalDate dataNasc = null) {
        conectar()
        String sql = """INSERT INTO Candidato(nome, sobrenome, cpf, email, pais, cep, data_nasc, desc_pessoal, senha)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_candidato"""
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setString(1, candidato.nome)
        stmt.setString(2, candidato.sobrenome)
        stmt.setString(3, candidato.cpf)
        stmt.setString(4, candidato.email)
        stmt.setString(5, candidato.pais)
        stmt.setString(6, candidato.cep)
        stmt.setDate(7, java.sql.Date.valueOf(dataNasc ?: LocalDate.now()))
        stmt.setString(8, candidato.descricaoPessoal)
        stmt.setString(9, senha ?: "1234")
        ResultSet rs = stmt.executeQuery()
        rs.next()
        candidato.id = rs.getInt("id_candidato")
        stmt.close()

        candidato.competencias.each { comp ->
            int compId = 0
            PreparedStatement stmtComp = conn.prepareStatement(
                    "SELECT id_competencia FROM Competencia WHERE nome_competencia = ?")
            stmtComp.setString(1, comp)
            ResultSet rsComp = stmtComp.executeQuery()
            if (rsComp.next()) {
                compId = rsComp.getInt("id_competencia")
            } else {
                PreparedStatement stmtInsert = conn.prepareStatement(
                        "INSERT INTO Competencia(nome_competencia) VALUES(?) RETURNING id_competencia")
                stmtInsert.setString(1, comp)
                ResultSet rsInsert = stmtInsert.executeQuery()
                rsInsert.next()
                compId = rsInsert.getInt("id_competencia")
                stmtInsert.close()
            }
            stmtComp.close()

            PreparedStatement stmtRel = conn.prepareStatement(
                    "INSERT INTO Candidato_competencia(id_candidato, id_competencia) VALUES (?, ?)")
            stmtRel.setInt(1, candidato.id)
            stmtRel.setInt(2, compId)
            stmtRel.executeUpdate()
            stmtRel.close()
        }
        desconectar()
    }

    List<PessoaFisica> listarCandidatos() {
        conectar()
        List<PessoaFisica> lista = []
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Candidato")
        while (rs.next()) {
            PessoaFisica c = new PessoaFisica(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("email"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getString("cpf"),
                    rs.getString("desc_pessoal"),
                    [],
                    rs.getDate("data_nasc")?.toLocalDate(),
                    rs.getString("senha")// competências serão adicionadas depois se precisar
            )
            c.id = rs.getInt("id_candidato")
            lista.add(c)
        }
        desconectar()
        return lista
    }

    void atualizarCandidato(PessoaFisica c) {
        conectar()
        String sql = """UPDATE Candidato SET nome=?, email=?, cep=?, desc_pessoal=? WHERE id_candidato=?"""
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setString(1, c.nome)
        stmt.setString(2, c.email)
        stmt.setString(3, c.cep)
        stmt.setString(4, c.descricaoPessoal)
        stmt.setInt(5, c.id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void deletarCandidato(int id) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Candidato WHERE id_candidato=?")
        stmt.setInt(1, id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void inserirEmpresa(PessoaJuridica empresa, String senha = null) {
        conectar()
        String sql = """INSERT INTO Empresa(cnpj, nome_emp, email_corporativo, cep, desc_empresa, pais, senha)
                        VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_empresa"""
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setString(1, empresa.cnpj)
        stmt.setString(2, empresa.nome)
        stmt.setString(3, empresa.email)
        stmt.setString(4, empresa.cep)
        stmt.setString(5, empresa.descricaoEmpresa)
        stmt.setString(6, empresa.pais)
        stmt.setString(7, senha ?: "1234")
        ResultSet rs = stmt.executeQuery()
        rs.next()
        empresa.id = rs.getInt("id_empresa")
        stmt.close()
        desconectar()
    }

    List<PessoaJuridica> listarEmpresas() {
        conectar()
        List<PessoaJuridica> lista = []
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Empresa")
        while (rs.next()) {
            PessoaJuridica e = new PessoaJuridica(
                    rs.getString("nome_emp"),
                    rs.getString("email_corporativo"),
                    rs.getString("cep") ?: "",
                    rs.getString("cnpj"),
                    rs.getString("pais"),
                    rs.getString("desc_empresa"),
                    [],           // competências (vazio por enquanto)
                    rs.getString("senha")
            )
            e.id = rs.getInt("id_empresa")
            lista.add(e)
        }
        desconectar()
        return lista
    }


    void atualizarEmpresa(PessoaJuridica e) {
        conectar()
        String sql = """UPDATE Empresa SET nome_emp=?, email_corporativo=?, cep=?, desc_empresa=?, pais=? WHERE id_empresa=?"""
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setString(1, e.nome)
        stmt.setString(2, e.email)
        stmt.setString(3, e.cep)
        stmt.setString(4, e.descricaoEmpresa)
        stmt.setString(5, e.pais)
        stmt.setInt(6, e.id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void deletarEmpresa(int id) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Empresa WHERE id_empresa=?")
        stmt.setInt(1, id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void inserirVaga(Vaga vaga) {
        conectar()
        try {
            // 1️⃣ Inserir a vaga e pegar o ID gerado
            def stmt = conn.prepareStatement("""
            INSERT INTO vaga (id_empresa, nome_vaga, desc_vaga, localizacao)
            VALUES (?, ?, ?, ?) RETURNING id_vaga
        """)
            stmt.setInt(1, vaga.idEmpresa)
            stmt.setString(2, vaga.nomeVaga)
            stmt.setString(3, vaga.descricaoVaga)
            stmt.setString(4, "Remoto")
            def rs = stmt.executeQuery()
            rs.next()
            vaga.id = rs.getInt("id_vaga")
            rs.close()
            stmt.close()

            // 2️⃣ Inserir competências e associar à vaga
            vaga.competencias.each { comp ->
                // Inserir na tabela Competencia se não existir
                def stmtComp = conn.prepareStatement("""
                INSERT INTO competencia (nome_competencia)
                VALUES (?)
                ON CONFLICT (nome_competencia) DO NOTHING
            """)
                stmtComp.setString(1, comp)
                stmtComp.executeUpdate()
                stmtComp.close()

                // Associar vaga à competência
                def stmtAssoc = conn.prepareStatement("""
                INSERT INTO vaga_competencia (id_vaga, id_competencia)
                SELECT ?, id_competencia FROM competencia WHERE nome_competencia = ?
                ON CONFLICT (id_vaga, id_competencia) DO NOTHING
            """)
                stmtAssoc.setInt(1, vaga.id)
                stmtAssoc.setString(2, comp)
                stmtAssoc.executeUpdate()
                stmtAssoc.close()
            }

            println "Vaga '${vaga.nomeVaga}' inserida com sucesso!"
        } finally {
            desconectar()
        }
    }



    List<Vaga> listarVagas() {
        conectar()
        List<Vaga> lista = []
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM vaga")
        while (rs.next()) {
            Vaga v = new Vaga(
                    rs.getString("nome_vaga"),
                    rs.getString("desc_vaga"),
                    [],
                    rs.getInt("id_empresa")
            )
            v.id = rs.getInt("id_vaga")
            lista.add(v)
        }
        desconectar()
        return lista
    }

    void atualizarVaga(Vaga v) {
        conectar()
        String sql = """UPDATE vaga SET nome_vaga=?, desc_vaga=? WHERE id_vaga=?"""
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setString(1, v.nomeVaga)
        stmt.setString(2, v.descricaoVaga)
        stmt.setInt(3, v.id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void deletarVaga(int id) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM vaga WHERE id_vaga=?")
        stmt.setInt(1, id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void inserirCompetencia(String nome) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("""
            INSERT INTO Competencia(nome_competencia)
            VALUES (?)
            ON CONFLICT (nome_competencia) DO NOTHING
        """)
        stmt.setString(1, nome)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    Integer buscarCompetenciaPorNome(String nome) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("SELECT id_competencia FROM Competencia WHERE nome_competencia=?")
        stmt.setString(1, nome)
        ResultSet rs = stmt.executeQuery()
        Integer id = rs.next() ? rs.getInt("id_competencia") : null
        stmt.close()
        desconectar()
        return id
    }

    void atualizarCompetencia(int id, String novoNome) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("UPDATE Competencia SET nome_competencia=? WHERE id_competencia=?")
        stmt.setString(1, novoNome)
        stmt.setInt(2, id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }

    void deletarCompetencia(int id) {
        conectar()
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Competencia WHERE id_competencia=?")
        stmt.setInt(1, id)
        stmt.executeUpdate()
        stmt.close()
        desconectar()
    }
}



