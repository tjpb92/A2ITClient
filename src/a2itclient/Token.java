package a2itclient;

/**
 * Classe décrivant un token
 *
 * @author Thierry Baribaud
 * @version 1.02
 */
public class Token {

    /**
     * Token permettant l'accès à l'API REST
     */
    private String access_token;

    /**
     * Type de token
     */
    private String token_type;

    /**
     * Délai d'expiration exprimé en secondes
     */
    private int expires_in;

    /**
     * @return retourne le token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * @param access_token définit le token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * @return retourne le type de token
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * @param token_type définit le type de token
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * @return retourne le délai d'expiration du token (secondes)
     */
    public int getExpires_in() {
        return expires_in;
    }

    /**
     * @param expires_in définit le délai d'expiration du token (seconde)
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * Retourne le contenu de Token
     *
     * @return retourne le contenu de Token
     */
    @Override
    public String toString() {
        return "token:{"
                + "access_token:" + access_token
                + ", token_type:" + token_type
                + ", expires_in:" + expires_in
                + "}";
    }
}
