package br.com.nobre.commons.exception;

public class InvalidParamsException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    public InvalidParamsException( String message) {
        super(message); 
    }
	
}
