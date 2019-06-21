package ticketEvents;

import a2itclient.Event;
import a2itclient.TicketInfos;
import static bkgpi2a.EventType.TICKET_CLOSED;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant l'événement de clôture de ticket : TicketClosed, #561
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("TicketClosed")
public class TicketClosed extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe MessageAdded
     */
    public TicketClosed() {
        setEventTypeUid(TICKET_CLOSED.getUid());
        setEventType(TICKET_CLOSED.getName());
    }

    /**
     * @return les informations sur le ticket
     */
    public TicketInfos getTicketInfos() {
        return ticketInfos;
    }

    /**
     * @param ticketInfos définit les informations sur le ticket
     */
    public void setTicketInfos(TicketInfos ticketInfos) {
        this.ticketInfos = ticketInfos;
    }

    /**
     * @return la date à laquelle l'événement a eu lieu
     */
    public String getClosedDate() {
        return getDate();
    }

    /**
     * @param closedDate définit la date à laquelle l'événement a eu lieu
     */
    public void setClosedDate(String closedDate) {
        setDate(closedDate);
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "TicketClosed:{"
                + super.toString()
                + ", " + getTicketInfos()
                + ", closedDate:" + getClosedDate()
                + "}";
    }

}
