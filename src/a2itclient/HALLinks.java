package a2itclient;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe définissant une liste de liens Hypextest
 *
 * @author Thierry Baribaud
 * @version 1.06
 */
public class HALLinks {

    /**
     * Liste des liens
     */
    @JsonProperty("_links")
    private Map<String, HALLink> links;

    /**
     * Constructeur principal de la classe Links
     */
    public HALLinks() {
        links = new HashMap<>();
    }

    /**
     * Ajoute un lien à la liste
     *
     * @param name nom du lien
     * @param link lien
     */
    public void put(String name, HALLink link) {
        getLinks().put(name, link);
    }

    /**
     * @return retourne la liste des liens
     */
    @JsonSetter("_links")
    public Map<String, HALLink> getLinks() {
        return links;
    }

    /**
     * @param links définit la liste des liens
     */
    @JsonGetter("_links")
    public void setLinks(Map<String, HALLink> links) {
        this.links = links;
    }
}
