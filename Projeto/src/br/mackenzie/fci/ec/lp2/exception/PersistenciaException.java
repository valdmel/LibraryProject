package br.mackenzie.fci.ec.lp2.exception;

public class PersistenciaException extends Exception
{
    public PersistenciaException()
    {

    }

    public PersistenciaException(String message)
    {
        super(message);
    }

    public PersistenciaException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PersistenciaException(Throwable cause)
    {
        super(cause);
    }

    public PersistenciaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
