package ticketEvents;

import a2itclient.Event;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.INTERVENTION_STARTED;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant l'événement de début d'intervention : InterventionStarted,
 * #532
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("InterventionStarted")
public class InterventionStarted extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe InterventionStarted
     */
    public InterventionStarted() {
        setEventTypeUid(INTERVENTION_STARTED.getUid());
        setEventType(INTERVENTION_STARTED.getName());
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
    public String getStartedDate() {
        return getDate();
    }

    /**
     * @param startedDate définit la date à laquelle l'événement a eu lieu
     */
    public void setStartedDate(String startedDate) {
        setDate(startedDate);
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "InterventionStarted:{"
                + super.toString()
                + ", " + getTicketInfos()
                + ", startedDate:" + getStartedDate()
                + "}";
    }

}
