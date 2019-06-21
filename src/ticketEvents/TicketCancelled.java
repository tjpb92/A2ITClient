package ticketEvents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.TICKET_CANCELLED;

/**
 * Classe décrivant l'annulation d'un ticket : TicketCancelled, #585
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("TicketCancelled")
public class TicketCancelled extends TicketEvent {

    /**
     * Constructeur de la classe TicketCancelled
     */
    public TicketCancelled() {
        setEventTypeUid(TICKET_CANCELLED.getUid());
        setEventType(TICKET_CANCELLED.getName());
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "ticketCancelled:{"
                + super.toString()
                + ", " + getTicketInfos()
                + "}";
    }

}
