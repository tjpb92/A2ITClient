package a2itclient;

/**
 * Exception lancée en cas d'erreur sur la connexion Https
 * @author Thierry Baribaud
 * @version 1.09
 */
public class HttpsClientException extends Exception {

    /**
     * Exception lancée en cas d'erreur sur la connexion Https
     * (sans message)
     */
    public HttpsClientException() {
        super();
    }

    /**
     * Exception lancée en cas d'erreur sur la connexion Https 
     * (avec message)
     *
     * @param msg the detail message.
     */
    public HttpsClientException(String msg) {
        super(msg);
    }
}
