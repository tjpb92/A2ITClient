package a2itclient;

import bdd.Fa2it;
import bdd.Fa2itDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Request;
import okhttp3.Response;
import utils.ApplicationProperties;
import utils.DBServer;
import utils.DBServerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.sql.*;
import utils.DBManager;

/**
 * Connecteur Anstel / Intent Technologies (lien montant)
 *
 * @author Thierry Baribaud
 * @version 1.10
 */
public class A2ITClient {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * apiServerType : prod pour le serveur de production, pre-prod pour le
     * serveur de pré-production. Valeur par défaut : pre-prod.
     */
    private String apiServerType = "pre-prod";

    /**
     * Serveur de l'API REST
     */
    private APIREST apiRest;

    /**
     * mgoDbServerType : prod pour le serveur de production, pre-prod pour le
     * serveur de pré-production. Valeur par défaut : pre-prod.
     */
    private String mgoDbServerType = "pre-prod";

    /**
     * ifxDbServerType : prod pour le serveur de production, pre-prod pour le
     * serveur de pré-production. Valeur par défaut : pre-prod.
     */
    private String ifxDbServerType = "pre-prod";

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
     * Exception pouvant être lancée en cas de problème avec un paramètre de la
     * ligne de commande
     */
    public class GetArgsException extends Exception {

        private final static String ERRMSG
                = "Problem during command line parameters parsing";

        /**
         * Constructeur principal de l'exception
         */
        public GetArgsException() {
            super(ERRMSG);
        }

        /**
         * Constructeur secondaire de l'exception
         *
         * @param errMsg message d'erreur à diffuser
         */
        public GetArgsException(String errMsg) {
            super(ERRMSG + " : " + errMsg);
        }
    }

    /**
     * Constructeur de la classe A2ITClient
     *
     * @param args arguments en ligne de commande
     * @throws java.io.IOException en cas d'erreur d'entrée/sortie.
     * @throws utils.DBServerException en cas d'erreur avec le serveur de base
     * de données.
     * @throws a2itclient.A2ITClient.GetArgsException en cas d'erreur avec les
     * paramètres en ligne de commande
     * @throws a2itclient.APIREST.APIServerException en cas de problème avec les
     * paramètres du serveur API
     * @throws a2itclient.HttpsClientException en cas de problème avec la connexion Https.
     * @throws java.lang.ClassNotFoundException en cas de problème avec une classe inconnue
     * @throws java.sql.SQLException en cas d'erreur d'entrée/sortie. 
     */
    public A2ITClient(String[] args) throws IOException, DBServerException, GetArgsException, 
            APIREST.APIServerException, HttpsClientException, ClassNotFoundException, SQLException {
        ApplicationProperties applicationProperties;
        DBServer mgoServer;
        DBServer ifxServer;
        HttpsClient httpsClient;
        String json;
        Token token;
        Users users;
        DBManager informixDbManager;
        Connection informixConnection;
        MongoClient mongoClient;
        MongoDatabase mongoDatabase;

        System.out.println("Création d'une instance de A2ITClient ...");

        System.out.println("Analyse des arguments de la ligne de commande ...");
        this.getArgs(args);
        System.out.println("Argument(s) en ligne de commande lus().");

        System.out.println("Lecture des paramètres d'exécution ...");
        applicationProperties = new ApplicationProperties("A2ITClient.prop");
        System.out.println("Paramètres d'exécution lus.");

        System.out.println("Lecture des paramètres du serveur API ...");
        this.apiRest = new APIREST(apiServerType, applicationProperties);
        System.out.println("Paramètres du serveur API lus.");
        if (debugMode) {
            System.out.println(this.apiRest);
        }

        System.out.println("Lecture des paramètres du serveur Informix ...");
        ifxServer = new DBServer(ifxDbServerType, "ifxserver", applicationProperties);
        System.out.println("Paramètres du serveur Informix lus.");
        if (debugMode) {
            System.out.println(ifxServer);
        }

        System.out.println("Lecture des paramètres du serveur Mongo ...");
        mgoServer = new DBServer(ifxDbServerType, "mgoserver", applicationProperties);
        System.out.println("Paramètres du serveur Mongo lus.");
        if (debugMode) {
            System.out.println(mgoServer);
        }

        if (debugMode) {
            System.out.println(this.toString());
        }

        System.out.println("Ouverture de la connexion avec le server API" + apiRest.getName() + " ...");
        httpsClient = new HttpsClient(apiRest, debugMode);
        System.out.println("Connexion avec le server API ouverte.");

        System.out.println("Ouverture de la connexion au serveur Informix : " + ifxServer.getName());
        informixDbManager = new DBManager(ifxServer);

        System.out.println("Connexion à la base de données : " + ifxServer.getDbName());
        informixConnection = informixDbManager.getConnection();

        System.out.println("Ouverture de la connexion au serveur MongoDb : " + mgoServer.getName());
        mongoClient = new MongoClient(mgoServer.getIpAddress(), (int) mgoServer.getPortNumber());

        System.out.println("Connexion à la base de données : " + mgoServer.getDbName());
        mongoDatabase = mongoClient.getDatabase(mgoServer.getDbName());

        Request request2 = new Request.Builder()
                .url("https://apisandbox.hubintent.com/api/users/v1/users")
                .get()
                .addHeader("content-type", "application/json; charset=utf-8")
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization", "Bearer " + httpsClient.getToken().getAccess_token())
                .build();
//                    OkHttpClient client2 = new OkHttpClient();
//                    Response response2 = client2.newCall(request2).execute();
//                    System.out.println("response:" + response2);
//                    System.out.println("response2.code():" + response2.code());
//                    System.out.println("response2.message():" + response2.message());
//                    json = response2.body().string();

        Response response2 = httpsClient.newCall(request2).execute();
        System.out.println("response2:" + response2);
        System.out.println("response2.code():" + response2.code());
        System.out.println("response2.message():" + response2.message());
        json = response2.body().string();
        System.out.println("response2.body():" + json);

        if (json != null) {
            users = objectMapper.readValue(json, Users.class);
            System.out.println(users.size() + " user(s) found");
//                        System.out.println(users);
            for (User user : users) {
                System.out.println(user);
            }
        }

//                            .url("https://apisandbox.hubintent.com/api/assets/v1/assets/14436")
        Request request3 = new Request.Builder()
                .url("https://apisandbox.hubintent.com/api/assets/v1/assets/14435")
                .get()
                .addHeader("content-type", "application/json; charset=utf-8")
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization", "Bearer " + httpsClient.getToken().getAccess_token())
                .build();
        Response response3 = httpsClient.newCall(request3).execute();
        System.out.println("response3:" + response3);
        System.out.println("response3.code():" + response3.code());
        System.out.println("response3.message():" + response3.message());
        json = response3.body().string();
        System.out.println("response3.body():" + json);

        if (json != null) {
            Asset asset;
            asset = objectMapper.readValue(json, Asset.class);
            System.out.println(asset);
        }

        Request request4 = new Request.Builder()
                .url("https://apisandbox.hubintent.com/api/contracts/v1/contracts/NPM_ANSTEL")
                .get()
                .addHeader("content-type", "application/json; charset=utf-8")
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization", "Bearer " + httpsClient.getToken().getAccess_token())
                .build();
        Response response4 = httpsClient.newCall(request4).execute();
        System.out.println("response4:" + response4);
        System.out.println("response4.code():" + response4.code());
        System.out.println("response4.message():" + response4.message());
        json = response4.body().string();
        System.out.println("response4.body():" + json);

        if (json != null) {
            Contract contract;
            contract = objectMapper.readValue(json, Contract.class);
            System.out.println(contract);
        }

        System.out.println("Traitement des événements ...");
        processEvents(informixConnection, mongoDatabase);
    }

    /**
     * Traitement des événements
     */
    private void processEvents(Connection informixConnection, MongoDatabase mongoDatabase) {
        
        Fa2it fa2it;
        Fa2itDAO fa2itDAO;
        int i;
        
        try {
            fa2itDAO = new Fa2itDAO(informixConnection);
            fa2itDAO.setUpdatePreparedStatement();
            
            fa2itDAO.filterByStatus(0);
            System.out.println("  SelectStatement=" + fa2itDAO.getSelectStatement());
            fa2itDAO.setSelectPreparedStatement();
            i = 0;
            while ((fa2it = fa2itDAO.select()) != null) {
                i++;
                System.out.println("Fa2it(" + i + ")=" + fa2it);
                
                fa2it.setA11status(-1);
                fa2it.setA11nberr(1);
                fa2it.setA11update(new Timestamp(new java.util.Date().getTime()));
                fa2itDAO.update(fa2it);
                System.out.println("Rangée(s) affectée(s)=" + fa2itDAO.getNbAffectedRow());
            }
            fa2itDAO.closeUpdatePreparedStatement();
            fa2itDAO.closeSelectPreparedStatement();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Récupère les paramètres en ligne de commande
     *
     * @param args arguments en ligne de commande
     */
    private void getArgs(String[] args) throws GetArgsException {
        int i;
        int n;
        int ip1;

        n = args.length;
//        System.out.println("nargs=" + n);
//    for(i=0; i<n; i++) System.out.println("args["+i+"]="+Args[i]);
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            ip1 = i + 1;
            switch (args[i]) {
                case "-apiserver":
                    if (ip1 < n) {
                        if (args[ip1].equals("pre-prod") || args[ip1].equals("prod")) {
                            this.apiServerType = args[ip1];
                        } else {
                            throw new GetArgsException("ERREUR : Mauvais serveur API : " + args[ip1]);
                        }
                        i = ip1;
                    } else {
                        throw new GetArgsException("ERREUR : Serveur API non défini");
                    }
                    break;
                case "-ifxserver":
                    if (ip1 < n) {
                        if (args[ip1].equals("pre-prod") || args[ip1].equals("prod")) {
                            this.ifxDbServerType = args[ip1];
                        } else {
                            throw new GetArgsException("ERREUR : Mauvais serveur Informix : " + args[ip1]);
                        }
                        i = ip1;
                    } else {
                        throw new GetArgsException("ERREUR : Serveur Informix non défini");
                    }
                    break;
                case "-mgoserver":
                    if (ip1 < n) {
                        if (args[ip1].equals("pre-prod") || args[ip1].equals("prod")) {
                            this.mgoDbServerType = args[ip1];
                        } else {
                            throw new GetArgsException("ERREUR : Mauvais serveur Mongo : " + args[ip1]);
                        }
                        i = ip1;
                    } else {
                        throw new GetArgsException("ERREUR : Serveur Mongo non définie");
                    }
                    break;
                case "-d":
                    setDebugMode(true);
                    break;
                case "-t":
                    setTestMode(true);
                    break;
                default:
                    usage();
                    throw new GetArgsException("ERREUR : Mauvais argument : " + args[i]);
            }
            i++;
        }
    }

    /**
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        A2ITClient.debugMode = debugMode;
    }

    /**
     * @param testMode : fonctionnement du programme en mode test (true/false).
     */
    public void setTestMode(boolean testMode) {
        A2ITClient.testMode = testMode;
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        A2ITClient a2ITClient;

        System.out.println("Lancement de A2ITclient ...");
        try {
            a2ITClient = new A2ITClient(args);
        } catch (IOException | DBServerException | GetArgsException | APIREST.APIServerException | 
                HttpsClientException | ClassNotFoundException | SQLException exception) {
            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
        System.out.println("Fin de A2ITclient.");
        }
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java A2ITclient [-apiserver prod|pre-prod]"
                + " [-ifxserver prod|pre-prod]"
                + " [-mgoserver prod|pre-prod]"
                + " [-d] [-t]");
    }

    /**
     * @param mgoDbServerType définit le serveur Web
     */
    private void setMgoDbServerType(String mgoDbServerType) {
        this.mgoDbServerType = mgoDbServerType;
    }

    /**
     * @param ifxDbServerType définit le serveur de base de données
     */
    private void setIfxDbServerType(String ifxDbServerType) {
        this.ifxDbServerType = ifxDbServerType;
    }

    /**
     * @return mgoDbServerType le serveur web
     */
    private String getMgoDbServerType() {
        return (mgoDbServerType);
    }

    /**
     * @return ifxDbServerType le serveur de base de données
     */
    private String getIfxDbServerType() {
        return (ifxDbServerType);
    }

    /**
     * Retourne le contenu de A2ITClient
     *
     * @return retourne le contenu de A2ITClient
     */
    @Override
    public String toString() {
        return "A2ITClient:{"
                + "apiServerType:" + apiServerType
                + ", ifxDbServerType:" + ifxDbServerType
                + ", mgoDbServerType:" + mgoDbServerType
                + ", debugMode:" + debugMode
                + ", testMode:" + testMode
                + "}";
    }
}
