package br.com.fiap.soulup.dominio;

public class AcaoPublicada {
    private int id; // Agregado para la base de datos
    private Grupo grupo;
    private Desafio desafio;
    private boolean consolidada;
    private int validacoesRecebidas;


    public AcaoPublicada() {
    }

    public AcaoPublicada(int id, Grupo grupo, Desafio desafio) {
        this.id = id;
        this.grupo = grupo;
        this.desafio = desafio;
        this.consolidada = false;
        this.validacoesRecebidas = 0;
    }


    public int lerId() {
        return id;
    }

    public void receberValidacao(Validador validador) {
        if (consolidada == true) {
            throw new UnsupportedOperationException("Esta acao ja esta consolidada e finalizada.");
        }

        validacoesRecebidas++;
        validador.adicionarPontos(25);

        if (validacoesRecebidas >= 2) {
            consolidada = true;
            grupo.adicionarPontos(desafio.lerPontos());
        }
    }

    public Desafio lerDesafio() {
        return desafio;
    }

    public Grupo lerGrupo() {
        return grupo;
    }

    public boolean estaConsolidada() {
        return consolidada;
    }

    public String exibir() {
        String texto = "Acao do Grupo '" + grupo.lerNome() + "' no desafio '" + desafio.lerTitulo() + "'";
        if (consolidada == true) {
            texto += " [CONSOLIDADA - " + desafio.lerPontos() + " pts creditados ao grupo]";
        } else {
            texto += " [PENDENTE - " + validacoesRecebidas + "/2 validacoes necessarias]";
        }
        return texto;
    }
}