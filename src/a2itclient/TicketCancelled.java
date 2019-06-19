package a2itclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.TICKET_CANCELLED;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant l'annuation d'un ticket : TicketCancelled, #585
 *
 * @author Thierry Baribaud
 * @version 1.28
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("TicketCancelled")
public class TicketCancelled extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe TicketCancelled
     */
    public TicketCancelled() {
        setEventTypeUid(TICKET_CANCELLED.getUid());
        setEventType(TICKET_CANCELLED.getName());
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
