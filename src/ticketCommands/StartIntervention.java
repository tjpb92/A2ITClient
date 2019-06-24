package ticketCommands;

import a2itclient.CallPurpose;
import a2itclient.Contract2;
import ticketEvents.InterventionStarted;

/**
 * Classe décrivant la commande de début d'intervention
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class StartIntervention extends TicketCommand {

    /**
     * Contructeur principal de la classe StartIntervention
     */
    public StartIntervention() {
    }

    /**
     * Constructeur secondaire de la classe StartIntervention
     *
     * @param interventionStarted événement de début d'intervention
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public StartIntervention(InterventionStarted interventionStarted, CallPurpose callPurpose, Contract2 currentContract) {
        super(interventionStarted.getStartedDate(), interventionStarted.getDate(), interventionStarted.getTicketInfos(), callPurpose, currentContract);

        // La description de la demande est inutile pour cette commande
        setDescription(null);
        setStatus("pending");
        setEvent("start");
    }

    /**
     * @return Retourne la commande CloseTicket sous forme textuelle
     */
    @Override
    public String toString() {
        return "startIntervention:{"
                + super.toString()
                + "}";
    }
}
