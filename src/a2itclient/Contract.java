package a2itclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Classe décrivant un contrat
 * @author Thierry Baribaud
 * @version 1.06
 */
public class Contract {
    
    /**
     * Liens du contrat
     */
    @JsonProperty("_links")
    private Map<String, HALLink> links;
    
    /**
     * L'identifiant unique du contrat
     */
    private String id;
    
    /**
     * Le label du contrat
     */
    private String label;
    
    /**
     * Le topic du contrat
     */
    private String topic;
    
    /**
     * la référence du contrat
     */
    private String reference;
    
    /**
     * Le tier du contrat
     */
    private ThirdParty thirdParty;

    
    /**
     * Dernière date de mise à jour
     */
    private String lastUpdateDate;
    
    /**
     * Date de création 
     */
    private String creationDate;
    
    /**
     * Date de début du contrat
     */
    private String startDate;
    
    /**
     * Date de fin du contrat
     */
    private String endDate;
    
    /**
     * @return Retourne les liens de l'asset
     */
    public Map<String, HALLink> getLinks() {
        return links;
    }

    /**
     * @param links Définit les liens de l'asset
     */
    public void setLinks(Map<String, HALLink> links) {
        this.links = links;
    }

    /**
     * @return Retourne la date de dernière mise à jour
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate Définit la date de dernière mise à jour
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @return Retourne la date de création
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate Définit la date de création
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the thirdParty
     */
    public ThirdParty getThirdParty() {
        return thirdParty;
    }

    /**
     * @param thirdParty the thirdParty to set
     */
    public void setThirdParty(ThirdParty thirdParty) {
        this.thirdParty = thirdParty;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
