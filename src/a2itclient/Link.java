package a2itclient;

/**
 * Classe définissant un lien Hypertext
 * @author Thierry Baribaud
 * @version 1.04
 */
public class Link {
    
    /**
     * Référence du lien
     */
    private String href;
    
    /**
     * Constructeur principal du lien
     */
    public Link(){}
    
    /**
     * Constructeur secondaire du lien
     * @param href référence du lien
     */
    public Link(String href){
        this.href = href;
    }

    /**
     * @return retourne la référence du lien
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href définit la référence du lien
     */
    public void setHref(String href) {
        this.href = href;
    }
    
    /**
     * Retourne le contenu du lien
     *
     * @return retourne le contenu du lien
     */
    @Override
    public String toString() {
        return "link:{"
                + "href:" + getHref()
                + "}";
    }
}
