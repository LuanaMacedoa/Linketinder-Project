package org.example

import java.sql.*

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

    void inserirCandidato(PessoaFisica candidato) {
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
        stmt.setDate(7, java.sql.Date.valueOf(candidato.dataNasc))
        stmt.setString(8, candidato.descricaoPessoal)
        stmt.setString(9, candidato.senha)
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
                    0,
                    rs.getString("desc_pessoal"),
                    rs.getString("senha"),
                    rs.getDate("data_nasc").toLocalDate()
            )
            c.id = rs.getInt("id_candidato")
            lista.add(c)
        }
        desconectar()
        return lista
    }


    void inserirEmpresa(PessoaJuridica empresa) {
        conectar()
        String sql = """INSERT INTO Empresa(cnpj, nome_emp, email_corporativo,cep, desc_empresa, pais, senha)
                        VALUES (?, ?, ?, ?, ?, ?,?) RETURNING id_empresa"""
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setString(1, empresa.cnpj)
        stmt.setString(2, empresa.nome)
        stmt.setString(3, empresa.email)
        stmt.setString(4, empresa.cep)
        stmt.setString(5, empresa.descricaoEmpresa)
        stmt.setString(6, empresa.pais)
        stmt.setString(7, empresa.senha)
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
                    [],
                    rs.getString("senha")
            )
            e.id = rs.getInt("id_empresa")
            lista.add(e)
        }
        desconectar()
        return lista
    }


    def inserirVaga(Vaga vaga) {
        conectar()
        try {
            def stmt = conn.prepareStatement("""
            INSERT INTO vaga (id_empresa, nome_vaga, desc_vaga, localizacao)
            VALUES (?, ?, ?, ?)
            RETURNING id_vaga
        """)
            stmt.setInt(1, vaga.idEmpresa)
            stmt.setString(2, vaga.nomeVaga)
            stmt.setString(3, vaga.descricaoVaga)
            stmt.setString(4, "Remoto")
            def rs = stmt.executeQuery()
            rs.next()
            def idVaga = rs.getInt("id_vaga")
            rs.close()
            stmt.close()

            vaga.competencias.each { comp ->
                def stmtComp = conn.prepareStatement("""
                INSERT INTO competencia (nome_competencia)
                VALUES (?)
                ON CONFLICT (nome_competencia) DO NOTHING
            """)
                stmtComp.setString(1, comp)
                stmtComp.executeUpdate()
                stmtComp.close()

                def stmtAssoc = conn.prepareStatement("""
                INSERT INTO vaga_competencia (id_vaga, id_competencia)
                SELECT ?, id_competencia FROM competencia WHERE nome_competencia = ?
                ON CONFLICT (id_vaga, id_competencia) DO NOTHING
            """)
                stmtAssoc.setInt(1, idVaga)
                stmtAssoc.setString(2, comp)
                stmtAssoc.executeUpdate()
                stmtAssoc.close()
            }

            println " Vaga '${vaga.nomeVaga}' inserida com sucesso!"
        } finally {
            desconectar()
        }
    }


}



