package ticketCommands;

import a2itclient.CallPurpose;
import a2itclient.Contact;
import a2itclient.ContactList;
import a2itclient.Contract2;
import a2itclient.TicketInfos;
import ticketEvents.TicketUpdated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe décrivant la commande de mise à jour de ticket
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class UpdateTicket extends TicketCommand {

    /**
     * Personnes à contacter sur le ticket
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> contacts;

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Contructeur principal de la classe UpdateTicket
     */
    public UpdateTicket() {
    }

    /**
     * Constructeur secondaire de la classe UpdateTicket
     *
     * @param ticketUpdated événement de mise à jour de ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public UpdateTicket(TicketUpdated ticketUpdated, CallPurpose callPurpose, Contract2 currentContract) {
        super(ticketUpdated.getDate(), ticketUpdated.getDate(), ticketUpdated.getTicketInfos(), callPurpose, currentContract);

        TicketInfos ticketInfos;

        ticketInfos = ticketUpdated.getTicketInfos();
        
        setStatus("open");
        setEvent("requested");
        
        this.contacts = new ArrayList();
        this.setContacts(ticketInfos.getContacts());
        this.technicalReason = ticketInfos.getTechnicalReason();
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
     * @param contacts définit les contacts sur le ticket
     */
    public void setContacts(ContactList contacts) {
        for (Contact contact : contacts) {
            this.contacts.add(contact.toVCard());
        }
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
        return "updateTicket:{"
                + super.toString()
                + ", technicalReason:" + getTechnicalReason()
                + "}";
    }
}
