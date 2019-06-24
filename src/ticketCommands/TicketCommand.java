package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import a2itclient.Location;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant une commande sur les tickets
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class TicketCommand {

    /**
     * Référence du ticket
     */
    private String reference;

    /**
     * Description de la demande
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    
    /**
     * Référence du contrat
     */
    private String contractReference;
    
    /**
     * Statut du ticket
     */
    private String status;
    
    /**
     * Evénement courant sur le ticket
     */
    private String event;

    /**
     * Date de l'événement
     */
    private String eventDate;
    
    /**
     * Date d'envoi de l'événement
     */
    private String logDate;
    
    /**
     * Code de la raison d'appel
     */
    private String serviceCode;
    
    /**
     * Localisation de la panne
     */
    private Location location;


    /**
     * Type d'opération
     */
    private String workType;

    /**
     * Entité à l'origine de la demande
     */
    private String origin;

    /**
     * Niveau de criticité du ticket : min=default=0 à max=5 0 : Non précisé 1 :
     * Non urgente 2 : Urgente 3 : Stratégique 4 : Prioritaire
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int criticalLevel;

    /**
     * Contructeur principal de la classe TicketCommand
     */
    public TicketCommand() {
        contractReference = "NPM_ANSTEL";
        workType = "corrective";
        origin = "other";
        criticalLevel = 0;
    }

    /**
     * Constructeur secondaire de la classe TicketCommand
     *
     * @param eventDate date de l'événement courant
     * @param logDate date d'envoi de l'événement courant
     * @param ticketInfos informations sur le ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public TicketCommand(String eventDate, String logDate, TicketInfos ticketInfos, CallPurpose callPurpose, Contract2 currentContract) {
        this();
        
        Location thisLocation;
        
        reference = reference = ticketInfos.getClaimNumber().getCallCenterClaimNumber();
        description = ticketInfos.getRequest();
        contractReference = currentContract.getReference();
        this.eventDate = eventDate;
        this.logDate = logDate;
        serviceCode = String.valueOf(callPurpose.getReferenceCode());
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        location = thisLocation;
        criticalLevel = ticketInfos.getCriticalLevel();
    }

    /**
     * @return retourne la référence du ticket
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference définit la référence du ticket
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * @return retourne la description de le demande
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description définit la description de le demande
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return retourne la référence du contrat
     */
    public String getContractReference() {
        return contractReference;
    }

    /**
     * @param contractReference définit la référence du contrat
     */
    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    /**
     * @return retourne le status du ticket
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status définit le status du ticket
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return retourne l'événement courant sur le ticket
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event définit l'événement courant sur le ticket
     */
    public void setEvent(String event) {
        this.event = event;
    }
    /**
     * @return la date de l'événement courant
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate définit la date de l'événement courant
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return retourne la date de l'envoi de l'événement courant
     */
    public String getLogDate() {
        return logDate;
    }

    /**
     * @param logDate définit la date de l'envoi de l'événement courant
     */
    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    /**
     * @return retourne le code de la raison d'appel
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode définit le code de la raison d'appel
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * @return retourne la localisation de la panne
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location définit la localisation de la panne
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return retourne le type d'opération
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType définit le type d'opération
     */
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    /**
     * @return retourne l'entité à l'origine de la demande
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin définit l'entité à l'origine de la demande
     */
    public void setOrigin(String origin) {
        this.origin = origin;

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

    /**
     * @return Retourne la commande TicketCommand sous forme textuelle
     */
    @Override
    public String toString() {
        return "ticketCommand:{"
                + "reference:" + getReference()
                + ", description:" + getDescription()
                + ", contractReference:" + getContractReference()
                + ", status:" + getStatus()
                + ", event:" + getEvent()
                + ", eventDate:" + getEventDate()
                + ", logDate:" + getLogDate()
                + ", serviceCode:" + getServiceCode()
                + ", location:" + getLocation()
                + ", workType:" + getWorkType()
                + ", origin:" + getOrigin()
                + ", criticalLevel:" + getCriticalLevel()
                + "}";
    }
}
