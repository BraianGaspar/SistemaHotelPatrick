package sistema;

import java.util.ArrayList;
import java.util.Scanner;
import Objeto.fag.Quarto;
import Objeto.reservas.Reserva;

public class SistemaHotel {
    private static Scanner entrada = new Scanner(System.in);
    private static ArrayList<Quarto> listaQuartos = new ArrayList<>();
    private static ArrayList<Reserva> listaReservas = new ArrayList<>();

    public static void main(String[] args) {
        iniciarSistema();
    }

    public static void iniciarSistema() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Menu de Gerenciamento do Hotel ---");
            System.out.println("1. Cadastrar Quartos");
            System.out.println("2. Fazer Reservas");
            System.out.println("3. Verificar Ocupação");
            System.out.println("4. Exibir Histórico de Reservas");
            System.out.println("5. Check-in/Check-out");
            System.out.println("6. Sair");
            System.out.print("Selecione uma opção: ");
            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    adicionarQuartos();
                    break;
                case 2:
                    realizarReserva();
                    break;
                case 3:
                    visualizarOcupacao();
                    break;
                case 4:
                    exibirHistoricoReservas();
                    break;
                case 5:
                    gerenciarCheckInCheckOut();
                    break;
                case 6:
                    executando = false;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

   
    public static void adicionarQuartos() {
        System.out.print("Quantos quartos deseja adicionar? ");
        int totalQuartos = entrada.nextInt();
        entrada.nextLine();

        for (int i = 0; i < totalQuartos; i++) {
            System.out.print("Número do quarto: ");
            String numero = entrada.nextLine().trim();

            System.out.print("Tipo de quarto (solteiro, casal, suite): ");
            String tipo = entrada.nextLine().trim();

            System.out.print("Preço por diária: ");
            float valor = entrada.nextFloat();
            entrada.nextLine();

            System.out.print("Disponibilidade (disponível/ocupado): ");
            String disponibilidade = entrada.nextLine().trim();

            listaQuartos.add(new Quarto(numero, tipo, valor, disponibilidade));
        }
    }

  
    public static void realizarReserva() {
        System.out.print("Quantas reservas deseja registrar? ");
        int totalReservas = entrada.nextInt();
        entrada.nextLine();

        for (int i = 0; i < totalReservas; i++) {
            System.out.print("Nome do hóspede: ");
            String nomeHospede = entrada.nextLine().trim();

            System.out.print("Data de check-in: ");
            String dataEntrada = entrada.nextLine().trim();

            System.out.print("Data de check-out: ");
            String dataSaida = entrada.nextLine().trim();

            System.out.print("Número de quartos reservados: ");
            int qtdQuartos = entrada.nextInt();
            entrada.nextLine();

            System.out.print("Tipo de quarto reservado (solteiro, casal, suite): ");
            String tipoQuarto = entrada.nextLine().trim();

            listaReservas.add(new Reserva(nomeHospede, dataEntrada, dataSaida, qtdQuartos, tipoQuarto));

            atualizarQuarto(tipoQuarto);
        }
    }


    private static void atualizarQuarto(String tipoQuarto) {
        for (Quarto quarto : listaQuartos) {
            if (quarto.getTipo().equalsIgnoreCase(tipoQuarto) && quarto.getDisponibilidade().equalsIgnoreCase("disponível")) {
                quarto.setDisponibilidade("ocupado");
                System.out.println("Quarto " + quarto.getNumero() + " foi reservado.");
                return;
            }
        }
        System.out.println("Nenhum quarto disponível para o tipo selecionado.");
    }

    public static void visualizarOcupacao() {
        System.out.println("\n--- Relatório de Ocupação dos Quartos ---");
        for (Quarto quarto : listaQuartos) {
            if (quarto.getDisponibilidade().equalsIgnoreCase("ocupado")) {
                System.out.println("Quarto " + quarto.getNumero() + " - Tipo: " + quarto.getTipo() + " - Ocupado");
            }
        }
    }

    public static void exibirHistoricoReservas() {
        System.out.println("\n--- Histórico de Reservas ---");
        for (Reserva reserva : listaReservas) {
            System.out.println("Hóspede: " + reserva.getNomeHospede() + ", Check-in: " + reserva.getDataEntrada() +
                               ", Check-out: " + reserva.getDataSaida() + ", Quartos Reservados: " + reserva.getQtdQuartos() +
                               ", Tipo de Quarto: " + reserva.getTipoQuarto());
        }
    }

  
    public static void gerenciarCheckInCheckOut() {
        boolean gerenciar = true;
        while (gerenciar) {
            System.out.println("\n--- Check-in/Check-out ---");
            System.out.println("1. Realizar Check-in");
            System.out.println("2. Realizar Check-out");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Selecione uma opção: ");
            int opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    realizarCheckIn();
                    break;
                case 2:
                    realizarCheckOut();
                    break;
                case 3:
                    gerenciar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void realizarCheckIn() {
        System.out.print("Informe o número do quarto para check-in: ");
        String numeroQuarto = entrada.nextLine().trim();

        for (Quarto quarto : listaQuartos) {
            if (quarto.getNumero().equalsIgnoreCase(numeroQuarto) && quarto.getDisponibilidade().equalsIgnoreCase("disponível")) {
                quarto.setDisponibilidade("ocupado");
                System.out.println("Check-in realizado para o quarto " + numeroQuarto);
                return;
            }
            }
        System.out.println("Quarto indisponível ou não encontrado.");
    }

   
    public static void realizarCheckOut() {
        System.out.print("Informe o número do quarto para check-out: ");
        String numeroQuarto = entrada.nextLine().trim();

        for (Quarto quarto : listaQuartos) {
            if (quarto.getNumero().equalsIgnoreCase(numeroQuarto) && quarto.getDisponibilidade().equalsIgnoreCase("ocupado")) {
                quarto.setDisponibilidade("disponível");
                System.out.println("Check-out realizado para o quarto " + numeroQuarto);
                return;
            }
        }
        System.out.println("Quarto não encontrado ou já está disponível.");
    }

	@Override
	public String toString() {
		return "SistemaHotel []";
	}

	public static Scanner getEntrada() {
		return entrada;
	}

	public static void setEntrada(Scanner entrada) {
		SistemaHotel.entrada = entrada;
	}

	public static ArrayList<Quarto> getListaQuartos() {
		return listaQuartos;
	}

	public static void setListaQuartos(ArrayList<Quarto> listaQuartos) {
		SistemaHotel.listaQuartos = listaQuartos;
	}

	public static ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	public static void setListaReservas(ArrayList<Reserva> listaReservas) {
		SistemaHotel.listaReservas = listaReservas;
	}
    
}
