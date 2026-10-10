package banco;

/** Conta corrente: permite usar um limite de cheque especial. */
public class ContaCorrente extends Conta {
    private final double limite;

    public ContaCorrente(String titular, double depositoInicial, double limite) {
        super(titular, depositoInicial);
        this.limite = limite;
    }

    @Override
    public double saldoDisponivel() {
        return saldo + limite;
    }

    @Override
    public String tipo() {
        return "Conta Corrente";
    }
}
