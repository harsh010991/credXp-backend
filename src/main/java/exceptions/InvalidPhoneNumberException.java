package exceptions;

public class InvalidPhoneNumberException extends Exception{
    public InvalidPhoneNumberException(String s) {
        super(s);
    }

    public InvalidPhoneNumberException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidPhoneNumberException(Throwable throwable) {
        super(throwable);
    }
}
