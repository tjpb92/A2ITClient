package a2itclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static bkgpi2a.EventType.PERMANENTLY_FIXED;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant une clôture d'appel définitive : PermanentlyFixed, #535
 *
 * @author Thierry Baribaud
 * @version 1.16
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("PermanentlyFixed")
public class PermanentlyFixed extends Event {

    /**
     * Informations sur le ticket
     */
    @JsonProperty("ticket")
    private TicketInfos ticketInfos;

    /**
     * Constructeur de la classe PermanentlyFixed
     */
    public PermanentlyFixed() {
        setEventTypeUid(PERMANENTLY_FIXED.getUid());
        setEventType(PERMANENTLY_FIXED.getName());
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
        return "PermanentlyFixed:{"
                + super.toString()
                + ", " + getTicketInfos()
                + ", closedDate:" + getClosedDate()
                + "}";
    }

}
