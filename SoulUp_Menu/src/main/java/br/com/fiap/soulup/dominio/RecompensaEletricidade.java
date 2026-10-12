package br.com.fiap.soulup.dominio;

public class RecompensaEletricidade extends Recompensa {
    public RecompensaEletricidade(int custoPontos) {
        super(custoPontos);
    }

    @Override
    public String aplicarRecompensa() {
        return "SUCESSO: Pontos convertidos em desconto para abater na fatura de Eletricidade!";
    }
}
