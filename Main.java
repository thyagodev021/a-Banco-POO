package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Banco simples em Java (Programação Orientada a Objetos).
 * Autor: Thyago Ferreira (https://github.com/thyagodev021)
 */
public class Main {
    private static final List<Conta> contas = new ArrayList<>();
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🏦 Banco POO em Java - por Thyago Ferreira");

        int opcao;
        do {
            System.out.println("\n[1] Criar conta corrente  [2] Criar poupança  [3] Listar contas");
            System.out.println("[4] Depositar  [5] Sacar  [6] Transferir  [7] Extrato  [8] Render juros  [0] Sair");
            opcao = lerInt("Escolha: ");

            try {
                switch (opcao) {
                    case 1 -> criarCorrente();
                    case 2 -> criarPoupanca();
                    case 3 -> contas.forEach(System.out::println);
                    case 4 -> buscar("Conta").depositar(lerDouble("Valor: "));
                    case 5 -> buscar("Conta").sacar(lerDouble("Valor: "));
                    case 6 -> transferir();
                    case 7 -> buscar("Conta").getExtrato().forEach(System.out::println);
                    case 8 -> renderJuros();
                    case 0 -> System.out.println("Até logo!");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (SaldoInsuficienteException | IllegalArgumentException e) {
                System.out.println("⚠️  " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void criarCorrente() {
        String nome = lerTexto("Titular: ");
        double deposito = lerDouble("Depósito inicial: ");
        Conta c = new ContaCorrente(nome, deposito, 500.0);
        contas.add(c);
        System.out.println("Criada: " + c + " (limite de R$ 500,00)");
    }

    private static void criarPoupanca() {
        String nome = lerTexto("Titular: ");
        double deposito = lerDouble("Depósito inicial: ");
        Conta c = new ContaPoupanca(nome, deposito, 0.005);
        contas.add(c);
        System.out.println("Criada: " + c + " (rendimento de 0,5% ao mês)");
    }

    private static void transferir() throws SaldoInsuficienteException {
        Conta origem = buscar("Conta de origem");
        Conta destino = buscar("Conta de destino");
        origem.transferir(destino, lerDouble("Valor: "));
        System.out.println("Transferência realizada!");
    }

    private static void renderJuros() {
        Conta c = buscar("Conta");
        if (c instanceof ContaPoupanca poupanca) {
            poupanca.renderJuros();
            System.out.println("Juros aplicados. " + c);
        } else {
            System.out.println("Só contas poupança rendem juros.");
        }
    }

    private static Conta buscar(String rotulo) {
        int numero = lerInt(rotulo + " (número): ");
        return contas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
    }

    private static String lerTexto(String msg) {
        System.out.print(msg);
        return entrada.nextLine().trim();
    }

    private static int lerInt(String msg) {
        try {
            return Integer.parseInt(lerTexto(msg));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double lerDouble(String msg) {
        try {
            return Double.parseDouble(lerTexto(msg).replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
