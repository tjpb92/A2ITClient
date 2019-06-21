package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import a2itclient.Location;
import a2itclient.TicketInfos;
import ticketEvents.InterventionStarted;

/**
 * Classe décrivant la commande de début d'intervention
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
public class StartIntervention extends TicketCommand {

    private String reference;
    private String contractReference;
    private String status;
    private String event;
    private String eventDate;
    private String logDate;
    private String serviceCode;
    private Location location;
    private String workType;

    /**
     * Entité à l'origine de la demande
     */
    private String origin;

    /**
     * Contructeur principal de la classe StartIntervention
     */
    public StartIntervention() {
    }

    /**
     * Constructeur secondaire de la classe StartIntervention
     *
     * @param interventionStarted événement de début d'intervention
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public StartIntervention(InterventionStarted interventionStarted, CallPurpose callPurpose, Contract2 currentContract) {
        super(interventionStarted.getTicketInfos(), callPurpose, currentContract);
        TicketInfos ticketInfos;
        Location thisLocation;

        ticketInfos = interventionStarted.getTicketInfos();
        this.reference = ticketInfos.getClaimNumber().getCallCenterClaimNumber();
        this.contractReference = currentContract.getReference();
        this.status = "pending";
        this.event = "start";
        this.eventDate = interventionStarted.getStartedDate();
        this.logDate = interventionStarted.getDate();
//        this.serviceCode = ticketInfos.getCallPurposeExtId() + " " + ticketInfos.getCallPurposeLabel();
//        this.serviceCode = callPurpose.getReference();
        this.serviceCode = String.valueOf(callPurpose.getReferenceCode());
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        this.location = thisLocation;
        this.workType = "corrective";
        this.origin = "other";
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the contractReference
     */
    public String getContractReference() {
        return contractReference;
    }

    /**
     * @param contractReference the contractReference to set
     */
    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the event
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return the eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the logDate
     */
    public String getLogDate() {
        return logDate;
    }

    /**
     * @param logDate the logDate to set
     */
    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the workType
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType the workType to set
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
     * @return Retourne la commande CloseTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "startIntervention:{"
                + "reference:" + getReference()
                + ", contractReference:" + getContractReference()
                + ", status:" + getStatus()
                + ", event:" + getEvent()
                + ", eventDate:" + getEventDate()
                + ", logDate:" + getLogDate()
                + ", serviceCode:" + getServiceCode()
                + ", location:" + getLocation()
                + ", workType:" + getWorkType()
                + ", origin:" + getOrigin()
                + "}";
    }
}
