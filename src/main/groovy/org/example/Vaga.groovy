package org.example

class Vaga {
        String nomeVaga
        String descricaoVaga
        List<String> competencias
        int idEmpresa
        int id

        Vaga(String nomeVaga, String descricaoVaga, List<String> competencias, int idEmpresa) {
            this.nomeVaga = nomeVaga
            this.descricaoVaga = descricaoVaga
            this.competencias = competencias
            this.idEmpresa = idEmpresa
        }

    String toString() {
        return "Vaga(id: $id, nome: $nomeVaga, descrição: $descricaoVaga, empresaId: $idEmpresa)"
    }
    }


