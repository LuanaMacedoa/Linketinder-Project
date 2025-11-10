import org.example.Pessoa
import org.example.connection.ConnectionFactorySingleton

import org.example.PessoaJuridica
import org.example.dao.EmpresaDAO
import org.junit.jupiter.api.Test

import java.sql.Connection;




class MainTest {


        @Test
        void testInserirEmpresas(){
            Connection conn = ConnectionFactorySingleton.getInstance().getConnection()

        try {
            EmpresaDAO dao = new EmpresaDAO();

            Pessoa empresa = new PessoaJuridica(
                    "Empresa yx",
                    "contatooooo0@empresa.com",
                    "59010-000",
                    "11.342.698/1112-90",
                    "brasil",
                    "Empresa de testes",
                    ["Gestão", "TI"],
                    "senha123"
            )


            dao.inserir(conn, empresa)
            assertNotNull(empresa.id)
            println "empresa inserida com sucesso"
        } finally {
            conn.close()
        }

    }
}