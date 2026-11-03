package br.com.fiap.soulup.dominio;

public class RecompensaCurso extends Recompensa {

    private String nomeCurso;


    public RecompensaCurso() {
        super();
    }


    public RecompensaCurso(int id, String descricao, int custoPontos, String nomeCurso) {
        super(id, descricao, custoPontos); // Esto soluciona el error rojo de la línea 7
        this.nomeCurso = nomeCurso;
    }

    public String lerNomeCurso() {
        return nomeCurso;
    }

    @Override
    public String aplicarRecompensa() {
        return "Curso '" + nomeCurso + "' liberado para o usuario!";
    }
}