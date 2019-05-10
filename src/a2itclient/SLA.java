package a2itclient;

/**
 * Classe décrivant un Service Level Agreement : SLA
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class SLA {

    /**
     * Durée estimée de l'intervention
     */
    private Duration estimatedInterventionDuration;

    /**
     * Durée maximale pour intervenir
     */
    private Duration maxTimeToIntervention;

    /**
     * Durée maximale pour réparer
     */
    private Duration maxTimeToRecovery;

    /**
     * @return retourne la durée estimée de l'intervention
     */
    public Duration getEstimatedInterventionDuration() {
        return estimatedInterventionDuration;
    }

    /**
     * @param estimatedInterventionDuration définit la durée estimée de
     * l'intervention
     */
    public void setEstimatedInterventionDuration(Duration estimatedInterventionDuration) {
        this.estimatedInterventionDuration = estimatedInterventionDuration;
    }

    /**
     * @return retourne la durée maximale pour intervenir
     */
    public Duration getMaxTimeToIntervention() {
        return maxTimeToIntervention;
    }

    /**
     * @param maxTimeToIntervention définit la durée maximale pour intervenir
     */
    public void setMaxTimeToIntervention(Duration maxTimeToIntervention) {
        this.maxTimeToIntervention = maxTimeToIntervention;
    }

    /**
     * @return retourne la durée maximale pour réparer
     */
    public Duration getMaxTimeToRecovery() {
        return maxTimeToRecovery;
    }

    /**
     * @param maxTimeToRecovery définit la durée maximale pour réparer
     */
    public void setMaxTimeToRecovery(Duration maxTimeToRecovery) {
        this.maxTimeToRecovery = maxTimeToRecovery;
    }

    /**
     * Retourne le contenu du SLA
     *
     * @return retourne le contenu du SLA
     */
    @Override
    public String toString() {
        return "sla:{"
                + "estimatedInterventionDuration:" + estimatedInterventionDuration
                + ", maxTimeToIntervention:" + maxTimeToIntervention
                + ", maxTimeToRecovery:" + maxTimeToRecovery
                + "}";
    }
}
