package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Classe décrivant un client se connectant en HTTPS à un serveur
 *
 * @author Thierry Baribaud
 * @version 1.09
 */
public class HttpsClient extends OkHttpClient {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Paramètres du serveur
     */
    private APIREST apiRest;

    /**
     * Toke pour sécuriser la session
     */
    private Token token;

    /**
     * Constructeur principal de la classe
     *
     * @param apiRest paramètres du serveur
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws java.io.IOException en cas d'erreur de lecture du fichier des
     * propriétés
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public HttpsClient(APIREST apiRest, boolean debugMode) throws IOException, HttpsClientException {
        super();
        this.apiRest = apiRest;

        StringBuffer mediaTypeParams;
        MediaType mediaType;
        String json;
        int code;
        String message;

        mediaType = MediaType.parse("application/x-www-form-urlencoded");
        mediaTypeParams = new StringBuffer("grant_type=client_credentials&client_id=");
        mediaTypeParams.append(this.apiRest.getLogin());
        mediaTypeParams.append("&client_secret=");
        mediaTypeParams.append(this.apiRest.getPassword());
        if (debugMode) {
            System.out.println("mediaTypeParams:" + mediaTypeParams.toString());
        }
        RequestBody body = RequestBody.create(mediaType, mediaTypeParams.toString());
        Request request = new Request.Builder()
                .url("https://accountsandbox.hubintent.com/oauth/token")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = this.newCall(request).execute();
//            System.out.println("response:" + response);
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("response.code():" + code);
            System.out.println("response.message():" + message);
        }

        if (code == 200) {
            json = response.body().string();
            System.out.println("response.body():" + json);
//            System.out.println("response.headers():" + response.headers());
            if (json != null) {
                token = objectMapper.readValue(json, Token.class);
                System.out.println(token);
            }
        } else {
            throw new HttpsClientException(code + " " + message);
        }
    }

    /**
     * @return retourne le token sécurisant la connexion
     */
    public Token getToken() {
        return token;
    }

    /**
     * @param token définit le token sécurisant la connexion
     */
    public void setToken(Token token) {
        this.token = token;
    }
}
