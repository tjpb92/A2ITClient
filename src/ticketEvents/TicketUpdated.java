package ticketEvents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.TICKET_UPDATED;

/**
 * Classe décrivant l'événement de mise à jour du ticket : TicketUpdated, #510
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("TicketUpdated")
public class TicketUpdated extends TicketEvent {

    /**
     * Constructeur de la classe TicketUpdated
     */
    public TicketUpdated() {
        setEventTypeUid(TICKET_UPDATED.getUid());
        setEventType(TICKET_UPDATED.getName());
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "ticketUpdated:{"
                + super.toString()
                + "}";
    }

}
