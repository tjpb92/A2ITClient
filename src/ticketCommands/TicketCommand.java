package ticketCommands;

import a2itclient.CallPurpose;
import a2itclient.Contract2;
import a2itclient.TicketInfos;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant une commande sur les tickets
 *
 * @author Thierry Baribaud
 * @version 1.31
 */
public class TicketCommand {

    /**
     * Type d'opération
     */
    private String workType;

    /**
     * Entité à l'origine de la demande
     */
    private String origin;

    /**
     * Niveau de criticité du ticket : min=default=0 à max=5 0 : Non précisé 1 :
     * Non urgente 2 : Urgente 3 : Stratégique 4 : Prioritaire
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int criticalLevel;

    /**
     * Contructeur principal de la classe TicketCommand
     */
    public TicketCommand() {
        workType = "corrective";
        origin = "other";
        criticalLevel = 0;
    }

    /**
     * Constructeur secondaire de la classe TicketCommand
     *
     * @param ticketInfos informations sur le ticket
     * @param callPurpose raison d'appel
     * @param currentContract contrant courant
     */
    public TicketCommand(TicketInfos ticketInfos, CallPurpose callPurpose, Contract2 currentContract) {
        this();

        criticalLevel = ticketInfos.getCriticalLevel();
    }

    /**
     * @return retourne le type d'opération
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType définit le type d'opération
     */
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    /**
     * @return retourne l'entité à l'origine de la demande
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin définit l'entité à l'origine de la demande
     */
    public void setOrigin(String origin) {
        this.origin = origin;

    }

    /**
     * @return retourne le niveau de criticité du ticket
     */
    public int getCriticalLevel() {
        return criticalLevel;
    }

    /**
     * @param criticalLevel définit le niveau de criticité du ticket
     */
    public void setCriticalLevel(int criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    /**
     * @return Retourne la commande TicketCommand sous forme textuelle
     */
    @Override
    public String toString() {
        return "ticketCommand:{"
                + "workType:" + getWorkType()
                + ", origin:" + getOrigin()
                + ", criticalLevel:" + getCriticalLevel()
                + "}";
    }
}
