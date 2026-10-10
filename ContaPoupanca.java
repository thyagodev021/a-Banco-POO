package banco;

/** Conta poupança: não tem limite, mas rende juros. */
public class ContaPoupanca extends Conta {
    private final double taxaRendimento;

    public ContaPoupanca(String titular, double depositoInicial, double taxaRendimento) {
        super(titular, depositoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    /** Aplica o rendimento do mês sobre o saldo atual. */
    public void renderJuros() {
        double juros = saldo * taxaRendimento;
        saldo += juros;
        registrar(String.format("Rendimento: R$ %.2f", juros));
    }

    @Override
    public double saldoDisponivel() {
        return saldo;
    }

    @Override
    public String tipo() {
        return "Conta Poupança";
    }
}
