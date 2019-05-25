package a2itclient;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant la commande de clôture de ticket
 *
 * @author Thierry Baribaud
 * @version 1.18
 */
public class CloseTicket {

    private String reference;
//    private String description;
    private String contractReference;
    private String status;
    private String event;
    private String eventDate;
    private String logDate;
    private String serviceCode;
    private Location location;
    private String workType;

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Personnes à contacter sur le ticket
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private List<String> contacts;
    /**
     * Entité à l'origine de la demande
     */
    private String origin;

    /**
     * Contructeur principal de la classe CloseTicket
     */
    public CloseTicket() {
    }

    /**
     * Constructeur secondaire de la classe CloseTicket
     *
     * @param ticketClosed événement d'ouvert de ticket
     */
    public CloseTicket(TicketClosed ticketClosed) {
        TicketInfos ticketInfos;
        Location thisLocation;

        ticketInfos = ticketClosed.getTicketInfos();
        this.reference = ticketInfos.getClaimNumber().getCallCenterClaimNumber();
//        this.description = ticketInfos.getRequest();
        this.contractReference = "NPM_ANSTEL";
        this.status = "closed";
        this.event = "done";
        this.eventDate = ticketClosed.getClosedDate();
        this.logDate = ticketClosed.getDate();
        this.serviceCode = ticketInfos.getCallPurposeExtId() + " " + ticketInfos.getCallPurposeLabel();
        thisLocation = new Location();
        thisLocation.setAssetReference(ticketInfos.getAssetReference());
        thisLocation.setAddress(new Address(ticketInfos.getAddress()));
        this.location = thisLocation;
        this.workType = "corrective";
//        this.contacts = new ArrayList();
//        this.addContact(ticketInfos.getCaller());
        this.origin = "other";
        this.technicalReason = ticketInfos.getTechnicalReason();
    }

    /**
     * @param caller ajoute un contact sur le ticket
     */
//    public void addContact(Caller caller) {
//        VCard vcard = new VCard();
//        StructuredName structuredName = new StructuredName();
//        HumanCaller humanCaller;
//        Name name;
//        CivilName civilName;
//        PoorName poorName;
//        ContactMedium medium;
//        
//        if (caller instanceof HumanCaller) {
//            humanCaller = (HumanCaller) caller;
//            medium = humanCaller.getMedium();
//            if (medium instanceof Phone) {
//                vcard.addTelephoneNumber(((Phone)medium).getPhone(), TelephoneType.VOICE);
//            } else if (medium instanceof Fax) {
//                vcard.addTelephoneNumber(((Fax)medium).getFax(), TelephoneType.FAX);
//            } else if (medium instanceof Sms) {
//                vcard.addTelephoneNumber(((Sms)medium).getPhone(), TelephoneType.TEXT);
//            } else if (medium instanceof Mail) {
//                vcard.addEmail(((Mail)medium).getMail(), EmailType.INTERNET);
//            }
//            name = humanCaller.getName();
//            if (name instanceof CivilName) {
//                civilName = (CivilName) name;
//                structuredName.setFamily(civilName.getLastName());
//                structuredName.setGiven(civilName.getFirstName());
//                vcard.setStructuredName(structuredName);
//                this.getContacts().add(Ezvcard.write(vcard).version(VCardVersion.V4_0).go());
//            } else if (name instanceof PoorName) {
//                poorName = (PoorName) name;
//                structuredName.setFamily(poorName.getValue());
//                vcard.setStructuredName(structuredName);
//                this.getContacts().add(Ezvcard.write(vcard).version(VCardVersion.V4_0).go());
//            } else {
//                this.setContacts(null);
//            }
//        } else if (caller instanceof AutomatonCaller) {
//            this.getContacts().add("automate");
//        } else {
//            this.setContacts(null);
//        }
//    }
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
//    public String getDescription() {
//        return description;
//    }
    /**
     * @param description the description to set
     */
//    public void setDescription(String description) {
//        this.description = description;
//    }
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
     * @return the contacts
     */
//    public List<String> getContacts() {
//        return contacts;
//    }
    /**
     * @param contacts the contacts to set
     */
//    public void setContacts(List<String> contacts) {
//        this.contacts = contacts;
//    }
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
                + "reference:" + getReference()
                //                + ", description:" + getDescription()
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
                + ", technicalReason:" + getTechnicalReason()
                + "}";
    }
}
