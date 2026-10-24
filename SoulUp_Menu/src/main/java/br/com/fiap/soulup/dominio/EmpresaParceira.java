package br.com.fiap.soulup.dominio;

import java.util.ArrayList;

public class EmpresaParceira {

    private String nome;
    private String endereco;
    private ArrayList<String> cursosComDesconto;
    private ArrayList<String> certificadosComDesconto;

    public EmpresaParceira(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.cursosComDesconto = new ArrayList<>();
        this.certificadosComDesconto = new ArrayList<>();
    }


    public String getNome() {
        return this.nome;
    }

    public String lerNome() {
        return this.nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void registrarDescontoCurso(String curso) {
        if (curso != null && !curso.trim().isEmpty()) {
            this.cursosComDesconto.add(curso);
        }
    }

    public void registrarDescontoCertificado(String certificado) {
        if (certificado != null && !certificado.trim().isEmpty()) {
            this.certificadosComDesconto.add(certificado);
        }
    }

    public String exibirPerfil() {
        String info = "Empresa: " + this.nome + "\n"
                + "Endereco: " + this.endereco + "\n"
                + "Cursos com Desconto: " + (cursosComDesconto.isEmpty() ? "Nenhum" : cursosComDesconto) + "\n"
                + "Certificados com Desconto: " + (certificadosComDesconto.isEmpty() ? "Nenhum" : certificadosComDesconto);
        return info;
    }

    @Override
    public String toString() {
        return "EmpresaParceira [Nome: " + nome + ", Endereco: " + endereco + "]";
    }
}