package ticketCommands;

import a2itclient.Address;
import a2itclient.CallPurpose;
import a2itclient.Contract2;
import ticketEvents.InterventionFinished;
import a2itclient.Location;
import a2itclient.TicketInfos;

/**
 * Classe décrivant la commande de fin d'intervention
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class FinishIntervention extends TicketCommand {

    /**
     * Contructeur principal de la classe FinishtIntervention
     */
    public FinishIntervention() {
    }

    /**
     * Constructeur secondaire de la classe FinishtIntervention
     *
     * @param interventionFinished événement de début d'intervention
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public FinishIntervention(InterventionFinished interventionFinished, CallPurpose callPurpose, Contract2 currentContract) {
        super(interventionFinished.getFinishedDate(), interventionFinished.getDate(), interventionFinished.getTicketInfos(), callPurpose, currentContract);

        // La description de la demande est inutile pour cette commande
        setDescription(null);
        setStatus("pending");
        setEvent("end");
        
    }

    /**
     * @return Retourne la commande CloseTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "finishIntervention:{"
                + super.toString()
                + "}";
    }
}
