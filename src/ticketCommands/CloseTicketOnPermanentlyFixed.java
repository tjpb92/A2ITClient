package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import a2itclient.Location;
import ticketEvents.PermanentlyFixed;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant la commande de clôture de ticket sur réparation définitive
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class CloseTicketOnPermanentlyFixed extends TicketCommand {

    /**
     * Nature de la panne
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String technicalReason;

    /**
     * Contructeur principal de la classe FixPermanently
     */
    public CloseTicketOnPermanentlyFixed() {
    }

    /**
     * Constructeur secondaire de la classe FixPermanently
     *
     * @param permanentlyFixed événement de clôture du ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public CloseTicketOnPermanentlyFixed(PermanentlyFixed permanentlyFixed, CallPurpose callPurpose, Contract2 currentContract) {
        super(permanentlyFixed.getClosedDate(), permanentlyFixed.getDate(), permanentlyFixed.getTicketInfos(), callPurpose, currentContract);

        setStatus("closed");
        setEvent("solved");
        this.technicalReason = permanentlyFixed.getTicketInfos().getTechnicalReason();
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
