package ticketCommands;

import ticketEvents.ClosedQuoteRequested;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant la commande de clôture de ticket sur demande de devis
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class CloseTicketOnQuoteRequested extends TicketCommand {

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Contructeur principal de la classe RequestQuote
     */
    public CloseTicketOnQuoteRequested() {
    }

    /**
     * Constructeur secondaire de la classe RequestQuote
     *
     * @param closedQuoteRequested événement de clôture du ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public CloseTicketOnQuoteRequested(ClosedQuoteRequested closedQuoteRequested, CallPurpose callPurpose, Contract2 currentContract) {
        super(closedQuoteRequested.getClosedDate(), closedQuoteRequested.getDate(), closedQuoteRequested.getTicketInfos(), callPurpose, currentContract);

        setStatus("hold");
        setEvent("quote_request");
        this.technicalReason = closedQuoteRequested.getTicketInfos().getTechnicalReason();
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
