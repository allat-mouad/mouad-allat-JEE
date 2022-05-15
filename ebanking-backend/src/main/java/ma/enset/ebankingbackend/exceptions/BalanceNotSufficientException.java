package ma.enset.ebankingbackend.exceptions;

public class BalanceNotSufficientException extends Throwable {
    public BalanceNotSufficientException(String msg) {
        super(msg);
    }
}
