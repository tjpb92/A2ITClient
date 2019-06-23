package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import ticketEvents.InterventionFinished;
import a2itclient.Location;
import a2itclient.TicketInfos;

/**
 * Classe décrivant la commande de fin d'intervention
 *
 * @author Thierry Baribaud
 * @version 1.32
 */
public class FinishIntervention extends TicketCommand {

    private String status;
    private String event;
    private String eventDate;
    private String logDate;
    private String serviceCode;
    private Location location;

    /**
     * Contructeur principal de la classe FinishtIntervention
     */
    public FinishIntervention() {
    }

    /**
     * Constructeur secondaire de la classe FinishtIntervention
     *
     * @param interventionFinished événement de début d'intervention
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public FinishIntervention(InterventionFinished interventionFinished, CallPurpose callPurpose, Contract2 currentContract) {
        super(interventionFinished.getTicketInfos(), callPurpose, currentContract);

        TicketInfos ticketInfos;
        Location thisLocation;

        ticketInfos = interventionFinished.getTicketInfos();
        this.status = "pending";
        this.event = "end";
        this.eventDate = interventionFinished.getFinishedDate();
        this.logDate = interventionFinished.getDate();
        this.serviceCode = String.valueOf(callPurpose.getReferenceCode());
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        this.location = thisLocation;
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
     * @return Retourne la commande CloseTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "finishIntervention:{"
                + super.toString()
                + ", status:" + getStatus()
                + ", event:" + getEvent()
                + ", eventDate:" + getEventDate()
                + ", logDate:" + getLogDate()
                + ", serviceCode:" + getServiceCode()
                + ", location:" + getLocation()
                + "}";
    }
}
