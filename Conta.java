package banco;

import java.util.ArrayList;
import java.util.List;

/** Classe base (abstrata) de toda conta do banco. */
public abstract class Conta {
    private static int proximoNumero = 1001;

    private final int numero;
    private final String titular;
    protected double saldo;
    private final List<String> extrato = new ArrayList<>();

    protected Conta(String titular, double depositoInicial) {
        this.numero = proximoNumero++;
        this.titular = titular;
        if (depositoInicial > 0) {
            this.saldo = depositoInicial;
            extrato.add(String.format("Depósito inicial: R$ %.2f", depositoInicial));
        }
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        saldo += valor;
        registrar(String.format("Depósito: R$ %.2f", valor));
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        if (valor > saldoDisponivel()) {
            throw new SaldoInsuficienteException(
                    String.format("Saldo insuficiente. Disponível: R$ %.2f", saldoDisponivel()));
        }
        saldo -= valor;
        registrar(String.format("Saque: R$ %.2f", valor));
    }

    public void transferir(Conta destino, double valor) throws SaldoInsuficienteException {
        sacar(valor);
        destino.depositar(valor);
        registrar(String.format("Transferência enviada para conta %d: R$ %.2f", destino.numero, valor));
    }

    /** Cada tipo de conta define quanto pode ser sacado. */
    public abstract double saldoDisponivel();

    public abstract String tipo();

    protected void registrar(String linha) {
        extrato.add(linha);
    }

    public int getNumero() { return numero; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public List<String> getExtrato() { return extrato; }

    @Override
    public String toString() {
        return String.format("%s nº %d | %s | Saldo: R$ %.2f", tipo(), numero, titular, saldo);
    }
}
