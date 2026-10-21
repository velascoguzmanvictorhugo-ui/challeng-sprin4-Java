package br.com.fiap.soulup.dominio;

public class Validador extends Usuario {
    private int pontosAdquiridos;

    public Validador() {
        super(0,"","");
        this.pontosAdquiridos = 0;
    }
    public Validador(int id, String nome, String email) {
        super(id, nome, email);
        this.pontosAdquiridos = 0;
    }

    public void adicionarPontos(int pontos) {
        this.pontosAdquiridos += pontos;
    }

    public void resgatarRecompensa(Recompensa recompensa) {
        if (this.pontosAdquiridos < recompensa.lerCusto()) {
            throw new IllegalArgumentException("Pontos insuficientes para resgatar esta recompensa. Custo: "
                    + recompensa.lerCusto() + " pts | Saldo: " + this.pontosAdquiridos + " pts.");
        }
        this.pontosAdquiridos -= recompensa.lerCusto();
    }

    public int lerPontos() {
        return pontosAdquiridos;
    }

    @Override
    public String exibirPerfil() {
        return "Usuario: " + lerNome() + " (ID: " + lerId() + " | Email: " + lerEmail() + ") | Saldo: " + pontosAdquiridos + " pts";
    }
}
