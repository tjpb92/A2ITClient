package a2itclient;

import static bkgpi2a.EventType.TICKET_CLOSED;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import bkgpi2a.Operator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe décrivant l'événement de clôture de ticket : TicketClosed, #561
 *
 * @author Thierry Baribaud
 * @version 1.15
 */
@JsonIgnoreProperties({"_id", "date", "eventTypeUid"})
@JsonTypeName("TicketClosed")
public class TicketClosed extends Event {

//    /**
//     * Référence à l'agence ou au patrimoine
//     */
//    private LocationReference locationRef;
    /**
     * Opérateur ayant ajouté le ticketInfos
     */
//    private Operator operator;

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

//    /**
//     * @return la référence à l'agence ou au patrimoine
//     */
//    public LocationReference getLocationRef() {
//        return locationRef;
//    }
//
//    /**
//     * @param locationRef définit la référence à l'agence ou au patrimoine
//     */
//    public void setLocationRef(LocationReference locationRef) {
//        this.locationRef = locationRef;
//    }
    /**
     * @return l'opérateur ayant ajouté le ticketInfos
     */
//    public Operator getOperator() {
//        return operator;
//    }

    /**
     * @param operator définit l'opérateur ayant ajouté le ticketInfos
     */
//    public void setOperator(Operator operator) {
//        this.operator = operator;
//    }

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
                //                + ", " + getLocationRef()
//                + ", " + getOperator()
                + ", " + getTicketInfos()
                + ", closedDate:" + getClosedDate()
                + "}";
    }

}
