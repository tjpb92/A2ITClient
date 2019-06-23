package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import a2itclient.Location;
import ticketEvents.TicketClosed;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant la commande de clôture de ticket
 *
 * @author Thierry Baribaud
 * @version 1.32
 */
public class CloseTicket extends TicketCommand {

    private String description;
    private String status;
    private String event;
    private String eventDate;
    private String logDate;
    private String serviceCode;
    private Location location;

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Contructeur principal de la classe CloseTicket
     */
    public CloseTicket() {
    }

    /**
     * Constructeur secondaire de la classe CloseTicket
     *
     * @param ticketClosed événement d'ouvert de ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public CloseTicket(TicketClosed ticketClosed, CallPurpose callPurpose, Contract2 currentContract) {
        super(ticketClosed.getTicketInfos(), callPurpose, currentContract);

        TicketInfos ticketInfos;
        Location thisLocation;

        ticketInfos = ticketClosed.getTicketInfos();
        this.description = ticketInfos.getRequest();
        this.status = "closed";
        this.event = "done";
        this.eventDate = ticketClosed.getClosedDate();
        this.logDate = ticketClosed.getDate();
        this.serviceCode = String.valueOf(callPurpose.getReferenceCode());
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        this.location = thisLocation;
        this.technicalReason = ticketInfos.getTechnicalReason();
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return Retourne la commande CloseTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "closeTicket:{"
                + super.toString()
                + ", description:" + getDescription()
                + ", status:" + getStatus()
                + ", event:" + getEvent()
                + ", eventDate:" + getEventDate()
                + ", logDate:" + getLogDate()
                + ", serviceCode:" + getServiceCode()
                + ", location:" + getLocation()
                + ", technicalReason:" + getTechnicalReason()
                + "}";
    }
}
