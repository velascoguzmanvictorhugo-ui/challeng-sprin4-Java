package br.com.fiap.soulup.main;


import br.com.fiap.soulup.dominio.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Avatar avatar = new Avatar("Bryan");

        Validador anaSilva = new Validador(1, "Ana Silva", "ana.silva@fiap.com.br");
        Validador carlosGomes = new Validador(2, "Carlos Gomes", "carlos.gomes@fiap.com.br");
        Validador beatrizSouza = new Validador(3, "Beatriz Souza", "beatriz.souza@fiap.com.br");

        ArrayList<Validador> validadores = new ArrayList<>();
        validadores.add(anaSilva);
        validadores.add(carlosGomes);
        validadores.add(beatrizSouza);

        Grupo ecoWarriors = new Grupo("Eco Warriors");
        Grupo greenForce = new Grupo("Green Force");
        Grupo sustentabilidadeSP = new Grupo("Sustentabilidade SP");

        ArrayList<Grupo> grupos = new ArrayList<>();
        grupos.add(ecoWarriors);
        grupos.add(greenForce);
        grupos.add(sustentabilidadeSP);

        DesafioPratico limpezaParque = new DesafioPratico("Limpeza do Parque Ibirapuera", 1500);
        DesafioPratico reciclarPlastico = new DesafioPratico("Reciclar 10kg de plastico", 800);
        DesafioEducacional cursoCompostagem = new DesafioEducacional("Curso Online de Compostagem", 500);

        ArrayList<Desafio> desafios = new ArrayList<>();
        desafios.add(limpezaParque);
        desafios.add(reciclarPlastico);
        desafios.add(cursoCompostagem);

        EmpresaParceira ecoTech = new EmpresaParceira("EcoTech", "Avenida Paulista, 1000");
        ecoTech.registrarDescontoCurso("Programacao Verde (EAD)");

        EmpresaParceira bioSolucoes = new EmpresaParceira("BioSolucoes", "Rua das Flores, 45");
        bioSolucoes.registrarDescontoCertificado("Certificacao Lixo Zero");

        EmpresaParceira energiaLimpa = new EmpresaParceira("EnergiaLimpa", "Praca do Sol, 99");
        energiaLimpa.registrarDescontoCurso("Instalacao de Paineis Solares");

        ArrayList<EmpresaParceira> empresas = new ArrayList<>();
        empresas.add(ecoTech);
        empresas.add(bioSolucoes);
        empresas.add(energiaLimpa);

        AcaoPublicada acaoInicial = new AcaoPublicada(ecoWarriors, limpezaParque);
        ArrayList<AcaoPublicada> acoes = new ArrayList<>();
        acoes.add(acaoInicial);

        System.out.println(avatar.darBoasVindas());

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n" + avatar.exibirMenuPrincipal());
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("Erro: Digite um numero valido.");
                continue;
            }

            if (opcao == 1) {
                System.out.println("\nCRIAR USUARIO / SELECIONAR USUARIO EXISTENTE");
                for (int i = 0; i < validadores.size(); i++) {
                    System.out.println((i + 1) + ". " + validadores.get(i).lerNome() + " (Saldo: " + validadores.get(i).lerPontos() + " pts)");
                }
                int opcaoNovoUsr = validadores.size() + 1;
                System.out.println(opcaoNovoUsr + ". [CRIAR NOVO USUARIO]");
                System.out.print("Sua escolha: ");
                Validador validadorAtual = null;
                try {
                    int escolhaUsr = Integer.parseInt(leitor.nextLine());
                    if (escolhaUsr == opcaoNovoUsr) {
                        System.out.print("Digite o ID (numero) do novo usuario: ");
                        int idNovo = Integer.parseInt(leitor.nextLine());
                        System.out.print("Digite o Nome do novo usuario: ");
                        String nomeNovo = leitor.nextLine();
                        System.out.print("Digite o Email do novo usuario: ");
                        String emailNovo = leitor.nextLine();
                        validadorAtual = new Validador(idNovo, nomeNovo, emailNovo);
                        validadores.add(validadorAtual);
                        System.out.println("\nUsuario '" + nomeNovo + "' criado com sucesso!");
                    } else if (escolhaUsr >= 1 && escolhaUsr <= validadores.size()) {
                        validadorAtual = validadores.get(escolhaUsr - 1);
                    } else {
                        System.out.println("Opcao invalida.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Opcao invalida.");
                    continue;
                }

                int subOpcao = -1;
                while (subOpcao != 0) {
                    System.out.println("\n[" + validadorAtual.lerNome() + "] O que deseja fazer?");
                    System.out.println(avatar.exibirMenuUsuario());
                    System.out.println("0. Voltar ao menu principal");
                    System.out.print("Escolha: ");
                    try {
                        subOpcao = Integer.parseInt(leitor.nextLine());
                    } catch (Exception e) {
                        continue;
                    }

                    if (subOpcao == 1) {
                        System.out.println("\nDESAFIOS REALIZADOS E POSSIVEIS A VALIDAR");
                        if (acoes.isEmpty()) {
                            System.out.println("Nenhuma acao pendente de validacao no momento.");
                        } else {
                            for (int i = 0; i < acoes.size(); i++) {
                                System.out.println((i + 1) + ". " + acoes.get(i).exibir());
                                System.out.println("Pontos ganhos ao validar: +25 pontos!");
                            }
                            System.out.print("\nDigite o numero do desafio que deseja validar (ou 0 para cancelar): ");
                            try {
                                int inputVal = Integer.parseInt(leitor.nextLine());
                                if (inputVal >= 1 && inputVal <= acoes.size()) {
                                    AcaoPublicada acaoAlvo = acoes.get(inputVal - 1);
                                    acaoAlvo.receberValidacao(validadorAtual);
                                    System.out.println("\n[SUCESSO] Validacao confirmada para: " + acaoAlvo.lerDesafio().lerTitulo());
                                    System.out.println("Voce faturou: +25 pontos!");
                                    System.out.println("\nRESUMO DO USUARIO");
                                    System.out.println(validadorAtual.exibirPerfil());
                                    oferecerRecompensa(leitor, validadorAtual);
                                }
                            } catch (Exception e) {
                                System.out.println("Aviso: " + e.getMessage());
                            }
                        }
                    } else if (subOpcao == 2) {
                        System.out.println("\nDESAFIOS DISPONIVEIS PARA REALIZAR");
                        if (desafios.isEmpty()) {
                            System.out.println("Nenhum desafio disponivel no catalogo.");
                        } else {
                            for (int i = 0; i < desafios.size(); i++) {
                                Desafio d = desafios.get(i);
                                System.out.println((i + 1) + ". " + d.lerTitulo());
                                System.out.println("Tipo: " + d.tipoDesafio());
                                System.out.println("Pontos que o grupo faturara ao concluir: " + d.lerPontuacao() + " pts");
                            }
                            System.out.print("\nDigite o numero do desafio que deseja realizar (ou 0 para cancelar): ");
                            try {
                                int inputDes = Integer.parseInt(leitor.nextLine());
                                if (inputDes >= 1 && inputDes <= desafios.size()) {
                                    Desafio desafioEscolhido = desafios.get(inputDes - 1);
                                    Grupo grupoExecutor = grupos.get(0);
                                    AcaoPublicada novaAcao = new AcaoPublicada(grupoExecutor, desafioEscolhido);
                                    acoes.add(novaAcao);
                                    System.out.println("\n[SUCESSO] DESAFIO INICIADO!");
                                    System.out.println("Desafio Escolhido: " + desafioEscolhido.lerTitulo());
                                    System.out.println("Pontos que serao faturados: " + desafioEscolhido.lerPontuacao() + " pontos!");
                                    System.out.println("\nRESUMO DA ACAO E DO GRUPO");
                                    System.out.println(novaAcao.exibir());
                                    System.out.println(grupoExecutor.exibirResumo());
                                    oferecerRecompensa(leitor, validadorAtual);
                                }
                            } catch (Exception e) {
                                System.out.println("Erro ao escolher o desafio.");
                            }
                        }
                    }
                }
            } else if (opcao == 2) {
                int subOpcao = -1;
                while (subOpcao != 0) {
                    System.out.println("\nMENU DE GRUPOS");
                    System.out.println(avatar.exibirMenuGrupo());
                    System.out.println("0. Voltar ao menu principal");
                    System.out.print("Escolha: ");
                    try {
                        subOpcao = Integer.parseInt(leitor.nextLine());
                    } catch (Exception e) {
                        continue;
                    }

                    if (subOpcao == 1) {
                        System.out.print("\nDigite o nome do novo grupo: ");
                        String nomeG = leitor.nextLine().trim();
                        if (nomeG.isEmpty()) {
                            System.out.println("Erro: O nome do grupo nao pode ser vazio.");
                            continue;
                        }
                        Grupo novoGrupo = new Grupo(nomeG);
                        grupos.add(novoGrupo);
                        System.out.println("\n[SUCESSO] Grupo '" + nomeG + "' criado e registrado na plataforma!");
                    } else if (subOpcao == 2) {
                        if (grupos.isEmpty()) {
                            System.out.println("\nErro: Nenhum grupo registrado.");
                            continue;
                        }
                        System.out.println("\nSelecione o grupo participante:");
                        for (int i = 0; i < grupos.size(); i++) {
                            System.out.println((i + 1) + ". " + grupos.get(i).lerNome() + " (Saldo: " + grupos.get(i).lerPontos() + " pts)");
                        }
                        System.out.print("Escolha o grupo: ");
                        Grupo grupoEscolhido = null;
                        try {
                            int gIdx = Integer.parseInt(leitor.nextLine());
                            if (gIdx >= 1 && gIdx <= grupos.size()) {
                                grupoEscolhido = grupos.get(gIdx - 1);
                            } else {
                                System.out.println("Grupo invalido.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Grupo invalido.");
                            continue;
                        }

                        System.out.println("\nDESAFIOS DISPONIVEIS PARA O GRUPO " + grupoEscolhido.lerNome());
                        for (int i = 0; i < desafios.size(); i++) {
                            Desafio d = desafios.get(i);
                            System.out.println((i + 1) + ". " + d.lerTitulo());
                            System.out.println("Tipo: " + d.tipoDesafio());
                            System.out.println("Pontuacao base: " + d.lerPontuacao() + " pts");
                        }
                        System.out.print("\nEscolha o numero do desafio desejado: ");
                        Desafio desafioEscolhido = null;
                        try {
                            int dIdx = Integer.parseInt(leitor.nextLine());
                            if (dIdx >= 1 && dIdx <= desafios.size()) {
                                desafioEscolhido = desafios.get(dIdx - 1);
                            } else {
                                System.out.println("Desafio invalido.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Desafio invalido.");
                            continue;
                        }

                        AcaoPublicada novaAcao = new AcaoPublicada(grupoEscolhido, desafioEscolhido);
                        acoes.add(novaAcao);

                        System.out.println("\nComo o grupo deseja receber o beneficio?");
                        System.out.println("  1. Pontuacao no Ranking (" + desafioEscolhido.lerPontuacao() + " pontos para o grupo)");
                        System.out.println("  2. Desconto e bolsa em Curso EAD de Capacitacao Profissional");
                        System.out.print("Sua escolha: ");
                        String escolhaBeneficio = leitor.nextLine();
                        System.out.println("\nRESUMO DO DESAFIO DO GRUPO:");
                        System.out.println("Grupo: " + grupoEscolhido.lerNome());
                        System.out.println("Desafio Selecionado: " + desafioEscolhido.lerTitulo());
                        if (escolhaBeneficio.equals("2")) {
                            System.out.println("Beneficio escolhido: Desconto/Bolsa em Curso EAD ao concluir.");
                        } else {
                            System.out.println("Beneficio escolhido: " + desafioEscolhido.lerPontuacao() + " pontos para o Ranking Soul Up.");
                        }
                        System.out.println("Status: " + novaAcao.exibir());
                    }
                }
            } else if (opcao == 3) {
                System.out.println("\nREGISTRAR NOVO DESAFIO");
                System.out.print("Titulo do desafio: ");
                String titulo = leitor.nextLine();
                System.out.print("Quantos pontos este desafio vai conceder? ");
                try {
                    int pts = Integer.parseInt(leitor.nextLine());
                    DesafioPratico d = new DesafioPratico(titulo, pts);
                    desafios.add(d);
                    System.out.println("\nDesafio registrado com sucesso!");
                    System.out.println("Titulo: " + d.lerTitulo() + " | Pontos a conceder: " + d.lerPontuacao() + " pts.");
                } catch (Exception e) {
                    System.out.println("Erro ao registrar desafio: " + e.getMessage());
                }
            } else if (opcao == 4) {
                System.out.println("\nSELECIONAR OU REGISTRAR EMPRESA PARCEIRA");
                for (int i = 0; i < empresas.size(); i++) {
                    System.out.println((i + 1) + ". Empresa: " + empresas.get(i).getNome());
                }
                int opcaoNovaEmp = empresas.size() + 1;
                System.out.println(opcaoNovaEmp + ". REGISTRAR NOVA EMPRESA");
                System.out.print("Escolha: ");
                EmpresaParceira empAtual = null;
                try {
                    int escolhaEmp = Integer.parseInt(leitor.nextLine());
                    if (escolhaEmp == opcaoNovaEmp) {
                        System.out.print("Nome da Empresa/Instituicao: ");
                        String nomeE = leitor.nextLine();
                        System.out.print("Endereco: ");
                        String endE = leitor.nextLine();
                        empAtual = new EmpresaParceira(nomeE, endE);
                        empresas.add(empAtual);
                        System.out.println("Empresa registrada com sucesso!");
                    } else if (escolhaEmp >= 1 && escolhaEmp <= empresas.size()) {
                        empAtual = empresas.get(escolhaEmp - 1);
                    } else {
                        System.out.println("Opcao invalida.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Opcao invalida.");
                    continue;
                }

                int subOpcao = -1;
                while (subOpcao != 0) {
                    System.out.println("\nMENU DA EMPRESA PARCEIRA");
                    System.out.println(avatar.exibirMenuEmpresa());
                    System.out.println("0. Voltar ao menu principal");
                    System.out.print("Escolha: ");
                    try {
                        subOpcao = Integer.parseInt(leitor.nextLine());
                    } catch (Exception e) {
                        continue;
                    }
                    if (subOpcao == 1) {
                        System.out.print("Nome do Curso EAD para conceder desconto: ");
                        empAtual.registrarDescontoCurso(leitor.nextLine());
                        System.out.println("Desconto em curso registrado com sucesso!");
                    } else if (subOpcao == 2) {
                        System.out.print("Nome do Certificado para conceder desconto: ");
                        empAtual.registrarDescontoCertificado(leitor.nextLine());
                        System.out.println("Desconto em certificado registrado com sucesso!");
                    } else if (subOpcao == 3) {
                        System.out.println("\nPerfil da Empresa");
                        System.out.println(empAtual.exibirPerfil());
                    }
                }
            }
        }
    }

    private static void oferecerRecompensa(Scanner leitor, Validador validador) {
        System.out.println("\nRESGATE DE RECOMPENSA (OPCIONAL)");
        System.out.println("Saldo disponivel: " + validador.lerPontos() + " pts");
        System.out.println("Deseja resgatar uma recompensa com seus pontos agora?");
        System.out.println("  1. Desconto na Conta de Eletricidade (Custa 50 pts)");
        System.out.println("  2. Desconto em Curso EAD (Custa 30 pts)");
        System.out.println("  0. Nao resgatar agora / Voltar");
        System.out.print("Sua escolha: ");
        String recOp = leitor.nextLine();
        Recompensa r = null;
        if (recOp.equals("1")) {
            r = new RecompensaEletricidade(50);
        } else if (recOp.equals("2")) {
            r = new RecompensaCurso(30, "Gestao Ambiental e Sustentabilidade");
        }
        if (r != null) {
            try {
                validador.resgatarRecompensa(r);
                System.out.println(r.aplicarRecompensa());
                System.out.println("Novo saldo restante: " + validador.lerPontos() + " pts.");
            } catch (Exception e) {
                System.out.println("Aviso: " + e.getMessage());
            }
        }
    }
}