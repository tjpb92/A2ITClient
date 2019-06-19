package a2itclient;

import bkgpi2a.BasicAddress;
import bkgpi2a.ClaimNumber;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe définissant les informations d'un ticket
 *
 * @author Thierry Baribaud
 * @version 1.28
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
     * Référence au contrat (Contract)
     */
    private String contractReference;

    /**
     * Contacts
     */
    private ContactList contacts;

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

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;
    
    /**
     * Niveau de criticité du ticket : min=default=0 à max=5
     * ... définition
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int criticalLevel;

//    /**
//     * Données additionnelles
//     */
//    private Map<String, String> additionalData;
    /**
     * Constructeur de la classe TicketInfos
     */
    public TicketInfos() {
//        additionalData = new HashMap<>();
        altCallPurpose = new HashMap<>();
        criticalLevel = 0;
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
     * @return retourne la référence au contrat
     */
    public String getContractReference() {
        return contractReference;
    }

    /**
     * @param contractReference définit la référence au contrat
     */
    public void setContratReference(String contractReference) {
        this.contractReference = contractReference;
    }

    /**
     * @return retourne les contacts
     */
    public ContactList getContacts() {
        return contacts;
    }

    /**
     * @param contacts définit ls contacts
     */
    public void setContacts(ContactList contacts) {
        this.contacts = contacts;
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
     * @param callPurposeExtId définit l'identifiant externe de la raison de
     * l'appel
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

    /**
     * @return retourne la nature de la panne
     */
    public String getTechnicalReason() {
        return technicalReason;
    }

    /**
     * @param technicalReason définit la nature de la panne
     */
    public void setTechnicalReason(String technicalReason) {
        this.technicalReason = technicalReason;
    }

    /**
     * @return retourne le niveau de criticité du ticket
     */
    public int getCriticalLevel() {
        return criticalLevel;
    }

    /**
     * @param criticalLevel définit le niveau de criticité du ticket
     */
    public void setCriticalLevel(int criticalLevel) {
        this.criticalLevel = criticalLevel;
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
                + ", contacts:" + getContacts()
                + ", assetReference:" + getAssetReference()
                + ", contractReference:" + getContractReference()
                //                + ", contactToCallback:" + getContactToCallback()
                + ", claimNumber:" + getClaimNumber()
                + ", address:" + getAddress()
                + ", request:" + getRequest()
                + ", instructions:" + getInstructions()
                + ", callPurposeUid :" + getCallPurposeUid()
                + ", callPurposeExtId :" + getCallPurposeExtId()
                + ", callPurposeLabel:" + getCallPurposeLabel()
                + ", altCallPurpose:" + getAltCallPurpose()
                + ", technicalReason:" + getTechnicalReason()
                + ", criticalLevel:" + getCriticalLevel()
                //                + ", additionalData:" + getAdditionalData()
                + "}";
    }

}
