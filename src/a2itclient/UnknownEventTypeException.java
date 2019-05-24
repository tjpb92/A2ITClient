package a2itclient;

/**
 * Exception lancée si l'indice d'un EventType est inconnu
 * @author Thierry Baribaud
 * @version 1.16
 */
public class UnknownEventTypeException extends Exception {

    /**
     * Exception lancée si l'indice d'un EventType est inconnu
     * (sans message)
     */
    public UnknownEventTypeException() {
        super();
    }

    /**
     * Exception lancée si l'indice d'un EventType est inconnu 
     * (avec message)
     *
     * @param msg the detail message.
     */
    public UnknownEventTypeException(String msg) {
        super(msg);
    }
}
