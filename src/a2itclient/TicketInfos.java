package a2itclient;

import bkgpi2a.BasicAddress;
import bkgpi2a.Caller;
import bkgpi2a.ClaimNumber;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe définissant les informations d'un ticket
 *
 * @author Thierry Baribaud
 * @version 1.11
 */
public class TicketInfos {

    /**
     * Référence au client
     */
    private String companyUid;
    
    /**
     * Référence à l'immeuble (Asset)
     */
    private String assetReference;
    
    /**
     * Appelant
     */
    private Caller caller;

//    /**
//     * Personne à rappeler
//     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private ContactToCallback contactToCallback;

    /**
     * Numéro de ticket
     */
    private ClaimNumber claimNumber;

    /**
     * Adresse du lieu d'intervention
     */
    private BasicAddress address;

    /**
     * Desciption du problème
     */
    private String request;

    /**
     * Instruction sur l'incident
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String instructions;

    /**
     * Identifiant de la raison d'appel
     */
    private String callPurposeUid;

    /**
     * Identifiant externe de la raison d'appel
     */
    private String callPurposeExtId;

    /**
     * Raison de l'appel
     */
    private String callPurposeLabel;

    /**
     * Raisons d'appel alternatives ATTENTION : à implémenter sous forme de
     * Map[String, Array[String]]
     */
    private Map<String, String[]> altCallPurpose;

//    /**
//     * Donn�es additionnelles
//     */
//    private Map<String, String> additionalData;

    /**
     * Constructeur de la classe TicketInfos
     */
    public TicketInfos() {
//        additionalData = new HashMap<>();
        altCallPurpose = new HashMap<>();
    }

    /**
     * @return retourne la référence au client
     */
    public String getCompanyUid() {
        return companyUid;
    }

    /**
     * @param companyUid définit la référence au client
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * @return retourne la référence à l'immeuble
     */
    public String getAssetReference() {
        return assetReference;
    }

    /**
     * @param assetReference définit la référence à l'immeuble
     */
    public void setAssetReference(String assetReference) {
        this.assetReference = assetReference;
    }

    /**
     * @return le nom de l'appelant
     */
    public Caller getCaller() {
        return caller;
    }

    /**
     * @param caller définit le nom de l'appelant
     */
    public void setCaller(Caller caller) {
        this.caller = caller;
    }

//    /**
//     * @return le nom du contact � rappeler
//     */
//    public ContactToCallback getContactToCallback() {
//        return contactToCallback;
//    }
//
//    /**
//     * @param contactToCallback définit le nom du contact � rappeler
//     */
//    public void setContactToCallback(ContactToCallback contactToCallback) {
//        this.contactToCallback = contactToCallback;
//    }

    /**
     * @return le numéro de ticket
     */
    public ClaimNumber getClaimNumber() {
        return claimNumber;
    }

    /**
     * @param claimNumber définit le numéro de ticket
     */
    public void setClaimNumber(ClaimNumber claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * @return l'adresse du lieu d'intervention
     */
    public BasicAddress getAddress() {
        return address;
    }

    /**
     * @param address définit l'adresse du lieu d'intervention
     */
    public void setAddress(BasicAddress address) {
        this.address = address;
    }

    /**
     * @return la requête de l'appelant
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request définit la requête de l'appelant
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * @return les instructions sur l'appel
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * @param instructions définit les instructions sur l'appel
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * @return l'identifiant de la raison de l'appel
     */
    public String getCallPurposeUid() {
        return callPurposeUid;
    }

    /**
     * @param callPurposeUid définit l'identifiant de la raison de l'appel
     */
    public void setCallPurposeUid(String callPurposeUid) {
        this.callPurposeUid = callPurposeUid;
    }

    /**
     * @return l'identifiant externe de la raison de l'appel
     */
    public String getCallPurposeExtId() {
        return callPurposeExtId;
    }

    /**
     * @param callPurposeExtId définit l'identifiant externe de la raison de l'appel
     */
    public void setCallPurposeExtId(String callPurposeExtId) {
        this.callPurposeExtId = callPurposeExtId;
    }

    /**
     * @return la raison de l'appel
     */
    public String getCallPurposeLabel() {
        return callPurposeLabel;
    }

    /**
     * @param callPurposeLabel définit la raison de l'appel
     */
    public void setCallPurposeLabel(String callPurposeLabel) {
        this.callPurposeLabel = callPurposeLabel;
    }

    /**
     * @return les raisons d'appel alternatives
     */
    public Map<String, String[]> getAltCallPurpose() {
        return altCallPurpose;
    }

    /**
     * @param altCallPurpose définit les raisons d'appel alternatives
     */
    public void setAltCallPurpose(Map<String, String[]> altCallPurpose) {
        this.altCallPurpose = altCallPurpose;
    }

//    /**
//     * @return les données additionnelles
//     */
//    public Map<String, String> getAdditionalData() {
//        return additionalData;
//    }
//
//    /**
//     * @param additionalData définit les données additionnelles
//     */
//    public void setAdditionalData(Map<String, String> additionalData) {
//        this.additionalData = additionalData;
//    }
//
//    /**
//     * Ajoute une donn�e compl�mentaire � la liste
//     *
//     * @param key clef pour indexer la valeur
//     * @param value valeur � indexer
//     */
//    public void put(String key, String value) {
//        this.additionalData.put(key, value);
//    }

    /**
     * Ajoute une raisons d'appel alternative à la liste
     *
     * @param key clef pour indexer la valeur
     * @param value tableau de valeurs à indexer
     */
    public void put(String key, String[] value) {
        this.altCallPurpose.put(key, value);
    }

    /**
     * @return Retourne l'adresse sous forme textuelle
     */
    @Override
    public String toString() {
        return "TicketInfo:{"
                + "companyUid:" + getCompanyUid()
                + ", caller:" + getCaller()
                + ", assetReference:" + getAssetReference()
//                + ", contactToCallback:" + getContactToCallback()
                + ", claimNumber:" + getClaimNumber()
                + ", address:" + getAddress()
                + ", request:" + getRequest()
                + ", instructions:" + getInstructions()
                + ", callPurposeUid :" + getCallPurposeUid()
                + ", callPurposeExtId :" + getCallPurposeExtId()
                + ", callPurposeLabel:" + getCallPurposeLabel()
                + ", altCallPurpose:" + getAltCallPurpose()
//                + ", additionalData:" + getAdditionalData()
                + "}";
    }

}
