package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contact;
import a2itclient.ContactList;
import a2itclient.Contract2;
import a2itclient.Location;
import a2itclient.TicketInfos;
import ticketEvents.TicketOpened;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe décrivant la commande d'ouverture de ticket
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
public class OpenTicket extends TicketCommand {

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
     * Personnes à contacter sur le ticket
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> contacts;

    /**
     * Entité à l'origine de la demande
     */
    private String origin;

    /**
     * Contructeur principal de la classe OpenTicket
     */
    public OpenTicket() {
    }

    /**
     * Constructeur secondaire de la classe OpenTicket
     *
     * @param ticketOpened événement d'ouverture de ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public OpenTicket(TicketOpened ticketOpened, CallPurpose callPurpose, Contract2 currentContract) {
        super(ticketOpened.getTicketInfos(), callPurpose, currentContract);
        
        TicketInfos ticketInfos;
        Location thisLocation;

        ticketInfos = ticketOpened.getTicketInfos();
        this.reference = ticketInfos.getClaimNumber().getCallCenterClaimNumber();
        this.description = ticketInfos.getRequest();
        this.contractReference = currentContract.getReference();
        this.status = "open";
        this.event = "requested";
        this.eventDate = ticketOpened.getOpenedDate();
        this.logDate = ticketOpened.getDate();
//        this.serviceCode = ticketInfos.getCallPurposeExtId() + " " + ticketInfos.getCallPurposeLabel();
//        this.serviceCode = ticketInfos.getCallPurposeLabel();
//        this.serviceCode = callPurpose.getReference();
        this.serviceCode = String.valueOf(callPurpose.getReferenceCode());
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        this.location = thisLocation;
        this.workType = "corrective";
        this.contacts = new ArrayList();
        this.setContacts(ticketInfos.getContacts());
        this.origin = "other";
    }

    /**
     * @param contacts définit les contacts sur le ticket
     */
    public void setContacts(ContactList contacts) {
        for (Contact contact : contacts) {
            this.contacts.add(contact.toVCard());
        }
    }

    /**
     * @return retourne la référence de l'asset
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference définit la référence de l'asset
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return retourne la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description définit la description
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
     * @return retourne le status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status définit le status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return retourne l'événement
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event définit l'événement
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return retourne la date de l'événement
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate définit la date de l'événement
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return retourne la date de traitement de l'événement
     */
    public String getLogDate() {
        return logDate;
    }

    /**
     * @param logDate définit la date de traitement de l'événement
     */
    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    /**
     * @return retourne le serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode définit le serviceCode
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * @return retourne la localisation
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location définit la localisation
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return retourne le workType
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType définit le workType
     */
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    /**
     * @return retourne les contacts
     */
    public List<String> getContacts() {
        return contacts;
    }

    /**
     * @param contacts définit les contacts
     */
    @JsonSetter("contacts")
    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
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
     * @return Retourne la commande OpenTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "openTicket:{"
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
                //                + ", contacts:" + contacts.replace("\r", " ").replace("\n", "")
                + ", origin:" + getOrigin()
                + "}";
    }
}
