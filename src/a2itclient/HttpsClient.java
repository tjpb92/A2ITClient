package a2itclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
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
 * @version 1.24
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

    /**
     * Créer une demande d'intervention sur la plate-forme
     *
     * @param openTicket commande d'ouverture de ticket
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws com.fasterxml.jackson.core.JsonProcessingException en cas
     * d'erreur de convertion au format Json
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public void openTicket(OpenTicket openTicket, boolean debugMode) throws JsonProcessingException, IOException, HttpsClientException {
        String url;
        String json;
        int code;
        String message;
        StringBuffer mediaTypeParams;

        url = this.apiRest.getBaseUrl() + "/operations/v2/interventions/logs";
        if (debugMode) {
            System.out.println("  url:" + url);
        }

//        MediaType mediaType = MediaType.get("application/json+hal; charset=utf-8");
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

//        mediaTypeParams = new StringBuffer("authorization=Bearer ");
//        mediaTypeParams.append(this.token.getAccess_token());
        objectMapper.writeValue(new File("testOpenTicket_2.json"), openTicket);
//        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(closeTicket);
        json = objectMapper.writeValueAsString(openTicket);
        if (debugMode) {
            System.out.println("  openTicket:" + openTicket);
            System.out.println("  openTicket(json):" + json);
        }
//        mediaTypeParams.append(json);
//        if (debugMode) {
//            System.out.println("  mediaTypeParams:" + mediaTypeParams.toString());
//        }

//        RequestBody body = RequestBody.create(mediaType, mediaTypeParams.toString());
        RequestBody body = RequestBody.create(mediaType, json);
        System.out.println("  body.contentType():" + body.contentType() + ", body.contentLength():" + body.contentLength());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json+hal")
                .addHeader("Authorization", "Bearer " + this.token.getAccess_token())
                .addHeader("content-type", "application/json; charset=utf-8")
                //                .addHeader("cache-control", "no-cache")
                .post(body)
                .build();
        System.out.println("  request.headers():" + request.headers());

        Response response = this.newCall(request).execute();
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("  response.code():" + code);
            System.out.println("  response.message():" + message);
        }

        if (code == 204) {
            json = response.body().string();
            System.out.println("    response.body():" + json);
        } else {
            throw new HttpsClientException(code + " " + message);
        }

    }

    /**
     * Créer une clôture d'intervention sur la plate-forme
     *
     * @param closeTicket commande de clôture de ticket
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws com.fasterxml.jackson.core.JsonProcessingException en cas
     * d'erreur de convertion au format Json
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public void closeTicket(CloseTicket closeTicket, boolean debugMode) throws JsonProcessingException, IOException, HttpsClientException {
        String url;
        String json;
        int code;
        String message;
        StringBuffer mediaTypeParams;

        url = this.apiRest.getBaseUrl() + "/operations/v2/interventions/logs";
        if (debugMode) {
            System.out.println("  url:" + url);
        }

//        MediaType mediaType = MediaType.get("application/json+hal; charset=utf-8");
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

//        mediaTypeParams = new StringBuffer("authorization=Bearer ");
//        mediaTypeParams.append(this.token.getAccess_token());
        objectMapper.writeValue(new File("testCloseTicket_2.json"), closeTicket);
//        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(closeTicket);
        json = objectMapper.writeValueAsString(closeTicket);
        if (debugMode) {
            System.out.println("  closeTicket:" + closeTicket);
            System.out.println("  closeTicket(json):" + json);
        }
//        mediaTypeParams.append(json);
//        if (debugMode) {
//            System.out.println("  mediaTypeParams:" + mediaTypeParams.toString());
//        }

//        RequestBody body = RequestBody.create(mediaType, mediaTypeParams.toString());
        RequestBody body = RequestBody.create(mediaType, json);
        System.out.println("  body.contentType():" + body.contentType() + ", body.contentLength():" + body.contentLength());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json+hal")
                .addHeader("Authorization", "Bearer " + this.token.getAccess_token())
                .addHeader("content-type", "application/json; charset=utf-8")
                //                .addHeader("cache-control", "no-cache")
                .post(body)
                .build();
        System.out.println("  request.headers():" + request.headers());

        Response response = this.newCall(request).execute();
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("  response.code():" + code);
            System.out.println("  response.message():" + message);
        }

        if (code == 204) {
            json = response.body().string();
            System.out.println("    response.body():" + json);
        } else {
            throw new HttpsClientException(code + " " + message);
        }

    }

    /**
     * Créer une clôture d'intervention sur la plate-forme suite à une
     * réparation définitive
     *
     * @param closeTicketOnPermanentlyFixed commande de clôture de ticket
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws com.fasterxml.jackson.core.JsonProcessingException en cas
     * d'erreur de convertion au format Json
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public void fixPermanently(CloseTicketOnPermanentlyFixed closeTicketOnPermanentlyFixed, boolean debugMode) throws JsonProcessingException, IOException, HttpsClientException {
        String url;
        String json;
        int code;
        String message;
        StringBuffer mediaTypeParams;

        url = this.apiRest.getBaseUrl() + "/operations/v2/interventions/logs";
        if (debugMode) {
            System.out.println("  url:" + url);
        }

//        MediaType mediaType = MediaType.get("application/json+hal; charset=utf-8");
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

//        mediaTypeParams = new StringBuffer("authorization=Bearer ");
//        mediaTypeParams.append(this.token.getAccess_token());
        objectMapper.writeValue(new File("testCloseTicketOnPermanentlyFixed_2.json"), closeTicketOnPermanentlyFixed);
//        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(closeTicket);
        json = objectMapper.writeValueAsString(closeTicketOnPermanentlyFixed);
        if (debugMode) {
            System.out.println("  closeTicketOnPermanentlyFixed:" + closeTicketOnPermanentlyFixed);
            System.out.println("  closeTicketOnPermanentlyFixed(json):" + json);
        }
//        mediaTypeParams.append(json);
//        if (debugMode) {
//            System.out.println("  mediaTypeParams:" + mediaTypeParams.toString());
//        }

//        RequestBody body = RequestBody.create(mediaType, mediaTypeParams.toString());
        RequestBody body = RequestBody.create(mediaType, json);
        System.out.println("  body.contentType():" + body.contentType() + ", body.contentLength():" + body.contentLength());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json+hal")
                .addHeader("Authorization", "Bearer " + this.token.getAccess_token())
                .addHeader("content-type", "application/json; charset=utf-8")
                //                .addHeader("cache-control", "no-cache")
                .post(body)
                .build();
        System.out.println("  request.headers():" + request.headers());

        Response response = this.newCall(request).execute();
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("  response.code():" + code);
            System.out.println("  response.message():" + message);
        }

        if (code == 204) {
            json = response.body().string();
            System.out.println("    response.body():" + json);
        } else {
            throw new HttpsClientException(code + " " + message);
        }

    }

    /**
     * Créer une clôture d'intervention sur la plate-forme suite à une
     * demande de devis
     *
     * @param closeTicketOnQuoteRequested commande de clôture de ticket
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws com.fasterxml.jackson.core.JsonProcessingException en cas
     * d'erreur de convertion au format Json
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public void requestQuote(CloseTicketOnQuoteRequested closeTicketOnQuoteRequested, boolean debugMode) throws JsonProcessingException, IOException, HttpsClientException {
        String url;
        String json;
        int code;
        String message;
        StringBuffer mediaTypeParams;

        url = this.apiRest.getBaseUrl() + "/operations/v2/interventions/logs";
        if (debugMode) {
            System.out.println("  url:" + url);
        }

//        MediaType mediaType = MediaType.get("application/json+hal; charset=utf-8");
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

//        mediaTypeParams = new StringBuffer("authorization=Bearer ");
//        mediaTypeParams.append(this.token.getAccess_token());
        objectMapper.writeValue(new File("testCloseTicketOnQuoteRequested_2.json"), closeTicketOnQuoteRequested);
//        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(closeTicket);
        json = objectMapper.writeValueAsString(closeTicketOnQuoteRequested);
        if (debugMode) {
            System.out.println("  closeTicketOnQuoteRequested:" + closeTicketOnQuoteRequested);
            System.out.println("  closeTicketOnQuoteRequested(json):" + json);
        }
//        mediaTypeParams.append(json);
//        if (debugMode) {
//            System.out.println("  mediaTypeParams:" + mediaTypeParams.toString());
//        }

//        RequestBody body = RequestBody.create(mediaType, mediaTypeParams.toString());
        RequestBody body = RequestBody.create(mediaType, json);
        System.out.println("  body.contentType():" + body.contentType() + ", body.contentLength():" + body.contentLength());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json+hal")
                .addHeader("Authorization", "Bearer " + this.token.getAccess_token())
                .addHeader("content-type", "application/json; charset=utf-8")
                //                .addHeader("cache-control", "no-cache")
                .post(body)
                .build();
        System.out.println("  request.headers():" + request.headers());

        Response response = this.newCall(request).execute();
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("  response.code():" + code);
            System.out.println("  response.message():" + message);
        }

        if (code == 204) {
            json = response.body().string();
            System.out.println("    response.body():" + json);
        } else {
            throw new HttpsClientException(code + " " + message);
        }

    }

    /**
     * Envoyer un début d'intervention à la plate-forme
     *
     * @param startIntervention commande de début d'intervention
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws com.fasterxml.jackson.core.JsonProcessingException en cas
     * d'erreur de convertion au format Json
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public void startIntervention(StartIntervention startIntervention, boolean debugMode) throws JsonProcessingException, IOException, HttpsClientException {
        String url;
        String json;
        int code;
        String message;
        StringBuffer mediaTypeParams;

        url = this.apiRest.getBaseUrl() + "/operations/v2/interventions/logs";
        if (debugMode) {
            System.out.println("  url:" + url);
        }

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        objectMapper.writeValue(new File("testStartIntervention_2.json"), startIntervention);
        json = objectMapper.writeValueAsString(startIntervention);
        if (debugMode) {
            System.out.println("  startIntervention:" + startIntervention);
            System.out.println("  startIntervention(json):" + json);
        }

        RequestBody body = RequestBody.create(mediaType, json);
        System.out.println("  body.contentType():" + body.contentType() + ", body.contentLength():" + body.contentLength());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json+hal")
                .addHeader("Authorization", "Bearer " + this.token.getAccess_token())
                .addHeader("content-type", "application/json; charset=utf-8")
                .post(body)
                .build();
        System.out.println("  request.headers():" + request.headers());

        Response response = this.newCall(request).execute();
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("  response.code():" + code);
            System.out.println("  response.message():" + message);
        }

        if (code == 204) {
            json = response.body().string();
            System.out.println("    response.body():" + json);
        } else {
            throw new HttpsClientException(code + " " + message);
        }

    }

    /**
     * Envoyer une fin d'intervention à la plate-forme
     *
     * @param finishIntervention commande de fin d'intervention
     * @param debugMode indique si l'on est en mode debug ou non
     * @throws com.fasterxml.jackson.core.JsonProcessingException en cas
     * d'erreur de convertion au format Json
     * @throws a2itclient.HttpsClientException en cas d'erreur avec la connexion
     * Https
     */
    public void finishIntervention(FinishIntervention finishIntervention, boolean debugMode) throws JsonProcessingException, IOException, HttpsClientException {
        String url;
        String json;
        int code;
        String message;
        StringBuffer mediaTypeParams;

        url = this.apiRest.getBaseUrl() + "/operations/v2/interventions/logs";
        if (debugMode) {
            System.out.println("  url:" + url);
        }

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        objectMapper.writeValue(new File("testFinishIntervention_2.json"), finishIntervention);
        json = objectMapper.writeValueAsString(finishIntervention);
        if (debugMode) {
            System.out.println("  finishIntervention:" + finishIntervention);
            System.out.println("  finishIntervention(json):" + json);
        }

        RequestBody body = RequestBody.create(mediaType, json);
        System.out.println("  body.contentType():" + body.contentType() + ", body.contentLength():" + body.contentLength());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json+hal")
                .addHeader("Authorization", "Bearer " + this.token.getAccess_token())
                .addHeader("content-type", "application/json; charset=utf-8")
                .post(body)
                .build();
        System.out.println("  request.headers():" + request.headers());

        Response response = this.newCall(request).execute();
        code = response.code();
        message = response.message();

        if (debugMode) {
            System.out.println("  response.code():" + code);
            System.out.println("  response.message():" + message);
        }

        if (code == 204) {
            json = response.body().string();
            System.out.println("    response.body():" + json);
        } else {
            throw new HttpsClientException(code + " " + message);
        }

    }
}
