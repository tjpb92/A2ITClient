package ticketCommands;

import a2itclient.CallPurpose;
import a2itclient.Contact;
import a2itclient.ContactList;
import a2itclient.Contract2;
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
 * @version 1.33
 */
public class OpenTicket extends TicketCommand {

    /**
     * Personnes à contacter sur le ticket
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> contacts;

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
        super(ticketOpened.getOpenedDate(), ticketOpened.getDate(), ticketOpened.getTicketInfos(), callPurpose, currentContract);

        TicketInfos ticketInfos;

        ticketInfos = ticketOpened.getTicketInfos();

        setStatus("open");
        setEvent("requested");

        this.contacts = new ArrayList();
        this.setContacts(ticketInfos.getContacts());
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
     * @return Retourne la commande OpenTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "openTicket:{"
                + super.toString()
                //                + ", contacts:" + contacts.replace("\r", " ").replace("\n", "")
                + "}";
    }
}
