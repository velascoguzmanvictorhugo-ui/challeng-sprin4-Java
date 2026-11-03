package br.com.fiap.soulup.dominio;

public class RecompensaEletricidade extends Recompensa {

    private String mesReferencia;

    public RecompensaEletricidade() {
        super();
    }

    public RecompensaEletricidade(int id, String descricao, int custoPontos, String mesReferencia) {
        super(id, descricao, custoPontos); // Llama al nuevo constructor del padre
        this.mesReferencia = mesReferencia;
    }

    public String lerMesReferencia() {
        return mesReferencia;
    }

    @Override
    public String aplicarRecompensa() {
        return "Desconto na conta de luz aplicado para o mes " + mesReferencia + "!";
    }
}