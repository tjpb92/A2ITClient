package a2itclient;

/**
 * Classe décrivant un message
 *
 * @author Thierry Baribaud
 * @version 1.04
 */
public class Message {

    /**
     * Message
     */
    private String message;

    /**
     * Code du message
     */
    private String code;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retourne le contenu du message
     *
     * @return retourne le contenu du message
     */
    @Override
    public String toString() {
        return "message:{"
                + "message:" + message
                + ", code:" + code
                + "}";
    }
}
