package a2itclient;

/**
 * Classe décrivant une durée
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class Duration {

    /**
     * Durée
     */
    private int value;

    /**
     * Unité
     */
    private String unit;

    /**
     * @return retourne la durée
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value définit la durée
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return retourne l'unité
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit définit l'unité
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retourne le contenu de la durée
     *
     * @return retourne le contenu de la durée
     */
    @Override
    public String toString() {
        return "duration:{"
                + "value:" + value
                + ", unit:" + unit
                + "}";
    }
}
