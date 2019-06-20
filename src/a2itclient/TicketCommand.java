package a2itclient;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant une commande sur les tickets
 * @author Thierry Baribaud
 * @version 1.29
 */
public class TicketCommand {

    /**
     * Niveau de criticité du ticket : min=default=0 à max=5
     * 0 : Non précisé 
     * 1 : Non urgente 
     * 2 : Urgente 
     * 3 : Stratégique 
     * 4 : Prioritaire
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int criticalLevel;

    /**
     * Contructeur principal de la classe CloseTicket
     */
    public TicketCommand() {
        criticalLevel = 0;
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
                + "criticalLevel:" + getCriticalLevel()
                + "}";
    }
}
