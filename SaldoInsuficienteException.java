package banco;

/** Erro lançado quando o saque ou a transferência passa do limite disponível. */
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
