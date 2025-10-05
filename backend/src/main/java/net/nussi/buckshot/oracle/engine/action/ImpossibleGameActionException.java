package net.nussi.buckshot.oracle.engine.action;

public class ImpossibleGameActionException extends RuntimeException {
    public ImpossibleGameActionException() {
    }

    public ImpossibleGameActionException(String message) {
        super(message);
    }

    public ImpossibleGameActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImpossibleGameActionException(Throwable cause) {
        super(cause);
    }

    public ImpossibleGameActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
