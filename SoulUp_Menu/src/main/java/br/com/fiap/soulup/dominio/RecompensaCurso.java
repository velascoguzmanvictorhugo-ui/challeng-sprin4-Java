package br.com.fiap.soulup.dominio;

public class RecompensaCurso extends Recompensa {
    private String nomeCurso;

    public RecompensaCurso(int custoPontos, String nomeCurso) {
        super(custoPontos);
        this.nomeCurso = nomeCurso;
    }

    @Override
    public String aplicarRecompensa() {
        return "SUCESSO: Cupom de desconto gerado para o curso EAD: " + nomeCurso + "!";
    }
}
