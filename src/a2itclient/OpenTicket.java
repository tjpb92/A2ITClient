package a2itclient;

import bkgpi2a.AutomatonCaller;
import bkgpi2a.Caller;
import bkgpi2a.CivilName;
import bkgpi2a.ContactMedium;
import bkgpi2a.Sms;
import bkgpi2a.Fax;
import bkgpi2a.HumanCaller;
import bkgpi2a.Mail;
import bkgpi2a.Name;
import bkgpi2a.Phone;
import bkgpi2a.PoorName;
import com.fasterxml.jackson.annotation.JsonInclude;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.StructuredName;

/**
 * Classe décrivant la commande d'ouverture de ticket
 * @author Thierry Baribaud
 * @version 1.13
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
     * Personnes à contacter sur le ticket
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contacts;
    
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
//        setContacts(ticketInfos.getCaller());
    }

    /**
     * @return les personnes à contacter sur le ticket
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts définit les personnes à contacter sur le ticket
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * @param caller définit les personnes à contacter sur le ticket
     */
    public void setContacts(Caller caller) {
        VCard vcard = new VCard();
        StructuredName structuredName = new StructuredName();
        HumanCaller humanCaller;
        Name name;
        CivilName civilName;
        PoorName poorName;
        ContactMedium medium;
        
        if (caller instanceof HumanCaller) {
            humanCaller = (HumanCaller) caller;
            medium = humanCaller.getMedium();
            if (medium instanceof Phone) {
                vcard.addTelephoneNumber(((Phone)medium).getPhone(), TelephoneType.VOICE);
            } else if (medium instanceof Fax) {
                vcard.addTelephoneNumber(((Fax)medium).getFax(), TelephoneType.FAX);
            } else if (medium instanceof Sms) {
                vcard.addTelephoneNumber(((Sms)medium).getPhone(), TelephoneType.TEXT);
            } else if (medium instanceof Mail) {
                vcard.addEmail(((Mail)medium).getMail(), EmailType.INTERNET);
            }
            name = humanCaller.getName();
            if (name instanceof CivilName) {
                civilName = (CivilName) name;
                structuredName.setFamily(civilName.getLastName());
                structuredName.setGiven(civilName.getFirstName());
                vcard.setStructuredName(structuredName);
                this.contacts = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
            } else if (name instanceof PoorName) {
                poorName = (PoorName) name;
                structuredName.setFamily(poorName.getValue());
                vcard.setStructuredName(structuredName);
                this.contacts = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
            } else {
                this.contacts = null;
            }
        } else if (caller instanceof AutomatonCaller) {
            this.contacts = "automate";
        } else {
            this.contacts = null;
        }
        
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
                + "}";
    }

}
