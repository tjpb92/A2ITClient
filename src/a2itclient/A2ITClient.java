package a2itclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.ApplicationProperties;
import utils.DBServer;
import utils.DBServerException;
import utils.Identifiants;
import utils.WebServer;
import utils.WebServerException;

/**
 * Connecteur Anstel / Intent Technologies (lien montant)
 *
 * @author Thierry Baribaud
 * @version 1.00
 */
public class A2ITClient {

    /**
     * webServerType : prod pour le serveur de production, pre-prod pour le
     * serveur de pré-production. Valeur par défaut : pre-prod.
     */
    private String webServerType = "pre-prod";

    /**
     * dbServerType : prod pour le serveur de production, pre-prod pour le
     * serveur de pré-production. Valeur par défaut : pre-prod.
     */
    private String dbServerType = "pre-prod";

    /**
     * webId : identifiants pour se connecter au serveur Web courant. Pas de
     * valeur par défaut, ils doivent être fournis dans le fichier
     * MyDatabases.prop.
     */
    private Identifiants webId;

    /**
     * dbId : identifiants pour se connecter à la base de données courante. Pas
     * de valeur par défaut, ils doivent être fournis dans le fichier
     * MyDatabases.prop.
     */
    private Identifiants dbId;

    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par défaut : false.
     */
    private static boolean debugMode = false;

    /**
     * testMode : fonctionnement du programme en mode test (true/false). Valeur
     * par défaut : false.
     */
    private static boolean testMode = false;

    /**
     * Constructeur de la classe A2ITClient
     *
     * @param args arguments en ligne de commande
     * @throws java.io.IOException en cas d'erreur d'entrée/sortie.
     * @throws utils.WebServerException en cas d'erreur avec le serveur Web.
     * @throws utils.DBServerException en cas d'erreur avec le serveur de base
     * de données.
     */
    public A2ITClient(String[] args) throws IOException, WebServerException, DBServerException {
        ApplicationProperties applicationProperties;
        DBServer dbServer;
        WebServer webServer;

        System.out.println("Création d'une instance de A2ITClient ...");
        System.out.println("Analyse des arguments de la ligne de commande ...");
        if (getArgs(args) == 1) {
            System.out.println("Argument(s) en ligne de commande lus().");
            System.out.println("Lecture des paramètres d'exécution ...");
            applicationProperties = new ApplicationProperties("A2ITClient.prop");
            System.out.println("Lecture des paramètres du serveur Web ...");
            webServer = new WebServer(getWebServerType(), applicationProperties);
            if (debugMode) {
                System.out.println(webServer);
            }
            setWebId(applicationProperties);
            if (debugMode) {
                System.out.println(getWebId());
            }

            System.out.println("Lecture des paramètres du serveur de base de données ...");
            dbServer = new DBServer(getDbServerType(), applicationProperties);
            if (debugMode) {
                System.out.println(dbServer);
            }
            setDbId(applicationProperties);
            if (debugMode) {
                System.out.println(getDbId());
            }
            if (debugMode) {
                System.out.println(this.toString());
            }

            OkHttpClient client;
            StringBuffer mediaTypeParams;

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            mediaTypeParams = new StringBuffer("grant_type=client_credentials&client_id=");
            mediaTypeParams.append(webId.getLogin());
            mediaTypeParams.append("&client_secret=");
            mediaTypeParams.append(webId.getPassword());
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
            client = new OkHttpClient();
            Response response = client.newCall(request).execute();
//            System.out.println("response:" + response);
            System.out.println("response.code():" + response.code());
            System.out.println("response.message():" + response.message());
            System.out.println("response.body():" + response.body().string());
//            System.out.println("response.headers():" + response.headers());
        } else {
            usage();
        }
    }

    /**
     * Récupère les paramètres en ligne de commande
     *
     * @param args arguments en ligne de commande
     */
    private int getArgs(String[] args) {
        int i;
        int n;
        int ip1;
        int retcode = 1;

        n = args.length;
//        System.out.println("nargs=" + n);
//    for(i=0; i<n; i++) System.out.println("args["+i+"]="+Args[i]);
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            ip1 = i + 1;
            switch (args[i]) {
                case "-webserver":
                    if (ip1 < n) {
                        if (args[ip1].equals("pre-prod") || args[ip1].equals("prod")) {
                            setWebServerType(args[ip1]);
                        } else {
                            retcode = -1;
                            System.out.println("ERREUR : Mauvais serveur web : " + args[ip1]);
                        }
                        i = ip1;
                    } else {
                        retcode = -1;
                        System.out.println("ERREUR : Serveur Web non défini");
                    }
                    break;
                case "-dbserver":
                    if (ip1 < n) {
                        if (args[ip1].equals("pre-prod") || args[ip1].equals("prod")) {
                            setDbServerType(args[ip1]);
                        } else {
                            retcode = -1;
                            System.out.println("ERREUR : Mauvaise base de données : " + args[ip1]);
                        }
                        i = ip1;
                    } else {
                        retcode = -1;
                        System.out.println("ERREUR : Base de données non définie");
                    }
                    break;
                case "-d":
                    setDebugMode(true);
                    break;
                case "-t":
                    setTestMode(true);
                    break;
                default:
                    System.out.println("ERREUR : Mauvais argument : " + args[i]);
                    retcode = -1;
                    break;
            }
            i++;
        }

        return retcode;
    }

    /**
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * @param testMode : fonctionnement du programme en mode test (true/false).
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    /**
     * @return debugMode : retourne le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * @return testMode : retourne le mode de fonctionnement test.
     */
    public boolean getTestMode() {
        return (testMode);
    }

    /**
     * @param webServerType définit le serveur Web
     */
    private void setWebServerType(String webServerType) {
        this.webServerType = webServerType;
    }

    /**
     * @param dbServerType définit le serveur de base de données
     */
    private void setDbServerType(String dbServerType) {
        this.dbServerType = dbServerType;
    }

    /**
     * @return webServerType le serveur web
     */
    private String getWebServerType() {
        return (webServerType);
    }

    /**
     * @return dbServerType le serveur de base de données
     */
    private String getDbServerType() {
        return (dbServerType);
    }

    /**
     * @return les identifiants pour accéder au serveur Web
     */
    public Identifiants getWebId() {
        return webId;
    }

    /**
     * @param webId définit les identifiants pour accéder au serveur Web
     */
    public void setWebId(Identifiants webId) {
        this.webId = webId;
    }

    /**
     * @param applicationProperties définit les identifiants pour accéder au
     * serveur Web
     * @throws WebServerException en cas d'erreur sur la lecteur des
     * identifiants
     */
    public void setWebId(ApplicationProperties applicationProperties) throws WebServerException {
        String value;
        Identifiants identifiants = new Identifiants();

        value = applicationProperties.getProperty(getWebServerType() + ".webserver.login");
        if (value != null) {
            identifiants.setLogin(value);
        } else {
            throw new WebServerException("Nom utilisateur pour l'accès Web non défini");
        }

        value = applicationProperties.getProperty(getWebServerType() + ".webserver.passwd");
        if (value != null) {
            identifiants.setPassword(value);
        } else {
            throw new WebServerException("Mot de passe pour l'accès Web non défini");
        }
        setWebId(identifiants);
    }

    /**
     * @return les identifiants pour accéder à la base de données
     */
    public Identifiants getDbId() {
        return dbId;
    }

    /**
     * @param dbId définit les identifiants pour accéder à la base de données
     */
    public void setDbId(Identifiants dbId) {
        this.dbId = dbId;
    }

    /**
     * @param applicationProperties définit les identifiants pour accéder au
     * serveur Web
     * @throws WebServerException en cas d'erreur sur la lecteur des
     * identifiants
     */
    public void setDbId(ApplicationProperties applicationProperties) throws WebServerException {
        String value;
        Identifiants identifiants = new Identifiants();

        value = applicationProperties.getProperty(getDbServerType() + ".dbserver.login");
        if (value != null) {
            identifiants.setLogin(value);
        } else {
            throw new WebServerException("Nom utilisateur pour l'accès base de données non défini");
        }

        value = applicationProperties.getProperty(getDbServerType() + ".dbserver.passwd");
        if (value != null) {
            identifiants.setPassword(value);
        } else {
            throw new WebServerException("Mot de passe pour l'accès base de données non défini");
        }
        setDbId(identifiants);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        A2ITClient a2ITClient;

        System.out.println("Lancement de A2ITclient ...");
        try {
            a2ITClient = new A2ITClient(args);
        } catch (IOException exception) {
            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
        } catch (WebServerException exception) {
            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
        } catch (DBServerException exception) {
            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
        }
        System.out.println("Fin de A2ITclient.");
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java A2ITclient [-webserver prod|pre-prod]"
                + " [-dbserver prod|pre-prod]"
                + " [-d] [-t]");
    }

    /**
     * Retourne le contenu de A2ITClient
     *
     * @return retourne le contenu de A2ITClient
     */
    @Override
    public String toString() {
        return "A2ITClient:{"
                + "webServer=" + getWebServerType()
                + ", dbServer=" + getDbServerType()
                + ", debugMode:" + getDebugMode()
                + ", testMode:" + getTestMode()
                + "}";
    }
}
