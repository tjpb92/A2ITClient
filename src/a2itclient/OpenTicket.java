package a2itclient;

/**
 * Classe décrivant la commande d'ouverture de ticket
 * @author Thierry Baribaud
 * @version 1.11
 */
public class OpenTicket {
    
    private String reference;
    private String description;
    private String contractReference;
    private String status;
    private String event;
    private String eventDate;
    private String logDate;
    private String serviceCode;
    private Location location;
    private String workType;
    
    /**
     * Contructeur principal de la classe OpenTicket
     */
    public OpenTicket() {
        
    }
    
    /**
     * Constructeur secondaire de la classe OpenTicket
     * @param ticketOpened événement d'ouvert de ticket
     */
    public OpenTicket(TicketOpened ticketOpened) {
        TicketInfos ticketInfos;
        Location thisLocation;
        
        ticketInfos = ticketOpened.getTicketInfos();
        this.reference = ticketInfos.getClaimNumber().getCallCenterClaimNumber();
        this.description = ticketInfos.getRequest();
        this.contractReference = "NPM_ANSTEL";
        this.status = "open";
        this.event = "requested";
        this.eventDate = ticketOpened.getOpenedDate();
        this.logDate = ticketOpened.getDate();
        this.serviceCode = ticketInfos.getCallPurposeExtId() + " " + ticketInfos.getCallPurposeLabel();
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        this.location = thisLocation;
        this.workType = "corrective";
    }

    /**
     * @return Retourne la commande OpenTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "openTicket:{"
                + "reference:" + reference
                + ", description:" + description
                + ", contractReference:" + contractReference
                + ", status:" + status
                + ", event:" + event
                + ", eventDate:" + eventDate
                + ", logDate:" + logDate
                + ", serviceCode:" + serviceCode
                + ", location:" + location
                + ", workType:" + workType
                + "}";
    }
}
