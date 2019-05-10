package a2itclient;

/**
 * Classe définissant un owner
 *
 * @author Thierry Baribaud
 * @version 1.04
 */
public class Owner {

    /**
     * Label
     */
    private String label;

    /**
     * Logo URL
     */
    private String logoUrl;

    /**
     * Constructeur principal de la classe
     */
    public Owner() {
    }

    /**
     * Constructeur secondraire de la classe
     *
     * @param label label du owner
     * @param logoUrl logoUrl du owner
     */
    public Owner(String label, String logoUrl) {
        this.label = label;
        this.logoUrl = logoUrl;
    }

    /**
     * @return retourne le label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label définit le label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return retourne le logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * @param logoUrl définit le logoUrl
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * Retourne le contenu du owner
     *
     * @return retourne le contenu du owner
     */
    @Override
    public String toString() {
        return "owner:{"
                + "label:" + label
                + ", logoUrl:" + logoUrl
                + "}";
    }
}
