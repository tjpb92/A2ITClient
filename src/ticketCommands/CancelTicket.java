package ticketCommands;

import a2itclient.CallPurpose;
import a2itclient.Contract2;
import ticketEvents.TicketCancelled;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant la commande d'annulation de ticket
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class CancelTicket extends TicketCommand {

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Contructeur principal de la classe CancelTicket
     */
    public CancelTicket() {
    }

    /**
     * Constructeur secondaire de la classe CancelTicket
     *
     * @param ticketCancelled événement d'annulation de ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public CancelTicket(TicketCancelled ticketCancelled, CallPurpose callPurpose, Contract2 currentContract) {
        super(ticketCancelled.getDate(), ticketCancelled.getDate(), ticketCancelled.getTicketInfos(), callPurpose, currentContract);

        setStatus("denied");
        setEvent("canceled");
        this.technicalReason = ticketCancelled.getTicketInfos().getTechnicalReason();
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
