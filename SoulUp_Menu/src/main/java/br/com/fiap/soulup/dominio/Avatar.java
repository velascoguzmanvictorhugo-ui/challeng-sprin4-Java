package br.com.fiap.soulup.dominio;

public class Avatar {
    private String nome;

    public Avatar(String nome) {
        this.nome = nome;
    }

    public String darBoasVindas() {
        return "Ola! Eu sou o Avatar " + nome + ". Bem-vindo a plataforma Soul Up! Estou gerenciando todas as acoes da rede.";
    }

    public String exibirMenuPrincipal() {
        return "1. Criar usuario / Validar Desafio\n" +
               "2. menu de grupos\n" +
               "3. Registrar desafio\n" +
               "4. Registrar empresa parceira\n" +
               "0. Sair do sistema";
    }

    public String exibirMenuUsuario() {
        return "  1. Validar desafio\n" +
               "  2. Realizar desafio\n" +
               "  0. Voltar ao menu principal";
    }

    public String exibirMenuGrupo() {
        return "  1. criar um novo grupo\n" +
               "  2. Mostrar desafios para o grupo\n" +
               "  0. Voltar ao menu principal";
    }

    public String exibirMenuEmpresa() {
        return "  1. Oferecer descontos em Cursos\n" +
               "  2. Oferecer descontos em Certificados\n" +
               "  3. Mostrar resumo e perfil da Empresa\n" +
               "  0. Voltar ao menu principal";
    }
}
