package ticketCommands;

import a2itclient.CallPurpose;
import a2itclient.Contract2;
import ticketEvents.TicketClosed;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant la commande de clôture de ticket
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class CloseTicket extends TicketCommand {

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Contructeur principal de la classe CloseTicket
     */
    public CloseTicket() {
    }

    /**
     * Constructeur secondaire de la classe CloseTicket
     *
     * @param ticketClosed événement d'ouvert de ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public CloseTicket(TicketClosed ticketClosed, CallPurpose callPurpose, Contract2 currentContract) {
        super(ticketClosed.getClosedDate(), ticketClosed.getDate(), ticketClosed.getTicketInfos(), callPurpose, currentContract);

        setStatus("closed");
        setEvent("done");
        this.technicalReason = ticketClosed.getTicketInfos().getTechnicalReason();
    }
    /**
     * @return retourne la nature de la panne
     */
    public String getTechnicalReason() {
        return technicalReason;
    }

    /**
     * @param technicalReason définit la nature de la panne
     */
    public void setTechnicalReason(String technicalReason) {
        this.technicalReason = technicalReason;
    }

    /**
     * @return Retourne la commande CloseTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "closeTicket:{"
                + super.toString()
                + ", technicalReason:" + getTechnicalReason()
                + "}";
    }
}
