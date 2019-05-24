package a2itclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.CLOSED_AFTER_SEVERAL_UNSUCCESSFUL_RECALLS;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant une clôture d'appel après plusieurs relances infructueuses :
 * ClosedAfterSeveralUnsuccessfulRecalls, #560
 *
 * @author Thierry Baribaud
 * @version 1.16
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("ClosedAfterSeveralUnsuccessfulRecalls")
public class ClosedAfterSeveralUnsuccessfulRecalls extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe ClosedAfterSeveralUnsuccessfulRecalls
     */
    public ClosedAfterSeveralUnsuccessfulRecalls() {
        setEventTypeUid(CLOSED_AFTER_SEVERAL_UNSUCCESSFUL_RECALLS.getUid());
        setEventType(CLOSED_AFTER_SEVERAL_UNSUCCESSFUL_RECALLS.getName());
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
        return "ClosedAfterSeveralUnsuccessfulRecalls:{"
                + super.toString()
                + ", " + getTicketInfos()
                + ", closedDate:" + getClosedDate()
                + "}";
    }

}
