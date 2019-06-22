package ticketEvents;

import a2itclient.Event;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe encapsulant les événements des tickets
 *
 * @author Thierry Baribaud
 * @version 1.31
 */
@JsonIgnoreProperties({"_id", "eventTypeUid"})
@JsonTypeName("TicketEvent")
public class TicketEvent extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe TicketEvent
     */
    public TicketEvent() {
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
        return "ticketEvent:{"
                + super.toString()
                + ", " + getTicketInfos()
                + "}";
    }

}
