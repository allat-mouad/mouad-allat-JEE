package ma.enset.ebankingbackend.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String message){
        super (message);
    }
    }
