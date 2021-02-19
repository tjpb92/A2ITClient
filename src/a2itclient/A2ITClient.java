package a2itclient;

import ticketCommands.UpdateTicket;
import ticketEvents.TicketUpdated;
import ticketEvents.TicketOpened;
import ticketEvents.TicketClosed;
import ticketEvents.TicketCancelled;
import ticketCommands.StartIntervention;
import ticketEvents.PermanentlyFixed;
import ticketCommands.OpenTicket;
import ticketEvents.InterventionStarted;
import ticketEvents.InterventionFinished;
import ticketCommands.FinishIntervention;
import ticketEvents.ClosedQuoteRequested;
import ticketCommands.CloseTicketOnQuoteRequested;
import ticketCommands.CloseTicketOnPermanentlyFixed;
import ticketCommands.CloseTicket;
import ticketCommands.CancelTicket;
import a2itclient.MailServer.MailServerException;
import bdd.Fa2it;
import bdd.Fa2itDAO;
import bkgpi2a.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import utils.ApplicationProperties;
import utils.DBServer;
import utils.DBServerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import javax.mail.internet.*;
import javax.mail.*;
import org.bson.Document;
import utils.DBManager;
import utils.GetArgsException;
import utils.HttpsClientException;
import utils.Md5;

/**
 * Connecteur Anstel / Intent Technologies (lien montant)
 *
 * @author Thierry Baribaud
 * @version 1.38
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
     * Serveur de mail pour les notifications
     */
    private MailServer mailServer;

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
     * EvenType lookup list by Uid
     */
    private Map<Integer, EventType> eventTypeByUid;

    /**
     * Constructeur de la classe A2ITClient
     *
     * @param args arguments en ligne de commande
     * @throws java.io.IOException en cas d'erreur d'entrée/sortie.
     * @throws utils.DBServerException en cas d'erreur avec le serveur de base
     * de données.
     * @throws utils.GetArgsException en cas d'erreur avec les
     * paramètres en ligne de commande
     * @throws a2itclient.APIREST.APIServerException en cas de problème avec les
     * paramètres du serveur API
     * @throws a2itclient.HttpsClientException en cas de problème avec la
     * connexion Https.
     * @throws java.lang.ClassNotFoundException en cas de problème avec une
     * classe inconnue
     * @throws java.sql.SQLException en cas d'erreur d'entrée/sortie.
     * @throws a2itclient.MailServer.MailServerException en cas de problème sur
     * l'envoi des mails.
     */
    public A2ITClient(String[] args) throws IOException, DBServerException, GetArgsException,
            APIREST.APIServerException, HttpsClientException, ClassNotFoundException, SQLException, MailServer.MailServerException {
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

        System.out.println("Building EventType reverse Uid lookup list ...");
        eventTypeByUid = new HashMap<>();
        for (EventType eventType : EventType.values()) {
            eventTypeByUid.put(eventType.getUid(), eventType);
        }

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
        mgoServer = new DBServer(mgoDbServerType, "mgoserver", applicationProperties);
        System.out.println("Paramètres du serveur Mongo lus.");
        if (debugMode) {
            System.out.println(mgoServer);
        }

        System.out.println("Lecture des paramètres du serveur de mail ...");
        mailServer = new MailServer(applicationProperties);
        System.out.println("Paramètres du serveur Mongo lus.");
        if (debugMode) {
            System.out.println(mailServer);
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

//        Request request2 = new Request.Builder()
//                .url("https://apisandbox.hubintent.com/api/users/v1/users")
//                .get()
//                .addHeader("content-type", "application/json; charset=utf-8")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("Authorization", "Bearer " + httpsClient.getToken().getAccess_token())
//                .build();
////                    OkHttpClient client2 = new OkHttpClient();
////                    Response response2 = client2.newCall(request2).execute();
////                    System.out.println("response:" + response2);
////                    System.out.println("response2.code():" + response2.code());
////                    System.out.println("response2.message():" + response2.message());
////                    json = response2.body().string();
//
//        Response response2 = httpsClient.newCall(request2).execute();
//        System.out.println("response2:" + response2);
//        System.out.println("response2.code():" + response2.code());
//        System.out.println("response2.message():" + response2.message());
//        json = response2.body().string();
//        System.out.println("response2.body():" + json);
//
//        if (json != null) {
//            users = objectMapper.readValue(json, Users.class);
//            System.out.println(users.size() + " user(s) found");
////                        System.out.println(users);
//            for (User user : users) {
//                System.out.println(user);
//            }
//        }
//
////                            .url("https://apisandbox.hubintent.com/api/assets/v1/assets/14436")
//        Request request3 = new Request.Builder()
//                .url("https://apisandbox.hubintent.com/api/assets/v1/assets/14435")
//                .get()
//                .addHeader("content-type", "application/json; charset=utf-8")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("Authorization", "Bearer " + httpsClient.getToken().getAccess_token())
//                .build();
//        Response response3 = httpsClient.newCall(request3).execute();
//        System.out.println("response3:" + response3);
//        System.out.println("response3.code():" + response3.code());
//        System.out.println("response3.message():" + response3.message());
//        json = response3.body().string();
//        System.out.println("response3.body():" + json);
//
//        if (json != null) {
//            Asset asset;
//            asset = objectMapper.readValue(json, Asset.class);
//            System.out.println(asset);
//        }
//
//        Request request4 = new Request.Builder()
//                .url("https://apisandbox.hubintent.com/api/contracts/v1/contracts/NPM_ANSTEL")
//                .get()
//                .addHeader("content-type", "application/json; charset=utf-8")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("Authorization", "Bearer " + httpsClient.getToken().getAccess_token())
//                .build();
//        Response response4 = httpsClient.newCall(request4).execute();
//        System.out.println("response4:" + response4);
//        System.out.println("response4.code():" + response4.code());
//        System.out.println("response4.message():" + response4.message());
//        json = response4.body().string();
//        System.out.println("response4.body():" + json);
//
//        if (json != null) {
//            Contract contract;
//            contract = objectMapper.readValue(json, Contract.class);
//            System.out.println(contract);
//        }
        System.out.println("Traitement des événements ...");
        processEvents(httpsClient, informixConnection, mongoDatabase);
    }

    /**
     * Traitement des événements
     */
    private void processEvents(HttpsClient httpsClient, Connection informixConnection, MongoDatabase mongoDatabase) {

        Fa2it fa2it;
        Fa2itDAO fa2itDAO;
        int i;
        String json;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(timeZone);
        Event event;
        OpenTicket openTicket;
        int retcode;
        MongoCollection<Document> collection;
        MongoCursor<Document> cursor;
        int nbClient;
        BasicDBObject filter;
        UpdateResult updateResult;
        TicketOpened ticketOpened;
        Client client;
        TicketInfos ticketInfos;
        String clientUuid;
        String reference;
        Patrimony patrimony;
        CallPurpose callPurpose;
        String callPurposeUuid;
        TicketClosed ticketClosed;
        CloseTicket closeTicket;
        EventType eventType;
        int evtType;

//        collection = mongoDatabase.getCollection("clients");
//        System.out.println(collection.count() + " client(s) dans la base MongoDb");
        try {
            fa2itDAO = new Fa2itDAO(informixConnection);
            fa2itDAO.setUpdatePreparedStatement();

            fa2itDAO.filterByStatus(0);
            System.out.println("  SelectStatement=" + fa2itDAO.getSelectStatement());
            fa2itDAO.setSelectPreparedStatement();
            i = 0;

            while ((fa2it = fa2itDAO.select()) != null) {
                i++;
                retcode = -1;

                System.out.println("Fa2it(" + i + ")=" + fa2it);
                try {
                    json = toJson(fa2it, dateFormat);
                    System.out.println("  json:" + json);
                    event = objectMapper.readValue(json.toString(), Event.class);
                    System.out.println("  " + event.getClass().getName() + ", " + event);

                    if (event instanceof TicketOpened) {
                        retcode = processTicketOpened(mongoDatabase, (TicketOpened) event, httpsClient);
                    } else if (event instanceof InterventionStarted) {
                        retcode = processInterventionStarted(mongoDatabase, (InterventionStarted) event, httpsClient);
                    } else if (event instanceof InterventionFinished) {
                        retcode = processInterventionFinished(mongoDatabase, (InterventionFinished) event, httpsClient);
                    } else if (event instanceof PermanentlyFixed) {
                        retcode = processPermanentlyFixed(mongoDatabase, (PermanentlyFixed) event, httpsClient);
                    } else if (event instanceof ClosedQuoteRequested) {
                        retcode = processClosedQuoteRequested(mongoDatabase, (ClosedQuoteRequested) event, httpsClient);
                    } else if (event instanceof TicketClosed) {
                        retcode = processTicketClosed(mongoDatabase, (TicketClosed) event, httpsClient);
                    } else if (event instanceof TicketCancelled) {
                        retcode = processTicketCancelled(mongoDatabase, (TicketCancelled) event, httpsClient);
                    } else if (event instanceof TicketUpdated) {
                        retcode = processTicketUpdated(mongoDatabase, (TicketUpdated) event, httpsClient);
                    }
                } catch (IOException exception) {
                    retcode = -1;
                    System.out.println("  Cannot convert Json to Event, record rejected " + exception);
//                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                } catch (UnknownEventTypeException exception) {
                    retcode = -1;
                    System.out.println(exception);
//                    Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, ex);
                }

                fa2it.setA11status(retcode);
                if (retcode != 1) {
                    fa2it.setA11nberr(1);
                }

                fa2it.setA11update(new Timestamp(new java.util.Date().getTime()));
                fa2itDAO.update(fa2it);
//                System.out.println("Rangée(s) affectée(s)=" + fa2itDAO.getNbAffectedRow());
            }
            fa2itDAO.closeUpdatePreparedStatement();
            fa2itDAO.closeSelectPreparedStatement();

        } catch (ClassNotFoundException | SQLException exception) {
            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
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
                        if (args[ip1].equals("pre-prod") || args[ip1].equals("prod")
                                || args[ip1].equals("pre-prod2") || args[ip1].equals("prod2")) {
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
     * @return retourne le serveur de mail
     */
    public MailServer getMailServer() {
        return mailServer;
    }

    /**
     * @param mailServer définit le serveur de mail
     */
    public void setMailServer(MailServer mailServer) {
        this.mailServer = mailServer;
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
                MailServerException |
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
                + ", mailServer:" + mailServer
                + ", debugMode:" + debugMode
                + ", testMode:" + testMode
                + "}";
    }

    /**
     * Conversion des données venant de la base de données Informix au foramt
     * Json
     *
     * @param fa2it données à convertir
     * @param dateFormat format de date
     * @return données converties au format Json
     * @throws UnknownEventTypeException exception envoyée si un type
     * d'événément est inconnu
     */
    private String toJson(Fa2it fa2it, DateFormat dateFormat) throws UnknownEventTypeException {

        StringBuffer json;
        int evtType;
        EventType eventType;

        json = null;
        evtType = fa2it.getA11evttype();
        eventType = eventTypeByUid.get(evtType);
        if (eventType == null) {
            throw new UnknownEventTypeException("Unknown EventType:" + evtType);
        } else {
            json = new StringBuffer("{");
            json.append("\"processUid\":\"").append(Md5.encode("a11:" + String.valueOf(fa2it.getA11num()))).append("\",");
            json.append("\"aggregateUid\":\"").append(Md5.encode(fa2it.getA11laguid())).append("\",");
            json.append("\"eventType\":\"").append(eventType.getName()).append("\",");
            json.append("\"sentDate\":\"").append(dateFormat.format(fa2it.getA11credate())).append("\",");
            json.append(fa2it.getA11data());
            json.append("}");
        }

        return json.toString();

    }

    /**
     * Envoie une alerte simple par mail
     */
    private void sendAlert(String alertMessage) {
        sendAlert(alertMessage, alertMessage);
    }
    
    /**
     * Envoie une alerte par mail
     */
    private void sendAlert(String alertSubject, String alertMessage) {
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", mailServer.getIpAddress());
            Session session = Session.getDefaultInstance(properties, null);
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailServer.getFromAddress()));
            InternetAddress[] internetAddresses = new InternetAddress[1];
            internetAddresses[0] = new InternetAddress(mailServer.getToAddress());
            message.setRecipients(javax.mail.Message.RecipientType.TO, internetAddresses);
            message.setSubject(alertSubject);
            message.setText(alertMessage);
            message.setHeader("X-Mailer", "Java");
            message.setSentDate(new Date());
            session.setDebug(debugMode);
            Transport.send(message);
        } catch (AddressException exception) {
            System.out.println("Problème avec une adresse mail " + exception);
        } catch (MessagingException exception) {
            System.out.println("Problème avec les mails " + exception);
        }

    }

    /**
     * Vérifie si le client est authorisé à utiliser l'API REST
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param clientUuid identifiant unique du client
     * @return retourne vrai/faux
     */
    private boolean isClientAuthorizedToUseAPI(MongoDatabase mongoDatabase, String clientUuid) {
        boolean okToUseAPI = false;
        MongoCollection<Document> collection;
        MongoCursor<Document> cursor;
        int nbClient;
        BasicDBObject filter;
        Client client;

        filter = new BasicDBObject("uuid", clientUuid);
        collection = mongoDatabase.getCollection("clients");
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext()) {
            try {
                client = objectMapper.readValue(cursor.next().toJson(), Client.class);
                System.out.println("  client trouvé : " + client.getName() + ", useApi:" + client.getUseApi());
                okToUseAPI = "yes".equals(client.getUseApi());
            } catch (IOException exception) {
                System.out.println("  client non trouvé, clientUuid:" + clientUuid);
//                Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return okToUseAPI;
    }

    /**
     * Vérifie si l'immeuble est authorisé à utiliser l'API REST
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param reference identifiant unique de l'immeuble
     * @return retourne vrai/faux
     */
    private boolean isAssetAuthorizedToUseAPI(MongoDatabase mongoDatabase, String clientUuid, String reference) {
        boolean okToUseAPI = false;
        MongoCollection<Document> collection;
        MongoCursor<Document> cursor;
        int nbClient;
        BasicDBObject filter;
        Patrimony patrimony;

        filter = new BasicDBObject("reference", reference);
        collection = mongoDatabase.getCollection("patrimonies");
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext()) {
            try {
                patrimony = objectMapper.readValue(cursor.next().toJson(), Patrimony.class);
                System.out.println("  patrimoine trouvé : " + patrimony.getName() + ", reference:" + reference + ", useApi:" + patrimony.getUseApi());
                okToUseAPI = "yes".equals(patrimony.getUseApi());
            } catch (IOException exception) {
                System.out.println("  patrimoine non trouvé, clientUuid:" + clientUuid + ", reference:" + reference);
//                Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return okToUseAPI;
    }

    /**
     * Convertie la raison d'appel Anstel en raison d'appel Intent Technologie
     * (serviceCode)
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param callPurposeUuid identifiant unique de la raison d'appel
     * @return retourne une raison d'appel
     */
    private CallPurpose convertCallPurpose(MongoDatabase mongoDatabase, String clientUuid, String callPurposeUuid) {
        MongoCollection<Document> collection;
        MongoCursor<Document> cursor;
        int nbClient;
        BasicDBObject filter;
        CallPurpose callPurpose;
        String errMsg;

        callPurpose = null;
        filter = new BasicDBObject("clientUuid", clientUuid).append("uuid", callPurposeUuid);
        collection = mongoDatabase.getCollection("callPurposes");
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext()) {
            try {
                callPurpose = objectMapper.readValue(cursor.next().toJson(), CallPurpose.class);
                System.out.println("  Raison d'appel trouvée : " + callPurpose.getName() + ", useApi:" + callPurpose.getUseApi());
            } catch (IOException exception) {
                errMsg = "Raison d'appel non trouvée, clientUuid:" + clientUuid + ", uuid:" + callPurposeUuid + ", exception:" + exception;
                System.out.println("  " + errMsg);
                sendAlert("Raison d'appel non trouvée, uuid:" + callPurposeUuid, errMsg);
//                Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return callPurpose;
    }

    /**
     * Traitement de l'ouverture d'un ticket
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param ticketOpened événement d'ouverture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processTicketOpened(MongoDatabase mongoDatabase, TicketOpened ticketOpened, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        OpenTicket openTicket;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = ticketOpened.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  Ticket can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        openTicket = new OpenTicket(ticketOpened, callPurpose, currentContract);
                        System.out.println("  " + openTicket);
                        try {
                            objectMapper.writeValue(new File("testOpenTicket_1.json"), openTicket);
                            httpsClient.openTicket(openTicket, debugMode);
                            sendAlert("Ticket " + ticketInfos.getClaimNumber().getCallCenterClaimNumber() + " opened");
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            //                      Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to sent ticket to Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de la date de début de l'intervention
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param ticketOpened événement d'ouverture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processInterventionStarted(MongoDatabase mongoDatabase, InterventionStarted interventionStarted, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        StartIntervention startIntervention;
        CallPurpose callPurpose;
        Contract2 currentContract;

        int retcode;

        retcode = -1;
        ticketInfos = interventionStarted.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  Ticket can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        startIntervention = new StartIntervention(interventionStarted, callPurpose, currentContract);
                        System.out.println("  " + startIntervention);
                        try {
                            objectMapper.writeValue(new File("testStartIntervention_1.json"), startIntervention);
                            httpsClient.startIntervention(startIntervention, debugMode);
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            //                    } catch (JsonProcessingException  exception) {
                            //                      Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to sent ticket to Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de la date de fin de l'intervention
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param ticketOpened événement d'ouverture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processInterventionFinished(MongoDatabase mongoDatabase, InterventionFinished interventionFinished, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        FinishIntervention finishIntervention;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = interventionFinished.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  Ticket can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        finishIntervention = new FinishIntervention(interventionFinished, callPurpose, currentContract);
                        System.out.println("  " + finishIntervention);
                        try {
                            objectMapper.writeValue(new File("testFinishIntervention_1.json"), finishIntervention);
                            httpsClient.finishIntervention(finishIntervention, debugMode);
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            //                    } catch (JsonProcessingException  exception) {
                            //                      Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to sent ticket to Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de la clôture d'un ticket suite à une réparation définitive
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param permanentlyFixed événement de clôture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processPermanentlyFixed(MongoDatabase mongoDatabase, PermanentlyFixed permanentlyFixed, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        CloseTicketOnPermanentlyFixed closeTicketOnPermanentlyFixed;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = permanentlyFixed.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  PermanentlyFixed event can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        closeTicketOnPermanentlyFixed = new CloseTicketOnPermanentlyFixed(permanentlyFixed, callPurpose, currentContract);
                        System.out.println("  " + closeTicketOnPermanentlyFixed);
                        try {
                            objectMapper.writeValue(new File("testCloseTicketOnPermanentlyFixed_1.json"), closeTicketOnPermanentlyFixed);
                            httpsClient.fixPermanently(closeTicketOnPermanentlyFixed, debugMode);
                            sendAlert("Ticket " + ticketInfos.getClaimNumber().getCallCenterClaimNumber() + " closed because permanently fixed");
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to close ticket in Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de la clôture d'un ticket suite à une demande de devis
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param closedQuoteRequested événement de clôture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processClosedQuoteRequested(MongoDatabase mongoDatabase, ClosedQuoteRequested closedQuoteRequested, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        CloseTicketOnQuoteRequested closeTicketOnQuoteRequested;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = closedQuoteRequested.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  ClosedQuoteRequested event can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        closeTicketOnQuoteRequested = new CloseTicketOnQuoteRequested(closedQuoteRequested, callPurpose, currentContract);
                        System.out.println("  " + closeTicketOnQuoteRequested);
                        try {
                            objectMapper.writeValue(new File("testCloseTicketOnQuoteRequested_1.json"), closeTicketOnQuoteRequested);
                            httpsClient.requestQuote(closeTicketOnQuoteRequested, debugMode);
                            sendAlert("Ticket " + ticketInfos.getClaimNumber().getCallCenterClaimNumber() + " closed because a quote is requested");
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to close ticket in Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de la clôture d'un ticket
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param ticketClosed événement de clôture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processTicketClosed(MongoDatabase mongoDatabase, TicketClosed ticketClosed, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        CloseTicket closeTicket;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = ticketClosed.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  Ticket can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        closeTicket = new CloseTicket(ticketClosed, callPurpose, currentContract);
                        System.out.println("  " + closeTicket);
                        try {
                            objectMapper.writeValue(new File("testCloseTicket_1.json"), closeTicket);
                            httpsClient.closeTicket(closeTicket, debugMode);
                            sendAlert("Ticket " + ticketInfos.getClaimNumber().getCallCenterClaimNumber() + " closed");
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to close ticket in Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de l'annulation d'un ticket
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param ticketCancelled événement de clôture d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processTicketCancelled(MongoDatabase mongoDatabase, TicketCancelled ticketCancelled, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        CancelTicket cancelTicket;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = ticketCancelled.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  Ticket can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        cancelTicket = new CancelTicket(ticketCancelled, callPurpose, currentContract);
                        System.out.println("  " + cancelTicket);
                        try {
                            objectMapper.writeValue(new File("testCloseTicket_1.json"), cancelTicket);
                            httpsClient.cancelTicket(cancelTicket, debugMode);
                            sendAlert("Ticket " + ticketInfos.getClaimNumber().getCallCenterClaimNumber() + " cancelled");
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to cancel ticket in Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Traitement de la mise à jour d'un ticket
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param ticketUpdated événement mise à jour d'un ticket
     * @return résultat de l'opération 1=succès, 0=abandon, -1=erreur
     */
    private int processTicketUpdated(MongoDatabase mongoDatabase, TicketUpdated ticketUpdated, HttpsClient httpsClient) {
        TicketInfos ticketInfos;
        String clientUuid;
        UpdateTicket updateTicket;
        CallPurpose callPurpose;
        Contract2 currentContract;
        int retcode;

        retcode = -1;
        ticketInfos = ticketUpdated.getTicketInfos();
        clientUuid = ticketInfos.getCompanyUid();
        if (isClientAuthorizedToUseAPI(mongoDatabase, clientUuid)) {
            if (isAssetAuthorizedToUseAPI(mongoDatabase, clientUuid, ticketInfos.getAssetReference())) {
                callPurpose = convertCallPurpose(mongoDatabase, clientUuid, ticketInfos.getCallPurposeUid());
                if (callPurpose != null) {
                    if (callPurpose.isAuthorizedToUseAPI()) {
                        System.out.println("  Ticket can be sent to Intent Technologies");
                        currentContract = getCurrentContract(mongoDatabase, clientUuid, ticketInfos.getAssetReference(), ticketInfos.getCallPurposeUid());
                        updateTicket = new UpdateTicket(ticketUpdated, callPurpose, currentContract);
                        System.out.println("  " + updateTicket);
                        try {
                            objectMapper.writeValue(new File("testUpdateTicket_1.json"), updateTicket);
                            httpsClient.updateTicket(updateTicket, debugMode);
                            sendAlert("Ticket " + ticketInfos.getClaimNumber().getCallCenterClaimNumber() + " updated");
                            retcode = 1;
                        } catch (JsonProcessingException | HttpsClientException exception) {
                            Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                            System.out.println("  ERROR : fail to update ticket in Intent Technologies");
                        } catch (IOException exception) {
                            System.out.println("  ERROR : Fail to write Json to file");
                            //                        Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
                        }
                    } else {
                        System.out.println("  ERROR : call purpose :" + callPurpose.getName() + " not authorized to use API");
                    }
                } else {
                    System.out.println("  ERROR : cannot find call purpose Uid:" + ticketInfos.getCallPurposeUid());
                }
            }
        }

        return retcode;
    }

    /**
     * Recupère le contrat en vigueur à partir de :
     *
     * @param mongoDatabase connexion à la base de données Mongodb
     * @param clientUuid référence du client
     * @param assetReference référence de l'immeuble
     * @param callPurposeUuid raison d'appel
     */
    private Contract2 getCurrentContract(MongoDatabase mongoDatabase, String clientUuid, String assetReference, String callPurposeUuid) {
        MongoCollection<Document> collection;
        MongoCursor<Document> cursor;
        int nbClient;
        BasicDBObject filter;
        Contract2 contract2;

        contract2 = new Contract2();
        contract2.setReference("NPM_ANSTEL");

        System.out.println("  getContractReference(mongoDatabase"
                + ", clientUuid:" + clientUuid
                + ", assetReference:" + assetReference
                + ", callPurposeUuid:" + callPurposeUuid);
        filter = new BasicDBObject("clientUuid", clientUuid).append("assetReference", assetReference).append("callPurposeUuid", callPurposeUuid);
        System.out.println("  filter:" + filter);
        collection = mongoDatabase.getCollection("contracts");
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext()) {
            try {
                contract2 = objectMapper.readValue(cursor.next().toJson(), Contract2.class);
                System.out.println("  contrat trouvé : " + contract2.getReference());
            } catch (IOException exception) {
                System.out.println("  ERROR : lecture contrat impossible, clientUuid:" + clientUuid
                        + ", assetReference:" + assetReference
                        + ", callPurposeUuid:" + callPurposeUuid
                        + ", exception:" + exception);
//                Logger.getLogger(A2ITClient.class.getName()).log(Level.SEVERE, null, exception);
            }
        } else {
            System.out.println("  contrat non trouvé, clientUuid:" + clientUuid
                    + ", assetReference:" + assetReference
                    + ", callPurposeUuid:" + callPurposeUuid);
        }

        return contract2;
    }
}
