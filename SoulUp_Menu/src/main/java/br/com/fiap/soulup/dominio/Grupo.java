package br.com.fiap.soulup.dominio;

public class Grupo {
    private String nome;
    private int pontosAcumulados;

    public Grupo(String nome) {
        this.nome = nome;
        this.pontosAcumulados = 0;
    }

    public void adicionarPontos(int pontos) {
        this.pontosAcumulados += pontos;
    }
    
    public int lerPontos() {
        return pontosAcumulados; }
    public String lerNome() {
        return nome; }

    public String classificarNivel() {
        if (pontosAcumulados >= 6000) {
            return "Lider Verde";
        } else if (pontosAcumulados >= 3000) {
            return "Defensor";
        } else if (pontosAcumulados >= 1000) {
            return "Guardiao";
        } else {
            return "Broto Verde";
        }
    }

    public String exibirResumo() {
        return "Grupo: " + nome + " | Saldo de Pontos: " + pontosAcumulados + " | Nivel: " + classificarNivel();
    }
}
