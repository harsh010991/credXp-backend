package exceptions;

public class HibernateDaoException extends Exception{
    public HibernateDaoException(String s) {
        super(s);
    }

    public HibernateDaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public HibernateDaoException(Throwable throwable) {
        super(throwable);
    }
}
