package ticketEvents;

import a2itclient.Event;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.INTERVENTION_FINISHED;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant l'événement de fin d'intervention : InterventionFinished,
 * #534
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("InterventionFinished")
public class InterventionFinished extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe InterventionFinished
     */
    public InterventionFinished() {
        setEventTypeUid(INTERVENTION_FINISHED.getUid());
        setEventType(INTERVENTION_FINISHED.getName());
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
    public String getFinishedDate() {
        return getDate();
    }

    /**
     * @param finishedDate définit la date à laquelle l'événement a eu lieu
     */
    public void setFinishedDate(String finishedDate) {
        setDate(finishedDate);
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "InterventionFinished:{"
                + super.toString()
                + ", " + getTicketInfos()
                + ", finishedDate:" + getFinishedDate()
                + "}";
    }

}
